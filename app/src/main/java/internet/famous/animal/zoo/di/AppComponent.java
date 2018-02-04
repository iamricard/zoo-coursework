package internet.famous.animal.zoo.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import internet.famous.animal.zoo.App;
import internet.famous.animal.zoo.data.DataModule;

@Singleton
@Component(
  modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    DataModule.class,
    ActivityBuilderModule.class,
    FragmentBuilderModule.class
  }
)
public interface AppComponent extends AndroidInjector<DaggerApplication> {
  void inject(App app);

  @Override
  void inject(DaggerApplication instance);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}
