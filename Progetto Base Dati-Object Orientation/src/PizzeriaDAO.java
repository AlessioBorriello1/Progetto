import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;

public class PizzeriaDAO {

	
	public Pizzeria creaPizzeria(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		Pizzeria p = new Pizzeria();
		p.setNome(nome);
		p.setIndirizzo(indirizzo);
		p.setTelefono(telefono);
		p.setTipoAttivita(tipoAttivita);
		p.setAttributoAttivita(specializzazione);
	
		return p;
		
	}

}