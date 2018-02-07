package internet.famous.animal.zoo.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
  @GET("weather?units=metric")
  Call<Weather> fetchWeather(@Query("id") int id);
}
