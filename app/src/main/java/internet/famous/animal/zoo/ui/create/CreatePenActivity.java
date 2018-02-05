package internet.famous.animal.zoo.ui.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ActivityCreatePenBinding;

public final class CreatePenActivity extends BaseCreateActivity<Pen, ActivityCreatePenBinding> {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.setActivity(this);
    binding.radioButtons.setOnCheckedChangeListener(this::onRadioBtnSelected);
    binding.saveBtn.setOnClickListener(this::onSaveBtnClicked);
  }

  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_pen;
  }

  private void onRadioBtnSelected(RadioGroup group, int id) {
    if (id == 1) {
      data.airSpace = -1;
      data.landSpace = 0;
      data.waterSpace = 0;
      binding.airSpace.setVisibility(View.VISIBLE);
      binding.landSpace.setVisibility(View.GONE);
      binding.waterSpace.setVisibility(View.GONE);
    } else if (id == 2) {
      data.airSpace = 0;
      data.landSpace = -1;
      data.waterSpace = 0;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.VISIBLE);
      binding.waterSpace.setVisibility(View.GONE);
    } else if (id == 3) {
      data.airSpace = 0;
      data.landSpace = 0;
      data.waterSpace = -1;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.GONE);
      binding.waterSpace.setVisibility(View.VISIBLE);
    } else {
      data.airSpace = 0;
      data.landSpace = -1;
      data.waterSpace = -1;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.VISIBLE);
      binding.waterSpace.setVisibility(View.VISIBLE);
    }
    binding.spaceRequirements.setVisibility(View.VISIBLE);
  }

  public void onAirSpaceChanged(CharSequence s, int start, int before, int count) {
    data.airSpace = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  public void onLandSpaceChanged(CharSequence s, int start, int before, int count) {
    data.landSpace = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  public void onWaterSpaceChanged(CharSequence s, int start, int before, int count) {
    data.waterSpace = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  private void activateSaveBtnIfPossible() {
    if (data.airSpace > -1 && data.landSpace > -1 && data.waterSpace > -1) {
      binding.saveBtn.setEnabled(true);
    } else {
      binding.saveBtn.setEnabled(false);
    }
  }
}
