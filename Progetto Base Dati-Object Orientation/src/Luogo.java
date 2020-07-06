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

	//Getter e setter ID
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	//Getter e setter indirizzo
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	
	//Getter e setter nome
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Getter e setter proprietario
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	
	//Getter e setter telefono
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	//Getter e setter recensioni
	public float getMediaRecensioni() {
		return mediaRecensioni;
	}
	public void setMediaRecensioni(float mediaRecensioni) {
		this.mediaRecensioni = mediaRecensioni;
	}
	
	//Getter e setter attributoAttivita
	public String getAttributoAttivita() {
		return attributoAttivita;
	}
	public void setAttributoAttivita(String attributoAttivita) {
		this.attributoAttivita = attributoAttivita;
	}
	
	//Getter e setter tipoAttivita
	public String getTipoAttivita() {
		return tipoAttivita;
	}
	public void setTipoAttivita(String tipoAttivita) {
		this.tipoAttivita = tipoAttivita;
	}
	
	//Getter e setter nomeUtente
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	

}
