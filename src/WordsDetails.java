import java.util.List;

class WordsDetails {
    private int wordNumber;
    private int wordLength;
    private int connectedWordsCount;
    private int firstPositionOfXAxis;
    private int firstPositionOfYAxis;
    private List<Integer> letterPositions;
    private List<Integer> connectedWords;
    private List<Integer> connectedPositions;
    public WordsDetails(int wordNumber, int wordLength, int connectedWordsCount,
                        List<Integer> letterPositions, List<Integer> connectedWords, List<Integer> connectedPositions,
                        int firstPositionOfXAxis, int firstPositionOfYAxis) {
        this.wordNumber = wordNumber;
        this.wordLength = wordLength;
        this.connectedWordsCount = connectedWordsCount;
        this.letterPositions = letterPositions;
        this.connectedWords = connectedWords;
        this.connectedPositions = connectedPositions;
        this.firstPositionOfXAxis = firstPositionOfXAxis;
        this.firstPositionOfYAxis = firstPositionOfYAxis;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int getConnectedWordsCount() {
        return connectedWordsCount;
    }

    public int getFirstPositionOfXAxis() {
        return firstPositionOfXAxis;
    }

    public int getFirstPositionOfYAxis() {
        return firstPositionOfYAxis;
    }

    public List<Integer> getLetterPositions() {
        return letterPositions;
    }

    public List<Integer> getConnectedWords() {
        return connectedWords;
    }

    public List<Integer> getConnectedPositions() {
        return connectedPositions;
    }

    public static class Builder {
        private int wordNumber;
        private int wordLength;
        private int connectedWordsCount;
        private List<Integer> letterPositions;
        private List<Integer> connectedWords;
        private List<Integer> connectedPositions;
        private int firstPositionOfXAxis;
        private int firstPositionOfYAxis;

        public Builder setWordNumber(int wordNumber) {
            this.wordNumber = wordNumber;
            return this;
        }

        public Builder setWordLength(int wordLength) {
            this.wordLength = wordLength;
            return this;
        }

        public Builder setConnectedWordsCount(int connectedWordsCount) {
            this.connectedWordsCount = connectedWordsCount;
            return this;
        }

        public Builder setLetterPositions(List<Integer> letterPositions) {
            this.letterPositions = letterPositions;
            return this;
        }

        public Builder setConnectedWords(List<Integer> connectedWords) {
            this.connectedWords = connectedWords;
            return this;
        }

        public Builder setConnectedPositions(List<Integer> connectedPositions) {
            this.connectedPositions = connectedPositions;
            return this;
        }

        public Builder setFirstPositionOfXAxis(int firstPositionOfXAxis) {
            this.firstPositionOfXAxis = firstPositionOfXAxis;
            return this;
        }

        public Builder setFirstPositionOfYAxis(int firstPositionOfYAxis) {
            this.firstPositionOfYAxis = firstPositionOfYAxis;
            return this;
        }

        public WordsDetails build() {
            return new WordsDetails(wordNumber, wordLength, connectedWordsCount, letterPositions, connectedWords, connectedPositions, firstPositionOfXAxis, firstPositionOfYAxis);
        }
    }
}