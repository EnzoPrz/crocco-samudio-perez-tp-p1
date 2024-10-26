package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class BombaTortuga {
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


public BombaTortuga(double x, double y, int ancho, int alto, char direccion, int desplazamiento, double velocidad) {
	this.x = x;
	this.y = y;
	this.ancho = ancho;
	this.alto = alto;
	this.direccion = direccion;
	this.desplazamiento = desplazamiento;
	this.velocidad = velocidad;
	this.img = new Image[2];
	
	this.img[0] = Herramientas.cargarImagen("bombatortugad.png"); // imagen derecha
	this.img[1] = Herramientas.cargarImagen("bombatortugai.png"); // imagen izquierda
	}

public void dibujarse(Entorno e) {
	int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
	e.dibujarImagen(img[indiceImagen], this.x, this.y, 0, 0.08);
	
}

public void moverBombaTortuga() {
	if (this.direccion == 'd') {
		this.x += this.desplazamiento*this.velocidad;
	}
	if (this.direccion == 'i') {
		this.x -= this.desplazamiento*this.velocidad;
	}
}
public boolean colisionBombaTortugaBorde(Entorno e) {
	if(this.x+this.ancho/2 >= e.ancho()  || this.x-this.ancho/2<=0) {
		return true;
	}
	return false;
}

}
