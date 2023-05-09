package Cliente;

public class Question {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;
    private int points;

    public Question(String question, String[] answers, int correctAnswerIndex, int points) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

