package com.wran;

import java.util.List;

public class StrategiaGracza {

    private List<Stan> stany;
    private int numerGracza;
    private int numerStrategii;

    public StrategiaGracza() {
    }

    public StrategiaGracza(List<Stan> stany, int numerGracza, int numerStrategii) {
        this.stany = stany;
        this.numerGracza = numerGracza;
        this.numerStrategii = numerStrategii;
    }

    public List<Stan> getStany() {
        return stany;
    }

    public void setStany(List<Stan> stany) {
        this.stany = stany;
    }

    public int getNumerGracza() {
        return numerGracza;
    }

    public void setNumerGracza(int numerGracza) {
        this.numerGracza = numerGracza;
    }

    public int getNumerStrategii() {
        return numerStrategii;
    }

    public void setNumerStrategii(int numerStrategii) {
        this.numerStrategii = numerStrategii;
    }
}
