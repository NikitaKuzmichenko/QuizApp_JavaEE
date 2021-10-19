package by.epam.jwd.testingApp.entity;

import java.io.Serializable;

public class Pair<T, U> implements Serializable {
    private T t;
    private U u;

    public Pair(){}

    public Pair(T t, U u) {
        this.t= t;
        this.u= u;
    }

    public T getT() {
        return t;
    }

    public U getU() {
        return u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (t != null ? !t.equals(pair.t) : pair.t != null) return false;
        return u != null ? u.equals(pair.u) : pair.u == null;
    }

    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (u != null ? u.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "t=" + t +
                ", u=" + u +
                '}';
    }
}
