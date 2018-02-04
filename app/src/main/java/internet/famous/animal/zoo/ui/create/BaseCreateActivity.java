package internet.famous.animal.zoo.ui.create;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import internet.famous.animal.zoo.ui.BaseActivity;
import io.objectbox.Box;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;

abstract class BaseCreateActivity<DataT, DB extends ViewDataBinding> extends BaseActivity<DB> {
  @Inject Box<DataT> box;
  @Inject DataT data;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_VISIBLE);
  }

  final void onSaveBtnClicked(View __) {
    box.put(data);
    finish();
  }
}
