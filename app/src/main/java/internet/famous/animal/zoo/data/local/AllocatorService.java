package internet.famous.animal.zoo.data.local;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.objectbox.Box;

public final class AllocatorService {
  private final ListeningExecutorService executor;
  private final Box<Animal> animals;
  private final Box<Keeper> keepers;
  private final Box<Pen> pens;

  @Inject
  AllocatorService(
      ListeningExecutorService executor, Box<Animal> animals, Box<Keeper> keepers, Box<Pen> pens) {
    this.executor = executor;
    this.animals = animals;
    this.keepers = keepers;
    this.pens = pens;
  }

  public ListenableFuture<Boolean> allocateAnimals() {
    return executor.submit(
        () -> {
          boolean success = true;
          for (Animal animal :
              animals.query().filter(animal -> animal.pen.isNull()).build().find()) {
            for (Pen pen : pens.query().build().find()) {
              if (pen.canAccommodate(animal)) {
                animal.pen.setTarget(pen);
                animals.put(animal);
                pens.put(pen);
                break;
              }
            }
            if (animal.pen.isNull()) {
              success = false;
            }
          }
          return success;
        });
  }

  public ListenableFuture<Boolean> allocateKeepers() {
    return executor.submit(
        () -> {
          Random rand = new Random();
          List<Keeper> keeperList = keepers.query().build().find();
          int keeperCount = keeperList.size();
          for (Pen pen : pens.query().filter(pen -> pen.keeper.isNull()).build().find()) {
            Keeper keeper = keeperList.get(rand.nextInt(keeperCount));
            pen.keeper.setTarget(keeper);
            pens.put(pen);
            keepers.put(keeper);
          }
          return true;
        });
  }
}
