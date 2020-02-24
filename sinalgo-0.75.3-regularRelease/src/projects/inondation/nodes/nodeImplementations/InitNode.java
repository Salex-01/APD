package projects.inondation.nodes.nodeImplementations;

import java.awt.Color;
import projects.inondation.nodes.messages.WaterMessage;
import projects.inondation.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends FloodNode {

	/* InitNode() { ... } */
	public void init() {
		super.init();
		setColor(Color.GREEN);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
	}

	public void initiate() {
		WaterMessage droplet = new WaterMessage();
		System.out.println(this + " is sending now message " + droplet);
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
		while (i.hasNext()) {
			send(droplet, i.next().endNode);
		}
	}

	public String toString() {
		return super.toString() + "(init)";
	}
}
