package internet.famous.animal.zoo;

import android.app.Application;

import internet.famous.animal.zoo.animal.Animal;
import internet.famous.animal.zoo.keeper.Keeper;
import internet.famous.animal.zoo.pen.Pen;
import internet.famous.animal.zoo.species.Species;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public final class App extends Application {
  private BoxStore boxStore;

  @Override
  public void onCreate() {
    super.onCreate();
    //    EmojiCompat.init(new BundledEmojiCompatConfig(this).setReplaceAll(true));
    boxStore = MyObjectBox.builder().androidContext(this).build();
    seedData();
  }

  public BoxStore getBoxStore() {
    return boxStore;
  }

  private void seedData() {
    // Species seeds
    Box<Species> speciesBox = boxStore.boxFor(Species.class);
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
    Box<Animal> animalBox = boxStore.boxFor(Animal.class);
    animalBox.removeAll();
    Animal pipoca = new Animal();
    pipoca.name = "Pipoca";
    pipoca.species.setTarget(dog);
    animalBox.put(pipoca);
    Animal samantha = new Animal();
    samantha.name = "Samantha";
    samantha.species.setTarget(dog);
    animalBox.put(samantha);
    Animal lucky = new Animal();
    lucky.name = "Lucky";
    lucky.species.setTarget(dog);
    animalBox.put(lucky);
    Animal totem = new Animal();
    totem.name = "Totem";
    totem.species.setTarget(dog);
    animalBox.put(totem);
    Animal atena = new Animal();
    atena.name = "Atena";
    atena.species.setTarget(dog);
    animalBox.put(atena);
    Animal mala = new Animal();
    mala.name = "Mala";
    mala.species.setTarget(cat);
    animalBox.put(mala);
    Animal henry = new Animal();
    henry.name = "Henry";
    henry.species.setTarget(parrot);
    animalBox.put(henry);

    // Keeper seeds
    Box<Keeper> keeperBox = boxStore.boxFor(Keeper.class);
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
    Box<Pen> penBox = boxStore.boxFor(Pen.class);
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
