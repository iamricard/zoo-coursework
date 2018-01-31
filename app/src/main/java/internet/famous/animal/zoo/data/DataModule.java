package internet.famous.animal.zoo.data;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.MyObjectBox;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Species;
import io.objectbox.Box;
import io.objectbox.BoxStore;

@Module
public final class DataModule {
  @Provides
  @Singleton
  BoxStore provideBoxStore(Application application) {
    return MyObjectBox.builder().androidContext(application).build();
  }

  @Provides
  @Singleton
  Box<Animal> provideAnimalBox(BoxStore boxStore) {
    return boxStore.boxFor(Animal.class);
  }

  @Provides
  @Singleton
  Box<Keeper> provideKeeperBox(BoxStore boxStore) {
    return boxStore.boxFor(Keeper.class);
  }

  @Provides
  @Singleton
  Box<Pen> providePenBox(BoxStore boxStore) {
    return boxStore.boxFor(Pen.class);
  }

  @Provides
  @Singleton
  Box<Species> provideSpeciesBox(BoxStore boxStore) {
    return boxStore.boxFor(Species.class);
  }
}
