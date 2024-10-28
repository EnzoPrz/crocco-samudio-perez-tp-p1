package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ItemDisparo {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img;
	
	public ItemDisparo (double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img= Herramientas.cargarImagen("boladefuegoi.jpg");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void aparecer(Entorno e) {
		 e.dibujarImagen(img, this.x, this.y, 0, 0.10);
		//e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.YELLOW);
	}

	
	
//	int alturaEntorno = 600;
//	int w;
//	for (w=0 ; w<=gnomos.length-1;w++) {
//		if(gnomos[w] != null) {
//			gnomos[w].dibujar(entorno);
//		
//			if( !gnomos[w].estaColisionandoPorAbajo(islas)) {
//				gnomos[w].moverHaciaAbajo(entorno);
//				
//			}
//			
//			
//			// SE MUEVEN
//			if (gnomos[w].estaColisionandoPorAbajo(islas)) {
//				gnomos[w].mover(islas);
//			}
//			
//			//COLISION gnompos con pep
//			if(this.pep!=null && colisionar(this.gnomos[w].getX(), this.gnomos[w].getY(), this.pep.getX(), this.pep.getY(), 30)) {
//				this.gnomos[w]=null;
//				salvados++;
//				puntaje+=5;
//			}
//			
//			//COLISION Gnomos perdidos
//	if(gnomos[w].cayoAlVacio(alturaEntorno)){
//	this.gnomos[w]=null;
//	gnomosPerdidos++;
//}
//			
//			//COLISION Gnomos con tortugas
//			for (r=0;r<=this.tortugas.length-1;r++) {
//				if(tortugas[r] != null){
//					if(colisionar(this.gnomos[w].getX(), this.gnomos[w].getY(), this.tortugas[r].getX(), this.tortugas[r].getY(), 30)) {
//						this.gnomos[w]=null;
//						gnomosPerdidos++;
//					}
//				}
//			}
//		}
//	}
//
//	entorno.escribirTexto("Gnomos Salvados: " + salvados, 3, 20);
//	entorno.escribirTexto("Gnomos Perdidos: " + gnomosPerdidos, 180, 20);
//	entorno.escribirTexto("Puntaje: " + puntaje, 400, 20);
//	entorno.escribirTexto("Enemigos Eliminados: " + enemigos_eliminados, 550, 20);
//	
	
}
