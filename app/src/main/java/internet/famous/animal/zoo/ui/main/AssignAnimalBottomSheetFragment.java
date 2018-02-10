package internet.famous.animal.zoo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.GenericListBinding;
import io.objectbox.Box;

public final class AssignAnimalBottomSheetFragment extends BottomSheetDialogFragment {
  private static final String ARG_ANIMAL_ID = "ARG_ANIMAL_ID";
  @Inject Box<Animal> animalBox;
  @Inject Box<Pen> penBox;
  @Inject PenListAdapter penListAdapter;

  public AssignAnimalBottomSheetFragment() {}

  public static AssignAnimalBottomSheetFragment newInstance(long animalId) {
    Bundle args = new Bundle();
    args.putLong(ARG_ANIMAL_ID, animalId);
    AssignAnimalBottomSheetFragment fragment = new AssignAnimalBottomSheetFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    Animal animal = animalBox.get(getArguments().getLong(ARG_ANIMAL_ID));
    penListAdapter.setHideAssignBtn(true);
    penListAdapter.setData(penBox.query().filter(pen -> pen.canAccommodate(animal)).build().find());
    penListAdapter.setOnItemClickedConsumer(
        pen -> {
          animal.pen.setTarget(pen);
          animalBox.put(animal);
          penBox.put(animal.pen.getTarget());
          dismiss();
        });
    GenericListBinding binding = GenericListBinding.inflate(inflater, container, false);
    binding.recyclerView.setAdapter(penListAdapter);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    return binding.getRoot();
  }
}
