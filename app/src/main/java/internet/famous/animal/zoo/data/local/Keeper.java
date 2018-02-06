package internet.famous.animal.zoo.data.local;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import javax.inject.Inject;

@Entity
public final class Keeper {
  @Id public long id;
  public String name;
  @Backlink public ToMany<Pen> pens;

  @Inject
  public Keeper() {}
}
