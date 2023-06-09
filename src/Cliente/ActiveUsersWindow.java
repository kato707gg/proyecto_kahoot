package Cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import Servidor.ServerGUI;

public class ActiveUsersWindow extends JFrame {
	private ArrayList<Question> questions;
    private JPanel contentPane;
    
    public ActiveUsersWindow(List<String> activeUsers) {
    	setTitle("Kahoot!");
    	setResizable(false);
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 800);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblNewLabel = new JLabel("Unete al juego con el pin: 123456");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 60));

        String activeUsersString = String.join(", ", activeUsers);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBackground(new Color(166, 137, 51));
        lblNewLabel_1.setOpaque(true);
        
        JTextArea textArea = new JTextArea();
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Dubai", Font.BOLD, 36));
        textArea.setBackground(new Color(255, 170, 55));
        textArea.setEditable(false);
        textArea.setText(activeUsersString);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 5000);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Enviar comando para obtener la lista de usuarios activos
                    writer.println("ACTIVEUSERS");

                    while (true) {
                        // Leer respuesta del servidor
                        String response = reader.readLine();

                        // Actualizar el texto en el área de texto
                        textArea.setText(response);

                        // Esperar 1 segundo antes de volver a solicitar la lista de usuarios activos
                        Thread.sleep(1000);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        
        JLabel lblNewLabel_2 = new JLabel("Kahoot!");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        
        JLabel lblNewLabel_3 = new JLabel("Jugadores");
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setFont(new Font("Dubai", Font.BOLD, 20));
        
        JButton btnNewButton = new JButton("Iniciar");
        btnNewButton.setBackground(Color.GRAY);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Dubai", Font.BOLD, 20));
        
        btnNewButton.addActionListener((e) -> {
        	questions = new ArrayList<Question>();
    	    questions.add(new Question("1. ¿Cuál de los siguientes tipos de datos de Java tiene más precisión?", 
    	                                new String[]{"String", "int", "double", "float"}, 
    	                                3, 100));
    	    questions.add(new Question("2. ¿Cuál es el lenguaje que se utiliza para hacer consultas en las bases de datos", 
    	                                new String[]{"XML", "Select", "C++", "SQL"}, 
    	                                3, 100));
    	    questions.add(new Question("3. Para insertar un hiperenlace en una página se utiliza la etiqueta...", 
    	                                new String[]{"href", "a", "link", "label"}, 
    	                                1, 100));
    	    questions.add(new Question("4. ¿En qué directorio se encuentran los archivos de configuración de Linux?", 
    	                                new String[]{"/etc", "/config", "/cfg", "/link"}, 
    	                                0, 100));
    	    questions.add(new Question("5. ¿Cuál de las siguientes memorias es volátil?", 
    	                                new String[]{"RAM", "EPROM", "ROM", "SSD"}, 
    	                                0, 100));
    	    questions.add(new Question("6. ¿Cuantos bits tiene un byte?", 
                    new String[]{"1", "2", "8", "4"}, 
                    2, 100));
    	    questions.add(new Question("7. ¿Cual es un IDE de programacion?", 
                    new String[]{"Vim", "Sublime Text", "NotePad", "IntellijIDEA"}, 
                    3, 100));
    	    questions.add(new Question("8. Es un framework de Javascript", 
                    new String[]{"ASP.NET", "ReactJS", "SpringBoot", "Laravel"}, 
                    1, 100));
    	    questions.add(new Question("9. ¿En que año se creo Java?", 
                    new String[]{"1995", "2001", "2010", "2020"}, 
                    0, 100));
    	    questions.add(new Question("10. ¿Que lenguaje de programacion esta mal escrito?", 
                    new String[]{"C++", "Piton", "C#", "Java"}, 
                    1, 100));
        	
        	
            GameWindow gameWindow = new GameWindow(questions);
            gameWindow.setVisible(true);
            setLocationRelativeTo(null);
            GameManager gameManager = new GameManager(questions);
        	gameManager.start();
        });
        
        int valor = ServerGUI.numUsuariosConectados;
        JLabel lblNewLabel_4 = new JLabel(Integer.toString(valor));
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Dubai", Font.BOLD, 30));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(95)
        			.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
        			.addGap(100))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(60)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
        					.addGap(355)
        					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 201, Short.MAX_VALUE)
        					.addGap(350)
        					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
        			.addGap(68))
        		.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 1224, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(41)
        			.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        			.addGap(57)
        			.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
        			.addGap(28)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblNewLabel_2)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(29)
        							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        					.addGap(20)))
        			.addGap(18)
        			.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }


    /*private void updateUsersPanel() {
        usersPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        if (activeUsers.isEmpty()) {
            JLabel lblNoActiveUsers = new JLabel("No hay usuarios activos");
            lblNoActiveUsers.setForeground(Color.GRAY);
            usersPanel.add(lblNoActiveUsers, gbc);
        } else {
            for (String user : activeUsers) {
                JLabel lblUser = new JLabel(user);
                usersPanel.add(lblUser, gbc);
                gbc.gridy++;
            }
        }
        usersPanel.revalidate();
        usersPanel.repaint();
    }*/

    /*public void setActiveUsers(ArrayList<String> activeUsers) {
        this.activeUsers = activeUsers;
        updateUsersPanel();
    }*/
}