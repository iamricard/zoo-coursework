package internet.famous.animal.zoo.ui.create;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ItemSpeciesGridBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public class SpeciesGridAdapter extends BaseAdapter<SpeciesGridAdapter.SpeciesViewHolder, Species> {
  static final class SpeciesViewHolder extends BaseViewHolder<Species, ItemSpeciesGridBinding> {
    public static SpeciesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new SpeciesViewHolder(ItemSpeciesGridBinding.inflate(inflater, parent, false));
    }

    public SpeciesViewHolder(ItemSpeciesGridBinding binding) {
      super(binding);
    }

    @Override
    protected void onBind(Species species, ItemSpeciesGridBinding binding) {
      binding.setSpecies(species);
    }
  }

  private List<Species> species = new ArrayList<>();

  @Inject
  SpeciesGridAdapter() {}

  @Override
  public void setData(List<Species> species) {
    this.species = species;
  }

  @Override
  public SpeciesGridAdapter.SpeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return SpeciesViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  @Override
  public void onBindViewHolder(SpeciesViewHolder holder, int position) {
    holder.onBind(species.get(position));
  }

  @Override
  public int getItemCount() {
    return species.size();
  }
}
