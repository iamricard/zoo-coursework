package internet.famous.animal.zoo.data.local;

import javax.inject.Inject;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public final class Pen {
  @Id public long id;
  public ToOne<Keeper> keeper;
  public double landSpace = -1;
  public double waterSpace = -1;
  public double airSpace = -1;
  public boolean isPettable = false;
  @Backlink public ToMany<Animal> animals;

  public static Pen landPen(long landSpace, boolean isPettable) {
    Pen p = new Pen();
    p.landSpace = landSpace;
    p.isPettable = isPettable;
    return p;
  }

  public static Pen aquarium(long waterSpace) {
    Pen p = new Pen();
    p.waterSpace = waterSpace;
    return p;
  }

  public static Pen amphibiousPen(long landSpace, long waterSpace) {
    Pen p = new Pen();
    p.landSpace = landSpace;
    p.waterSpace = waterSpace;
    return p;
  }

  public static Pen aviary(long airSpace) {
    Pen p = new Pen();
    p.airSpace = airSpace;
    return p;
  }

  @Inject
  public Pen() {}

  @Override
  public String toString() {
    if (landSpace > 0) {
      if (waterSpace > 0) {
        return "Hybrid";
      } else if (isPettable) {
        return "Petting";
      }
      return "Dry";
    } else if (waterSpace > 0) {
      return "Aquarium";
    }
    return "Aviary";
  }

  public boolean canAccomodate(Animal animal) {
    Species species = animal.species.getTarget();
    double availableAir = airSpace - usedAir();
    double availableLand = landSpace - usedLand();
    double availableWater = waterSpace - usedWater();
    if (species.landNeeded > 0) {
      if (species.waterNeeded > 0) {
        return availableLand >= species.landNeeded && availableWater >= species.waterNeeded;
      } else if (species.isPettable) {
        return availableLand >= species.landNeeded && isPettable;
      } else {
        return availableLand >= species.landNeeded;
      }
    } else if (species.waterNeeded > 0) {
      return availableWater >= species.waterNeeded;
    } else if (species.airNeeded > 0) {
      return availableAir >= species.airNeeded;
    }
    return false;
  }

  private double usedAir() {
    return animals
        .stream()
        .map(a -> a.species.getTarget().airNeeded)
        .reduce(0.0, (used, needed) -> used + needed);
  }

  private double usedLand() {
    return animals
        .stream()
        .map(a -> a.species.getTarget().landNeeded)
        .reduce(0.0, (used, needed) -> used + needed);
  }

  private double usedWater() {
    return animals
        .stream()
        .map(a -> a.species.getTarget().waterNeeded)
        .reduce(0.0, (used, needed) -> used + needed);
  }
}
