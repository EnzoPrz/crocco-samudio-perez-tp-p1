package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Gnomo {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private double velocidad;
	private double desplazamiento;
	private boolean estaCayendo;
	private double movimientoHorizontal;
	private Random random;
	private Isla islaActual;
	private static final int IZQUIERDA = -1;
	private static final int DERECHA = 1;
	private int direccionMovimiento;

	private boolean puedeCambiarDireccion; // Controla el cambio de dirección
	private int framesSinCambiar; // Contador de frames sin cambiar dirección
	private Image img;
	
	
	public Gnomo(double x, double y, int ancho, int alto, double velocidad, double desplazamiento, boolean estaCayendo,double movH, Random random) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 2;
		this.desplazamiento = desplazamiento;
		this.estaCayendo= estaCayendo;
		this.movimientoHorizontal = movH;
		this.random = new Random();
		this.islaActual= islaActual;
		this.direccionMovimiento = (Math.random() < 0.5) ? IZQUIERDA : DERECHA;
		this.puedeCambiarDireccion = true; // Permitir el cambio al principio
		this.framesSinCambiar = 0; // Contador inicial
		img= Herramientas.cargarImagen("gnomos.png");

	}
	
	public void dibujar(Entorno e) {
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.yellow);
		 e.dibujarImagen(img, this.x, this.y, 0, 0.10);
		
	}
	
	
	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
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



	public void mover() {
		this.x-=movimientoHorizontal*velocidad;
		
	}

	public void mover(Isla[] islas) {
		
		
		this.x -= direccionMovimiento * velocidad;
	
		
		if (estaColisionandoPorAbajo(islas) && puedeCambiarDireccion) {
	        cambiarMovimientoHorizontalIzq(); // Cambia dirección solo si puede
	        puedeCambiarDireccion = false; // Desactivar cambio de dirección hasta que caiga de nuevo
	        framesSinCambiar = 0; // Reiniciar contador
	    }
	    // Incrementar el contador de frames
		if (estaColisionandoPorAbajo(islas)) {
		    framesSinCambiar++;
		    if (framesSinCambiar >= 70) { // Cambiar dirección después de 60 frames
		        puedeCambiarDireccion = true; // Permitir el cambio nuevamente
		    }
		}
	
}
	
	public void cambiarMovimientoHorizontalIzq() {
		this.movimientoHorizontal*=-1;
		direccionMovimiento = (Math.random() < 0.5) ? IZQUIERDA : DERECHA;
	}
	
	public boolean isFueraDeLimites(Isla[] islas) {
	    for (Isla isla : islas) {
	        if (isla == null) continue;
	        double bordeIzquierdoIsla = isla.getX() - isla.getAncho() / 2;
	        double bordeDerechoIsla = isla.getX() + isla.getAncho() / 2;
	        if (this.x < bordeIzquierdoIsla || this.x > bordeDerechoIsla) {
	            return true; // El gnomo está fuera de los límites de la isla
	        }
	    }
	    return false; // El gnomo está dentro de los límites
	}
	
	public void moverHaciaAbajo(Entorno e) {
		this.y+=velocidad;			
	}
	
	
	public Isla getIslaActual() {
	    return islaActual;
	}
	
	public void setIslaActual(Isla islaActual) {
	    this.islaActual= islaActual;
	}
	
//	public void moverDerecha(Entorno e) {
//		this.x+=velocidad;
//	}
	
	
	
	public boolean estaColisionandoPorAbajo(Isla[] islas) {		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			double bordeInferiorGnomo = this.y + (this.alto / 2);
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

	
	public boolean estaColisionandoPorAbajo(Entorno e) {
		if(this.y+(this.alto/2) >= e.alto()) {
			return true;
		}
		return false;
	}
	
//	public boolean cayoAlVacio(double entorno e) {
//	    double bordeInferiorGnomo = this.y + (this.alto / 2);
//	    if (bordeInferiorGnomo >= e) {
//	        return true;
//	    } else {
//	        return false;
//	    }
//	}
	
	
//	public boolean estaColisionandoPorDerecha(Personaje pep) {	
//		if(pep==null) {
//			return false;
//		}
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
