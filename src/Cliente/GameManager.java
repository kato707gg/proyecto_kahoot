package Cliente;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Cliente.Question;
import Cliente.GameWindow;


public class GameManager {
	private static ArrayList<Question> questions;
	private static int questionIndex;
	private static int score;
	private static Timer timer;
	private static int timeLeft;

	public GameManager(ArrayList<Question> questions) {
	    GameManager.questions = questions;
	    GameManager.questionIndex = 0;
	    GameManager.score = 0;
	    GameManager.timer = new Timer();
	    GameManager.timeLeft = 0;
	}

	public void start() {
	    
	    // Iniciar el juego con la primera pregunta
	    questionIndex = 0;
	    Question currentQuestion = questions.get(questionIndex);
	    GameWindow.setQuestion(currentQuestion);
	    
	    // Establecer la primera pregunta
	    GameWindow.setQuestion(questions.get(questionIndex));

	    // Iniciar el temporizador
	    updateTimeLeft();
	    
	    // Habilitar los botones de respuesta y el botón de enviar respuesta
	    GameWindow.setAnswerButtonsEnabled(true);
	    
	    //timeLeft = 30;
	}

	

	public static void updateScore(int points) {
	    score += points;
	    GameWindow.updateScore(score);
	}


    public static void updateTimeLeft() {
        timeLeft = 12;
        GameWindow.setNextButtonEnabled(false);
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeft > 0) { // Verificar si el tiempo restante es mayor que cero
                    timeLeft--;
                    GameWindow.updateTimeLeft(timeLeft);
                    if (timeLeft == 0) {
                        GameWindow.setNextButtonEnabled(true);
                        checkAnswer(-1); // El usuario no seleccionó ninguna respuesta
                    }
                }
            }
        }, 0, 1000);
    }
	
	public static void checkAnswer(int answerIndex) {
		    // Deshabilitar los botones de respuesta y el botón de enviar respuesta
		    GameWindow.setAnswerButtonsEnabled(false);
		    // Obtener la pregunta actual
		    Question currentQuestion = questions.get(questionIndex);
		    // Calcular la puntuación en función del tiempo restante
		    int maxTime = 10;
		    int answerTime = maxTime - timeLeft;
		    int speedFactor = 7; // factor de velocidad
		    int points = (maxTime - answerTime) * speedFactor;
		    if (points < 0) {
		        points = 0;
		    }
		    // Verificar si la respuesta seleccionada es correcta
		    int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
		    if (answerIndex == correctAnswerIndex) {
			    // Actualizar la puntuación
			    updateScore(points);
		    }
		    //timer.cancel();
		}
	
	public static void nextQuestion() {
	    // Pasar a la siguiente pregunta
	    questionIndex++;
	    if (questionIndex >= questions.size()) {
	        endGame();
	        return;
	    }
	    // Establecer la siguiente pregunta
	    GameWindow.setQuestion(questions.get(questionIndex));
	    // Reiniciar el temporizador
	    updateTimeLeft();
	    // Habilitar los botones de respuesta y el botón de enviar respuesta
	    GameWindow.setAnswerButtonsEnabled(true);
	}
	
	public static void endGame() {
	    // Deshabilitar los botones de respuesta y el botón de enviar respuesta
	    GameWindow.setAnswerButtonsEnabled(false);
	    // Mostrar las puntuaciones finales
	    System.out.println("Fin del juego.\n\nTu puntuación final es: " + score);
	    JOptionPane.showMessageDialog(null, "Fin del juego.\n\nTu puntuación final es: " + score, null, JOptionPane.WARNING_MESSAGE, null);
	    //GameWindow.showScoreMessage(message);
	}
}