package projects.DM.nodes.nodeImplementations;

import java.awt.Color;
import projects.DM.nodes.messages.DMMessage;
import projects.DM.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends DMNode {

	/* InitNode() { ... } */
	public void init() {
		super.init();
		setColor(Color.CYAN);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
	}

	public void initiate() {
		DMMessage first = new DMMessage(1, tag);
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
		DMNode n = (DMNode) i.next().endNode;
		System.out.println(this + " is sending 1," + tag + " to " + n.toString());
		send(first, n);
	}

	public String toString() {
		return super.toString() + "(" + tag + ")";
	}
}
