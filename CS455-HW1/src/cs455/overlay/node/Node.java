package cs455.overlay.node;

import java.io.IOException;

import cs455.overlay.transport.TCPConnection;
import cs455.overlay.wireformats.Event;

public interface Node {
	public Event onEvent(Event e) ;
}
