import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class RistoranteDAO {

	/**
	 * Inserisce nel database un nuovo ristorante in base agli attributi inseriti
	 * @param c MainController
	 * @param mf MainFrame
	 * @param nome Nome del ristorante da inserire
	 * @param indirizzo Indirizzo del ristorante da inserire
	 * @param telefono Telefono del ristorante da inserire
	 * @param proprietario Proprietario del ristorante da inserire
	 * @param tipoAttivita Tipo attività del ristorante da inserire
	 * @param specializzazione Tipo specializzazione del ristorante da inserire
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @param u Utente che crea il ristorante
	 * @param ID ID del luogo
	 * @return boolean operazione riuscita o meno
	 */
	public boolean creaRistorante(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			System.out.println("Inserimento ristorante");
			
			//Informazioni uniche ristorante
			boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVegano")).isSelected();
			String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCibo")).getSelectedItem().toString();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO ristorante(idLuogo, vegano, tipoRistorante)\r\n" + 
					"VALUES ('"+ ID +"','" + ((vegano) ? 1:0) + "','" + nazionalita + "');"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Pizzeria
			if(specializzazione.contentEquals("Pizzeria")) {
				System.out.println("Inserimento pizzeria");
				//Informazioni uniche Pizzeria
				boolean asporto = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAsporto")).isSelected();
				
				q = "INSERT INTO pizzeria(idRistorante, asporto)\r\n" + 
					"VALUES ('"+ ID + "','" + ((asporto) ? 1:0) + "');"; //Inizializzo query 2
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Braceria
			}else if(specializzazione.contentEquals("Braceria")) {
				System.out.println("Inserimento braceria");
				//Informazioni uniche Braceria
				String tipoCarne = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoCarne")).getSelectedItem().toString();
				
				q = "INSERT INTO braceria(idRistorante, tipoCarne)\r\n" + 
					"VALUES ('"+ ID + "','" + tipoCarne + "');"; //Inizializzo query 2
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita

			//Se la specializzazione è Pub
			}else {
				System.out.println("Inserimento pub");
				//Informazioni uniche Pub
				String tipoBirra = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoBirra")).getSelectedItem().toString();
				
				q = "INSERT INTO pub(idRistorante, tipoBirra)\r\n" + 
					"VALUES ('"+ ID + "','" + tipoBirra + "');"; //Inizializzo query 2
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
			
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
	/**
	 * Recupera dati di un ristorante in base al suo ID unico
	 * @param ID ID unico del ristorante da recuperare
	 * @param specializzazione Tipo specializzazione attrazione da recuperare
	 * @return Ristorante con ID specificato
	 */
	public Ristorante getRistoranteByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM ristorante WHERE idLuogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        //Prendi dati dalla query relativi al ristorante
	        boolean vegano = rs.getBoolean("vegano");
	        String nazione = rs.getString("tiporistorante");
	        rs.close();
	        
	        //Se l'attrazione è una Pizzeria
			if(specializzazione.contentEquals("Pizzeria")) {
				q = "SELECT * FROM pizzeria WHERE idRistorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean asporto = r.getBoolean("asporto");
				//Crea Pizzeria e imposta attributi presi dalla query
				Pizzeria p = new Pizzeria();
				p.setVegano(vegano);
				p.setNazionalitaCibo(nazione);
				p.setAsporto(asporto);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return p; //Restituisce Pizzeria
				
			//Se l'attrazione è una Braceria
			}else if(specializzazione.contentEquals("Braceria")) {
				q = "SELECT * FROM braceria WHERE idRistorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoCarne = r.getString("tipoCarne");
				//Crea Braceria e imposta attributi presi dalla query
				Braceria b = new Braceria();
				b.setVegano(vegano);
				b.setNazionalitaCibo(nazione);
				b.setTipoCarne(tipoCarne);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return b; //Restituisce Braceria
				
			//Se l'attrazione è un Pub
			}else {
				q = "SELECT * FROM pub WHERE idRistorante = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoBirra = r.getString("tipoBirra");
				//Crea Pub e imposta attributi presi dalla query
				Pub p = new Pub();
				p.setVegano(vegano);
				p.setNazionalitaCibo(nazione);
				p.setTipoBirra(tipoBirra);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return p; //Restituisce Pub
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Operazione inserimento fallita, restituisce false
		}
		
		
	}

	/**
	 * Modifica un ristorante all'interno del database
	 * @param c MainController
	 * @param vecchioLuogo Luogo da rimpiazzare nel database
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @return boolean operazione riuscita o meno
	 */
	public boolean modificaRistorante(MainController c, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive) {
		
		try {
			
			System.out.println("Modifica ristorante");
			
			//Informazioni uniche Ristorante
			boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVegano")).isSelected();
			String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCibo")).getSelectedItem().toString();
			String specializzazione = vecchioLuogo.getAttributoAttivita();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE ristorante SET vegano = " + ((vegano) ? 1:0) + ", tipoRistorante = '" + nazionalita  + "' WHERE idLuogo = " + vecchioLuogo.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Pizzeria
			if(specializzazione.contentEquals("Pizzeria")) {
				System.out.println("Modifica pizzeria");
				//Informazioni uniche Pizzeria
				boolean asporto = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAsporto")).isSelected();
				
				q = "UPDATE pizzeria SET asporto = " + ((asporto) ? 1:0) + " WHERE idRistorante = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Braceria
			}else if(specializzazione.contentEquals("Braceria")) {
				System.out.println("Modifica braceria");
				//Informazioni uniche Braceria
				String tipoCarne = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoCarne")).getSelectedItem().toString();
				
				q = "UPDATE braceria SET tipoCarne = '" + tipoCarne + "' WHERE idRistorante = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Pub
			}else {
				System.out.println("Modifica pub");
				//Informazioni uniche Pub
				String tipoBirra = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoBirra")).getSelectedItem().toString();
				
				q = "UPDATE pub SET tipoBirra = '" + tipoBirra + "' WHERE idRistorante = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
			
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}

}
