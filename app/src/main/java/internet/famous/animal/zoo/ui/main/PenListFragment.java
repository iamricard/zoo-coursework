package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Pen_;
import internet.famous.animal.zoo.databinding.FragmentGenericListBinding;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class PenListFragment extends BaseListFragment<PenListAdapter, Pen> {
  @Inject Box<Pen> penBox;
  @Inject PenListAdapter adapter;

  public static PenListFragment newInstance() {
    Bundle args = new Bundle();
    PenListFragment fragment = new PenListFragment();
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
  protected QueryBuilder<Pen> getQueryBuilder() {
    return penBox.query().orderDesc(Pen_.id);
  }

  @Override
  protected PenListAdapter getAdapter() {
    return adapter;
  }
}
