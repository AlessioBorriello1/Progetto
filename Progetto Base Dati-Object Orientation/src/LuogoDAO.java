import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class LuogoDAO {
	
	public boolean creaLuogo(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u) {
		
		try {
			
			if(nome.contentEquals("") || indirizzo.contentEquals("") || proprietario.contentEquals("") || telefono.length() < 10) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Completa tutti i campi!");
				return false; //Operazione fallita, valori non inseriti
			}
			
			if(tipoAttivita.contentEquals("Alloggio")) {
				if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString().contentEquals("")) {
					mf.createNotificationFrame("Completa tutti i campi!");
					return false; //Operazione fallita, valori non inseriti
				}
			}
			
			if(luogoEsiste(nome, proprietario)){
				mf.createNotificationFrame("Un luogo con questo nome e proprietario esiste già!");
				return false; //Operazione fallita, valori non inseriti
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			String q1 = "INSERT INTO luogo(Nome, Indirizzo, Telefono, Proprietario, Idutente, tipoattivita, attributoattivita)\r\n" + 
					"VALUES ('"+ nome +"','"+ indirizzo +"','"+ telefono + "','" + proprietario + "','" + u.getNomeUtente() + "','" + tipoAttivita + "','" + specializzazione + "');"; //Inizializzo query 1
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q1); //Eseguo la query contenuta in stringa q1
			
			if(tipoAttivita.contentEquals("Ristorante")) {
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				RistoranteDAO dao = new RistoranteDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaRistorante(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			}else if(tipoAttivita.contentEquals("Alloggio")) {
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				AlloggioDAO dao = new AlloggioDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaAlloggio(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			}else {
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				AttrazioneDAO dao = new AttrazioneDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaAttrazione(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
			
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
	
	}
	
	public int getIDLuogoByNomeAndPossessore(String nome, String proprietario) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where Nome = '" + nome + "' AND Proprietario = '" + proprietario + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			System.out.println("Query eseguita");
			
			if(rs.next()) { //Se il luogo è stato trovato
				
				return (rs.getInt("Idluogo"));
				
			}else { //Se il luogo non è stato trovato (result set vuoto)
	
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				System.out.println("Resul set vuoto");
				return -1; //Restituisci null
				
			}
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return -1;
		}
		
	}
	
	public boolean luogoEsiste(String nome, String proprietario) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where Nome = '" + nome + "' AND Proprietario = '" + proprietario + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se il luogo è stato trovato
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else { //Se il luogo non è stato trovato (result set vuoto)
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return false;
				
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false;
		}
		
	}
	
}
