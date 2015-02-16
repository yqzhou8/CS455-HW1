package cs455.overlay.node;

import cs455.overlay.transport.TCPConnection;

public interface Node {
	public void onEvent();
	public void registConnect(TCPConnection connect);
    public void deregistConnect(TCPConnection connect);

}
