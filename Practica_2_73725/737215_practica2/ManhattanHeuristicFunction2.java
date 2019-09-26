package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Ravi Mohan
 * 
 */
public class ManhattanHeuristicFunction2 implements HeuristicFunction {

	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		EightPuzzleBoard finalBoard = EightPuzzleGoalTest2.getFinalState();
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation locAct = board.getLocationOf(i);
			XYLocation locFin = finalBoard.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(locAct, locFin);
		}
		return retVal;
	}

	// Se ha modificado la heuristica para poder aceptar el estado aleatorio objetivo
	public int evaluateManhattanDistanceOf(XYLocation locI, XYLocation locF) {
		int retVal = -1;
		int xposI = locI.getXCoOrdinate();
		int yposI = locI.getYCoOrdinate();
		int xposF = locF.getXCoOrdinate();
		int yposF = locF.getYCoOrdinate();
		
		retVal = Math.abs(xposI - xposF) + Math.abs(yposI - yposF); 
		
		return retVal;
	}
}