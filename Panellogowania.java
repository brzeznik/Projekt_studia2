package projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Panellogowania extends JPanel{
    private static JTextField login;
    private static JTextField haslo;
    private ArrayList<Uzytkownik> uzytkownicy;
    private ArrayList<Brygadzista> brygadzisci;
    private Okienko okienko;

    public Panellogowania(Okienko okienko){
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        this.okienko = okienko;
        GridBagConstraints gbc = new GridBagConstraints();
        login = new JTextField("Login");
        haslo = new JTextField("Hasło");
        brygadzisci = Brygadzista.zwrocbrygadzistow();
        uzytkownicy = Uzytkownik.zwrocuzytkownikow();
        login.setPreferredSize(new Dimension(200,50));
        haslo.setPreferredSize(new Dimension(200,50));
        JButton ok = new JButton("Zaloguj");
        ok.setPreferredSize(new Dimension(200,50));

        gbc.insets = new Insets(10,0,10,0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(login,gbc);

        gbc.gridy = 1;
        this.add(haslo,gbc);

        gbc.gridy = 2;
        this.add(ok,gbc);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String l = login.getText();
                String h = haslo.getText();

                for (Uzytkownik u : uzytkownicy){
                    if (Objects.equals(u.getLogin(), l) && Objects.equals(u.getHaslo(), h)){
                        Okienko.setZalogowanyuzytkownik(u);
                        okienko.Pokazpanel(u.getInicjal());
                    }
                }
                for (Brygadzista b : brygadzisci){
                    if (Objects.equals(b.getLogin(), l) && Objects.equals(b.getHaslo(), h)){
                        Okienko.setZalogowanybrygadzista(b);
                        okienko.Pokazpanel(b.getInicjal());
                    }
                }
            }
        });
    }
}
