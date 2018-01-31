package internet.famous.animal.zoo.animal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import internet.famous.animal.zoo.BoxBuilder;
import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.species.Species;

public final class CreateAnimalDialogFragment extends DialogFragment {
  @FunctionalInterface
  interface OnAnimalSavedListener {
    void onSaved(Animal animal);
  }

  private OnAnimalSavedListener onAnimalSavedListener;
  private Species animalSpecies;

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view =
        inflater.inflate(
            R.layout.dialog_fragment_create_animal, container, /* attachToRoot= */ false);

    Button selectSpeciesDialogButton = view.findViewById(R.id.select_species_dialog_button);
    TextInputEditText editTextAnimalName = view.findViewById(R.id.edit_text_animal_name);

    // Open species selection dialog
    selectSpeciesDialogButton.setOnClickListener(
        v ->
            new SelectSpeciesDialogFragment()
                .setOnSpeciesSelectedListener(
                    species -> {
                      animalSpecies = species;
                      selectSpeciesDialogButton.setText(
                          String.format("%s %s", species.emoji, species.name));
                    })
                .show(getActivity().getSupportFragmentManager(), "select_species_dialog"));

    // Save species
    view.findViewById(R.id.save_button)
        .setOnClickListener(
            v -> {
              String animalName = editTextAnimalName.getText().toString();
              if (animalName.isEmpty()) {
                editTextAnimalName.setError("The animal's name can't be null");
                return;
              }
              if (animalSpecies == null) {
                Snackbar.make(view, "Please select a species!", Snackbar.LENGTH_LONG).show();
                return;
              }
              Animal animal = new Animal();
              animal.name = animalName;
              animal.species.setTarget(animalSpecies);
              BoxBuilder.build(getActivity(), Animal.class).put(animal);
              onAnimalSavedListener.onSaved(animal);
              dismiss();
            });

    return view;
  }

  public CreateAnimalDialogFragment setOnAnimalSavedListener(
      OnAnimalSavedListener onAnimalSavedListener) {
    this.onAnimalSavedListener = onAnimalSavedListener;
    return this;
  }

  public static final class SelectSpeciesDialogFragment extends BottomSheetDialogFragment {
    @FunctionalInterface
    interface OnSpeciesSelectedListener {
      void onSelected(Species species);
    }

    private List<Species> speciesList;
    private GridView view;
    private SpeciesItemAdapter adapter;
    private OnSpeciesSelectedListener onSpeciesSelectedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      speciesList = BoxBuilder.build(getActivity(), Species.class).getAll();
      adapter = new SpeciesItemAdapter(getContext(), speciesList);
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
      view =
          (GridView)
              inflater.inflate(
                  R.layout.dialog_fragment_select_species, container, /* attachToRoot= */ false);
      view.setAdapter(adapter);
      view.setOnItemClickListener(
          (parent, view, position, id) -> {
            onSpeciesSelectedListener.onSelected(adapter.getItem(position));
            dismiss();
          });
      return view;
    }

    public SelectSpeciesDialogFragment setOnSpeciesSelectedListener(
        OnSpeciesSelectedListener onSpeciesSelectedListener) {
      this.onSpeciesSelectedListener = onSpeciesSelectedListener;
      return this;
    }
  }

  private static final class SpeciesItemAdapter extends ArrayAdapter<Species> {
    SpeciesItemAdapter(Context context, List<Species> speciesList) {
      super(context, 0, speciesList);
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
      Species species = getItem(position);
      if (species == null) {
        return null;
      }
      if (view == null) {
        view =
            LayoutInflater.from(getContext())
                .inflate(R.layout.gridview_species_item, parent, /* attachToRoot= */ false);
      }
      TextView speciesEmoji = view.findViewById(R.id.species_emoji);
      TextView speciesName = view.findViewById(R.id.species_name);

      speciesEmoji.setText(species.emoji);
      speciesName.setText(species.name);

      return view;
    }
  }
}
