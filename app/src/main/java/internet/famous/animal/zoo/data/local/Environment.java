package internet.famous.animal.zoo.data.local;

public enum Environment {
  DRY("Dry"),
  PETTING("Pettable"),
  WATER("Water"),
  HYBRID("Hybrid"),
  AIR("Air");

  private final String name;

  Environment(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
