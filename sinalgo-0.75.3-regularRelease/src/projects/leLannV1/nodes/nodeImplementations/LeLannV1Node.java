package projects.leLannV1.nodes.nodeImplementations;

import java.awt.Color;
import java.util.LinkedList;

import projects.leLannV1.nodes.messages.LeLannV1Message;

public class LeLannV1Node extends sinalgo.nodes.Node {

	LinkedList<Integer> IDs = new LinkedList<Integer>();
	boolean leader = false;

	/*
	 * WalkerNode() { // no constructor code, it breaks the way sinalgo builds the
	 * nodes. // instead use the init() method }
	 */
	public void init() {
		setColor(Color.YELLOW);
		IDs.add(this.ID);
	}

	public String toString() {
		return ID + "";
	}

	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		while (inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if (msg instanceof LeLannV1Message) {
				LeLannV1Message rmsg = (LeLannV1Message) msg;
				if (rmsg.getID() != this.ID) {
					sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = outgoingConnections.iterator();
					LeLannV1Node n = (LeLannV1Node) iter.next().endNode;
					send(rmsg, n);
					System.out.println(this + " received message " + rmsg + " and sent it to " + n.toString());
					IDs.add(rmsg.getID());
				} else {
					int minID = this.ID;
					for (int i = 0; i < IDs.size(); i++) {
						minID = Math.min(minID, IDs.get(i));
					}
					leader = (minID == this.ID);
					System.out.println(
							this + " received message with its own ID and performed leadership check : " + leader);
				}

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