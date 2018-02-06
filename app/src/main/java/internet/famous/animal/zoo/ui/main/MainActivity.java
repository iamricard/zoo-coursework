package internet.famous.animal.zoo.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.WeatherRepository;
import internet.famous.animal.zoo.databinding.ActivityMainBinding;
import internet.famous.animal.zoo.ui.BaseActivity;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;
import internet.famous.animal.zoo.ui.create.CreateKeeperActivity;
import internet.famous.animal.zoo.ui.create.CreatePenActivity;
import internet.famous.animal.zoo.ui.create.CreateSpeciesActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public final class MainActivity extends BaseActivity<ActivityMainBinding> {
  private List<Intent> createEntityActivityIntents;
  @Inject WeatherRepository weatherRepository;

  @Override
  public int getLayoutRes() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION}, 0);
    }
    createEntityActivityIntents =
        ImmutableList.of(
            new Intent(this, CreateAnimalActivity.class),
            new Intent(this, CreateKeeperActivity.class),
            new Intent(this, CreatePenActivity.class),
            new Intent(this, CreateSpeciesActivity.class));
    weatherRepository.loadWeather().observeForever(weather -> binding.setWeather(weather));
    binding.viewpager.setAdapter(new MainFragmentAdapter(this));
    binding.tabs.setupWithViewPager(binding.viewpager);
    binding.btnCreate.setOnClickListener(this::handleBtnCreateClick);
  }

  private void handleBtnCreateClick(View __) {
    startActivity(createEntityActivityIntents.get(binding.viewpager.getCurrentItem()));
  }
}
