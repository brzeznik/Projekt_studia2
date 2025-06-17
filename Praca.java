package projekt2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Praca implements Serializable {
    private static final String nazwapliku = "Prace.ser";
    private static final long serialVersionUID = 1L;
    private final int numerPracy;
    private static int nastepneid;
    enum rodzajPracy{
        Ogolna,
        Montaz,
        Demontaz,
        Wymiana
    }
    private rodzajPracy rodzaj;
    private int czaspracy;
    private transient Boolean czyzrealizowane = false;
    private String opis;
    private static HashMap<Integer,Praca> prace = new HashMap<>();

    static {
        try {
            zaaktualizujprace(nazwapliku);
        }catch (Exception e){
            nastepneid = 1;
        }
    }

    public Praca(rodzajPracy rodzaj, int czaspracy, String opis) throws IOException, ClassNotFoundException {
        nastepneid = IDrecorder.pobierznextid(Praca.class.getName());
        this.rodzaj = rodzaj;
        this.czaspracy = czaspracy * 60;
        this.opis = opis;
        this.numerPracy = nastepneid;
        nastepneid++;
        IDrecorder.zapisznextid(nastepneid,Praca.class.getName());
        prace.put(numerPracy,this);

    }

    public static Praca dodajPrace(rodzajPracy rodzaj,Integer czaspracy, String opis) throws IOException, ClassNotFoundException {
        Praca.zaaktualizujprace(nazwapliku);
        Praca p = new Praca(rodzaj,czaspracy,opis);
        IOPliku.dodajdopliku(nazwapliku,p.numerPracy,p);
        IDrecorder.zapisznextid(nastepneid,Praca.class.getName());
        return p;
    }

    public static ArrayList<Praca> zwrocPrace(){
        ArrayList<Praca> pomoc = new ArrayList<>();
        for (Praca p : prace.values()){
            pomoc.add(p);
        }
        return pomoc;
    }
    public int getNumerPracy(){
        return numerPracy;
    }

    public Integer getCzasPracy(){
        return czaspracy;
    }

    public rodzajPracy getRodzaj(){
        return rodzaj;
    }

    public void usunprace(Integer i){
        prace.remove(i);
    }

    public void setRodzaj(rodzajPracy r){
        rodzaj = r;
    }

    public String getOpis(){
        return opis;
    }

    public void setOpis(String s){
        opis = s;
    }

    public void setCzaspracy(Integer i){
        czaspracy = i;
    }

    public static void zaaktualizujprace(String nazwapliku){
        try {
            nastepneid = IDrecorder.pobierznextid(Praca.class.getName());
            IOPliku<Praca> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                prace.clear();
                prace.putAll(kontener.getMap());
                if (!prace.isEmpty()){
                    int maxid = prace.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxid >= nastepneid){
                        nastepneid = maxid + 1;
                        IDrecorder.zapisznextid(nastepneid,Praca.class.getName());
                    }
                }
            }else{
                nastepneid = 1;
                IDrecorder.zapisznextid(nastepneid,Praca.class.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Wystąpił błąd przy pobieraniu Prac");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Praca praca = (Praca) o;
        return numerPracy == praca.numerPracy && czaspracy == praca.czaspracy && Objects.equals(nazwapliku, praca.nazwapliku) && rodzaj == praca.rodzaj && Objects.equals(czyzrealizowane, praca.czyzrealizowane) && Objects.equals(opis, praca.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwapliku, numerPracy, rodzaj, czaspracy, czyzrealizowane, opis);
    }

    @Override
    public String toString() {
        return "Praca{" +
                "rodzaj=" + rodzaj +
                ", czaspracy=" + czaspracy +
                ", opis='" + opis + '\'' +
                '}';
    }


    //    @Override
//    public String toString() {
//        return "Praca{" +
//                numerPracy +
//                '}';
//    }
}
