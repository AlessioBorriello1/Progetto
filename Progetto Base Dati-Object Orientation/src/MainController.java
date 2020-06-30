import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
	
	static String URL = "jdbc:mysql://localhost:3306/progetto?autoReconnect=true&useSSL=false"; //URL del database
	
	private boolean loggedIn = false; //Utente ha fatto il login o meno
	private JPanel currentPanel = null; //Pannello attualmente mostrato nel workPanel
	private Utente utente = null; //Puntatore all'utente che ha fatto il login (null se non sono loggato)
	
	//Palette colori
	public Color lightGrey = new Color(210, 210, 210);
	public Color turquoise = new Color(63, 224, 208);
	public Color powder = new Color(196, 243, 249);
	public Color sky = new Color(149, 200, 216);
	public Color skyWhiter = new Color(200, 255, 255);
	public Color electric = new Color(126, 249, 255);
	public Color airForce = new Color(88, 139, 174);
	public Color babyBlue = new Color(137, 207, 240);
	public Color tiffany = new Color(129, 216, 208);
	public Color steel = new Color(70, 130, 180);
	public Color carolina = new Color(87, 160, 211);
	public Color trukishBlue = new Color(79, 151, 163);
	public Color pigeon = new Color(114, 133, 165);
	public Color maya = new Color(115, 194, 251);
	public Color teal = new Color(0, 128, 129);
	public Color independence = new Color(76, 81, 109);
	public Color cornflower = new Color(101, 147, 245);
	public Color olympic = new Color(0, 142, 204);
	public Color sapphire = new Color(15, 82, 186);
	public Color azure = new Color(0, 128, 255);
	public Color egyptian = new Color(16, 52, 166);
	public Color yale = new Color(14, 77, 146);
	public Color navy = new Color(0, 0, 128);
	public Color prussian = new Color(0, 49, 82);
	public Color space = new Color(29, 41, 81);
	public Color royale = new Color(17, 30, 108);
	public Color white = new Color(255, 255, 255);

	public static void main(String[] args) {
		
		//Crea Main frame
		MainController controller = new MainController();
		MainFrame mainFrame = new MainFrame(controller);
		mainFrame.setVisible(true);
	
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

	public boolean creaLuogo(MainFrame mainFrame, Luogo l, JPanel pannelloImpostazioniAggiuntive) {
		
		LuogoDAO dao = new LuogoDAO();
		System.out.println(l.getAttributoAttivita());
		if(dao.creaLuogo(this, mainFrame, l.getNome(), l.getIndirizzo(), l.getTelefono(), l.getProprietario(), l.getTipoAttivita(), l.getAttributoAttivita(), pannelloImpostazioniAggiuntive, getUtente())) {
			System.out.println("Operazione riuscita");
			getUtente().setNumeroLuoghi(getUtente().getNumeroLuoghi() + 1); //Aumenta numero dei luoghi di quell'utente
			mainFrame.refreshaPannelloInfo(); //Refresha pannello
			int ID = dao.getIDLuogoByNomeAndPossessore(l.getNome(), l.getProprietario());
			getUtente().getLuoghiUtente().add(dao.getLuogoByID(ID));
			mainFrame.createNotificationFrame("Luogo aggiunto correttamente!");
			mainFrame.cambiaPannelloLavoroALocaliPanel((JPanel)getComponentByName(mainFrame, "workPanel"));
			return true;
		}else {
			System.out.println("Operazione fallita");
			return false;
		}
			
	}

	public boolean rimuoviLuogo(MainFrame mainFrame, MainController controller, JPanel workPanel, Luogo l) {
		
		LuogoDAO dao = new LuogoDAO();
		if(dao.rimuoviLuogo(this, l, utente)) {
			
			utente.setNumeroLuoghi(utente.getNumeroLuoghi()-1);
			UtenteDAO dao2 = new UtenteDAO();
			dao2.updateNumeroLuoghi(utente.getNomeUtente());
			mainFrame.refreshaPannelloInfo();
			mainFrame.createNotificationFrame("Eliminazione completata");
			
			utente.getLuoghiUtente().removeAll(utente.getLuoghiUtente());
			utente.setRecensioniUtente();
			
			mainFrame.cambiaPannelloLavoroAHomePanel(workPanel);
			return true;
		}else {
			mainFrame.createNotificationFrame("Qualcosa è andato storto!");
			return false;
		}
	}

	public boolean lasciaRecensione(MainFrame mainFrame, Luogo l, Recensione r) {
		
		if(!r.getTesto().contentEquals("")) {
			RecensioneDAO dao = new RecensioneDAO();
			if(dao.lasciaRecensioneALuogo(mainFrame, utente, l, r)) {
				utente.setNumeroRecensioni(getUtente().getNumeroRecensioni() + 1); //Aumenta numero delle recensioni di quell'utente
				utente.getRecensioniUtente().add(dao.getRecensioneLuogoByNomeUtente(l, utente.getNomeUtente()));
				mainFrame.refreshaPannelloInfo(); //Refresha pannello
				return true;
			}else {
				System.out.println("Operazione fallita");
				return false;
			}
		}else {
			System.out.println("Operazione fallita");
			mainFrame.createNotificationFrame("Inserire il testo della recensione!");
			return false;
		}
	}
	
	public boolean modificaRecensione(MainFrame mainFrame, Luogo l, Recensione nuovaRecensione, Recensione vecchiaRecensione) {
		if(!nuovaRecensione.getTesto().contentEquals("")) {
			RecensioneDAO dao = new RecensioneDAO();
			return dao.modificaRecensioneLuogo(mainFrame, utente, l, nuovaRecensione.getVoto(), nuovaRecensione.getTesto(), vecchiaRecensione);
		}else {
			System.out.println("Operazione fallita");
			mainFrame.createNotificationFrame("Inserire il testo della recensione!");
			return false;
		}
			
	}

	public boolean rimuoviRecensione(MainFrame mainFrame, JPanel workPanel, Luogo l, Recensione r) {
		
		RecensioneDAO dao = new RecensioneDAO();
		if(dao.rimuoviRecensioneLuogo(this, l, r)) {
			utente.setNumeroRecensioni(utente.getNumeroRecensioni() - 1);
			UtenteDAO dao2 = new UtenteDAO();
			dao2.updateNumeroRecensioni(utente.getNomeUtente());
			
			utente.getRecensioniUtente().removeAll(utente.getRecensioniUtente());
			RecensioneDAO dao3 = new RecensioneDAO();
			utente.setRecensioniUtente(dao3.getListaRecensioniByNomeUtente(utente.getNomeUtente()));
			mainFrame.refreshaPannelloInfo(); //Refresha pannello
			mainFrame.createNotificationFrame("Eliminazione compleatata");
			mainFrame.cambiaPannelloLavoroAHomePanel(workPanel);
			return true;
		}else {
			mainFrame.createNotificationFrame("Qualcosa è andato storto");
			return false;
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