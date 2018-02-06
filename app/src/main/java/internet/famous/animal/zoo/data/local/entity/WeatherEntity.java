package internet.famous.animal.zoo.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "weather")
public final class WeatherEntity {
  @PrimaryKey
  @SerializedName("id")
  public int id;

  @SerializedName("main.temp")
  public double temperature;

  @SerializedName("weather.icon")
  public String icon;
}
