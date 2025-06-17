package projekt2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DzialPracownikow implements Serializable {
    private final Integer ID;
    private static int nextid;
    private String nazwa;
    private static final long serialVersionUID = 1L;
    private static final String nazwapliku = "Dzialy.ser";
    private static ArrayList<String> zajetenazwy = new ArrayList<>();
    private static HashMap<Integer,DzialPracownikow> dzialypracownikow = new HashMap<>();
    private ArrayList<Pracownik> pracownicywdziale = new ArrayList<>();



    public DzialPracownikow(String nazwa){
        ID = nextid;
        dzialypracownikow.put(ID,this);
        if (zajetenazwy.contains(nazwa)){
            System.out.println("Nazwa zajęta!");
        }else{
            nextid++;
            zajetenazwy.add(nazwa);
            this.nazwa = nazwa;
        }
    }
    public static DzialPracownikow zwrocdzial(String s){
        for (DzialPracownikow d : dzialypracownikow.values()){
            if (Objects.equals(d.nazwa, s)){
                return d;
            }
        }
        return null;
    }

    public ArrayList<Pracownik> zwrocpracownikowdzialu(){
        if (pracownicywdziale == null){
            pracownicywdziale = new ArrayList<>();
        }
        return this.pracownicywdziale;
    }

    public void Usundzial(Integer i){
        dzialypracownikow.remove(i);
    }

    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String s){
        nazwa = s;
    }

    public static void wypiszdzialy(){
        for (DzialPracownikow d : dzialypracownikow.values()){
            System.out.println(d);
        }
    }

    public static int getNextid(){
        return nextid;
    }

    public static void setNextid(int i){
        nextid = i;
    }

    public static ArrayList<DzialPracownikow> zwrocdzialy(){
        return new ArrayList<>(dzialypracownikow.values());
    }
    public void dodajpracownika(String dzialpracownika,Pracownik p){
        if (pracownicywdziale == null){
            pracownicywdziale = new ArrayList<>();
        }
        this.pracownicywdziale.add(p);
        try {
            IOPliku.dodajdopliku(nazwapliku,this.ID,this);
        } catch (IOException | ClassNotFoundException e) {

        }

    }

    public void wypiszpracownikowzdzialu(String s){
        System.out.println("Pracownicy z dzialu: "+s);
        for (Pracownik p : pracownicywdziale){
            System.out.println(p);
        }

    }

    public static ArrayList<String> getZajetenazwy(){
        return zajetenazwy;
    }

    public static void zaaktualizujdzialy(String nazwapliku){
        try {
            IDrecorder<DzialPracownikow> zapiszid = IDrecorder.deserializuj();
            if (zapiszid != null && zapiszid.getMap() != null){
                Integer idzmapy = zapiszid.getMap().get(String.valueOf(DzialPracownikow.class));
                if (idzmapy == null){
                    setNextid(1);
                }else{
                    setNextid(idzmapy);
                }
            }
            IOPliku<DzialPracownikow> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                dzialypracownikow.clear();
                dzialypracownikow.putAll(kontener.getMap());

                zajetenazwy.clear();


                for (DzialPracownikow d : dzialypracownikow.values()){
                    zajetenazwy.add(d.nazwa);
                }
            }
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Wystąpił błąd przy pobieraniu plików");
        }
    }

    public Integer getID(){
        return ID;
    }

    public static DzialPracownikow dodajDzial(String nazwa) throws IOException, ClassNotFoundException {
        DzialPracownikow d = new DzialPracownikow(nazwa);
        IDrecorder.zapisznextid(nextid,String.valueOf(DzialPracownikow.class));
        IOPliku.dodajdopliku(nazwapliku,d.ID,d);
        return d;
    }

    @Override
    public String toString() {
        return "DzialPracownikow{" +
                 nazwa  +
                '}';
    }
}

