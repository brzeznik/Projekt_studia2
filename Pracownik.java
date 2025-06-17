package projekt2;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Pracownik implements Serializable {
    private static final String nazwapliku = "Pracownicy.ser";
    private static final long serialVersionUID = 1L;
    private String imie;
    private String nazwisko;
    private final LocalDate dataurodzenia;
    private String dzialPracownikow;
    private static HashMap<Integer,Pracownik> pracownicy = new HashMap<>();
    private final Integer id;
    private static int nastepneid; //to nie moze rownac sie jeden bo jak plik jeszcze raz sie odpali to id znowu bedzie 1 i nadpisze poprzednikow

    static {
        try {
            zaaktualizujpracownikow(nazwapliku);
        } catch (Exception e) {
            nastepneid = 1;
        }
    }

    public Pracownik(String imie,String nazwisko,String dzialPracownikow) throws IOException, ClassNotFoundException{
        nastepneid = IDrecorder.pobierznextid(Pracownik.class.getName());
        this.id = nastepneid;
        this.dataurodzenia = LocalDate.now();
        if (DzialPracownikow.getZajetenazwy().contains(dzialPracownikow)){
            this.dzialPracownikow=dzialPracownikow;
            DzialPracownikow d = DzialPracownikow.zwrocdzial(dzialPracownikow);
            if (d != null){
                d.dodajpracownika(dzialPracownikow,this);
            }
            this.imie = imie;
            this.nazwisko = nazwisko;
            nastepneid++;

            if (!(this instanceof Uzytkownik)){
                pracownicy.put(this.id,this);
            }

            IDrecorder.zapisznextid(nastepneid,Pracownik.class.getName());

        }else{
            //usunac pracownika jak dzial zly wpiszesz
            System.out.println("Nie ma takiego działu, pracownik nie zostanie stworzony");
        }
    }

    public String getImie(){
        return this.imie;
    }
    public String getNazwisko(){
        return this.nazwisko;
    }

    public String getDzialPracownikow(){
        return this.dzialPracownikow;
    }

    public void setDzialPracownikow(String s){
        if (DzialPracownikow.getZajetenazwy().contains(s)){
            dzialPracownikow = s;
        }
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void usunpracownika(Integer i){
        pracownicy.remove(i);
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public static int getNastepneid(){
        return nastepneid;
    }

    public static void setNasetpneid(int i){
        nastepneid = i;
    }


    public static void zaaktualizujpracownikow(String nazwapliku) throws IOException, ClassNotFoundException {
        try {
            nastepneid = IDrecorder.pobierznextid(Pracownik.class.getName());
            IOPliku<Pracownik> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                pracownicy.clear();
                pracownicy.putAll(kontener.getMap());
                if (!pracownicy.isEmpty()){
                    int maxId = pracownicy.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxId >= nastepneid){
                        nastepneid = maxId + 1;
                        IDrecorder.zapisznextid(nastepneid,Pracownik.class.getName());
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Wystąpił błąd przy pobieraniu Pracowników");
            nastepneid = 1;
        }
    }

    public static ArrayList<Pracownik> zwrocPracownikow(){
        ArrayList<Pracownik> p = new ArrayList<>();
        for (Pracownik k : pracownicy.values()){
            p.add(k);
        }
        return p;
    }

    public static Pracownik dodajPracownika(String imie,String nazwisko,String dzialPracownikow) throws IOException, ClassNotFoundException {
        Pracownik p = new Pracownik(imie,nazwisko,dzialPracownikow);
        IDrecorder.zapisznextid(nastepneid, String.valueOf(Pracownik.class));
        IOPliku.dodajdopliku(nazwapliku,p.id,p);
        zaaktualizujpracownikow(nazwapliku);
        return p;
    }

    public Integer getId(){
        return this.id;
    }


//    @Override
//    public String toString() {
//        return "Pracownik{" +
//                id +
//                '}';
//    }


    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataurodzenia=" + dataurodzenia +
                ", dzialPracownikow='" + dzialPracownikow + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik pracownik = (Pracownik) o;
        return id == pracownik.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, nazwisko, dataurodzenia, dzialPracownikow, id);
    }
}
