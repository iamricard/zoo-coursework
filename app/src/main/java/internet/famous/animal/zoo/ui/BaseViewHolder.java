package internet.famous.animal.zoo.ui;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public abstract class BaseViewHolder<DataT, DB extends ViewDataBinding>
    extends RecyclerView.ViewHolder {
  protected final DB binding;

  public BaseViewHolder(DB binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public final void onBind(DataT data) {
    bindData(data);
    binding.executePendingBindings();
  }

  protected abstract void bindData(DataT data);
}
