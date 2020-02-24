package projects.inondation.nodes.messages;

import sinalgo.nodes.messages.Message;

public class WaterMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	public WaterMessage() {
		super();
		msgId = msgCounter;
		msgCounter++;
	}

	public Message clone() {
		return new WaterMessage();
	}
	
	public String toString() {
		return "water" + msgId;
	}
}