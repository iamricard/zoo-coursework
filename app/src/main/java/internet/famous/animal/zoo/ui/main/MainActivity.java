package internet.famous.animal.zoo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.common.collect.ImmutableList;

import java.util.List;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.databinding.ActivityMainBinding;
import internet.famous.animal.zoo.ui.BaseActivity;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;
import internet.famous.animal.zoo.ui.create.CreateKeeperActivity;

public final class MainActivity extends BaseActivity<ActivityMainBinding> {
  private List<Intent> createEntityActivityIntents;

  @Override
  public int getLayoutRes() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    createEntityActivityIntents =
        ImmutableList.of(
            new Intent(this, CreateAnimalActivity.class),
            new Intent(this, CreateKeeperActivity.class),
            new Intent(this, CreateKeeperActivity.class),
            new Intent(this, CreateKeeperActivity.class));
    binding.viewpager.setAdapter(new MainFragmentAdapter(this));
    binding.tabs.setupWithViewPager(binding.viewpager);
    binding.btnCreate.setOnClickListener(this::handleBtnCreateClick);
  }

  private void handleBtnCreateClick(View __) {
    startActivity(createEntityActivityIntents.get(binding.viewpager.getCurrentItem()));
  }
}
