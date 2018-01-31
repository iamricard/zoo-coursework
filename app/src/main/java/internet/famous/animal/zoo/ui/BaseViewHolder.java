package internet.famous.animal.zoo.ui;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public abstract class BaseViewHolder<DataT, DB extends ViewDataBinding>
    extends RecyclerView.ViewHolder {
  private final DB db;

  public BaseViewHolder(DB db) {
    super(db.getRoot());
    this.db = db;
  }

  public final void onBind(DataT data) {
    onBind(data, db);
    db.executePendingBindings();
  }

  protected abstract void onBind(DataT data, DB db);
}
