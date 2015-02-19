package cs455.overlay.node;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import cs455.overlay.transport.TCPConnection;
import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.OverlayNodeSendsRegistration;
import cs455.overlay.wireformats.Protocol;

// 10 messagingnodes in system
public class MessagingNode extends Thread implements Node {
	String registry_host;
	int registry_port;
	int messaging_port;
	byte[] address;
	TCPConnection connection;
	Socket socket; 
	

	// initiates and accepts both communications and messages

	// send a registration request to registry
	//private Queue<OverlayNodeSendsData> relayQueue = new LinkedList<OverlayNodeSendsData>();

	public MessagingNode(String registryHost, int registryPort) throws IOException {
		// TODO Auto-generated constructor stub
		this.registry_host = registryHost;
		this.registry_port = registryPort;
		this.socket = new Socket();
		connection = new TCPConnection(this, socket);
		this.messaging_port = connection.getPort();
		this.address  = connection.getAddress();
	}

	// send data
	public void start(){
		this.connection.start();
	}
	
	@Override
	public Event onEvent(Event e) {
//		//...
//		case Protocol.OVERLAY_NODE_SENDS_DATA:
//		 // if the destination is the current node
//		 //...
//		 // if the message should be relayed
//		 synchronized (relayQueue){
//		relayQueue.add(sendData);
//		}
//		 break;
		return null;
	}

	public void run(){
//		 while(true){
//			 OverlayNodeSendsData relayMsg;
//			 synchronized (relayQueue){
//				 relayMsg = relayQueue.poll();
//			 }
//			 if (relayMsg != null){
//				 try {
//					 byte[] bytesToSend = relayMsg.getBytes();
//					 // send the bytes through the appropriate TCPSender
//					 // Increment the relay counter
//				 } catch (IOException e) {
//					 System.err.println(e.getMessage());
//				 }
//			}
//		}
	}
	
	private boolean register() throws IOException {
		OverlayNodeSendsRegistration regReq = new OverlayNodeSendsRegistration();
		regReq.setMessIP(this.address);
		regReq.setPort(this.messaging_port);
		Registry registry = new Registry(this.registry_host,this.registry_port);
		//now registry need to send message
		//registry get response
		//if the response instanceof RegistryReportsRegistrationStatus
		// if it is suuccess
		// return true, else false
		return false;
	}
	
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("need registry host and port!");
			return;
		}
		
		String registryHost = args[0];
		int registryPort= Integer.parseInt(args[1]);
		
		MessagingNode messNode = null;
		try{
			messNode = new MessagingNode(registryHost, registryPort);
		}catch(IOException e){
			System.out.println("cannot create a messaging node");
			System.out.println(e.getMessage());
		}
		
		messNode.start();
		System.out.println("Messaging node now starting on port: " + messNode.messaging_port);
		
		boolean registrySuccess = false;
		try {
			registrySuccess = messNode.register();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(registrySuccess == false){
			System.out.println("Registration failed");
			return;
		}else{
			System.out.println("Yeah! Registration is successful!");
		}
	}
}
