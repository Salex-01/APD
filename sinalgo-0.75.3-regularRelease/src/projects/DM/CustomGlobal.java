/* TODO */

package projects.DM;

import java.util.Random;

import projects.DM.nodes.nodeImplementations.DMNode;
import projects.DM.nodes.nodeImplementations.InitNode;
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

	private enum M {
		ASC, DESC, ADAD, DADA, RAND
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see runtime.AbstractCustomGlobal#hasTerminated()
	 */
	public boolean hasTerminated() {
		return false;
	}

	/** Button to create a ring network. */

	// 1 Node
	@AbstractCustomGlobal.CustomButton(buttonText = "1 Node")
	public void ringButton1() {
		buildRing(1, M.RAND);
	}

	// 2 Nodes
	@AbstractCustomGlobal.CustomButton(buttonText = "2 Nodes")
	public void ringButton2() {
		buildRing(2, M.RAND);
	}

	// 3 Nodes
	@AbstractCustomGlobal.CustomButton(buttonText = "3 Nodes ascending")
	public void ringButton3() {
		buildRing(3, M.ASC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "3 Nodes descending")
	public void ringButton4() {
		buildRing(3, M.DESC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "3 Nodes +-+-")
	public void ringButton5() {
		buildRing(3, M.ADAD);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "3 Nodes -+-+")
	public void ringButton6() {
		buildRing(3, M.DADA);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "3 Nodes random")
	public void ringButton7() {
		buildRing(3, M.RAND);
	}

	// 4 Nodes
	@AbstractCustomGlobal.CustomButton(buttonText = "4 Nodes ascending")
	public void ringButton8() {
		buildRing(4, M.ASC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "4 Nodes descending")
	public void ringButton9() {
		buildRing(4, M.DESC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "4 Nodes +-+-")
	public void ringButton10() {
		buildRing(4, M.ADAD);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "4 Nodes -+-+")
	public void ringButton11() {
		buildRing(4, M.DADA);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "4 Nodes random")
	public void ringButton12() {
		buildRing(4, M.RAND);
	}

	// 5 Nodes
	@AbstractCustomGlobal.CustomButton(buttonText = "5 Nodes ascending")
	public void ringButton13() {
		buildRing(5, M.ASC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "5 Nodes descending")
	public void ringButton14() {
		buildRing(5, M.DESC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "5 Nodes +-+-")
	public void ringButton15() {
		buildRing(5, M.ADAD);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "5 Nodes -+-+")
	public void ringButton16() {
		buildRing(5, M.DADA);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "5 Nodes random")
	public void ringButton17() {
		buildRing(5, M.RAND);
	}

	// 10 Nodes
	@AbstractCustomGlobal.CustomButton(buttonText = "10 Nodes ascending")
	public void ringButton18() {
		buildRing(10, M.ASC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "10 Nodes descending")
	public void ringButton19() {
		buildRing(10, M.DESC);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "10 Nodes +-+-")
	public void ringButton20() {
		buildRing(10, M.ADAD);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "10 Nodes -+-+")
	public void ringButton21() {
		buildRing(10, M.DADA);
	}

	@AbstractCustomGlobal.CustomButton(buttonText = "10 Nodes random")
	public void ringButton22() {
		buildRing(10, M.RAND);
	}

	private void addEdge(Node from, Node to) {
		from.addConnectionTo(to);
	}

	private void buildRing(int numOfNodes, M mode) {

		Random r = new Random();
		int randBound = 10000;
		int[] IDs = new int[10];

		sinalgo.tools.Tools.removeAllNodes();

		// nodes
		DMNode[] theNodes = new DMNode[numOfNodes];

		// the center
		double centerPosX = sinalgo.configuration.Configuration.dimX / 2;
		double centerPosY = sinalgo.configuration.Configuration.dimY / 2;

		// the ring...
		double initAngle = 2 * Math.PI / numOfNodes;
		double range = 350.0;
		double angle = 0;

		int nID = 0;
		switch (mode) {
		case ADAD:
			nID = -10;
			break;
		case DADA:
			nID = 10;
			break;
		case RAND:
			nID = r.nextInt() % randBound;
			break;
		default:
			break;
		}

		for (int i = 0; i < numOfNodes; i++) {
			double posX = centerPosX + range * Math.cos(angle);
			double posY = centerPosY + range * Math.sin(angle);
			DMNode node;
			node = new InitNode();

			switch (mode) {
			case ASC:
				node.ID = nID;
				nID++;
				break;
			case DESC:
				node.ID = nID;
				nID--;
				break;
			case ADAD:
				node.ID = nID;
				nID = (int) (nID * (-1.5));
				break;
			case DADA:
				node.ID = nID;
				nID = (int) (nID * (-1.5));
				break;
			case RAND:
				IDs[i] = nID;
				node.ID = nID;
				do {
					nID = r.nextInt() % randBound;
				} while (contains(IDs, nID, i));
				break;
			default:
				System.err.println("Unexpected mode in ring creation");
				break;
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

	private boolean contains(int[] IDs, int nID, int b) {
		for (int i = 0; i <= b; i++) {
			if (IDs[i] == nID) {
				return true;
			}
		}
		return false;
	}
}