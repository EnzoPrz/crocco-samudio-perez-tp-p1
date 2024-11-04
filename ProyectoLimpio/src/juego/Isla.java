package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Isla {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	Image imgisla;
	
	
	
	public Isla(int x, int y, int ancho, int alto) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		imgisla= Herramientas.cargarImagen("imagenislas.png");
	}


	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imgisla, this.x, this.y, 0, 0.35);
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

	
}
