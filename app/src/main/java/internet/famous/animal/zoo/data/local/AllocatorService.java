package internet.famous.animal.zoo.data.local;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import javax.inject.Inject;

import io.objectbox.Box;

public final class AllocatorService {
  private final ListeningExecutorService executor;
  private final Box<Animal> animals;
  private final Box<Keeper> keepers;
  private final Box<Pen> pens;
  private final Box<Species> species;

  @Inject
  AllocatorService(
      ListeningExecutorService executor,
      Box<Animal> animals,
      Box<Keeper> keepers,
      Box<Pen> pens,
      Box<Species> species) {
    this.executor = executor;
    this.animals = animals;
    this.keepers = keepers;
    this.pens = pens;
    this.species = species;
  }

  public ListenableFuture<Boolean> allocateAnimals() {
    return executor.submit(this::allocateAnimalsFn);
  }

  public ListenableFuture<Boolean> allocateKeepers() {
    return executor.submit(this::allocateKeepersFn);
  }

  private Boolean allocateAnimalsFn() {
    boolean success = true;
    for (Animal animal :
        animals.query().filter(animal -> animal.pen.getTarget() == null).build().find()) {
      for (Pen pen : pens.query().build().find()) {
        if (pen.canAccommodate(animal)) {
          animal.pen.setTarget(pen);
          animals.put(animal);
          pens.put(pen);
          break;
        }
      }
      if (animal.pen.getTarget() == null) {
        success = false;
      }
    }
    return success;
  }

  private Boolean allocateKeepersFn() {
    return true;
  }
}
