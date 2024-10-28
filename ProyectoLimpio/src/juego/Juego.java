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
		private int perdidos= 0;
		private double[][] posicionesInicialesTortugas;
		private double[][] posicionesInicialesGnomos;
		private boolean inicio;
		private int Iniciojuego;
		
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
			RellenarJuegoConBombas(bombitas);
			RellenarJuegoConGnomos(gnomos);

			// Inicia el juego!
			this.entorno.iniciar();
			this.Iniciojuego = 0;

					
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
			
			//menu
			if (pep != null && Iniciojuego == 0) {

				inicio = true;

				if (inicio) {
//					entorno.removeAll();
					//entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
					entorno.cambiarFont("Arial", 50, Color.white);
					entorno.escribirTexto("  MENU", entorno.ancho()/2-100, entorno.alto()/2-200);
					entorno.cambiarFont("Arial", 40, Color.white);
					entorno.escribirTexto("  TOCA 'ESCAPE' PARA SALIR", entorno.ancho()/2-400, entorno.alto()/2+160);
					entorno.cambiarFont("Arial", 40, Color.white);
					entorno.escribirTexto("  TOCA 'ENETR' PARA COMENZAR", entorno.ancho()/2-400, entorno.alto()/2+80);
//					entorno.dibujarImagen(letramenu3, 178, 550, 0);

					if (entorno.sePresiono(entorno.TECLA_ENTER)) {
						Iniciojuego = 1;
						inicio = false;

					} else if (entorno.sePresiono(entorno.TECLA_ESCAPE)) { // SI TOCAS LA S SALIS DEL JUEGO
						System.exit(0); // Cerrar la aplicación
					}

				}
			}
			
//			public void Ganaste() {
//				entorno.removeAll();
//				entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
//				entorno.cambiarFont("Arial", 50, Color.white);
//				entorno.escribirTexto("    Ganaste!", entorno.ancho()/2-170, entorno.alto()/2);
//			}
			
			if (Iniciojuego == 1) {
			
//			pantalla perder
			if (pep==null) {
				Perder();
				return;
			}
			
			if(salvados==7) {
				Ganaste();
				return;
			}
				
			
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
				if (!pep.estaColisionandoPorArriba(islas) && !pep.estaColisionandoPorArriba(entorno)&& pep.getY() > 300) {
					pep.moverHaciaArriba(entorno) ;
				}else {
					pep.setEstaSaltando(false);
					
				}
			}
			
			
			
			///////disparo personaje pep ---> disparo


			if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && this.disparoPersonaje==null && this.contadorDisparos>0 && this.contadorDisparos<=1000) {
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
			
			entorno.escribirTexto("Enemigos eliminados: " + enemigos_eliminados, 3, 40);
			entorno.escribirTexto("Puntaje: " + puntaje, 3, 60);
			
			
			
			
	////////////////////////////////  GNOMOS    //////////////////////////////////////////////////////		
			
			
		    // Verifica si el gnomo colisiona con las islas			
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
					

					
//					//colision gnomos con tortugas
//					if(tortugas[t] != null && colisionar(this.gnomos[w].getX(), this.gnomos[w].getY(), tortugas[t].getX(), tortugas[t].getY(), 30)) {
//						this.gnomos[w]=null;
//						perdidos++;
//					}
				
				}
			}
			
			entorno.escribirTexto("Gnomos perdidos: " + perdidos, 3, 100);
			entorno.escribirTexto("Gnomos salvados: " + salvados, 3, 20);
			entorno.escribirTexto("tiempo:"+entorno.numeroDeTick(),3 , 80);
			
	}
