package aima.core.environment.MisionerosCanibales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 * @author Rubén Rodríguez Esteban 
 * @nip 737215
 */
public class Canibales {
	
	// Definición del problema de los misioneros y los caníbales

	
	// Secuencia de movimientos legales
	public static Action M1M = new DynamicAction("M1M");

	public static Action M2M = new DynamicAction("M2M");

	public static Action M1C = new DynamicAction("M1C");

	public static Action M2C = new DynamicAction("M2C");
	
	public static Action M1M1C = new DynamicAction("M1M1C");
	

	
	private int[] state;

	//
	// PUBLIC METHODS
	//

	
	// Estado inicial
	public Canibales() {
		state = new int[] {3,3,0,0,0};
	}

	public Canibales(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	
	public Canibales(Canibales copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}
	
	// MOVIMIENTOS BARCA
	
	/*
	 * Pre: <<ribera>> = 0 || <<ribera>> = 1
	 * Post: Establece el bote en la riebera izquierda si <<ribera>> es 0
	 * 		 si es 1 en la ribera derecha 
	 */
	public void establecerBote(int ribera) {
		state[4] = ribera;
	}
	
	/*
	 * Pre: ---
	 * Post: Devuelve en que ribera está el bote
	 */
	public int consultarBote() {
		return state[4];
	}

	
	// MOVIMIENTOS DE MISIONERO
	
	/*
	 * Pre: ---
	 * Post: Devuelve los misioneros presentes en la ribera izquierda 		
	 */
	public int getMisionerosIzd(){
		return state[0];
	}
	
	/*
	 * Pre: ---
	 * Post: Devuelve los misioneros presentes en la ribera derecha
	 */
	public int getMisionerosDcha(){
		return state[2];
	}
	
	
	/*
	 * Pre: ---
	 * Post: Incrementa en una unidad los misioneros de la ribera izquierda
	 *  	 y decrementa los de la derecha
	 */
	public void actualizarUnMisionerosIzda() {
		state[0]++;
		state[2]--;
	}
	
	
	/*
	 * Pre: ---
	 * Post: Incrementa en una unidad los misioneros de la ribera derecha
	 *  	 y decrementa los de la izquierda
	 */
	public void actualizarUnMisionerosDcha() {
		state[0]--;
		state[2]++;
	}
	
	
	/*
	 * Pre: ---
	 * Post: Incrementa en dos unidades los misioneros de la ribera izquierda
	 * 		  y decrementa dos de la derecha
	 */
	public void actualizarDosMisionerosIzda() {
		state[0] += 2;
		state[2] -= 2;
	}
	
	
	/*
	 * Pre: ---
	 * Post: Incrementa en dos unidades los misioneros de la ribera derecha
	 * 		  y decrementa dos de la izquierda
	 */
	public void actualizarDosMisionerosDcha() {
		state[0] -= 2;
		state[2] += 2;
	}
	
	
	/*
	 * Pre : ---
	 * Post : Mueve un misionero
	 */
	public void moverM() {
		if (consultarBote() == 0){
			actualizarUnMisionerosDcha();
			establecerBote(1);
		}
		else{
			actualizarUnMisionerosIzda();
			establecerBote(0);
		}
	}
	
	
	/*
	 * Pre : ---
	 * Post : Mueve dos misioneros
	 */
	public void moverMM() {
		if (consultarBote() == 0){
			actualizarDosMisionerosDcha();
			establecerBote(1);
		}
		else{
			actualizarDosMisionerosIzda();
			establecerBote(0);
		}
	}
	
	
	// MOVIMIENTOS DE CANIBAL
	

	/*
	 * Pre: ---
	 * Post: Devuelve los caníbales presentes en la ribera izquierda 		
	 */
	public int getCanibalesIzd(){
		return state[1];
	}
		
	/*
	 * Pre: ---
	 * Post: Devuelve los caníbales presentes en la ribera derecha 		
	 */
	public int getCanibalesDcha(){
		return state[3];
	}

	
	/*
	 * Pre: ---
	 * Post: Incrementa en una unidad los caníbales de la ribera izquierda
	 *  	 y decrementa los de la derecha
	 */
	public void actualizarUnCanibalIzda() {
		state[1]++;
		state[3]--;
	}
	
	
	/*
	 * Pre: ---
	 * Post: Incrementa en una unidad los caníbales de la ribera derecha
	 *  	 y decrementa los de la izquierda
	 */
	public void actualizarUnCanibalDcha() {
		state[1]--;
		state[3]++;
	}
		
	
	/*
	 * Pre: ---
	 * Post: Incrementa en dos unidades los caníbales de la ribera izquierda
	 *  	 y decrementa los de la derecha
	 */
	public void actualizarDosCanibalesIzda() {
		state[1] += 2;
		state[3] -= 2;
	}
		
	
	/*
	 * Pre: ---
	 * Post: Incrementa en dos unidades los caníbales de la ribera derecha
	 *  	 y decrementa los de la izquierda
	 */
	public void actualizarDosCanibalesDcha() {
		state[1] -= 2;
		state[3] += 2;
	}
		
		
		/*
		 * Pre : ---
		 * Post : Mueve un caníbal
		 */
	public void moverC() {
		if (consultarBote() == 0){
			actualizarUnCanibalDcha();
			establecerBote(1);
			}
			else{
				actualizarUnCanibalIzda();
				establecerBote(0);
			}
	}
		
		
		/*
		 * Pre : ---
		 * Post : Mueve dos caníbales
		 */
	public void moverCC() {
		if (consultarBote() == 0){
			actualizarDosCanibalesDcha();
			establecerBote(1);
		}
		else{
			actualizarDosCanibalesIzda();
			establecerBote(0);
		}
	}

		
	/*
	 * Pre : ---
	 * Post : Mueve un misionero y un caníbal a la izquierda
	 * 		  y los quita de la derecha
	 */
	public void actualizarUnMisioneroUnCanibalIzda() {
		state[0]++;
		state[1]++;
		state[2]--;
		state[3]--;
	}
		
		
	/*
	 * Pre : ---
	 * Post : Mueve un misionero y un caníbal a la derecha
	 * 		  y los quita de la izquierda
	 */
	public void actualizarUnMisioneroUnCanibalDcha() {
		state[0]--;
		state[1]--;
		state[2]++;
		state[3]++;
	}
		
		
	/*
	 * Pre: ---
	 * Post: Mueve un misionero y un caníbal
	 */
	public void moverMC() {
		if (consultarBote() == 0){
			actualizarUnMisioneroUnCanibalDcha();
			establecerBote(1);
		}
		else{
			actualizarUnMisioneroUnCanibalIzda();
			establecerBote(0);
		}
	}
		
	/*
	 * Pre: <<mI>> indica los misioneros a la izquierda, <<cI>> los
	 * 		caníbales a la izquierda, <<mD>> los misioneros a la derecha
	 * 		y <<cD>> los caníbales a la derecha
	 * Post: Devuelve <true>> si y solo si el estado actual del juego es invalido,
	 * 		 en caso contario devuelve falso
	 */
	public boolean estadoErroneo(int mI, int cI, int mD, int cD) {
		return (mI < cI && mI > 0) || (mD < cD && mD > 0);
	}
	
		
	/*
	 * Pre: ---
	 * Post: Devuelve verdad si y solo si se puede realizar movimiento, 
	 * 		 en caso contrario devuelve falso
	 */
	public boolean poderMover(Action where) {
		boolean retVal = true;
		if (where.equals(M1C)) {
			if (consultarBote() == 0) {
				retVal = getCanibalesIzd() > 0 && !estadoErroneo(getMisionerosIzd(), getCanibalesIzd() - 1,
						 getMisionerosDcha(), getCanibalesDcha() + 1);
			}
			else {
				retVal = getCanibalesDcha() > 0 && !estadoErroneo(getMisionerosDcha(), getCanibalesDcha() + 1,
					     getMisionerosDcha(), getCanibalesDcha() - 1);
			}
		}
		else if (where.equals(M2C)) {
			if (consultarBote() == 0) {
				retVal = getCanibalesIzd() > 1 && !estadoErroneo(getMisionerosIzd(), getCanibalesIzd() - 2,
						 getMisionerosDcha(), getCanibalesDcha() + 2);
			}
			else {
				retVal = getCanibalesDcha() > 1 && !estadoErroneo(getMisionerosIzd(), getCanibalesIzd()  + 2,
						 getMisionerosDcha(), getCanibalesDcha() -2);
			}
		}
		else if (where.equals(M1M)){
			if (consultarBote() == 0) {
				retVal = getMisionerosIzd() > 0 && !estadoErroneo(getMisionerosIzd() - 1, getCanibalesIzd(),
						 getMisionerosDcha() + 1, getCanibalesDcha());
			}
			else {
				retVal = getMisionerosDcha() > 0 && !estadoErroneo(getMisionerosIzd() + 1, getCanibalesIzd(),
					     getMisionerosDcha() - 1, getCanibalesDcha());
			}
		}
		else if (where.equals(M2M)) {
			if (consultarBote() == 0){
				retVal = getMisionerosIzd() > 1 && !estadoErroneo(getMisionerosIzd() - 2, getCanibalesIzd(),
						 getMisionerosDcha() + 2, getCanibalesDcha());
			}
			else {
				retVal = getMisionerosDcha() > 1 && !estadoErroneo(getMisionerosIzd() + 2, getCanibalesIzd(),
						 getMisionerosDcha() - 2 , getCanibalesDcha());
			}
		}
		else if (where.equals(M1M1C)) {
			if (consultarBote() == 0) {
				retVal = getMisionerosIzd() > 0 && getCanibalesIzd() > 0
												 && !estadoErroneo(getMisionerosIzd() - 1, getCanibalesIzd() - 1, getMisionerosDcha() + 1, getCanibalesDcha() + 1);
				}
			else {
				retVal = getMisionerosDcha() > 0 && getCanibalesDcha() > 0
												  && !estadoErroneo(getMisionerosIzd() + 1, getCanibalesIzd() + 1, getMisionerosDcha() - 1, getCanibalesDcha() - 1 );
			}
		}
		return retVal;
	}
	
		
	@Override
	public String toString(){
		String retVal;
		if (consultarBote() == 1) {
			 retVal = "RIBERA-IZQ ";
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[0]) {
					 retVal = retVal + "M ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[1]) {
					 retVal = retVal + "C ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 retVal = retVal + "     --RIO-- BOTE ";
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[2]) {
					 retVal = retVal + "M ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[3]) {
					 retVal = retVal + "C ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 retVal = retVal + "RIBERA-DCHA";
		}
		else {
			 retVal = "RIBERA-IZQ ";
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[0]) {
					 retVal = retVal + "M ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[1]) {
					 retVal = retVal + "C ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 retVal = retVal + "BOTE --RIO--      ";
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[2]) {
					 retVal = retVal + "M ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 for (int i = 3; i > 0; i--) {
				 if (i <= state[3]) {
					 retVal = retVal + "C ";
				 }
				 else {
					 retVal = retVal + "  ";
				 }
			 }
			 retVal = retVal + "RIBERA-DCHA";
		}
		return retVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Canibales other = (Canibales) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}

	
}