package juego;


import java.awt.Color;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje pep;
	private Isla[] islas;
	private Tortuga tortugas [] = new Tortuga [6];
	private Gnomo gnomos;
	private BolaDeFuego esfera [] = new BolaDeFuego [8];
	private DisparoPersonaje disparoPersonaje;
	private ItemDisparo itemdisparo;
	private int contadorDisparos= 3;

	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		pep = new Personaje(entorno.ancho()/2-170, entorno.alto()/2+ 110, 20, 40, 0, false, 'i');
		islas=crearIslas(entorno);
		//tortugas=new Tortuga(entorno.ancho()/2, entorno.alto()/2- 100, 27, 50, 0, true, 0, 0.5, 'd');
		//tortuga1=new Tortuga(entorno.ancho()/2, entorno.alto()/2- 100, 27, 50, 0, true, 0, 1);
		RellenarJuegoConTortugas(tortugas);
		
		//RellenarJuegoConBolasDeFuego(esfera);
		gnomos= new Gnomo(entorno.ancho()/2,entorno.alto()/2-270,20, 60, 1, 0, false, 0.5, null);
		//tortugas=crearTortugas(entorno);
		//RellenarJuegoConDinos(tortugas);

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
		/*
		for(Tortuga tortuga: tortugas) {
			if(tortuga!=null) {
				tortuga.dibujar(entorno);				
			}
		}
		*/
//		if(tortugas != null) {
//			tortugas.dibujar(entorno);
//		}
		gnomos.dibujar(entorno);
		
		
		
		
		
		//pep - entorno - islas
		
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !pep.estaColisionandoPorDerecha(entorno) && !pep.estaColisionandoPorDerecha(islas)) {
			pep.moverDerecha(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !pep.estaColisionandoPorIzquierda(entorno) && !pep.estaColisionandoPorIzquierda(islas)) {
			pep.moverIzquierda(entorno);
		}
	
		//esto hace que el personaje caiga y salte
		// esto hace que caiga
		if (!pep.getEstaSaltando()) {
			if(!pep.estaColisionandoPorAbajo(islas) ) {
				pep.moverHaciaAbajo(entorno);
			}
		}
		
		//esto hace que suba con la flecha_arriba
		if(entorno.sePresiono(entorno.TECLA_ARRIBA) && pep.estaColisionandoPorAbajo(islas) ) {
			pep.moverHaciaArriba(entorno);
			pep.setEstaSaltando(true);
		}
		
		//esto hace que baje 
		if (pep.getEstaSaltando()) {
			if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				pep.moverHaciaAbajo(entorno);
				pep.setEstaSaltando(false);
			}
		}
		
		// esto hace tope para que no pueda subir a la ultima isla
		if (pep.getEstaSaltando()) {
			if (!pep.estaColisionandoPorArriba(islas) && !pep.estaColisionandoPorArriba(entorno)&& pep.getY() > 100) {
				pep.moverHaciaArriba(entorno) ;
			}else {
				pep.setEstaSaltando(false);
				
			}
		}

		///////disparo personaje pep ---> disparo


		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.disparoPersonaje==null && this.contadorDisparos>0 && this.contadorDisparos<=3) {
			this.disparoPersonaje=new DisparoPersonaje (this.pep.getX(), this.pep.getY(), 20, 20,1,2, this.pep.getDireccion());
		}
		
		if(this.disparoPersonaje!=null) {
			this.disparoPersonaje.dibujarse(entorno);
			this.disparoPersonaje.moverDisparo();
			if (this.disparoPersonaje.colisionDisparoBorde(entorno)) {
				this.disparoPersonaje=null;
				this.contadorDisparos--;
			}
		}
		
		//item - disparo
		if (contadorDisparos==0) {
			this.itemdisparo = new ItemDisparo (entorno.ancho()-35, 350, 18, 18);
			this.itemdisparo.aparecer(entorno);
		}
				
		if (this.itemdisparo!=null && colisionar(this.pep.getX(), this.pep.getY(), this.itemdisparo.getX(), this.itemdisparo.getY(), 10)) {
			this.itemdisparo=null;
			contadorDisparos=3;
		}
		
		
		
		
//////////////////////////////////////tortugas - pep- islas //////////////////////////////////////////////////////////////////////////
		
		// las tortugas caen
		int r;
		for (r=0;r<=tortugas.length-1;r++) {
			if(tortugas[r] != null) {
				tortugas[r].dibujar(entorno);
			
			if( !tortugas[r].estaColisionandoPorAbajo(islas)) {
				tortugas[r].moverHaciaAbajo(entorno);
				tortugas[r].setEstaCayendo(true);
			}
			
			
			// SE MUEVEN
			if (tortugas[r].estaColisionandoPorAbajo(islas)) {
				tortugas[r].mover();
			}
			
//			if(tortugas[r].estaColisionandoConBordes(islas)) {
//				tortugas[r].cambiarMovimientoHorizontal();
//			}
		
//			if( tortugas[r].estaColisionandoPorIzquierda(islas)||tortugas[r].estaColisionandoPorDerecha(islas)) {
//				tortugas[r].cambiarMovimientoHorizontal();
//			}
			}
		}
		
	
