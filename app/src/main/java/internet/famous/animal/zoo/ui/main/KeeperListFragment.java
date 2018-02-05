package internet.famous.animal.zoo.ui.main;

import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Keeper_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class KeeperListFragment extends BaseListFragment<KeeperListAdapter, Keeper> {
  @Override
  protected QueryBuilder<Keeper> getQueryBuilder() {
    return box.query().orderDesc(Keeper_.id);
  }
}
