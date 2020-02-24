package projects.leLannV1.nodes.nodeImplementations;

import java.awt.Color;
import projects.leLannV1.nodes.messages.LeLannV1Message;
import projects.leLannV1.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends LeLannV1Node {

	/* InitNode() { ... } */
	public void init() {
		super.init();
		setColor(Color.GREEN);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
	}

	public void initiate() {
		LeLannV1Message first = new LeLannV1Message(this.ID);
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
		LeLannV1Node n = (LeLannV1Node) i.next().endNode;
		System.out.println(this + " is sending its ID to "+n.toString());
		send(first, n);
	}

	public String toString() {
		return super.toString() + "(init)";
	}
}
