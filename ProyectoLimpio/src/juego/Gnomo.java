package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Gnomo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double velocidad;
	private double desplazamiento;
	private boolean estaCayendo;
	private double movimientoHorizontal;
	private Random random;
	//private int nuevoX;
	
	
	
	
	public Gnomo(int x, int y, int ancho, int alto, double velocidad, double desplazamiento, boolean estaCayendo,double movH, Random random/*int nuevoX*/) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.desplazamiento = desplazamiento;
		this.estaCayendo= estaCayendo;
		this.movimientoHorizontal = movH;
		this.random = new Random();
		//this.nuevoX= nuevoX;

	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.yellow);
	}
	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getAncho() {
		return ancho;
	}



	public void setAncho(int ancho) {
		this.ancho = ancho;
	}



	public int getAlto() {
		return alto;
	}



	public void setAlto(int alto) {
		this.alto = alto;
	}



	public double getVelocidad() {
		return velocidad;
	}



	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}



	public boolean getEstaCayendo() {
		return estaCayendo;
	}



	public void setEstaCayendo(boolean estaCayendo) {
		this.estaCayendo = estaCayendo;
	}


	public void moverRandom(Isla [] islas) {
		int dir = random.nextInt(2); // 0: izquierda, 1: derecha, 2: arriba, 3: abajo
	    int nuevoX = x;
	    int nuevoY = y;

	    switch (dir) {
	        case 0: // Mover a la izquierda
	            nuevoX -= movimientoHorizontal*velocidad;
	            break;
	        case 1: // Mover a la derecha
	            nuevoX += movimientoHorizontal*velocidad;
	            break;
			
		}
	    
	 // Verificar si el nuevo movimiento está dentro de la isla
	    if (nuevoX >= islas.length - ancho / 2 && nuevoX <= islas.length + islas.length / 2 &&
	        nuevoY >= islas.length - alto / 2 && nuevoY <= islas.length + islas.length / 2) {
	        // Actualizar la posición
	        x = nuevoX;
	        y = nuevoY;
	    }
	    
		
	}	
	
	
	public void mover() {
		this.x-=movimientoHorizontal*velocidad;
		
	}

	public void cambiarMovimientoHorizontal() {
		this.movimientoHorizontal*=-1;
	}
	
	
	public void moverHaciaAbajo(Entorno e) {
		this.y+=velocidad;			
	}
	
	
	public void moverDerecha(Entorno e) {
		this.x+=velocidad;
	}
	
	
	
	public boolean estaColisionandoPorAbajo(Isla[] islas) {		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			float bordeInferiorGnomo = this.y + (this.alto / 2);
		    float bordeSuperiorIsla = isla.getY() - (isla.getAlto() / 2);	
			
			if(bordeInferiorGnomo>=bordeSuperiorIsla && bordeInferiorGnomo<=bordeSuperiorIsla +velocidad) {
				if(this.x+(this.ancho/2) > isla.getX()-(isla.getAncho()/2)  &&  this.x-(this.ancho/2) < isla.getX()+(isla.getAncho()/2)) {
					this.y=(int) bordeSuperiorIsla-(this.alto/2);
					return true;
				}
			}			
		}
		return false;
	}

	
	
	
	
	
//	public boolean estaColisionandoPorDerecha(Personaje pep) {			
//			float bordeDerechoGnomo = this.x + (this.ancho / 2);
//		    float bordeIzquierdPersonaje = pep.getX() - (pep.getAncho() / 2);	
//			
//			if(bordeDerechoGnomo <=bordeIzquierdPersonaje && bordeDerechoGnomo >=bordeIzquierdPersonaje -velocidad) {
//				if(this.y+(this.alto/2) > pep.getY()-(pep.getAlto()/2)  /*&&  this.y-(this.alto/2) < pep.getY()+(pep.getAlto()/2)*/) {
//					this.x=(int) bordeIzquierdPersonaje-(this.ancho/2);
//					return true;
//				}
//			}			
//			return false;
//		
//	}
//	
//	
//	public boolean estaColisionandoPorIzquierda(Personaje pep) {
//			float bordeIzquierdoGnomo = this.x - (this.ancho / 2);
//		    float bordeDerechoPersonaje = pep.getX() + (pep.getAncho() / 2);	
//			
//			if(bordeIzquierdoGnomo >=bordeDerechoPersonaje && bordeIzquierdoGnomo <=bordeDerechoPersonaje +velocidad) {
//				if(this.y-(this.alto/2) < pep.getY()+(pep.getAlto()/2) /* &&  this.y+(this.alto/2) > pep.getY()-(pep.getAlto()/2)*/) {
//					this.x=(int) bordeDerechoPersonaje+(this.ancho/2);
//					return true;
//				}
//			}			
//			return false;
//	}
	
	
}
