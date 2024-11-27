package telran.employees.client;

import telran.net.TCPClient;
import telran.net.exceptions.ServerUnavailableException;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

import java.io.IOException;

public class Client
{
    private static TCPClient client = null;
    private int current_port = 0;

    public static void main(String[] args)
    {
        new Client();
    }

    private Client()
    {
        try {
            Item[] items = {
                    Item.of("Start session", io -> {
                        try {
                            startSession(io);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }),
                    Item.of("Exit", _ -> {
                        try {
                            exit();
                        } catch (ServerUnavailableException e) {
                            System.out.println(e.getMessage());
                            exit();
                        }
                    })
            };
            Menu menu = new Menu("Echo Application", items);
            menu.perform(new StandardInputOutput());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void exit()
    {
        System.exit(0);
    }

    private void startSession(InputOutput io) throws IOException
    {
        if (client != null) {
            client.close();
        }
        for (int port = Settings.PORT_FROM; port <= Settings.PORT_TO; port++) {
            try {
                client = new TCPClient(Settings.host, port);
                System.out.println("Connected to server " + Settings.host + " on port " + port);
                this.current_port = port;
                break;
            } catch (ServerUnavailableException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (client == null) {
            throw new ServerUnavailableException("Unable to connect to server " + Settings.host, Settings.PORT_TO);
        }
        ClientOperations co = new ClientOperations(this, io);
        co.createMenu();
    }

    public TCPClient getClient()
    {
        return client;
    }

    public int getCurrentPort()
    {
        return this.current_port;
    }

}
