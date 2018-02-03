package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ItemSpeciesListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public final class SpeciesListAdapter
    extends BaseAdapter<SpeciesListAdapter.SpeciesViewHolder, Species> {
  static final class SpeciesViewHolder extends BaseViewHolder<Species, ItemSpeciesListBinding> {
    public static SpeciesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new SpeciesViewHolder(ItemSpeciesListBinding.inflate(inflater, parent, false));
    }

    public SpeciesViewHolder(ItemSpeciesListBinding binding) {
      super(binding);
    }

    @Override
    protected void onBind(Species species, ItemSpeciesListBinding binding) {
      binding.setSpecies(species);
    }
  }

  @Inject
  SpeciesListAdapter() {}

  @Override
  public SpeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return SpeciesViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
