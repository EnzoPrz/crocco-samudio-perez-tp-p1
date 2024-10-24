package juego;

import java.awt.Color;


import entorno.Entorno;


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
		
		
		
		
		
		
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.green);
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
		this.x+=movimientoHorizontal*velocidad;
		
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





	

	
	
	
	

