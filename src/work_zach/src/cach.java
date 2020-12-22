import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Objects;

// перечисление для валют

 enum Сurrency{
    DOLLAR,
    YUAN,
    RUBLE
}

//  реализация класса (собственный enum)
final class MyCurrency {
    public static final MyCurrency DOLLAR = new MyCurrency();
    public static final MyCurrency YUAN = new MyCurrency();
    public static final MyCurrency RUBLE = new MyCurrency();

    private MyCurrency() {}
}

// лдасс комплексных чисел
final class MyComplex implements Comparable<MyComplex> {

    private double realPart;
    private double imaginaryPart;

    public MyComplex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public static MyComplex add(MyComplex a, MyComplex b) {
        return new MyComplex(a.realPart + b.realPart, a.imaginaryPart + b.imaginaryPart);
    }

    public static MyComplex mul(MyComplex a, MyComplex b) {
        return new MyComplex(
                a.realPart * b.realPart - a.imaginaryPart * b.imaginaryPart,
                a.imaginaryPart * b.realPart + a.realPart * b.imaginaryPart
        );
    }

    public static MyComplex summ(MyComplex a, MyComplex b) {
        return new MyComplex(
                a.realPart + b.realPart,
                a.imaginaryPart + b.imaginaryPart
        );

    }

    public static MyComplex minus(MyComplex a, MyComplex b) {
        return new MyComplex(
                a.realPart - b.realPart,
                a.imaginaryPart - b.imaginaryPart
        );

    }

    public double norm() {
        return Math.sqrt(this.realPart * this.realPart + this.imaginaryPart * this.imaginaryPart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyComplex complex = (MyComplex) o;
        return Double.compare(complex.realPart, realPart) == 0 && Double.compare(complex.imaginaryPart, imaginaryPart) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.realPart, this.imaginaryPart);
    }

    @Override
    public int compareTo(final MyComplex o) {
        final double myNorm= norm();
        final double oNorm = o.norm();
        return Double.compare(myNorm, oNorm);
    }


}

class cash {
    public static void main(String[] args) {}
}
