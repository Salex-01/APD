package projects.DM.nodes.nodeImplementations;

import java.awt.Color;

import projects.DM.nodes.messages.DMMessage;

public class DMNode extends sinalgo.nodes.Node {

	boolean output;
	State state;
	int tag;
	int v1;
	int v2;
	
	private enum State{
		ACTIVE,
		PASSIVE;
	};

	/*
	 * WalkerNode() { // no constructor code, it breaks the way sinalgo builds the
	 * nodes. // instead use the init() method }
	 */
	public void init() {
		setColor(Color.YELLOW);
		output = false;
		state = State.ACTIVE;
		tag = this.ID;
	}

	public String toString() {
		return Integer.toString(this.ID);
	}

	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		while (inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if (msg instanceof DMMessage) {
				DMMessage rmsg = (DMMessage) msg;
				if (state == State.PASSIVE) {
					DMNode n = (DMNode) outgoingConnections.iterator().next().endNode;
					System.out.println(this + " sends " + rmsg.toString() + " to " + n.toString());
					send(rmsg, n);
				} else if (rmsg.getRank() == 1) {
					forOne(rmsg.getID());
				} else {
					forTwo(rmsg.getID());
				}
			}
		}
	}

	private void forOne(int t) {
		if (t == tag) {
			setColor(Color.GREEN);
			output = true;
			System.out.println(this + " is elected");
		} else {
			v1 = t;
			DMMessage rmsg = new DMMessage(2, t);
			DMNode n = (DMNode) outgoingConnections.iterator().next().endNode;
			System.out.println(this + " sends " + rmsg.toString() + " to " + n.toString());
			send(rmsg, n);
		}
	}

	private void forTwo(int t) {
		v2 = t;
		if ((v1 < v2) && (v1 < tag)) {
			tag = v1;
			DMMessage rmsg = new DMMessage(1, tag);
			DMNode n = (DMNode) outgoingConnections.iterator().next().endNode;
			System.out.println(this + " sends " + rmsg.toString() + " to " + n.toString());
			send(rmsg, n);
		} else {
			setColor(Color.ORANGE);
			state = State.PASSIVE;
			System.out.println(this + " is inactive");
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