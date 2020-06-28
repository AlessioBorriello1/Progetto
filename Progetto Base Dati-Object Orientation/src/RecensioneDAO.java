import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

	public boolean lasciaRecensioneALuogo(MainFrame mainFrame, Utente u, Luogo l, int voto, String recensione, int numeroRecensioni) {
		
		try {
			
		RecensioneDAO daor = new RecensioneDAO();
		int somma = 0;
		for(Recensione r: daor.getListaRecensioniLuogo(l)) {
			somma += r.getVoto();
		}
			
		LocalDateTime now = LocalDateTime.now();  
		
		Class.forName("com.mysql.jdbc.Driver");
		String q = "INSERT INTO recensione(IdLuogo, Voto, Idutente, Data, Testo)\r\n" + 
				"VALUES ('"+ l.getID() +"','"+ voto +"', '"+ u.getNomeUtente()+ "','" +  now +"','" + recensione +"');"; //Inizializzo query
		
		String connectionURL = MainController.URL; //URL di connessione

        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
		Statement st = con.createStatement(); //Creo statement
		st.executeUpdate(q); //Eseguo la query contenuta in stringa q
		
		con.close(); //Chiudi connessione
		st.close(); //Chiudi statement
		
		mainFrame.createNotificationFrame("Recensione inserita!");
		
		float media = (float)(somma + voto) / (numeroRecensioni + 1);
		l.setMediaRecensioni(media);
		LuogoDAO dao = new LuogoDAO();
		dao.updateMediaRecensioni(l, media);
		
		return true; //Operazione inserimento riuscita, restituisce true
		
		}catch(Exception e) { //Error catching
			if(e.getClass().toString().contentEquals("class com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
				mainFrame.createNotificationFrame("Hai gi� creato una recensione per questo luogo!");
				return false; //Operazione inserimento fallita, restituisce false
			}
			mainFrame.createNotificationFrame("Qualcosa � andato storto!");
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
	public boolean modificaRecensioneLuogo(MainFrame mainFrame, Utente u, Luogo l, int voto, String recensione, int numeroRecensioni, Recensione r) {
		
		try {
			
			
		RecensioneDAO daor = new RecensioneDAO();
		int somma = 0;
		for(Recensione rev: daor.getListaRecensioniLuogo(l)) {
			somma += rev.getVoto();
		}
		
		LocalDateTime now = LocalDateTime.now();  
		
		Class.forName("com.mysql.jdbc.Driver");
		String q = "UPDATE recensione SET Voto = " + voto + ", Testo = '" + recensione  + "' WHERE IdLuogo = " + l.getID() + " AND Idutente = '" + u.getNomeUtente() + "'";
		
		String connectionURL = MainController.URL; //URL di connessione

        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
		Statement st = con.createStatement(); //Creo statement
		st.executeUpdate(q); //Eseguo la query contenuta in stringa q
		
		con.close(); //Chiudi connessione
		st.close(); //Chiudi statement
		
		mainFrame.createNotificationFrame("Recensione modificata!");
		
		float media = (float)(somma - r.getVoto() + voto) / (numeroRecensioni);
		l.setMediaRecensioni(media);
		LuogoDAO dao = new LuogoDAO();
		dao.updateMediaRecensioni(l, media);
		
		r.setTesto(recensione);
		r.setVoto(voto);
		
		return true; //Operazione inserimento riuscita, restituisce true
		
		}catch(Exception e) { //Error catching
			if(e.getClass().toString().contentEquals("class com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
				System.out.println(e);
				mainFrame.createNotificationFrame("Hai gi� creato una recensione per questo luogo!");
				return false; //Operazione inserimento fallita, restituisce false
			}
			System.out.println(e);
			mainFrame.createNotificationFrame("Qualcosa � andato storto!");
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
	public Recensione getRecensioneLuogoByNomeUtente(Luogo l, String nomeUtente) {
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String q = "SELECT * FROM recensione WHERE IdLuogo = '" + l.getID() + "' AND Idutente = '" + nomeUtente + "'"; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeQuery(q); //Eseguo la query contenuta in stringa q
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se la recensione � stata trovata
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("Data"));
				r.setIDLuogo(l.getID());
				r.setNomeUtente(nomeUtente);
				r.setVoto(rs.getInt("Voto"));
				r.setTesto(rs.getString("Testo"));
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return r;
				
			}else { //Se l'utente non � stato trovato (result set vuoto)
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return null; //Restituisci null
				
			}
			
			}catch(Exception e) { //Error catching
				System.out.println(e);
				return null; //Operazione inserimento fallita, restituisce false
			}
		
		}
	
	public List<Recensione> getListaRecensioniLuogo(Luogo l){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from recensione where IdLuogo= '" + l.getID() + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>();
			
			while(rs.next()) {
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("Data"));
				r.setIDLuogo(l.getID());
				r.setNomeUtente(rs.getString("Idutente"));
				r.setVoto(rs.getInt("Voto"));
				r.setTesto(rs.getString("Testo"));
				
				recensioni.add(r);
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return recensioni; //Restituisci lista recensioni
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}
	
	public List<Recensione> getListaRecensioniByNomeUtente(String nomeUtente) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from recensione where Idutente = '" + nomeUtente + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>();
			
			while(rs.next()) {
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("Data"));
				r.setNomeUtente(nomeUtente);
				r.setTesto(rs.getString("Testo"));
				r.setVoto(rs.getInt("Voto"));
				r.setIDLuogo(rs.getInt("IdLuogo"));
				
				recensioni.add(r);
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return recensioni; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}

}
