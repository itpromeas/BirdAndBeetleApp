package org.meas.Square;

public class SquareCoordinate {

    public SquareCoordinate() {
    }

    public SquareCoordinate(int abscisse, int ordinate) {
        this.abscisse = abscisse;
        this.ordinate = ordinate;
    }

    int abscisse;
    int ordinate;

    public int getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(int ordinate) {
        this.ordinate = ordinate;
    }

    @Override
    public String toString() {
        return "SquareCoordinate{" +
                "abscisse=" + abscisse +
                ", ordinate=" + ordinate +
                '}';
    }
}
