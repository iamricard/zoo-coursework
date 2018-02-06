package internet.famous.animal.zoo.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pens")
public final class PenEntity {
  @PrimaryKey public int id;
  public int keeperId;
  public double landSpace = -1;
  public double waterSpace = -1;
  public double airSpace = -1;
  public boolean isPettable = false;
}
