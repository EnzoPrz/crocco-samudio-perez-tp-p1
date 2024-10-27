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
	private Gnomo gnomos[] = new Gnomo [40];
	private int tiempo;
	private DisparoPersonaje disparoPersonaje;
	private ItemDisparo itemdisparo;
	private int contadorDisparos= 1000;
	private int puntaje=0;
	private int enemigos_eliminados=0;
	private BombaTortuga bombitas [] = new BombaTortuga [6];
	private Image fondo;
	private int salvados =0;
	private double[][] posicionesInicialesTortugas;
	private double[][] posicionesInicialesGnomos;
	
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
		RellenarJuegoConBombas(bombitas);
		RellenarJuegoConGnomos(gnomos);
		
		//gnomos= new Gnomo(entorno.ancho()/2,entorno.alto()/2-270,20, 60, 0, 0, false, 0.5);
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
		
//		pantalla perder
		if (pep==null) {
			Perder();
			return;
		}
			
		
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
		//gnomos.dibujar(entorno);
		
		
		
		
		
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
				this.disparoPersonaje=null;
				this.contadorDisparos--;
			}
		}
		
		//TORTUGAS ---> bombas
				int t = 0;
				for (t = 0; t < bombitas.length; t++) {
					if(this.bombitas[t]!=null) {
						this.bombitas[t].dibujarse(entorno);
						this.bombitas[t].moverBombaTortuga();
					
						if (this.bombitas[t].colisionBombaTortugaBorde(entorno)) {
							this.bombitas[t]=null;
						}
					}
					
					if(this.bombitas[5]==null) {
						RellenarJuegoConBombas(this.bombitas);
					}
					
				//COLISION BOMBITAS-DISPARO
					if(this.disparoPersonaje!=null && this.bombitas[t]!=null && colisionar(this.bombitas[t].getX(), this.bombitas[t].getY(), this.disparoPersonaje.getX(),this.disparoPersonaje.getY(),20)) {
						this.disparoPersonaje=null;
						this.contadorDisparos--;
						this.bombitas[t]=null;
					}
					
				//COLISION BOMBITAS-PRINCESA
					if(this.pep!=null && this.bombitas[t]!=null && colisionar(this.bombitas[t].getX(), this.bombitas[t].getY(), this.pep.getX(), this.pep.getY(),20)) {
						this.pep=null;
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
		
		
		
		
//////////////////////////////////////tortugas - pep- islas //////////////////////////////////////////////////////////////////////////
		
		// las tortugas caen
	
		int r;
		for (r=0;r<=this.tortugas.length-1;r++) {
			if(tortugas[r] != null) {
				tortugas[r].dibujar(entorno);
			
//			if (!tortugas[r].estaColisionandoPorAbajo(islas)) {
//				tortugas[r].moverHaciaAbajo(entorno);
//				tortugas[r].setEstaCayendo(true);
//			}
			
//			//se mueven
//			if(tortugas[r].estaColisionandoPorAbajo(islas)) {
//				tortugas[r].mover();
//			}
				
				if( !tortugas[r].estaColisionandoPorAbajo(islas)) {
					tortugas[r].moverHaciaAbajo(entorno);
					tortugas[r].setEstaCayendo(true);
				}
				
				// SE MUEVEN
				if (tortugas[r].estaColisionandoPorAbajo(islas)) {
					tortugas[r].mover();
				}
					
				for (Isla isla : islas) {
	                if (this.tortugas[r].estaColisionandoPorAbajo(new Isla[]{isla})) {
	                    this.tortugas[r].setIslaActual(isla);
	                    break;
	                }
				}
				
				//COLISION TORTUGAS-PEP
				if(this.pep!=null && colisionar(this.tortugas[r].getX(), this.tortugas[r].getY(), this.pep.getX(), this.pep.getY(), 30)) {
					this.pep=null;
				}
				
				//COLISION TORTUGAS-DISPARO	PEP
				if (this.disparoPersonaje!=null && colisionar(this.disparoPersonaje.getX(),this.disparoPersonaje.getY(), this.tortugas[r].getX(), this.tortugas[r].getY(),20)) {
					this.tortugas[r]=null;
					this.disparoPersonaje=null;
					this.contadorDisparos--;
					this.puntaje +=2;
					this.enemigos_eliminados +=1;
				}
			}
		}
		
		
		
		
		
		
////////////////////////////////  GNOMOS    //////////////////////////////////////////////////////		
		
		
	    // Verifica si el gnomo colisiona con las islas
		
//		if(!gnomos.getEstaCayendo()) {
//		    if (!gnomos.estaColisionandoPorAbajo(islas)) {
//		        gnomos.moverHaciaAbajo(entorno);
//		
//		    }
//	    
//		}
//	    if (gnomos.getEstaCayendo() &&  !gnomos.estaColisionandoPorAbajo(islas)) {
//	    	gnomos.moverHaciaAbajo(entorno);
//	    	gnomos.setEstaCayendo(false);
//	    }
//	    
//		
//	    if (!gnomos.getEstaCayendo()&& gnomos.estaColisionandoPorAbajo(islas)) {
//	    	gnomos.mover();
//		}
//		
//		
//		if(!gnomos.estaColisionandoPorDerecha(pep)) {
//			gnomos.mover();
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
		
		
		//////////////////////////////////////REAPARICION DE TORTUGAS Y GNOMOS//////////////////////////////////////
		// Reaparecer tortugas en sus posiciones originales
		for (int i = 0; i < tortugas.length; i++) {
		    if (tortugas[i] == null) {
		        double x = posicionesInicialesTortugas[i][0];
		        double y = posicionesInicialesTortugas[i][1];
		        tortugas[i] = new Tortuga(x, y, 27, 50, 0.5, 2, 'd', false, 0.5);
		    }
		}

		// Reaparecer gnomos en sus posiciones originales
		for (int j = 0; j < gnomos.length; j++) {
		    if (gnomos[j] == null) {
		        double x = posicionesInicialesGnomos[j][0];
		        double y = posicionesInicialesGnomos[j][1];
		        gnomos[j] = new Gnomo(x, y, 20, 60, 1, 0, true, 1, null);
		    }
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
		this.posicionesInicialesTortugas = new double[t.length][2];
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
				t[i] = new Tortuga (XparaTortuF1, YparaTortuF1-170, 27, 50, 0.5, 2, 'd', false, 0.5 );
				 this.posicionesInicialesTortugas[i][0] = XparaTortuF1; 
		         this.posicionesInicialesTortugas[i][1] = YparaTortuF1 - 170;
		         XparaTortuF1 = (XparaTortuF1 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF1 : entorno.ancho() / 2;
			}
			
			if (i==2 || i==3) {
				t[i] = new Tortuga (XparaTortuF2, YparaTortuF2-170, 27, 50, 0.5, 1.5, 'd', false, 0.5 );
				this.posicionesInicialesTortugas[i][0] = XparaTortuF2;
	            this.posicionesInicialesTortugas[i][1] = YparaTortuF2 - 170;
	            XparaTortuF2 = (XparaTortuF2 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF2 : entorno.ancho() / 2;
			}
			if (i==4 || i==5) {
				t[i] = new Tortuga (XparaTortuF3, YparaTortuF3-170, 27, 50, 0.5, 1, 'd', false, 0.5 );
				this.posicionesInicialesTortugas[i][0] = XparaTortuF3;
	            this.posicionesInicialesTortugas[i][1] = YparaTortuF3 - 170;
	            XparaTortuF3 = (XparaTortuF3 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF3 : entorno.ancho() / 2;
			}
			if (i==6 || i==7) {
				t[i] = new Tortuga (XparaTortuF4, YparaTortuF4-50, 27, 50, 0.5, 1, 'd', false, 0.5 );
				this.posicionesInicialesTortugas[i][0] = XparaTortuF4;
	            this.posicionesInicialesTortugas[i][1] = YparaTortuF4 - 50;
	            XparaTortuF4 = (XparaTortuF4 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF4 : entorno.ancho() / 2;
			}
			
		}
	}
	
	public void RellenarJuegoConGnomos(Gnomo [] gno) {
		this.posicionesInicialesGnomos = new double[gno.length][2];
		double XparaGnomoF1 = entorno.ancho()/2;
		double YparaGnomoF1 = entorno.alto()/2-270;
		double XparaGnomoF2 = entorno.ancho()/2;
		double YparaGnomoF2 = entorno.alto()/2-270;
		double XparaGnomoF3 = entorno.ancho()/2;
		double YparaGnomoF3 = entorno.alto()/2-270;
	
		
		
		for (int g=0; g<gno.length; g++) {
			if (g==0 || g==1) {
				gno[g]= new Gnomo(XparaGnomoF1,YparaGnomoF1,20, 60, 1, 0, true, 1, null);
				this.posicionesInicialesGnomos[g][0] = XparaGnomoF1;
	            this.posicionesInicialesGnomos[g][1] = YparaGnomoF1;
	            XparaGnomoF1 = (XparaGnomoF1 < entorno.ancho() / 2) ? entorno.ancho() - XparaGnomoF1 : entorno.ancho() / 2;
			}
			
			if (g==2 || g==3) {
				gno[g]= new Gnomo(XparaGnomoF2,YparaGnomoF2,20, 60, 1, 0, true, 1, null);
				this.posicionesInicialesGnomos[g][0] = XparaGnomoF2;
	            this.posicionesInicialesGnomos[g][1] = YparaGnomoF2;
	            XparaGnomoF2 = (XparaGnomoF2 < entorno.ancho() / 2) ? entorno.ancho() - XparaGnomoF2 : entorno.ancho() / 2;

			}
			
			if (g==4 || g==5) {
				gno[g]= new Gnomo(XparaGnomoF3,YparaGnomoF3,20, 60, 1, 0, true, 1, null);
				this.posicionesInicialesGnomos[g][0] = XparaGnomoF3;
	            this.posicionesInicialesGnomos[g][1] = YparaGnomoF3;
	            XparaGnomoF3 = (XparaGnomoF3 < entorno.ancho() / 2) ? entorno.ancho() - XparaGnomoF3 : entorno.ancho() / 2;
			}
			
		}
		
	}
	
	public void RellenarJuegoConBombas(BombaTortuga [] bombatortu) {
		for (int g=0; g<bombatortu.length; g++) {
			if(this.tortugas[g]!=null && bombatortu[g]==null) {
				bombatortu[g]= new BombaTortuga (this.tortugas[g].getX(), this.tortugas[g].getY()+12, 20,20, this.tortugas[g].getDireccion(),1,1);
			}
		}
	}
	
	
	
	
	public void Perder() {
		entorno.removeAll();
		entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
		entorno.cambiarFont("Arial", 50, Color.white);
		entorno.escribirTexto(" GAME OVER ", entorno.ancho()/2, entorno.alto()/2);
	}
	
	public void ReiniciarPep() {
		this.pep.setX(20);
		this.pep.setY(40);
	}
	
	
	
	public boolean colisionar(double x1, double y1, double x2, double y2, double dist) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < dist;
	}
	


	@SuppressWarnings("unused")
	public static void main(String[] args){
		Juego juego = new Juego();
	}
	
	
	
	
	
	
}









