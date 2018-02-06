package internet.famous.animal.zoo.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity
    implements HasSupportFragmentInjector {
  protected DB binding;
  @Inject DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

  @LayoutRes
  public abstract int getLayoutRes();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, getLayoutRes());
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentAndroidInjector;
  }
}
