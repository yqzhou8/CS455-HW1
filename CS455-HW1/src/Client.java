// File Name GreetingClient.java


import java.util.Scanner;
import java.io.IOException;
import java.net.Socket;

import cs455.overlay.transport.TCPReceiverThread;
import cs455.overlay.transport.TCPSender;

public class Client
{
	private Socket client;
	public void run(String serverName, int port){
		try
	      {
	         System.out.println("Connecting to " + serverName
	                             + " on port " + port);
	         client = new Socket(serverName, port);
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
	         
	         TCPSender sender = new TCPSender(client);
	         TCPReceiverThread receiver = new TCPReceiverThread(client);
	         Scanner keyboard = new Scanner(System.in);
	         String input = keyboard.nextLine();
	         while (input != null && !input.equalsIgnoreCase("quit")) {
	        	 sender.sendData(input.concat("\n").getBytes());
	        	 String message = new String(receiver.receive());
	        	 System.out.println("From server: " + message);
	        	 input = keyboard.nextLine();
	         }
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
   public static void main(String [] args)
   {
//     try
//     {
//        Thread t = new Client(port);
//        t.start();
//     }catch(IOException e)
//     {
//        e.printStackTrace();
//     }
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
      Client c = new Client();
      c.run(serverName, port);
   }
}