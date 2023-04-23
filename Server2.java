package trabalho_3;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server2 implements Hello {

    private int port;
    private String serverName;

    public Server2(int port, String serverName) {
        this.port = port;
        this.serverName = serverName;
    }

    public String sayHello() {
        return "Hello from server 2!";
    }

    public int getPort() {
        return port;
    }

    public String getServerName() {
        return serverName;
    }

    public static void main(String args[]) {
        int port = 1101;

        try {
            LocateRegistry.createRegistry(port);
            Server2 obj = new Server2(port, "Server 2");
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry(port);
            registry.bind("Hello", stub);

            System.err.println("Server 2 is running on port " + port);
        } catch (Exception e) {
            if (!(e instanceof java.rmi.server.ExportException
                    && e.getMessage().contains("Port already in use"))) {
                e.printStackTrace();
            }
            System.err.println("Server 2 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}