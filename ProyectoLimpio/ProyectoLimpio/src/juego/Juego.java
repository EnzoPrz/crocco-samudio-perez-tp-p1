package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	private Personaje personaje;
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.personaje = new Personaje(entorno.ancho()/2, entorno.alto() - 200, 30, 45, 3);
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		personaje.dibujarse(entorno);
		
		
		//Colisiones barra - entorno
				if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !personaje.colisionaPorDerecha(entorno) /*&& !personaje.colisionaPorDerecha(pelota)*/)
					personaje.moverDerecha();
				
				if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !personaje.colisionaPorIzquierda(entorno) /*&& !personaje.colisionaPorIzquierda(pelota)^*/)
					personaje.moverIzquierda();
				
				if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && !personaje.colisionaPorArriba(entorno) /*&& !barra.colisionaPorIzquierda(pelota)*/)
					personaje.moverArriba();

				if(entorno.estaPresionada(entorno.TECLA_ABAJO) && !personaje.colisionaPorAbajo(entorno) /*&& !barra.colisionaPorIzquierda(pelota)*/)
					personaje.moverAbajo();
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
