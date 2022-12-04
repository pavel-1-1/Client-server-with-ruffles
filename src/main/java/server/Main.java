package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("server started");
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            boolean startServer = true;
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("новый клиент порт: " + clientSocket.getPort());

                out.println("Write your name:");
                String name = in.readLine();
                out.println("Are you child? (yes/no)");
                String youChild = in.readLine();

                if (youChild.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else if (youChild.equals("no")) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                }

                while (startServer) {
                    String value = in.readLine();
                    out.println(String.format("привет %s ", value));
                    System.out.println(value);

                    if (value.equals("end")) {
                        startServer = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
