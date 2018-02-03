package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ItemPenListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public final class PenListAdapter extends BaseAdapter<PenListAdapter.PenViewHolder, Pen> {
  static final class PenViewHolder extends BaseViewHolder<Pen, ItemPenListBinding> {
    public static PenViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new PenViewHolder(ItemPenListBinding.inflate(inflater, parent, false));
    }

    public PenViewHolder(ItemPenListBinding binding) {
      super(binding);
    }

    @Override
    protected void onBind(Pen pen, ItemPenListBinding binding) {
      binding.setPen(pen);
    }
  }

  @Inject
  PenListAdapter() {}

  @Override
  public PenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return PenViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
