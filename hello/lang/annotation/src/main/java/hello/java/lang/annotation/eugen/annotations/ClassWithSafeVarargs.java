package hello.java.lang.annotation.eugen.annotations;

class ClassWithSafeVarargs<T> {

    @SafeVarargs
    final void iterateOverVarargs(T... args) {
        for (T x : args) {
            // do stuff with x
        }
    }
}
