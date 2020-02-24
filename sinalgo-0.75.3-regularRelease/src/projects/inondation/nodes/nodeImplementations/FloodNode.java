package projects.inondation.nodes.nodeImplementations;

import java.awt.Color;
import projects.inondation.nodes.messages.WaterMessage;

public class FloodNode extends sinalgo.nodes.Node {

	private boolean visited = false;
	
	/*
	 * WalkerNode() { // no constructor code, it breaks the way sinalgo builds the
	 * nodes. // instead use the init() method }
	 */
	public void init() {
		setColor(Color.YELLOW);
	}

	public String toString() {
		return " " + ID + " ";
	}

	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		while (inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if(!visited) {
				if (msg instanceof WaterMessage) {
					WaterMessage droplet = (WaterMessage) msg;
					setColor(Color.CYAN);
					sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> i = outgoingConnections.iterator();
					while (i.hasNext()) {
						send(droplet, i.next().endNode);
					}
					System.out.println(this + " received message " + droplet + " and sent it to all its neighbors");
				}
				visited = true;
			}
		}
	}

	public void preStep() {
	};

	public void neighborhoodChange() {
	};

	public void postStep() {
	};

	public void checkRequirements() throws sinalgo.configuration.WrongConfigurationException {
	};

	public void draw(java.awt.Graphics g, sinalgo.gui.transformation.PositionTransformation pt, boolean highlight) {
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, toString(), 20, Color.black);
	}
}