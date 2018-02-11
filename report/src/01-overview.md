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

### Local entities

### Local services

### Remote services

## UI

### Base classes

### Main

### Create

[OkHttp]: http://square.github.io/okhttp/
[Java Stream API]: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
