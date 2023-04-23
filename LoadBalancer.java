package trabalho_3;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoadBalancer implements Hello, Serializable {

    private int currentIndex;
    private final int[] serverPorts;

    public LoadBalancer(int[] serverPorts) {
        this.currentIndex = 0;
        this.serverPorts = serverPorts;
    }

    public int getNextServer() {
        System.out.println("Getting next server");
        int offlineServersCount = 0;
        int nextServerIndex = currentIndex;
        while (offlineServersCount < serverPorts.length) {
            nextServerIndex = (nextServerIndex + 1) % serverPorts.length;
            if (checkServer(serverPorts[nextServerIndex])) {
                currentIndex = nextServerIndex;
                return serverPorts[nextServerIndex];
            }
            offlineServersCount++;
        }
        return -1;
    }

    public boolean checkServer(int port) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", port);
            Hello server = (Hello) registry.lookup("Hello");
            server.sayHello();
            return true;
        } catch (Exception e) {
            System.out.println("Server on port " + port + " is not available");
            return false;
        }
    }

    public String sayHello() {
        int port = serverPorts[currentIndex];
        if (!checkServer(port))
            port = getNextServer();

        if (port == -1)
            return "No servers available";

        System.out.println("Sending request to server on port " + port);

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", port);
            Hello server = (Hello) registry.lookup("Hello");
            return server.sayHello();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        int port = 1099;

        try {
            LocateRegistry.createRegistry(port);
            int[] serverNames = { 1100, 1101 };
            LoadBalancer lb = new LoadBalancer(serverNames);

            Registry registry = LocateRegistry.getRegistry(port);

            registry.bind("Hello", lb);

            System.out.println("Load Balancer running on port " + port);
            // Loop infinito para manter o servidor do LoadBalancer em execução
            while (true) {
                Thread.sleep(1000); // Aguarda 1 segundo antes de verificar novamente
            }
        } catch (Exception e) {
            System.err.println("Load Balancer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
