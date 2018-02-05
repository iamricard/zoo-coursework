package internet.famous.animal.zoo.ui.main;

import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.data.local.Species_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class SpeciesListFragment extends BaseListFragment<SpeciesListAdapter, Species> {
  @Override
  protected QueryBuilder<Species> getQueryBuilder() {
    return box.query().orderDesc(Species_.id);
  }
}
