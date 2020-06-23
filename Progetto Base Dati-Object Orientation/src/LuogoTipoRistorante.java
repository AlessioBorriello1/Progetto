import javax.swing.JPanel;

public class LuogoTipoRistorante extends Luogo{
	
	private int ID;
	private String nazioneCibo;
	private boolean vegetariano;
	private boolean vegano;


	public String getNazioneCibo() {
		return nazioneCibo;
	}
	public void setNazioneCibo(String nazioneCibo) {
		this.nazioneCibo = nazioneCibo;
	}
	public boolean isVegetariano() {
		return vegetariano;
	}
	public void setVegetariano(boolean vegetariano) {
		this.vegetariano = vegetariano;
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
	
	public LuogoTipoRistorante creaLuogoTipoRistorante(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		String s = pannelloImpostazioniAggiuntive.getName();
		
		switch(s) {
		case "panelImpostazioniAggiuntivePizzeria":{
			System.out.println("Pizzeria");
			Pizzeria l = new Pizzeria();
			l = l.creaPizzeria(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
			return l;
		}
		case "panelImpostazioniAggiuntiveBraceria":{
			System.out.println("Braceria");
			Pizzeria l = new Pizzeria();
			l = l.creaPizzeria(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
			return l;
		}
		case "panelImpostazioniAggiuntivePub":{
			System.out.println("Pub");
			Pizzeria l = new Pizzeria();
			l = l.creaPizzeria(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
			return l;
		}
		}
		
		return null;
	
	}
	
}
