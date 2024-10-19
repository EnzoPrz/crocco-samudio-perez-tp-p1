package juego;

import java.awt.Color;

import entorno.Entorno;

public class Tortuga {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private double desplazamiento;
	private boolean estaCayendo;
	;
	
	
	public Tortuga(int x, int y, int ancho, int alto, int velocidad, boolean estaCayendo, double desplazamiento) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 2;
		this.estaCayendo= estaCayendo;
		this.desplazamiento= desplazamiento;
		
		
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.green);
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
	
	
	public boolean getEstaCayendo() {
		return estaCayendo;
	}

	
	public void  setEstaCayendo(boolean estaCayendo) {
		this.estaCayendo = estaCayendo;
	}

	
	
	public boolean estaColisionandoPorAbajo(Isla[] islas) {		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			float bordeInferiorTortuga = this.y + (this.alto / 2);
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

	public void moverHaciaAbajo(Entorno e) {
			this.y+=velocidad;			
	}
	
	
	

	
	
	
	
	
}
