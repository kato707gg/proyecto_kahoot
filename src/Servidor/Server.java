package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Server {
    /*
    private static final int PORT = 5000;
    private static final String CODIGOS_VALIDOS_FILE = "src/resources/codigos_validos.txt";
    private static final String CODIGOS_VALIDOS_ADMIN_FILE = "src/resources/codigos_validos_admin.txt";
    private static final int NUM_JUGADORES_INICIO = 3;

    private static boolean juegoIniciado = false;
    private static boolean esAdmin = false;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }
        }
    }
    
    public static void handleClient(Socket socket) throws IOException {
        
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
            esAdmin = false;
            handleActiveUsers(socket, reader, writer);
        } 
        else if (codigosValidosAdmin.contains(codigoRecibido)) {
            writer.println("CODIGO_VALIDO_ADMIN");
            esAdmin = true;
        }
        else {
            writer.println("CODIGO_INVALIDO");
        }
        socket.close();
        System.out.println("Cliente desconectado");
    }
    
    private static List<String> activeUsers = new ArrayList<String>();

    public static synchronized boolean addUser(String user) {
        if (activeUsers.contains(user)) {
            return false;
        } else {
            activeUsers.add(user);
            return true;
        }
    }

    public static synchronized String[] getActiveUsers() {
        return activeUsers.toArray(new String[activeUsers.size()]);
    }
    
    
    private static void handleActiveUsers(Socket socket, BufferedReader reader, PrintWriter writer) throws IOException {
        
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
    */
}
