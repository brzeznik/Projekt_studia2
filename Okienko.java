package projekt2;

import javax.swing.*;
import java.awt.*;

public class Okienko extends JFrame {
    private static Uzytkownik zalogowanyuzytkownik;
    private static Brygadzista zalogowanybrygadzista;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                Okienko::new
        );
    }

    public static void setZalogowanyuzytkownik(Uzytkownik u){
        zalogowanybrygadzista = null;
        zalogowanyuzytkownik = u;
    }
    public static void setZalogowanybrygadzista(Brygadzista b){
        zalogowanybrygadzista = b;
        zalogowanyuzytkownik = null;
    }
    
    public static Uzytkownik getKtozalogowany(){
        if (zalogowanyuzytkownik == null){
            return zalogowanybrygadzista;
        } else if (zalogowanybrygadzista == null) {
            return zalogowanyuzytkownik;
        }
        return null;
    }

    public Okienko(){
        super("Projekt2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLayout(new BorderLayout());

        Pokazlogowanie();
    }
    public void Pokazlogowanie(){
        this.getContentPane().removeAll();
        Panellogowania panellogowania = new Panellogowania(this);
        this.getContentPane().add(panellogowania);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
    public void Pokazpanel(String inicjal){
        this.getContentPane().removeAll();
        System.out.println(getKtozalogowany());
        Srodkowypanel srodkowypanel = new Srodkowypanel(this,getKtozalogowany());
        Lewypanel lewypanel = new Lewypanel(srodkowypanel,getKtozalogowany());
        Gornypanel gornypanel = new Gornypanel(srodkowypanel,inicjal);

        this.getContentPane().add(lewypanel, BorderLayout.LINE_START);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(gornypanel,BorderLayout.PAGE_START);
        panel.add(srodkowypanel,BorderLayout.CENTER);
        this.getContentPane().add(panel,BorderLayout.CENTER);
        this.setVisible(true);
    }

    }

