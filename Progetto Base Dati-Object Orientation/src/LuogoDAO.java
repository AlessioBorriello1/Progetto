import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class LuogoDAO {
	
	/**
	 * Inserisce un nuovo luogo nel database
	 * @param c MainController
	 * @param mf MainFrame
	 * @param l Luogo da cui prendere i dati da inserire nel database
	 * @param pannelloImpostazioniAggiuntive Pannello contenente le informazioni uniche del tipo di luogo
	 * @return Operazione riuscita o meno
	 */
	public boolean creaLuogo(MainController c, MainFrame mf, Luogo l, JPanel pannelloImpostazioniAggiuntive) {
		
		//Prende attributi dall'oggetto
		Utente u = c.getUtente();
		String nome = l.getNome();
		String indirizzo = l.getIndirizzo();
		String telefono = l.getTelefono();
		String proprietario = l.getProprietario();
		String tipoAttivita = l.getTipoAttivita();
		String specializzazione = l.getAttributoAttivita();
		
		try {
			
			//Se anche uno dei campi non è completo
			if(nome.contentEquals("") || indirizzo.contentEquals("") || proprietario.contentEquals("") || telefono.length() < 10) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Completa tutti i campi!");
				return false; //Operazione fallita, valori non inseriti
			}
			
			//Se è un alloggio, abbiamo un altro campo da completare, il numero di stanze
			if(tipoAttivita.contentEquals("Alloggio")) {
				if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString().contentEquals("")) {
					mf.createNotificationFrame("Completa tutti i campi!");
					return false; //Operazione fallita, valori non inseriti
				}
			}
			
			//Se esiste già un luogo con quel nome e proprietario
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
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			//Se è un ristorante
			if(tipoAttivita.contentEquals("Ristorante")) {
				//Crea ristorante
				RistoranteDAO dao = new RistoranteDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaRistorante(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			//Se è un alloggio
			}else if(tipoAttivita.contentEquals("Alloggio")) {
				//Crea alloggio
				AlloggioDAO dao = new AlloggioDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaAlloggio(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
				
			//Se è un attrazione
			}else {
				//Crea attrazione
				AttrazioneDAO dao = new AttrazioneDAO();
				int ID = getIDLuogoByNomeAndPossessore(nome, proprietario);
				return dao.creaAttrazione(c, mf, nome, indirizzo, telefono, proprietario, tipoAttivita, specializzazione, pannelloImpostazioniAggiuntive, u, ID);
			
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
	
	}
	
	/**
	 * Cerca nel database un luogo con un certo nome e possessore e ne restituisce l'ID
	 * @param nome Nome del luogo di cui cercare l'ID
	 * @param proprietario Proprietario del luogo di cui cercare l'ID
	 * @return ID del luogo trovato o -1 se non trovo corrispondenze
	 */
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
				return -1; //Restituisci -1
				
			}
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return -1; //Restituisci -1
		}
		
	}
	
	/**
	 * Controlla se un luogo con quel nome e proprietario è già presente nel database
	 * @param nome Nome del luogo da cercare
	 * @param proprietario Proprietario del luogo da cercare
	 * @return Se il luogo con quel nome e proprietario esiste già (true) o meno (false)
	 */
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
	
	/**
	 * Crea lista di tutti i luoghi di un determinato utente
	 * @param nomeUtente Nome Utente per cui prendere i luoghi
	 * @return La lista di luoghi di quell'utente
	 */
	public List<Luogo> getListaLuoghiByNomeUtente(String nomeUtente) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where nomeUtente = '" + nomeUtente + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>(); //Lista di luoghi
			
			while(rs.next()) {
				
				int ID = rs.getInt("idLuogo"); //ID unico luogo
				String nome = rs.getString("nome"); //Nome luogo
				String indirizzo = rs.getString("indirizzo"); //Indirizzo luogo
				String telefono = rs.getString("telefono"); //Numero telefono luogo
				String proprietario = rs.getString("proprietario"); //Nome proprietario
				float mediaRecensioni = rs.getFloat("mediaRecensioni"); //Media recensioni
				String tipoAttivita = rs.getString("tipoAttivita"); //Tipo attività (Ristorante, Alloggio, Attrazione)
				String attributoAttivita = rs.getString("attributoAttivita"); //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
				
				//Se è un ristorante
				if(tipoAttivita.contentEquals("Ristorante")) {
					
					//Informazioni uniche ristorante
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
					luoghi.add(l); //Aggiungi ristorante alla lista
					
				//Se è un alloggio
				}else if(tipoAttivita.contentEquals("Alloggio")) {
					
					//Informazioni uniche alloggio
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
					
					luoghi.add(l); //Aggiungi alloggio alla lista
					
				//Se è un attrazionie
				}else {
					
					//Informazioni uniche attrazione
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
					
					luoghi.add(l); //Aggiungi attrazione alla lista
				}
				
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return luoghi; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Nessun luogo
		}
		
	}

	/**
	 * Cerca un luogo in base al suo ID
	 * @param ID ID del luogo da cercare
	 * @return Luogo con quell'ID
	 */
	public Luogo getLuogoByID(int ID) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where idLuogo = '" + ID + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Luogo trovato
				
				String nomeUtente = rs.getString("nomeUtente"); //Nickname utente
				String nome = rs.getString("nome"); //Nome luogo
				String indirizzo = rs.getString("indirizzo"); //Indirizzo luogo
				String telefono = rs.getString("telefono"); //Numero telefono luogo
				String proprietario = rs.getString("proprietario"); //Nome proprietario
				float mediaRecensioni = rs.getFloat("mediaRecensioni"); //Media recensioni
				String tipoAttivita = rs.getString("tipoAttivita"); //Tipo attività (Ristorante, Alloggio, Attrazione)
				String attributoAttivita = rs.getString("attributoAttivita"); //Specializzazione attività ((Pizzeria, Ristorante, Braceria) (Hotel, Motel, B&B) (Intrattenimento, Culturale))
				
				//Se è un ristorante
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
					return l; //Restituisci ristorante
					
				//Se è un alloggio
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
					return l; //Restituisci alloggio
					
				//Se è un attrazione
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
					return l; //Restituisci attrazione
				}
				
			}else { //Luogo non trovato
				
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
	 * Prendi tutti i luoghi all'interno del database
	 * @return Lista con tutti i luoghi presenti neldatabase
	 */
	public List<Luogo> getListaTuttiLuoghi(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo order by nome ASC" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>(); //Lista luoghi
			
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
				
				//Se è un ristorante
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
					
					luoghi.add(l); //Aggiungi ristorante alla lista
					
				//Se è un alloggio
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
					
					luoghi.add(l); //Aggiungi alloggio alla lista
					
				//Se è un attrazione
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
					
					luoghi.add(l); //Aggiungi attrazione alla lista
				}
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return luoghi; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Restituisci null
		}
		
	}

	/**
	 * Calcola la media di un certo luogo in base alle sue recensioni
	 * @param l Luogo di cui ottenere la media
	 * @return Media del luogo specificato
	 */
	public float calcolaMediaRecensioniLuogo(Luogo l) {
		
		RecensioneDAO dao = new RecensioneDAO();
		List<Recensione> lista = new ArrayList<Recensione>(); //Lista di tutte le recensioni di quel luogo
		lista = dao.getListaRecensioniLuogo(l);
		
		int somma = 0; //Inizializza somma a 0
		int c = 0; //Numero recensioni
		
		//Somma il campo voto di ogni recensione presente nella lista alla somma
		for(Recensione r: lista) {
			somma += r.getVoto();
			c++; //Aumenta numero recensioni
		}
		
		//Se non ho recensioni
		if(c == 0) {
			return 0; //La media è 0
			
		//Se ho almeno una recensione
		}else {
			return (float)somma/c;
		}
		
		
	}

	/**
	 * Update il valore mediaRecensioni di un certo luogo
	 * @param l Luogo di cui updatare la media
	 * @param media Media da inserire
	 * @return Operazione riuscita o meno
	 */
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
			
			return true; //Operazione modifica riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione modifica fallita, restituisce false
		}
		
	}

	/**
	 * Rimuove un luogo dal database
	 * @param controller MainController
	 * @param l Luogo da rimuovere
	 * @return Operazione riuscita o meno
	 */
	public boolean rimuoviLuogo(MainController controller, Luogo l) {
		
		try {
			
			RecensioneDAO dao = new RecensioneDAO();
			List<Recensione> lista = dao.getListaRecensioniLuogo(l); //Prendi tutte le recensioni del luogo da rimuovere
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "DELETE FROM luogo WHERE idLuogo = " + l.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
	        Statement st = con.createStatement(); //Creo statement
	        
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			//Aggiorna il numero di recensioni degli utenti che hanno lasciato una recensione sul luogo eliminato
			for(Recensione r : lista) {
				
				UtenteDAO dao2 = new UtenteDAO();
				dao2.updateNumeroRecensioni(r.getNomeUtente());
			
			}
			
			return true; //Operazione riuscita
		
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione fallita
		}
		
	}
	
	/**
	 * Modifica luogo nel database
	 * @param c MainController
	 * @param nuovoLuogo Nuovo luogo da cui prendere gli attributi da inserire nel database
	 * @param vecchioLuogo Vecchio luogo da sostituire nel database (Prendo il suo ID)
	 * @param pannelloImpostazioniAggiuntive
	 * @return
	 */
	public boolean modificaLuogo(MainController c, Luogo nuovoLuogo, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "UPDATE luogo SET nome = '" + nuovoLuogo.getNome() + "', indirizzo = '" + nuovoLuogo.getIndirizzo()  
			+ "', telefono = '" + nuovoLuogo.getTelefono() + "', proprietario = '" + nuovoLuogo.getProprietario() + "' WHERE idLuogo = " + vecchioLuogo.getID();
			
			String connectionURL = MainController.URL; //URL di connessione
	
	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q); //Eseguo la query contenuta in stringa q
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			
			//Se il luogo era un ristorante
			if(vecchioLuogo.getTipoAttivita().contentEquals("Ristorante")) {
				//Modifica ristorante
				RistoranteDAO dao = new RistoranteDAO();
				return dao.modificaRistorante(c, vecchioLuogo, pannelloImpostazioniAggiuntive);
				
			//Se il luogo era un alloggio
			}else if(vecchioLuogo.getTipoAttivita().contentEquals("Alloggio")) {
				//Modifica alloggio
				AlloggioDAO dao = new AlloggioDAO();
				return dao.modificaAlloggio(c, vecchioLuogo, pannelloImpostazioniAggiuntive);
				
			//Se il luogo era un attrazione
			}else {
				//Modifica attrazione
				AttrazioneDAO dao = new AttrazioneDAO();
				return dao.modificaAttrazione(c, vecchioLuogo, pannelloImpostazioniAggiuntive);
			
			}
		
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione fallita
		}
			
	}

	/**
	 * Crea una lista di luoghi in base ai parametri di ricerca inserita
	 * @param proprietarioRicerca Proprietario luogo
	 * @param nomeRicerca Nome luogo
	 * @param ristorante Se il luogo può essere un ristorante
	 * @param alloggio Se il luogo può essere un alloggio
	 * @param attrazione Se il luogo può essere un attrazione
	 * @param votoMinimo Voto minimo che il luogo deve avere
	 * @param ordine Tipo di ordine della lista
	 * @param invertito Se la lista è invertita o meno
	 * @return Lista luoghi che soddisfano i parametri inseriti
	 */
	public List<Luogo> faiRicerca(String proprietarioRicerca, String nomeRicerca, String ristorante, String alloggio, String attrazione, int votoMinimo, String ordine, boolean invertito){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = generaQueryRicerca(proprietarioRicerca, nomeRicerca, ristorante, alloggio, attrazione, votoMinimo, ordine, invertito); //Genera query in base ai parametri
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			List<Luogo> luoghi = new ArrayList<Luogo>(); //Lista luoghi
			
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
				
				//Se il luogo è un Ristorante
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
					
					luoghi.add(l); //Aggiungi ristorante alla lista
					
				//Se il luogo è un Alloggio
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
					
					luoghi.add(l); //Aggiungi alloggio alla lista
					
				//Se il luogo è un Attrazione
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
					
					luoghi.add(l); //Aggiungi attrazione alla lista
				}
			}
			
			con.close(); //Chiudi connessione
			st.close(); //Chiudi statement
			return luoghi; //Restituisci lista luoghi
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return null; //Restituisce null
		}
		
	}

	/**
	 * Genera la query da eseguire per la ricerca
	 * @param proprietario Proprietario luogo
	 * @param nome Nome luogo
	 * @param ristorante Se il luogo può essere un ristorante
	 * @param alloggio Se il luogo può essere un alloggio
	 * @param attrazione Se il luogo può essere un attrazione
	 * @param votoMinimo Voto minimo che il luogo deve avere
	 * @param ordine Tipo di ordine della lista
	 * @param invertito Se la lista è invertita o meno
	 * @return La stringa contenente la corretta query
	 */
	public String generaQueryRicerca(String proprietario, String nome, String ristorante, String alloggio, String attrazione, int votoMinimo, String ordine, boolean invertito) {
		
		proprietario = (!proprietario.contentEquals(""))? proprietario : null; //Se il campo proprietario è vuoto diventa null
		nome = (!nome.contentEquals(""))? nome : null; //Se il campo nome è vuoto diventa null
		
		String nomeQuery;
		if(nome != null) {
			nomeQuery = "(nome LIKE '%" + nome + "%' OR '" + nome + "' IS NULL)"; //Se nome non era vuoto
		}else {
			nomeQuery = "(nome LIKE '" + nome + "' OR " + nome + " IS NULL)";
		}
		
		String proprietarioQuery;
		if(proprietario != null) {
			proprietarioQuery = "(proprietario = '%" + proprietario + "%' OR '" + proprietario + "' IS NULL)"; //Se proprietario non era vuoto
		}else {
			proprietarioQuery = "(proprietario = '" + proprietario + "' OR " + proprietario + " IS NULL)";
		}
		
		String query = null;
		
		query = "SELECT * FROM luogo WHERE (";
		query += nomeQuery + " AND " + proprietarioQuery + " AND (mediaRecensioni >= " + votoMinimo + "))";
		
		//Combinazioni ristorante alloggio e attrazione 0 = checkbox non selezionate, 1 = checkbox selezionata
		if(ristorante == null && alloggio == null && attrazione != null) { //001
			query += "AND ((tipoAttivita = '" + attrazione + "'))";
		}else if(ristorante == null && alloggio != null && attrazione == null) { //010
			query += "AND ((tipoAttivita = '" + alloggio + "'))";
		}else if(ristorante == null && alloggio != null && attrazione != null) { //011
			query += "AND ((tipoAttivita = '" + alloggio + "') OR (tipoAttivita = '" + attrazione + "'))";
		}else if(ristorante != null && alloggio == null && attrazione == null) { //100
			query += "AND ((tipoAttivita = '" + ristorante + "'))";
		}else if(ristorante != null && alloggio == null && attrazione != null) { //101
			query += "AND ((tipoAttivita = '" + ristorante + "') OR (tipoAttivita = '" + attrazione + "'))";
		}else if(ristorante != null && alloggio != null && attrazione == null) { //110
			query += "AND ((tipoAttivita = '" + ristorante + "') OR (tipoAttivita = '" + alloggio + "'))";
		}else { //111 e 000
			query += "AND ((tipoAttivita = '" + ristorante + "') OR (tipoAttivita = '" + alloggio + "') OR (tipoAttivita = '" + attrazione + "'))";
		}
		
		//Inserisci l'ordine nella query
		switch(ordine) {
		case "Ordine alfabetico": query += " ORDER BY nome"; break;
		case "Valutazione": query += " ORDER BY mediaRecensioni"; break;
		case "Ordine creazione": query += " ORDER BY idLuogo"; break;
		}
		
		//Se la checkbox invertito è selezionata
		if(!invertito) {
			//Se l'ordine è "Valutazione"
			if(!ordine.contentEquals("Valutazione")) {
				query += " ASC";
			//Altrimenti
			}else {
				query += " DESC";
			}
		//Se la checkbox invertito non è selezionata	
		}else {
			//Se l'ordine è "Valutazione"
			if(!ordine.contentEquals("Valutazione")) {
				query += " DESC";
			//Altrimenti
			}else {
				query += " ASC";
			}
		}
		
		return query; //Restituisci la query costruita
		
	}

}