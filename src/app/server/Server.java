package app.server;

import app.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private static final int PORT = 8080;
    private static AtomicInteger clientCounter = new AtomicInteger(1);

    public static List<Connection> connections = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("[SERVER] Сервер запущено на порту " + PORT);

                Socket clientSocket = serverSocket.accept();
                String clientName = "client-" + clientCounter.incrementAndGet();
                String connectTime = LocalDateTime.now().toString();

                System.out.println("[SERVER] " + clientName + "успішно підключився о " + connectTime);
                connections.add(new Connection(clientSocket, clientName, connectTime));

                new Thread(new ClientHandler(clientSocket, clientName, connectTime)).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
