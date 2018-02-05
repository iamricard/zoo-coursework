package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.databinding.ItemAnimalListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

final class AnimalListAdapter extends BaseAdapter<AnimalListAdapter.AnimalViewHolder, Animal> {
  static final class AnimalViewHolder extends BaseViewHolder<Animal, ItemAnimalListBinding> {
    static AnimalViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new AnimalViewHolder(ItemAnimalListBinding.inflate(inflater, parent, false));
    }

    public AnimalViewHolder(ItemAnimalListBinding binding) {
      super(binding);
    }

    @Override
    protected void bindData(Animal animal) {
      binding.setAnimal(animal);
      binding.setSpecies(animal.species.getTarget());
    }
  }

  @Inject
  AnimalListAdapter() {}

  @Override
  public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return AnimalViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
