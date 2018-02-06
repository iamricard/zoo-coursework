package internet.famous.animal.zoo.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import internet.famous.animal.zoo.data.local.entity.WeatherEntity;
import java.util.List;

@Dao
public interface WeatherDao {
  @Query("SELECT * FROM weather")
  LiveData<List<WeatherEntity>> loadWeatherEntities();

  @Query("SELECT * FROM weather WHERE id=:id")
  LiveData<WeatherEntity> getWeather(int id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(WeatherEntity weatherEntities);
}
