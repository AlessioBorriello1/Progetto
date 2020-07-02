import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class AlloggioDAO {

	public boolean creaAlloggio(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			System.out.println("Inserimento alloggio");
			boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscina")).isSelected();
			boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifi")).isSelected();
			int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString());
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO alloggio(idLuogo, piscina, numeroStanze, wifi)\r\n" + 
					"VALUES ('"+ ID +"','"+ ((piscina) ? 1:0) + "','" + numeroStanze + "','" + ((wifi) ? 1:0) + "');"; //Inizializzo query 2
			
			String connectionURL = MainController.URL; //URL di connessione
			
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
			
			if(specializzazione.contentEquals("Hotel")) {
				System.out.println("Inserimento hotel");
				int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
				
				String q2 = "INSERT INTO hotel(idAlloggio, numeroStelle)\r\n" + 
						"VALUES ('"+ ID + "','" + numeroStelle + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else if(specializzazione.contentEquals("Motel")) {
				System.out.println("Inserimento motel");
				boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
				
				String q2 = "INSERT INTO motel(idAlloggio, assistenzaAutovetture)\r\n" + 
						"VALUES ('"+ ID + "','" + ((assistenzaAutovetture) ? 1:0) + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else {
				System.out.println("Inserimento BB");
				boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
				
				String q2 = "INSERT INTO bedbreakfast(idAlloggio, colazione)\r\n" + 
						"VALUES ('"+ ID + "','" + ((colazione) ? 1:0) + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
	public Alloggio getAlloggioByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM alloggio WHERE idLuogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        boolean piscina = rs.getBoolean("piscina");
	        boolean wifi = rs.getBoolean("wifi");
	        int numeroStanze = rs.getInt("numeroStanze");
	        
	        rs.close();
	        
			if(specializzazione.contentEquals("Hotel")) {
				q = "SELECT * FROM hotel WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				int numeroStelle = r.getInt("numeroStelle");
				Hotel h = new Hotel();
				h.setNumeroStanze(numeroStanze);
				h.setPiscina(piscina);
				h.setWifi(wifi);
				h.setNumeroStelle(numeroStelle);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return h;
			}else if(specializzazione.contentEquals("Motel")) {
				q = "SELECT * FROM motel WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean assitenza = r.getBoolean("assistenzaAutovetture");
				Motel m = new Motel();
				m.setNumeroStanze(numeroStanze);
				m.setPiscina(piscina);
				m.setWifi(wifi);
				m.setAssistenzaAuto(assitenza);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return m;
			}else {
				q = "SELECT * FROM bedbreakfast WHERE idAlloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean colazione = r.getBoolean("colazione");
				BB b = new BB();
				b.setNumeroStanze(numeroStanze);
				b.setPiscina(piscina);
				b.setWifi(wifi);
				b.setColazione(colazione);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return b;
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Operazione inserimento fallita, restituisce false
		}
		
	}

	public boolean modificaAlloggio(MainController c, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive) {
		
		try {
			
		System.out.println("Modifica alloggio");
		boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscina")).isSelected();
		boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifi")).isSelected();
		int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString());
		String specializzazione = vecchioLuogo.getAttributoAttivita();
		
		Class.forName("com.mysql.jdbc.Driver");
		String q = "UPDATE alloggio SET piscina = " + ((piscina) ? 1:0) + ", numeroStanze = " + numeroStanze  + ", wifi = " + ((wifi)? 1:0) + " WHERE idLuogo = " + vecchioLuogo.getID();
		
		String connectionURL = MainController.URL; //URL di connessione

        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
        Statement st = con.createStatement(); //Creo statement
        
		st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
		
		if(specializzazione.contentEquals("Hotel")) {
			System.out.println("Modifica Hotel");
			int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
			
			q = "UPDATE hotel SET numeroStelle = " + numeroStelle + " WHERE idAlloggio = " + vecchioLuogo.getID();
			
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q3
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return true;
			
		}else if(specializzazione.contentEquals("Motel")) {
			System.out.println("Modifica Motel");
			boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
			
			q = "UPDATE motel SET assistenzaAutovetture = " + ((assistenzaAutovetture)? 1:0) + " WHERE idAlloggio = " + vecchioLuogo.getID();
			
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q3
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return true;
			
		}else {
			System.out.println("Modifica BB");
			boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
			
			q = "UPDATE bedbreakfast SET colazione = " + ((colazione)? 1:0) + " WHERE idAlloggio = " + vecchioLuogo.getID();
			
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

}
