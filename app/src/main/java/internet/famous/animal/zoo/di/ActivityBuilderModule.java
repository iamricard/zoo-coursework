package internet.famous.animal.zoo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;
import internet.famous.animal.zoo.ui.create.CreateKeeperActivity;
import internet.famous.animal.zoo.ui.create.CreateSpeciesActivity;
import internet.famous.animal.zoo.ui.main.MainActivity;

@Module
interface ActivityBuilderModule {
  @ContributesAndroidInjector
  MainActivity contributeMainActivity();

  @ContributesAndroidInjector
  CreateAnimalActivity contributeCreateAnimalActivity();

  @ContributesAndroidInjector
  CreateKeeperActivity contributeCreateKeeperActivity();

  @ContributesAndroidInjector
  CreateSpeciesActivity contributeCreateSpeciesActivity();
}
