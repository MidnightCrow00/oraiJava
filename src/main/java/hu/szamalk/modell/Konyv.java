package hu.szamalk.modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Konyv implements Serializable {
    private String cim;
    private List<String> szerzok;
    private int kiadasEve;
    private int ar;
    /* transient: kéynes vagy foylamatosan változó adatot nem írunk ki */
    private transient UUID id;

    public Konyv(String sor){
        String[] adatok = sor.split(";");
        String cim = adatok[0];
        this.cim = cim;

        String szerzokSor = adatok[1];
        String[] szerzok = szerzokSor.split(",");
        this.szerzok = new ArrayList<>();
        for (String szerzo:szerzok){
            this.szerzok.add(szerzo);
        }

        int kiadasEve = Integer.parseInt(adatok[2]);
        int ar = Integer.parseInt(adatok[3]);
        this.kiadasEve = kiadasEve;
        this.ar = ar;
        ujIdGeneralas();
    }

    /* "Cím1";"Szerzo1";1987 */
    public Konyv(String cim, String szerzo, int kiadasEve, int ar){
        this(cim, new ArrayList<>(), kiadasEve, ar);
        szerzok.add(szerzo);
    }

    public Konyv(String cim, ArrayList<String> szerzok, int kiadasEve,int ar) {
        this.cim = cim;
        this.ar = ar;
        this.szerzok = szerzok;
        this.kiadasEve = kiadasEve;
        ujIdGeneralas();
    }

    public int getAr() {
        return ar;
    }

    public int getKiadasEve() {
        return kiadasEve;
    }

    /* paraméterként nem jó az UUID */
    public void ujIdGeneralas(){
        id = UUID.randomUUID();
    }
    /* getterek, esetleg setterek */

    @Override
    public String toString() {
        return "Konyv{" +
                "cim='" + cim + '\'' +
                ", szerzok=" + szerzok +
                ", kiadasEve=" + kiadasEve +
                ", id=" + id +
                '}';
    }
}
