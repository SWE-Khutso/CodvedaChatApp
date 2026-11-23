/**
 *
 * @author Khutso Elias Langa
 */
import java.util.*;
import java.io.*;
import java.net.*;

public class Server 
{
   static final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
   
    public static void main(String[] args) throws IOException 
    {
    ServerSocket serverSocket = new ServerSocket(54321);
        System.out.println("Server has started... ");
        
        while (true)
        {
            Socket socket = serverSocket.accept();
           
            System.out.println("New client connected: " + socket);
            
            ClientHandler clientHandler = new ClientHandler(socket);
            clients.add(clientHandler);
            clientHandler.start(); 
        }
    }
       static void broadcast(String message, ClientHandler sender)
    {
        synchronized(clients)
        {
            for (ClientHandler client : clients)
            {
                if (client != sender)
                {
                    client.send(message);
                }
            }
        }
    } 
}

class ClientHandler extends Thread
{
    private Socket socket;
    private PrintWriter writer;
    
    public ClientHandler(Socket socket)
    {
        this.socket = socket;
    }
    
        public void send(String message)
    {
        if (writer != null)
        {
            writer.println(message);
        }
    }
        
    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            
            String message;
            while((message = reader.readLine()) != null)
            {
                System.out.println("Message Recieved message... " + message);
                Server.broadcast(message, this);
            }
        }
        
        catch (IOException e)
        {
        System.out.println("A client connection was lost.");
        }
        finally
        {
            Server.clients.remove(this);
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("Client disconnected...");
        }
    }
}