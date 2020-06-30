import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

	public boolean lasciaRecensioneALuogo(MainFrame mainFrame, Utente u, Luogo l, Recensione r) {
		
		
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
		float media = dao.calcolaMediaRecensioniLuogo(l);
		l.setMediaRecensioni(media);
		dao.updateMediaRecensioni(l, media);
		
		return true; //Operazione inserimento riuscita, restituisce true
		
		}catch(Exception e) { //Error catching
			if(e.getClass().toString().contentEquals("class com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
				mainFrame.createNotificationFrame("Hai già creato una recensione per questo luogo!");
				return false; //Operazione inserimento fallita, restituisce false
			}
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
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
		float media = dao.calcolaMediaRecensioniLuogo(l);
		l.setMediaRecensioni(media);
		dao.updateMediaRecensioni(l, media);
		
		r.setTesto(recensione);
		r.setVoto(voto);
		
		return true; //Operazione inserimento riuscita, restituisce true
		
		}catch(Exception e) { //Error catching
			if(e.getClass().toString().contentEquals("class com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
				System.out.println(e);
				mainFrame.createNotificationFrame("Hai già creato una recensione per questo luogo!");
				return false; //Operazione inserimento fallita, restituisce false
			}
			System.out.println(e);
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
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
				return r;
				
			}else { //Se l'utente non è stato trovato (result set vuoto)
				
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
			String q = "Select * from recensione where idLuogo= '" + l.getID() + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>();
			
			while(rs.next()) {
				
				Recensione r = new Recensione();
				r.setData(rs.getDate("data"));
				r.setIDLuogo(l.getID());
				r.setNomeUtente(rs.getString("nomeUtente"));
				r.setVoto(rs.getInt("voto"));
				r.setTesto(rs.getString("testo"));
				
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
			String q = "Select * from recensione where nomeUtente = '" + nomeUtente + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Recensione> recensioni = new ArrayList<Recensione>();
			
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
			return recensioni; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}

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
			float media = dao.calcolaMediaRecensioniLuogo(l);
			l.setMediaRecensioni(media);
			dao.updateMediaRecensioni(l, media);
			
			
			return true; //Operazione inserimento riuscita, restituisce true
			
			}catch(Exception e) { //Error catching
				return false; //Operazione inserimento fallita, restituisce false
			}
	}

}
