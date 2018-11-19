package com.wran;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {

    private static Stan znajdzStan(List<Stan> stany, int strG1, int strG2, int strG3){
        for(Stan s : stany){
            if(s.getStrG1() == strG1 && s.getStrG2() == strG2 && s.getStrG3() == strG3)
                return s;
        }
        return null;
    }

    private static List<Stan> wczytajGre(String file){
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
        return stany;
    }

    public static void main(String[] args) {
        List<Stan> stany = wczytajGre(System.getProperty("user.dir") + "\\src\\com\\wran\\gra.txt");
        stany.forEach(stan -> System.out.println(stan.toString()));

        System.out.println(Objects.requireNonNull(znajdzStan(stany, 2, 1, 2)).toString());
    }
}
