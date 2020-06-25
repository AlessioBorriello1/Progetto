import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class Utente {

	private String nomeUtente; //NickName dell'utente
	private String email; //Email dell'utente
	private int numeroRecensioni; //Numero delle recensioni dell'utente
	private int numeroLuoghi; //Numero dei luoghi in possesso dell'utente
	
	private List<Luogo> luoghiUtente;
	
	/**
	 * Stampa sulla console informazioni dell'oggetto utente che chiama la funzione
	 */
	public void stampaInfo() {
		
		System.out.println("Nome utente: " + nomeUtente + " Email: " + email + " Numero luoghi: " + numeroLuoghi + " Numero recensioni: " + numeroRecensioni);
		for(Luogo l : luoghiUtente) {
			System.out.println(l.getNome());
		}
		
	}
	
	
	//Setter e getter variabile nomeUtente
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	//Setter e getter variabile email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Setter e getter variabile numeroRecensioni
	public int getNumeroRecensioni() {
		return numeroRecensioni;
	}
	public void setNumeroRecensioni(int numeroRecensioni) {
		this.numeroRecensioni = numeroRecensioni;
	}
	
	//Setter e getter variabile numeroLuoghi
	public int getNumeroLuoghi() {
		return numeroLuoghi;
	}
	public void setNumeroLuoghi(int numeroLuoghi) {
		this.numeroLuoghi = numeroLuoghi;
	}

	
	//Setter e getter lista luoghi utente
	public List<Luogo> getLuoghiUtente() {
		return luoghiUtente;
	}
	public void setLuoghiUtente(List<Luogo> luoghiUtente) {
		this.luoghiUtente = luoghiUtente;
	}
	
	public void setLuoghiUtente() {
		
		LuogoDAO dao = new LuogoDAO();
		this.luoghiUtente = dao.getListaLuoghiByNomeUtente(nomeUtente);
		
	}
	
}
