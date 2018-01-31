package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.databinding.ItemAnimalListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public final class AnimalListAdapter
    extends BaseAdapter<AnimalListAdapter.AnimalViewHolder, Animal> {
  static final class AnimalViewHolder extends BaseViewHolder<Animal, ItemAnimalListBinding> {
    public static AnimalViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new AnimalViewHolder(ItemAnimalListBinding.inflate(inflater, parent, false));
    }

    public AnimalViewHolder(ItemAnimalListBinding binding) {
      super(binding);
    }

    @Override
    protected void onBind(Animal animal, ItemAnimalListBinding binding) {
      binding.setAnimal(animal);
      binding.setSpecies(animal.species.getTarget());
    }
  }

  private List<Animal> animals = new ArrayList<>();

  @Inject
  AnimalListAdapter() {}

  @Override
  public void setData(List<Animal> animals) {
    this.animals = animals;
    notifyDataSetChanged();
  }

  @Override
  public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return AnimalViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  @Override
  public void onBindViewHolder(AnimalViewHolder holder, int position) {
    holder.onBind(animals.get(position));
  }

  @Override
  public int getItemCount() {
    return animals.size();
  }
}
