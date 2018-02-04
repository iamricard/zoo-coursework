package internet.famous.animal.zoo.data.local;

import javax.inject.Inject;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public final class Animal {
  @Id public long id;
  public String name;
  public ToOne<Species> species;
  public ToOne<Pen> pen;

  @Inject
  public Animal() {}
}
