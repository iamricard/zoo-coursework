package internet.famous.animal.zoo.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "species")
public final class SpeciesEntity {
  @PrimaryKey public int id;
  public String name;
  public String emoji;
  public double landNeeded = -1;
  public double waterNeeded = -1;
  public double airNeeded = -1;
  public boolean isPettable;
}
