package internet.famous.animal.zoo.ui.main;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.data.local.Species_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class SpeciesListFragment extends BaseListFragment<SpeciesListAdapter, Species> {
  @Inject Box<Species> speciesBox;

  @Override
  protected QueryBuilder<Species> getQueryBuilder() {
    return speciesBox.query().orderDesc(Species_.id);
  }
}
