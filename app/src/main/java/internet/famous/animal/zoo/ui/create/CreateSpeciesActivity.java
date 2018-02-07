package internet.famous.animal.zoo.ui.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.RadioGroup;

import com.vdurmont.emoji.EmojiManager;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ActivityCreateSpeciesBinding;
import io.objectbox.Box;

public final class CreateSpeciesActivity
    extends BaseCreateActivity<Species, ActivityCreateSpeciesBinding> {
  @Inject Box<Species> speciesBox;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.setActivity(this);
    binding.radioButtons.setOnCheckedChangeListener(this::onRadioBtnSelected);
    binding.emoji.setFilters(
        new InputFilter[] {new InputFilter.LengthFilter(2), new EmojiFilter()});
    binding.saveBtn.setOnClickListener(this::onSaveBtnClicked);
  }

  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_species;
  }

  private void onRadioBtnSelected(RadioGroup group, int id) {
    if (id == R.id.air_radio_btn) {
      data.airNeeded = -1;
      data.landNeeded = 0;
      data.waterNeeded = 0;
      binding.airReq.setVisibility(View.VISIBLE);
      binding.landReq.setVisibility(View.GONE);
      binding.waterReq.setVisibility(View.GONE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    } else if (id == R.id.dry_radio_btn) {
      data.airNeeded = 0;
      data.landNeeded = -1;
      data.waterNeeded = 0;
      binding.airReq.setVisibility(View.GONE);
      binding.landReq.setVisibility(View.VISIBLE);
      binding.waterReq.setVisibility(View.GONE);
      binding.pettableCheckbox.setVisibility(View.VISIBLE);
    } else if (id == R.id.water_radio_btn) {
      data.airNeeded = 0;
      data.landNeeded = 0;
      data.waterNeeded = -1;
      binding.airReq.setVisibility(View.GONE);
      binding.landReq.setVisibility(View.GONE);
      binding.waterReq.setVisibility(View.VISIBLE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    } else {
      data.airNeeded = 0;
      data.landNeeded = -1;
      data.waterNeeded = -1;
      binding.airReq.setVisibility(View.GONE);
      binding.landReq.setVisibility(View.VISIBLE);
      binding.waterReq.setVisibility(View.VISIBLE);
      binding.pettableCheckbox.setVisibility(View.GONE);
    }
    binding.spaceRequirements.setVisibility(View.VISIBLE);
  }

  public void onAirReqChanged(CharSequence s, int start, int before, int count) {
    data.airNeeded = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  public void onLandReqChanged(CharSequence s, int start, int before, int count) {
    data.landNeeded = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  public void onWaterReqChanged(CharSequence s, int start, int before, int count) {
    data.waterNeeded = Double.parseDouble(s.toString());
    activateSaveBtnIfPossible();
  }

  public void onNameChanged(CharSequence s, int start, int before, int count) {
    data.name = s.toString();
    activateSaveBtnIfPossible();
  }

  public void onEmojiChanged(CharSequence s, int start, int before, int count) {
    data.emoji = s.toString();
    activateSaveBtnIfPossible();
  }

  private void activateSaveBtnIfPossible() {
    data.isPettable =
        binding.pettableCheckbox.isChecked() && data.waterNeeded == 0 && data.airNeeded == 0;
    if (data.name != null
        && !data.name.isEmpty()
        && data.emoji != null
        && !data.emoji.isEmpty()
        && data.airNeeded > -1
        && data.landNeeded > -1
        && data.waterNeeded > -1) {
      binding.saveBtn.setEnabled(true);
    } else {
      binding.saveBtn.setEnabled(false);
    }
  }

  private static final class EmojiFilter implements InputFilter {
    @Override
    public CharSequence filter(
        CharSequence source, int start, int end, Spanned _0, int _1, int _2) {
      if (EmojiManager.isEmoji(source.toString())) {
        return source;
      }
      return "";
    }
  }
}
