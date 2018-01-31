package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Animal_;
import internet.famous.animal.zoo.databinding.FragmentGenericListBinding;
import internet.famous.animal.zoo.ui.BaseListFragment;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public final class AnimalListFragment extends BaseListFragment<AnimalListAdapter, Animal> {
  @Inject Box<Animal> animalBox;
  @Inject AnimalListAdapter adapter;

  public static AnimalListFragment newInstance() {
    Bundle args = new Bundle();
    AnimalListFragment fragment = new AnimalListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup parent, Bundle savedInstanceState) {
    FragmentGenericListBinding binding =
        FragmentGenericListBinding.inflate(inflater, parent, false);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recyclerView.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  protected QueryBuilder<Animal> getQueryBuilder() {
    return animalBox.query().orderDesc(Animal_.id);
  }

  @Override
  protected AnimalListAdapter getAdapter() {
    return adapter;
  }
}
