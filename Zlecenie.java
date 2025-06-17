package projekt2;

import projekt1.Praca;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Zlecenie implements Serializable {
    private static final String nazwapliku = "Zlecenia.ser";
    private static final long serialVersionUID = 1L;
    private ArrayList<Praca> prace;
    private Brygada brygada;
    private enum stan {
        Planowane,
        Nieplanowane,
        Realizowane,
        Zakonczone
    }
    private transient stan stanzlecenia;
    private final LocalDate dataUtworzenia;
    private transient LocalDate dataRealizacji;
    private transient LocalDate dataZakonczenia;
    private final int id;
    private static int nastepneid;
    private static HashMap<Integer,Zlecenie> zlecenia = new HashMap<>();
    private Boolean czyplanowane;

    static {
        try {
            zaaktualizujzlecenia(nazwapliku);
        } catch (Exception e) {
            nastepneid = 1;
        }
    }

    public ArrayList<Praca> zwrocprace(){
        return prace;
    }

    public Zlecenie(Brygada brygada, Boolean czyplanowane) throws IOException, ClassNotFoundException {
        nastepneid = IDrecorder.pobierznextid(Zlecenie.class.getName());
        this.czyplanowane = czyplanowane;
        if (czyplanowane){
            stanzlecenia = stan.Planowane;
        }else{
            stanzlecenia = stan.Nieplanowane;
        }
        this.brygada = brygada;
        this.brygada.dodajzlecenie(this);
        prace = new ArrayList<>();
        dataUtworzenia = LocalDate.now();
        this.id = nastepneid;
        nastepneid++;
        IDrecorder.zapisznextid(nastepneid,Zlecenie.class.getName());
        System.out.println(this);
    }

    public static void setNextid(int i){
        nastepneid = i;
    }

    public static Integer getnextid(){
        return nastepneid;
    }

    public Boolean getCzyplanowane(){
        return czyplanowane;
    }

    public void setCzyplanowane(Boolean b){
        czyplanowane = b;
    }

    public void usunzlecenie(Integer i){
        zlecenia.remove(i);
    }

    public Integer getBrygada(){
        return brygada.getId();
    }

    public void setBrygada(Integer br){
        Brygada pomocnik = null;
        for (Brygada b : Brygada.zwrocBrygady()){
            if (b.getId().equals(br)){
                pomocnik = b;
                break;
            }
        }
        brygada = pomocnik;
    }

    public static void zaaktualizujzlecenia(String nazwapliku){
        try {
            nastepneid = IDrecorder.pobierznextid(Zlecenie.class.getName());
            IOPliku<Zlecenie> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                zlecenia.clear();
                zlecenia.putAll(kontener.getMap());
                if (!zlecenia.isEmpty()){
                    int maxid = zlecenia.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxid >= nastepneid){
                        nastepneid = maxid + 1;
                        IDrecorder.zapisznextid(nastepneid,Zlecenie.class.getName());
                    }
                }
            }else{
                nastepneid = 1;
                IDrecorder.zapisznextid(nastepneid,Zlecenie.class.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Wystąpił błąd przy pobieraniu Zleceń");
        }
    }

    public Integer getId(){
        return id;
    }

    public static Zlecenie dodajZlecenie(Integer brygada,Boolean czyplanowane) throws IOException, ClassNotFoundException {
        Zlecenie.zaaktualizujzlecenia(nazwapliku);
        Brygada pomocnik = null;
        for (Brygada b : Brygada.zwrocBrygady()){
            if (b.getId().equals(brygada)){
                pomocnik = b;
                break;
            }
        }
        if (pomocnik != null){
            Zlecenie z = new Zlecenie(pomocnik,czyplanowane);
            zlecenia.put(z.getId(),z);
            IOPliku.dodajdopliku(nazwapliku,z.getId(),z);
            zaaktualizujzlecenia(nazwapliku);
            return z;
        }
        return null;
    }

    public static ArrayList<Zlecenie> zwrocZlecenia(){
        ArrayList<Zlecenie> pomoc = new ArrayList<>();
        for (Zlecenie z : zlecenia.values()){
            pomoc.add(z);
        }

        return pomoc;
    }

    @Override
    public String toString() {
        return "Zlecenie{"+
                id +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zlecenie zlecenie = (Zlecenie) o;
        return id == zlecenie.id && Objects.equals(brygada, zlecenie.brygada) && stanzlecenia == zlecenie.stanzlecenia && Objects.equals(dataUtworzenia, zlecenie.dataUtworzenia) && Objects.equals(dataRealizacji, zlecenie.dataRealizacji) && Objects.equals(dataZakonczenia, zlecenie.dataZakonczenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brygada, stanzlecenia, dataUtworzenia, dataRealizacji, dataZakonczenia, id, nastepneid);
    }
}
