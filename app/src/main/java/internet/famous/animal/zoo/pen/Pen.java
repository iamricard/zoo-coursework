package internet.famous.animal.zoo.pen;

import internet.famous.animal.zoo.keeper.Keeper;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public final class Pen {
  @Id public long id;
  public ToOne<Keeper> keeper;
  public long landSpace = 0;
  public long waterSpace = 0;
  public long airSpace = 0;
  public boolean isPettable = false;

  public static Pen landPen(long landSpace, boolean isPettable) {
    Pen p = new Pen();
    p.landSpace = landSpace;
    p.isPettable = isPettable;
    return p;
  }

  public static Pen aquarium(long waterSpace) {
    Pen p = new Pen();
    p.waterSpace = waterSpace;
    return p;
  }

  public static Pen amphibiousPen(long landSpace, long waterSpace) {
    Pen p = new Pen();
    p.landSpace = landSpace;
    p.waterSpace = waterSpace;
    return p;
  }

  public static Pen aviary(long airSpace) {
    Pen p = new Pen();
    p.airSpace = airSpace;
    return p;
  }
}
