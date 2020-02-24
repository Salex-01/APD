package projects.inondation.nodes.nodeImplementations;

import java.awt.Color;
import projects.inondation.nodes.messages.WaterMessage;

public class EndNode extends FloodNode {

	/* EndNode() { ... } */
	public void init() {
		super.init(); 
		setColor(Color.BLUE);
	}

	public String toString() {
		return super.toString() + "(end)";
	}
	
	/** when the node receives the message, it stops it! */
	@Override
	public void handleMessages(sinalgo.nodes.messages.Inbox inbox) {
		while(inbox.hasNext()){
			sinalgo.nodes.messages.Message msg = inbox.next();
			if(msg instanceof WaterMessage){ /* do nothing! */
				System.out.println(this + " received message " + msg + " and stops it!");
			}
		}
	}
}
