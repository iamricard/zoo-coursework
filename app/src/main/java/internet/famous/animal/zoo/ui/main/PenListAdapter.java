package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.function.Consumer;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ItemPenListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public final class PenListAdapter extends BaseAdapter<PenListAdapter.PenViewHolder, Pen> {
  private Consumer<Pen> consumer;
  private boolean hideAssignBtn = false;

  @Inject
  PenListAdapter() {}

  @Override
  public PenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return PenViewHolder.create(LayoutInflater.from(parent.getContext()), parent, hideAssignBtn);
  }

  @Override
  public void onBindViewHolder(PenViewHolder holder, Pen pen) {
    holder
        .itemView
        .findViewById(R.id.assign_btn)
        .setOnClickListener(
            __ -> {
              if (consumer != null) {
                consumer.accept(pen);
              }
            });
  }

  void setOnAssignBtnClicked(Consumer<Pen> consumer) {
    this.consumer = consumer;
  }

  void setHideAssignBtn(boolean hideAssignBtn) {
    this.hideAssignBtn = hideAssignBtn;
  }

  static final class PenViewHolder extends BaseViewHolder<Pen, ItemPenListBinding> {
    PenViewHolder(ItemPenListBinding binding) {
      super(binding);
    }

    public static PenViewHolder create(
        LayoutInflater inflater, ViewGroup parent, boolean hideAssignBtn) {
      ItemPenListBinding binding = ItemPenListBinding.inflate(inflater, parent, false);
      binding.assignBtn.setVisibility(hideAssignBtn ? View.GONE : View.VISIBLE);
      return new PenViewHolder(binding);
    }

    @Override
    protected void bindData(Pen pen) {
      binding.setPen(pen);
    }
  }
}
