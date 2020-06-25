import javax.swing.JPanel;

public class Ristorante extends Luogo{
	
	private boolean vegano;
	private String nazionalitaCibo;

	public boolean isVegano() {
		return vegano;
	}
	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public String getNazionalitaCibo() {
		return nazionalitaCibo;
	}

	public void setNazionalitaCibo(String nazionalitaCibo) {
		this.nazionalitaCibo = nazionalitaCibo;
	}
	
}
