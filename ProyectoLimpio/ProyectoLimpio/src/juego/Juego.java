package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje pep;
	private Isla[] islas;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		pep = new Personaje(entorno.ancho()/2, entorno.alto()/2, 20, 40);
		islas=crearIslas(entorno);
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
		pep.dibujar(entorno);
		for(Isla isla: islas) {
			if(isla!=null) {
				isla.dibujar(entorno);				
			}
		}
		
		
		//Acciones
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&& !pep.estaColisionandoPorArriba(entorno) && !pep.estaColisionandoPorArriba(islas)) {
			pep.moverHaciaArriba(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_ABAJO)&& !pep.estaColisionandoPorAbajo(entorno) && !pep.estaColisionandoPorAbajo(islas)) {
			pep.moverHaciaAbajo(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !pep.estaColisionandoPorDerecha(entorno) && !pep.estaColisionandoPorDerecha(islas)) {
			pep.moverDerecha(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !pep.estaColisionandoPorIzquierda(entorno) && !pep.estaColisionandoPorIzquierda(islas)) {
			pep.moverIzquierda(entorno);
		}
		
		
	}
	
	public static Isla[] crearIslas(Entorno e) {
		int pisos=5;
		Isla[] islas=new Isla[pisos*(pisos+1)/2];
		int y=0;
		int x=0;
		int indice=0;
		for(int i=1 ;i<=pisos; i++) {
			y=y+100;
			int expansion=-50*i;
			for(int j=1 ; j<=i; j++) {
				x=(e.ancho()-expansion)/(i+1)*j+expansion/2;
				islas[indice]= new Isla(x,y,100,30);
				indice++;
			}
		}
		
		return islas;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
