import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
class MainPanel extends JPanel {
    Toolkit toolkit = getToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    public static ArrayList<String> listOfAllWords;
    public static ArrayList<String> listOfSelectedWords;
    public static int[] mainOrderOfWordSelection = new int[37];
    private JButton bFirstBlock = new JButton("BLOCK 1 (1-7)"),
            bSecondBlock = new JButton("BLOCK 2 (11-20)"),
            bThirdBlock = new JButton("BLOCK 3 (1-17)"),
            bFourthBlock = new JButton("BLOCK 4 (1-20)"),
            bFifthBlock = new JButton("BLOCK 5 (21-28)"),
            bSixthBlock = new JButton("BLOCK 6 (28-37)"),
            bSearchGivenRange = new JButton("Szukaj"),
            refreshButton = new JButton("Odswiez"),
            selectOrderButton = new JButton("Wybierz kolejnosc wybierania slow"),
            startFillingCrosswordButton = new JButton("Rozpocznij uzupelnianie krzyzowki"),
            clearButton = new JButton("Wyczysc krzyzowke");
    private JLabel chosenWordsLabel, jlFirstRange, jlSecondRange;
    public static JTextField tfPath = new JTextField("C:\\Users\\PC\\Documents\\wyrazy_moje2.txt"), tfFirstRange, tfSecondRange;
    public static JCheckBox jcNumbersOfWords;
    private JTextArea chosenWordsTextArea;
    private JComboBox pathComboBox;

    public MainPanel() {
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        this.setFocusable(true);
        this.requestFocus();

        JButton[] allButtons = {bFirstBlock, bSecondBlock, bThirdBlock, bFourthBlock, bFifthBlock, bSixthBlock,
                bSearchGivenRange, refreshButton, selectOrderButton, startFillingCrosswordButton, clearButton};
        int[] xPositionOfAllButtons = {200, 350, 500, 650, 800, 950, 1380, 675, 675, 675, 675};
        int[] yPositionOfAllButtons = {10, 10, 10, 10, 10, 10, 10, 475, 540, 605, 730};
        int[] widthOfAllButtons = {130, 130, 130, 130, 130, 130, 100, 400, 400, 400, 400};
        int[] heightOfAllButtons = {30, 30, 30, 30, 30, 30, 30, 50, 50, 50, 50};

        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setBounds(xPositionOfAllButtons[i], yPositionOfAllButtons[i], widthOfAllButtons[i], heightOfAllButtons[i]);
            add(allButtons[i]);
            addKeyListenerToComponent(allButtons[i]);
        }
        JLabel[] allLabels = {jlFirstRange, jlSecondRange, chosenWordsLabel};
        String[] namesOfAllLabels = {"Pierwszy numer:", "Ostatni numer:", "Wybrane slowa:"};
        int[] xPositionOfAllLabels = {1100, 1240, 1225};
        int[] yPositionOfAllLabels = {10, 10, 100};
        int[] widthOfAllLabels = {100, 90, 300};

        for (int i = 0; i < namesOfAllLabels.length; i++) {
            allLabels[i] = new JLabel(namesOfAllLabels[i]);
            allLabels[i].setBounds(xPositionOfAllLabels[i], yPositionOfAllLabels[i], widthOfAllLabels[i], 30);
            allLabels[i].setFont(new Font("Times New Roman", Font.PLAIN, 14));
            add(allLabels[i]);
            addKeyListenerToComponent(allLabels[i]);
        }

        JTextField[] allTextFields = {tfFirstRange, tfSecondRange};
        String[] namesOfAllTextFields = {"1", "28"};
//                "C:\\Users\\PC\\Documents\\wyrazyCalaWersja2.txt"};
//                "C:\\Users\\alark\\Documents\\wyrazy.txt"+};
        int[] xPositionOfAllTextFields = {1200, 1330};
        int[] yPositionOfAllTextFields = {10, 10};
        int[] widthOfAllTextFields = {30, 30};

        for (int i = 0; i < allTextFields.length; i++) {
            allTextFields[i] = new JTextField(namesOfAllTextFields[i]);
            allTextFields[i].setBounds(xPositionOfAllTextFields[i], yPositionOfAllTextFields[i], widthOfAllTextFields[i], 30);
            add(allTextFields[i]);
            addKeyListenerToComponent(allTextFields[i]);
        }

        jcNumbersOfWords = new JCheckBox();
        jcNumbersOfWords.setBounds(1005, 670, 50, 50);
        add(jcNumbersOfWords);
        addKeyListenerToComponent(jcNumbersOfWords);

        chosenWordsTextArea = new JTextArea();
        chosenWordsTextArea.setBounds(1125, 150, 305, 635);
        chosenWordsTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        chosenWordsTextArea.setFocusable(false);
        add(chosenWordsTextArea);

