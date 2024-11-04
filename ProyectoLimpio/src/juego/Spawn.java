package juego;

public class Spawn {
	private int x;
	private int y;
	private Isla[] islas;
	
	public Spawn(int x, int y) {
		super();
		this.x=x;
		this.y=y;
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
	
	public void SetY(int y) {
		this.y=y;
	}

}
