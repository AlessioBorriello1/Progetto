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
			String q = "INSERT INTO alloggio(idluogo, piscina, NumeroStanze, WiFi)\r\n" + 
					"VALUES ('"+ ID +"','"+ ((piscina) ? 1:0) + "','" + numeroStanze + "','" + ((wifi) ? 1:0) + "');"; //Inizializzo query 2
			
			String connectionURL = MainController.URL; //URL di connessione
			
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
			
			if(specializzazione.contentEquals("Hotel")) {
				System.out.println("Inserimento hotel");
				int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
				
				String q2 = "INSERT INTO hotel(idalloggio, numerostelle)\r\n" + 
						"VALUES ('"+ ID + "','" + numeroStelle + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else if(specializzazione.contentEquals("Motel")) {
				System.out.println("Inserimento motel");
				boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
				
				String q2 = "INSERT INTO motel(idalloggio, assistenzaautovetture)\r\n" + 
						"VALUES ('"+ ID + "','" + ((assistenzaAutovetture) ? 1:0) + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else {
				System.out.println("Inserimento BB");
				boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
				
				String q2 = "INSERT INTO bedbreakfast(idalloggio, colazione)\r\n" + 
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
	        
	        String q = "SELECT * FROM alloggio WHERE idluogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        boolean piscina = rs.getBoolean("piscina");
	        boolean wifi = rs.getBoolean("WiFi");
	        int numeroStanze = rs.getInt("NumeroStanze");
	        
	        rs.close();
	        
			if(specializzazione.contentEquals("Hotel")) {
				q = "SELECT * FROM hotel WHERE idalloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				int numeroStelle = r.getInt("numerostelle");
				Hotel h = new Hotel();
				h.setNumeroStanze(numeroStanze);
				h.setPiscina(piscina);
				h.setWifi(wifi);
				h.setNumeroStelle(numeroStelle);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return h;
			}else if(specializzazione.contentEquals("Motel")) {
				q = "SELECT * FROM motel WHERE idalloggio = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean assitenza = r.getBoolean("assistenzaautovetture");
				Motel m = new Motel();
				m.setNumeroStanze(numeroStanze);
				m.setPiscina(piscina);
				m.setWifi(wifi);
				m.setAssistenzaAuto(assitenza);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return m;
			}else {
				q = "SELECT * FROM bedbreakfast WHERE idalloggio = '" + ID + "'";
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
	
}
