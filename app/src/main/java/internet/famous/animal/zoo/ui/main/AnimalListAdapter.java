package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.function.Consumer;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
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

  private Consumer<Animal> consumer;

  @Inject
  AnimalListAdapter() {}

  @Override
  public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return AnimalViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  @Override
  public void onBindViewHolder(AnimalViewHolder holder, Animal animal) {
    holder
        .itemView
        .findViewById(R.id.assign_btn)
        .setOnClickListener(
            __ -> {
              if (consumer != null) {
                consumer.accept(animal);
              }
            });
  }

  void setOnAssignBtnClicked(Consumer<Animal> consumer) {
    this.consumer = consumer;
  }
}
