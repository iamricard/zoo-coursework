package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Keeper_;
import internet.famous.animal.zoo.databinding.FragmentGenericListBinding;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class KeeperListFragment extends BaseListFragment<KeeperListAdapter, Keeper> {
  @Inject Box<Keeper> keeperBox;
  @Inject KeeperListAdapter adapter;

  public static KeeperListFragment newInstance() {
    Bundle args = new Bundle();
    KeeperListFragment fragment = new KeeperListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
    FragmentGenericListBinding binding =
        FragmentGenericListBinding.inflate(inflater, parent, false);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recyclerView.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  protected QueryBuilder<Keeper> getQueryBuilder() {
    return keeperBox.query().orderDesc(Keeper_.id);
  }

  @Override
  protected KeeperListAdapter getAdapter() {
    return adapter;
  }
}
