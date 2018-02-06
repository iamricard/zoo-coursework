package internet.famous.animal.zoo.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "keepers")
public final class KeeperEntity {
  @PrimaryKey public long id;
  public String name;
}
