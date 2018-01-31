package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.data.local.Species_;
import internet.famous.animal.zoo.databinding.FragmentGenericListBinding;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class SpeciesListFragment extends BaseListFragment<SpeciesListAdapter, Species> {
  @Inject Box<Species> speciesBox;
  @Inject SpeciesListAdapter adapter;

  public static SpeciesListFragment newInstance() {
    Bundle args = new Bundle();
    SpeciesListFragment fragment = new SpeciesListFragment();
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
  protected QueryBuilder<Species> getQueryBuilder() {
    return speciesBox.query().orderDesc(Species_.id);
  }

  @Override
  protected SpeciesListAdapter getAdapter() {
    return adapter;
  }
}