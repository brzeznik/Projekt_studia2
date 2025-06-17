package projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gornypanel extends JPanel {
    private Srodkowypanel srodkowypanel;
    private String inicjal;
    public Gornypanel(Srodkowypanel srodkowypanel, String inicjal){
        this.inicjal = inicjal;
        this.srodkowypanel = srodkowypanel;
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(0,25));

        Guzik Dodaj = new Guzik("Dodaj", e -> {
            cowyswietlic c = cowyswietlic.Dodaj;
            srodkowypanel.przekazdane(c);
        });
        Guzik Edycja = new Guzik("Edycja", e -> {
            cowyswietlic c = cowyswietlic.Edytuj;
            srodkowypanel.przekazdane(c);
        });
        Guzik Usun = new Guzik("Usuń", e -> {
            cowyswietlic c = cowyswietlic.Usun;
            srodkowypanel.przekazdane(c);
        });
        Guzik PracownicyDzialu = new Guzik("Dodaj", e -> {
            cowyswietlic c = cowyswietlic.Pracownicydzialu;
            srodkowypanel.przekazdane(c);
        });

        JLabel label = new JLabel("Witaj " + inicjal);
        label.setPreferredSize(new Dimension(Lewypanel.zwrocszerokosc(), label.getPreferredSize().height));
        List<JButton> guziki = List.of(Dodaj,Edycja,Usun,PracownicyDzialu);
        GridLayout layout = new GridLayout(1,guziki.size()+2);
        this.setLayout(layout);
        add(Dodaj);
        add(Edycja);
        add(Usun);
        add(label);

    }


}
