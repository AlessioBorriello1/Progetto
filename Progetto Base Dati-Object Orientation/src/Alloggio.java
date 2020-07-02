
public class Alloggio extends Luogo{
	
	//Attributi unici alloggio
	private boolean piscina;
	private boolean wifi;
	private int numeroStanze;

	//Getter piscina
	public boolean isPiscina() {
		return piscina;
	}
	//Setter piscina
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	//Getter wifi
	public boolean isWifi() {
		return wifi;
	}
	//Setter wifi
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	//Getter numero stanze
	public int getNumeroStanze() {
		return numeroStanze;
	}
	//Setter numero stanze
	public void setNumeroStanze(int numeroStanze) {
		this.numeroStanze = numeroStanze;
	}
	
}
