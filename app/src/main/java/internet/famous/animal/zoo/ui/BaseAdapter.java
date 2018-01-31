package internet.famous.animal.zoo.ui;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T extends BaseViewHolder, DataT> extends RecyclerView.Adapter<T> {
  public abstract void setData(List<DataT> dataList);
}
