package app.server;

import java.net.Socket;

public class Connection {

    private Socket clientSocket;
    private String clientName;
    private String connectTime;

    public Connection(Socket clientSocket, String clientName, String connectTime) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
        this.connectTime = connectTime;
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public String getClientName() {
        return clientName;
    }

    public String getConnectTime() {
        return connectTime;
    }


}
