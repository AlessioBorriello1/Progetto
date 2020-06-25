import javax.swing.JPanel;

public class Attrazione extends Luogo{
	
	private boolean primozioneStudenti;
	
	public Attrazione creaAttrazione(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c) {
		
		String pannello = pannelloImpostazioniAggiuntive.getName();
		Attrazione a = null;
		AttrazioneDAO dao = new AttrazioneDAO();
		
		switch(pannello) {
		case "panelImpostazioniAggiuntiveMuseo":{
			a = dao.creaMuseo(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		case "panelImpostazioniAggiuntiveZoo":{
			a = dao.creaZoo(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		case "panelImpostazioniAggiuntiveParco":{
			a = dao.creaParco(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, c);
		}break;
		}
		
		return a;
		
	}

	public boolean isPrimozioneStudenti() {
		return primozioneStudenti;
	}
	public void setPrimozioneStudenti(boolean primozioneStudenti) {
		this.primozioneStudenti = primozioneStudenti;
	}
	
}
