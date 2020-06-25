import javax.swing.JPanel;

public class Alloggio extends Luogo{
	
	private boolean piscina;
	private boolean wifi;
	private int numeroStanze;

	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public int getNumeroStanze() {
		return numeroStanze;
	}
	public void setNumeroStanze(int numeroStanze) {
		this.numeroStanze = numeroStanze;
	}
	
}