//		if(tortugas.getEstaCayendo()&& !tortugas.estaColisionandoPorAbajo(islas)) {
//				tortugas.moverHaciaAbajo(entorno);
//		}	
//		
//		// SE MUEVEN
//		if (tortugas.estaColisionandoPorAbajo(islas)) {
//			tortugas.mover();
//		}
//		
//		if( tortugas.estaColisionandoPorIzquierda(islas)||tortugas.estaColisionandoPorDerecha(islas)) {
//			tortugas.cambiarMovimientoHorizontal();
//		}

		
		
//		int r;
//		for (r=0;r<=this.tortugas.length-1;r++) {
//			if(this.tortugas[r] != null) {
//				this.tortugas[r].dibujar(entorno);
//			
//			if (this.tortugas[r].getEstaCayendo()==false) {
//				this.tortugas[r].mover();
//			}
//			
//			if(this.tortugas[r].estaColisionandoPorDerecha(islas) || this.tortugas[r].estaColisionandoPorIzquierda(islas)) {
//				this.tortugas[r].cambiarMovimientoHorizontal();
//			}
//						
//			
//			
//			//COLISION DINOSAURIO-BLOQUEABAJO /si no hay bloque --> CAE	
//			
//			if(this.tortugas[r]!=null && !this.tortugas[r].estaColisionandoPorAbajo(islas)) {
//				this.tortugas[r].moverHaciaAbajo(entorno);
//				this.tortugas[r].setEstaCayendo(true);
//			} else {
//				if(this.tortugas[r]!=null) {
//				this.tortugas[r].setEstaCayendo(false);
//				}
//			}
//			}
//		}
		
////////////////////////////////  GNOMOS    //////////////////////////////////////////////////////		
		
		
	    // Verifica si el gnomo colisiona con las islas
		if(!gnomos.getEstaCayendo()) {
		    if (!gnomos.estaColisionandoPorAbajo(islas)) {
		        gnomos.moverHaciaAbajo(entorno);
		
		    }
	    
		}
	    if (gnomos.getEstaCayendo() &&  !gnomos.estaColisionandoPorAbajo(islas)) {
	    	gnomos.moverHaciaAbajo(entorno);
	    	gnomos.setEstaCayendo(false);
	    }
	    
		
	    if (!gnomos.getEstaCayendo()&& gnomos.estaColisionandoPorAbajo(islas)) {
	    	gnomos.moverRandom(islas);
		}
		
		
//		if(!gnomos.estaColisionandoPorDerecha(pep)) {
//			gnomos.mover();
//		}
		
	
		
		
		
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
				islas[indice]= new Isla(x,y,125,30);
				indice++;
			}
		}
		
		return islas;
	}
	
	
	
	public void RellenarJuegoConTortugas (Tortuga [] t) {
		double XparaTortuF1 = 175;
		double YparaTortuF1 = entorno.alto()/2;
		double XparaTortuF2 = 95;
		double YparaTortuF2 = entorno.alto()/2;
		double XparaTortuF3 = 45;
		double YparaTortuF3 = entorno.alto()/2;
//		double XparaTortuF4 = 255;
//		double YparaDinoF4 = entorno.alto()/2;
		
		for (int i=0; i < t.length; i++) {
			if (i==0 || i==1) {
				t[i] = new Tortuga (XparaTortuF1, YparaTortuF1-170, 27, 50, 0.5, 1, 'd', false, 0.5 );
				//XparaTortuF1=entorno.ancho()-XparaTortuF1;
				
			}
			
			if (i==2 || i==3) {
				t[i] = new Tortuga (XparaTortuF2, YparaTortuF2-170, 27, 50, 0.5, 1.5, 'd', false, 0.5 );
				//XparaTortuF2=entorno.ancho()-XparaTortuF2;
			}
			if (i==4 || i==5) {
				t[i] = new Tortuga (XparaTortuF3, YparaTortuF3-170, 27, 50, 0.5, 2, 'd', false, 0.5 );
				//XparaTortuF3=entorno.ancho()-XparaTortuF3;
			}
//			if (i==6 || i==7) {
//				t[i] = new Tortuga (XparaDinoF4, YparaDinoF4-190, 27, 50, 0.5, 1, 'd', false, 0.5 );
//				XparaDinoF4=entorno.ancho()-XparaDinoF4;
//			}
			
		}
	}
	
	
	
	
	
	
	public void RellenarJuegoConBolasDeFuego(BolaDeFuego [] esfera) {
		for (int g=0; g<esfera.length; g++) {
			if(tortugas[g]!=null && esfera[g]==null) {
				esfera[g]= new BolaDeFuego (tortugas[g].getX(), tortugas[g].getY()+12, 20,20, tortugas[g].getDireccion(),1,1);
			}
		}
	}
	
	
	
	
	public boolean colisionar(double x1, double y1, double x2, double y2, double dist) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < dist;
	}
	
	


	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	
	
	
	
	
	
}









