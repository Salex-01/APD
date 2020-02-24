package projects.leLannV2.nodes.nodeImplementations;

import java.awt.Color;
import projects.leLannV2.nodes.messages.LeLannV2Message;
import projects.leLannV2.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends LeLannV2Node {

	/* InitNode() { ... } */
	public void init() {
		super.init();
		setColor(Color.GREEN);
		candidate = true;
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
	}

	public void initiate() {
		LeLannV2Message first = new LeLannV2Message(this.ID);
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
		LeLannV2Node n = (LeLannV2Node) i.next().endNode;
		System.out.println(this + " is sending its ID to "+n.toString());
		send(first, n);
	}

	public String toString() {
		return super.toString() + "(init)";
	}
}
