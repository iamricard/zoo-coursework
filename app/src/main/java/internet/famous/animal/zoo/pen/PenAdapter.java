package internet.famous.animal.zoo.pen;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import internet.famous.animal.zoo.R;

final class PenAdapter extends BaseAdapter {
  static final class ViewHolder {
    @BindView(R.id.title)
    TextView penType;

    @BindView(R.id.subtitle)
    TextView keeperName;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  private final Context context;
  private List<Pen> penList = new ArrayList<>();

  public PenAdapter(Context context) {
    this.context = context;
  }

  public void setPenList(List<Pen> penList) {
    this.penList = penList;
    notifyDataSetChanged();
  }

  @Override
  public View getView(int position, @Nullable View view, ViewGroup parent) {
    ViewHolder holder;
    if (view == null) {
      view =
          LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_entity, parent, /* attachToRoot= */ false);
      holder = new ViewHolder(view);
      view.setTag(holder);
    } else {
      holder = (ViewHolder) view.getTag();
    }

    Resources resources = context.getResources();
    Pen pen = getItem(position);
    holder.penType.setText(resources.getString(R.string.pen_title, pen.id));
    holder.keeperName.setText(resources.getString(R.string.pen_subtitle, 0, "unknown"));

    return view;
  }

  @Override
  public int getCount() {
    return penList.size();
  }

  @Override
  public Pen getItem(int position) {
    return penList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).id;
  }
}
