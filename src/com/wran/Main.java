package com.wran;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String input;
        Gra gra;
        String nazwaGry;
        while (true){
            System.out.println("Wprowadz nazwe pliku z gra:");
            input = reader.nextLine();
            nazwaGry = System.getProperty("user.dir") + "\\src\\com\\wran\\" + input + ".txt";
//            nazwaGry = input + ".txt";

            if(new File(nazwaGry).exists())
                break;
        }
        gra = new Gra();
        gra.wczytajGreZPliku(nazwaGry);

        if(!gra.sprawdzGre()) {
            System.out.println("Wprowadzona gra jest nieprawidlowa");
            return;
        }
        while(!input.equals("3")){
            System.out.println("\nWybierz akcje: \n");
            System.out.println("1.Wykonaj MPESZ");
            System.out.println("2.Utworz gre ilorazowa");
            System.out.println("3.Zakoncz dzialanie programu");
            input = reader.nextLine();
            if(input.equals("1")) {
                gra = new Gra();
                gra.wczytajGreZPliku(nazwaGry);
                gra.mPesz();

            }
            else if(input.equals("2")) {
                gra = new Gra();
                gra.wczytajGreZPliku(nazwaGry);
                gra.utworzGreIlorazowa();
            }
        }
    }
}
