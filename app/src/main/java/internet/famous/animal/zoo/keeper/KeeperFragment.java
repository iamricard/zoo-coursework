package internet.famous.animal.zoo.keeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import internet.famous.animal.zoo.BoxBuilder;
import internet.famous.animal.zoo.R;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataSubscriptionList;

public final class KeeperFragment extends Fragment {
  private static final DataSubscriptionList subscriptions = new DataSubscriptionList();

  private KeeperAdapter adapter;
  private Query<Keeper> keeperQuery;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new KeeperAdapter(getContext());
    keeperQuery = BoxBuilder.build(getActivity(), Keeper.class).query().build();
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, /* attachToRoot= */ false);
    ListView keeperListView = (ListView) view;
    keeperQuery
        .subscribe(subscriptions)
        .on(AndroidScheduler.mainThread())
        .observer(adapter::setKeeperList);
    keeperListView.setAdapter(adapter);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    subscriptions.cancel();
  }
}
