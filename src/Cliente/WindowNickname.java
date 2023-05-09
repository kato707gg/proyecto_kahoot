package Cliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import Servidor.ServerGUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
//import Cliente.UserListGUI;

public class WindowNickname extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickname;
	public static String nickname;
    //private UserListGUI userListGUI;
    Client client = new Client();

	public WindowNickname() {
		//userListGUI = new UserListGUI();
		initComponents();
	}

	private void initComponents() {
		
	    new ArrayList<String>();
	    
		setTitle("Kahoot!");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(126, 179, 70));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Kahoot!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 65));
		lblNewLabel.setForeground(Color.WHITE);
		
		JPanel panel = new JPanel();
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(450)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
					.addGap(450))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
					.addGap(588))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(230)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
		);
		JLabel lblNewLabel_1 = new JLabel("  ¡ El nickname debe tener al menos un caracter !");
		lblNewLabel_1.setBounds(0, 0, 548, 69);
		layeredPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Dubai", Font.BOLD, 26));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(220, 20, 60));
		lblNewLabel_1.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_2 = new JLabel("  Este nombre de usuario ya está en uso");
		layeredPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Dubai", Font.BOLD, 26));
		lblNewLabel_2.setBackground(new Color(220, 20, 60));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(0, 0, 448, 69);
		
		
		txtNickname = new JTextField();
		txtNickname.setForeground(new Color(180, 180, 180));
		txtNickname.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
		txtNickname.setHorizontalAlignment(SwingConstants.CENTER);
		txtNickname.setDocument(new JTextFieldLimit(10)); // Establece el límite de caracteres
		txtNickname.setText("Nickname");
		int pos = txtNickname.getText().length() / 2; //calcular la mitad del texto
		txtNickname.setCaretPosition(pos); //posicionar el cursor
		
		txtNickname.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        super.mouseClicked(e);
		        if (txtNickname.getText().equals("Nickname")) {
		            txtNickname.setText("");
		            txtNickname.setForeground(Color.BLACK);
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setFont(new Font("Dubai", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(54, 54, 54));
		btnNewButton.setForeground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
						.addComponent(txtNickname, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(14))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNickname, btnNewButton}));
		contentPane.setLayout(gl_contentPane);
	    
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nickname = txtNickname.getText();

		        if (nickname.isEmpty()) {
		            lblNewLabel_1.setVisible(true);
		            Timer timer = new Timer(5000, new ActionListener() { // El label se oculta después de 5 segundos
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    lblNewLabel_1.setVisible(false);
		                }
		            });
		            timer.setRepeats(false); // La acción del timer se ejecuta solo una vez
		            timer.start();
		            return;
		        }

		        if (!ServerGUI.addUser(nickname)) {
		            // Si el usuario ya existe, mostrar un mensaje de error
		            lblNewLabel_2.setVisible(true);
		            Timer timer = new Timer(5000, new ActionListener() { // El label se oculta después de 5 segundos
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    lblNewLabel_1.setVisible(false);
		                }
		            });
		            timer.setRepeats(false); // La acción del timer se ejecuta solo una vez
		            timer.start();
		            return;
		        }

		        try {
		            // Intentar conectarse al servidor
		            Socket socket = new Socket("127.0.0.1", 5000);
		            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		            // Enviar el nickname al servidor
		            dos.writeUTF(nickname);

		            // Cerrar la ventana actual y abrir la ventana activeUsersWindow
		            dispose();
		            ActiveUsersWindow activeUsersWindow = new ActiveUsersWindow(new ArrayList<String>(Arrays.asList(ServerGUI.getActiveUsers())));
		            activeUsersWindow.setVisible(true);
		            setLocationRelativeTo(null);
		            //UserListGUI usersList = new UserListGUI();
	                //usersList.setVisible(true);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
	}
	
    public void paint (Graphics g)
    {
        super.paint(g);
        Color myColor = new Color(117, 166, 65);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(45), 350, 350);
        g2d.setColor(myColor);
        g2d.fillRect(-120, 150, 450, 450);

        g.setColor (myColor);
        g.fillOval(870, -200, 500, 500);
    }
}