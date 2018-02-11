# Design Choices

Some of the choice I have made when building this project deviate a little from
the initially suggested approach and can therefore benefit from some extra
explanation.

## [Android][], not [JavaFX][]

The design of this assignment seems to be testing more of UI development than it
does OOP and other concepts covered in the _Advanced Programming_ module.
While one lecture did briefly touch on what _JavaFX_ is, and how it might be
and improvement on Java's [Swing][] the lecture was not nearly in-depth enough
to justify using a framework that has been put on maintenance mode[^1].

Instead the more sensible choices seemed to use a web-framework with a UI built
on HTML, CSS, and JavaScript, such as [Spark][]; or the most popular mobile
platform's SDK, _Android_. I would argue an even better choice for the given
requirements (building a UI) would have been a 100% [TypeScript][] (or plain
_JavaScript_) application using the browser's [LevelDB][], [LocalStorage][], or
[Firebase][] as a persistance layer but one of the requirements commanded _Java_
as the main language so they were not valid options. My most recient
professional experience in _Java_ included building _Android_ applications so
that was, from my point view, the most sensible choice given all the previously
stated arguments was to use the _Android Platform_:

1.  Existing knowledge
1.  Industry standards
1.  Assignment requirements

## Pens

The assignment's wording was very vague and it was specially so when referring
to the _pens_. I will unpack the text presented in the specification and explain
my interpretation, in an attempt to get the reader of this report and myself on
the same page:

---

> _A single pen can only contain animals of the same type._

It's unclear what the specification refers to here when it comes to _type_. An
animal type could be one of the provided _environments_ (water, dry, hybrid,
and air), or it could also mean a species (Sloth, Cat, Dog, etc.). For this
implementation the assumption made is that type refers to _environment_, not
_species_. Using the _species_ as the type would be a trivial change with the
existing implementation.

---

> _Each pen has a length, width, and temperature. Aquariums and aviaries also
> have a height._

There are two things worth noting in this particular part of the specification:

1.  There is no need to store the length, the width, or the height. If a pen is
dry its are will be land in m^2, if it's an aviary it'll be m^3 in air space,
and in water for aquariums. If it's hybrid, to be able to accomodate, for
example, a Hippo, the pen will have two measurements, land in m^2, and water in
m^3.

2.  The temperature is irrelevant with the provided specification. None of the
examples provided by the person writing the spec made mention of an animal
needing specific temperatures and is therefore irrelevant information that need
not be stored. Were this requirement changed, storing temperature data would be
a trivial change with this implementation.

---

> _Pens including water have a water volume and can be either salt or fresh
> water._

As with to the temperature data whether it is fresh or salt water is irrelevant.
Examples only specify water, dry, petting, air, or part-dry, party-water. Were
this requirement changed, storing water type data would be a trivial change
with this implementation.

## Species class as opposed to multiple Animal classes

The assignment presented looks like a more lengthy example of the canonical
concrete `Dog` class extends from `abstract` `Animal` class. The difference
being the need for a UI and some other entities, like `Keeper`, and `Pen`. If
that were the case it would indicate that the coursework designer expected or
suggested we create one `class` for each sample animal provided.

While that approach works, I believe it would be severely defficient in a
real-world application when managing a zoo. The most obvious short-coming of
the (TODO)afformentioned architecture is that any new species registered by the
zoo would require actual source code change. Instead I propose this: treat each
animal entity as having a relationship to a _species_ entity. Akin to how in the
game of _Pokémon_ one would have a _Pokédex_ with all known _Pokémon types_
(_species_) and a separate compartment with all the actual Pokémon. One
wouldn't create a new `class` for every new _Pokémon type_ that appeared.
Instead, one would treat each _species_ as a simply data. This would allow new
discoveries about the _species_ to be changed as needed from a UI and without
changing the source code itself.

## Keepers don't have a type

The module specification makes each of the initial Keepers be responsible for a
given type of pen. I have chosen not to do that. It seems to be a silly choice.
To change that it's a trivial modification.

[android]: https://android.com
[javafx]: https://www.wikiwand.com/en/JavaFX
[swing]: https://www.wikiwand.com/en/Swing_(Java)
[spark]: http://sparkjava.com/
[typescript]: https://www.typescriptlang.org/
[leveldb]: http://leveldb.org/
[localstorage]: https://html.spec.whatwg.org/multipage/webstorage.html
[firebase]: https://firebase.google.com/
[^1]: Last version is JavaFX 8, released nearly 4 years ago in March of 2014.
