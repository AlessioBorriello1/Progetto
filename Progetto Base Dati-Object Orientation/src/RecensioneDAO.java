import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

	/**
	 * Aggiungi una recensione all'interno del database
	 * @param mainFrame MainFrame
	 * @param u Utente che crea la recensione
	 * @param l Luogo a cui attribuire la recensione
	 * @param r Recensione da creare
	 * @return Operazione riuscita o meno
	 */
	public boolean lasciaRecensioneALuogo(MainFrame mainFrame, Utente u, Luogo l, Recensione r) {
		
		//Prendi lista recensioni dell'utente che vuole creare la recensione
		List<Recensione> lista = getListaRecensioniByNomeUtente(u.getNomeUtente());
		for(Recensione recensione : lista) {
			//Se l'utente ha già creato una recensione per quel luogo
			if(recensione.getIDLuogo() == l.getID()) {
				mainFrame.createNotificationFrame("Hai già creato una recensione per questo luogo!");
				return false;
			}
			
		}
		
		int voto = r.getVoto();
		String recensione = r.getTesto();
		
		try {
			
			LocalDateTime now = LocalDateTime.now();  
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "INSERT INTO recensione(idLuogo, voto, nomeUtente, data, testo)\r\n" + 
					"VALUES ('"+ l.getID() +"','"+ voto +"', '"+ u.getNomeUtente()+ "','" +  now +"','" + recensione +"');"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			mainFrame.createNotificationFrame("Recensione inserita!");
			
			LuogoDAO dao = new LuogoDAO();
			float media = dao.calcolaMediaRecensioniLuogo(l); //Calcolo la nuova media
			l.setMediaRecensioni(media); //Setto la media del luogo
			dao.updateMediaRecensioni(l, media); //Aggiorno la media nel database
			
			return true; //Operazione riuscita
		
		}catch(Exception e) { //Error catching
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione fallita
		}
		
	}
	
	/**
	 * Modifica una recensione all'interno del database
	 * @param mainFrame MainFrame
	 * @param u Utente che modifica la recensione
	 * @param l Luogo a cui modificare la recensione
	 * @param voto Voto della recensione
	 * @param recensione Stringa testo della recensione
	 * @param r Recensione da modificare
	 * @return Operazione riuscita o meno
	 */
	public boolean modificaRecensioneLuogo(MainFrame mainFrame, Utente u, Luogo l, int voto, String recensione, Recensione r) {
		
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		String q = "UPDATE recensione SET voto = " + voto + ", testo = '" + recensione  + "' WHERE idLuogo = " + l.getID() + " AND nomeUtente = '" + u.getNomeUtente() + "'";
		
		String connectionURL = MainController.URL; //URL di connessione

        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
		Statement st = con.createStatement(); //Creo statement
		st.executeUpdate(q); //Eseguo la query contenuta in stringa q
		
		con.close(); //Chiudi connessione
		st.close(); //Chiudi statement
		
		mainFrame.createNotificationFrame("Recensione modificata!");
		
		LuogoDAO dao = new LuogoDAO();
		float media = dao.calcolaMediaRecensioniLuogo(l); //Calcolo la nuova media
		l.setMediaRecensioni(media); //Setto la media del luogo
		dao.updateMediaRecensioni(l, media); //Aggiorno la media nel database
		
		//Imposta i nuovi valori della recensione nell'oggetto
		r.setTesto(recensione);
		r.setVoto(voto);
		
		return true; //Operazione riuscita
		
		}catch(Exception e) { //Error catching
			System.out.println(e);
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione fallita
		}
		
	}
	
	/**
	 * Prende la recensione in un certo luogo di un determinato utente
	 * @param l Luogo a cui è riferita la recensione
	 * @param nomeUtente Stringa nome dell'utente che ha creato la recensione
	 * @return Recensione del luogo specificato creata dall'utente specificato
	 */
	public Recensione getRecensioneLuogoByNomeUtente(Luogo l, String nomeUtente) {
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String q = "SELECT * FROM recensione WHERE idLuogo = '" + l.getID() + "' AND nomeUtente = '" + nomeUtente + "'"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeQuery(q); //Eseguo la query contenuta in stringa q
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se la recensione è stata trovata
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("data"));
				r.setIDLuogo(l.getID());
				r.setNomeUtente(nomeUtente);
				r.setVoto(rs.getInt("voto"));
				r.setTesto(rs.getString("testo"));
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return r; //Restituisci recensione
				
			}else { //Se la recensione non è stata trovata (result set vuoto)
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return null; //Restituisci null
				
			}
			
			}catch(Exception e) { //Error catching
				System.out.println(e);
				return null; //Restituisci null
			}
		
		}
	
	/**
	 * Ottieni lista recensioni di un certo luogo
	 * @param l Luogo da cui prendere le recensioni
	 * @return Lista recensioni del luogo specificato
	 */
	public List<Recensione> getListaRecensioniLuogo(Luogo l){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from recensione where idLuogo= '" + l.getID() + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>(); //Lista delle recensioni
			
			while(rs.next()) {
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("data"));
				r.setIDLuogo(l.getID());
				r.setNomeUtente(rs.getString("nomeUtente"));
				r.setVoto(rs.getInt("voto"));
				r.setTesto(rs.getString("testo"));
				
				recensioni.add(r); //Inserisci recensione nella lista
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return recensioni; //Restituisci lista recensioni
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Restituisci null
		}
		
	}
	
	/**
	 * Ottieni lista recensioni di un certo utente
	 * @param nomeUtente Stringa nome dell'utente da cui prendere le recensioni
	 * @return Lista recensioni dell'utente specificato
	 */
	public List<Recensione> getListaRecensioniByNomeUtente(String nomeUtente) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from recensione where nomeUtente = '" + nomeUtente + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>(); //Lista recensioni
			
			while(rs.next()) {
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("data"));
				r.setNomeUtente(nomeUtente);
				r.setTesto(rs.getString("testo"));
				r.setVoto(rs.getInt("voto"));
				r.setIDLuogo(rs.getInt("idLuogo"));
				
				recensioni.add(r); 
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return recensioni; //Restituisci lista recensioni
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Restituisci null
		}
		
	}

	/**
	 * Rimuovi recensione dal database
	 * @param controller MainController
	 * @param l Luogo che possiede la recensione da eliminare
	 * @param r Recensione da eliminare
	 * @return Operazione riuscita o meno
	 */
	public boolean rimuoviRecensioneLuogo(MainController controller, Luogo l, Recensione r) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "DELETE FROM recensione WHERE idLuogo = " + r.getIDLuogo() + " AND nomeUtente = '" + r.getNomeUtente() + "'";
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			LuogoDAO dao = new LuogoDAO();
			float media = dao.calcolaMediaRecensioniLuogo(l); //Ricalocala media senza la recensione appena eliminata
			l.setMediaRecensioni(media); //imposta media recensione per il luogo0
			dao.updateMediaRecensioni(l, media); //Aggiorna la media nel database
			
			
			return true; //Operazione eliminazione riuscita, restituisce true
			
			}catch(Exception e) { //Error catching
				return false; //Operazione eliminazione fallita, restituisce false
			}
	}

}
