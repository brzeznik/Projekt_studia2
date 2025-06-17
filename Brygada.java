package projekt2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Brygada implements Serializable {
    private String nazwa;
    private Brygadzista brygadzista;
    private transient ArrayList<Pracownik> pracownicy;
    private ArrayList<Zlecenie> zlecenia;
    private final int id;
    private static int nastepneid;
    private static HashMap<Integer,Brygada> brygady = new HashMap<>();
    private static final String nazwapliku = "Brygady.ser";
    private static final long serialVersionUID = 1L;

    static {
        try {
            zaaktualizujbrygady(nazwapliku);
        } catch (Exception e) {
            nastepneid = 1;
        }
    }

    public Brygada(String nazwa, Brygadzista brygadzista) throws IOException, ClassNotFoundException {
        zlecenia = new ArrayList<>();
        nastepneid = IDrecorder.pobierznextid(Brygada.class.getName());
        this.pracownicy = new ArrayList<>();
        this.nazwa = nazwa;
        this.brygadzista = brygadzista;
        if (this.brygadzista != null){this.brygadzista.dodajbrygade(this);}
        this.id = nastepneid;
        nastepneid++;
        IDrecorder.zapisznextid(nastepneid,Brygada.class.getName());
        System.out.println(this);
    }

    public void dodajzlecenie(Zlecenie z){
        if (brygadzista != null){
            brygadzista.dodajzlecenie(z);
        }
        zlecenia.add(z);
    }
    public ArrayList<Zlecenie> getZlecenia(){
        return zlecenia;
    }

    public static Brygada dodajBrygade(String nazwa, Integer brygadzista) throws IOException, ClassNotFoundException {
        Brygadzista.zaaktualizujbrygadzistow("Brygadzisci.ser");
        Brygadzista pomocnik = null;
        for (Brygadzista b : Brygadzista.zwrocbrygadzistow()){
            if (b.getId().equals(brygadzista)){
                pomocnik = b;
                break;
            }
        }
        if (pomocnik != null){
            Brygada b = new Brygada(nazwa,pomocnik);
            brygady.put(b.getId(), b);
            IOPliku.dodajdopliku(nazwapliku, b.getId(),b);
            zaaktualizujbrygady(nazwapliku);
            return b;
        }
        return null;
    }

    public Integer getId(){
        return id;
    }

    public void dodajpracownika(Pracownik p){
        if (p instanceof Uzytkownik){
            System.out.println("Nie można dodawać użytkowników");
        }else {
            if (this.pracownicy == null){this.pracownicy = new ArrayList<>();}
            pracownicy.add(p);
        }
    }

    public static void zaaktualizujbrygady(String nazwapliku){
        try {
            nastepneid = IDrecorder.pobierznextid(Brygada.class.getName());
            IOPliku<Brygada> kontener = IOPliku.deserializuj(nazwapliku);

            if (kontener != null && kontener.getMap() != null){
                brygady.clear();
                brygady.putAll(kontener.getMap());

                if (!brygady.isEmpty()){
                    int maxid = brygady.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxid >= nastepneid){
                        nastepneid = maxid + 1;
                        IDrecorder.zapisznextid(nastepneid,Brygada.class.getName());
                    }
                }
            }else {
                nastepneid = 1;
                IDrecorder.zapisznextid(nastepneid,Brygada.class.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            nastepneid = 1;
            System.err.println("Wystąpił błąd przy pobieraniu pliku");
        }
    }
    public Integer getbrygadzista(){
        return brygadzista.getId();
    }

    public void usunbrygade(Integer i){
        brygady.remove(i);
    }

    public void setBrygadzista(Integer i) {
        Brygadzista pomocnik = null;
        for (Brygadzista b : Brygadzista.zwrocbrygadzistow()){
            if (b.getId().equals(i)){
                pomocnik = b;
                break;
            }
        }
        brygadzista = pomocnik;
    }

    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public static ArrayList<Brygada> zwrocBrygady(){
        return new ArrayList<>(brygady.values());
    }


    @Override
    public String toString() {
        return "Brygada{"+id+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brygada brygada = (Brygada) o;
        return id == brygada.id && Objects.equals(nazwa, brygada.nazwa) && Objects.equals(brygadzista, brygada.brygadzista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, brygadzista, id);
    }

}
