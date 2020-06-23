import javax.swing.JPanel;

public class LuogoTipoRistorante extends Luogo{
	
	private int ID;
	private String nazioneCibo;
	private boolean vegano;


	public String getNazioneCibo() {
		return nazioneCibo;
	}
	public void setNazioneCibo(String nazioneCibo) {
		this.nazioneCibo = nazioneCibo;
	}
	public boolean isVegano() {
		return vegano;
	}
	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public LuogoTipoRistorante creaLuogoTipoRistorante(String nome, String indirizzo, double telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		String s = pannelloImpostazioniAggiuntive.getName();
		
		switch(s) {
		case "panelImpostazioniAggiuntivePizzeria":{
			//Caso pizzeria
		}
		case "panelImpostazioniAggiuntiveBraceria":{
			//Caso braceria
		}
		case "panelImpostazioniAggiuntivePub":{
			//Caso pub
		}
		}
		
		return null;
	
	}
	
}
