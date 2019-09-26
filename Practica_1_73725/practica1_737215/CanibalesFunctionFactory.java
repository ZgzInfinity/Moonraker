package aima.core.environment.MisionerosCanibales;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class CanibalesFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			Canibales board = (Canibales) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.poderMover(Canibales.M1M)) {
				actions.add(Canibales.M1M);
			}
			if (board.poderMover(Canibales.M2M)) {
				actions.add(Canibales.M2M);
			}
			if (board.poderMover(Canibales.M1C)) {
				actions.add(Canibales.M1C);
			}
			if (board.poderMover(Canibales.M2C)) {
				actions.add(Canibales.M2C);
			}
			if (board.poderMover(Canibales.M1M1C)) {
				actions.add(Canibales.M1M1C);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Canibales board = (Canibales) s;

			if (Canibales.M1M.equals(a)
					&& board.poderMover(Canibales.M1M)) {
				Canibales newBoard = new Canibales(board);
				newBoard.moverM();
				return newBoard;
			} else if (Canibales.M2M.equals(a)
					&& board.poderMover(Canibales.M2M)) {
				Canibales newBoard = new Canibales(board);
				newBoard.moverMM();
				return newBoard;
			} else if (Canibales.M1C.equals(a)
					&& board.poderMover(Canibales.M1C)) {
				Canibales newBoard = new Canibales(board);
				newBoard.moverC();
				return newBoard;
			} else if (Canibales.M2C.equals(a)
					&& board.poderMover(Canibales.M2C)) {
				Canibales newBoard = new Canibales(board);
				newBoard.moverCC();
				return newBoard;
		    } else if (Canibales.M1M1C.equals(a)
		    		&& board.poderMover(Canibales.M1M1C)) {
		    	Canibales newBoard = new Canibales(board);
		    	newBoard.moverMC();
		    	return newBoard;
		    }
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}