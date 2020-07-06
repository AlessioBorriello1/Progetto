import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainController {
	
	static String URL = "jdbc:mysql://bplrildi4wdwcrgrpita-mysql.services.clever-cloud.com:3306/bplrildi4wdwcrgrpita?autoReconnect=true&useSSL=false"; //URL del database
	static String PASSWORD = "pGCMuO99CWrSjweV0toj";
	static String UTENTE = "uqp9ovkdodflayr7";
	
	private boolean loggedIn = false; //Utente ha fatto il login o meno
	private JPanel currentPanel = null; //Pannello attualmente mostrato nel workPanel
	private Utente utente = null; //Puntatore all'utente che ha fatto il login (null se non sono loggato)
	
	//Palette colori
	public Color lightGrey = new Color(210, 210, 210);
	public Color sky = new Color(149, 200, 216);
	public Color skyWhiter = new Color(200, 255, 255);
	public Color electric = new Color(126, 249, 255);
	public Color steel = new Color(70, 130, 180);
	public Color pigeon = new Color(114, 133, 165);
	public Color independence = new Color(76, 81, 109);
	public Color egyptian = new Color(16, 52, 166);
	public Color prussian = new Color(0, 49, 82);
	public Color white = new Color(255, 255, 255);

	//Inizio del programma
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Crea Main frame
					MainController controller = new MainController();
					MainFrame mainFrame = new MainFrame(controller);
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	//Getter per variabile loggedIn
	public boolean isLoggedIn() {
		return loggedIn;
	}

	//Setter per variabile loggedIn
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	//Getter per variabile utente
	public Utente getUtente() {
		return utente;
	}

	//Setter per variabile utente
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	/**
	 * Bottone login nel pannello login premuto, istanzia un UtenteDAO per vedere se i valori
	 * nomeUtente/email e password appartengono a qualcuno nel database.
	 * @param nomeUtente Nome Utente o Email da cercare nel database
	 * @param password Password corrispondente a Nome Utente o Email da cercare nel database
	 * @param mainFrame Frame applicazione per aver accesso ad alcune delle sue funzioni
	 */
	public void login(String nomeUtente, String password, MainFrame mainFrame) {
		
		UtenteDAO dao = new UtenteDAO(); //Istanzia un UtenteDAO per eseguire la ricerca
		Utente u = dao.getUtente(nomeUtente, password, mainFrame); //Funzione UtenteDAO che restituisce (se lo trova) un utente
		setUtente(u); //Imposta il nuovo utente trovato (o meno) nella variabile utente del controller
		if(u != null) { //Se utente trovato
			u.setLuoghiUtente(); //Inizializza array luoghi
			u.setRecensioniUtente(); //Inizializza array recensioni
			u.setNumeroLuoghi(u.getLuoghiUtente().size());
			setLoggedIn(true); //Imposta la variabile del controller loggedIn a vero
			
			mainFrame.refreshaLoginButton(); //Ricarica il bottone di login/logout del mainFrame per fargli mostrare l'icona corretta
			mainFrame.refreshaPannelloInfo(); //Cambia pannello info utente se sono loggato o meno
			mainFrame.createNotificationFrame("Login riuscito! Benvenuto " + getUtente().getNomeUtente());
			mainFrame.cambiaPannelloLavoroAHomePanel((JPanel)getComponentByName(mainFrame, "workPanel")); //Vai a pannello HomePanel
		}
		
	}
	
	/**
	 * Bottone registrazione nel pannello registrazione premuto, istanza un UtenteDAO per vedere se i valori
	 * nomeUtente, email non appartengono a nessuno per poter completare la registrazione
	 * @param nomeUtente Nome Utente del nuovo utente da creare
	 * @param password Password del nuovo utente da creare
	 * @param email Email del nuovo utente da creare
	 * @param mainFrame Frame applicazione per aver accesso ad alcune delle sue funzioni
	 * @return Boolean se l'operazione è riuscita o meno
	 */
	public boolean registrazione(String nomeUtente, String password, String email, MainFrame mainFrame) {
		
		UtenteDAO dao = new UtenteDAO(); //Istanzia un UtenteDAO per eseguire la ricerca
		if(dao.registraUtente(nomeUtente, email, password, mainFrame)) { //Funzione UtenteDAO che restituisce vero se la creazione è avvenuta con successo
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * Crea un nuovo luogo nel database
	 * @param mainFrame MainFrame
	 * @param l Luogo da cui prendere le informazioni da inserire nel database
	 * @param pannelloImpostazioniAggiuntive Pannello contenente le informazioni uniche in base al tipo di luogo
	 * @return Operazione riuscita o meno
	 */
	public boolean creaLuogo(MainFrame mainFrame, Luogo l, JPanel pannelloImpostazioniAggiuntive) {
		
		LuogoDAO dao = new LuogoDAO();
		System.out.println(l.getAttributoAttivita());
		if(dao.creaLuogo(this, mainFrame, l, pannelloImpostazioniAggiuntive)) {
			//Se sono riuscito a creare il luogo
			System.out.println("Operazione riuscita");
			getUtente().setNumeroLuoghi(getUtente().getNumeroLuoghi() + 1); //Aumenta numero dei luoghi di quell'utente
			mainFrame.refreshaPannelloInfo(); //Refresha pannello
			int ID = dao.getIDLuogoByNomeAndPossessore(l.getNome(), l.getProprietario());
			getUtente().getLuoghiUtente().add(dao.getLuogoByID(ID)); //Aggiungi il luogo appena creato alla lista dei lughi dell'utente
			mainFrame.createNotificationFrame("Luogo aggiunto correttamente!");
			mainFrame.cambiaPannelloLavoroALocaliPanel((JPanel)getComponentByName(mainFrame, "workPanel")); //Vai al pannello locali Panel
			return true; //Operazione riuscita
		}else {
			System.out.println("Operazione fallita");
			return false; //Operazione fallita
		}
			
	}

	/**
	 * Modifica un luogo
	 * @param mainFrame MainFrame
	 * @param nuovoLuogo Luogo con le nuove informazioni
	 * @param vecchioLuogo Luogo da modificare
	 * @param pannelloImpostazioniAggiuntive Pannello contenente le informazioni uniche in base al tipo di luogo
	 * @return Operazione riuscita o meno
	 */
	public boolean modificaLuogo(MainFrame mainFrame, Luogo nuovoLuogo, Luogo vecchioLuogo, JPanel pannelloImpostazioniAggiuntive){
		
		LuogoDAO dao = new LuogoDAO();
		if(dao.modificaLuogo(this, nuovoLuogo, vecchioLuogo, pannelloImpostazioniAggiuntive)) {
			//Modifica riuscita
			utente.getLuoghiUtente().removeAll(utente.getLuoghiUtente()); //Svuota lista dei luoghi dell'utente
			utente.setLuoghiUtente(); //Popola lista dei luoghi utente
			mainFrame.createNotificationFrame("Modifica completata!");
			mainFrame.cambiaPannelloLavoroALocaliPanel((JPanel)getComponentByName(mainFrame, "workPanel")); //Vai al pannello locali Panel
			return true; //Operazione riuscita
		}else {
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione fallita
		}
		
	}

	/**
	 * Rimuovi un luogo
	 * @param mainFrame MainFrame
	 * @param l Luogo da eliminare
	 * @return Operazione riuscita o meno
	 */
	public boolean rimuoviLuogo(MainFrame mainFrame, Luogo l) {
		
		LuogoDAO dao = new LuogoDAO();
		if(dao.rimuoviLuogo(this, l)) {
			//Modifica riuscita
			utente.setNumeroLuoghi(utente.getNumeroLuoghi()-1); //Imposta il nuovo numero di luoghi
			UtenteDAO dao2 = new UtenteDAO();
			dao2.updateNumeroLuoghi(utente.getNomeUtente()); //Aggiorna numero luoghi nel database
			mainFrame.refreshaPannelloInfo();
			mainFrame.createNotificationFrame("Eliminazione completata");
			
			utente.getLuoghiUtente().removeAll(utente.getLuoghiUtente()); //Svuota lista dei luoghi dell'utente
			utente.setRecensioniUtente(); //Popola lista dei luoghi utente
			
			mainFrame.cambiaPannelloLavoroAHomePanel((JPanel)getComponentByName(mainFrame, "workPanel"));
			return true; //Operazione riuscita
		}else {
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false; //Operazione fallita
		}
	}

	/**
	 * Lascia una recensione per un determinato luogo e la aggiunge al database
	 * @param mainFrame MainFrame
	 * @param l Luogo della recensione
	 * @param r Recensione
	 * @return Operazione riuscita o meno
	 */
	public boolean lasciaRecensione(MainFrame mainFrame, Luogo l, Recensione r) {
		
		//Se il testo della recensione non è vuoto
		if(!r.getTesto().contentEquals("")) {
			RecensioneDAO dao = new RecensioneDAO();
			if(dao.lasciaRecensioneALuogo(mainFrame, utente, l, r)) {
				//Recensione lasciata con successo
				utente.setNumeroRecensioni(getUtente().getNumeroRecensioni() + 1); //Aumenta numero delle recensioni di quell'utente
				utente.getRecensioniUtente().add(dao.getRecensioneLuogoByNomeUtente(l, utente.getNomeUtente())); //Aggiungi recensione alla lista delle recensioni dell'utente
				mainFrame.refreshaPannelloInfo(); //Refresha pannello
				return true; //Operazione riuscita
			}else {
				System.out.println("Operazione fallita");
				return false; //Operazione fallita
			}
		}else {
			System.out.println("Operazione fallita");
			mainFrame.createNotificationFrame("Inserire il testo della recensione!");
			return false; //Operazione fallita
		}
	}
	
	/**
	 * Modifica una recensione
	 * @param mainFrame MainFrame
	 * @param l Luogo della recensione da modificare
	 * @param nuovaRecensione Recensione nuova
	 * @param vecchiaRecensione Recensione vecchia, da sostituire
	 * @return Operazione riuscita o meno
	 */
	public boolean modificaRecensione(MainFrame mainFrame, Luogo l, Recensione nuovaRecensione, Recensione vecchiaRecensione) {
		
		//Se il testo della recensione non è vuoto
		if(!nuovaRecensione.getTesto().contentEquals("")) {
			RecensioneDAO dao = new RecensioneDAO();
			return dao.modificaRecensioneLuogo(mainFrame, utente, l, nuovaRecensione.getVoto(), nuovaRecensione.getTesto(), vecchiaRecensione); //Modifica la recensione nel database
		}else {
			System.out.println("Operazione fallita");
			mainFrame.createNotificationFrame("Inserire il testo della recensione!");
			return false; //Operazione fallita
		}
			
	}

	/**
	 * Rimuovi una recensione
	 * @param mainFrame MainFrame
	 * @param l Luogo della recensione da eliminare
	 * @param r Recensione da eliminare
	 * @return Operazione riuscita o meno
	 */
	public boolean rimuoviRecensione(MainFrame mainFrame, Luogo l, Recensione r) {
		
		RecensioneDAO dao = new RecensioneDAO();
		if(dao.rimuoviRecensioneLuogo(this, l, r)) {
			//Se ho rimosso la recensione con successo
			utente.setNumeroRecensioni(utente.getNumeroRecensioni() - 1); //Aggiorna numero recensioni
			UtenteDAO dao2 = new UtenteDAO();
			dao2.updateNumeroRecensioni(utente.getNomeUtente()); //Aggiorna numero recensioni del database
			
			utente.getRecensioniUtente().removeAll(utente.getRecensioniUtente()); //Svuota lista recensioni utente
			RecensioneDAO dao3 = new RecensioneDAO();
			utente.setRecensioniUtente(dao3.getListaRecensioniByNomeUtente(utente.getNomeUtente())); //Popola le recensioni dell'utente
			mainFrame.refreshaPannelloInfo(); //Refresha pannello
			mainFrame.createNotificationFrame("Eliminazione compleatata");
			mainFrame.cambiaPannelloLavoroAHomePanel((JPanel)getComponentByName(mainFrame, "workPanel")); //Vai al pannello home
			return true; //Operazione riuscita
		}else {
			mainFrame.createNotificationFrame("Qualcosa è andato storto");
			return false; //Operazione fallita
		}
	}
	
	//Getter per variabile currentPanel
	public JPanel getCurrentPanel() {
		return currentPanel;
	}
	
	//Setter per variabile currentPanel
	public void setCurrentPanel(JPanel currentPanel) {
		this.currentPanel = currentPanel;
	}

	/**
	 * Cerca una component nel container specificato, se la trova la restituisce
	 * @param container Container in cui cercare la component
	 * @param nome Nome della component da cercare
	 * @return Component cercata o null
	 */
	public Component getComponentByName(Container container, String nome) {
		
		List<Component> lista = getAllComponents(container); //Prendi tutte le componenti del container
		
		for(Component c: lista) { //Scorrile tutte
			if(c.getName() != null && c.getName().contentEquals(nome)) { //Componente trovata
				return c; //Restituiscila
			}
		}
		
		return null;
		
	}

	/**
	 * Crea una lista con tutte le componenti presenti nel container specificato
	 * @param container Container di cui vogliamo sapere le components
	 * @return lista di tutte le components presenti nel container specificato
	 */
	public List<Component> getAllComponents(Container container) {
		
	    Component[] components = container.getComponents(); //Prendi tutte le componenti del contenitore più esterno
	    List<Component> listaComponents = new ArrayList<Component>(); //Crea lista che contiene il tipo Component
	    for (Component c: components) { //Per tutte le componenti in components
	    	listaComponents.add(c); //Aggiungi la component alla lista
	    	//System.out.println(c.getName()); //Stampa nome (per debug)
	        if (c instanceof Container) //Se la component è a sua volta un container, allora ricorsivamente
	        	listaComponents.addAll(getAllComponents((Container) c)); //Aggiungi alla lista tutti gli elementi aggiunti dalla chiamata ricorsiva
	    }
	    return listaComponents; //Restituisci lista
	    
	}

	
}