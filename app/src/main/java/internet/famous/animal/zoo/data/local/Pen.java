package internet.famous.animal.zoo.data.local;

import java.util.function.Function;

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

  public boolean canAccommodate(Animal animal) {
    Species species = animal.species.getTarget();
    double availableAir = airSpace - usedSpace(s -> s.airNeeded);
    double availableLand = landSpace - usedSpace(s -> s.landNeeded);
    double availableWater = waterSpace - usedSpace(s -> s.waterNeeded);
    if (species.landNeeded > 0) {
      if (species.waterNeeded > 0) {
        return availableLand >= species.landNeeded && availableWater >= species.waterNeeded;
      }
      if (isPettable) {
        return availableLand >= species.landNeeded && species.isPettable;
      }
      return availableLand >= species.landNeeded && waterSpace <= 0;
    } else if (species.waterNeeded > 0) {
      return availableWater >= species.waterNeeded && landSpace <= 0;
    } else if (species.airNeeded > 0) {
      return availableAir >= species.airNeeded;
    }
    return false;
  }

  private double usedSpace(Function<Species, Double> attrGetter) {
    return animals
        .stream()
        .map(attrGetter.compose(animal -> animal.species.getTarget()))
        .reduce(0.0, Double::sum);
  }
}
