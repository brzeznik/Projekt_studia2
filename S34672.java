package projekt2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class S34672{
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        Pracownik.wypiszpracownikow();
//        DzialPracownikow.wypiszpracownikowzdzialu("IT");
//        System.out.println(u1.getInicjal());
        DzialPracownikow.zaaktualizujdzialy("Dzialy.ser");
        Praca.zaaktualizujprace("Prace.ser");
        Zlecenie.zaaktualizujzlecenia("Zlecenia.ser");
        Brygada.zaaktualizujbrygady("Brygady.ser");
        Pracownik.zaaktualizujpracownikow("Pracownicy.ser");
        Uzytkownik.zaaktualizujuzytkownikow("Uzytkownicy.ser");
        Brygadzista.zaaktualizujbrygadzistow("Brygadzisci.ser");
        Okienko okienko = new Okienko();

        //ZALEZNIE OD GUZIKA KTORY MAMY(cowidac) W NIEKTORYCH PRZYPADKACH DODAC GUZIK, NP DODAJ PRACOWNIKA W ZLECENIU
        //usuwanie wielu wierszy na raz
        //jak zaloguje sie brygadzista case menu dac i w nim aktywne zlecenia brygadzisty
        //dla zlecenia mozliwosc zakonczenia zlecenia
        //mozliwosc zobaczenia pracownikow  w dziale
        //mozliwosc zobaczenia prac w zleceniu
        //kontrolka do wyboru daty, daty z czasem, i z dzialu pracownikow jak na zdj na teams
        //wlasna funkcja


}}
