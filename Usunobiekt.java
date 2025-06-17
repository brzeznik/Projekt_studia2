package projekt2;

import javax.swing.*;
import java.io.IOException;

public class Usunobiekt{
    private Object object;
    private Srodkowypanel srodkowypanel;

    public Usunobiekt(Object object,Srodkowypanel srodkowypanel){
        this.srodkowypanel = srodkowypanel;
        this.object = object;

        if (object instanceof DzialPracownikow){
            DzialPracownikow d = (DzialPracownikow) object;
            d.Usundzial(d.getID());
            try {
                IOPliku<DzialPracownikow> kontener = IOPliku.deserializuj("Dzialy.ser");
                kontener.getMap().remove(d.getID());
                kontener.serializuj("Dzialy.ser");
                DzialPracownikow.zaaktualizujdzialy("Dzialy.ser");
            } catch (ClassNotFoundException | IOException e) {
                System.err.println();
            }
        } else if (object instanceof Pracownik){
            if (object instanceof Uzytkownik){
                if (object instanceof Brygadzista){
                    Brygadzista b = (Brygadzista) object;
                    b.usunbrygadziste(b.getId());
                    try {
                        IOPliku<Pracownik> kontener = IOPliku.deserializuj("Brygadzisci.ser");
                        kontener.getMap().remove(b.getId());
                        kontener.serializuj("Brygadzisci.ser");
                        Brygadzista.zaaktualizujbrygadzistow("Brygadzisci.ser");
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println();
                    }
                }else{
                    Uzytkownik u = (Uzytkownik) object;
                    u.usunuzytkownika(u.getId());
                    try {
                        IOPliku<Uzytkownik> kontener = IOPliku.deserializuj("Uzytkownicy.ser");
                        kontener.getMap().remove(u.getId());
                        kontener.serializuj("Uzytkownicy.ser");
                        Uzytkownik.zaaktualizujuzytkownikow("Uzytkownicy.ser");
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println();
                    }
                }
            }else{
                Pracownik p = (Pracownik) object;
                p.usunpracownika(p.getId());
                try {
                    IOPliku<Pracownik> kontener = IOPliku.deserializuj("Pracownicy.ser");
                    kontener.getMap().remove(p.getId());
                    kontener.serializuj("Pracownicy.ser");
                    Pracownik.zaaktualizujpracownikow("Pracownicy.ser");
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println();
                }
            }
        } else if (object instanceof Brygada) {
            Brygada b = (Brygada) object;
            b.usunbrygade(b.getId());
            try {
                IOPliku<Brygada> kontener = IOPliku.deserializuj("Brygady.ser");
                kontener.getMap().remove(b.getId());
                kontener.serializuj("Brygady.ser");
                Brygada.zaaktualizujbrygady("Brygady.ser");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println();
            }
        } else if (object instanceof  Zlecenie) {
            Zlecenie z = (Zlecenie) object;
            z.usunzlecenie(z.getId());
            try {
                IOPliku<Zlecenie> kontener = IOPliku.deserializuj("Zlecenia.ser");
                kontener.getMap().remove(z.getId());
                kontener.serializuj("Zlecenia.ser");
                Zlecenie.zaaktualizujzlecenia("Zlecenia.ser");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println();
            }
        } else if (object instanceof  Praca) {
            Praca p = (Praca) object;
            p.usunprace(p.getNumerPracy());
            try {
                IOPliku<Praca> kontener = IOPliku.deserializuj("Prace.ser");
                kontener.getMap().remove(p.getNumerPracy());
                kontener.serializuj("Prace.ser");
                Praca.zaaktualizujprace("Prace.ser");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println();
            }
        }
        if (srodkowypanel != null){
            srodkowypanel.przekazdane(srodkowypanel.odswiezliste());
        }
    }

}
