package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.MisionerosCanibales.Canibales;
import aima.core.environment.MisionerosCanibales.CanibalesFunctionFactory;
import aima.core.environment.MisionerosCanibales.CanibalesGoalTest;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author Rubén Rodríguez Esteban 
 * @nip 737215
 * 
 */

public class CanibalesDemo extends ClaseGenerica {
	static Canibales tablero = new Canibales(
			new int[] { 3,3,0,0,0 });

	public static void main(String[] args) {
		
		// Conjunto de pruebas para verificar el comportamiento del
		// problema de los misioneros y los caníbales
		
		
		// Algoritmo de busqueda de primero en anchura
		MisionerosCanibalesBreadthFisrtSearchDemo();
		
		// Algoritmo de busqueda en profundidad iterativa con limite
		MisionerosCanibalesDepthLimitedSearchDemo();
		
		// Algoritmo de busqueda iterativa en profundidad
		MisionerosCanibalesIterativeDeepeningSearchDemo();
	}

	
	// Evaluación del algoritmo de búsqueda de primero en achura
	private static void MisionerosCanibalesBreadthFisrtSearchDemo() {
		System.out
				.println("\nMisioneros y Canibales BFS -->");
		try {
			Problem problem = new Problem(tablero, CanibalesFunctionFactory
					.getActionsFunction(), CanibalesFunctionFactory
					.getResultFunction(), new CanibalesGoalTest());
			Search search = new BreadthFirstSearch(new GraphSearch());
			long t1 = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long t2 = System.currentTimeMillis();
			printInstrumentation(agent.getInstrumentation());
			System.out.println("Tiempo:" + (t2-t1) + "mls");
			System.out.println("\nSOLUCION");
			CanibalesGoalTest s = new CanibalesGoalTest();
			System.out.printf("GOAL STATE \n%s \n", s.getGoal());
			System.out.println("CAMINO ENCONTRADO");
			executeActions(agent.getActions(), problem);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	// Evaluación del algoritmo de búsuqeda en profunfidad limitada
	private static void MisionerosCanibalesDepthLimitedSearchDemo() {
		System.out
				.println("\nMisioneros y Canibales DLS(11) -->");
		try {
			Problem problem = new Problem(tablero, CanibalesFunctionFactory
					.getActionsFunction(), CanibalesFunctionFactory
					.getResultFunction(), new CanibalesGoalTest());
			Search search = new DepthLimitedSearch(11);
			long t1 = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long t2 = System.currentTimeMillis();
			printInstrumentation(agent.getInstrumentation());
			System.out.println("Tiempo:" + (t2-t1) + "mls");
			System.out.println("\nSOLUCION");
			CanibalesGoalTest s = new CanibalesGoalTest();
			System.out.printf("GOAL STATE \n%s \n", s.getGoal());
			System.out.println("CAMINO ENCONTRADO");
			executeActions(agent.getActions(), problem);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	// Evaluación del algoritmo de búsuqeda en profunfidad iterativa
	private static void MisionerosCanibalesIterativeDeepeningSearchDemo() {
		System.out
				.println("\nMisioneros y Canibales IDLS -->");
		try {
			Problem problem = new Problem(tablero, CanibalesFunctionFactory
					.getActionsFunction(), CanibalesFunctionFactory
					.getResultFunction(), new CanibalesGoalTest());
			Search search = new IterativeDeepeningSearch();
			long t1 = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long t2 = System.currentTimeMillis();
			printInstrumentation(agent.getInstrumentation());
			System.out.println("Tiempo:" + (t2-t1) + "mls");
			System.out.println("\nSOLUCION");
			CanibalesGoalTest s = new CanibalesGoalTest();
			System.out.printf("GOAL STATE \n%s \n", s.getGoal());
			System.out.println("CAMINO ENCONTRADO");
			executeActions(agent.getActions(), problem);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}


}