package ru.stazaev.agency;

import java.util.Objects;

public class Tt {
    private String a;
    private String b;

    public Tt(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tt tt = (Tt) o;
        return Objects.equals(a, tt.a) && Objects.equals(b, tt.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
