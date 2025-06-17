package projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Lewypanel extends JPanel{
    private Srodkowypanel srodkowypanel;
    private static int szerokosc;
    public Lewypanel(Srodkowypanel srodkowypanel,Object zalogowany){
//        Dimension d = new Dimension(150,this.getPreferredSize().height);
//        setPreferredSize(d);
        this.srodkowypanel = srodkowypanel;
        this.setBackground(Color.DARK_GRAY);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,10,7,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel pomoc = new JLabel();
        pomoc.setPreferredSize(new Dimension(0,25));

        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        this.add(pomoc, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,0,0,0);

        Guzik Menu = new Guzik("Menu", e -> {
           cowyswietlic c = cowyswietlic.Menu;
           srodkowypanel.przekazdane(c);
        });
        Guzik DzialPracownikow = new Guzik("Dział Pracowników", e -> {
            cowyswietlic c = cowyswietlic.Dzialpracownikow;
            srodkowypanel.przekazdane(c);
        });
        Guzik Pracownik = new Guzik("Pracownik",e -> {
            cowyswietlic c = cowyswietlic.Pracownik;
            srodkowypanel.przekazdane(c);
        });
        Guzik Uzytkownik = new Guzik("Użytkownik", e -> {
            cowyswietlic c = cowyswietlic.Uzytkownik;
            srodkowypanel.przekazdane(c);
        });
        Guzik Brygadzista = new Guzik("Brygadzista", e -> {
            cowyswietlic c = cowyswietlic.Brygadzista;
            srodkowypanel.przekazdane(c);
        });
        Guzik Brygada = new Guzik("Brygada", e -> {
            cowyswietlic c = cowyswietlic.Brygada;
            srodkowypanel.przekazdane(c);
        });
        Guzik Zlecenie = new Guzik("Zlecenie", e -> {
            cowyswietlic c = cowyswietlic.Zlecenie;
            srodkowypanel.przekazdane(c);
        });
        Guzik Praca = new Guzik("Praca",e -> {
            cowyswietlic c = cowyswietlic.Praca;
            srodkowypanel.przekazdane(c);
        });
        Guzik Wyloguj = new Guzik("Wyloguj",e -> {
            cowyswietlic c = cowyswietlic.Wyloguj;
            srodkowypanel.przekazdane(c);
        });
        List<JButton> guziki = List.of(DzialPracownikow,Pracownik,Uzytkownik,Brygadzista,Brygada,Zlecenie,Praca,Wyloguj);
//        GridLayout layout = new GridLayout(guziki.size()+1,1,0,7);
//        this.setAlignmentX(SwingConstants.LEFT);
//        this.setLayout(layout);
        int grid = 1;
        if (zalogowany instanceof Brygadzista){
            gbc.gridx = 0;
            gbc.gridy = grid++;
            this.add(Menu,gbc);
        }
        for (JButton g : guziki){
            gbc.gridx = 0;
            gbc.gridy = grid++;
            this.add(g,gbc);
        }
        szerokosc = guziki.get(0).getPreferredSize().width + gbc.insets.left + gbc.insets.right;

//        JLabel bottomfiller = new JLabel();
//        gbc.gridx = 0;
//        gbc.gridy = grid;
//        gbc.weighty = 1.0;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.insets = new Insets(0,0,0,0);
//        this.add(bottomfiller,gbc);

        this.setPreferredSize(new Dimension(szerokosc,0));
    }
    public static int zwrocszerokosc(){
        return szerokosc;
    }


}
