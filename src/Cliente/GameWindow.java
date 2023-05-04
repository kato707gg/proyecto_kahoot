package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener {

    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JLabel countdownLabel;
    private JButton nextButton;
    private int countdownTime;
    private Timer countdownTimer;
    private boolean isAdmin;
    private int questionValue;

    private int[] answerValues;

    private int selectedAnswerIndex;

    private boolean isAnswered;
    private boolean isCorrect;

    private GameWindowListener listener;

    public GameWindow(boolean isAdmin, int questionValue, String question, String[] answers, int[] answerValues, int countdownTime) {
        this.isAdmin = isAdmin;
        this.questionValue = questionValue;
        this.answerValues = answerValues;
        this.selectedAnswerIndex = -1;
        this.isAnswered = false;
        this.isCorrect = false;

        this.countdownTime = countdownTime;
        this.countdownTimer = new Timer(1000, this);

        // Configuración de la ventana
        setTitle("Kahoot - Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);

        // Etiqueta de la pregunta
        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 400, 50);
        questionLabel.setText(question);
        getContentPane().add(questionLabel);

        // Botones de respuesta
        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setBounds(50 + 100 * (i % 2), 125 + 50 * (i / 2), 90, 40);
            answerButtons[i].setText(answers[i]);
            answerButtons[i].addActionListener(this);
            getContentPane().add(answerButtons[i]);
        }

        // Etiqueta de la cuenta regresiva
        countdownLabel = new JLabel();
        countdownLabel.setBounds(200, 225, 100, 30);
        countdownLabel.setText(Integer.toString(countdownTime));
        getContentPane().add(countdownLabel);

        // Botón "Siguiente"
        if (isAdmin) {
            nextButton = new JButton();
            nextButton.setBounds(200, 300, 100, 30);
            nextButton.setText("Siguiente");
            nextButton.setEnabled(false);
            nextButton.addActionListener(this);
            getContentPane().add(nextButton);
        }

        setVisible(true);
    }

    // Método para iniciar la cuenta regresiva
    public void startCountdown() {
        countdownTimer.start();
    }

    // Método para detener la cuenta regresiva
    public void stopCountdown() {
        countdownTimer.stop();
    }

    // Método para obtener la respuesta seleccionada
    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    // Método para obtener si la respuesta es correcta
    public boolean isCorrect() {
        return isCorrect;
    }

    // Método para establecer el listener de la ventana
    public void setListener(GameWindowListener listener) {
        this.listener = listener;
    }

    // Método para actualizar la etiqueta de la cuenta regresiva
    private void updateCountdownLabel() {
        countdownLabel.setText(Integer.toString(countdownTime));
    }

    // Método para manejar los eventos de los botones
    /*public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Manejo del botón "next"
            if (currentQuestionIndex == numQuestions - 1) {
                // Se acabaron las preguntas
                endGame();
            } else {
                // Pasar a la siguiente pregunta
                currentQuestionIndex++;
                currentQuestion = questions.get(currentQuestionIndex);
                showQuestion();
            }
        } else if (e.getSource() == timer) {
            // Actualizar el temporizador
            timeRemaining--;
            if (timeRemaining == 0) {
                // Tiempo agotado
                disableAnswerButtons();
                timer.stop();
                showCorrectAnswer();
            } else {
                lblTimeRemaining.setText("Tiempo restante: " + timeRemaining + " s");
            }
        } else {
            // Manejo de los botones de respuesta
            disableAnswerButtons();
            timer.stop();
            JButton clickedButton = (JButton) e.getSource();
            int selectedAnswerIndex = answerButtons.indexOf(clickedButton);
            if (currentQuestion.checkAnswer(selectedAnswerIndex)) {
                // Respuesta correcta
                int points = currentQuestion.getPoints();
                updateScore(points);
                showCorrectAnswer();
            } else {
                // Respuesta incorrecta
                showCorrectAnswer();
            }
        }
    }*/
}