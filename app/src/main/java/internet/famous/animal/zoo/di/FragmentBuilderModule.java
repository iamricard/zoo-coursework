package internet.famous.animal.zoo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import internet.famous.animal.zoo.ui.create.SelectSpeciesBottomSheetFragment;
import internet.famous.animal.zoo.ui.main.AnimalListFragment;
import internet.famous.animal.zoo.ui.main.AssignAnimalBottomSheetFragment;
import internet.famous.animal.zoo.ui.main.AssignPenBottomSheetFragment;
import internet.famous.animal.zoo.ui.main.KeeperListFragment;
import internet.famous.animal.zoo.ui.main.PenListFragment;
import internet.famous.animal.zoo.ui.main.SpeciesListFragment;

@Module
interface FragmentBuilderModule {
  @ContributesAndroidInjector
  AnimalListFragment contributeAnimalListFragment();

  @ContributesAndroidInjector
  KeeperListFragment contributeKeeperListFragment();

  @ContributesAndroidInjector
  PenListFragment contributePenListFragment();

  @ContributesAndroidInjector
  SpeciesListFragment contributeSpeciesListFragment();

  @ContributesAndroidInjector
  SelectSpeciesBottomSheetFragment contributeSelectSpeciesBottomSheetFragment();

  @ContributesAndroidInjector
  AssignAnimalBottomSheetFragment contributeAssignAnimalBottomSheetFragment();

  @ContributesAndroidInjector
  AssignPenBottomSheetFragment contributeAssignPenBottomSheetFragment();
}
