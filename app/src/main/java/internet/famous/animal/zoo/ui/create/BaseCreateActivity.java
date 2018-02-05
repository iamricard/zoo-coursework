package internet.famous.animal.zoo.ui.create;

import android.databinding.ViewDataBinding;
import android.view.View;

import javax.inject.Inject;

import internet.famous.animal.zoo.ui.BaseActivity;
import io.objectbox.Box;

abstract class BaseCreateActivity<DataT, DB extends ViewDataBinding> extends BaseActivity<DB> {
  @Inject Box<DataT> box;
  @Inject DataT data;

  final void onSaveBtnClicked(View __) {
    box.put(data);
    finish();
  }
}
