package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private char direccion;
	private int desplazamiento;
	private double velocidad;
	Image []img;
	
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


public BolaDeFuego(double x, double y, int ancho, int alto, char direccion, int desplazamiento, double velocidad) {
	this.x = x;
	this.y = y;
	this.ancho = ancho;
	this.alto = alto;
	this.direccion = direccion;
	this.desplazamiento = desplazamiento;
	this.velocidad = velocidad;
	this.img = new Image[2];
	
	this.img[0] = Herramientas.cargarImagen("boladefuegod.jpg"); // imagen derecha
	this.img[1] = Herramientas.cargarImagen("boladefuegoi.jpg"); // imagen izquierda
	}

public void dibujarse(Entorno e) {
	int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
	e.dibujarImagen(img[indiceImagen], this.x, this.y, 0, 0.08);
	
}

public void moverBomba() {
	if (this.direccion == 'd') {
		this.x += this.desplazamiento*this.velocidad;
	}
	if (this.direccion == 'i') {
		this.x -= this.desplazamiento*this.velocidad;
	}
}
}
