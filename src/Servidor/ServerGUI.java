package Servidor;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import Cliente.WindowStart;

public class ServerGUI extends JFrame{
	    
        public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					ServerGUI serverGUI = new ServerGUI();
    	                serverGUI.setVisible(true);
    	                serverGUI.setLocationRelativeTo(null);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
	}
	
    private static final String CODIGOS_VALIDOS_FILE = "src/resources/codigos_validos.txt";
    private static final String CODIGOS_VALIDOS_ADMIN_FILE = "src/resources/codigos_validos_admin.txt";
    private String direccionIP;
    private int numeroPuerto;
    private ServerSocket serverSocket;
    public static int numUsuariosConectados = 0;
    private static final int MAXIMO_USUARIOS = 20;
    private static List<String> activeUsers = new ArrayList<String>();
    private JTextField txtDireccionIP;
    private JTextField txtNumeroPuerto;
    List<Socket> sockets = new ArrayList<>();
    
    
    public ServerGUI() {
    	setResizable(false);
    	setBounds(100, 100, 466, 389);
    	getContentPane().setBackground(new Color(99, 184, 211));
    	
    	JLabel lblDireccionIP = new JLabel("Direccion IP");
    	lblDireccionIP.setForeground(Color.WHITE);
    	lblDireccionIP.setFont(new Font("Dubai", Font.BOLD, 20));
    	
    	JLabel lblNumeroPuerto = new JLabel("Puerto");
    	lblNumeroPuerto.setForeground(Color.WHITE);
    	lblNumeroPuerto.setFont(new Font("Dubai", Font.BOLD, 20));
    	
    	txtDireccionIP = new JTextField();
    	txtDireccionIP.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
    	txtDireccionIP.setColumns(10);
    	
    	txtNumeroPuerto = new JTextField();
    	txtNumeroPuerto.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
    	txtNumeroPuerto.setColumns(10);
    	
    	JButton btnConectar = new JButton("Conectar");
    	btnConectar.setBackground(Color.GRAY);
    	btnConectar.setForeground(Color.WHITE);
    	btnConectar.setFont(new Font("Dubai", Font.BOLD, 17));
        btnConectar.addActionListener(e ->{
        	if (txtDireccionIP.getText().isEmpty() || txtNumeroPuerto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la dirección IP y el número de puerto", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Integer.parseInt(txtNumeroPuerto.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el número de puerto", "Valor inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            WindowStart windowStart = new WindowStart();
            windowStart.setVisible(true);
            windowStart.setLocationRelativeTo(null);
            conectar();
        });
    	
    	GroupLayout groupLayout = new GroupLayout(getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(61)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addComponent(lblDireccionIP)
    					.addComponent(lblNumeroPuerto))
    				.addGap(50)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(txtDireccionIP)
    						.addGap(61))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(txtNumeroPuerto, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
    						.addContainerGap())))
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(151)
    				.addComponent(btnConectar, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
    				.addGap(149))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(52)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
    					.addComponent(txtDireccionIP, 0, 0, Short.MAX_VALUE)
    					.addComponent(lblDireccionIP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    				.addGap(62)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
    					.addComponent(txtNumeroPuerto, 0, 0, Short.MAX_VALUE)
    					.addComponent(lblNumeroPuerto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    				.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
    				.addComponent(btnConectar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
    				.addGap(47))
    	);
    	getContentPane().setLayout(groupLayout);
    }
    
    private void conectar() {
        // Leer los valores ingresados en los campos de texto
        direccionIP = txtDireccionIP.getText();
        numeroPuerto = Integer.parseInt(txtNumeroPuerto.getText());
        try {
            serverSocket = new ServerSocket(numeroPuerto, 0, InetAddress.getByName(direccionIP));
            System.out.println("Servidor escuchando en la dirección: " + direccionIP + " puerto: " + numeroPuerto);

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    while (true) {
                        if (numUsuariosConectados < MAXIMO_USUARIOS) {
                            Socket socket = serverSocket.accept();
                            sockets.add(socket); // Agrega el nuevo socket a la lista
                            //numUsuariosConectados++; // Incrementa el contador de usuarios conectados
                            handleClient(socket);
                        }
                        else {
                            System.out.println("Máximo de usuarios alcanzado. Conexión rechazada.");
                            // Si se alcanzó el número máximo de usuarios conectados, se ignora la conexión entrante
                            Thread.sleep(1000); // Espera un segundo antes de volver a verificar
                        }
                    }
                }

                @Override
                protected void done() {
                    try {
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            worker.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
public void handleClient(Socket socket) throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        
        // Lee el archivo de códigos válidos
        List<String> codigosValidos = Files.readAllLines(Paths.get(CODIGOS_VALIDOS_FILE));
        List<String> codigosValidosAdmin = Files.readAllLines(Paths.get(CODIGOS_VALIDOS_ADMIN_FILE));

        // Obtiene el código enviado por el cliente
        String codigoRecibido = reader.readLine();

        // Valida el código
        if (codigosValidos.contains(codigoRecibido)) {
            writer.println("CODIGO_VALIDO");
            handleActiveUsers(socket, reader, writer);
        } 
        else if (codigosValidosAdmin.contains(codigoRecibido)) {
            writer.println("CODIGO_VALIDO_ADMIN");
        }
        else {
            writer.println("CODIGO_INVALIDO");
        }
        //socket.close();
        //numUsuariosConectados--; 
        //System.out.println("Cliente desconectado");
    }
    

    public static synchronized boolean addUser(String user) {
        if (activeUsers.contains(user)) {
            return false;
        } else {
            activeUsers.add(user);
            numUsuariosConectados++;
            return true;
        }
    }

    public static synchronized String[] getActiveUsers() {
        return activeUsers.toArray(new String[activeUsers.size()]);
    }
    
    
    private void handleActiveUsers(Socket socket, BufferedReader reader, PrintWriter writer) throws IOException {
        
    	String command = reader.readLine();
        if (command != null && command.equals("ACTIVEUSERS")) {
            String[] users = getActiveUsers();
            if (users.length == 0) {
                writer.println("No hay usuarios activos aún.");
            } else {
                writer.println("Usuarios activos: " + String.join(", ", users));
            }
        }
    }
}