package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Tortuga {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private double velocidad;
	private double desplazamiento;
	private boolean estaCayendo;
	private double movimientoHorizontal;
	private char direccion;
	private Isla islaActual;
	Image img[];

	;
	
	
	public Tortuga(double x, double y, int ancho, int alto,double desplazamiento, double velocidad,char direccion, boolean estaCayendo,  double movH) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.desplazamiento= desplazamiento;
		this.velocidad = velocidad;
		this.direccion = direccion;
		this.estaCayendo= estaCayendo;
		this.movimientoHorizontal = movH;
		this.islaActual = islaActual;
		this.img = new Image[2];
		
		this.img[0] = Herramientas.cargarImagen("tortugamalad.png"); // imagen derecha
		this.img[1] = Herramientas.cargarImagen("tortugamalai.png"); // imagen izquierda
		
		
		
		
		
		
	}
	
	public void dibujar(Entorno e) {
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.green);
		
		int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
		e.dibujarImagen(img[indiceImagen], this.x, this.y, 0, 0.10);
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
	
	
	public boolean getEstaCayendo() {
		return estaCayendo;
	}

	
	public void  setEstaCayendo(boolean estaCayendo) {
		this.estaCayendo = estaCayendo;
	}

	
	public void moverHaciaAbajo(Entorno e) {
		this.y+=velocidad;			
	}
	
	public double getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(double desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
	
	
	public void mover() {
		
		if (islaActual != null) {
	        double bordeIzquierdoIsla = islaActual.getX() - (islaActual.getAncho() / 2);
	        double bordeDerechoIsla = islaActual.getX() + (islaActual.getAncho() / 2);

	        this.x += movimientoHorizontal * velocidad;

	        
	        if (this.x - (this.ancho/2) <= bordeIzquierdoIsla) {
	            this.x = bordeIzquierdoIsla + (this.ancho/2);
	            cambiarMovimientoHorizontal();
	            this.direccion='d';
	        } else if (this.x + (this.ancho/2) >= bordeDerechoIsla) {
	        
	            this.x = bordeDerechoIsla - (this.ancho/2);
	            cambiarMovimientoHorizontal();
	            this.direccion='i';
	        }
	    }
	}
	
	
	public void cambiarMovimientoHorizontal() {
		
		this.movimientoHorizontal*=-1;
	}
	

	public char getDireccion() {
		return direccion;
	}


	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}


	public Isla getIslaActual() {
	    return islaActual;
	}

	public void setIslaActual(Isla islaActual) {
	    this.islaActual = islaActual;
	}
	
	
	
	public boolean estaColisionandoPorAbajo(Isla[] islas) {		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			double bordeInferiorTortuga = this.y + (this.alto / 2);
		    float bordeSuperiorIsla = isla.getY() - (isla.getAlto() / 2);	
			
			if(bordeInferiorTortuga>=bordeSuperiorIsla && bordeInferiorTortuga<=bordeSuperiorIsla +velocidad) {
				if(this.x+(this.ancho/2) > isla.getX()-(isla.getAncho()/2)  &&  this.x-(this.ancho/2) < isla.getX()+(isla.getAncho()/2)) {
					this.y=(int) bordeSuperiorIsla-(this.alto/2);
					return true;
				}
			}			
		}
		return false;
	}
	
	
	
	public boolean estaColisionandoPorDerecha(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}					
			double bordeDerechoTortuga = this.x + (this.ancho / 2);
		    float bordeDerechoIsla = isla.getX() + (isla.getAncho() / 2 );	
			
			if(bordeDerechoTortuga <=bordeDerechoIsla && bordeDerechoTortuga >=bordeDerechoIsla -velocidad) {
				if(this.x - (this.ancho / 2) <  isla.getY() - (isla.getAlto() / 2 )  /* && this.y-(this.alto/2) < isla.getY()-(isla.getAlto()/2)*/) {
					this.x=(int) bordeDerechoIsla-(this.ancho/2);
					return true;
				}
			}			
		}
		return false;
		
	}

	public boolean estaColisionandoPorIzquierda(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}					
			double bordeIquierdoTortuga = this.x - (this.ancho / 2);
		    float bordeizquierdoIsla = isla.getX() - (isla.getAncho() / 2);	
			
			if(bordeIquierdoTortuga >=bordeizquierdoIsla && bordeIquierdoTortuga <=bordeizquierdoIsla +velocidad) {
				if(this.x+(this.ancho/2) > isla.getY()+(isla.getAlto()/2) /* &&  this.y-(this.alto) < isla.getY()+(isla.getAlto()*/) {
					this.x=(int) bordeizquierdoIsla+(this.ancho/2);
					return true;
				}
			}			
		}
		return false;
	}
	
	
}	





	

	
	
	
	

