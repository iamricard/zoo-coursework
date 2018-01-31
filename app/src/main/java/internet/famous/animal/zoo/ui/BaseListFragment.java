package internet.famous.animal.zoo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.QueryBuilder;
import io.objectbox.reactive.DataSubscriptionList;

public abstract class BaseListFragment<AdapterT extends BaseAdapter<?, DataT>, DataT>
    extends Fragment {
  private final DataSubscriptionList subscriptions = new DataSubscriptionList();

  @Override
  public final void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getQueryBuilder()
        .build()
        .subscribe(subscriptions)
        .on(AndroidScheduler.mainThread())
        .observer(getAdapter()::setData);
  }

  @Override
  public final void onDestroyView() {
    super.onDestroyView();
    subscriptions.cancel();
  }

  protected abstract QueryBuilder<DataT> getQueryBuilder();

  protected abstract AdapterT getAdapter();
}
