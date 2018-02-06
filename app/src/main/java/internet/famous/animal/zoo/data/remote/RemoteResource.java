package internet.famous.animal.zoo.data.remote;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RemoteResource<EntityType> {
  private final MediatorLiveData<EntityType> result = new MediatorLiveData<>();

  @MainThread
  public RemoteResource() {
    result.setValue(null);
    LiveData<EntityType> local = loadLocally();
    result.addSource(
        local,
        data -> {
          result.removeSource(local);
          if (shouldFetch(data)) {
            fetch(local);
          } else {
            result.addSource(local, result::setValue);
          }
        });
  }

  private void fetch(final LiveData<EntityType> local) {
    result.addSource(local, result::setValue);
    loadRemotely()
        .enqueue(
            new Callback<EntityType>() {
              @Override
              public void onResponse(Call<EntityType> call, Response<EntityType> response) {
                result.removeSource(local);
                saveResultAndReInit(response.body());
              }

              @Override
              public void onFailure(Call<EntityType> call, Throwable t) {
                // TODO
              }
            });
  }

  @SuppressLint("StaticFieldLeak")
  @MainThread
  private void saveResultAndReInit(EntityType response) {
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... voids) {
        save(response);
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        result.addSource(loadLocally(), result::setValue);
      }
    }.execute();
  }

  public final LiveData<EntityType> asLiveData() {
    return result;
  }

  public abstract void save(EntityType entity);

  public abstract LiveData<EntityType> loadLocally();

  public abstract Call<EntityType> loadRemotely();

  public abstract boolean shouldFetch(EntityType entity);
}
