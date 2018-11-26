package com.wran;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Gra {
    private List<Stan> stany;
    private List<KlasaAbstrakcji> klasyAbstrakcji;
    private int stopienD;
    private int maxG1,maxG2,maxG3;


    public Gra() {
        klasyAbstrakcji = new ArrayList<>();
        stopienD = 0;
    }

    public Gra(List<Stan> stany, List<KlasaAbstrakcji> klasyAbstrakcji, int stopienD) {
        this.stany = stany;
        this.klasyAbstrakcji = klasyAbstrakcji;
        this.stopienD = stopienD;
        //Todo: metodka z maxami
        //
    }

    public List<Stan> getStany() {
        return stany;
    }

    public void setStany(List<Stan> stany) {
        this.stany = stany;
    }

    public List<KlasaAbstrakcji> getKlasyAbstrakcji() {
        return klasyAbstrakcji;
    }

    public void setKlasyAbstrakcji(List<KlasaAbstrakcji> klasyAbstrakcji) {
        this.klasyAbstrakcji = klasyAbstrakcji;
    }

    public int getStopienD() {
        return stopienD;
    }

    public void setStopienD(int stopienD) {
        this.stopienD = stopienD;
    }


    public void drukujGre(){
        stany.forEach(stan -> System.out.println(stan.toString()));
    }


    public void wczytajGreZPliku(String file){
        List<Stan> stany = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach((line)->{
                List<String> parts = Arrays.asList(line.split(","));
                parts.forEach(e -> parts.set(parts.indexOf(e), e.trim()));
                Stan stan = new Stan(Integer.parseInt(parts.get(0)), Integer.parseInt(parts.get(1)),
                        Integer.parseInt(parts.get(2)), Double.parseDouble(parts.get(3)),
                        Double.parseDouble(parts.get(4)), Double.parseDouble(parts.get(5)));
                stany.add(stan);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stany = stany;
    }

    private void obliczMaxNumerStrategii(){
        //Todo 3x ustawianie maxa
    }


    //Todo: Metoda sprawdzająca klasy abstrakcji
}

