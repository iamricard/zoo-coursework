package internet.famous.animal.zoo.data.local;

import javax.inject.Inject;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public final class Species {
  @Id public long id;
  public String name;
  public String emoji;
  public double landNeeded = -1;
  public double waterNeeded = -1;
  public double airNeeded = -1;
  public boolean isPettable;
  @Backlink public ToMany<Animal> animals;

  @Inject
  public Species() {}

  public Environment getEnvironment() {
    if (airNeeded > 0) {
      return Environment.AIR;
    } else if (isPettable) {
      return Environment.PETTING;
    } else if (landNeeded <= 0) {
      return Environment.WATER;
    } else if (waterNeeded <= 0) {
      return Environment.DRY;
    }
    return Environment.HYBRID;
  }
}
