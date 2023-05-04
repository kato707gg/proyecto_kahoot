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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class WindowStart extends JFrame {

	private JPanel contentPane;
	private JTextField txtPinDeJuego;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowStart frame = new WindowStart();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public WindowStart() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(56, 18, 114));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Kahoot!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 65));
		lblNewLabel.setForeground(Color.WHITE);
		
		JPanel panel = new JPanel();
		JLabel lblNewLabel_1 = new JLabel("  ¡ No reconocimos el PIN de juego. Comprueba y vuelve a intentarlo !");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Dubai", Font.BOLD, 26));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(220, 20, 60));
		lblNewLabel_1.setForeground(Color.WHITE);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(450)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
					.addGap(450))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(384, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(230)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(195)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
		);
		
		txtPinDeJuego = new JTextField();
		txtPinDeJuego.setForeground(new Color(180, 180, 180));
		txtPinDeJuego.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
		txtPinDeJuego.setHorizontalAlignment(SwingConstants.CENTER);
		txtPinDeJuego.setText("PIN de juego");
		int pos = txtPinDeJuego.getText().length() / 2; //calcular la mitad del texto
        txtPinDeJuego.setCaretPosition(pos); //posicionar el cursor
        
		txtPinDeJuego.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        super.mouseClicked(e);
		        if (txtPinDeJuego.getText().equals("PIN de juego")) {
		            txtPinDeJuego.setText("");
		            txtPinDeJuego.setForeground(Color.BLACK);
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
						.addComponent(txtPinDeJuego, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(txtPinDeJuego, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(14))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtPinDeJuego, btnNewButton}));
		contentPane.setLayout(gl_contentPane);
		btnNewButton.setFocusable(true);
	    btnNewButton.requestFocusInWindow();
	    
	    btnNewButton.addActionListener((e) -> {
            String codigo = txtPinDeJuego.getText();

            // Crea una instancia del cliente y se comunica con el servidor
            Client client = new Client();
            String response = client.enviarCodigo(codigo);

            if (response.equals("CODIGO_VALIDO")) {
                dispose();
                WindowNickname windownickname = new WindowNickname();
                windownickname.setVisible(true);
                setLocationRelativeTo(null);
                
            } 
            if (response.equals("CODIGO_VALIDO_ADMIN")) {
            	dispose();
            	ActiveUsersWindow activeUsersWindow = new ActiveUsersWindow(new ArrayList<String>(Arrays.asList(ServerGUI.getActiveUsers())));
	            activeUsersWindow.setVisible(true);
	            setLocationRelativeTo(null);
            }
            else {
            	lblNewLabel_1.setVisible(true);
                Timer timer = new Timer(5000, new ActionListener() { // El label se oculta después de 5 segundos
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lblNewLabel_1.setVisible(false);
                    }
                });
                timer.setRepeats(false); // La acción del timer se ejecuta solo una vez
                timer.start(); 
            }
        });
	}
	
    public void paint (Graphics g)
    {
        super.paint(g);
        Color myColor = new Color(50, 16, 102);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(45), 350, 350);
        g2d.setColor(myColor);
        g2d.fillRect(-120, 150, 450, 450);

        g.setColor (myColor);
        g.fillOval(870, -200, 500, 500);
    }
}