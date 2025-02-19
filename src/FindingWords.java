import javax.swing.*;
import java.util.*;

class FindingWords {
    ArrayList<String> possibleWords;
    private int startNumber;
    private int endNumber;
    boolean wordPass = true;
    int resetAllWords = 100;
    ArrayList<WordsDetails> parametersOfWords;
    private static Map<Integer, List<String>> wordsByLength = new HashMap<>();
    static int[] arrayOfWordsLength = {7, 5, 5, 7, 6, 5, 4, 5, 5, 4, 13, 7, 7, 5, 7, 5, 5, 7, 3, 3, 7, 5, 6, 5, 5, 6, 6, 6, 5, 7, 5, 7, 5, 4, 5, 4, 5};
    static int[] arrayOfFirstsPositionOfXAxis = {100, 100, 200, 100, 100, 300, 400, 400, 400, 500, 600, 600, 600, 700, 600, 800, 900, 600, 750, 850, 450,
            150, 100, 250, 350, 350, 350, 100, 100, 100, 200, 100, 300, 400, 400, 500, 400};
    static int[] arrayOfFirstPositionOfYAxis = {100, 100, 100, 200, 300, 100, 100, 150, 250, 100, 100, 100, 200, 100, 300, 100, 100, 400, 300, 300, 250,
            300, 400, 300, 300, 350, 450, 500, 500, 600, 500, 700, 500, 550, 550, 550, 650};
    static int[] connectedWordCount = {4, 3, 3, 3, 4, 5, 3, 4, 3, 3, 2, 10, 4, 4, 3, 6, 3, 3, 32, 2, 4, 3, 3, 3, 5, 3, 3, 6, 3, 4, 3, 4, 3, 4, 4, 2, 3};
    static List[] listOfNumberOfLettersInWordConnectedToConnectedWords = {Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 3, 5),
            Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 3, 5),
            Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5), Arrays.asList(2, 4),
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 12), Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 3, 5),
            Arrays.asList(1, 3, 4, 5, 6, 7), Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5), Arrays.asList(1, 4, 6),
            Arrays.asList(1, 3), Arrays.asList(1, 3), Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 3, 5),
            Arrays.asList(2, 4, 6), Arrays.asList(1, 3, 5), Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 3, 6),
            Arrays.asList(1, 3, 6), Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5, 7),
            Arrays.asList(1, 3, 5), Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 3, 5), Arrays.asList(1, 2, 3, 4),
            Arrays.asList(1, 2, 3, 5), Arrays.asList(1, 3), Arrays.asList(1, 3, 5)
    };
    static List[] listOfConnectedWord = {Arrays.asList(2, 3, 6, 7), Arrays.asList(1, 4, 5), Arrays.asList(1, 4, 5),
            Arrays.asList(2, 3, 6, 7), Arrays.asList(2, 22, 3, 24, 6), Arrays.asList(1, 4, 5), Arrays.asList(1, 8, 4, 9)
            , Arrays.asList(7, 10, 11), Arrays.asList(7, 10, 11), Arrays.asList(8, 9), Arrays.asList(12, 8, 13, 9, 15, 18, 26, 27, 35, 37),
            Arrays.asList(11, 14, 16, 17), Arrays.asList(11, 14, 16, 17), Arrays.asList(12, 13, 15), Arrays.asList(11, 14, 19, 16, 20, 17),
            Arrays.asList(12, 13, 15), Arrays.asList(12, 13, 15), Arrays.asList(11, 19, 20), Arrays.asList(15, 18), Arrays.asList(15, 18),
            Arrays.asList(9, 26, 27, 35), Arrays.asList(5, 23, 28), Arrays.asList(5, 23, 28), Arrays.asList(5, 23, 28), Arrays.asList(5, 26, 23, 27, 28),
            Arrays.asList(25, 21, 11), Arrays.asList(25, 21, 11), Arrays.asList(29, 22, 31, 24, 33, 25), Arrays.asList(28, 30, 32),
            Arrays.asList(29, 31, 33, 34), Arrays.asList(28, 30, 32), Arrays.asList(29, 31, 33, 34), Arrays.asList(28, 30, 32),
            Arrays.asList(35, 30, 37, 32), Arrays.asList(34, 21, 36, 11), Arrays.asList(35, 37), Arrays.asList(34, 36, 11)};

    static List[] listOfListOfConnectedPositions = {Arrays.asList(1, 1, 1, 1), Arrays.asList(1, 1, 1), Arrays.asList(3, 3, 3), Arrays.asList(3, 3, 3, 3),
            Arrays.asList(5, 1, 5, 1, 5), Arrays.asList(5, 5, 5), Arrays.asList(7, 1, 7, 1), Arrays.asList(2, 2, 2), Arrays.asList(4, 4, 4),
            Arrays.asList(3, 3), Arrays.asList(1, 5, 1, 5, 1, 6, 1, 6, 5, 5), Arrays.asList(1, 1, 1, 1), Arrays.asList(3, 3, 3, 3),
            Arrays.asList(3, 3, 3), Arrays.asList(5, 5, 1, 5, 1, 5), Arrays.asList(5, 5, 5), Arrays.asList(7, 7, 7), Arrays.asList(7, 3, 3),
            Arrays.asList(4, 4), Arrays.asList(6, 6), Arrays.asList(2, 3, 3, 2), Arrays.asList(2, 2, 2), Arrays.asList(3, 3, 3),
            Arrays.asList(4, 4, 4), Arrays.asList(6, 1, 6, 1, 6), Arrays.asList(2, 3, 6), Arrays.asList(4, 5, 8),
            Arrays.asList(1, 5, 1, 5, 1, 5), Arrays.asList(1, 1, 1), Arrays.asList(3, 3, 3, 2), Arrays.asList(3, 3, 3), Arrays.asList(5, 5, 5, 4),
            Arrays.asList(5, 5, 5), Arrays.asList(1, 7, 1, 7), Arrays.asList(1, 7, 1, 10), Arrays.asList(3, 3), Arrays.asList(3, 3, 12)};

    public static ArrayList<WordsDetails> getParametersOfWords() {
        ArrayList<WordsDetails> allWordsDetails = new ArrayList<>();
        for (int i = 0; i < arrayOfWordsLength.length; i++) {
            allWordsDetails.add(new WordsDetails.Builder()
                    .setWordNumber(i)
                    .setWordLength(arrayOfWordsLength[i])
                    .setConnectedWordsCount(connectedWordCount[i])
                    .setLetterPositions(listOfNumberOfLettersInWordConnectedToConnectedWords[i])
                    .setConnectedWords(listOfConnectedWord[i])
                    .setConnectedPositions(listOfListOfConnectedPositions[i])
                    .setFirstPositionOfXAxis(arrayOfFirstsPositionOfXAxis[i])
                    .setFirstPositionOfYAxis(arrayOfFirstPositionOfYAxis[i])
                    .build());
        }
        return allWordsDetails;
    }

    public FindingWords(int startFromSearchingWord, int stopToSearchingWord) {
        parametersOfWords = getParametersOfWords();
        possibleWords = new ArrayList<>();
        startNumber = startFromSearchingWord;
        endNumber = stopToSearchingWord;
    }

    public ArrayList<String> findingPossibleWords() {
        resetCrossword(startNumber);
        WordsDetails wordsDetails;
        Set<String> usedWords = new HashSet<>(); // Zbiór do przechowywania już wybranych słów

//        int i;
//        for (i = startNumber; i < endNumber; i++)
//            if (i != startNumber && possibleWords.get(i - 1) == null) {
//                resetCrossword(startNumber);
//            }
//        if (possibleWords.get(i) != null && !possibleWords.get(i).isEmpty()) {
//            i++;
//        }
        wordsDetails = parametersOfWords.get(0);
        List<String> presentsWordsOfGivenLength = wordDraw(wordsDetails.getWordLength());
        String firstWordToFit;
        String presentWord = null;

        for (int ii = 0; ii < presentsWordsOfGivenLength.size(); ii++) {
            if (resetAllWords <= 0) {
                resetCrossword(startNumber);
                break;
            }
            resetAllWords--; // Liczymy liczbę prób
            for (int j = 0; j < wordsDetails.getConnectedWordsCount(); j++) {
                WordsDetails presentWordsDetails = parametersOfWords.get(j);
                presentWord = presentsWordsOfGivenLength.get(ii);
                firstWordToFit = possibleWords.get(wordsDetails.getConnectedWordsCount() - 1);

                try {
                    if (!presentWord.equals(firstWordToFit)) {
                        if (presentWord.charAt(presentWordsDetails.getConnectedWordsCount() - 1) ==
                                firstWordToFit.charAt(presentWordsDetails.getConnectedWordsCount() - 1)) {
                            wordPass = true;
                        } else {
                            wordPass = false;
                            break;
                        }
                    }
                } catch (Exception ignore) {
                    wordPass = true;
                }
            }

            if (wordPass && !usedWords.contains(presentWord)) { // Sprawdzamy, czy słowo już zostało wybrane
                possibleWords.set(MainPanel.mainOrderOfWordSelection[0], presentWord);
                usedWords.add(presentWord); // Dodajemy słowo do zbioru wybranych słów
                resetAllWords = 100;
                break;
            } else if (resetAllWords <= 0) {
                resetCrossword(startNumber);
                break;
            }
        }
        JOptionPane.showMessageDialog(MainView.window,"Zakończono wyszukiwanie");
        MainPanel.listOfSelectedWords =possibleWords;
        return possibleWords;
    }

    private static List<String> wordDraw(int wordLength) {
        ArrayList<String> listOfAllWords = MainPanel.listOfAllWords;
        ArrayList<String> listOfGivenLength = new ArrayList<>();
        for (int i = 0; i < listOfAllWords.size(); i++) {
            if(listOfAllWords.get(i).length()==wordLength){
                listOfGivenLength.add(listOfAllWords.get(i));
            }
        }
//        List<String> wordsOfGivenLength = wordsByLength.getOrDefault(wordLength, Collections.emptyList());
//        Collections.shuffle(wordsOfGivenLength);  // Mieszamy słowa, by dodać element losowości
//        return wordsOfGivenLength;
        Collections.shuffle(listOfGivenLength);
        return listOfGivenLength;
    }

    public void resetCrossword(int startFromSearchingWord) {
//        mainLoopIterator = startFromSearchingWord;
        wordPass = true;
        resetAllWords = 100;
        fillChosenWordsListWithEmptySlots();
        MainPanel.listOfSelectedWords = new ArrayList<>();
    }

    public void fillChosenWordsListWithEmptySlots() {
        possibleWords = new ArrayList<>(Arrays.asList(
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null));
    }
}