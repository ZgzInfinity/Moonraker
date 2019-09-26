;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;	   PRACTICA 3 CLIPS				;;;
;;;    RUBEN RODRIGUEZ ESTEBAN      ;;;
;;;    NIP 737215					;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;           MODULO MAIN           ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule MAIN (export deftemplate nodo)
				(export deffunction heuristica))


(deftemplate MAIN::nodo 
	(multislot estado) 
	(multislot camino) 
	(slot heuristica) 
	(slot coste) 
	(slot clase (default abierto)))

(defglobal MAIN 
	?*estado-inicial* = (create$ B B B H V V V)
	?*estado-final* = (create$ V V V H B B B))

(deffunction MAIN::heuristica ($?estado) 
	(bind ?resultado 0)
	(loop-for-count (?i 1 7)
	(if (neq (nth$ ?i $?estado) (nth$ ?i $?*estado-final*)) then
		(bind ?resultado (+ ?resultado 1))))
			
?resultado)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;         MODULO INICIAL          ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrule MAIN::inicial 
 => 
(assert (nodo (estado ?*estado-inicial*)
		(camino (implode$ ?*estado-inicial*)) 
		(heuristica (heuristica ?*estado-inicial*)) 
		(coste 0))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; MODULO MAIN::CONTROL(SIN PRIORIDAD) ;;;
;;; PRIMERO EL MEJOR                ;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
		
		
(defrule MAIN::pasa-el-mejor-a-cerrado
  ?nodo <- (nodo (clase abierto)
  (heuristica ?h1) 
  (coste ?c1)) 
  (not (nodo (clase abierto)
  (heuristica ?h2) (coste ?c2 &:(< (+ ?h2 ?c2) (+ ?h1 ?c1)))))
   =>
  (modify ?nodo (clase cerrado))
  (focus OPERADORES))

  
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; MODULO MAIN::OPERACIONES        ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  
	
(defmodule OPERADORES
   (import MAIN deftemplate nodo)
   (import MAIN deffunction heuristica))

   				 
(defrule OPERADORES::movimiento-izquierda 
	(nodo (estado $?x H
		      $?y&:(<= (length$ $?y) 2) 
		      ?ficha $?z) 
		(camino $?movimientos) 
		(coste ?coste) 
		(clase cerrado)) 
	 => 
        (bind $?nuevo-estado 
		(create$ $?x ?ficha $?y H $?z))
	    (assert (nodo (estado $?nuevo-estado)
		       (camino $?movimientos 
				  (implode$ $?nuevo-estado))
		       (coste (+ ?coste 1)) 
		          (heuristica 
			          (heuristica $?nuevo-estado)))))
	
	
(defrule OPERADORES::movimiento-derecha
	(nodo (estado $?x ?ficha
	       $?y&:(<= (length$ $?y) 2)
	       H $?z) 
       (camino $?movimientos)
       (coste ?coste) 
       (clase cerrado))	   
	=>   
	   (bind $?nuevo-estado
       (create$ $?x H $?y ?ficha $?z))
       (assert (nodo (estado $?nuevo-estado)
	        (camino $?movimientos
		        (implode$ $?nuevo-estado)) 
	        (coste (+ ?coste 1))
                (heuristica 
		             (heuristica $?nuevo-estado)))))	
					 

(defmodule RESTRICCIONES
 (import MAIN deftemplate nodo))

(defrule RESTRICCIONES::repeticion-de-nodo
 (declare (auto-focus TRUE))
 (nodo (estado $?actual)
 (heuristica ?h1)
 (coste ?c1))
 ?nodo <- (nodo (estado $?actual)
 (heuristica ?h2)
 (coste ?c2 &:(> (+ ?c2 ?h2) (+ ?h1 ?c1))))
 =>
 (retract ?nodo)) 


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;    MODULO MAIN::SOLUCION        ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
 
(defmodule SOLUCION
 (import MAIN deftemplate nodo))

(defrule SOLUCION::reconoce-solucion
  (declare (auto-focus TRUE))
  ?nodo <- (nodo (heuristica 0)
		 (camino $?estados))
  =>
  (retract ?nodo)
  (assert (solucion $?estados)))

(defrule SOLUCION::escribe-solucion
  (solucion $?estados)
  =>
  (bind ?longitud (length$ $?estados))
  (printout t "La solucion tiene " (- ?longitud 1) 
	      " pasos " crlf)
  (loop-for-count (?i 1 ?longitud)
    (bind ?estado (nth$ ?i $?estados))
    (printout t ?estado crlf))
  (retract *))
