package projekt2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Brygadzista extends Uzytkownik implements Serializable {
    private static final String nazwapliku = "Brygadzisci.ser";
    private static HashMap<Integer,Brygadzista> brygadzisci = new HashMap<>();
    private static int nastepneid;
    private static final long serialVersionUID = 1L;
    private transient ArrayList<Brygada> Brygadywktorychbyl = new ArrayList<>();
    private ArrayList<Zlecenie> wszystkiezlecenia = new ArrayList<>();


    static {
        try {
            zaaktualizujbrygadzistow(nazwapliku);
        } catch (Exception e) {
            nastepneid = 1;
        }
    }

    public Brygadzista(String imie, String nazwisko, String dzialPracownikow, String login, String haslo) throws IOException, ClassNotFoundException {
        super(imie, nazwisko, dzialPracownikow, login, haslo);
        brygadzisci.put(super.getId(),this);
    }
    public void dodajbrygade(Brygada b){
        if (Brygadywktorychbyl == null){
            Brygadywktorychbyl = new ArrayList<>();
        }
        Brygadywktorychbyl.add(b);
        if (!b.getZlecenia().isEmpty()){
            wszystkiezlecenia.addAll(b.getZlecenia());
        }
    }

    public void dodajzlecenie(Zlecenie z){
        wszystkiezlecenia.add(z);
    }

    public void usunbrygadziste(Integer i){
        brygadzisci.remove(i);
    }

    public ArrayList<Zlecenie> getWszystkiezlecenia(){
        return wszystkiezlecenia;
    }

    public void wypiszbrygady(){
        for (Brygada b : Brygadywktorychbyl){
            System.out.println(b);
        }
    }

    public String zwrocimie(){
        return getImie();
    }

    public static Brygadzista dodajBrygadziste(String imie,String nazwisko,String dzialpracownikow, String login, String haslo) throws IOException, ClassNotFoundException {
        nastepneid = IDrecorder.pobierznextid(Brygadzista.class.getName());
        Brygadzista b = new Brygadzista(imie,nazwisko,dzialpracownikow,login,haslo);
        IDrecorder.zapisznextid(b.getId(), Brygadzista.class.getName());
        IOPliku.dodajdopliku(nazwapliku,b.getId(),b);
        zaaktualizujbrygadzistow(nazwapliku);
        return b;
    }

    public static ArrayList<Brygadzista> zwrocbrygadzistow(){
        return new ArrayList<>(brygadzisci.values());
    }

    public static void zaaktualizujbrygadzistow(String nazwapliku){
        try {
            nastepneid = IDrecorder.pobierznextid(Brygadzista.class.getName());
            IOPliku<Brygadzista> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                brygadzisci.clear();
                brygadzisci.putAll(kontener.getMap());
                if (!brygadzisci.isEmpty()){
                    int maxid = brygadzisci.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxid >= nastepneid){
                        nastepneid = maxid + 1;
                        IDrecorder.zapisznextid(nastepneid,Brygadzista.class.getName());
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            nastepneid = 1;
            System.err.println("Wystąpił błąd przy pobieraniu brygadzistów");
        }
    }

    @Override
    public String toString() {
        return "Brygadzista" +getId()+ "{" +
                "imie='" + super.getImie() + '\'' +
                "nazwisko='" + super.getNazwisko() + '\'' +
                "dzialPracownikow='" + super.getDzialPracownikow() + '\'' +
                "login='" + super.getLogin() + '\'' +
                ", haslo='" + super.getHaslo() + '\'' +
                ", inicjal='" + super.getInicjal() + '\'' +
                '}';
    }
//    @Override
//    public String toString() {
//        return "Brygadzista{"+this.getId()+"}";
//    }
}
