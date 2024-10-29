package juego;

public class Spawn {
	public double getX() {
        return Math.random() * 800; // reemplaza 800 por el ancho del entorno
    }

    public double getY() {
        return Math.random() * 600; // reemplaza 600 por el alto del entorno
    }
//	for (int i = 0; i < tortugas.length; i++) {
//	    if (tortugas[i] == null) {
//	        double x = posicionesInicialesTortugas[i][0];
//	        double y = posicionesInicialesTortugas[i][1];
//	        tortugas[i] = new Tortuga(x, y, 27, 50, 0.5, 2, 'd', false, 0.5);
//	    }
//	}
	
//	public void RellenarJuegoConTortugas (Tortuga [] t) {
//		this.posicionesInicialesTortugas = new double[t.length][2];
//		double XparaTortuF1 = 45;
//		double YparaTortuF1 = entorno.alto()/2;
//		double XparaTortuF2 = 95;
//		double YparaTortuF2 = entorno.alto()/2;
//		double XparaTortuF3 = 175;
//		double YparaTortuF3 = entorno.alto()/2;
//		double XparaTortuF4 = 295;
//		double YparaTortuF4 = entorno.alto()/2;
//		
//		for (int i=0; i < t.length; i++) {
//			if (i==0 || i==1) {
//				t[i] = new Tortuga (XparaTortuF1, YparaTortuF1-170, 27, 50, 0.5, 2, 'd', false, 0.5 );
//				 this.posicionesInicialesTortugas[i][0] = XparaTortuF1; 
//		         this.posicionesInicialesTortugas[i][1] = YparaTortuF1 - 170;
//		         XparaTortuF1 = (XparaTortuF1 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF1 : entorno.ancho() / 2;
//			}
//			if (i==2 || i==3) {
//				t[i] = new Tortuga (XparaTortuF2, YparaTortuF2-170, 27, 50, 0.5, 1.5, 'd', false, 0.5 );
//				this.posicionesInicialesTortugas[i][0] = XparaTortuF2;
//	            this.posicionesInicialesTortugas[i][1] = YparaTortuF2 - 170;
//	            XparaTortuF2 = (XparaTortuF2 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF2 : entorno.ancho() / 2;
//			}
//			if (i==4 || i==5) {
//				t[i] = new Tortuga (XparaTortuF3, YparaTortuF3-170, 27, 50, 0.5, 1, 'd', false, 0.5 );
//				this.posicionesInicialesTortugas[i][0] = XparaTortuF3;
//	            this.posicionesInicialesTortugas[i][1] = YparaTortuF3 - 170;
//	            XparaTortuF3 = (XparaTortuF3 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF3 : entorno.ancho() / 2;
//			}
//			if (i==6 || i==7) {
//				t[i] = new Tortuga (XparaTortuF4, YparaTortuF4-50, 27, 50, 0.5, 1, 'd', false, 0.5 );
//				this.posicionesInicialesTortugas[i][0] = XparaTortuF4;
//	            this.posicionesInicialesTortugas[i][1] = YparaTortuF4 - 50;
//	            XparaTortuF4 = (XparaTortuF4 < entorno.ancho() / 2) ? entorno.ancho() - XparaTortuF4 : entorno.ancho() / 2;
//			}
//			
//		}
//	}

}
