package internet.famous.animal.zoo.data;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.dao.WeatherDao;
import internet.famous.animal.zoo.data.local.entity.WeatherEntity;
import internet.famous.animal.zoo.data.remote.OpenWeatherMapService;
import internet.famous.animal.zoo.data.remote.RemoteResource;
import retrofit2.Call;

import static internet.famous.animal.zoo.data.remote.ApiConstants.CITY_ID;

public final class WeatherRepository {
  private final WeatherDao dao;
  private final OpenWeatherMapService service;

  @Inject
  public WeatherRepository(WeatherDao dao, OpenWeatherMapService service) {
    this.dao = dao;
    this.service = service;
  }

  public LiveData<WeatherEntity> loadWeather() {
    return new RemoteResource<WeatherEntity>() {
      @Override
      public void save(WeatherEntity weather) {
        dao.save(weather);
      }

      @Override
      public LiveData<WeatherEntity> loadLocally() {
        return dao.getWeather(CITY_ID);
      }

      @Override
      public Call<WeatherEntity> loadRemotely() {
        return service.fetchWeather(CITY_ID);
      }

      @Override
      public boolean shouldFetch(WeatherEntity entity) {
        return true;
      }
    }.asLiveData();
  }
}
