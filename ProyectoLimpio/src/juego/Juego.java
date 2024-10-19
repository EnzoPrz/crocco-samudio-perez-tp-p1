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
	private Tortuga tortugas;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		pep = new Personaje(entorno.ancho()/2, entorno.alto()/2- 100, 20, 40, 0, false);
		islas=crearIslas(entorno);
		tortugas=new Tortuga(entorno.ancho()/2, entorno.alto()/2- 80, 27, 50, 0, false, 0);

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
		
	
		tortugas.dibujar(entorno);				
		
		
		
		
		
		
		
		//pep - entorno - islas
		
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !pep.estaColisionandoPorDerecha(entorno) && !pep.estaColisionandoPorDerecha(islas)) {
			pep.moverDerecha(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !pep.estaColisionandoPorIzquierda(entorno) && !pep.estaColisionandoPorIzquierda(islas)) {
			pep.moverIzquierda(entorno);
		}
		
		
		
		//pep - salto -- gravedad
		
		//esto hace que el personaje caiga y salte
		if (!pep.getEstaSaltando()) {
			if(!pep.estaColisionandoPorAbajo(islas) ) {
				pep.moverHaciaAbajo(entorno);
			}
		}
		
		if(entorno.sePresiono(entorno.TECLA_ESPACIO) && pep.estaColisionandoPorAbajo(islas) ) {
			pep.setEstaSaltando(true);
		}
		
		if (pep.getEstaSaltando()) {
			if(entorno.estaPresionada(entorno.TECLA_ABAJO)/*&& pep.estaColisionandoPorArriba(entorno) && pep.estaColisionandoPorArriba(islas)*/) {
				pep.moverHaciaAbajo(entorno);
				pep.setEstaSaltando(false);
			}
		}
		
		if (pep.getEstaSaltando()) {
			if (!pep.estaColisionandoPorArriba(islas) && !pep.estaColisionandoPorArriba(entorno)&& pep.getY() > 100) {
				pep.moverHaciaArriba(entorno) ;
			}else {
				pep.setEstaSaltando(false);
				
			}
		}
		
		
		//tortugas - pep- islas 
		// las tortugas caen
		if (!tortugas.getEstaCayendo()) {
			if(!tortugas.estaColisionandoPorAbajo(islas) ) {
				tortugas.moverHaciaAbajo(entorno);
			}
		}		
		
		if (tortugas.getEstaCayendo() &&  !tortugas.estaColisionandoPorAbajo(islas)) {
				tortugas.moverHaciaAbajo(entorno);
				tortugas.setEstaCayendo(false);
			}
		
		//
		
		
	
		
		
		
	}
	
	
	
	
	public static Isla[] crearIslas(Entorno e) {
		int pisos=5;
		Isla[] islas=new Isla[pisos*(pisos+1)/2];
		int y=0;
		int x=0;
		int indice=0;
		for(int i=1 ;i<=pisos; i++) {
			y=y+100;
			int expansion=-25*i;
			for(int j=1 ; j<=i; j++) {
				x=(e.ancho()-expansion)/(i+1)*j+expansion/2;
				islas[indice]= new Isla(x,y,110,30);
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




/*
if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&& !pep.estaColisionandoPorArriba(entorno) && !pep.estaColisionandoPorArriba(islas)) {
	pep.moverHaciaArriba(entorno);
}
if(entorno.estaPresionada(entorno.TECLA_ABAJO)&& !pep.estaColisionandoPorAbajo(entorno) && !pep.estaColisionandoPorAbajo(islas)) {
	pep.moverHaciaAbajo(entorno);
}
*/




