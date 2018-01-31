package internet.famous.animal.zoo.keeper;

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

final class KeeperAdapter extends BaseAdapter {
  static final class ViewHolder {
    @BindView(R.id.title)
    TextView name;

    @BindView(R.id.subtitle)
    TextView pens;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  private final Context context;
  private List<Keeper> keeperList = new ArrayList<>();

  public KeeperAdapter(Context context) {
    this.context = context;
  }

  public void setKeeperList(List<Keeper> keeperList) {
    this.keeperList = keeperList;
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

    Keeper keeper = getItem(position);
    holder.name.setText(resources.getString(R.string.keeper_title, keeper.name));
    holder.pens.setText(resources.getString(R.string.keeper_subtitle, keeper.pens.size()));

    return view;
  }

  @Override
  public int getCount() {
    return keeperList.size();
  }

  @Override
  public Keeper getItem(int position) {
    return keeperList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).id;
  }
}
