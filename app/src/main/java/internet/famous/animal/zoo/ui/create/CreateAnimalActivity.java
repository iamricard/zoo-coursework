package internet.famous.animal.zoo.ui.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ActivityCreateAnimalBinding;
import internet.famous.animal.zoo.ui.BaseActivity;
import internet.famous.animal.zoo.ui.main.MainActivity;
import io.objectbox.Box;

public final class CreateAnimalActivity extends BaseActivity<ActivityCreateAnimalBinding> {
  private Species animalSpecies = null;
  @Inject Box<Animal> animalBox;
  @Inject SelectSpeciesBottomSheetFragment selectSpeciesFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.selectSpeciesBtn.setOnClickListener(this::onSelectSpeciesBtnClicked);
    binding.saveButton.setOnClickListener(this::onSaveAnimalBtnClicked);
  }

  @Override
  public int getLayoutRes() {
    return R.layout.activity_create_animal;
  }

  private void onSelectSpeciesBtnClicked(View __) {
    selectSpeciesFragment
        .setOnSpeciesSelected(
            species -> {
              animalSpecies = species;
              binding.selectSpeciesBtn.setText(String.format("%s %s", species.emoji, species.name));
              selectSpeciesFragment.dismiss();
            })
        .show(getSupportFragmentManager(), "select_species");
  }

  private void onSaveAnimalBtnClicked(View view) {
    String animalName = binding.editTextAnimalName.getText().toString();
    if (animalName.isEmpty()) {
      binding.editTextAnimalName.setError("Please select a name for the critter.");
      return;
    }
    if (animalSpecies == null) {
      Snackbar.make(view, "Please select a species for the critter", Snackbar.LENGTH_SHORT).show();
      return;
    }
    Animal newAnimal = new Animal();
    newAnimal.name = animalName;
    newAnimal.species.setTarget(animalSpecies);
    animalBox.put(newAnimal);
    startActivity(new Intent(this, MainActivity.class));
  }
}
