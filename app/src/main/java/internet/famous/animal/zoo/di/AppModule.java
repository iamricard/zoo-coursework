package internet.famous.animal.zoo.di;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.MyObjectBox;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.data.remote.ApiConstants;
import internet.famous.animal.zoo.data.remote.OpenWeatherMapService;
import internet.famous.animal.zoo.data.remote.RequestInterceptor;
import internet.famous.animal.zoo.data.remote.Weather;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
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
        .addConverterFactory(GsonConverterFactory.create(Weather.WeatherDeserializer.create()))
        .client(okHttpClient)
        .build()
        .create(OpenWeatherMapService.class);
  }

  @Binds
  abstract Context bindApplicationContext(Application application);

  @Provides
  @Singleton
  static BoxStore provideBoxStore(Application application) {
    return MyObjectBox.builder().androidContext(application).name("zoo-db").build();
  }

  @Provides
  @Singleton
  static Box<Animal> provideAnimalBox(BoxStore boxStore) {
    return boxStore.boxFor(Animal.class);
  }

  @Provides
  @Singleton
  static Box<Keeper> provideKeeperBox(BoxStore boxStore) {
    return boxStore.boxFor(Keeper.class);
  }

  @Provides
  @Singleton
  static Box<Pen> providePenBox(BoxStore boxStore) {
    return boxStore.boxFor(Pen.class);
  }

  @Provides
  @Singleton
  static Box<Species> provideSpeciesBox(BoxStore boxStore) {
    return boxStore.boxFor(Species.class);
  }
}
