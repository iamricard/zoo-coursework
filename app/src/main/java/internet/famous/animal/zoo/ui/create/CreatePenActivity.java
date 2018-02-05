package internet.famous.animal.zoo.ui.create;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ActivityCreatePenBinding;

public final class CreatePenActivity extends BaseCreateActivity<Pen, ActivityCreatePenBinding> {
  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_pen;
  }
}
