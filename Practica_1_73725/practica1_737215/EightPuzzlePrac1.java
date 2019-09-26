package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

/**
 * @author Rubén Rodríguez Esteban 
 * @nip 737215
 * 
 */

public class EightPuzzlePrac1 extends ClaseGenerica {
	
	// Algoritmo 8-puzzle que se resuelve en tres pasos
	static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });;

	
	// Algoritmo 8-puzzle que se resuelve en nueve pasos
	static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2,
			7, 5, 8, 3, 0, 6 });

	
	// Algoritmo 8-puzzle que se resuelve en treinta pasos
	static EightPuzzleBoard extreme = new EightPuzzleBoard(new int[] { 0, 8, 7,
			6, 5, 4, 3, 2, 1 });

	
	/*
	 * Pre: ---
	 * Post: Muestra por pantalla una línea con las estadisticas del problema lanzado
	 * 		 En dicha información se muestra el nombre del algoritmo, la profundiad de la
	 * 		 búsuqeda, los nodos expandidos, el tamaño actual  de la frontera, el máxmimo de
	 * 		 la frontera y el tiempo de ejecución 
	 */
	private static void mostrarResultados(String message, String depth, String expandedNodes,
										String queueSize, String maxQueueSize, String timeDif) {
		// Mostramos los resultados del algoritmo ejecutado
		System.out.format("%8s| %10s| %8s| %14s| %13s| %15s\n", message, depth, expandedNodes, queueSize, maxQueueSize,timeDif);
	}
	
	
	/*
	 * Pre: <<search>> es un tipo de algotimo de búsqueda ciega o no informada, 
	 * 		<<tablero>> es el problema sobre el que se ejecuta el algoritmo <<search>>,
	 * 		<<message>> es el nombre del algoritmo a ejecutar, <<execute>> indica si el
	 * 		algoritmo puede ejecutarse o no  <<resultado>>
	 * Post: Devuelve una línea con las estadísticas del problema <<tablero>> al lanzar
	 * 		 el algoritmo <<search>>, si <<exexute>> es falso devuelve (1) o (2) indicando
	 * 	  	 que el problema no ha devuelto resultado por exceso de memoria o de tiempo, 
	 * 		 respectivamente
	 */
	private static void eightSearch(Search search, EightPuzzleBoard tablero, String message, boolean execute, String resultado) {
		try {
			String pathcostM; 
			String depth;
			String expandedNodes;
			String queueSize;
			String maxQueueSize; 
			String tiempo;
			long timeDif;
				
			if (execute) {
				Problem problem = new Problem(tablero, EightPuzzleFunctionFactory
						.getActionsFunction(), EightPuzzleFunctionFactory
						.getResultFunction(), new EightPuzzleGoalTest());
			
				long t1 = System.currentTimeMillis();
				SearchAgent agent = new SearchAgent(problem, search);
				long t2 = System.currentTimeMillis();
				timeDif = t2 - t1;
				
				tiempo = String.valueOf(timeDif);
				
				pathcostM =agent.getInstrumentation().getProperty("pathCost"); 
				if (pathcostM!=null) depth = String.valueOf((int)Float.parseFloat(pathcostM)); 
				else depth = "0"; 
				if (agent.getInstrumentation().getProperty("nodesExpanded")==null) expandedNodes= "0"; 
				else expandedNodes = String.valueOf(agent.getInstrumentation().getProperty("nodesExpanded")); 
				if (agent.getInstrumentation().getProperty("queueSize")==null) queueSize="0"; 
				else queueSize = String.valueOf(agent.getInstrumentation().getProperty("queueSize")); 
				if (agent.getInstrumentation().getProperty("maxQueueSize")==null) maxQueueSize= "0";
				else maxQueueSize =String.valueOf(agent.getInstrumentation().getProperty("maxQueueSize")); 
			}	 
			else {
				pathcostM = "---"; 
				 depth = "---";
				 expandedNodes = "---";
				 queueSize = "---";
				 maxQueueSize = "---"; 
				 tiempo = resultado;
			}
			// Mostramos una tabla con los resultados obtenidos por pantalla para el algoritmo 
			mostrarResultados(message,depth,expandedNodes,queueSize,maxQueueSize,tiempo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		
		// Lanzamiento de los algoritmos de búsqueda no informada para el problema
		// del 8-puzzle para tres, nueve y treinta pasos
		
		System.out.println("Problema|Profundidad|	Expand|		Q.Size|		MaxQS|		tiempo");
	
		eightSearch(new BreadthFirstSearch(new GraphSearch()),boardWithThreeMoveSolution,"BFS-G-3",true,"null");
		eightSearch(new BreadthFirstSearch(new TreeSearch()),boardWithThreeMoveSolution,"BFS-T-3",true,"null");
		eightSearch(new DepthFirstSearch(new GraphSearch()),boardWithThreeMoveSolution,"DFS-G-3",true,"null");
		eightSearch(new DepthFirstSearch(new TreeSearch()),boardWithThreeMoveSolution,"DFS-T-3",false,"(1)");
		eightSearch(new DepthLimitedSearch(3),boardWithThreeMoveSolution,"DLS-9-3",true,"null");
		eightSearch(new DepthLimitedSearch(9),boardWithThreeMoveSolution,"DLS-3-3",true,"null");
		eightSearch(new IterativeDeepeningSearch(),boardWithThreeMoveSolution,"IDS-3",true,"null");
		eightSearch(new UniformCostSearch(),boardWithThreeMoveSolution,"UCS-F-3",true,"(2)");
		eightSearch(new UniformCostSearch(new TreeSearch()),boardWithThreeMoveSolution,"UCS-T-3",true,"null");
		
		eightSearch(new BreadthFirstSearch(new GraphSearch()),random1,"BFS-G-9",true,"null");
		eightSearch(new BreadthFirstSearch(new TreeSearch()),random1,"BFS-T-9",true,"null");
		eightSearch(new DepthFirstSearch(new GraphSearch()),random1,"DFS-G-9",true,"null");
		eightSearch(new DepthFirstSearch(new TreeSearch()),random1,"DFS-T-9",false,"(1)");
		eightSearch(new DepthLimitedSearch(3),random1,"DLS-9-3",true,"null");
		eightSearch(new DepthLimitedSearch(9),random1,"DLS-9-9",true,"null");
		eightSearch(new IterativeDeepeningSearch(),random1,"IDS-9",true,"null");
		eightSearch(new UniformCostSearch(new GraphSearch()),random1,"UCS-9-3",true,"null");
		eightSearch(new UniformCostSearch(new TreeSearch()),random1,"UCS-T-9",true,"null");
		
		eightSearch(new BreadthFirstSearch(new GraphSearch()),extreme,"BFS-G-30",true,"null");
		eightSearch(new BreadthFirstSearch(new TreeSearch()),extreme,"BFS-G-30",false,"(2)");
		eightSearch(new DepthFirstSearch(new GraphSearch()),extreme,"DFS-G-30",true,"null");
		eightSearch(new DepthFirstSearch(new TreeSearch()),extreme,"DFS-T-30",false,"(1)");
		eightSearch(new DepthLimitedSearch(3),extreme,"DLS-30-3",true,"null");
		eightSearch(new DepthLimitedSearch(9),extreme,"DLS-30-9",true,"null");
		eightSearch(new IterativeDeepeningSearch(),extreme,"IDS-30",false,"(2)");
		eightSearch(new UniformCostSearch(),extreme,"UCS-F-30",false,"(2)");
		eightSearch(new UniformCostSearch(new TreeSearch()),extreme,"UCS-T-30",false,"(1)");
	}
	
	
}