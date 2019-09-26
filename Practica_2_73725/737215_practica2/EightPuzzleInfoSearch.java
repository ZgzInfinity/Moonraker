package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction2;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction2;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.BestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.util.math.Biseccion;



/**
 * @author Rubén Rodríguez Esteban 
 * @nip 737215
 * 
 */

public class EightPuzzleInfoSearch extends GenerateRandomEightPuzzleBoard  {
	
	final static double INTERVALO_MINIMO = 1.0000001;
	final static double INTERVALO_MEDIO = 4;
	final static double ERROR_CALCULO = 0.0000000001;
	
	/*
	 * Pre: ---
	 * Post: Muestra por pantalla una línea con las estadisticas del problema lanzado
	 * 		 En dicha información se muestra el nombre del algoritmo, la profundiad de la
	 * 		 búsuqeda, los nodos expandidos, el tamaño actual  de la frontera, el máxmimo de
	 * 		 la frontera y el tiempo de ejecución 
	 */
	private static void mostrarResultados(String p, String nodosBFS, String nodosIDS, 
											String nodosAStarH1, String nodosAStarH2, double biseccionBFS,
											double biseccionIDS, double biseccionAStarH1, double biseccionAStarH2) { 
		
		// Mostramos los resultados del algoritmo ejecutado
		System.out.format("|| %3s|| %10s   |%10s  | %10s  | %10s  ||   %.2f  |", p, nodosBFS, nodosIDS, nodosAStarH1, nodosAStarH2,  biseccionBFS);
		
		// Verificamos si el algoritmo IDS ha hallado la solución
		if (biseccionIDS <= 0.0) {
			// No la ha encontrado
			System.out.format("  ----  ||");
		}
		else {
			// Si la ha encontrado
			System.out.format("  %.2f  ||", biseccionIDS);	
		}
		// Muestra el resto de las estadísticas
		System.out.format("   %.2f  |  %.2f   ||\n",  biseccionAStarH1,  biseccionAStarH2);
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
	private static int eightSearch(int prof, Search search , String nombre, boolean execute) {
		final int TOTAL_EXPERIMENTOS = 100;
		int nodosGenerados;
		int totalNodosGenerados = 0;
		int media;
		try {
			if (execute) {
				int experimentosRealizados = 0;
				while (experimentosRealizados < TOTAL_EXPERIMENTOS) {
					// Generación del estado final a partir de profundidad 
					// y el estado inicial BoardInicio
					EightPuzzleBoard InitialBoard = new EightPuzzleBoard(randomIni());
					EightPuzzleBoard finalState = random(prof, InitialBoard);
				
					// Validación del estado inicial
					EightPuzzleGoalTest2.setFinalState(finalState)
					;
					// Constructor del problema 
					Problem problem = new Problem(InitialBoard, EightPuzzleFunctionFactory
							.getActionsFunction(), EightPuzzleFunctionFactory
							.getResultFunction(), new EightPuzzleGoalTest2());   
				
					// Ejecutamos la búsqeda 
					SearchAgent agent = new SearchAgent(problem, search);
				
					// Extración de la profundidad de la solucion encontrada
					String pathcostM =agent.getInstrumentation().getProperty("pathCost");
					int profHallada;
					
					// Obtención de la profundidad de la solución
					if (pathcostM !=null) {
						profHallada = (int)Float.parseFloat(pathcostM);
					}
					else {
						profHallada = 0; 
					}
				
					// Comparacion para saber si hemos obtenido la solución optima
					if (prof == profHallada ) {
						// La solución es la correcta
						experimentosRealizados++;
					
						if (agent.getInstrumentation().getProperty("nodesGenerated")==null) {
							nodosGenerados= 0;
						}
						else {
							nodosGenerados = (int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesGenerated")); 
						}
					
						totalNodosGenerados += nodosGenerados;
						
						Biseccion b = new Biseccion();
						
					}
				}
			}
			return media = totalNodosGenerados / TOTAL_EXPERIMENTOS;	
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	

	public static double calcularBiseccion(Biseccion b, int d, int n) {
		// Asignamos la profundidad del experimento
		b.setDepth(d);
		
		// Asignamos el numero de nodos generados
		b.setGeneratedNodes(n);
		
		double resultado;
		
		// Calculo de la bisección para el intervalo
		return resultado = b.metodoDeBiseccion(INTERVALO_MINIMO, INTERVALO_MEDIO , ERROR_CALCULO);
	}
	
	// Programa principal
	public static void main(String[] args) {
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println("||    ||                     NODOS GENERADOS                   ||                   b*                    "); 
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println("||   d||         BFS  |      IDS   |    A*h(1)   |    A*h(2)   ||    BFS  |    IDS  |  A*h(1) | A*h(2)  ||");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		

		for (int depth = 2; depth < 25; depth ++) {
			
				String p = String.valueOf(depth);
				boolean execute = true;
			
				// Generamos un tablero inicial aleatorio

				// Lanzamos los cuatro algoritmos con profundidad depth 100 veces
			    int nodosGenBFS = eightSearch(depth, new BreadthFirstSearch(), "BFS", true);
			    
			    if (depth > 10) {
			    	execute = false;
			    }
			    
				int nodosGenIDS = eightSearch(depth, new IterativeDeepeningSearch(), "IDS", execute);
				
				int nodosGenAStarH1 = eightSearch(depth, new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction2()), "A*h(1)", true);
				
				int nodosGenAStarH2 = eightSearch(depth, new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction2()), "A*h(2)", true);
				
				String nodBFS = String.valueOf(nodosGenBFS);
				String nodIDS;
				
				if (nodosGenIDS <= 0) {
				     nodIDS = "----";
				}
				else {
					 nodIDS = String.valueOf(nodosGenIDS);
				}
				 
				
				String nodAStarH1 = String.valueOf(nodosGenAStarH1); 
				String nodAStarH2 = String.valueOf(nodosGenAStarH2); 
				
				// Calculo del factor de ramificación o bisección
 
				Biseccion biBFS = new Biseccion();
				Biseccion biIDS = new Biseccion();
				Biseccion biAStarH1 = new Biseccion();
				Biseccion biAStarH2 = new Biseccion();
				
				double biseccionBFS = calcularBiseccion(biBFS, depth, nodosGenBFS);
				double biseccionIDS = calcularBiseccion(biIDS, depth, nodosGenIDS);
				double biseccionAStarH1 = calcularBiseccion(biAStarH1, depth,nodosGenAStarH1);
				double biseccionAStarH2 = calcularBiseccion(biAStarH2, depth, nodosGenAStarH2);
				
				mostrarResultados(p, nodBFS, nodIDS, nodAStarH1, nodAStarH2, biseccionBFS, biseccionIDS, biseccionAStarH1, biseccionAStarH2);
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------");
	}
	
}