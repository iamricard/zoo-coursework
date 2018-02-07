package internet.famous.animal.zoo.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import internet.famous.animal.zoo.R;
import internet.famous.animal.zoo.data.local.AllocatorService;
import internet.famous.animal.zoo.data.remote.OpenWeatherMapService;
import internet.famous.animal.zoo.data.remote.Weather;
import internet.famous.animal.zoo.databinding.ActivityMainBinding;
import internet.famous.animal.zoo.ui.BaseActivity;
import internet.famous.animal.zoo.ui.create.CreateAnimalActivity;
import internet.famous.animal.zoo.ui.create.CreateKeeperActivity;
import internet.famous.animal.zoo.ui.create.CreatePenActivity;
import internet.famous.animal.zoo.ui.create.CreateSpeciesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public final class MainActivity extends BaseActivity<ActivityMainBinding> {
  private static final int CITY_ID = 3128760;

  private List<Intent> createEntityActivityIntents;
  @Inject ListeningExecutorService executor;
  @Inject OpenWeatherMapService weatherService;
  @Inject AllocatorService allocatorService;

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
    loadWeather(null);
    createEntityActivityIntents =
        ImmutableList.of(
            new Intent(this, CreateAnimalActivity.class),
            new Intent(this, CreateKeeperActivity.class),
            new Intent(this, CreatePenActivity.class),
            new Intent(this, CreateSpeciesActivity.class));
    binding.viewpager.setAdapter(new MainFragmentAdapter(this));
    binding.tabs.setupWithViewPager(binding.viewpager);
    binding.btnCreate.setOnClickListener(this::handleBtnCreateClick);
    binding.btnRefreshWeather.setOnClickListener(this::loadWeather);
    binding.btnAutoAllocate.setOnClickListener(this::handleBtnAutoAllocateClick);
  }

  private void handleBtnCreateClick(View __) {
    startActivity(createEntityActivityIntents.get(binding.viewpager.getCurrentItem()));
  }

  private void loadWeather(@Nullable View view) {
    weatherService
        .fetchWeather(CITY_ID)
        .enqueue(
            new Callback<Weather>() {
              @Override
              public void onResponse(Call<Weather> call, Response<Weather> response) {
                binding.setWeather(response.body());
                if (view != null) {
                  Snackbar.make(binding.getRoot(), "Weather updated", Snackbar.LENGTH_SHORT).show();
                }
              }

              @Override
              public void onFailure(Call<Weather> call, Throwable throwable) {}
            });
  }

  private void handleBtnAutoAllocateClick(View view) {
    Futures.addCallback(
        allocatorService.allocateAnimals(),
        new FutureCallback<Boolean>() {
          @Override
          public void onSuccess(Boolean result) {
            int resId =
                result ? R.string.allocate_animals_success : R.string.allocate_animals_warning;
            Snackbar.make(binding.getRoot(), resId, Snackbar.LENGTH_SHORT)
                .addCallback(
                    new Snackbar.Callback() {
                      @Override
                      public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        Futures.addCallback(
                            allocatorService.allocateKeepers(),
                            new FutureCallback<Boolean>() {
                              @Override
                              public void onSuccess(Boolean result) {
                                Snackbar.make(
                                        binding.getRoot(),
                                        R.string.allocate_keepers_done,
                                        Snackbar.LENGTH_SHORT)
                                    .show();
                              }

                              @Override
                              public void onFailure(Throwable t) {}
                            },
                            executor);
                      }
                    })
                .show();
          }

          @Override
          public void onFailure(Throwable t) {}
        },
        executor);
  }

  private void showSnackbar(int resId) {
    ;
  }
}
