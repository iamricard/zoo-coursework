package internet.famous.animal.zoo.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import internet.famous.animal.zoo.data.local.ZooDatabase;
import internet.famous.animal.zoo.data.local.dao.WeatherDao;
import internet.famous.animal.zoo.data.local.entity.WeatherEntity;
import internet.famous.animal.zoo.data.remote.ApiConstants;
import internet.famous.animal.zoo.data.remote.OpenWeatherMapService;
import internet.famous.animal.zoo.data.remote.RequestInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
  @Binds
  abstract Context bindApplicationContext(Application application);

  @Provides
  @Singleton
  static OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    okHttpClient.addInterceptor(new RequestInterceptor());
    return okHttpClient.build();
  }

  @Provides
  @Singleton
  static OpenWeatherMapService provideOpenWeatherMapService(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(ApiConstants.ENDPOINT)
        .addConverterFactory(
            GsonConverterFactory.create(WeatherEntity.WeatherDeserializer.create()))
        .client(okHttpClient)
        .build()
        .create(OpenWeatherMapService.class);
  }

  @Provides
  @Singleton
  static ZooDatabase provideMovieDatabase(Application application) {
    return Room.databaseBuilder(application, ZooDatabase.class, "zoo.db")
        .fallbackToDestructiveMigration()
        .build();
  }

  @Provides
  @Singleton
  static WeatherDao provideMovieDao(ZooDatabase movieDatabase) {
    return movieDatabase.weatherDao();
  }
}
