import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class RistoranteDAO {

	public boolean creaRistorante(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			System.out.println("Inserimento ristorante");
			boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVegano")).isSelected();
			String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCibo")).getSelectedItem().toString();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO ristorante(idluogo, vegano, tiporistorante)\r\n" + 
					"VALUES ('"+ ID +"','"+ ((vegano) ? 1:0) + "','" + nazionalita + "');"; //Inizializzo query 2
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
			
			if(specializzazione.contentEquals("Pizzeria")) {
				System.out.println("Inserimento pizzeria");
				boolean asporto = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAsporto")).isSelected();
				
				q = "INSERT INTO pizzeria(idristorante, asporto)\r\n" + 
					"VALUES ('"+ ID + "','" + ((asporto) ? 1:0) + "');"; //Inizializzo query 3
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else if(specializzazione.contentEquals("Braceria")) {
				System.out.println("Inserimento braceria");
				String tipoCarne = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoCarne")).getSelectedItem().toString();
				
				q = "INSERT INTO braceria(idristorante, tipocarne)\r\n" + 
					"VALUES ('"+ ID + "','" + tipoCarne + "');"; //Inizializzo query 3
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else {
				System.out.println("Inserimento pub");
				String tipoBirra = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoBirra")).getSelectedItem().toString();
				
				q = "INSERT INTO braceria(idristorante, tipobirra)\r\n" + 
					"VALUES ('"+ ID + "','" + tipoBirra + "');"; //Inizializzo query 3
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
			
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
	public Ristorante getRistoranteByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM ristorante WHERE idluogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        boolean vegano = rs.getBoolean("vegano");
	        String nazione = rs.getString("tiporistorante");
	        rs.close();
	        
			if(specializzazione.contentEquals("Pizzeria")) {
				q = "SELECT * FROM pizzeria WHERE idristorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean asporto = r.getBoolean("asporto");
				Pizzeria p = new Pizzeria();
				p.setVegano(vegano);
				p.setNazionalitaCibo(nazione);
				p.setAsporto(asporto);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return p;
			}else if(specializzazione.contentEquals("Braceria")) {
				q = "SELECT * FROM braceria WHERE idristorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoCarne = r.getString("tipocarne");
				Braceria b = new Braceria();
				b.setVegano(vegano);
				b.setNazionalitaCibo(nazione);
				b.setTipoCarne(tipoCarne);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return b;
			}else {
				q = "SELECT * FROM pub WHERE idristorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoBirra = r.getString("tipobirra");
				Pub p = new Pub();
				p.setVegano(vegano);
				p.setNazionalitaCibo(nazione);
				p.setTipoBirra(tipoBirra);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return p;
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Operazione inserimento fallita, restituisce false
		}
		
		
	}
	
}
