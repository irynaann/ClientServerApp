package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {


    private Socket clientSocket;
    private String clientName;

    public ClientHandler(Socket clientSocket, String clientName) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }


    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream());

        ) {
            output.println("Привіт, " + clientName + "! Напиши exit щоб вийти");

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("[SERVER] " + clientName + ": " + message);

                if (message.equalsIgnoreCase("exit")) {
                    output.println("Бувай!");
                    System.out.println("[SERVER] " + clientName + " відключився");
                    break;
                }

                output.println("Сервер отримав: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
