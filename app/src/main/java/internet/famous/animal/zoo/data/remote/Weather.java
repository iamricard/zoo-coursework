package internet.famous.animal.zoo.data.remote;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

import internet.famous.animal.zoo.R;

public final class Weather {
  public double temperature;
  public String description;
  public int icon;

  public static final class WeatherDeserializer implements JsonDeserializer<Weather> {
    private static final Map<String, Integer> nameToResId;

    static {
      nameToResId =
          new ImmutableMap.Builder<String, Integer>()
              .put("01d", R.drawable.ic_weather_01d)
              .put("01n", R.drawable.ic_weather_01n)
              .put("02d", R.drawable.ic_weather_02d)
              .put("02n", R.drawable.ic_weather_02n)
              .put("03d", R.drawable.ic_weather_03d)
              .put("03n", R.drawable.ic_weather_03n)
              .put("04d", R.drawable.ic_weather_04d)
              .put("04n", R.drawable.ic_weather_04n)
              .put("09d", R.drawable.ic_weather_09d)
              .put("09n", R.drawable.ic_weather_09n)
              .put("10d", R.drawable.ic_weather_10d)
              .put("10n", R.drawable.ic_weather_10n)
              .put("11d", R.drawable.ic_weather_11d)
              .put("11n", R.drawable.ic_weather_11n)
              .put("13d", R.drawable.ic_weather_13d)
              .put("13n", R.drawable.ic_weather_13n)
              .put("50d", R.drawable.ic_weather_50d)
              .put("50n", R.drawable.ic_weather_50n)
              .build();
    }

    public static Gson create() {
      return new GsonBuilder()
          .registerTypeAdapter(Weather.class, new WeatherDeserializer())
          .create();
    }

    @Override
    public Weather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject base = json.getAsJsonObject();
      Weather weather = new Weather();
      weather.description =
          base.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
      weather.temperature = base.get("main").getAsJsonObject().get("temp").getAsDouble();
      weather.icon =
          nameToResId.getOrDefault(
              base.get("weather")
                  .getAsJsonArray()
                  .get(0)
                  .getAsJsonObject()
                  .get("icon")
                  .getAsString(),
              R.drawable.ic_weather_01d);
      return weather;
    }
  }
}
