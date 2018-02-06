package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ItemSpeciesListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;
import javax.inject.Inject;

final class SpeciesListAdapter extends BaseAdapter<SpeciesListAdapter.SpeciesViewHolder, Species> {
  @Inject
  SpeciesListAdapter() {}

  @Override
  public SpeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return SpeciesViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  static final class SpeciesViewHolder extends BaseViewHolder<Species, ItemSpeciesListBinding> {
    SpeciesViewHolder(ItemSpeciesListBinding binding) {
      super(binding);
    }

    public static SpeciesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new SpeciesViewHolder(ItemSpeciesListBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void bindData(Species species) {
      binding.setSpecies(species);
    }
  }
}
