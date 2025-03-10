package hu.szamalk;

import hu.szamalk.modell.Konyv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private List<Konyv> konyvek;
    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        konyvek = new ArrayList<>();
        beolvasas();
        feladatok();
    }

    private void beolvasas() throws IOException {
        String fn = "konyvek.txt";
        List<String> sorok = Files.readAllLines(Path.of(fn));
        sorok.forEach(s -> {
            Konyv konyv = new Konyv(s);
            konyvek.add(konyv);
        });
        System.out.println(konyvek);
    }

    private void feladatok() {
        System.out.println("Könyvek átlaga: "+atlagAr());
        System.out.println("Melyik években adtak ki könyvet: "+kiadKonyv());
        System.out.println("Melyik a legrégebbi kiadási év: ");
        System.out.println(Collections.min(kiadKonyv()));
        System.out.println("Melyik években hány könyvet adtak ki: "+kiadKonyvEvbenHany());
    }

    private Map<Integer, Integer> kiadKonyvEvbenHany() {
        Map<Integer, Integer> kiadHany = new HashMap<>();
        konyvek.forEach(konyv -> kiadHany.put(konyv.getKiadasEve(), kiadHany.getOrDefault(konyv.getKiadasEve(),0)+1));
        return  kiadHany;
    }

    private Set<Integer> kiadKonyv() {
        Set<Integer> kiadottEvek = new HashSet<>();
        for (Konyv konyv : konyvek) {
            kiadottEvek.add(konyv.getKiadasEve());
        }
        return kiadottEvek;
    }


    private double atlagAr() {
       int osszeg= 0;
        for (Konyv konyv:konyvek){
            osszeg+= konyv.getAr();
        }
        return(double)osszeg/konyvek.size();
    }
}