package projekt2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Uzytkownik extends Pracownik implements Serializable {
    private static final String nazwapliku = "Uzytkownicy.ser";
    private String login;
    private String haslo;
    private final String inicjal;
    private static final long serialVersionUID = 1L;
    private static HashMap<Integer,Uzytkownik> uzytkownicy = new HashMap<>();
    private static int nastepneid;

    static {
     try {
         zaaktualizujuzytkownikow(nazwapliku);
     } catch (Exception e) {
         nastepneid = 1;
     }
    }

    public Uzytkownik(String imie, String nazwisko, String dzialPracownikow,String login, String haslo) throws IOException, ClassNotFoundException {
        super(imie, nazwisko, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.inicjal =""+ imie.charAt(0) + nazwisko.charAt(0);


        uzytkownicy.put(super.getId(),this);
        System.out.println(this);
    }

    public String getInicjal(){
        return inicjal;
    }

    public static Uzytkownik dodajUzytkownika(String imie,String nazwisko,String dzialpracownikow, String login, String haslo) throws IOException, ClassNotFoundException {
        nastepneid = IDrecorder.pobierznextid(Uzytkownik.class.getName());
        Uzytkownik u = new Uzytkownik(imie,nazwisko,dzialpracownikow,login,haslo);
        IDrecorder.zapisznextid(u.getId() + 1, Uzytkownik.class.getName());
        IOPliku.dodajdopliku(nazwapliku,u.getId(),u);
        zaaktualizujuzytkownikow("Uzytkownicy.ser");
        return u;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String s){
        login = s;
    }

    public void usunuzytkownika(Integer i){
        uzytkownicy.remove(i);
    }

    public void setHaslo(String s){
        haslo = s;
    }

    public static ArrayList<Uzytkownik> zwrocuzytkownikow(){
        return new ArrayList<>(uzytkownicy.values());
    }

    public static void zaaktualizujuzytkownikow(String nazwapliku) throws IOException, ClassNotFoundException {
        try {
            nastepneid = IDrecorder.pobierznextid(Uzytkownik.class.getName());
            IOPliku<Uzytkownik> kontener = IOPliku.deserializuj(nazwapliku);
            if (kontener != null && kontener.getMap() != null){
                uzytkownicy.clear();
                uzytkownicy.putAll(kontener.getMap());
                if (!uzytkownicy.isEmpty()){
                    int maxid = uzytkownicy.keySet().stream().max(Integer::compare).orElse(0);
                    if (maxid >= nastepneid){
                        nastepneid = maxid + 1;
                        IDrecorder.zapisznextid(nastepneid,Uzytkownik.class.getName());
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Wystąpił błąd przy pobieraniu użytkowników");
            dodajUzytkownika("Admin","Główny","Budowlanka","admin","haslo");
        }
    }

    public String getHaslo(){
        return haslo;
    }

    public String getinicjal(){
        return inicjal;
    }
//    @Override
//    public String toString() {
//        return "Uzytkownik{"+this.getId()+"}";
//    }


    @Override
    public String toString() {
        return "Uzytkownik{" +
                "imie='" + super.getImie() + '\'' +
                "nazwisko='" + super.getNazwisko() + '\'' +
                "dzialPracownikow='" + super.getDzialPracownikow() + '\'' +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", inicjal='" + inicjal + '\'' +
                '}';
    }
}
