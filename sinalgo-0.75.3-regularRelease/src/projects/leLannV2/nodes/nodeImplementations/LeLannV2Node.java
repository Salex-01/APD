package projects.leLannV2.nodes.nodeImplementations;

import java.awt.Color;
import projects.leLannV2.nodes.messages.LeLannV2Message;

public class LeLannV2Node extends sinalgo.nodes.Node {

	int minID;
	protected boolean candidate = false;
	private boolean leader = false;

	/*
	 * WalkerNode() { // no constructor code, it breaks the way sinalgo builds the
	 * nodes. // instead use the init() method }
	 */
	public void init() {
		setColor(Color.YELLOW);
		minID = this.ID;
	}

	public String toString() {
		return ID + "";
	}

	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = outgoingConnections.iterator();
		LeLannV2Node n = (LeLannV2Node) iter.next().endNode;
		while (inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if (candidate) {
				if (msg instanceof LeLannV2Message) {
					LeLannV2Message rmsg = (LeLannV2Message) msg;
					if (rmsg.getID() == this.ID) {
						leader = true;
						System.out.println(this + " is leader");
					} else {
						if (rmsg.getID() < minID) {
							if (leader) {
								System.out.println(this + " is not leader");
							}
							leader = false;
							minID = rmsg.getID();
							send(rmsg, n);
							System.out.println(this + " sent " + rmsg.getID() + " to " + n);
						}
					}
				}
			} else {
				send(msg, n);
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