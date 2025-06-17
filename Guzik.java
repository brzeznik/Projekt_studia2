package projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Guzik extends JButton {
    public Guzik(String s, ActionListener actionListener){
        super(s);
        this.addActionListener(actionListener);
        this.setAlignmentX(SwingConstants.LEFT);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setPreferredSize(new Dimension(170,50));
        //zrobic cos z ramka guzika zeby byla okragla
    }
    public int zwrocszerokoscguzika(){
        return this.getPreferredSize().width;
    }
}
