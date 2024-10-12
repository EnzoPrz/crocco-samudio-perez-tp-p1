package juego;

import java.awt.Color;

import entorno.Entorno;

public class Personaje {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double desplazamiento;
	
	public Personaje(double x, double y, double ancho, double alto, double desplazamiento) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.desplazamiento = desplazamiento;
	}

	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.red);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	
	public boolean colisionaPorArriba(Entorno entorno) {
		if(this.y - this.alto/2 <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean colisionaPorAbajo(Entorno entorno) {
		if(this.y + this.alto/2 >= entorno.alto()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean colisionaPorDerecha(Entorno e) {
		if(this.x + this.ancho/2 >= e.ancho()) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean colisionaPorIzquierda(Entorno e) {
		if(this.x - this.ancho/2 <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void moverDerecha() {
		this.x += this.desplazamiento; 
	}
	
	public void moverIzquierda() {
		this.x -= this.desplazamiento; 
	}

	public void moverArriba() {
		this.y -= this.desplazamiento; 
		
	}
	public void moverAbajo() {
		this.y += this.desplazamiento; 
		
	}
}
