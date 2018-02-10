package internet.famous.animal.zoo.data.local;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import javax.inject.Inject;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public final class Pen {
  private static final Function<Animal, Species> getSpecies = a -> a.species.getTarget();
  @Id public long id;
  public ToOne<Keeper> keeper;
  public double landSpace = -1;
  public double waterSpace = -1;
  public double airSpace = -1;
  public boolean isPettable = false;
  @Backlink public ToMany<Animal> animals;

  @Inject
  public Pen() {}

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

  public Environment getEnvironment() {
    if (airSpace > 0) {
      return Environment.AIR;
    } else if (isPettable) {
      return Environment.PETTING;
    } else if (landSpace <= 0) {
      return Environment.WATER;
    } else if (waterSpace <= 0) {
      return Environment.DRY;
    }
    return Environment.HYBRID;
  }

  private double usedSpace(Function<Species, Double> attrGetter) {
    return Stream.of(animals)
        .map(Function.Util.compose(attrGetter, getSpecies))
        .reduce(0.0, (a, b) -> a + b);
  }
}
