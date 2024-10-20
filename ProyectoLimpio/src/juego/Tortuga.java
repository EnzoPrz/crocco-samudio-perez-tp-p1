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
	private double movimientoHorizontal;
	;
	
	
	public Tortuga(int x, int y, int ancho, int alto, int velocidad, boolean estaCayendo, double desplazamiento, double movH) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 2;
		this.estaCayendo= estaCayendo;
		this.desplazamiento= desplazamiento;
		this.movimientoHorizontal = movH;
		
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

	
	public void moverHaciaAbajo(Entorno e) {
		this.y+=velocidad;			
	}
	
	public double getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(double desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
	
	public void mover() {
		this.x+=movimientoHorizontal*velocidad;
		
	}
	
	
	public void cambiarMovimientoHorizontal() {
		this.movimientoHorizontal*=-1;
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
	
	
	public boolean estaColisionandoPorDerecha(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}					
			float bordeDerechoTortuga = this.x + (this.ancho / 2);
		    float bordeIzquierdoIsla = isla.getX() - (isla.getAncho() / 2);	
			
			if(bordeDerechoTortuga <=bordeIzquierdoIsla && bordeDerechoTortuga >=bordeIzquierdoIsla -velocidad) {
				if(this.x+(this.ancho/2) < isla.getY()+(isla.getAlto()/2)  /*&&  this.y+(this.alto) > isla.getY()-(isla.getAlto())*/) {
					this.x=(int) bordeIzquierdoIsla-(this.ancho/2);
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
			float bordeIquierdoTortuga = this.x - (this.ancho / 2);
		    float bordeDerechodoIsla = isla.getX() + (isla.getAncho() / 2);	
			
			if(bordeIquierdoTortuga >=bordeDerechodoIsla && bordeIquierdoTortuga <=bordeDerechodoIsla +velocidad) {
				if(this.x-(this.ancho/2) > isla.getY()+(isla.getAlto()) /* &&  this.y-(this.alto) < isla.getX()+(isla.getAncho())*/) {
					this.x=(int) bordeDerechodoIsla-(this.ancho);
					return true;
				}
			}			
		}
		return false;
		
	}
	
	
	
	
}	
	
	




/*
 
 
 
 public boolean estaColisionandoPorDerecha(Isla []islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}					
			float bordeDerechoTortuga = this.x + (this.ancho / 2);
		    float bordeDerechoIsla = isla.getX() + (isla.getAncho() / 2);	
			
			if(bordeDerechoTortuga <=bordeDerechoIsla && bordeDerechoTortuga >=bordeDerechoIsla -velocidad) {
				if((isla.getY() + isla.getAlto() >= this.y - this.alto/2)&&(isla.getY() - isla.getAlto() <= this.y + this.alto/2)) {
			
					this.x=(int) bordeDerechoIsla+(this.ancho/2);
					return true;
				}		
			}
		return false;	
		}

	}
 
 public boolean colisionaPorDerecha(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}	
			if(isla.getX() - isla.getAncho() == this.x + this.ancho/2) {
				if((isla.getY() + isla.getAlto() >= this.y - this.alto/2)&&(isla.getY() - isla.getAlto() <= this.y + this.alto/2)){
					return true;				
				}
			}
			return false;
	}
	
	
	public boolean colisionaPorIzquierda(Isla[] islas) {
		if(islas.getX() + islas.getRadio() == this.x - this.ancho/2) {
			if((islas.getY() + islas.getRadio() >= this.y - this.alto/2)&&(islas.getY() - islas.getRadio() <= this.y + this.alto/2)){
				return true;				
			}
		}
		return false;
	}
	
	public boolean colisionaPorDerecha(Isla[] islas) {
		if(islas.getX() - islas.getAncho() == this.x + this.ancho/2) {
			if((islas.getY() + islas.getAlto() >= this.y - this.alto/2)&&(islas.getY() - islas.getAlto() <= this.y + this.alto/2)){
				return true;				
			}
		}
		return false;
	}



	
	

	
	public boolean estaColisionandoPorDerecha(Isla[] islas) {
		for(Isla isla : islas) {
			if(isla==null) {
				continue;
			}					
			float bordeDerechoTortuga = this.x + (this.ancho / 2);
		    float bordeDerechoIsla = isla.getX() + (isla.getAncho() / 2);	
			
			if(bordeDerechoTortuga >=bordeDerechoIsla && bordeDerechoTortuga <=bordeDerechoIsla +velocidad) {
				this.x=(int) bordeDerechoIsla-(this.ancho/2);
				return true;
			}
				/*
				if(this.y-(this.alto/2) < isla.getY()+(isla.getAlto()/2)  &&  this.y+(this.alto/2) > isla.getY()-(isla.getAlto()/2)) {
					this.x=(int) bordeDerechoIsla+(this.ancho/2);
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
			float bordeIzquierdoTortuga = this.x - (this.ancho / 2);
		    float bordeIzquierdooIsla = isla.getX() - (isla.getAncho() / 2);	
			
			if(bordeIzquierdoTortuga <=bordeIzquierdooIsla && bordeIzquierdoTortuga >=bordeIzquierdooIsla +velocidad) {
				this.x=(int) bordeIzquierdooIsla+(this.ancho/2);
				return true;
			}		
						
		}		
		return false;
	}
	
	 */
	

	
	
	
	

