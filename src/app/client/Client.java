package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("[CLIENT] Ви під'єдналися. Напишіть своє повідомлення: ");
            System.out.println("[CLIENT] Напишіть exit, щоб вийти: ");


            while (true) {
                String message = scanner.nextLine();
                out.println(message);

                String serverResponse = in.readLine();
                if (serverResponse != null) {
                    System.out.println("[SERVER]: " + serverResponse);
                }


                if (message.trim().equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("[CLIENT] ERROR: " + e.getMessage());
        }
    }

}
