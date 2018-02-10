package internet.famous.animal.zoo;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import internet.famous.animal.zoo.data.local.Animal;
import internet.famous.animal.zoo.data.local.Keeper;
import internet.famous.animal.zoo.data.local.Pen;
import internet.famous.animal.zoo.data.local.Species;
import internet.famous.animal.zoo.di.DaggerAppComponent;
import io.objectbox.Box;

public final class App extends Application implements HasActivityInjector {
  private static final Gson gson = new Gson();

  private static final String PREFS_FILENAME = "zoo-prefs";
  private static final String DB_INITIALIZED_KEY = "dbInitialized";

  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
  }

  @Inject DispatchingAndroidInjector<Activity> activityDispatchingInjector;
  @Inject Box<Animal> animals;
  @Inject Box<Keeper> keepers;
  @Inject Box<Pen> pens;
  @Inject Box<Species> species;

  @Override
  public void onCreate() {
    super.onCreate();
    DaggerAppComponent.builder().application(this).build().inject(this);
    seedData();
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingInjector;
  }

  private boolean needsDataSeeds() {
    return !getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)
        .getBoolean(DB_INITIALIZED_KEY, false);
  }

  private void seedData() {
    if (getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE).getBoolean(DB_INITIALIZED_KEY, false)) {
      return;
    }
    // Remove all existing data
    animals.removeAll();
    keepers.removeAll();
    pens.removeAll();
    species.removeAll();

    // Parse JSON file
    InputStream resource = getResources().openRawResource(R.raw.seed_data);
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
    StringBuilder stringBuilder = new StringBuilder();
    try {
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line).append("\n");
      }
    } catch (IOException ignored) {
    }
    JsonSeedData data = gson.fromJson(stringBuilder.toString(), JsonSeedData.class);

    // Put all seed data
    species.put(data.species);
    keepers.put(data.keepers);

    // Mark DB as initialized
    getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)
        .edit()
        .putBoolean(DB_INITIALIZED_KEY, true)
        .apply();
  }

  private static final class JsonSeedData {
    @SerializedName("species")
    public List<Species> species;

    @SerializedName("keepers")
    public List<Keeper> keepers;
  }
}
