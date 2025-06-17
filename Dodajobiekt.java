package projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class Dodajobiekt<T> extends JDialog {
    private Class<T> danedoobiektu;
    private int ID;
    private static int nextid = 1;
    private Srodkowypanel srodkowypanel;
    private Boolean czyplanowane = false;
    private JComboBox<Praca.rodzajPracy> rodzajpracy;


    public Dodajobiekt(JFrame owner, String title, boolean modal, ArrayList<T> lista, Class<T> klasa, Srodkowypanel srodkowypanel){
        super(owner,title,modal);
        this.srodkowypanel = srodkowypanel;
        int zlicz = 0;
        this.danedoobiektu = klasa;
        Field[] listapolobiektu = danedoobiektu.getDeclaredFields();
        HashMap<String,JTextField> pola = new HashMap<>();
        this.ID = nextid;
        nextid++;

        if (danedoobiektu == DzialPracownikow.class){
            JTextField pole1 = new JTextField("nazwa");
            add(pole1);
            pola.put("nazwa",pole1);
            pole1.setHorizontalAlignment(JTextField.CENTER);
            zlicz += 1;
        }
        if (danedoobiektu == Pracownik.class){
            JTextField pole1 = new JTextField("Imie");
            JTextField pole2 = new JTextField("Nazwisko");
            JTextField pole3 = new JTextField("Dział pracowników");
            add(pole1);
            add(pole2);
            add(pole3);
            pola.put("imie",pole1);
            pola.put("nazwisko",pole2);
            pola.put("dzialPracownikow",pole3);
            for (JTextField p : pola.values()){p.setHorizontalAlignment(JTextField.CENTER);}
            zlicz += 3;
        }
        if (danedoobiektu == Uzytkownik.class) {
            JTextField pole1 = new JTextField("Imie");
            JTextField pole2 = new JTextField("Nazwisko");
            JTextField pole3 = new JTextField("Dział pracowników");
            JTextField pole4 = new JTextField("Login");
            JTextField pole5 = new JTextField("Hasło");
            add(pole1);
            add(pole2);
            add(pole3);
            add(pole4);
            add(pole5);
            pola.put("imie",pole1);
            pola.put("nazwisko",pole2);
            pola.put("dzialPracownikow",pole3);
            pola.put("login",pole4);
            pola.put("haslo",pole5);
            for (JTextField p : pola.values()){p.setHorizontalAlignment(JTextField.CENTER);}
            zlicz += 5;
        } else if (danedoobiektu == Brygadzista.class) {
            JTextField pole1 = new JTextField("Imie");
            JTextField pole2 = new JTextField("Nazwisko");
            JTextField pole3 = new JTextField("Dział pracowników");
            JTextField pole4 = new JTextField("Login");
            JTextField pole5 = new JTextField("Hasło");
            add(pole1);
            add(pole2);
            add(pole3);
            add(pole4);
            add(pole5);
            pola.put("imie",pole1);
            pola.put("nazwisko",pole2);
            pola.put("dzialPracownikow",pole3);
            pola.put("login",pole4);
            pola.put("haslo",pole5);
            for (JTextField p : pola.values()){p.setHorizontalAlignment(JTextField.CENTER);}
            zlicz += 5;
        }else if (danedoobiektu == Brygada.class){
            JTextField pole1 = new JTextField("Nazwa");
            JTextField pole2 = new JTextField("ID brygadzisty");
            add(pole1);
            add(pole2);
            pola.put("nazwa",pole1);
            pola.put("brygadzista",pole2);
            for (JTextField p : pola.values()){p.setHorizontalAlignment(JTextField.CENTER);}
            zlicz += 2;
        } else if (danedoobiektu == Zlecenie.class) {
            JTextField pole1 = new JTextField("ID brygady");
            JCheckBox pole2 = new JCheckBox("Czy planowane?");
            add(pole1);
            add(pole2);
            czyplanowane = pole2.isSelected();
            pola.put("brygada",pole1);
            pole1.setHorizontalAlignment(JTextField.CENTER);
            zlicz += 2;
        } else if (danedoobiektu == Praca.class) {
            rodzajpracy = new JComboBox<>(Praca.rodzajPracy.values());
            rodzajpracy.setSelectedItem(Praca.rodzajPracy.Ogolna);
            add(rodzajpracy);
            JTextField pole2 = new JTextField("Czas pracy");
            JTextField pole3 = new JTextField("Opis");
            add(pole2);
            add(pole3);
            pola.put("czaspracy",pole2);
            pola.put("opis",pole3);
            for (JTextField p : pola.values()){p.setHorizontalAlignment(JTextField.CENTER);}
            zlicz += 3;
        }


        GridLayout layout = new GridLayout(zlicz+1,1,0,7);
        setLayout(layout);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JButton oK = new JButton("Dodaj");

        oK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (danedoobiektu.getSimpleName()){
                        case "DzialPracownikow" -> {
                            DzialPracownikow.dodajDzial(pola.get("nazwa").getText() );
                        }
                        case "Pracownik" -> {
                            Pracownik.dodajPracownika(pola.get("imie").getText(),pola.get("nazwisko").getText(),pola.get("dzialPracownikow").getText());
                        }
                        case "Uzytkownik" -> {
                            Uzytkownik.dodajUzytkownika(pola.get("imie").getText(),pola.get("nazwisko").getText(),pola.get("dzialPracownikow").getText(),pola.get("login").getText(),pola.get("haslo").getText());
                        }
                        case "Brygadzista" -> {
                            Brygadzista.dodajBrygadziste(pola.get("imie").getText(),pola.get("nazwisko").getText(),pola.get("dzialPracownikow").getText(),pola.get("login").getText(),pola.get("haslo").getText());
                        }
                        case "Brygada" -> {

                            Integer brygadzista;
                            try {
                                brygadzista = Integer.valueOf(pola.get("brygadzista").getText());
                                if (brygadzista < 1){
                                    JOptionPane.showMessageDialog(Dodajobiekt.this,"Wprowadź poprawne ID!");
                                    return;
                                }
                            }catch (NumberFormatException ex){
                                JOptionPane.showMessageDialog(Dodajobiekt.this,"Wprowadź poprawne ID!");
                                return;
                            }
                            Brygada.dodajBrygade(pola.get("nazwa").getText(),brygadzista);
                        }
                        case "Zlecenie" -> {
                            Integer brygada;
                            try {
                                brygada = Integer.valueOf(pola.get("brygada").getText());
                                if (brygada < 1){
                                    JOptionPane.showMessageDialog(Dodajobiekt.this,"Wprowadź poprawne ID!");
                                    return;
                                }
                            }catch (NumberFormatException exc){
                                JOptionPane.showMessageDialog(Dodajobiekt.this,"Wprowadź poprawne ID!");
                                return;
                            }
                            Zlecenie.dodajZlecenie(brygada,czyplanowane);
                        }
                        case "Praca" -> {
                            Praca.rodzajPracy rodzaj = (Praca.rodzajPracy) rodzajpracy.getSelectedItem();
                            Integer i = Integer.parseInt(pola.get("czaspracy").getText());
                            Praca.dodajPrace(rodzaj,i,pola.get("opis").getText());

                        }
                    }
                    if (srodkowypanel != null){
                        srodkowypanel.przekazdane(srodkowypanel.odswiezliste());
                    }
                }catch (Exception w){
                    w.printStackTrace();
                }


                dispose();
//                owner.repaint();
//                owner.revalidate();
            }

        });
        add(oK);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }
}
