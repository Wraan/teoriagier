package com.wran;

import java.util.ArrayList;
import java.util.List;

public class KlasaAbstrakcji {
    List<Integer> numerGracza;
    List<Integer> numeryStrategii;
    List<Stan> stany;

    public KlasaAbstrakcji() {
        stany = new ArrayList<>();
        numeryStrategii = new ArrayList<>();
    }

    public KlasaAbstrakcji(List<Integer> numerGracza, List<Integer> numeryStrategii, List<Stan> stany) {
        this.numerGracza = numerGracza;
        this.numeryStrategii = numeryStrategii;
        this.stany = stany;
    }

    public List<Integer> getNumerGracza() {
        return numerGracza;
    }

    public void setNumerGracza(List<Integer> numerGracza) {
        this.numerGracza = numerGracza;
    }

    public List<Integer> getNumeryStrategii() {
        return numeryStrategii;
    }

    public void setNumeryStrategii(List<Integer> numeryStrategii) {
        this.numeryStrategii = numeryStrategii;
    }

    public List<Stan> getStany() {
        return stany;
    }

    public void setStany(List<Stan> stany) {
        this.stany = stany;
    }
}
