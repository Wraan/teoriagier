package com.wran;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Wprowadź nazwę pliku z grą:");
        String input = reader.nextLine();
        input +=".txt";
        Gra gra = new Gra();
        gra.wczytajGreZPliku(System.getProperty("user.dir") + "\\src\\com\\wran\\"+input);
        if(!gra.sprawdzGre())
            System.out.println("Wprowadzona gra jest nieprawidłowa");
        while(!input.equals("1") && !input.equals("2")){
            System.out.println("Wybierz akcję: \n");
            System.out.println("1.Wykonaj MPESZ");
            System.out.println("2.Utwórz grę ilorazową");
            input = reader.nextLine();
        }
        if(input.equals("1"))
            gra.mPesz();
        else if(input.equals("2"))
            gra.utworzGreIlorazowa();

        }
}
