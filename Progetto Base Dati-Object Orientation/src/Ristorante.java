import javax.swing.JPanel;

public class Ristorante extends Luogo{
	
	private boolean vegano;
	private String nazionalitaCibo;

	public Ristorante creaRistorante(String nome, String indirizzo, double telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c) {
		
		String pannello = pannelloImpostazioniAggiuntive.getName();
		Ristorante r = null;
		
		switch(pannello) {
		case "panelImpostazioniAggiuntivePizzeria":{
			PizzeriaDAO dao = new PizzeriaDAO();
			r = dao.creaPizzeria(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		case "panelImpostazioniAggiuntiveBracieria":{
			//BraceriaDAO dao = new BraceriaDAO();
			//r = dao.creaBraceria();
		}break;
		case "panelImpostazioniAggiuntivePub":{
			//PubDAO dao = new PubDAO();
			//r = dao.creaPub();
		}break;
		}
		
		return r;
		
	}

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
