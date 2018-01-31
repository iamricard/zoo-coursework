package internet.famous.animal.zoo;

import android.app.Activity;

import io.objectbox.Box;

public final class BoxBuilder {
  public static <T> Box<T> build(Activity activity, Class<T> tClass) {
    return ((App) activity.getApplication()).getBoxStore().boxFor(tClass);
  }
}
