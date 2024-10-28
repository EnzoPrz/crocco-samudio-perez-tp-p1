package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class casaGnomos {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img;
	
	public casaGnomos (double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img= Herramientas.cargarImagen("casagnomo.png");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void aparecer(Entorno e) {
		 e.dibujarImagen(img, this.x, this.y, 0, 0.200);
		
	}

}
