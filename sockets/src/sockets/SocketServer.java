package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigInteger;

public class SocketServer {

    public static void main(String args[]) throws IOException {

        BufferedReader entrada = null;
        PrintWriter salida = null;

        Socket socket = null;
        //se crea una instancia de ServerSocket que estara atendiendo en el puerto 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Esperando conexion de cliente en el puerto 1234...");

        while (true) {
            try {
                //el ServerSocket da el acceso Socket al cliente que lo solicito
                socket = serverSocket.accept();

                //se obtiene informacion(IP) del cliente
                System.out.println("Conexion establecida desde la IP: " + socket.getInetAddress() + " Puerto: " + socket.getLocalPort());

                //obtengo la entrada y la salida de bytes 
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                //leo el nombre que envia el cliente

                String numero = entrada.readLine();
                int n = Integer.parseInt(numero);
                System.out.println(numero);

                double resultado = 1;
                for (int i = 1; i <= n; i++) {
                    resultado *= i;
                }

                String mensaje = Double.toString(resultado);
                System.out.println("El número es: ");
                salida.println(mensaje);

                socket.close();

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
