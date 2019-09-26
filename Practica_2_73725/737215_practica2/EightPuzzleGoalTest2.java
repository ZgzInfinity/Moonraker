package aima.core.environment.eightpuzzle;


import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Search;


/**
 * @author Ravi Mohan
 * 
 */

// Generamos un 
public class EightPuzzleGoalTest2 implements GoalTest{
	// Atributo estático para almacenar el estado aleatorio
	private static EightPuzzleBoard goal;
	
	/*
	 * Pre: ---
	 * Post: Devuelve true si y solo si <<state>> es el 
	 * 		 estado objetivo
	 */
	public boolean isGoalState(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return board.equals(goal);
	}

	/*
	 * Pre: ---
	 * Post: Devuelve el estado objetivo 
	 */
	public static EightPuzzleBoard getFinalState() {
		return goal;
	}


	/*
	 * Pre: <<estadoFinal>> es un estado del problema 8-puzzle
	 * Post: El estado objetivo toma el valor de <<estadoFinal>>
	 */
	public static void setFinalState(EightPuzzleBoard estadoFinal) {
		goal = estadoFinal;
	}
	
	
}