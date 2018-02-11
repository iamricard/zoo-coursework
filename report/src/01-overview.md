# Overview

In this chapter I will attempt to provide a medium-level overview of the
application architecture. I will also mention the few libraries that are used on
top of the already existing _Android Platform_ framework.

## Libraries

### Retrofit/OkHttp

> _Type-safe HTTP client for Android and Java by Square, Inc._
>
> <https://github.com/square/retrofit>

Square's retrofit is built on top of another Square library: [OkHttp][]. It
comes with a set of Java annotations that make it straight forward and safe to
declare a remote service in Java and Android. Here it is used to model the
OpenWeatherMap API data.

### Lightweight-Stream-API

> _Stream API from Java 8 rewritten on iterators for Java 7 and below._
>
> <https://github.com/aNNiMON/Lightweight-Stream-API>

To allow for backward compatibility on older Android devices (back to Lollipop
in this application) and still be able to use the nifty [Java Stream API][] I
have included a library that backports the functionality to Android friendly
idioms.

### Gson

> _Gson is a Java library that can be used to convert Java Objects into their
> JSON representation. It can also be used to convert a JSON string to an
> equivalent Java object. Gson can work with arbitrary Java objects including
> pre-existing objects that you do not have source-code of._
>
> <https://github.com/google/gson>

It is widely known that the default _org.json_ is a terrible implementation of
a JSON parsing library. It is also widely known the Gson and Jackson are great
implementations solving the same problem. Due to my familiarity with it I have
chosen the former.

I use it to deserialize the Weather data from the API as well as load the
initial seed data from a JSON file.

### Guava

> _Guava is a set of core libraries that includes new collection types (such as
> multimap and multiset), immutable collections, a graph library, functional
> types, an in-memory cache, and APIs/utilities for concurrency, I/O, hashing,
> primitives, reflection, string processing, and much more!_
>
> <https://github.com/google/guava>

I don't think Guava needs much explanation due to its widespread use in the
industry. In the particular case of this application it is used to have better
futures for asynchronous and non-blocking operations, as well as immutable data
structures.

### Dagger

> _Dagger 2 is a compile-time evolution approach to dependency injection.
> Taking the approach started in Dagger 1.x to its ultimate conclusion, Dagger
> 2.x eliminates all reflection, and improves code clarity by removing the
> traditional ObjectGraph/Injector in favor of user-specified @Component
> interfaces._
>
> <https://github.com/google/dagger>

Like Guava, Dagger 2 is a very common library to use dependency injection. Even
more so when it comes to Android applications, due to its performance.
_Dependency Injection_ solves many problems and is out of the scope of
explanation in this report.

## Data

To quote the coursework specification:

> _Your program should demonstrate good object oriented design and modelling
> techniques. __The domain model should be separate from user interface (UI)__
> controllers and views. You are expected to use object-oriented concepts such
> as inheritance and polymorphism where it is required and makes sense to do
> so._ \[Emphasis mine\]

This section focuses on dicussing the __bolded__ section. The apps models can be
divided many ways, but one that makes sense is local v. remote, entity v.
service. In the context of this application: a service retrieves or creates data
on-demand; an entity is data that will eventually be persisted.

### Local entities

*   `Animal`
    *   Has a name.
    *   Has a Many-to-One relationship to Species entity.
*   `Keeper`
    *   Has a name.
    *   One-to-Many relationship to Pen entity.
*   `Pen`
    *   Has the amount of land, air, water, and whether or not is pettable.
    *   Many-to-One relationship to Keeper entity.
*   `Species`
    *   Has a name.
    *   Has the amount of land, air, water needed, and whether or not is
    pettable.
    *   One-to-Many relationship to Animal entity.

### Local services

`AllocatorService` automatically assigns keepers to un-assigned pens, and
un-assigned animals to available pens.

### Remote services

`OpenWeatherMapService` fetches weather data about Barcelona (where the Zoo is
located), and turns it into a POJO (Plain Old Java Object) using Gson.

## UI

To quote the coursework specification:

> _Your program should demonstrate good object oriented design and modelling
> techniques. The domain model should be separate from __user interface (UI)
> controllers and views__. You are expected to use object-oriented concepts such
> as inheritance and polymorphism where it is required and makes sense to do
> so._ \[Emphasis mine\]

This section focuses on dicussing the __bolded__ section. There are two main
sections to this application: main, where the user can see all available data;
create, where the user can create new data.

For the requirements we read:

> _If a pen is full or otherwise unable to accommodate the animal, the user
> should see an error message explaining why._

I believe that an error is always bad if we can avoid it. Precisely for this
reason, when a user wants to assign an animal to a pen the only choices that
they are prompted with are those that can accommodate the animal.

[OkHttp]: http://square.github.io/okhttp/
[Java Stream API]: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
