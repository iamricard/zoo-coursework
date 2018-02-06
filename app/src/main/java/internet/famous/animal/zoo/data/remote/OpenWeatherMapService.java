package internet.famous.animal.zoo.data.remote;

import internet.famous.animal.zoo.data.local.entity.WeatherEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
  @GET("weather?units=metric")
  Call<WeatherEntity> fetchWeather(@Query("id") int id);
}
