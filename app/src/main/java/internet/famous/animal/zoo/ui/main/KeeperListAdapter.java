package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.databinding.ItemKeeperListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;

final class KeeperListAdapter extends BaseAdapter<KeeperListAdapter.KeeperViewHolder, Keeper> {
  static final class KeeperViewHolder extends BaseViewHolder<Keeper, ItemKeeperListBinding> {
    public static KeeperViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new KeeperViewHolder(ItemKeeperListBinding.inflate(inflater, parent, false));
    }

    KeeperViewHolder(ItemKeeperListBinding binding) {
      super(binding);
    }

    @Override
    protected void bindData(Keeper keeper) {
      binding.setKeeper(keeper);
    }
  }

  @Inject
  KeeperListAdapter() {}

  @Override
  public KeeperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return KeeperViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }
}
