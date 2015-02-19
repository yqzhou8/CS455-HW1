package cs455.overlay.node;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import cs455.overlay.transport.TCPConnection;
import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.OverlayNodeSendsRegistration;
import cs455.overlay.wireformats.RegistryReportsRegistrationStatus;

// only 1 registry in system
public class Registry implements Node {

	TCPConnection connection;
	Socket socket;
	String host;
	int port;

	public Registry(Node node, Socket socket) throws IOException {
		connection = new TCPConnection(this,socket);
		new OverlayNodeSendsRegistration();
		new RegistryReportsRegistrationStatus();
	}
	
	public Registry(String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		setupSocket();
		connection = new TCPConnection(this,port);
		new OverlayNodeSendsRegistration();
		new RegistryReportsRegistrationStatus();
		start();
	}
	
	public void setupSocket() throws UnknownHostException, IOException{
		socket = new Socket(host,port);
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
		System.out.println("Event received!");
		RegistryReportsRegistrationStatus resp = new RegistryReportsRegistrationStatus();
		resp.Success_status = 1;
		resp.Information_string = "Registration Succeful".getBytes();
		return resp;
	}

	public void start(){
		connection.start();
	}
	
	public static void main(String [] args)
	{
		if (args.length == 0) {
            System.out.println("Port number is not specified!");
            return;
        }
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		Registry r = null;;
		try {
            r = new Registry(host,port);
        } catch (IOException e) {
            System.out.println("Error starting the registry.");
            System.out.println(e.getMessage());
        }
        r.start();
      System.out.println("Registry is started up and listening on port " + port);
   }
		

}
