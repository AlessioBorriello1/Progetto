import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
	
	private boolean loggedIn = false; //Utente ha fatto il login o meno
	private JPanel currentPanel = null; //Pannello attualmente mostrato nel workPanel
	private Utente utente = null; //Puntatore all'utente che ha fatto il login (null se non sono loggato)
	
	//Palette colori
	public Color turquoise = new Color(63, 224, 208);
	public Color powder = new Color(196, 243, 249);
	public Color sky = new Color(149, 200, 216);
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
	public void loginButtonOnLoginPanelPressed(String nomeUtente, String password, MainFrame mainFrame) {
		
		UtenteDAO dao = new UtenteDAO(); //Istanzia un UtenteDAO per eseguire la ricerca
		Utente u = dao.getUtente(nomeUtente, password); //Funzione UtenteDAO che restituisce (se lo trova) un utente
		setUtente(u); //Imposta il nuovo utente trovato (o meno) nella variabile utente del controller
		
		if(u != null) { //Se utente trovato
			
			u.stampaInfo(); //Stampa info utente trovato (Per debug)
			setLoggedIn(true); //Imposta la variabile del controller loggedIn a vero
			mainFrame.refreshaLoginButton(); //Ricarica il bottone di login/logout del mainFrame per fargli mostrare l'icona corretta
			mainFrame.cambiaPannelloLavoroAHomePanel(mainFrame.getWorkPanel()); //Vai a pannello HomePanel
			
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
	public boolean registrazioneButtonOnRegistrazionePanelPressed(String nomeUtente, String password, String email, MainFrame mainFrame) {
		
		UtenteDAO dao = new UtenteDAO(); //Istanzia un UtenteDAO per eseguire la ricerca
		if(dao.registraUtente(nomeUtente, email, password)) { //Funzione UtenteDAO che restituisce vero se la creazione è avvenuta con successo
			return true;
		}else {
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

}