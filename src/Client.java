/**
 *
 * @author Khutso Elias Langa
 */
import java.util.*;
import java.io.*;
import java.net.*;

public class Client 
{
    public static void main(String[] args) throws IOException
    {
    Socket socket = new Socket("localhost", 54321);
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);


System.out.print("Enter your name: ");
String name = reader.readLine();
System.out.println("Connected!.. When done testing, Type 'exit' to leave.");

Thread inputThread = new Thread(() ->
{
    try 
    {
        String message;
               while ((message = reader.readLine()) != null)
        {
            if (message.equalsIgnoreCase("exit"))
            {
                socket.close();
                System.exit(0);
            }
            writer.println(name + ": " + message);
        }
    }
    
    catch (IOException e)
    {
        e.printStackTrace();
    }
});

inputThread.start();
BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
String serverMessage;
while((serverMessage = serverReader.readLine()) != null)
{
    System.out.println(serverMessage);
}
    }
    
}
