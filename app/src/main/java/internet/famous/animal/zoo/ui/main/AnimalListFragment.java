package internet.famous.animal.zoo.ui.main;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Animal_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class AnimalListFragment extends BaseListFragment<AnimalListAdapter, Animal> {
  @Override
  protected QueryBuilder<Animal> getQueryBuilder() {
    return box.query().orderDesc(Animal_.id);
  }
}
