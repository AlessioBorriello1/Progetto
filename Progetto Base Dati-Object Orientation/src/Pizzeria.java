import javax.swing.JPanel;

public class Pizzeria extends LuogoTipoRistorante{
	
	private int ID;
	private boolean celiaco;
	private boolean asporto;
	
	public Pizzeria creaPizzeria(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		PizzeriaDAO dao = new PizzeriaDAO(); //Istanzia un UtenteDAO per eseguire la ricerca
		Pizzeria l = dao.creaPizzeria(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive); //Funzione UtenteDAO che restituisce (se lo trova) un utente
		
		return l;
	}
}
