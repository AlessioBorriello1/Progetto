import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AttrazioneDAO {

	public boolean creaAttrazione(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u, int ID) {
		
		try {
			
			System.out.println("Inserimento attrazione");
			boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudenti")).isSelected();
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO attrazione(idluogo, promozionestudenti)\r\n" + 
					"VALUES ('"+ ID + "','" + ((promozione) ? 1:0) + "');"; //Inizializzo query 2
			
			String connectionURL = MainController.URL; //URL di connessione
			
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
			
			if(specializzazione.contentEquals("Museo")) {
				System.out.println("Inserimento museo");
				String tipoMuseo = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoMuseo")).getSelectedItem().toString();
				
				String q2 = "INSERT INTO museo(idattrazione, tipomuseo)\r\n" + 
						"VALUES ('"+ ID + "','" + tipoMuseo + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else if(specializzazione.contentEquals("Zoo")) {
				System.out.println("Inserimento zoo");
				String tipoSpecie = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxSpecie")).getSelectedItem().toString();
				
				String q2 = "INSERT INTO zoo(idattrazione, specie)\r\n" + 
						"VALUES ('"+ ID + "','" + tipoSpecie + "');"; //Inizializzo query 3
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q3
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else {
				System.out.println("Inserimento Parco");
				boolean gratuito = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxIngressoGratuito")).isSelected();
				
				String q2 = "INSERT INTO parco(idattrazione, ingressogratuito)\r\n" + 
						"VALUES ('"+ ID + "','" + ((gratuito) ? 1:0) + "');"; //Inizializzo query 3
				
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
	
	public Attrazione getAttrazioneByID(int ID, String specializzazione) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
	        String q = "SELECT * FROM attrazione WHERE idluogo = '" + ID + "'";
	        ResultSet rs = st.executeQuery(q);
	        
	        rs.next(); //Vai a inizio risultati
	        
	        boolean promozione = rs.getBoolean("promozionestudenti");
	        
	        rs.close();
	        
			if(specializzazione.contentEquals("Museo")) {
				q = "SELECT * FROM museo WHERE idattrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String tipoMuseo = r.getString("tipomuseo");
				Museo m = new Museo();
				m.setPromozioneStudenti(promozione);
				m.setTipoMuseo(tipoMuseo);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return m;
			}else if(specializzazione.contentEquals("Zoo")) {
				q = "SELECT * FROM zoo WHERE idattrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				String specie = r.getString("specie");
				Zoo z = new Zoo();
				z.setPromozioneStudenti(promozione);
				z.setSpecie(specie);
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return z;
			}else {
				q = "SELECT * FROM parco WHERE idattrazione = '" + ID + "'";
				ResultSet r = st.executeQuery(q); //Eseguo la query contenuta in stringa
				r.next(); //Vai a inizio risultati
				boolean ingresso = r.getBoolean("ingressogratuito");
				Parco p = new Parco();
				p.setPromozioneStudenti(promozione);
				p.setIngressoGratuito(ingresso);
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
