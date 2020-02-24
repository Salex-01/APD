package projects.leLannV2.nodes.messages;

import sinalgo.nodes.messages.Message;

public class LeLannV2Message extends Message {

	private static int msgCounter = 0;
	private int msgId;
	private int procID;
	
	public LeLannV2Message(int n) {
		super();
		msgId = msgCounter;
		msgCounter++;
		procID = n;
	}
	
	public int getID() {
		return procID;
	}

	public Message clone() {
		return new LeLannV2Message(procID);
	}
	
	public String toString() {
		return "LeLannMessage" + msgId;
	}
}