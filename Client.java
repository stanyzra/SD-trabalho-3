package trabalho_3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            System.out.println("host: " + host);
            if (host == null) {
                System.out.println("Provided null host, using default host: 1099");
                host = "1099";
            }
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(host));
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}