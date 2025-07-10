package app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static app.server.Server.connections;

public class ClientHandler implements Runnable {


    private Socket clientSocket;
    private String clientName;
    private String connectTime;

    public ClientHandler(Socket clientSocket, String clientName, String connectTime) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }


    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        ) {
            output.println("Привіт, " + clientName + "! Напишіть своє повідомлення або exit щоб вийти");

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("[SERVER] " + clientName + ": " + message);

                if (message.equalsIgnoreCase("exit")) {
                    output.println("Бувай!");
                    System.out.println("[SERVER] " + clientName + " відключився");
                    connections.removeIf(c -> c.getClientName().equals(clientName));
                    break;
                }

                output.println("Сервер отримав: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
