package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ItemPenListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

final class PenListAdapter extends BaseAdapter<PenListAdapter.PenViewHolder, Pen> {
  static final class PenViewHolder extends BaseViewHolder<Pen, ItemPenListBinding> {
    public static PenViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new PenViewHolder(ItemPenListBinding.inflate(inflater, parent, false));
    }

    PenViewHolder(ItemPenListBinding binding) {
      super(binding);
    }

    @Override
    protected void bindData(Pen pen) {
      binding.setPen(pen);
      if (pen.keeper.getTarget() == null) {
        binding.noKeeperWarning.setVisibility(View.VISIBLE);
      }
    }
  }

  @Inject
  PenListAdapter() {}

  @Override
  public PenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return PenViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
