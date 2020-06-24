import javax.swing.JPanel;

public class Alloggio extends Luogo{
	
	private boolean piscina;
	private boolean wifi;
	private int numeroStanze;

	public Alloggio creaAlloggio(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c) {
		
		String pannello = pannelloImpostazioniAggiuntive.getName();
		Alloggio a = null;
		AlloggioDAO dao = new AlloggioDAO();
		
		switch(pannello) {
		case "panelImpostazioniAggiuntiveHotel":{
			a = dao.creaHotel(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		case "panelImpostazioniAggiuntiveMotel":{
			a = dao.creaMotel(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		case "panelImpostazioniAggiuntiveBB":{
			a = dao.creaBB(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		}
		
		return a;
		
	}

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
