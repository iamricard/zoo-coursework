package internet.famous.animal.zoo.ui.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.databinding.ItemSpeciesGridBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class SelectSpeciesBottomSheetAdapter
    extends BaseAdapter<SelectSpeciesBottomSheetAdapter.SpeciesViewHolder, Species> {
  @Inject
  SelectSpeciesBottomSheetAdapter() {}

  @Override
  public SpeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return SpeciesViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  static final class SpeciesViewHolder extends BaseViewHolder<Species, ItemSpeciesGridBinding> {
    SpeciesViewHolder(ItemSpeciesGridBinding binding) {
      super(binding);
    }

    public static SpeciesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      ItemSpeciesGridBinding binding = ItemSpeciesGridBinding.inflate(inflater, parent, false);
      int sidePadding =
          parent.getContext().getResources().getDimensionPixelSize(R.dimen.app_padding);
      View root = binding.getRoot();
      root.getLayoutParams().width = (parent.getMeasuredWidth() - (sidePadding * 2)) / 3;
      return new SpeciesViewHolder(binding);
    }

    @Override
    protected void bindData(Species species) {
      binding.setSpecies(species);
    }
  }
}
