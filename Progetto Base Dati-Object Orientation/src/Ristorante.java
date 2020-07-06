import javax.swing.JPanel;

public class Ristorante extends Luogo{
	
	//Attributi unici ristorante
	private boolean vegano;
	private String nazionalitaCibo;

	//Getter e setter vegano
	public boolean isVegano() {
		return vegano;
	}
	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	//Getter e setter nazionalitaCibo
	public String getNazionalitaCibo() {
		return nazionalitaCibo;
	}
	public void setNazionalitaCibo(String nazionalitaCibo) {
		this.nazionalitaCibo = nazionalitaCibo;
	}
	
}
