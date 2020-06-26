import java.sql.Date;

public class Recensione {
	
	private int IDLuogo;
	private String nomeUtente;
	private int voto;
	private Date data;
	private String testo;
	
	
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public int getIDLuogo() {
		return IDLuogo;
	}
	public void setIDLuogo(int iDLuogo) {
		IDLuogo = iDLuogo;
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}