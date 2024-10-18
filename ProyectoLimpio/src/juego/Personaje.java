package juego;

import java.awt.Color;

import entorno.Entorno;

public class Personaje {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	
	
	public Personaje(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad=4;
	}

	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
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
	
	public void moverDerecha(Entorno e) {
			this.x+=velocidad;
	}
	
	public void moverIzquierda(Entorno e) {
			this.x-=velocidad;	
	}
	
	public void moverHaciaAbajo(Entorno e) {
			this.y+=velocidad;			
	}
	
	public void moverHaciaArriba(Entorno entorno) {
		this.y-=velocidad;				
	}
	
	public boolean estaColisionandoPorAbajo(Entorno e) {
		if(this.y+(this.alto/2) >= e.alto()) {
			return true;
		}
		return false;
	}
	
	public boolean estaColisionandoPorArriba(Entorno e) {
		if(this.y-(this.alto/2) <= 0) {
			return true;
		}
		return false;
	}
	public boolean estaColisionandoPorDerecha(Entorno e) {
		if(this.x+(this.ancho/2) >= e.ancho()) {
			return true;
		}
		return false;
	}
	public boolean estaColisionandoPorIzquierda(Entorno e) {
		if(this.x-(this.ancho/2) <= 0) {
			return true;
		}
		return false;
	}


	public boolean estaColisionandoPorArriba(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			float bordeSuperiorPersonaje = this.y - (this.alto / 2);
		    float bordeInferiorIsla = isla.getY() + (isla.getAlto() / 2);
		    
		    if(bordeSuperiorPersonaje <= bordeInferiorIsla && bordeSuperiorPersonaje>= bordeInferiorIsla-velocidad) {
				if(this.x+(this.ancho/2) > isla.getX()-(isla.getAncho()/2)  &&  this.x-(this.ancho/2) < isla.getX()+(isla.getAncho()/2)) {
					this.y=(int) bordeInferiorIsla+(this.alto/2);
					return true;
				}
			}			
		}	
		return false;
	}
	
	public boolean estaColisionandoPorAbajo(Isla[] islas) {		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			float bordeInferiorPersonaje = this.y + (this.alto / 2);
		    float bordeSuperiorIsla = isla.getY() - (isla.getAlto() / 2);	
			
			if(bordeInferiorPersonaje>=bordeSuperiorIsla && bordeInferiorPersonaje<=bordeSuperiorIsla +velocidad) {
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
			float bordeDerechoPersonaje = this.x + (this.ancho / 2);
		    float bordeIzquierdoIsla = isla.getX() - (isla.getAncho() / 2);	
			
			if(bordeDerechoPersonaje <=bordeIzquierdoIsla && bordeDerechoPersonaje >=bordeIzquierdoIsla -velocidad) {
				if(this.y+(this.alto/2) > isla.getY()-(isla.getAlto()/2)  &&  this.y-(this.alto/2) < isla.getY()+(isla.getAlto()/2)) {
					this.x=(int) bordeIzquierdoIsla-(this.ancho/2);
					return true;
				}
			}			
		}
		return false;	}
	
	public boolean estaColisionandoPorIzquierda(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			float bordeIzquierdoPersonaje = this.x - (this.ancho / 2);
		    float bordeDerechoIsla = isla.getX() + (isla.getAncho() / 2);	
			
			if(bordeIzquierdoPersonaje >=bordeDerechoIsla && bordeIzquierdoPersonaje <=bordeDerechoIsla +velocidad) {
				if(this.y-(this.alto/2) < isla.getY()+(isla.getAlto()/2)  &&  this.y+(this.alto/2) > isla.getY()-(isla.getAlto()/2)) {
					this.x=(int) bordeDerechoIsla+(this.ancho/2);
					return true;
				}
			}			
		}		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	

}
