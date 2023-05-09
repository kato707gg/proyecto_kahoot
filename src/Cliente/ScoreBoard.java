package Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {
    private List<Score> scores;

    public ScoreBoard() {
        scores = new ArrayList<>();
    }

    public void addScore(String name, int points) {
        scores.add(new Score(name, points));
    }

    public List<Score> getScores() {
        // Ordenar la lista de puntuaciones de mayor a menor
        Collections.sort(scores, Comparator.comparing(Score::getPoints).reversed());
        return scores;
    }

    public static class Score {
        private String name;
        private int points;

        public Score(String name, int points) {
            this.name = name;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getPoints() {
            return points;
        }
    }

	public Object[][] getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}