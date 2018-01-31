package internet.famous.animal.zoo;

import android.app.Activity;
import android.app.Application;
import android.support.v4.util.Pair;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.di.DaggerAppComponent;
import io.objectbox.Box;

public final class App extends Application implements HasActivityInjector {
  @Inject DispatchingAndroidInjector<Activity> activityDispatchingInjector;
  @Inject Box<Animal> animalBox;
  @Inject Box<Keeper> keeperBox;
  @Inject Box<Pen> penBox;
  @Inject Box<Species> speciesBox;

  @Override
  public void onCreate() {
    super.onCreate();
    DaggerAppComponent.builder().application(this).build().inject(this);
    seedData();
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingInjector;
  }

  private void seedData() {
    // Species seeds
    speciesBox.removeAll();
    Species sloth = Species.newLandSpecies("sloth", "\uD83D\uDE34", 3, false);
    speciesBox.put(sloth);
    Species penguin = Species.newAmphibiousSpecies("penguin", "\uD83D\uDC27", 2, 4);
    speciesBox.put(penguin);
    Species goat = Species.newLandSpecies("goat", "\uD83D\uDC10", 3, true);
    speciesBox.put(goat);
    Species dog = Species.newLandSpecies("dog", "\uD83D\uDC15", 3.5, true);
    speciesBox.put(dog);
    Species owl = Species.newAirSpecies("owl", "\uD83E\uDD89", 20);
    speciesBox.put(owl);
    Species parrot = Species.newAirSpecies("parrot", "\uD83D\uDC26", 10);
    speciesBox.put(parrot);
    Species dolphin = Species.newWaterSpecies("dolphin", "\uD83D\uDC2C", 40);
    speciesBox.put(dolphin);
    Species hippo = Species.newAmphibiousSpecies("hippo", "\uD83E\uDD8F", 10, 20);
    speciesBox.put(hippo);
    Species cat = Species.newLandSpecies("cat", "\uD83D\uDC08", 4, true);
    speciesBox.put(cat);

    // Animal seeds
    animalBox.removeAll();
    List<Pair<String, Species>> animals =
        ImmutableList.of(
            new Pair<>("Pipoca", dog),
            new Pair<>("Flipper", dolphin),
            new Pair<>("Hedwig", owl),
            new Pair<>("Pingu", penguin),
            new Pair<>("Totem", dog),
            new Pair<>("Atena", dog),
            new Pair<>("Mala", cat),
            new Pair<>("Henry", parrot));
    for (Pair<String, Species> animal : animals) {
      Animal a = new Animal();
      a.name = animal.first;
      a.species.setTarget(animal.second);
      animalBox.put(a);
    }

    // Keeper seeds
    keeperBox.removeAll();
    Keeper hardip = new Keeper("Hardip");
    keeperBox.put(hardip);
    Keeper alex = new Keeper("Alex");
    keeperBox.put(alex);
    Keeper farhad = new Keeper("Farhad");
    keeperBox.put(farhad);
    Keeper alan = new Keeper("Alan");
    keeperBox.put(alan);

    // Pen seeds
    penBox.removeAll();

    Pen landPen = Pen.landPen(100, true);
    landPen.keeper.setTarget(hardip);
    penBox.put(landPen);

    landPen = Pen.landPen(200, false);
    landPen.keeper.setTarget(hardip);
    penBox.put(landPen);

    Pen hybridPen = Pen.amphibiousPen(100, 200);
    hybridPen.keeper.setTarget(alan);
    penBox.put(hybridPen);

    Pen aquarium = Pen.aquarium(100);
    aquarium.keeper.setTarget(alex);
    penBox.put(aquarium);

    Pen aviary = Pen.aviary(100);
    aviary.keeper.setTarget(farhad);
    penBox.put(aviary);
  }
}
