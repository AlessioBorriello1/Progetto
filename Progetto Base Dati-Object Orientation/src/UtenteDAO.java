import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class UtenteDAO {
	
	/**
	 * Cerca nel DataBase uno specifico utente in base al suo Nome Utente o Email e Password
	 * @param nomeUtente Nome Utente oppure Email dell'utente da cercare
	 * @param password Password dell'utente da cercare
	 * @param mf MainFrame per avere accesso alla funzione per creare notifiche
	 * @return Oggetto Utente (Se presente nel DataBase)
	 */
	public Utente getUtente(String nomeUtente, String password, MainFrame mf) {
		
		try {
			
			if(nomeUtente.contentEquals("") && password.contentEquals("")) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Inserisci i valori Nome Utente e Password!");
				return null; //Operazione fallita, valori non inseriti
			}else if(nomeUtente.contentEquals("")) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Inserisci Nome Utente!");
				return null; //Operazione fallita, valori non inseriti
			}else if(password.contentEquals("")) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Inserisci Password!");
				return null; //Operazione fallita, valori non inseriti
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from utente where (nomeUtente = '" + nomeUtente + "' OR email = '" + nomeUtente + "') AND password = '" + password + "'" ; //Inizializzo query
			Utente u = new Utente(); //Instanzio nuovo utente
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se l'utente è stato trovato
				
				u.setNomeUtente(rs.getString("nomeUtente")); //Setta variabile nomeUtente dell'utente "u" uguale al campo "Idutente"
				u.setEmail(rs.getString("email")); //Setta variabile email dell'utente "u" uguale al campo "Email"
				u.setNumeroLuoghi(rs.getInt("numeroLuoghi")); //Setta variabile numeroLuoghi dell'utente "u" uguale al campo "NumeroLuoghi"
				u.setNumeroRecensioni(rs.getInt("numeroRecensioni")); //Setta variabile numeroRecensioni dell'utente "u" uguale al campo "NumeroRecensioni"
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return u;
				
			}else { //Se l'utente non è stato trovato (result set vuoto)
				
				System.out.println("Resul set vuoto");
				mf.createNotificationFrame("I valori inseriti non sono corretti!");
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return null; //Restituisci null
				
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Inserisce nel DataBase un nuovo utente, se non abbiamo un istanza di NomeUtente e Email già presenti nel database
	 * @param nomeUtente Nome Utente dell'utente da inserire
	 * @param email Email dell'utente da inserire
	 * @param password Password dell'utente da inserire
	 * @param mf MainFrame per avere accesso alla funzione per creare notifiche
	 * @return boolean (Se operazione riuscita)
	 */
	public boolean registraUtente(String nomeUtente, String email, String password, MainFrame mf) {
			
		try {
				
			if(nomeUtente.contentEquals("") || email.contentEquals("") || password.contentEquals("")) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Completa tutti i campi!");
				return false; //Operazione inserimento fallita, restituisce false
			}
			
			if(utenteEsiste(nomeUtente, email)) { //Controlla se l'utente è già nel database
				
				System.out.println("Utente già esistente");
				mf.createNotificationFrame("Nome utente o Email non diponibili!");
				return false; //Operazione inserimento fallita, restituisce false
				
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO utente(nomeUtente, email, password, numeroRecensioni, numeroLuoghi)\r\n" + 
					"VALUES ('"+ nomeUtente +"','"+ email +"', '"+ password+ "','0','0');"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			mf.createNotificationFrame("Registrazione completata!");
			
			return true; //Operazione inserimento riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			mf.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione inserimento fallita, restituisce false
		}
	}
	
	/**
	 * Controlla in base a nomeUtente e Email se un utente è presente nel database
	 * @param nomeUtente Nome Utente dell'utente da cercare
	 * @param email Email dell'utente da cercare
	 * @return boolean (Se presente nel DataBase)
	 */
	public boolean utenteEsiste(String nomeUtente, String email) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from utente where nomeUtente = '" + nomeUtente + "' OR email = '" + email + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se l'utente è stato trovato
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else { //Se l'utente non è stato trovato (result set vuoto)
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return false;
				
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Aggiorna il numero di luoghi di un certo utente nel database
	 * @param nomeUtente Stringa nome dell'utente di cui aggiornare il numero dei luoghi
	 * @return Operazione aggiornamento riuscita o meno
	 */
	public boolean updateNumeroLuoghi(String nomeUtente) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE utente SET numeroLuoghi = (SELECT COUNT(*) FROM luogo WHERE nomeUtente = '" + nomeUtente + "') WHERE nomeUtente = '" + nomeUtente + "'";//Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			return true; //Operazione modifica riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione modifica fallita, restituisce false
		}
	}
	
	/**
	 * Aggiorna il numero di recensioni di un certo utente nel database
	 * @param nomeUtente Stringa nome dell'utente di cui aggiornare il numero delle recensioni
	 * @return Operazione aggiornamento riuscita o meno
	 */
	public boolean updateNumeroRecensioni(String nomeUtente) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE utente SET numeroRecensioni = (SELECT COUNT(*) FROM recensione WHERE nomeUtente = '" + nomeUtente + "') WHERE nomeUtente = '" + nomeUtente + "'";//Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			return true; //Operazione aggiornamento riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione aggiornamento fallita, restituisce false
		}
	}


}