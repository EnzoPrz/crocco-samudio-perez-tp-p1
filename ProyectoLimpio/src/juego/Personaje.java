package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Personaje {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private boolean estaSaltando;
	private char direccion;
	Image img[];
	
	
	
	
	public Personaje(int x, int y, int ancho, int alto,int velocidad, boolean estaSaltando, char direccion) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad=3;
		this.estaSaltando = estaSaltando;
		this.direccion= direccion;
		this.img = new Image[2];
		
		this.img[0] = Herramientas.cargarImagen("principed.png"); // imagen derecha
		this.img[1] = Herramientas.cargarImagen("principei.png"); // imagen izquierda
	
		
		
	}

	
	public void dibujar(Entorno e) {
		int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
		e.dibujarImagen(img[indiceImagen], this.x, this.y, 0, 0.10);
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
			this.direccion='d';
	}
	
	public void moverIzquierda(Entorno e) {
			this.x-=velocidad;	
			this.direccion='i';
	}
	
	public void moverHaciaAbajo(Entorno e) {
			this.y+=velocidad;			
	}
	
	public void moverHaciaArriba(Entorno entorno) {
		this.y-=velocidad;				
	}

	
	public boolean getEstaSaltando() {
		return estaSaltando;
	}
	
	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}

	public char getDireccion() {
		return direccion;
	}

	
	public void setEstaSaltando(boolean estaSaltando) {
		this.estaSaltando = estaSaltando;
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
					int indiceImagen = (this.direccion == 'd') ? 0 : 1; 
					this.direccion='d';
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
			float bordeIzquierdoPersonaje = this.x - (this.ancho / 2);
		    float bordeDerechoIsla = isla.getX() + (isla.getAncho() / 2);	
			
			if(bordeIzquierdoPersonaje >=bordeDerechoIsla && bordeIzquierdoPersonaje <=bordeDerechoIsla +velocidad) {
				if(this.y-(this.alto/2) < isla.getY()+(isla.getAlto()/2)  &&  this.y+(this.alto/2) > isla.getY()-(isla.getAlto()/2)) {
					this.x=(int) bordeDerechoIsla+(this.ancho/2);
					this.direccion='i';
					return true;
				}
			}			
		}		
		return false;
	}


	
	
	public void correjirPosicion(Isla [] islas) {
		Punto supIzq =new Punto (this.x-(this.ancho/2),this.y-(this.alto/2));
		Punto supDer =new Punto (this.x+(this.ancho/2),this.y-(this.alto/2));
		Punto infIzq =new Punto (this.x-(this.ancho/2),this.y+(this.alto/2));
		Punto infDer =new Punto (this.x+(this.ancho/2),this.y+(this.alto/2));
		
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}
			if(estaDentro(supIzq,isla) || estaDentro(supDer,isla)) {
				this.y = isla.getY()+ (isla.getAlto()/2)+ (this.alto/2);
			}
			else if(estaDentro(infIzq,isla) || estaDentro(infDer,isla)){
				this.y = isla.getY()-(isla.getAlto()/2)-(this.alto/2);
				
			}
		}
	}
	
	public boolean estaDentro(Punto p , Isla i) {
		if(p.getX() > i.getX()-(i.getAncho()/2)
				&& p.getX() < i.getX()+(i.getAncho()/2)
					&& p.getY() > i.getY()-(i.getAlto()/2)
						&& p.getY() < i.getY()+(i.getAlto()/2)) {
			return true;
		}
		return false;
	}

}
