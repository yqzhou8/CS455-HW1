// File Name GreetingServer.java


import java.net.*;

import cs455.overlay.transport.TCPReceiver;
import cs455.overlay.transport.TCPSender;

public class Server
{
   private ServerSocket serverSocket;
   
   private Server(int port){
	   try{
	    	 serverSocket = new ServerSocket(port);
	   }catch (Exception e) {
//	        System.out.println(e.getMessage());
	       System.out.println("test");
	   } 
   }
   
   public void run(int port){
	   try{
		   while (true) {
			   Socket socket = serverSocket.accept();
	    			while (socket.isConnected()) {
	    				TCPReceiver receiver = new TCPReceiver(socket);
	    				TCPSender sender = new TCPSender(socket);
	    				byte[] rawBytes = receiver.receive();
	    				String message = new String(rawBytes);
	    				message = message.replaceAll(" ", "_");
	    				sender.sendData(message.getBytes());
	    			}
	    			serverSocket.close();
		   }

	   }catch (Exception e) {
//	       	 System.out.println(e.getMessage());
		   System.out.println("server " + port + " is closed");
	   }
	      
   }
   public static void main(String [] args)
   {
	   
      int port = Integer.parseInt(args[0]);
      Server s = new Server(port);
      s.run(port);
   }
   
}