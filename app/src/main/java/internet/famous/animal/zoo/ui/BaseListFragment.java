package internet.famous.animal.zoo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import internet.famous.animal.zoo.databinding.GenericListBinding;
import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.QueryBuilder;
import io.objectbox.reactive.DataSubscriptionList;

public abstract class BaseListFragment<AdapterT extends BaseAdapter<?, DataT>, DataT>
    extends Fragment {
  private final DataSubscriptionList subscriptions = new DataSubscriptionList();
  @Inject protected AdapterT adapter;
  @Inject protected Box<DataT> box;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public final View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
    GenericListBinding binding = GenericListBinding.inflate(inflater, parent, false);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recyclerView.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getQueryBuilder()
        .build()
        .subscribe(subscriptions)
        .on(AndroidScheduler.mainThread())
        .observer(adapter::setData);
  }

  @Override
  public final void onDestroyView() {
    super.onDestroyView();
    subscriptions.cancel();
  }

  protected abstract QueryBuilder<DataT> getQueryBuilder();
}
