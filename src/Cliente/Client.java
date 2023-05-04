package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    public String enviarCodigo(String codigo) {
        String response = "";

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            // Envía el código al servidor
            out.println(codigo);

            // Espera la respuesta del servidor
            response = in.readLine();
        } catch (IOException e) {
            System.err.println("Error al enviar el código al servidor: " + e.getMessage());
        }

        return response;
    }

    public String[] obtenerListaUsuarios() {
        String[] listaUsuarios = new String[0];

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            // Envía la solicitud al servidor
            out.println("LISTA_USUARIOS");

            // Espera la respuesta del servidor
            String response = in.readLine();
            if (response.startsWith("USUARIOS_ACTIVOS")) {
                listaUsuarios = response.substring("USUARIOS_ACTIVOS ".length()).split(",");
            } else {
                System.err.println("El servidor no devolvió una lista de usuarios activos");
            }
        } catch (IOException e) {
            System.err.println("Error al obtener la lista de usuarios del servidor: " + e.getMessage());
        }

        return listaUsuarios;
    }
}