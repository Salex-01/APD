/* TODO */

package projects.leLannV2;

import projects.leLannV2.nodes.nodeImplementations.LeLannV2Node;

import java.util.Random;

import projects.leLannV2.nodes.nodeImplementations.InitNode;
import sinalgo.nodes.Node;
import sinalgo.runtime.AbstractCustomGlobal;

/**
 * This class holds customized global state and methods for the framework. The
 * only mandatory method to overwrite is <code>hasTerminated</code> <br>
 * Optional methods to override are
 * <ul>
 * <li><code>customPaint</code></li>
 * <li><code>handleEmptyEventQueue</code></li>
 * <li><code>onExit</code></li>
 * <li><code>preRun</code></li>
 * <li><code>preRound</code></li>
 * <li><code>postRound</code></li>
 * <li><code>checkProjectRequirements</code></li>
 * </ul>
 * 
 * @see sinalgo.runtime.AbstractCustomGlobal for more details. <br>
 *      In addition, this class also provides the possibility to extend the
 *      framework with custom methods that can be called either through the menu
 *      or via a button that is added to the GUI.
 */
public class CustomGlobal extends AbstractCustomGlobal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see runtime.AbstractCustomGlobal#hasTerminated()
	 */
	public boolean hasTerminated() {
		return false;
	}

	/** Button to create a ring network. */
	@AbstractCustomGlobal.CustomButton(buttonText = "Build a Ring Network")
	public void ringButton() {
		buildRing(15);
	}

	private void addEdge(Node from, Node to) {
		from.addConnectionTo(to);
	}

	private void buildRing(int numOfNodes) {
		Random r = new Random();
		sinalgo.tools.Tools.removeAllNodes();

		// nodes
		LeLannV2Node[] theNodes = new LeLannV2Node[numOfNodes];

		// the center
		double centerPosX = sinalgo.configuration.Configuration.dimX / 2;
		double centerPosY = sinalgo.configuration.Configuration.dimY / 2;

		// the ring...
		double initAngle = 2 * Math.PI / numOfNodes;
		double range = 200.0;
		double angle = 0;
		for (int i = 0; i < numOfNodes; i++) {
			double posX = centerPosX + range * Math.cos(angle);
			double posY = centerPosY + range * Math.sin(angle);
			LeLannV2Node node;
			if (r.nextInt() % 2 == 0) {
				node = new InitNode();
			} else {
				node = new LeLannV2Node();
			}
			node.setPosition(posX, posY, 0);
			node.finishInitializationWithDefaultModels(true);
			if (i > 0)
				addEdge(theNodes[i - 1], node);
			theNodes[i] = node;
			angle += initAngle;
		}
		addEdge(theNodes[numOfNodes - 1], theNodes[0]);
		// Repaint the GUI as we have added some nodes
		sinalgo.tools.Tools.repaintGUI();
	}
}