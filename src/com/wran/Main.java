package com.wran;

public class Main {

    public static void main(String[] args) {


        Gra gra = new Gra();
        gra.wczytajGreZPliku(System.getProperty("user.dir") + "\\src\\com\\wran\\gra.txt");
        gra.drukujGre();

        //System.out.println(Objects.requireNonNull(znajdzStan(stany, 2, 1, 2)).toString());

        System.out.println(Stan.porownajStany(Stan.znajdzStan(gra.getStany(),1,1,1),Stan.znajdzStan(gra.getStany(),2,1,1)));
    }






}