/////////////////////////////////////REAPARICION DE TORTUGAS Y GNOMOS//////////////////////////////////////////////////////			
			//reaparecer tortugas
			for(int i=0; i < tortugas.length-1; i++) {
				if(tortugas[i]== null) {
					double x= posicionesInicialesTortugas[i][0];
					double y= posicionesInicialesTortugas[i][1];
					tortugas[i] = new Tortuga (x, y, 27, 50, 0.5, 2, 'd', false, 0.5 );
				}
			}
			
			//reaparecer gnomos
			for(int j=0; j <gnomos.length; j++) {
				if(gnomos[j]== null) {
					double x= posicionesInicialesGnomos[j][0];
					double y= posicionesInicialesGnomos[j][1];
					gnomos[j] = new Gnomo (x, y, 20, 60, 1, 0,true, 1, null );
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
			double XparaTortuF4 = 310;
			double YparaTortuF4 = entorno.alto()/2;
			
			for (int i=0; i < t.length; i++) {
				if (i==0 || i==1) {
					t[i] = new Tortuga (XparaTortuF1, YparaTortuF1-170, 27, 50, 0.5, 2, 'd', false, 0.5 );
					this.posicionesInicialesTortugas[i][0] = XparaTortuF1; 
			        this.posicionesInicialesTortugas[i][1] = YparaTortuF1 - 170;
					XparaTortuF1=entorno.ancho()-XparaTortuF1;
				}
				
				if (i==2 || i==3) {
					t[i] = new Tortuga (XparaTortuF2, YparaTortuF2-170, 27, 50, 0.5, 1.5, 'd', false, 0.5 );
					this.posicionesInicialesTortugas[i][0] = XparaTortuF2;
		            this.posicionesInicialesTortugas[i][1] = YparaTortuF2 - 170;
					XparaTortuF2=entorno.ancho()-XparaTortuF2;
				}
				if (i==4 || i==5) {
					t[i] = new Tortuga (XparaTortuF3, YparaTortuF3-170, 27, 50, 0.5, 1, 'd', false, 0.5 );
					this.posicionesInicialesTortugas[i][0] = XparaTortuF3;
		            this.posicionesInicialesTortugas[i][1] = YparaTortuF3 - 170;
					XparaTortuF3=entorno.ancho()-XparaTortuF3;
				}
				if (i==6 || i==7) {
					t[i] = new Tortuga (XparaTortuF4, YparaTortuF4-60, 27, 50, 0.5, 1, 'd', false, 0.5 );
					this.posicionesInicialesTortugas[i][0] = XparaTortuF4;
		            this.posicionesInicialesTortugas[i][1] = YparaTortuF4 - 50;
					XparaTortuF4=entorno.ancho()-XparaTortuF4;
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
					gno[g]= new Gnomo(XparaGnomoF1,YparaGnomoF1,20, 60, 1.5, 0, true, 1, null);
					this.posicionesInicialesGnomos[g][0] = XparaGnomoF1;
		            this.posicionesInicialesGnomos[g][1] = YparaGnomoF1;
					XparaGnomoF1=((entorno.ancho()/2)+390)-XparaGnomoF1;
				}
				
				if (g==2 || g==3) {
					gno[g]= new Gnomo(XparaGnomoF2,YparaGnomoF2,20, 60, 1.5, 0, true, 1, null);
					this.posicionesInicialesGnomos[g][0] = XparaGnomoF2;
		            this.posicionesInicialesGnomos[g][1] = YparaGnomoF2;
					XparaGnomoF2=((entorno.ancho()/2)+400)-XparaGnomoF2;
				}
				
				if (g==4 || g==5) {
					gno[g]= new Gnomo(XparaGnomoF3,YparaGnomoF3,20, 60, 1.5, 0, true, 1, null);
					this.posicionesInicialesGnomos[g][0] = XparaGnomoF3;
		            this.posicionesInicialesGnomos[g][1] = YparaGnomoF3;
					XparaGnomoF3=((entorno.ancho()/2)+420)-XparaGnomoF3;
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
		
		
		public void Ganaste() {
			entorno.removeAll();
			entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
			entorno.cambiarFont("Arial", 50, Color.white);
			entorno.escribirTexto("    Ganaste!", entorno.ancho()/2-170, entorno.alto()/2);
		}
		
		public void Perder() {
			entorno.removeAll();
			entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, 1000, 1000, 0, Color.black);
			entorno.cambiarFont("Arial", 50, Color.white);
			entorno.escribirTexto("!!!GAME OVER¡¡¡", entorno.ancho()/2-170, entorno.alto()/2);
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









