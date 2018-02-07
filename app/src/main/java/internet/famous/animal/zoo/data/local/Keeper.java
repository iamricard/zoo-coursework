package internet.famous.animal.zoo.data.local;

import javax.inject.Inject;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public final class Keeper {
  public static final Keeper NONE;

  static {
    NONE = new Keeper();
    NONE.name = "None";
    NONE.id = -1;
  }

  @Id public long id;
  public String name;
  @Backlink public ToMany<Pen> pens;

  @Inject
  public Keeper() {}
}
