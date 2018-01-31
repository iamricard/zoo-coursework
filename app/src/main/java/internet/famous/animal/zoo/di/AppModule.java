package internet.famous.animal.zoo.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
interface AppModule {
  @Binds
  Context bindApplicationContext(Application application);
}
