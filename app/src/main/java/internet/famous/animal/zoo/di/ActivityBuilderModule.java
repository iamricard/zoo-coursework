package internet.famous.animal.zoo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;
import internet.famous.animal.zoo.ui.main.MainActivity;

@Module
interface ActivityBuilderModule {
  @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
  MainActivity mainActivity();

  @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
  CreateAnimalActivity createAnimalActivity();
}
