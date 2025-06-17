package projekt2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Srodkowypanel extends JPanel implements Przekazdane,ListCellRenderer{
    private String cowidac = "";
    private generujliste<?> aktualnalista;
    private cowyswietlic cowyswietlic = projekt2.cowyswietlic.Dzialpracownikow;
    private Okienko okienko;
    private static int index = 0;
    private int szerokosc = 150;
    private Srodkowypanel srodkowypanel;
    private Object zalogowany;
    public Srodkowypanel(Okienko okienko,Object zalogowany){
        this.setBackground(Color.DARK_GRAY);
        this.okienko = okienko;
        srodkowypanel = this;
        this.setVisible(true);
        this.zalogowany = zalogowany;
        if (zalogowany instanceof Brygadzista){
            cowyswietlic = projekt2.cowyswietlic.Menu;
            wyswietl(cowyswietlic);
        }else{
            wyswietl(cowyswietlic);
        }

    }
    public cowyswietlic odswiezliste(){
        switch (cowidac){
            case "Menu" -> {return projekt2.cowyswietlic.Menu;}
            case "class projekt2.DzialPracownikow" -> {return projekt2.cowyswietlic.Dzialpracownikow;}
            case "class projekt2.Pracownik" -> {return projekt2.cowyswietlic.Pracownik;}
            case "class projekt2.Uzytkownik" -> {return projekt2.cowyswietlic.Uzytkownik;}
            case "class projekt2.Brygadzista" -> {return projekt2.cowyswietlic.Brygadzista;}
            case "class projekt2.Brygada" -> {return  projekt2.cowyswietlic.Brygada;}
            case "class projekt2.Zlecenie" -> {return projekt2.cowyswietlic.Zlecenie;}
            case "class projekt2.Praca" -> {return projekt2.cowyswietlic.Praca;}
        }
        return null;
    }

    @Override
    public void przekazdane(cowyswietlic c) {
        wyswietl(c);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = new JLabel(value.toString());
        renderer.setBorder(new EmptyBorder(5,10,5,10));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setPreferredSize(new Dimension(szerokosc,40));
        renderer.setMaximumSize(new Dimension(szerokosc,40));
        if(isSelected){
            renderer.setBackground(Color.LIGHT_GRAY);
            renderer.setForeground(Color.WHITE);
        }else {
            renderer.setBackground(Color.DARK_GRAY);
            renderer.setForeground(Color.WHITE);
        }
        renderer.setEnabled(list.isEnabled());
        renderer.setFont(list.getFont());
        renderer.setOpaque(true);
        return renderer;
    }

    public class generujliste<T>{
        private JList<T> jlista;
        private JPanel glownypanel;
        private JLabel brakobiektow = new JLabel("Brak obiektów w Hashmapie, Użyj przycisku Dodaj :)");
        private JPanel blad = new JPanel();
        public JPanel stworz(List<T> lista) {

            DefaultListModel<T> listModel = new DefaultListModel<>();
            for (T t : lista){
                listModel.addElement(t);
            }
            if (lista.isEmpty()){
                blad = new JPanel();
                blad.setBackground(Color.DARK_GRAY);
                brakobiektow.setForeground(Color.WHITE);
                blad.add(brakobiektow);
                return blad;
            }else{
                glownypanel = new JPanel(new BorderLayout());
                jlista = new JList<>(listModel);
                jlista.setCellRenderer(Srodkowypanel.this);
                JScrollPane scrollPane = new JScrollPane(jlista);
                jlista.setBackground(Color.DARK_GRAY);
                scrollPane.setBackground(Color.DARK_GRAY);
//                scrollPane.setPreferredSize(new Dimension(szerokosc,40));
//                scrollPane.setMaximumSize(new Dimension(szerokosc,40));
                scrollPane.setVisible(true);
                cowidac = String.valueOf(listModel.get(0).getClass());
                glownypanel.add(scrollPane, BorderLayout.CENTER);
//                jlista.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        if (e.getClickCount() == 2){
//                            if (cowybrane() instanceof DzialPracownikow){
//                                ZawartoscObiektu zawartosc = new ZawartoscObiektu(okienko,cowybrane(),srodkowypanel);
//                            }else if (cowybrane() instanceof Zlecenie){
//
//                            }
//                        }
//                    }
//                });
                return glownypanel;
            }
        }
//        public JList<T> getJlista(){
//            return jlista;
//        }

        public int getindex(){
            return index;
        }

        public T cowybrane(){
            return jlista.getSelectedValue();
        }

    }

    public Object pobierzwybranaliste(){
        if (aktualnalista != null){
            return aktualnalista;
        }
        return null;
    }

    public Object pobierzwybranyelement(){
        if (aktualnalista != null){
            Object wybrany = aktualnalista.cowybrane();
            if (wybrany != null){
//                if (wybrany instanceof DzialPracownikow){
//
//                }
//                if (wybrany instanceof Pracownik){
//
//                }
//                if (wybrany instanceof Uzytkownik){
//
//                }
//                if (wybrany instanceof Brygadzista){
//
//                }
//                if (wybrany instanceof Brygada){
//
//                }
//                if (wybrany instanceof Zlecenie){
//
//                }
//                if (wybrany instanceof Pracownik){
//
//                }
            }
            return wybrany;
        }
        return null;
    }


    public JPanel generujliste(List lista){
        return new generujliste().stworz(lista);
    }


    private void wyswietl(cowyswietlic c) {
        setLayout(new BorderLayout());
        this.cowyswietlic = c;
        switch (c){
            case Menu -> {
                this.removeAll();
                generujliste<Zlecenie> zlecenia = new generujliste<>();
                Brygadzista b = (Brygadzista) zalogowany;
                ArrayList<Zlecenie> z = b.getWszystkiezlecenia();
                if (z == null){
                    JLabel label = new JLabel("Nie masz żadnych zleceń!");
                    label.setForeground(Color.WHITE);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    add(label,BorderLayout.NORTH);
                }else{
                    add(zlecenia.stworz(z));
                    setAlignmentX(SwingConstants.CENTER);
                    aktualnalista = zlecenia;
                }
                cowidac = "Menu";
            }
            case Dzialpracownikow -> {
                this.removeAll();
                generujliste<DzialPracownikow> dzialpracownikow = new generujliste<>();
                add(dzialpracownikow.stworz(DzialPracownikow.zwrocdzialy()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = dzialpracownikow;
                cowidac = String.valueOf(DzialPracownikow.class);
            }
            case Pracownik -> {
                this.removeAll();
                generujliste<Pracownik> pracownicy = new generujliste<>();
                add(pracownicy.stworz(Pracownik.zwrocPracownikow()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = pracownicy;
                cowidac = String.valueOf(Pracownik.class);
            }
            case Uzytkownik -> {
                this.removeAll();
                generujliste<Uzytkownik> uzytkownicy = new generujliste<>();
                add(uzytkownicy.stworz(Uzytkownik.zwrocuzytkownikow()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = uzytkownicy;
                cowidac = String.valueOf(Uzytkownik.class);
            }
            case Brygadzista -> {
                this.removeAll();
                generujliste<Brygadzista> brygadzisci = new generujliste<>();
                add(brygadzisci.stworz(Brygadzista.zwrocbrygadzistow()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = brygadzisci;
                cowidac = String.valueOf(Brygadzista.class);
            }
            case Brygada -> {
                this.removeAll();
                generujliste<Brygada> brygady = new generujliste<>();
                add(brygady.stworz(Brygada.zwrocBrygady()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = brygady;
                cowidac = String.valueOf(Brygada.class);
            }
            case Zlecenie -> {
                this.removeAll();
                generujliste<Zlecenie> zlecenia = new generujliste<>();
                add(zlecenia.stworz(Zlecenie.zwrocZlecenia()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = zlecenia;
                cowidac = String.valueOf(Zlecenie.class);
            }
            case Praca -> {
                this.removeAll();
                generujliste<Praca> prace = new generujliste<>();
                add(prace.stworz(Praca.zwrocPrace()),BorderLayout.CENTER);
                setAlignmentX(SwingConstants.CENTER);
                aktualnalista = prace;
                cowidac = String.valueOf(Praca.class);
                
            }
            case Wyloguj -> {
                okienko.Pokazlogowanie();
            }
            case Dodaj -> {
                switch (cowidac){
                    case "class projekt2.DzialPracownikow" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Dział",true,DzialPracownikow.zwrocdzialy(),DzialPracownikow.class,this);
                    }
                    case "class projekt2.Pracownik" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Pracownika",true,Pracownik.zwrocPracownikow(),Pracownik.class,this);
                    }
                    case "class projekt2.Uzytkownik" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Użytkownika",true,Uzytkownik.zwrocuzytkownikow(),Uzytkownik.class,this);
                    }
                    case "class projekt2.Brygadzista" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Brygadzistę",true,Brygadzista.zwrocbrygadzistow(),Brygadzista.class,this);
                    }
                    case "class projekt2.Brygada" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Brygadę",true,Brygada.zwrocBrygady(),Brygada.class,this);
                    }
                    case "class projekt2.Zlecenie" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Zlecenie",true,Zlecenie.zwrocZlecenia(),Zlecenie.class,this);
                    }
                    case "class projekt2.Praca" -> {
                        new Dodajobiekt<>(okienko,"Dodaj Pracę",true,Praca.zwrocPrace(),Praca.class,this);
                    }
                }
            }
            case Edytuj -> {
                new Edytujobiekt(okienko,"Edytuj",true,pobierzwybranyelement());
            }
            case Usun -> {
                new Usunobiekt(pobierzwybranyelement(),this);
            }
            case Pracownicydzialu -> {}
        }
        this.revalidate();
        this.repaint();
    }

}
