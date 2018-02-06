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
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.databinding.GenericListBinding;
import io.objectbox.Box;

public final class AssignPenBottomSheetFragment extends BottomSheetDialogFragment {
  private static final String ARG_PEN_ID = "ARG_PEN_ID";

  public static AssignPenBottomSheetFragment newInstance(long penId) {
    Bundle args = new Bundle();
    args.putLong(ARG_PEN_ID, penId);
    AssignPenBottomSheetFragment fragment = new AssignPenBottomSheetFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Inject Box<Keeper> keeperBox;
  @Inject Box<Pen> penBox;
  @Inject KeeperListAdapter adapter;

  public AssignPenBottomSheetFragment() {}

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    Pen pen = penBox.get(getArguments().getLong(ARG_PEN_ID));
    adapter.setData(keeperBox.query().build().find());
    adapter.setOnItemClickedConsumer(
        keeper -> {
          pen.keeper.setTarget(keeper);
          penBox.put(pen);
          keeperBox.put(pen.keeper.getTarget());
          dismiss();
        });
    GenericListBinding binding = GenericListBinding.inflate(inflater, container, false);
    binding.recyclerView.setAdapter(adapter);
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    return binding.getRoot();
  }
}
