package internet.famous.animal.zoo.ui.main;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Pen_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class PenListFragment extends BaseListFragment<PenListAdapter, Pen> {
  @Inject Box<Pen> penBox;

  @Override
  protected QueryBuilder<Pen> getQueryBuilder() {
    return penBox.query().orderDesc(Pen_.id);
  }
}
