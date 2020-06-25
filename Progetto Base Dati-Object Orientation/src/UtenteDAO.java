import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String q = "Select * from utente where (Idutente = '" + nomeUtente + "' OR Email = '" + nomeUtente + "') AND Password = '" + password + "'" ; //Inizializzo query
			Utente u = new Utente(); //Instanzio nuovo utente
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			System.out.println("Query eseguita");
			
			if(rs.next()) { //Se l'utente è stato trovato
				
				u.setNomeUtente(rs.getString("Idutente")); //Setta variabile nomeUtente dell'utente "u" uguale al campo "Idutente"
				u.setEmail(rs.getString("Email")); //Setta variabile email dell'utente "u" uguale al campo "Email"
				u.setNumeroLuoghi(rs.getInt("NumeroLuoghi")); //Setta variabile numeroLuoghi dell'utente "u" uguale al campo "NumeroLuoghi"
				u.setNumeroRecensioni(rs.getInt("NumeroRecensioni")); //Setta variabile numeroRecensioni dell'utente "u" uguale al campo "NumeroRecensioni"
				
			}else { //Se l'utente non è stato trovato (result set vuoto)
				
				System.out.println("Resul set vuoto");
				mf.createNotificationFrame("I valori inseriti non sono corretti!");
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return null; //Restituisci null
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			System.out.println("Utente trovato");
			return u; //Restituisci utente
			
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
					mf.createNotificationFrame("Esiste già un utente con questo Nome utente o Email");
					return false; //Operazione inserimento fallita, restituisce false
					
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				String q = "INSERT INTO utente(Idutente, Email, Password, NumeroRecensioni, NumeroLuoghi)\r\n" + 
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
			String q = "Select * from utente where Idutente = '" + nomeUtente + "' OR Email = '" + email + "'" ; //Inizializzo query
			
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
	
}