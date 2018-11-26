package com.wran;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Gra {
    private Set<Stan> stany;
    private List<KlasaAbstrakcji> klasyAbstrakcji;
    private int stopienD;
    private int maxG1,maxG2,maxG3;


    public Gra() {
        klasyAbstrakcji = new ArrayList<>();
        stopienD = 0;
    }

    public Gra(Set<Stan> stany, List<KlasaAbstrakcji> klasyAbstrakcji, int stopienD) {
        this.stany = stany;
        this.klasyAbstrakcji = klasyAbstrakcji;
        this.stopienD = stopienD;
        //Todo: metodka z maxami
    }

    public Set<Stan> getStany() {
        return stany;
    }

    public void setStany(Set<Stan> stany) {
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
        Set<Stan> stany = new HashSet<>();
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

    public boolean sprawdzGre(){
        List<Integer> numeryStrG1 = new ArrayList<>();
        List<Integer> numeryStrG2 = new ArrayList<>();
        List<Integer> numeryStrG3 = new ArrayList<>();

        stany.forEach(stan->{
            if(!numeryStrG1.contains(stan.getStrG1()))
                numeryStrG1.add(stan.getStrG1());
            if(!numeryStrG2.contains(stan.getStrG2()))
                numeryStrG2.add(stan.getStrG2());
            if(!numeryStrG3.contains(stan.getStrG3()))
                numeryStrG3.add(stan.getStrG3());
        });
        Collections.sort(numeryStrG1);
        Collections.sort(numeryStrG2);
        Collections.sort(numeryStrG3);

        //Sprawdzanie czy wystepuja po kolei liczby w zakresie [1, .size()] i jest ich .size()
        for(int i = 1; i <= numeryStrG1.size(); i++){
            if(!numeryStrG1.contains(i)) return false;
        }
        for(int i = 1; i <= numeryStrG2.size(); i++){
            if(!numeryStrG2.contains(i)) return false;
        }
        for(int i = 1; i <= numeryStrG3.size(); i++){
            if(!numeryStrG3.contains(i)) return false;
        }

        //Sprawdzenie czy istnieja stany po kolei
        Set<Stan> wyszStany;
        for(int i = 1; i <= numeryStrG1.size();i++){
            for (int j = 1; j <= numeryStrG2.size(); j++){
                for (int k = 1; k <= numeryStrG3.size(); k++){
                    wyszStany = Stan.znajdzStany(stany, i, j, k);
                    if(wyszStany.size() != 1) return false;
                }
            }
        }
        return true;
    }
    private int znajdzMasymalnaStrGracza(Set<Stan> stany, int gracz){
        int max = 0;
        if(gracz == 1){
            for(Stan stan : stany)
                if(max < stan.getStrG1()) max = stan.getStrG1();
            return max;
        }
        else if(gracz == 2){
            for(Stan stan : stany)
                if(max < stan.getStrG2()) max = stan.getStrG2();
            return max;
        }
        else if(gracz == 3){
            for(Stan stan : stany)
                if(max < stan.getStrG3()) max = stan.getStrG3();
            return max;
        }
        else return 0;
    }
}

