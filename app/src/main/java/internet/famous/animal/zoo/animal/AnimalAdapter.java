package internet.famous.animal.zoo.animal;

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

final class AnimalAdapter extends BaseAdapter {
  static final class ViewHolder {
    @BindView(R.id.entity_type)
    TextView speciesEmoji;

    @BindView(R.id.title)
    TextView name;

    @BindView(R.id.subtitle)
    TextView speciesName;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  private final Context context;
  private List<Animal> animalList = new ArrayList<>();

  public AnimalAdapter(Context context) {
    this.context = context;
  }

  public void setAnimalList(List<Animal> animalList) {
    this.animalList = animalList;
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
    Animal animal = getItem(position);
    holder.speciesEmoji.setText(animal.species.getTarget().emoji);
    holder.name.setText(resources.getString(R.string.animal_title, animal.name));
    holder.speciesName.setText(
        resources.getString(R.string.animal_subtitle, animal.species.getTarget().name));

    return view;
  }

  @Override
  public int getCount() {
    return animalList.size();
  }

  @Override
  public Animal getItem(int position) {
    return animalList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).id;
  }
}
