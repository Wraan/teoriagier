package com.wran;

public class Main {

    public static void main(String[] args) {


        Gra gra = new Gra();
        gra.wczytajGreZPliku(System.getProperty("user.dir") + "\\src\\com\\wran\\gra.txt");
        gra.drukujGre();
        System.out.println(gra.sprawdzGre());
        }
}
