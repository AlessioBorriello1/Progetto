import javax.swing.JPanel;

public class Luogo {

	private int ID; //ID unico luogo
	private String nome; //Nome luogo
	private String Indirizzo; //Indirizzo luogo
	private int telefono; //Numero telefono luogo
	private String proprietario; //Nome proprietario
	private float mediaRecensioni = 0; //Media recensioni
	private String tipoAttivita; //Tipo attività (Ristorante, Alloggio, Attrazione)
	private String attributoAttivita; //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
	
	private String nomeUtente; //Nome utente dell'utente che ha creato il luogo

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public float getMediaRecensioni() {
		return mediaRecensioni;
	}
	public void setMediaRecensioni(float mediaRecensioni) {
		this.mediaRecensioni = mediaRecensioni;
	}
	public String getAttributoAttivita() {
		return attributoAttivita;
	}
	public void setAttributoAttivita(String attributoAttivita) {
		this.attributoAttivita = attributoAttivita;
	}
	public String getTipoAttivita() {
		return tipoAttivita;
	}
	public void setTipoAttivita(String tipoAttivita) {
		this.tipoAttivita = tipoAttivita;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	public Luogo creaLuogo(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		String pannello = pannelloImpostazioniAggiuntive.getName();
		int i;
		Luogo l;
		
		if(pannello.contentEquals("panelImpostazioniAggiuntivePizzeria") || pannello.contentEquals("panelImpostazioniAggiuntiveBraceria") || pannello.contentEquals("panelImpostazioniAggiuntivePub")) {
			i = 0;
		}else if(pannello.contentEquals("panelImpostazioniAggiuntiveHotel") || pannello.contentEquals("panelImpostazioniAggiuntiveBB") || pannello.contentEquals("panelImpostazioniAggiuntiveMotel")) {
			i = 1;
		}else {
			i = 2;
		}
		
		switch(i) {
		case 0:{
			LuogoTipoRistorante luogo = new LuogoTipoRistorante();
			l = luogo.creaLuogoTipoRistorante(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
		}break;
		case 1:{
			LuogoTipoAlloggio luogo = new LuogoTipoAlloggio();
			l = luogo.creaLuogoTipoAlloggio(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
		}break;
		case 2:{
			LuogoTipoAttrazione luogo = new LuogoTipoAttrazione();
			l = luogo.creaLuogoTipoAttrazione(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
		}break;
		default:{
			LuogoTipoRistorante luogo = new LuogoTipoRistorante();
			l = luogo.creaLuogoTipoRistorante(nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive);
		}
		}
		
		return null;
	}

}
