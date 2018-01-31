package internet.famous.animal.zoo.ui.create;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.databinding.ActivityCreateAnimalBinding;
import internet.famous.animal.zoo.ui.BaseActivity;

public final class CreateAnimalActivity extends BaseActivity<ActivityCreateAnimalBinding> {
  @Inject SpeciesGridAdapter speciesGridAdapter;

  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_animal;
  }
}
