package com.wran;

public class Main {

    public static void main(String[] args) {


        Gra gra = new Gra();
        gra.wczytajGreZPliku(System.getProperty("user.dir") + "\\src\\com\\wran\\gra.txt");
        gra.drukujGre();
        System.out.println(gra.sprawdzGre());
        gra.utworzGreIlorazowa();
        for(int i = 0; i<gra.getKlasyAbstrakcji().size();i++){
            System.out.println(gra.getKlasyAbstrakcji().get(i).toString());
        }

        }
}
