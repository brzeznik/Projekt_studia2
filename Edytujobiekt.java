    package projekt2;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.IOException;

    public class Edytujobiekt extends JDialog {
        private Object object;
        private static int zlicz;
        private JComboBox<Praca.rodzajPracy> rodzajpracy;
        private JTextField imie;
        private JTextField nazwisko;
        private JTextField dzialpracownikow;
        private JTextField login;
        private JTextField haslo;
        private JTextField brygadzista;
        private JTextField nazwa;
        private JTextField brygada;
        private JTextField opis;
        private JTextField czaspracy;
        private JCheckBox czyplanowane;

        public Edytujobiekt(JFrame owner,String title,boolean modal,Object object){
            super(owner,title,modal);
            this.object = object;
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            if (object instanceof DzialPracownikow){
                DzialPracownikow d = (DzialPracownikow) object;
                nazwa = new JTextField(d.getNazwa());
                add(nazwa);
                zlicz = 1;
            } else if (object instanceof  Pracownik) {
                if (object instanceof Uzytkownik){
                    Uzytkownik u = (Uzytkownik) object;
                        if (u.equals(Okienko.getKtozalogowany())){
                            login = new JTextField(u.getLogin());
                            haslo = new JTextField(u.getHaslo());
                            add(login);
                            add(haslo);
                            zlicz = 2;
                        }else{
                            imie = new JTextField(u.getImie());
                            nazwisko = new JTextField(u.getNazwisko());
                            dzialpracownikow = new JTextField(u.getDzialPracownikow());
                            login = new JTextField(u.getLogin());
                            haslo = new JTextField(u.getHaslo());
                            add(imie);
                            add(nazwisko);
                            add(dzialpracownikow);
                            add(login);
                            add(haslo);
                            zlicz = 5;
                        }
                }else{
                    Pracownik p = (Pracownik) object;
                    imie = new JTextField(p.getImie());
                    nazwisko = new JTextField(p.getNazwisko());
                    dzialpracownikow = new JTextField(p.getDzialPracownikow());
                    add(imie);
                    add(nazwisko);
                    add(dzialpracownikow);
                    zlicz = 3;
                }
            } else if (object instanceof Brygada) {
                Brygada b = (Brygada) object;
                nazwa = new JTextField(b.getNazwa());
                brygadzista = new JTextField(String.valueOf(b.getbrygadzista()));
                add(nazwa);
                add(brygadzista);
                zlicz = 2;
            } else if (object instanceof Zlecenie) {
                Zlecenie z = (Zlecenie) object;
                brygada = new JTextField(String.valueOf(z.getBrygada()));
                czyplanowane = new JCheckBox("Czy planowane?",z.getCzyplanowane());
                add(brygada);
                add(czyplanowane);
                zlicz = 2;
            }else if(object instanceof Praca){
                Praca p = (Praca) object;
                rodzajpracy = new JComboBox<>(Praca.rodzajPracy.values());
                rodzajpracy.setSelectedItem(p.getRodzaj());
                add(rodzajpracy);
                czaspracy = new JTextField(String.valueOf(p.getCzasPracy()));
                opis = new JTextField(p.getOpis());
                add(czaspracy);
                add(opis);
                zlicz = 3;
            }

            JButton oK = new JButton("Zmień");
            oK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (object instanceof DzialPracownikow){
                    DzialPracownikow d = (DzialPracownikow) object;
                    d.setNazwa(nazwa.getText());
                    try {
                        IOPliku<DzialPracownikow> kontener = IOPliku.deserializuj("Dzialy.ser");
                        DzialPracownikow d1 = kontener.getMap().get(d.getID());
                        d1.setNazwa(nazwa.getText());
                        kontener.serializuj("Dzialy.ser");
                        DzialPracownikow.zaaktualizujdzialy("Dzialy.ser");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.err.println();
                    }
                } else if (object instanceof  Pracownik) {
                    if (object instanceof Uzytkownik){
                        if (object instanceof Brygadzista){
                            Brygadzista b = (Brygadzista) object;
                            b.setImie(imie.getText());
                            b.setNazwisko(nazwisko.getText());
                            b.setDzialPracownikow(dzialpracownikow.getText());
                            b.setLogin(login.getText());
                            b.setHaslo(haslo.getText());
                            try {
                                IOPliku<Brygadzista> kontener = IOPliku.deserializuj("Brygadzisci.ser");
                                Brygadzista b1 = kontener.getMap().get(b.getId());
                                b1.setImie(imie.getText());
                                b1.setNazwisko(nazwisko.getText());
                                b1.setDzialPracownikow(dzialpracownikow.getText());
                                b1.setLogin(login.getText());
                                b1.setHaslo(haslo.getText());
                                kontener.serializuj("Brygadzisci.ser");
                                Brygadzista.zaaktualizujbrygadzistow("Brygadzisci.ser");
                            } catch (IOException | ClassNotFoundException ex) {
                                System.err.println();
                            }
                        }else{
                            Uzytkownik u = (Uzytkownik) object;
                            u.setImie(imie.getText());
                            u.setNazwisko(nazwisko.getText());
                            u.setDzialPracownikow(dzialpracownikow.getText());
                            u.setLogin(login.getText());
                            u.setHaslo(haslo.getText());
                            try {
                                IOPliku<Uzytkownik> kontener = IOPliku.deserializuj("Uzytkownicy.ser");
                                Uzytkownik u1 = kontener.getMap().get(u.getId());
                                u1.setImie(imie.getText());
                                u1.setNazwisko(nazwisko.getText());
                                u1.setDzialPracownikow(dzialpracownikow.getText());
                                u1.setLogin(login.getText());
                                u1.setHaslo(haslo.getText());
                                kontener.serializuj("Uzytkownicy.ser");
                                Uzytkownik.zaaktualizujuzytkownikow("Uzytkownicy.ser");
                            } catch (IOException | ClassNotFoundException ex) {
                                System.err.println();
                            }
                        }
                    }else{
                        Pracownik p = (Pracownik) object;
                        p.setImie(imie.getText());
                        p.setNazwisko(nazwisko.getText());
                        p.setDzialPracownikow(dzialpracownikow.getText());
                        try {
                            IOPliku<Pracownik> kontener = IOPliku.deserializuj("Pracownicy.ser");
                            Pracownik p1 = kontener.getMap().get(p.getId());
                            p1.setImie(imie.getText());
                            p1.setNazwisko(nazwisko.getText());
                            p1.setDzialPracownikow(dzialpracownikow.getText());
                            kontener.serializuj("Pracownicy.ser");
                            Pracownik.zaaktualizujpracownikow("Pracownicy.ser");
                        } catch (IOException | ClassNotFoundException ex) {
                            System.err.println("Nie udało się wczytać Pracowników");
                        }
                    }
                } else if (object instanceof Brygada) {
                    Brygada b = (Brygada) object;
                    b.setNazwa(nazwa.getText());
                    b.setBrygadzista(Integer.valueOf(brygadzista.getText()));
                    try {
                        IOPliku<Brygada> kontener = IOPliku.deserializuj("Brygady.ser");
                        Brygada b1 = kontener.getMap().get(b.getId());
                        b1.setNazwa(nazwa.getText());
                        b1.setBrygadzista(Integer.valueOf(brygadzista.getText()));
                        kontener.serializuj("Brygady.ser");
                        Brygada.zaaktualizujbrygady("Brygady.ser");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.err.println();
                    }
                } else if (object instanceof Zlecenie) {
                    Zlecenie z = (Zlecenie) object;
                    z.setBrygada(Integer.valueOf(brygada.getText()));
                    z.setCzyplanowane(czyplanowane.isSelected());
                    try {
                        IOPliku<Zlecenie> kontener = IOPliku.deserializuj("Zlecenia.ser");
                        Zlecenie z1 = kontener.getMap().get(z.getId());
                        z1.setBrygada(Integer.valueOf(brygada.getText()));
                        z1.setCzyplanowane(czyplanowane.isSelected());
                        kontener.serializuj("Zlecenia.ser");
                        Zlecenie.zaaktualizujzlecenia("Zlecenia.ser");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.err.println();
                    }
                } else if (object instanceof Praca) {
                    Praca p = (Praca) object;
                    p.setCzaspracy(Integer.valueOf(czaspracy.getText()));
                    p.setOpis(opis.getText());
                    p.setRodzaj((Praca.rodzajPracy) rodzajpracy.getSelectedItem());
                    try {
                        IOPliku<Praca> kontener = IOPliku.deserializuj("Prace.ser");
                        Praca p1 = kontener.getMap().get(p.getNumerPracy());
                        p1.setCzaspracy(Integer.valueOf(czaspracy.getText()));
                        p1.setOpis(opis.getText());
                        p1.setRodzaj((Praca.rodzajPracy) rodzajpracy.getSelectedItem());
                        kontener.serializuj("Prace.ser");
                        Praca.zaaktualizujprace("Prace.ser");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.err.println();
                    }
                }
                dispose();
            }

        });
            GridLayout layout = new GridLayout(zlicz+1,1,0,7);
            setLayout(layout);
            add(oK);
            pack();
            setLocationRelativeTo(owner);
            setVisible(true);
        }


    }
