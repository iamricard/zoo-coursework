package internet.famous.animal.zoo.ui.main;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Animal_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class AnimalListFragment extends BaseListFragment<AnimalListAdapter, Animal> {
  @Inject Box<Animal> animalBox;

  @Override
  protected QueryBuilder<Animal> getQueryBuilder() {
    return animalBox.query().orderDesc(Animal_.id);
  }
}
