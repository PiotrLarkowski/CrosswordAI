import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Główne okno aplikacji
class MainView extends JPanel {
    public static JFrame window;
    static Dimension screenSize;
    public MainView() {
        Toolkit toolkit = getToolkit();
        screenSize = toolkit.getScreenSize();
    }
    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Crossword");
        window.setVisible(true);

        MainPanel crosswordPanel = new MainPanel();
        crosswordPanel.setLayout(null);
        window.add(crosswordPanel, BorderLayout.CENTER);
        window.pack();
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    int answer = JOptionPane.showConfirmDialog(
                            window,
                            "Czy na pewno chcesz zakończyć program?",
                            "Zamykanie",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (answer == JOptionPane.YES_OPTION) {
                        window.dispose(); // Zamknięcie okna zamiast natychmiastowego zamykania JVM
                        System.exit(0);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}