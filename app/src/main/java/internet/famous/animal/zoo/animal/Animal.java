package internet.famous.animal.zoo.animal;

import internet.famous.animal.zoo.species.Species;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public final class Animal {
  @Id public long id;
  public String name;
  public ToOne<Species> species;
}
