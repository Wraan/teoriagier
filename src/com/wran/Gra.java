package com.wran;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Gra {
    private Set<Stan> stany;
    private Set<Stan> stanyGryIlorazowej = new HashSet<>();
    private List<KlasaAbstrakcji> klasyAbstrakcji;
    private List<StrategiaGracza> strategieGracza1 = new ArrayList<>();
    private List<StrategiaGracza> strategieGracza2 = new ArrayList<>();
    private List<StrategiaGracza> strategieGracza3 = new ArrayList<>();
    private int stopienDrozwiazania = 1;
    private int maxG1,maxG2,maxG3;
    private List<Integer> zdominowaneStrategieGracza1 = new ArrayList<>();
    private List<Integer> zdominowaneStrategieGracza2 = new ArrayList<>();
    private List<Integer> zdominowaneStrategieGracza3 = new ArrayList<>();

    //Todo: strategia ostrożna i wypłata gwarantowana dla każdego z graczy


    public Gra() {
        klasyAbstrakcji = new ArrayList<>();
        stopienDrozwiazania = 1;
    }

    public Gra(Set<Stan> stany, List<KlasaAbstrakcji> klasyAbstrakcji, int stopienDrozwiazania) {
        this.stany = stany;
        this.klasyAbstrakcji = klasyAbstrakcji;
        this.stopienDrozwiazania = stopienDrozwiazania;
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
    public int getStopienDrozwiazania() {
        return stopienDrozwiazania;
    }
    public void setStopienDrozwiazania(int stopienDrozwiazania) {
        this.stopienDrozwiazania = stopienDrozwiazania;
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
    public int getMaxDanegoGracza(int i){
        if(i ==1)
            return maxG1;
        else if(i==2)
            return maxG2;
        else
            return  maxG3;

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




    /*Metody unwersalne*/
    public void drukujGre(){
        for(int i = 0; i<strategieGracza1.size();i++){
            for(int j = 0;j<strategieGracza2.size();j++){
                for(int k = 0;k<strategieGracza3.size();k++){
                    System.out.println(Stan.znajdzStan(stany,strategieGracza1.get(i).getNumerStrategii(),strategieGracza2.get(j).getNumerStrategii(),strategieGracza3.get(k).getNumerStrategii()).toString());
                }
            }
        }
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
    private void obliczMaxNumerStrategiiPoIlosciStrategii(){
        this.maxG1 = strategieGracza1.size();
        this.maxG2 = strategieGracza2.size();
        this.maxG3 = strategieGracza3.size();
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





    /*Metody obsługujące gry ilorazowe*/
    public void utworzGreIlorazowa(){
        obliczMaxNumerStrategii();
        porownajWszsytkieStrategieGraczaPierwszego();
        porownajWszystkieStrategieGraczaDrugiego();
        porownajWszystkieStrategieGraczaTrzeciego();
        drukujGreIlorazowa();
    }
    public void drukujGreIlorazowa(){
        //Drukowanie utworzonych klas abstrakcji
        System.out.println("Gra ilorazowa:");
        for (Stan s: stanyGryIlorazowej) {
            System.out.println(s.drukujStanyKlasAbstrakcji());
        }
    }

    //Metody obsługujące gre ilorazową gracza pierwszego
    public void porownajWszsytkieStrategieGraczaPierwszego(){
        for(int i = 1; i<=maxG1;i++){
            for(int j = i+1; j<=maxG1; j++){
                if(porownajStrategieGraczaPierwszego(i,j)){
                    //Obie nie należą
                    if(!czyStrategiaJestWKlasieAbstrakcji(1,i) && !czyStrategiaJestWKlasieAbstrakcji(1,j)){
                        klasyAbstrakcji.add(new KlasaAbstrakcji(1,getStrategiaDanegoGracza(1,i).getStany(),i,j));
                        for (Stan st:getStrategiaDanegoGracza(1,i).getStany()){
                            if(!czyGraIlorazowaZawieraStan(st)){
                                st.setNazwaKlasy1("KA1" + Integer.toString(i));
                                stanyGryIlorazowej.add(st);
                            }
                        }
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
            if(!czyStrategiaJestWKlasieAbstrakcji(1,i)){
                for (Stan st: getStrategiaDanegoGracza(1,i).getStany()) {
                    if(!czyGraIlorazowaZawieraStan(st)){
                        st.setNazwaKlasy1("KA1"+Integer.toString(i));
                        stanyGryIlorazowej.add(st);
                    }
                }
                klasyAbstrakcji.add(new KlasaAbstrakcji(1,getStrategiaDanegoGracza(1,i).getStany(),i));
            }

        }
    }
    public boolean porownajStrategieGraczaPierwszego(int str1, int str2){
        StrategiaGracza strat1 = getStrategiaDanegoGracza(1,str1);
        StrategiaGracza strat2 = getStrategiaDanegoGracza(1,str2);

        for(int i = 1; i <= maxG2;i++){
            for(int j = 1; j <=maxG3;j++){
                if(!Stan.maTakieSameWyplaty(Stan.znajdzStan(strat1.getStany(),str1,i,j),
                        Stan.znajdzStan(strat2.getStany(),str2,i,j))){
                    return false;
                }
            }
        }

        return true;
    }

    //Metody obsługujące gre ilorazową gracza drugiegio
    public void porownajWszystkieStrategieGraczaDrugiego(){
        for(int i = 1; i<=maxG2;i++){
            for(int j = i+1; j<=maxG2; j++){
                if(porownajStrategieGraczaDrugiego(i,j)){
                    //Obie nie należą
                    if(!czyStrategiaJestWKlasieAbstrakcji(2,i) && !czyStrategiaJestWKlasieAbstrakcji(2,j)){
                        //Tworzenie klasy abstrakcji
                        klasyAbstrakcji.add(new KlasaAbstrakcji(2,getStrategiaDanegoGracza(2,i).getStany(),i,j));

                        //Jeżeli gra już zawiera stany danej klasy nadaj nazwy ich klasom
                         dodajNazweKlasy(getStrategiaDanegoGracza(2,i).getStany(),2,i);

                        //Jeżeli gra zawiera stany drugiej strategii usuń je
                        for (Stan st:getStrategiaDanegoGracza(2,j).getStany()){
                            if(czyGraIlorazowaZawieraStan(st)){
                                stanyGryIlorazowej.remove(st);
                            }
                        }
                    }
                    //i należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(2,i) && !czyStrategiaJestWKlasieAbstrakcji(2,j)){
                        getKlasaGraczaIStrategii(2,i).getNumeryStrategii().add(j);
                    }
                    //j należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(2,j) && !czyStrategiaJestWKlasieAbstrakcji(2,i)){
                        getKlasaGraczaIStrategii(2,j).getNumeryStrategii().add(i);
                    }

                }
            }
            //Jeśli nie było podobnych strategii do strategii I
            if(!czyStrategiaJestWKlasieAbstrakcji(2,i)){
                dodajNazweKlasy(getStrategiaDanegoGracza(2,i).getStany(),2,i);
                klasyAbstrakcji.add(new KlasaAbstrakcji(2,getStrategiaDanegoGracza(2,i).getStany(),i));
            }

        }
    }
    public boolean porownajStrategieGraczaDrugiego(int str1, int str2){
        StrategiaGracza strat1 = getStrategiaDanegoGracza(2,str1);
        StrategiaGracza strat2 = getStrategiaDanegoGracza(2,str2);

        for(int i = 1; i <= maxG1;i++){
            for(int j = 1; j <=maxG3;j++){
                if(!Stan.maTakieSameWyplaty(Stan.znajdzStan(strat1.getStany(),i,str1,j),
                        Stan.znajdzStan(strat2.getStany(),i,str2,j))){
                    return false;
                }
            }
        }
        return true;
    }

    //Metody obsługujące gre ilorazową gracza  trzeciego
    public void porownajWszystkieStrategieGraczaTrzeciego(){
        for(int i = 1; i<=maxG3;i++){
            for(int j = i+1; j<=maxG3; j++){
                if(porownajStrategieGraczaTrzeciego(i,j)){
                    //Obie nie należą
                    if(!czyStrategiaJestWKlasieAbstrakcji(3,i) && !czyStrategiaJestWKlasieAbstrakcji(3,j)){
                        klasyAbstrakcji.add(new KlasaAbstrakcji(3,getStrategiaDanegoGracza(3,i).getStany(),i,j));

                        //Jeżeli gra już zawiera stany danej klasy nadaj nazwy ich klasom
                        dodajNazweKlasy(getStrategiaDanegoGracza(3,i).getStany(),3,i);

                        //Jeżeli gra zawiera stany drugiej strategii usuń je
                        for (Stan st:getStrategiaDanegoGracza(3,j).getStany()){
                            if(czyGraIlorazowaZawieraStan(st)){
                                stanyGryIlorazowej.remove(st);
                            }
                        }
                    }
                    //i należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(3,i) && !czyStrategiaJestWKlasieAbstrakcji(3,j)){
                        getKlasaGraczaIStrategii(3,i).getNumeryStrategii().add(j);
                    }
                    //j należy do klasy
                    else if(czyStrategiaJestWKlasieAbstrakcji(3,j) && !czyStrategiaJestWKlasieAbstrakcji(32,i)){
                        getKlasaGraczaIStrategii(3,j).getNumeryStrategii().add(i);
                    }
                }
            }
            //Jeśli nie było podobnych strategii do strategii I
            if(!czyStrategiaJestWKlasieAbstrakcji(3,i)){
                dodajNazweKlasy(getStrategiaDanegoGracza(3,i).getStany(),3,i);
                klasyAbstrakcji.add(new KlasaAbstrakcji(3,getStrategiaDanegoGracza(3,i).getStany(),i));
            }

        }
    }
    public boolean porownajStrategieGraczaTrzeciego(int str1, int str2){
        StrategiaGracza strat1 = getStrategiaDanegoGracza(3,str1);
        StrategiaGracza strat2 = getStrategiaDanegoGracza(3,str2);

        for(int i = 1; i <= maxG1;i++){
            for(int j = 1; j <=maxG2;j++){
                if(!Stan.maTakieSameWyplaty(Stan.znajdzStan(strat1.getStany(),i,j,str1),
                        Stan.znajdzStan(strat2.getStany(),i,j,str2))){
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
    public boolean czyGraIlorazowaZawieraStan(Stan stan){
        for (Stan stanGryIlo: stanyGryIlorazowej) {
            if(stan.equals(stanGryIlo))
                return true;
        }
        return false;
    }
    public void dodajNazweKlasy(Set<Stan> setStrategii, int numerGracza, int numerStrategii){
        for (Stan stanGryIlo: stanyGryIlorazowej){
            for(Stan stanStrat: setStrategii){
                if(stanStrat.equals(stanGryIlo) && numerGracza == 2){
                    stanGryIlo.setNazwaKlasy2("KA2"+Integer.toString(numerStrategii));
                    break;
                }
                else if(stanStrat.equals(stanGryIlo) && numerGracza == 3){
                    stanGryIlo.setNazwaKlasy3("KA3"+Integer.toString(numerStrategii));
                    break;
                }
            }
        }
    }




    /*Metody obsługujące MPESZ*/

    public void mPesz(){
        boolean czyStrategieSaNieporownywalne = false;
        int kroki = 1;
        //Jeśli liczba zdominowanych strategii będzie równa 0 zakończ mpesz
        while(!czyStrategieSaNieporownywalne){
            System.out.println("Krok "+ Integer.toString(kroki));
            drukujGre();
            kroki++;
            if(mpeszKrok() == 0)
                czyStrategieSaNieporownywalne = true;
            System.out.println("\n");
        }

        if(czyStrategieGraczaPierwszegoSaRownowazne() && czyStrategieGraczaDrugiegoSaRownowazne() && czyStrategieGraczaTrzeciegoSaRownowazne())
            System.out.println("Gra jest d-rozwiązalna " + stopienDrozwiazania + " stopnia");
    }


    public int mpeszKrok(){
        //Jeżeli pojawi się strategia zdominowana wartość się zwiększy co spowoduje kolejne wykonanie się mPesz
        int liczbaZdominowanychStrategii = 0;

        //Sprawdza czy któraś ze strategi któregoś z graczy jest zdominowana
        for(StrategiaGracza str : strategieGracza1){
            if(czyStrategiaGraczaPierwszegoJestZdominowana(str.getNumerStrategii())){
                liczbaZdominowanychStrategii++;
                zdominowaneStrategieGracza1.add(getStrategiaDanegoGracza(1,str.getNumerStrategii()).getNumerStrategii());;
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza I jest zdominowana");
        }
            else
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza I nie jest zdominowana");

        }

        for(StrategiaGracza str : strategieGracza2){
            if(czyStrategiaGraczaDrugiegoJestZdominowana(str.getNumerStrategii())){
                liczbaZdominowanychStrategii++;
                zdominowaneStrategieGracza2.add(getStrategiaDanegoGracza(2,str.getNumerStrategii()).getNumerStrategii());;
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza II jest zdominowana");
            }
            else
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza II nie jest zdominowana");

        }
        for(StrategiaGracza str : strategieGracza3){
            if(czyStrategiaGraczaTrzeciegoJestZdominowana(str.getNumerStrategii())){
                liczbaZdominowanychStrategii++;
                zdominowaneStrategieGracza3.add(getStrategiaDanegoGracza(3,str.getNumerStrategii()).getNumerStrategii());;
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza III jest zdominowana");
            }
            else
                System.out.println("Strategia " +Integer.toString(str.getNumerStrategii())+ " gracza III nie jest zdominowana");

        }


        //Jeżeli istnieje choć jedna strategia zdominowana
        if(liczbaZdominowanychStrategii > 0 ){
            stopienDrozwiazania++;
            //usuń zdominowane strategie gracza 1 oraz owe stany z gry
            for(int i : zdominowaneStrategieGracza1)
            {
                List<Stan> stanyDoUsuniecia = new ArrayList<>();
                strategieGracza1.remove(getStrategiaDanegoGracza(1,i));
                for(Stan s : stany){
                    if(s.getStrG1() == i)
                        stanyDoUsuniecia.add(s);
                }
                stany.removeAll(stanyDoUsuniecia);
            }
            //usuń zdominowane strategie gracza 2 oraz owe stany z gry
            for(int i : zdominowaneStrategieGracza2)
            {
                List<Stan> stanyDoUsuniecia = new ArrayList<>();
                strategieGracza1.remove(getStrategiaDanegoGracza(2,i));
                for(Stan s : stany){
                    if(s.getStrG2() == i)
                        stanyDoUsuniecia.add(s);
                }
                stany.removeAll(stanyDoUsuniecia);
            }
            //usuń zdominowane strategie gracza 3 oraz owe stany z gry
            for(int i : zdominowaneStrategieGracza3)
            {
                List<Stan> stanyDoUsuniecia = new ArrayList<>();
                strategieGracza3.remove(getStrategiaDanegoGracza(3,i));
                for(Stan s : stany){
                    if(s.getStrG3() == i)
                        stanyDoUsuniecia.add(s);
                }
                stany.removeAll(stanyDoUsuniecia);
            }


            obliczMaxNumerStrategiiPoIlosciStrategii();
            zdominowaneStrategieGracza1.clear();
            zdominowaneStrategieGracza2.clear();
            zdominowaneStrategieGracza3.clear();


        }


        return liczbaZdominowanychStrategii;
    }


    //Eliminacja strategii dominowanych, funkcja zwraca true gdy dana strategia gracza 1 jest zdominowana
    public boolean czyStrategiaGraczaPierwszegoJestZdominowana(int numerStrategii){
        int zdomin;
        int takieSame;
        for(int i = 0 ; i < strategieGracza1.size(); i++){
            zdomin = maxG2*maxG3;
            takieSame = zdomin;
            if(strategieGracza1.get(i).getNumerStrategii() == numerStrategii)
                continue;
            for(int j = 0; j < strategieGracza2.size(); j++){
                for( int k = 0; k < strategieGracza3.size(); k++)
                {
                    double porywnywana = Stan.znajdzStan(getStrategiaDanegoGracza(1,numerStrategii).getStany(), numerStrategii, strategieGracza2.get(j).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii()).getWyplG1();
                    //stan strategii do porównania
                    double doPorownania = Stan.znajdzStan(strategieGracza1.get(i).getStany(),strategieGracza1.get(i).getNumerStrategii(), strategieGracza2.get(j).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii()).getWyplG1();
                    if(porywnywana<doPorownania)
                        zdomin--;
                    else if(porywnywana==doPorownania){
                        zdomin--;
                        takieSame--;
                    }
                }
            }
            if(zdomin == 0 && takieSame != 0)
                return true;
        }
        return false;
    }
    //Eliminacja strategii dominowanych, funkcja zwraca true gdy dana strategia gracza 2 jest zdominowana
    public boolean czyStrategiaGraczaDrugiegoJestZdominowana(int numerStrategii){
        int zdomin;
        int takieSame;
        for(int i = 0 ; i < strategieGracza2.size(); i++){
            zdomin = maxG1*maxG3;
            takieSame = zdomin;
            if(strategieGracza2.get(i).getNumerStrategii() == numerStrategii)
                continue;
            for(int j = 0; j < strategieGracza1.size(); j++){
                for( int k = 0; k < strategieGracza3.size(); k++)
                {
                    double porywnywana = Stan.znajdzStan(getStrategiaDanegoGracza(2,numerStrategii).getStany(), strategieGracza1.get(j).getNumerStrategii(), numerStrategii,
                            strategieGracza3.get(k).getNumerStrategii()).getWyplG2();
                    //stan strategii do porównania
                    double doPorownania = Stan.znajdzStan(strategieGracza2.get(i).getStany(),strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(i).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii()).getWyplG2();
                    if(porywnywana<doPorownania)
                        zdomin--;
                    else if(porywnywana==doPorownania){
                        zdomin--;
                        takieSame--;
                    }
                }
            }
            if(zdomin == 0 && takieSame != 0)
                return true;
        }
        return false;
    }
    //Eliminacja strategii dominowanych, funkcja zwraca true gdy dana strategia gracza 3 jest zdominowana
    public boolean czyStrategiaGraczaTrzeciegoJestZdominowana(int numerStrategii){
        int zdomin;
        int takieSame;
        for(int i = 0 ; i < strategieGracza3.size(); i++){
            zdomin = maxG2*maxG1;
            takieSame = zdomin;
            if(strategieGracza3.get(i).getNumerStrategii() == numerStrategii)
                continue;
            for(int j = 0; j < strategieGracza1.size(); j++){
                for( int k = 0; k < strategieGracza2.size(); k++)
                {
                    double porywnywana = Stan.znajdzStan(getStrategiaDanegoGracza(3,numerStrategii).getStany(), strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(k).getNumerStrategii(),
                            numerStrategii).getWyplG3();
                    //stan strategii do porównania
                    double doPorownania = Stan.znajdzStan(strategieGracza3.get(i).getStany(),strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(k).getNumerStrategii(),
                            strategieGracza3.get(i).getNumerStrategii()).getWyplG3();
                    if(porywnywana<doPorownania)
                        zdomin--;
                    else if(porywnywana==doPorownania){
                        zdomin--;
                        takieSame--;
                    }
                }
            }
            if(zdomin == 0 && takieSame != 0)
                return true;
        }
        return false;
    }


    public boolean czyStrategieGraczaPierwszegoSaRownowazne(){
        if(strategieGracza1.size() == 1)
            return true;
        for(int i = 1 ; i < strategieGracza1.size(); i++){
            for(int j = 0; j < strategieGracza2.size(); j++){
                for( int k = 0; k < strategieGracza3.size(); k++)
                {
                    Stan porywnywana = Stan.znajdzStan(strategieGracza1.get(0).getStany(), strategieGracza1.get(0).getNumerStrategii(), strategieGracza2.get(j).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii());
                    //stan strategii do porównania
                    Stan doPorownania = Stan.znajdzStan(strategieGracza1.get(i).getStany(),strategieGracza1.get(i).getNumerStrategii(), strategieGracza2.get(j).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii());

                    if(!Stan.maTakieSameWyplaty(porywnywana,doPorownania)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean czyStrategieGraczaDrugiegoSaRownowazne(){
        if(strategieGracza2.size() == 1)
            return true;
        for(int i = 1 ; i < strategieGracza2.size(); i++){
            for(int j = 0; j < strategieGracza1.size(); j++){
                for( int k = 0; k < strategieGracza3.size(); k++)
                {
                    Stan porywnywana = Stan.znajdzStan(strategieGracza2.get(0).getStany(), strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(0).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii());
                    //stan strategii do porównania
                    Stan doPorownania = Stan.znajdzStan(strategieGracza2.get(i).getStany(),strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(i).getNumerStrategii(),
                            strategieGracza3.get(k).getNumerStrategii());

                    if(!Stan.maTakieSameWyplaty(porywnywana,doPorownania)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean czyStrategieGraczaTrzeciegoSaRownowazne(){
        if(strategieGracza3.size() == 1)
            return true;
        for(int i = 1 ; i < strategieGracza3.size(); i++){
            for(int j = 0; j < strategieGracza1.size(); j++){
                for( int k = 0; k < strategieGracza2.size(); k++)
                {
                    Stan porywnywana = Stan.znajdzStan(strategieGracza3.get(0).getStany(), strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(k).getNumerStrategii(),
                            strategieGracza3.get(0).getNumerStrategii());
                    //stan strategii do porównania
                    Stan doPorownania = Stan.znajdzStan(strategieGracza3.get(i).getStany(),strategieGracza1.get(j).getNumerStrategii(), strategieGracza2.get(k).getNumerStrategii(),
                            strategieGracza3.get(i).getNumerStrategii());

                    if(!Stan.maTakieSameWyplaty(porywnywana,doPorownania)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

