import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AttrazioneDAO {
	
	/**
	 * Inserisce nel database una nuova attrazione in base agli attributi inseriti
	 * @param c MainController
	 * @param mf MainFrame
	 * @param nome Nome dell'attrazione da inserire
	 * @param indirizzo Indirizzo dell'attrazione da inserire
	 * @param telefono Telefono dell'attrazione da inserire
	 * @param proprietario Proprietario dell'attrazione da inserire
	 * @param tipoAttivita Tipo attività dell'attrazione da inserire
	 * @param specializzazione Tipo specializzazione dell'attrazione da inserire
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @param u Utente che crea l'attrazione
	 * @param ID ID del luogo
	 * @return boolean operazione riuscita o meno
	 */
	public boolean creaAttrazione(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			
			System.out.println("Inserimento attrazione");
			
			//Informazioni uniche attrazione
			boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudenti")).isSelected();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO attrazione(idluogo, promozionestudenti)\r\n" + 
					"VALUES ('"+ ID + "','" + ((promozione) ? 1:0) + "');"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione
			
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Museo
			if(specializzazione.contentEquals("Museo")) {
				System.out.println("Inserimento museo");
				//Informazioni uniche Museo
				String tipoMuseo = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoMuseo")).getSelectedItem().toString();
				
				String q2 = "INSERT INTO museo(idattrazione, tipomuseo)\r\n" + 
						"VALUES ('"+ ID + "','" + tipoMuseo + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Zoo
			}else if(specializzazione.contentEquals("Zoo")) {
				System.out.println("Inserimento zoo");
				//Informazioni uniche Zoo
				String tipoSpecie = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxSpecie")).getSelectedItem().toString();
				
				String q2 = "INSERT INTO zoo(idattrazione, specie)\r\n" + 
						"VALUES ('"+ ID + "','" + tipoSpecie + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Parco
			}else {
				System.out.println("Inserimento Parco");
				//Informazioni uniche Parco
				boolean gratuito = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxIngressoGratuito")).isSelected();
				
				String q2 = "INSERT INTO parco(idattrazione, ingressogratuito)\r\n" + 
						"VALUES ('"+ ID + "','" + ((gratuito) ? 1:0) + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
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
	 * Recupera dati di un attrazione in base al suo ID unico
	 * @param ID ID unico dell'attrazione da recuperare
	 * @param specializzazione Tipo specializzazione attrazione da recuperare
	 * @return Attrazione con ID specificato
	 */
	public Attrazione getAttrazioneByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM attrazione WHERE idLuogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        //Prendi dati dalla query relativi all'attrazione
	        boolean promozione = rs.getBoolean("promozioneStudenti");
	        
	        rs.close();
	        
	        //Se l'attrazione è un Museo
			if(specializzazione.contentEquals("Museo")) {
				q = "SELECT * FROM museo WHERE idAttrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoMuseo = r.getString("tipoMuseo");
				//Crea Museo e imposta attributi presi dalla query
				Museo m = new Museo();
				m.setPromozioneStudenti(promozione);
				m.setTipoMuseo(tipoMuseo);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return m; //Restituisce Museo
				
			//Se l'attrazione è uno Zoo
			}else if(specializzazione.contentEquals("Zoo")) {
				q = "SELECT * FROM zoo WHERE idAttrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String specie = r.getString("specie");
				//Crea Zoo e imposta attributi presi dalla query
				Zoo z = new Zoo();
				z.setPromozioneStudenti(promozione);
				z.setSpecie(specie);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return z; //Restituisce Zoo
				
			//Se l'attrazione è un Parco
			}else {
				q = "SELECT * FROM parco WHERE idAttrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean ingresso = r.getBoolean("ingressoGratuito");
				//Crea Parco e imposta attributi presi dalla query
				Parco p = new Parco();
				p.setPromozioneStudenti(promozione);
				p.setIngressoGratuito(ingresso);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return p; //Restituisce Parco
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Operazione inserimento fallita, restituisce false
		}
		
		
	}

	/**
	 * Modifica un attrazione all'interno del database
	 * @param c MainController
	 * @param vecchioLuogo Luogo da rimpiazzare nel database
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @return boolean operazione riuscita o meno
	 */
	public boolean modificaAttrazione(MainController c, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive) {
		
		try {
			
			System.out.println("Modifica alloggio");
			
			//Informazioni uniche Attrazione
			boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudenti")).isSelected();
			String specializzazione = vecchioLuogo.getAttributoAttivita();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE attrazione SET promozioneStudenti = " + ((promozione) ? 1:0) + " WHERE idLuogo = " + vecchioLuogo.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, MainController.UTENTE, MainController.PASSWORD);  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Museo
			if(specializzazione.contentEquals("Museo")) {
				System.out.println("Modifica museo");
				//Informazioni uniche Museo
				String tipoMuseo = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoMuseo")).getSelectedItem().toString();
				
				q = "UPDATE museo SET tipoMuseo = '" + tipoMuseo + "' WHERE idAttrazione = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Zoo
			}else if(specializzazione.contentEquals("Zoo")) {
				System.out.println("Modifica zoo");
				//Informazioni uniche Zoo
				String tipoSpecie = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxSpecie")).getSelectedItem().toString();
				
				q = "UPDATE zoo SET specie = '" + tipoSpecie + "' WHERE idAttrazione = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa q
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Parco
			}else {
				System.out.println("Modifica parco");
				//Informazioni uniche Parco
				boolean gratuito = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxIngressoGratuito")).isSelected();
				
				q = "UPDATE parco SET ingressoGratuito = " + ((gratuito)? 1:0) + " WHERE idAttrazione = " + vecchioLuogo.getID();
				
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