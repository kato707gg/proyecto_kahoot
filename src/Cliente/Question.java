package Cliente;

public class Question {
    private String question;
    private String[] options;
    private int answerIndex;
    private int points;
    private int time;

    public Question(String question, String[] options, int answerIndex, int points, int time) {
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
        this.points = points;
        this.time = time;
        
    }
    

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }
}
