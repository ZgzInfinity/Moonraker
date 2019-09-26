package aima.gui.demo.search;

import java.util.List;
import aima.core.agent.Action;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.SearchAgent;
 
// Definicion de una clase generica que tiene el metodo generico executeActions
// Muestra los pasos intermedios hasta alcanzar una solución acertada para cualquier juego

public class ClaseGenerica {

	// Método execute_actions del ejercicio 2
	public static void executeActions(List<Action> actions, Problem problem) {
		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();
		Object state = initialState;   
		System.out.println("ESTADO INICIAL");  
		System.out.printf("%86s\n", state); 
		for (Action action : actions) {    
			state = resultFunction.result(state, action);     
			System.out.printf("%20s  %30s\n", action.toString(), state);      
		} 
	}
}