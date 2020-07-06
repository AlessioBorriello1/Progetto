import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class AlloggioDAO {

	/**
	 * Inserisce nel database un nuovo alloggio in base agli attributi inseriti
	 * @param c MainController
	 * @param mf MainFrame
	 * @param nome Nome dell'alloggio da inserire
	 * @param indirizzo Indirizzo dell'alloggio da inserire
	 * @param telefono Telefono dell'alloggio da inserire
	 * @param proprietario Proprietario dell'alloggio da inserire
	 * @param tipoAttivita Tipo attività dell'alloggio da inserire
	 * @param specializzazione Tipo specializzazione dell'alloggio da inserire
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @param u Utente che crea l'alloggio
	 * @param ID ID del luogo
	 * @return boolean operazione riuscita o meno
	 */
	public boolean creaAlloggio(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			System.out.println("Inserimento alloggio");
			
			//Informazioni uniche alloggio
			boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscina")).isSelected();
			boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifi")).isSelected();
			int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString());
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO alloggio(idLuogo, piscina, numeroStanze, wifi)\r\n" + 
					"VALUES ('"+ ID +"','"+ ((piscina) ? 1:0) + "','" + numeroStanze + "','" + ((wifi) ? 1:0) + "');"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione
			
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Hotel
			if(specializzazione.contentEquals("Hotel")) {
				System.out.println("Inserimento hotel");
				//Informazioni uniche Hotel
				int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
				
				String q2 = "INSERT INTO hotel(idAlloggio, numeroStelle)\r\n" + 
						"VALUES ('"+ ID + "','" + numeroStelle + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
			
			//Se la specializzazione è Motel
			}else if(specializzazione.contentEquals("Motel")) {
				System.out.println("Inserimento motel");
				//Informazioni uniche Motel
				boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
				
				String q2 = "INSERT INTO motel(idAlloggio, assistenzaAutovetture)\r\n" + 
						"VALUES ('"+ ID + "','" + ((assistenzaAutovetture) ? 1:0) + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è BB
			}else {
				System.out.println("Inserimento BB");
				//Informazioni uniche BB
				boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
				
				String q2 = "INSERT INTO bedbreakfast(idAlloggio, colazione)\r\n" + 
						"VALUES ('"+ ID + "','" + ((colazione) ? 1:0) + "');"; //Inizializzo query 2
				
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
	 * Recupera dati di un alloggio in base al suo ID unico
	 * @param ID ID unico dell'alloggio da recuperare
	 * @param specializzazione Tipo specializzazione alloggio da recuperare
	 * @return Alloggio con ID specificato
	 */
	public Alloggio getAlloggioByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM alloggio WHERE idLuogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q); //Esegui query
	        
	        rs.next(); //Vai a inizio risultati
	        
	        //Prendi dati dalla query relativi all'alloggio
	        boolean piscina = rs.getBoolean("piscina");
	        boolean wifi = rs.getBoolean("wifi");
	        int numeroStanze = rs.getInt("numeroStanze");
	        
	        rs.close();
	        
	        //Se l'alloggio è un Hotel
			if(specializzazione.contentEquals("Hotel")) {
				q = "SELECT * FROM hotel WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				int numeroStelle = r.getInt("numeroStelle");
				//Crea Hotel e imposta attributi presi dalla query
				Hotel h = new Hotel();
				h.setNumeroStanze(numeroStanze);
				h.setPiscina(piscina);
				h.setWifi(wifi);
				h.setNumeroStelle(numeroStelle);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return h; //Restituisci Hotel
			//Se l'alloggio è un Motel
			}else if(specializzazione.contentEquals("Motel")) {
				q = "SELECT * FROM motel WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean assitenza = r.getBoolean("assistenzaAutovetture");
				//Crea Motel e imposta attributi presi dalla query
				Motel m = new Motel();
				m.setNumeroStanze(numeroStanze);
				m.setPiscina(piscina);
				m.setWifi(wifi);
				m.setAssistenzaAuto(assitenza);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return m; //Restituisci Motel
			//Se l'alloggio è un BB
			}else {
				q = "SELECT * FROM bedbreakfast WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean colazione = r.getBoolean("colazione");
				//Crea BB e imposta attributi presi dalla query
				BB b = new BB();
				b.setNumeroStanze(numeroStanze);
				b.setPiscina(piscina);
				b.setWifi(wifi);
				b.setColazione(colazione);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return b; //Restituisci BB
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Operazione inserimento fallita, restituisce false
		}
		
	}

	/**
	 * Modifica un alloggio all'interno del database
	 * @param c MainController
	 * @param vecchioLuogo Luogo da rimpiazzare nel database
	 * @param pannelloImpostazioniAggiuntive Pannello contente le informazioni uniche di quel tipo di specializzazione
	 * @return boolean operazione riuscita o meno
	 */
	public boolean modificaAlloggio(MainController c, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive) {
		
		try {
			
			System.out.println("Modifica alloggio");
			
			//Informazioni uniche alloggio
			boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscina")).isSelected();
			boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifi")).isSelected();
			int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString());
			String specializzazione = vecchioLuogo.getAttributoAttivita();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE alloggio SET piscina = " + ((piscina) ? 1:0) + ", numeroStanze = " + numeroStanze  + ", wifi = " + ((wifi)? 1:0) + " WHERE idLuogo = " + vecchioLuogo.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			//Se la specializzazione è Hotel
			if(specializzazione.contentEquals("Hotel")) {
				System.out.println("Modifica Hotel");
				//Informazioni uniche Hotel
				int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
				
				q = "UPDATE hotel SET numeroStelle = " + numeroStelle + " WHERE idAlloggio = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è Motel
			}else if(specializzazione.contentEquals("Motel")) {
				System.out.println("Modifica Motel");
				//Informazioni uniche Motel
				boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
				
				q = "UPDATE motel SET assistenzaAutovetture = " + ((assistenzaAutovetture)? 1:0) + " WHERE idAlloggio = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true; //Operazione riuscita
				
			//Se la specializzazione è BB
			}else {
				System.out.println("Modifica BB");
				//Informazioni uniche BB
				boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
				
				q = "UPDATE bedbreakfast SET colazione = " + ((colazione)? 1:0) + " WHERE idAlloggio = " + vecchioLuogo.getID();
				
				st.executeUpdate(q); //Eseguo la query contenuta in stringa
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
