import javax.swing.*;
import java.awt.*;

class SetChosenOrderForSearch extends JDialog {
    public SetChosenOrderForSearch(JFrame parent) {
        super(parent, "Ustaw kolejność", true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton closeButton = new JButton("Powrót");
        closeButton.setBounds(100,100,100,30);
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}