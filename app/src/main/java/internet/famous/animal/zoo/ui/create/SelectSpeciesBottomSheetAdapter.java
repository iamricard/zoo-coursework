package internet.famous.animal.zoo.ui.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Singleton;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ItemSpeciesGridBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

@Singleton
final class SelectSpeciesBottomSheetAdapter
    extends BaseAdapter<SelectSpeciesBottomSheetAdapter.SpeciesViewHolder, Species> {
  static final class SpeciesViewHolder extends BaseViewHolder<Species, ItemSpeciesGridBinding> {
    public static SpeciesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      ItemSpeciesGridBinding binding = ItemSpeciesGridBinding.inflate(inflater, parent, false);
      int sidePadding =
          parent.getContext().getResources().getDimensionPixelSize(R.dimen.app_padding);
      View root = binding.getRoot();
      root.getLayoutParams().width = (parent.getMeasuredWidth() - (sidePadding * 2)) / 3;
      return new SpeciesViewHolder(binding);
    }

    SpeciesViewHolder(ItemSpeciesGridBinding binding) {
      super(binding);
    }

    @Override
    protected void bindData(Species species) {
      binding.setSpecies(species);
    }
  }

  @Inject
  SelectSpeciesBottomSheetAdapter() {}

  @Override
  public SpeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return SpeciesViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
