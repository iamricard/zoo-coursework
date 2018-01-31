package internet.famous.animal.zoo.ui.main;

import android.content.Intent;
import android.os.Bundle;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.databinding.ActivityMainBinding;
import internet.famous.animal.zoo.ui.BaseActivity;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;

public final class MainActivity extends BaseActivity<ActivityMainBinding> {
  @Override
  public int getLayoutRes() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.viewpager.setAdapter(new MainFragmentAdapter(this));
    binding.tabs.setupWithViewPager(binding.viewpager);
    binding.btnCreate.setOnClickListener(
        view -> startActivity(new Intent(MainActivity.this, CreateAnimalActivity.class)));
  }
}
