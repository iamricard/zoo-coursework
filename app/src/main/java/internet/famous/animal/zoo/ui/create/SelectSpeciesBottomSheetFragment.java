package internet.famous.animal.zoo.ui.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.Consumer;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.data.local.Species_;
import internet.famous.animal.zoo.databinding.GenericListBinding;
import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataSubscriptionList;

public final class SelectSpeciesBottomSheetFragment extends BottomSheetDialogFragment {
  private static final int GRID_COLUMNS = 3;

  private final DataSubscriptionList subscriptions = new DataSubscriptionList();

  @Inject Box<Species> speciesBox;
  @Inject SelectSpeciesBottomSheetAdapter adapter;

  @Inject
  public SelectSpeciesBottomSheetFragment() {}

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    speciesBox
        .query()
        .orderDesc(Species_.id)
        .build()
        .subscribe(subscriptions)
        .on(AndroidScheduler.mainThread())
        .observer(adapter::setData);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
    GenericListBinding binding = GenericListBinding.inflate(inflater, parent, false);
    binding.recyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(GRID_COLUMNS, StaggeredGridLayoutManager.HORIZONTAL));
    binding.recyclerView.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public final void onDestroyView() {
    super.onDestroyView();
    subscriptions.cancel();
  }

  public final SelectSpeciesBottomSheetFragment setOnSpeciesSelected(
      Consumer<Species> onSpeciesSelected) {
    adapter.setOnItemClickedConsumer(onSpeciesSelected);
    return this;
  }
}