        pathComboBox = new JComboBox();
        pathComboBox.setBounds(1130, 65,300,30);
        pathComboBox.addItem("C:\\Users\\PC\\Documents\\wyrazy_moje2.txt");
        pathComboBox.addItem("C:\\Users\\PC\\Documents\\wyrazyCalaWersja2.txt");
        pathComboBox.addItem("C:\\Users\\alark\\Documents\\wyrazy.txt");
        add(pathComboBox);
        addKeyListenerToComponent(pathComboBox);

        addActionsToAllButtons();
    }

    private static void addKeyListenerToComponent(Component component) {
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    int answer = JOptionPane.showConfirmDialog(
                            MainView.window,
                            "Czy na pewno chcesz zakończyć program?",
                            "Zamykanie",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (answer == JOptionPane.YES_OPTION) {
                        MainView.window.dispose(); // Zamknięcie okna zamiast natychmiastowego zamykania JVM
                        System.exit(0);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(225, 225, 225));
        g.fillRect(50, 50, screenSize.width - 200, screenSize.height - 200);

        g.setColor(Color.white);
        g.fillRect(100, 100, 850, 350);
        g.fillRect(100, 450, 550, 300);

        g.setColor(Color.BLACK);
        int numberOfRectInWidth = 0;
        int numberOfRectInHeight = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 17; j++) {
                if ((j == 7 && i == 0) || (j == 9 && i == 0)
                        || (j == 1 && i == 1) || (j == 3 && i == 1) || (j == 5 && i == 1)
                        || (j == 11 && i == 1) || (j == 13 && i == 1) || (j == 15 && i == 1)
                        || (j == 7 && i == 2) || (j == 9 && i == 2)
                        || (j == 1 && i == 3) || (j == 3 && i == 3) || (j == 5 && i == 3)
                        || (j == 11 && i == 3) || (j == 13 && i == 3) || (j == 15 && i == 3)
                        || (j == 6 && i == 4) || (j == 8 && i == 4) || (j == 9 && i == 4)
                        || (j == 0 && i == 5) || (j == 2 && i == 5) || (j == 4 && i == 5)
                        || (j == 11 && i == 5) || (j == 12 && i == 5) || (j == 14 && i == 5) || (j == 16 && i == 5)
                        || (j == 6 && i == 6) || (j == 8 && i == 6) || (j == 9 && i == 6)) {
                    g.fillRect(100 + numberOfRectInWidth, 100 + numberOfRectInHeight, 50, 50);
                } else {
                    g.drawRect(100 + numberOfRectInWidth, 100 + numberOfRectInHeight, 50, 50);
                }
                numberOfRectInWidth += 50;
            }
            numberOfRectInHeight += 50;
            numberOfRectInWidth = 0;
        }

        numberOfRectInWidth = 0;
        numberOfRectInHeight = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                if ((j == 0 && i == 0) || (j == 2 && i == 0) || (j == 4 && i == 0)
                        || (j == 6 && i == 1) || (j == 8 && i == 1) || (j == 9 && i == 1)
                        || (j == 1 && i == 2) || (j == 3 && i == 2) || (j == 5 && i == 2)
                        || (j == 7 && i == 3) || (j == 9 && i == 3)
                        || (j == 1 && i == 4) || (j == 3 && i == 4) || (j == 5 && i == 4)
                        || (j == 7 && i == 5) || (j == 9 && i == 5)) {
                    g.fillRect(100 + numberOfRectInWidth, 450 + numberOfRectInHeight, 50, 50);
                } else {
                    g.drawRect(100 + numberOfRectInWidth, 450 + numberOfRectInHeight, 50, 50);
                }
                numberOfRectInWidth += 50;
            }
            numberOfRectInHeight += 50;
            numberOfRectInWidth = 0;
        }
        g.drawRect(150, 100, 50, 50);

        g.drawRect(1124, 149, 306, 636);
        g.drawRect(1105, 130, 350, 670);

        if(jcNumbersOfWords.isSelected()){
            setNumbersOfWordInGivenPlacesOnCrossword(g);
            repaint();
        }else{
            repaint();
        }
    }

    private void addActionsToAllButtons() {
        bFirstBlock.addActionListener(e -> startFindingTheWords(0, 7));
        bSecondBlock.addActionListener(e -> startFindingTheWords(10, 20));
        bThirdBlock.addActionListener(e -> startFindingTheWords(0, 17));
        bFourthBlock.addActionListener(e -> startFindingTheWords(0, 20));
        bFifthBlock.addActionListener(e -> startFindingTheWords(21, 28));
        bSixthBlock.addActionListener(e -> startFindingTheWords(28, 37));
        bSearchGivenRange.addActionListener(e -> startFindingTheWords(Integer.parseInt(tfFirstRange.getText()) - 1, Integer.parseInt(tfSecondRange.getText()) - 1));
        refreshButton.addActionListener(e -> repaint());
        selectOrderButton.addActionListener(e -> new SetChosenOrderForSearch(MainView.window));
        startFillingCrosswordButton.addActionListener(e -> startFindingTheWords(0, 37));
        clearButton.addActionListener(e -> clearCrossword());
    }

    private void startFindingTheWords(int startNumber, int endNumber) {
        listOfAllWords = loadWordsFromFile();
        FindingWords findingWords = new FindingWords(startNumber, endNumber);
        listOfSelectedWords = findingWords.findingPossibleWords();
        inputChosenWordsIntoTextAreaFields();
        this.repaint();
    }

    public ArrayList<String> loadWordsFromFile() {
        ArrayList<String> allWords = new ArrayList<>();
        String filePath = tfPath.getText().trim();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ścieżka do pliku nie może być pusta.");
            return allWords;
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            JOptionPane.showMessageDialog(this, "Plik nie istnieje lub jest niepoprawny.");
            return allWords;
        }
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                allWords.add(myReader.nextLine().trim().toUpperCase());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Nie można znaleźć pliku: " + filePath);
        }
        return allWords;
    }
    private static void setNumbersOfWordInGivenPlacesOnCrossword(Graphics g) {
        g.drawString("1-", 105,115);
        g.drawString("37-", 405,665);
        g.drawString("36", 505,565);
        g.drawString("|", 507,576);
        g.drawString("35-", 405,565);
        g.drawString("34", 405,580);
        g.drawString("|", 407,591);
        g.drawString("33", 305,515);
        g.drawString("|", 307,526);
        g.drawString("32-", 105,715);
        g.drawString("31", 205,515);
        g.drawString("|", 207,526);
        g.drawString("30-", 105,615);
        g.drawString("29", 105,530);
        g.drawString("|", 107,541);
        g.drawString("28-", 105,515);
        g.drawString("27-", 355,465);
        g.drawString("26-", 355,365);
        g.drawString("25", 355,315);
        g.drawString("|", 360,326);
        g.drawString("24", 255,315);
        g.drawString("|", 260,326);
        g.drawString("23-", 105,415);
        g.drawString("22", 155,315);
        g.drawString("|", 160,326);
        g.drawString("21", 455,265);
        g.drawString("|", 460,276);
        g.drawString("18-", 605,415);
        g.drawString("17", 905,115);
        g.drawString("|", 910,126);
        g.drawString("16", 805,115);
        g.drawString("|", 810,126);
        g.drawString("15-", 605,315);
        g.drawString("19", 755,315);
        g.drawString("|", 760,326);
        g.drawString("20", 855,315);
        g.drawString("|", 860,326);
        g.drawString("13-", 605,215);
        g.drawString("12-", 605,115);
        g.drawString("14", 705,115);
        g.drawString("|", 710,126);
        g.drawString("11", 605,130);
        g.drawString("|", 610,141);
        g.drawString("10", 505,115);
        g.drawString("|", 510,126);
        g.drawString("3", 205,115);
        g.drawString("|", 207,126);
        g.drawString("6", 305,115);
        g.drawString("|", 307,126);
        g.drawString("7", 405,115);
        g.drawString("|", 407,126);
        g.drawString("8-", 405,165);
        g.drawString("9-", 405,265);
        g.drawString("2", 105,130);
        g.drawString("|", 107,141);
        g.drawString("4-", 105,215);
        g.drawString("5-", 105,315);
    }
    public void clearCrossword() {
        listOfSelectedWords = new ArrayList<>();
        chosenWordsTextArea = new JTextArea();
    }
    public void inputChosenWordsIntoTextAreaFields() {
        chosenWordsTextArea.setText("");
        int countOfSpaces;
        for (int i = 0; i < listOfSelectedWords.size(); i++) {
            if (listOfSelectedWords.get(i) != null) {
                countOfSpaces = 66 - listOfSelectedWords.get(i).length();
                chosenWordsTextArea.setText(chosenWordsTextArea.getText() + (i + 1) + ":");
                for (int j = 0; j < countOfSpaces / 2; j++)
                    chosenWordsTextArea.setText(chosenWordsTextArea.getText() + " ");
                chosenWordsTextArea.setText(chosenWordsTextArea.getText() + listOfSelectedWords.get(i));
                for (int j = 0; j < countOfSpaces / 2 - 3; j++)
                    chosenWordsTextArea.setText(chosenWordsTextArea.getText() + " ");
                chosenWordsTextArea.setText(chosenWordsTextArea.getText() + "\n");
            }
        }
    }
}