package internet.famous.animal.zoo.ui.main;

import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Pen_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class PenListFragment extends BaseListFragment<PenListAdapter, Pen> {
  @Override
  protected QueryBuilder<Pen> getQueryBuilder() {
    return box.query().orderDesc(Pen_.id);
  }
}
