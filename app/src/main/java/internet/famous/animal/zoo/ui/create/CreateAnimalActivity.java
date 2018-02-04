package internet.famous.animal.zoo.ui.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.databinding.ActivityCreateAnimalBinding;

public final class CreateAnimalActivity
    extends BaseCreateActivity<Animal, ActivityCreateAnimalBinding> {
  private static final int MINIMUM_NAME_LENGTH = 3;

  @Inject SelectSpeciesBottomSheetFragment selectSpeciesFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.editTextAnimalName.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            enableSaveButtonIfAnimalIsValid();
          }

          @Override
          public void afterTextChanged(Editable s) {}
        });
    binding.selectSpeciesBtn.setOnClickListener(this::onSelectSpeciesBtnClicked);
    binding.saveButton.setOnClickListener(this::onSaveBtnClicked);
  }

  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_animal;
  }

  private void onSelectSpeciesBtnClicked(View __) {
    selectSpeciesFragment
        .setOnSpeciesSelected(
            species -> {
              data.species.setTarget(species);
              binding.selectSpeciesBtn.setText(String.format("%s %s", species.emoji, species.name));
              selectSpeciesFragment.dismiss();
              enableSaveButtonIfAnimalIsValid();
            })
        .show(getSupportFragmentManager(), "select_species");
  }

  private void enableSaveButtonIfAnimalIsValid() {
    data.name = binding.editTextAnimalName.getText().toString();
    binding.saveButton.setEnabled(
        data.name.length() >= MINIMUM_NAME_LENGTH && data.species.getTarget() != null);
  }
}
