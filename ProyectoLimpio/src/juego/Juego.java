package juego;


import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje pep;
	private Isla[] islas;
	private Tortuga tortugas [] = new Tortuga [58];
	private Gnomo gnomos [] = new Gnomo [40];
	//private BolaDeFuego esfera [] = new BolaDeFuego [8];
	private DisparoPersonaje disparoPersonaje;
	private ItemDisparo itemdisparo;
	private int contadorDisparos= 1000;
	
	private Image fondo;
	private int tiempo;
	private int salvados= 0;
	
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
		RellenarJuegoConTortugas(tortugas);
		RellenarJuegoConGnomos(gnomos);
		//RellenarJuegoConBolasDeFuego(esfera);
		

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

		if(entorno.estaPresionado(entorno.BOTON_IZQUIERDO) && this.disparoPersonaje==null && this.contadorDisparos>0 && this.contadorDisparos<=1000) {
			this.disparoPersonaje=new DisparoPersonaje (this.pep.getX(), this.pep.getY(), 20, 20,1,2, this.pep.getDireccion());
		}
		
		
		if(this.disparoPersonaje!=null) {
			this.disparoPersonaje.dibujarse(entorno);
			this.disparoPersonaje.moverDisparo();
			if (this.disparoPersonaje.colisionDisparoBorde(entorno)) {
				this.disparoPersonaje= null;
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
			contadorDisparos=1000;
		}

		
		
		
		
		///////////////////////////
		
		
		
		
		
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
				
				
				for(Isla isla : islas) {
					if(tortugas[r].estaColisionandoPorAbajo(new Isla [] {isla})) {
						tortugas[r].setIslaActual(isla);
						break;
					}
				}
				
			
				
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


		
		

		
////////////////////////////////  GNOMOS    //////////////////////////////////////////////////////		
		
		
	    // Verifica si el gnomo colisiona con las islas
//		if(gnomos.getEstaCayendo()) {
//		    if (!gnomos.estaColisionandoPorAbajo(islas)) {
//		        gnomos.moverHaciaAbajo(entorno);
//		      
//		    }
//	    
//		}
//
//	    
//		if(gnomos.estaColisionandoPorAbajo(islas)) {
//			//System.out.print("mover");
//		
//			gnomos.mover(islas);
//		}
		
	
		int w;
		for (w=0;w<=gnomos.length-1;w++) {
			if(gnomos[w] != null) {
				gnomos[w].dibujar(entorno);
			
				if( !gnomos[w].estaColisionandoPorAbajo(islas)) {
					gnomos[w].moverHaciaAbajo(entorno);
					
				}
				
				
				// SE MUEVEN
				if (gnomos[w].estaColisionandoPorAbajo(islas)) {
					gnomos[w].mover(islas);
				}
				
				//COLISION gnompos con pep
				if(this.pep!=null && colisionar(this.gnomos[w].getX(), this.gnomos[w].getY(), this.pep.getX(), this.pep.getY(), 30)) {
					this.gnomos[w]=null;
					salvados++;
				}
			
			}
		}
		
		entorno.escribirTexto("gnomos salvados: " + salvados, 3, 20);
		
		
		
		

//		//pantalla perder
//			if (this.pep==null) {
//				Perdiste();
//			}
		
		
		
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
		double XparaTortuF4 = 295;
		double YparaTortuF4 = entorno.alto()/2;
		
		for (int i=0; i < t.length; i++) {
			if (i==0 || i==1) {
				t[i] = new Tortuga (XparaTortuF1, YparaTortuF1-170, 27, 50, 0.5, 1, 'd', false, 0.5 );
				XparaTortuF1=entorno.ancho()-XparaTortuF1;
				
			}
			
			if (i==2 || i==3) {
				t[i] = new Tortuga (XparaTortuF2, YparaTortuF2-170, 27, 50, 0.5, 1.5, 'd', false, 0.5 );
				XparaTortuF2=entorno.ancho()-XparaTortuF2;
			}
			if (i==4 || i==5) {
				t[i] = new Tortuga (XparaTortuF3, YparaTortuF3-170, 27, 50, 0.5, 2, 'd', false, 0.5 );
				XparaTortuF3=entorno.ancho()-XparaTortuF3;
			}
			if (i==6 || i==7) {
				t[i] = new Tortuga (XparaTortuF4, YparaTortuF4-50, 27, 50, 0.5, 1, 'd', false, 0.5 );
				XparaTortuF4=entorno.ancho()-XparaTortuF4;
			}
			
		}
	}
	
	
	
	
	
	
	public void RellenarJuegoConGnomos(Gnomo [] gno) {
		double XparaTortuF1 = entorno.ancho()/2;
		double YparaTortuF1 = entorno.alto()/2-270;
		double XparaTortuF2 = entorno.ancho()/2;
		double YparaTortuF2 = entorno.alto()/2-270;
		double XparaTortuF3 = entorno.ancho()/2;
		double YparaTortuF3 = entorno.alto()/2-270;
//		double XparaTortuF4 = entorno.ancho()/2;
//		double YparaTortuF4 = entorno.alto()/2-270;
//		double XparaTortuF5 = entorno.ancho()/2;
//		double YparaTortuF5 = entorno.alto()/2-270;

		
		//340 hasta el 465
		
		for (int g=0; g<gno.length; g++) {
			if (g==0 || g==1) {
				gno[g]= new Gnomo(XparaTortuF1,YparaTortuF1,20, 60, 1, 0, true, 1, null);
				XparaTortuF1=((entorno.ancho()/2)+390)-XparaTortuF1;
			}
			
			if (g==2 || g==3) {
				gno[g]= new Gnomo(XparaTortuF2,YparaTortuF2,20, 60, 1, 0, true, 1, null);
				XparaTortuF2=((entorno.ancho()/2)+400)-XparaTortuF2;
			}
			
			if (g==4 || g==5) {
				gno[g]= new Gnomo(XparaTortuF3,YparaTortuF3,20, 60, 1, 0, true, 1, null);
				XparaTortuF3=((entorno.ancho()/2)+420)-XparaTortuF3;
			}
//			
//			if (g==6 || g==11) {
//				gno[g]= new Gnomo(XparaTortuF4,YparaTortuF4,20, 60, 1, 0, true, 1, null);
//				XparaTortuF4=((entorno.ancho()/2)+440)-XparaTortuF4;
//			}
//			
//			if (g==12 || g==13) {
//				gno[g]= new Gnomo(XparaTortuF5,YparaTortuF5,20, 60, 1, 0, true, 1, null);
//				XparaTortuF5=((entorno.ancho()/2)+460)-XparaTortuF5;
//			}
		
	}
		
		
		
		
		
		
		
}
	
	
	
	
		
	public void Perdiste() {
		entorno.removeAll();
		entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
		entorno.cambiarFont("Arial", 50, Color.white);
		entorno.escribirTexto("GAME OVER ", entorno.ancho()/2, entorno.alto()/2);
	}

		
	public void ReiniciarPrincesa() {
		this.pep.setX(400);
		this.pep.setY(entorno.alto()-85);
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









