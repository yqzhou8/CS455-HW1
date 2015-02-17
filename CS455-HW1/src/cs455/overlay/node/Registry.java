package cs455.overlay.node;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import cs455.overlay.transport.TCPConnection;
import cs455.overlay.transport.TCPReceiverThread;
import cs455.overlay.transport.TCPSender;
import cs455.overlay.transport.TCPServerThread;
import cs455.overlay.wireformats.Event;

// only 1 registry in system
public class Registry implements Node {

	TCPConnection connection;

	public Registry(int port) {
		connection = new TCPConnection(this,port);
	}

	public int getRand() {
		Random rand = new Random();
		// this will give 0 - 127
		int value = rand.nextInt(128);
		return value;
	}

	public void deregister() {

	}

	public void setOverlay() {

	}
	
	@Override
	public Event onEvent(Event event) {
		return null;
	}

	public void start(){
		connection.run();
	}
	public static void main(String [] args)
	{
		if (args.length == 0) {
            System.out.println("Port number is not specified!");
            return;
        }
		int port = Integer.parseInt(args[0]);
		Registry r = null;
		r = new Registry(port);
		r.start();
		
	}

}
