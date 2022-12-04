package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8080;
        Scanner input = new Scanner(System.in);
        boolean clientStart = true;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String resp = in.readLine();
            System.out.println(resp);

            String value = input.nextLine();
            out.println(value);

            while (clientStart) {
                System.out.println("end для выхода!");
                String readText = in.readLine();
                System.out.println(readText);

                value = input.nextLine();
                out.println(value);

                assert value != null;
                if (value.equals("end")) {
                    clientStart = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
