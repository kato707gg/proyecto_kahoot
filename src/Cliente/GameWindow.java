package Cliente;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class GameWindow extends JFrame {
    private static JLabel questionLabel = new JLabel();
    public static JButton[] answerButtons;
    private static JLabel timeLabel;
    private static JLabel scoreLabel;
    private static JButton nextButton;
    private JTextArea scoreTextArea;
    private int questionIndex;
    //private int timeLeft;
    private int score;
    private static int correctAnswerIndex;
	public static int timeRemaining;
    private GameManager gameManager;
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public GameWindow(ArrayList<Question> questions) {
        getContentPane().setBackground(Color.WHITE);
        setTitle("Kahoot Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel answersPanel = new JPanel();
        answersPanel.setBounds(10, 347, 1216, 344);
        answerButtons = new JButton[4];
        answersPanel.setLayout(new GridLayout(0, 2, 0, 0));
        answerButtons[0] = new JButton("New button");
        answerButtons[0].setBackground(new Color(244, 48, 60));
        answerButtons[1] = new JButton("New button");
        answerButtons[1].setBackground(new Color(9, 102, 210));
        answerButtons[2] = new JButton("New button");
        answerButtons[2].setBackground(new Color(207, 143, 0));
        answerButtons[3] = new JButton("New button");
        answerButtons[3].setBackground(new Color(30, 118, 9));
        for(int i=0; i<4; i++) {
        	answerButtons[i].setForeground(Color.WHITE);
        	answerButtons[i].setFont(new Font("Dubai", Font.BOLD, 30));
            answersPanel.add(answerButtons[i]);
        }

        for(int i=0; i<4; i++) {
        	answerButtons[i].addMouseListener(new MouseAdapter() {
                Color originalColor;
                
                public void mouseEntered(MouseEvent e) {
                    JButton button = (JButton)e.getSource();
                    originalColor = button.getBackground();
                    button.setBackground(originalColor.darker());
                }
                
                public void mouseExited(MouseEvent e) {
                    JButton button = (JButton)e.getSource();
                    button.setBackground(originalColor);
                }
            });
        	answerButtons[i].addActionListener(e ->{
        		int answerIndex = 0;
        		for(int j = 0; j < 4; j++) {
        		    if(e.getSource() == answerButtons[j]) {
        		        answerIndex = j;
        		        break;
        		    }
        		}
        		GameManager.checkAnswer(answerIndex);
            });
        }
        
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        scoreLabel = new JLabel("Puntuación: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Dubai", Font.BOLD, 20));
        scoreLabel.setBounds(10, 10, 246, 65);
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.GRAY);
        nextButton.setBounds(570, 0, 82, 52);
        nextButton.setFont(new Font("Dialog", Font.BOLD, 20));
        nextButton.addActionListener(e ->{
    		GameManager.nextQuestion();
        });
        
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBounds(10, 10, 1216, 143);
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(935, 202, 266, 85);
        JPanel nextPanel = new JPanel();
        nextPanel.setBounds(10, 701, 1216, 52);
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        scorePanel.setLayout(null);
        scorePanel.add(scoreLabel);
        nextPanel.setLayout(null);
        nextPanel.add(nextButton);
        getContentPane().setLayout(null);
        getContentPane().add(questionPanel);
        getContentPane().add(answersPanel);
        getContentPane().add(scorePanel);
        getContentPane().add(nextPanel);
        
        scoreTextArea = new JTextArea();
        scoreTextArea.setBounds(216, 539, 734, 128);
        getContentPane().add(scoreTextArea);
        scoreTextArea.setEditable(false);
        scoreTextArea.setLineWrap(true);
        scoreTextArea.setWrapStyleWord(true);
        scoreTextArea.setPreferredSize(new Dimension(200, 450));
        timeLabel = new JLabel("");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBackground(new Color(141, 86, 198));
        timeLabel.setBounds(552, 202, 122, 85);
        timeLabel.setOpaque(true);
        getContentPane().add(timeLabel);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        
    }

    public void paint (Graphics g)
    {
        super.paint(g);
        Color myColor = new Color(141, 86, 198);
        g.setColor (myColor);
        g.fillOval(547, 200, 150, 150);
    }
    
    public static void setQuestion(Question question) {
        // Mostrar la pregunta en questionLabel
        questionLabel.setText(question.getQuestion());

        // Mostrar las respuestas en los botones de respuesta
        String[] answers = question.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            answerButtons[i].setText(answers[i]);
        }

        // Establecer el índice de la respuesta correcta en el array de respuestas
        correctAnswerIndex = question.getCorrectAnswerIndex();

        // Establecer los botones de respuesta y el botón de enviar respuesta como habilitados
        setAnswerButtonsEnabled(true);
    }

    public static void updateScore(int score) {
        scoreLabel.setText("Puntuación: " + score);
    }


    public static void updateTimeLeft(int timeLeft) {
        timeLabel.setText("" + timeLeft);
    }


    public static void setAnswerButtonsEnabled(boolean enabled) {
        for (JButton button : answerButtons) {
            button.setEnabled(enabled);
        }
    }

    public static void setNextButtonEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }
    
}