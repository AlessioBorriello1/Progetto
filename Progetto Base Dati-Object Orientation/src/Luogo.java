import javax.swing.JPanel;

public class Luogo {

	private int ID; //ID unico luogo
	private String nome; //Nome luogo
	private String Indirizzo; //Indirizzo luogo
	private String telefono; //Numero telefono luogo
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
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
	

}
