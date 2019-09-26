package aima.core.environment.MisionerosCanibales;

import aima.core.search.framework.GoalTest;

/**
 * @author Ravi Mohan
 * 
 */
public class CanibalesGoalTest implements GoalTest {
	Canibales goal = new Canibales(new int[] {0,0,3,3,1});

	public boolean isGoalState(Object state) {
		Canibales board = (Canibales) state;
		return board.equals(goal);
	}

	public Canibales getGoal() {
		return goal;
	}

	public void setGoal(Canibales goal) {
		this.goal = goal;
	}
}