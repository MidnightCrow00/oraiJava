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
            if (!(s.contains("CÍM")||s.contains("===="))){
            Konyv konyv = new Konyv(s);
            konyvek.add(konyv);
            }
        });
        System.out.println(konyvek);
    }

    private void feladatok() {
        System.out.println("Könyvek átlaga: "+atlagAr());
        System.out.println("Melyik években adtak ki könyvet: "+kiadKonyv());
        System.out.println("Melyik a legrégebbi kiadási év: ");
        System.out.println(Collections.min(kiadKonyv()));
        System.out.println("Melyik években hány könyvet adtak ki: "+kiadKonyvEvbenHany());
        System.out.println("Kik írtak könyvet: "+kikIrtak());
        System.out.println("Melyik szerző hány könyvet írt: "+szerzoHany());
    }

    private Map<String, Integer> szerzoHany() {
        Map<String, Integer> szerzoHany = new HashMap<>();
        konyvek.forEach(konyv -> {
            List<String> authors = konyv.getSzerzok();
            for (String author : authors) {
                author = author.trim();
                szerzoHany.put(author, szerzoHany.getOrDefault(author, 0) + 1);
            }
        });
        return szerzoHany;
    }



    private Set<String> kikIrtak() {
        Set<String> szerzok = new HashSet<>();
        konyvek.forEach(konyv -> {
            List<String> authors = konyv.getSzerzok();
            for (String author : authors) {
                szerzok.add(author.trim());
            }
        });
        return szerzok;
    }



    private void mapKiiras(Map <Integer,Integer> map) {
        /* generálva iter által*/
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v=entry.getValue();
            System.out.printf("[%d]=%d db\n", k,v);
        }
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