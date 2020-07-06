import java.sql.Date;

public class Recensione {
	
	private int IDLuogo; //ID del luogo riferito alla recensione
	private String nomeUtente; //Utente creatore della recensione
	private int voto; //Voto della recensione
	private Date data; //Data di creazione della recensione
	private String testo; //Stringa testo della recensione
	
	//Getter e setter nomeUtente
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	//Getter e setter IDLuogo
	public int getIDLuogo() {
		return IDLuogo;
	}
	public void setIDLuogo(int iDLuogo) {
		IDLuogo = iDLuogo;
	}
	
	//Getter e setter voto
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	//Getter e setter data
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	//Getter e setter testo
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}