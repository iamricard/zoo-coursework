package internet.famous.animal.zoo.keeper;

import internet.famous.animal.zoo.pen.Pen;
import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public final class Keeper {
  @Id public long id;
  public String name;
  @Backlink public ToMany<Pen> pens;

  public Keeper() {}

  public Keeper(String name) {
    this.name = name;
  }
}
