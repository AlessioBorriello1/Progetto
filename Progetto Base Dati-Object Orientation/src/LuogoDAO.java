import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			String q = "INSERT INTO luogo(nome, indirizzo, telefono, proprietario, nomeUtente, tipoAttivita, attributoAttivita)\r\n" + 
					"VALUES ('"+ nome +"','"+ indirizzo +"','"+ telefono + "','" + proprietario + "','" + u.getNomeUtente() + "','" + tipoAttivita + "','" + specializzazione + "');"; //Inizializzo query 1
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q1
			
			if(tipoAttivita.contentEquals("Ristorante")) {
				//Crea ristorante
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				RistoranteDAO dao = new RistoranteDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaRistorante(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			}else if(tipoAttivita.contentEquals("Alloggio")) {
				//Crea alloggio
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				AlloggioDAO dao = new AlloggioDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaAlloggio(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			}else {
				//Crea attrazione
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
			String q = "Select * from luogo where nome = '" + nome + "' AND proprietario = '" + proprietario + "'" ; //Inizializzo query
			
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
			String q = "Select * from luogo where nome = '" + nome + "' AND proprietario = '" + proprietario + "'" ; //Inizializzo query
			
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
	
	public List<Luogo> getListaLuoghiByNomeUtente(String nomeUtente) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where nomeUtente = '" + nomeUtente + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>();
			
			while(rs.next()) {
				
				int ID = rs.getInt("idLuogo"); //ID unico luogo
				String nome = rs.getString("nome"); //Nome luogo
				String indirizzo = rs.getString("indirizzo"); //Indirizzo luogo
				String telefono = rs.getString("telefono"); //Numero telefono luogo
				String proprietario = rs.getString("proprietario"); //Nome proprietario
				float mediaRecensioni = rs.getFloat("mediaRecensioni"); //Media recensioni
				String tipoAttivita = rs.getString("tipoAttivita"); //Tipo attività (Ristorante, Alloggio, Attrazione)
				String attributoAttivita = rs.getString("attributoAttivita"); //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
				
				if(tipoAttivita.contentEquals("Ristorante")) {
					
					RistoranteDAO dao = new RistoranteDAO();
					Luogo l = dao.getRistoranteByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					luoghi.add(l);
					
				}else if(tipoAttivita.contentEquals("Alloggio")) {
					
					AlloggioDAO dao = new AlloggioDAO();
					Luogo l = dao.getAlloggioByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					luoghi.add(l);
					
				}else {
					
					AttrazioneDAO dao = new AttrazioneDAO();
					Luogo l = dao.getAttrazioneByID(ID, attributoAttivita);
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					luoghi.add(l);
				}
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return luoghi; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}

	public Luogo getLuogoByID(int ID) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where idLuogo = '" + ID + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>();
			
			if(rs.next()) { //Luogo trovato
				
				String nomeUtente = rs.getString("nomeUtente"); //Nickname utente
				String nome = rs.getString("nome"); //Nome luogo
				String indirizzo = rs.getString("indirizzo"); //Indirizzo luogo
				String telefono = rs.getString("telefono"); //Numero telefono luogo
				String proprietario = rs.getString("proprietario"); //Nome proprietario
				float mediaRecensioni = rs.getFloat("mediaRecensioni"); //Media recensioni
				String tipoAttivita = rs.getString("tipoAttivita"); //Tipo attività (Ristorante, Alloggio, Attrazione)
				String attributoAttivita = rs.getString("attributoAttivita"); //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
				
				if(tipoAttivita.contentEquals("Ristorante")) {
					
					RistoranteDAO dao = new RistoranteDAO();
					Luogo l = dao.getRistoranteByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					return l; //Restituisci luogo
					
				}else if(tipoAttivita.contentEquals("Alloggio")) {
					
					AlloggioDAO dao = new AlloggioDAO();
					Luogo l = dao.getAlloggioByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					return l; //Restituisci luogo
					
				}else {
					
					AttrazioneDAO dao = new AttrazioneDAO();
					Luogo l = dao.getAttrazioneByID(ID, attributoAttivita);
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					return l; //Restituisci luogo
				}
				
			}else { //Luogo non trovato
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return null; //Restituisci luogo
				
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}

	public List<Luogo> getListaTuttiLuoghi(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>();
			
			while(rs.next()) {
				
				int ID = rs.getInt("idLuogo"); //ID unico luogo
				String nomeUtente = rs.getString("nomeUtente"); //Nome utente
				String nome = rs.getString("nome"); //Nome luogo
				String indirizzo = rs.getString("indirizzo"); //Indirizzo luogo
				String telefono = rs.getString("telefono"); //Numero telefono luogo
				String proprietario = rs.getString("proprietario"); //Nome proprietario
				float mediaRecensioni = rs.getFloat("mediaRecensioni"); //Media recensioni
				String tipoAttivita = rs.getString("tipoAttivita"); //Tipo attività (Ristorante, Alloggio, Attrazione)
				String attributoAttivita = rs.getString("attributoAttivita"); //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
				
				if(tipoAttivita.contentEquals("Ristorante")) {
					
					RistoranteDAO dao = new RistoranteDAO();
					Luogo l = dao.getRistoranteByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					luoghi.add(l);
					
				}else if(tipoAttivita.contentEquals("Alloggio")) {
					
					AlloggioDAO dao = new AlloggioDAO();
					Luogo l = dao.getAlloggioByID(ID, attributoAttivita);
					
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					luoghi.add(l);
					
				}else {
					
					AttrazioneDAO dao = new AttrazioneDAO();
					Luogo l = dao.getAttrazioneByID(ID, attributoAttivita);
					l.setID(ID);
					l.setIndirizzo(indirizzo);
					l.setMediaRecensioni(mediaRecensioni);
					l.setNome(nome);
					l.setProprietario(proprietario);
					l.setNomeUtente(nomeUtente);
					l.setTelefono(telefono);
					l.setAttributoAttivita(attributoAttivita);
					l.setTipoAttivita(tipoAttivita);
					
					luoghi.add(l);
				}
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return luoghi; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null;
		}
		
	}

	public float calcolaMediaRecensioniLuogo(Luogo l) {
		
		RecensioneDAO dao = new RecensioneDAO();
		List<Recensione> lista = new ArrayList<Recensione>();
		lista = dao.getListaRecensioniLuogo(l);
		
		int somma = 0;
		int c = 0;
		for(Recensione r: lista) {
			somma += r.getVoto();
			c++;
		}
		
		if(c == 0) {
			return 0;
		}else {
			return (float)somma/c;
		}
		
		
	}

	public boolean updateMediaRecensioni(Luogo l, float media) {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE luogo SET mediaRecensioni = " + media + " WHERE idLuogo = " + l.getID();//Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			return true; //Operazione inserimento riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}

	public boolean rimuoviLuogo(MainController controller, Luogo l, Utente u) {
		
		try {
			
			RecensioneDAO dao = new RecensioneDAO();
			List<Recensione> lista = dao.getListaRecensioniLuogo(l);
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "DELETE FROM luogo WHERE idLuogo = " + l.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q2
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			for(Recensione r : lista) {
				
				UtenteDAO dao2 = new UtenteDAO();
				dao2.updateNumeroRecensioni(r.getNomeUtente());
			
			}
			
			return true;
		
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
		
	}
	
}