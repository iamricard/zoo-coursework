package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Animal_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class AnimalListFragment extends BaseListFragment<AnimalListAdapter, Animal> {
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter.setOnAssignBtnClicked(
        animal -> {
          AssignAnimalBottomSheetFragment fragment =
              AssignAnimalBottomSheetFragment.newInstance(animal.id);
          fragment.show(getActivity().getSupportFragmentManager(), "assign-animal");
          adapter.notifyDataSetChanged();
        });
  }

  @Override
  protected QueryBuilder<Animal> getQueryBuilder() {
    return box.query().orderDesc(Animal_.id);
  }
}
