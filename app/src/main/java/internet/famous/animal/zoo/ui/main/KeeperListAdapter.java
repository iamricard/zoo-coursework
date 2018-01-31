package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.databinding.ItemKeeperListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

public final class KeeperListAdapter
    extends BaseAdapter<KeeperListAdapter.KeeperViewHolder, Keeper> {
  static final class KeeperViewHolder extends BaseViewHolder<Keeper, ItemKeeperListBinding> {
    public static KeeperViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new KeeperViewHolder(ItemKeeperListBinding.inflate(inflater, parent, false));
    }

    public KeeperViewHolder(ItemKeeperListBinding binding) {
      super(binding);
    }

    @Override
    protected void onBind(Keeper keeper, ItemKeeperListBinding binding) {
      binding.setKeeper(keeper);
    }
  }

  private List<Keeper> keepers = new ArrayList<>();

  @Inject
  KeeperListAdapter() {}

  @Override
  public void setData(List<Keeper> keepers) {
    this.keepers = keepers;
    notifyDataSetChanged();
  }

  @Override
  public KeeperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return KeeperViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  @Override
  public void onBindViewHolder(KeeperViewHolder holder, int position) {
    holder.onBind(keepers.get(position));
  }

  @Override
  public int getItemCount() {
    return keepers.size();
  }
}
