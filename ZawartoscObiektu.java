package projekt2;

import javax.swing.*;
import java.awt.*;

public class ZawartoscObiektu extends JDialog {
    private Object wybrany;
    private Srodkowypanel srodkowypanel;
    private int zlicz = 0;
    public ZawartoscObiektu(JFrame owner,Object wybrany,Srodkowypanel srodkowypanel){
        super(owner);
        this.srodkowypanel = srodkowypanel;
        this.wybrany = wybrany;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        if (wybrany instanceof DzialPracownikow d){
            setTitle("Pracownicy w dziale: "+d.getNazwa());
            JPanel lista = srodkowypanel.generujliste(d.zwrocpracownikowdzialu());
            for (Pracownik p : d.zwrocpracownikowdzialu()){
                zlicz+=1;
            }
            add(lista);
        }else if (wybrany instanceof Zlecenie z){
            setTitle("Prace w zleceniu: "+z.getId());
            JPanel lista = srodkowypanel.generujliste(z.zwrocprace());
            add(lista);

        }
        GridLayout layout = new GridLayout(zlicz,1,0,7);
        setLayout(layout);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }
}
