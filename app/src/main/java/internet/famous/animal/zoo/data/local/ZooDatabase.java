package internet.famous.animal.zoo.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import internet.famous.animal.zoo.data.local.dao.WeatherDao;
import internet.famous.animal.zoo.data.local.entity.WeatherEntity;

@Database(
  entities = {WeatherEntity.class},
  version = 1,
  exportSchema = false
)
public abstract class ZooDatabase extends RoomDatabase {
  public abstract WeatherDao weatherDao();
}
