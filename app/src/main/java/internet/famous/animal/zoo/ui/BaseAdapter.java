package internet.famous.animal.zoo.ui;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseAdapter<T extends BaseViewHolder<DataT, ?>, DataT>
    extends RecyclerView.Adapter<T> {
  private Consumer<DataT> onItemClickedConsumer;
  private List<DataT> dataList = new ArrayList<>();

  public final void setData(List<DataT> dataList) {
    this.dataList = dataList;
    notifyDataSetChanged();
  }

  @Override
  public final void onBindViewHolder(T holder, int position) {
    DataT item = dataList.get(position);
    holder.onBind(item);
    holder.itemView.setOnClickListener(
        view -> {
          if (onItemClickedConsumer != null) {
            onItemClickedConsumer.accept(item);
          }
        });
    onBindViewHolder(holder, item);
  }

  public void onBindViewHolder(T holder, DataT data) {}

  @Override
  public final int getItemCount() {
    return dataList.size();
  }

  public void setOnItemClickedConsumer(Consumer<DataT> onItemClickedConsumer) {
    this.onItemClickedConsumer = onItemClickedConsumer;
  }
}
