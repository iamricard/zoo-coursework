package internet.famous.animal.zoo.data.local;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public final class Species {
  @Id public long id;
  public String name;
  public String emoji;
  public double landNeeded;
  public double waterNeeded;
  public double airNeeded;
  public boolean isPettable;
  @Backlink public ToMany<Animal> animals;

  public static Species newLandSpecies(
      String name, String emoji, double landNeeded, boolean isPettable) {
    return create(name, emoji, landNeeded, /* waterNeeded= */ 0, /* airNeeded= */ 0, isPettable);
  }

  public static Species newWaterSpecies(String name, String emoji, double waterNeeded) {
    return create(
        name, emoji, /* landNeeded= */ 0, waterNeeded, /* airNeeded= */ 0, /* isPettable= */ false);
  }

  public static Species newAirSpecies(String name, String emoji, double airNeeded) {
    return create(
        name, emoji, /* landNeeded= */ 0, /* waterNeeded= */ 0, airNeeded, /* isPettable= */ false);
  }

  public static Species newAmphibiousSpecies(
      String name, String emoji, double landNeeded, double waterNeeded) {
    return create(
        name, emoji, landNeeded, waterNeeded, /* airNeeded= */ 0, /* isPettable= */ false);
  }

  private static Species create(
      String name,
      String emoji,
      double landNeeded,
      double waterNeeded,
      double airNeeded,
      boolean isPettable) {
    Species s = new Species();
    s.name = name;
    s.emoji = emoji;
    s.landNeeded = landNeeded;
    s.waterNeeded = waterNeeded;
    s.airNeeded = airNeeded;
    s.isPettable = isPettable;
    return s;
  }
}
