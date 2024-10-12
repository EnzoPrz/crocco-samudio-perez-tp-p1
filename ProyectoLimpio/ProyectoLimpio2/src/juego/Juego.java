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
	private Barra barra;
	private Pelota pelota;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.pelota = new Pelota(this.entorno.ancho()/2,this.entorno.alto()/2,50,-1,1,1);
		this.barra = new Barra(entorno.ancho()/2, entorno.alto() - 200, 200, 50, 3);
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
		
		//Dibujo objetos
		pelota.dibujar(entorno);
		barra.dibujarse(entorno);
		
		pelota.mover();
		
		//Colisiones pelota - entorno
		if(pelota.colisionaPorAbajo(entorno) || pelota.colisionaPorArriba()) {
			pelota.cambiarMovimientoVertical();
		}
		if(pelota.colisionaPorDerecha(entorno) || pelota.colisionaPorIzquierda()) {
			pelota.cambiarMovimientoHorizontal();
		}
		
		
		//colisiones barra - pelota
				if(barra.colisionaPorArriba(pelota)) {
					pelota.cambiarMovimientoVertical();
				}
				if(barra.colisionaPorAbajo(pelota)) {
					pelota.cambiarMovimientoVertical();
				}
				if(barra.colisionaPorIzquierda(pelota)) {
					pelota.cambiarMovimientoHorizontal();
				}
				if(barra.colisionaPorDerecha(pelota)) {
					pelota.cambiarMovimientoHorizontal();
				}
		
		//Colisiones barra - entorno
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !barra.colisionaPorDerecha(entorno) && !barra.colisionaPorDerecha(pelota))
			barra.moverDerecha();
		
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !barra.colisionaPorIzquierda(entorno) && !barra.colisionaPorIzquierda(pelota))
			barra.moverIzquierda();
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && !barra.colisionaPorArriba(entorno) /*&& !barra.colisionaPorIzquierda(pelota)*/)
			barra.moverArriba();

		if(entorno.estaPresionada(entorno.TECLA_ABAJO) && !barra.colisionaPorAbajo(entorno) /*&& !barra.colisionaPorIzquierda(pelota)*/)
			barra.moverAbajo();
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
