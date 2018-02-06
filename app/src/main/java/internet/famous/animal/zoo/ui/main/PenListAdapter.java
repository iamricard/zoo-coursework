package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.ItemPenListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;
import java.util.function.Consumer;
import javax.inject.Inject;

public final class PenListAdapter extends BaseAdapter<PenListAdapter.PenViewHolder, Pen> {
  private Consumer<Pen> consumer;

  @Inject
  PenListAdapter() {}

  @Override
  public PenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return PenViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
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

  static final class PenViewHolder extends BaseViewHolder<Pen, ItemPenListBinding> {
    PenViewHolder(ItemPenListBinding binding) {
      super(binding);
    }

    public static PenViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new PenViewHolder(ItemPenListBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void bindData(Pen pen) {
      binding.setPen(pen);
    }
  }
}
