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
    if (id == R.id.air_radio_btn) {
      data.airSpace = -1;
      data.landSpace = 0;
      data.waterSpace = 0;
      binding.airSpace.setVisibility(View.VISIBLE);
      binding.landSpace.setVisibility(View.GONE);
      binding.waterSpace.setVisibility(View.GONE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    } else if (id == R.id.dry_radio_btn) {
      data.airSpace = 0;
      data.landSpace = -1;
      data.waterSpace = 0;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.VISIBLE);
      binding.waterSpace.setVisibility(View.GONE);
      binding.pettableCheckbox.setVisibility(View.VISIBLE);
    } else if (id == R.id.water_radio_btn) {
      data.airSpace = 0;
      data.landSpace = 0;
      data.waterSpace = -1;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.GONE);
      binding.waterSpace.setVisibility(View.VISIBLE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    } else {
      data.airSpace = 0;
      data.landSpace = -1;
      data.waterSpace = -1;
      binding.airSpace.setVisibility(View.GONE);
      binding.landSpace.setVisibility(View.VISIBLE);
      binding.waterSpace.setVisibility(View.VISIBLE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    }
    binding.spaceRequirements.setVisibility(View.VISIBLE);
  }

  public void onAirSpaceChanged(CharSequence num, int start, int before, int count) {
    data.airSpace = safeDoubleParse(num);
    activateSaveBtnIfPossible();
  }

  public void onLandSpaceChanged(CharSequence num, int start, int before, int count) {
    data.landSpace = safeDoubleParse(num);
    activateSaveBtnIfPossible();
  }

  public void onWaterSpaceChanged(CharSequence num, int start, int before, int count) {
    data.waterSpace = safeDoubleParse(num);
    activateSaveBtnIfPossible();
  }

  private double safeDoubleParse(CharSequence num) {
    try {
      return Double.parseDouble(num.toString());
    } catch (NumberFormatException exception) {
      return 0.0;
    }
  }

  private void activateSaveBtnIfPossible() {
    data.isPettable =
        binding.pettableCheckbox.isChecked() && data.waterSpace == 0 && data.airSpace == 0;
    if (data.airSpace > -1 && data.landSpace > -1 && data.waterSpace > -1) {
      binding.saveBtn.setEnabled(true);
    } else {
      binding.saveBtn.setEnabled(false);
    }
  }
}
