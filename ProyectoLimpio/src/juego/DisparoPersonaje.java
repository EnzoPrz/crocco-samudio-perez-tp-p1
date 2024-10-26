package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class DisparoPersonaje {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private int desplazamiento;
	private int velocidad;
	private int direccion;
	Image[]img;
	
	
	
public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

public DisparoPersonaje(double x, double y, int ancho, int alto, int desplazamiento, int velocidad, char direccion) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.desplazamiento = desplazamiento;
		this.velocidad = velocidad;
		this.direccion = direccion;
		this.img = new Image[2];
		
		this.img[0] = Herramientas.cargarImagen("boladefuegod.png"); // imagen derecha
		this.img[1] = Herramientas.cargarImagen("boladefuegoi.png"); // imagen izquierda
	}

	public void dibujarse(Entorno e) {
		int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
		e.dibujarImagen(img[indiceImagen], this.x, this.y, 0, 0.055);
		
	}
	
	public boolean colisionDisparoBorde(Entorno e) {
		if(this.x+this.ancho/2 >= e.ancho()  || this.x-this.ancho/2<=0) {
			return true;
		}
		return false;
	}
	
	public void moverDisparo() {
		if (this.direccion == 'd') {
			this.x += this.desplazamiento*this.velocidad;
		}
		if (this.direccion == 'i') {
			this.x -= this.desplazamiento*this.velocidad;
		}
	}

}
