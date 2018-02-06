package internet.famous.animal.zoo.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.databinding.ItemKeeperListBinding;
import internet.famous.animal.zoo.ui.BaseAdapter;
import internet.famous.animal.zoo.ui.BaseViewHolder;
import javax.inject.Inject;

final class KeeperListAdapter extends BaseAdapter<KeeperListAdapter.KeeperViewHolder, Keeper> {
  @Inject
  KeeperListAdapter() {}

  @Override
  public KeeperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return KeeperViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
  }

  static final class KeeperViewHolder extends BaseViewHolder<Keeper, ItemKeeperListBinding> {
    KeeperViewHolder(ItemKeeperListBinding binding) {
      super(binding);
    }

    public static KeeperViewHolder create(LayoutInflater inflater, ViewGroup parent) {
      return new KeeperViewHolder(ItemKeeperListBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void bindData(Keeper keeper) {
      binding.setKeeper(keeper);
    }
  }
}
