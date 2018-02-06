package internet.famous.animal.zoo.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "animals")
public final class AnimalEntity {
  @PrimaryKey public int id;
  public String name;
  public int speciesId;
  public int penId;
}
