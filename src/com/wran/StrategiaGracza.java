package com.wran;

import java.util.List;
import java.util.Set;

public class StrategiaGracza {

    private Set<Stan> stany;
    private int numerGracza;
    private int numerStrategii;

    public StrategiaGracza() {
    }

    public StrategiaGracza(Set<Stan> stany, int numerGracza, int numerStrategii) {
        this.stany = stany;
        this.numerGracza = numerGracza;
        this.numerStrategii = numerStrategii;
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

    public Set<Stan> getStany() {
        return stany;
    }

    public void setStany(Set<Stan> stany) {
        this.stany = stany;
    }
}
