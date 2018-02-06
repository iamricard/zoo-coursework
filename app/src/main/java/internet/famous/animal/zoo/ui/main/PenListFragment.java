package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Pen_;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.query.QueryBuilder;

public final class PenListFragment extends BaseListFragment<PenListAdapter, Pen> {
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter.setOnAssignBtnClicked(
        animal -> {
          AssignPenBottomSheetFragment fragment =
              AssignPenBottomSheetFragment.newInstance(animal.id);
          fragment.show(getActivity().getSupportFragmentManager(), "assign-pen");
        });
  }

  @Override
  protected QueryBuilder<Pen> getQueryBuilder() {
    return box.query().orderDesc(Pen_.id);
  }
}
