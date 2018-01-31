package internet.famous.animal.zoo.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import internet.famous.animal.zoo.BoxBuilder;
import internet.famous.animal.zoo.R;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataSubscriptionList;

public final class AnimalFragment extends Fragment {
  @BindView(R.id.btn_create)
  FloatingActionButton floatingActionButton;

  private static final DataSubscriptionList subscriptions = new DataSubscriptionList();

  private AnimalAdapter adapter;
  private Query<Animal> animalQuery;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this, getActivity());
    adapter = new AnimalAdapter(getContext());
    animalQuery =
        BoxBuilder.build(getActivity(), Animal.class).query().orderDesc(Animal_.id).build();
    floatingActionButton.setOnClickListener(this::handleFloatingActionButtonClick);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, /* attachToRoot= */ false);
    ListView animalListView = (ListView) view;
    animalQuery
        .subscribe(subscriptions)
        .on(AndroidScheduler.mainThread())
        .observer(adapter::setAnimalList);
    animalListView.setAdapter(adapter);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    subscriptions.cancel();
  }

  private void handleFloatingActionButtonClick(View view) {
    FragmentManager fm = getActivity().getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    ft.add(
            android.R.id.content,
            new CreateAnimalDialogFragment()
                .setOnAnimalSavedListener(
                    animal ->
                        Snackbar.make(
                                getView(),
                                String.format(
                                    "%s of species %s saved \uD83D\uDCBE",
                                    animal.name, animal.species.getTarget().emoji),
                                Snackbar.LENGTH_LONG)
                            .show()))
        .addToBackStack(null)
        .commit();
  }
}
