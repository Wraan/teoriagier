package com.wran;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Gra {
    private Set<Stan> stany;
    private List<KlasaAbstrakcji> klasyAbstrakcji;
    private List<StrategiaGracza> strategieGracza1 = new ArrayList<>();
    private List<StrategiaGracza> strategieGracza2 = new ArrayList<>();
    private List<StrategiaGracza> strategieGracza3 = new ArrayList<>();
    private int stopienD;
    private int maxG1,maxG2,maxG3;

    //Todo: strategia ostrożna i wypłta gwarantowana dla każdego z graczy


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
    public List<StrategiaGracza> getStrategieGracza1() {
        return strategieGracza1;
    }
    public void setStrategieGracza1(List<StrategiaGracza> strategieGracza1) {
        this.strategieGracza1 = strategieGracza1;
    }
    public List<StrategiaGracza> getStrategieGracza2() {
        return strategieGracza2;
    }
    public void setStrategieGracza2(List<StrategiaGracza> strategieGracza2) {
        this.strategieGracza2 = strategieGracza2;
    }
    public List<StrategiaGracza> getStrategieGracza3() {
        return strategieGracza3;
    }
    public void setStrategieGracza3(List<StrategiaGracza> strategieGracza3) {
        this.strategieGracza3 = strategieGracza3;
    }
    public int getMaxG1() {
        return maxG1;
    }
    public void setMaxG1(int maxG1) {
        this.maxG1 = maxG1;
    }
    public int getMaxG2() {
        return maxG2;
    }
    public void setMaxG2(int maxG2) {
        this.maxG2 = maxG2;
    }
    public int getMaxG3() {
        return maxG3;
    }
    public void setMaxG3(int maxG3) {
        this.maxG3 = maxG3;
    }
    public StrategiaGracza getStrategiaDanegoGracza(int numerGracza, int numerStrategi) {
        if(numerGracza == 1){
            for (StrategiaGracza str1: strategieGracza1){
                if(str1.getNumerStrategii() == numerStrategi)
                    return str1;
            }
        }
        else if(numerGracza == 2){
            for (StrategiaGracza str2: strategieGracza2){
                if(str2.getNumerStrategii() == numerStrategi)
                    return str2;
            }
        }
         else{
            for (StrategiaGracza str3: strategieGracza3){
                if(str3.getNumerStrategii() == numerStrategi)
                    return str3;
            }
        }
        return null;
    }
    public KlasaAbstrakcji getKlasaGraczaIStrategii(int numerGracza, int numerStrategii){
        for (KlasaAbstrakcji ka: klasyAbstrakcji) {
            if(ka.numerGracza == numerGracza)
                for (int nrStr: ka.getNumeryStrategii()) {
                    if(nrStr == numerStrategii)
                        return ka;
                }
        }
        return null;
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
        obliczMaxNumerStrategii();
        utworzStrategieGraczy();
    }

    private int znajdzMaksymalnaStrGracza(int gracz){
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
    private void obliczMaxNumerStrategii(){
        this.maxG1 = znajdzMaksymalnaStrGracza(1);
        this.maxG2 = znajdzMaksymalnaStrGracza(2);
        this.maxG3 = znajdzMaksymalnaStrGracza(3);
    }


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

    public void utworzGreIlorazowa(){
        obliczMaxNumerStrategii();
        porownajWszsytkieStrategieGraczaPierwszego();
    }

    //Mteody tworzące strategie
    public Set<Stan> znajdzStanyDlaDanejStrategiiGraczaPierwszego(int numerStrategii){
        Set<Stan> stanyTymczasowe = new HashSet<>();
        for(int i = 1;i<=maxG2;i++){
            for(int j = 1; j<=maxG3 ; j++){
                stanyTymczasowe.add(Stan.znajdzStan(stany,numerStrategii,i,j));
            }
        }
        return stanyTymczasowe;
    }
    public Set<Stan> znajdzStanyDlaDanejStrategiiGraczaDrugiego(int numerStrategii){
        Set<Stan> stanyTymczasowe = new HashSet<>();
        for(int i= 1;i<=maxG1;i++){
            for(int j = 1; j<=maxG3 ; j++){
                stanyTymczasowe.add(Stan.znajdzStan(stany,i,numerStrategii,j));
            }
        }
        return stanyTymczasowe;
    }
    public Set<Stan> znajdzStanyDlaDanejStrategiiGraczaTrzeciego(int numerStrategii){
        Set<Stan> stanyTymczasowe = new HashSet<>();
        for(int i= 1;i<=maxG1;i++){
            for(int j = 1; j<=maxG2 ; j++){
                stanyTymczasowe.add(Stan.znajdzStan(stany,i,j,numerStrategii));
            }
        }
        return stanyTymczasowe;
    }
    private void utworzStrategieGraczy(){
        for(int i=1;i<=maxG1;i++){
            strategieGracza1.add(new StrategiaGracza(znajdzStanyDlaDanejStrategiiGraczaPierwszego(i),1,i)) ;
        }
        for(int i=1;i<=maxG2;i++){
            strategieGracza2.add(new StrategiaGracza(znajdzStanyDlaDanejStrategiiGraczaDrugiego(i),2,i)) ;
        }
        for(int i=1;i<=maxG3;i++){
            strategieGracza3.add(new StrategiaGracza(znajdzStanyDlaDanejStrategiiGraczaTrzeciego(i),3,i)) ;
        }
    }


    //Metody obsługujące gre ilorazową gracza pierwszego
    public void porownajWszsytkieStrategieGraczaPierwszego(){
        boolean flaga;

        for(int i = 1; i<=maxG1;i++){
            flaga = false;
            for(int j = i+1; j<=maxG1; j++){
                if(porownajStrategieGraczaPierwszego(i,j)){
                    //Obie nie należą
                    if(!czyStrategiaJestWKlasieAbstrakcji(1,i) && !czyStrategiaJestWKlasieAbstrakcji(1,j)){
                        klasyAbstrakcji.add(new KlasaAbstrakcji(1,getStrategiaDanegoGracza(1,i).getStany(),i,j));
                    }
                    //i należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(1,i) && !czyStrategiaJestWKlasieAbstrakcji(1,j)){
                        getKlasaGraczaIStrategii(1,i).getNumeryStrategii().add(j);
                    }
                    //j należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(1,j) && !czyStrategiaJestWKlasieAbstrakcji(1,i)){
                        getKlasaGraczaIStrategii(1,j).getNumeryStrategii().add(i);
                    }

                }
            }
            //Jeśli nie było podobnych strategii do strategii I
            if(!czyStrategiaJestWKlasieAbstrakcji(1,i))
                klasyAbstrakcji.add(new KlasaAbstrakcji(1,getStrategiaDanegoGracza(1,i).getStany(),i));
        }
    }
    public boolean porownajStrategieGraczaPierwszego(int str1, int str2){
        StrategiaGracza strat1 = getStrategiaDanegoGracza(1,str1);
        StrategiaGracza strat2 = getStrategiaDanegoGracza(1,str2);

        for(int i = 1; i <= maxG2;i++){
            for(int j = 1; j <=maxG3;j++){
                if(!Stan.porownajStanyDoGryIlorazowej(Stan.znajdzStan(strat1.getStany(),str1,i,j),
                        Stan.znajdzStan(strat2.getStany(),str2,i,j))){
                    return false;
                }
            }
        }

        return true;
    }
    public boolean czyStrategiaJestWKlasieAbstrakcji(int numerGracza, int numerStrategii){
        for (KlasaAbstrakcji ka: klasyAbstrakcji) {
            if(ka.numerGracza == numerGracza){
                for (int nr: ka.numeryStrategii) {
                    if(numerStrategii == nr)
                        return true;
                }
            }
        }
        return false;
    }

    //Todo: Metody dla gracza 2 i 3
}

