package com.wran;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Wprowadz nazwe pliku z gra:");
        String input = reader.nextLine();
        String nazwaGry = input += ".txt";
        Gra gra = new Gra();
//        gra.wczytajGreZPliku(System.getProperty("user.dir") + "\\src\\com\\wran\\"+input);
        gra.wczytajGreZPliku(nazwaGry);
        if(!gra.sprawdzGre()) {
            System.out.println("Wprowadzona gra jest nieprawidlowa");
            return;
        }
        while(!input.equals("3")){
            System.out.println("Wybierz akcje: \n");
            System.out.println("1.Wykonaj MPESZ");
            System.out.println("2.Utworz gre ilorazowa");
            System.out.println("3.Zakoncz dzialanie programu");
            input = reader.nextLine();
            if(input.equals("1")) {
                gra.mPesz();
                gra.wczytajGreZPliku(nazwaGry);
            }
            else if(input.equals("2")) {
                gra.utworzGreIlorazowa();
                gra.wczytajGreZPliku(nazwaGry);
            }
        }
    }
}
