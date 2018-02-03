package internet.famous.animal.zoo.ui.main;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Keeper_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class KeeperListFragment extends BaseListFragment<KeeperListAdapter, Keeper> {
  @Inject Box<Keeper> keeperBox;

  @Override
  protected QueryBuilder<Keeper> getQueryBuilder() {
    return keeperBox.query().orderDesc(Keeper_.id);
  }
}
