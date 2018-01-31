package internet.famous.animal.zoo.species;

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

final class SpeciesAdapter extends BaseAdapter {
  static final class ViewHolder {
    @BindView(R.id.entity_type)
    TextView entityType;

    @BindView(R.id.title)
    TextView name;

    @BindView(R.id.subtitle)
    TextView count;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  private final Context context;
  private List<Species> speciesList = new ArrayList<>();

  public SpeciesAdapter(Context context) {
    this.context = context;
  }

  public void setSpeciesList(List<Species> speciesList) {
    this.speciesList = speciesList;
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
    Species species = getItem(position);

    holder.entityType.setText(species.emoji);
    String speciesName = resources.getString(R.string.species_title, species.name);
    holder.name.setText(speciesName);
    String speciesCount = resources.getString(R.string.species_subtitle, species.animals.size());
    holder.count.setText(speciesCount);

    return view;
  }

  @Override
  public int getCount() {
    return speciesList.size();
  }

  @Override
  public Species getItem(int position) {
    return speciesList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).id;
  }
}
