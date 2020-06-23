import javax.swing.JPanel;

public class LuogoTipoAlloggio extends Luogo{
	
	private int ID;
	private boolean wifi;
	private int numeroStanze;
	private boolean piscina;

	
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
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public LuogoTipoAlloggio creaLuogoTipoAlloggio(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		String s = pannelloImpostazioniAggiuntive.getName();
		
		switch(s) {
		case "panelImpostazioniAggiuntiveHotel":{
			System.out.println("Hotel");
		}break;
		case "panelImpostazioniAggiuntiveMotel":{
			System.out.println("Motel");
		}break;
		case "panelImpostazioniAggiuntiveBB":{
			System.out.println("BB");
		}break;
		}
		
		return null;
	}
	
}
