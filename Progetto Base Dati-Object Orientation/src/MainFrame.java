import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.event.MouseMotionAdapter;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;

public class MainFrame extends JFrame {

	private MainController controller; //Controller che chiama il mainFrame
	private Point mouseClickPoint; //Posizione mouse
	private JButton btnRegistrati; //Bottone registrati (per far cambiare alcune sue propriet� da altre classi)
	private JButton buttonLogin; //Bottone login (per far cambiare alcune sue propriet� da altre classi)
	private JPanel workPanel; //Pannello workPanel (Pannello dove viene mostrata l'attivit� corrente) (Per poter chiamare alcune funzioni che richiedono workPanel)
	
	public MainFrame(MainController controller) {
		
		this.controller = controller; //Collega variabile controller al controller che ha creato il mainFrame
		
		setSize(new Dimension(1000, 600)); //Dimensioni MainFrame
		getContentPane().setBackground(controller.white); //Colore background
		setUndecorated(true); //Undecorated (Senza pulsanti chiusura/minimizzazione etc)
		setResizable(false); //Non ridimensionabile
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando si chiude il MainFrame chiudi applicazione
		
		
		JPanel upperPanel = new JPanel(); //Nuovo pannello (Upper panel)
		upperPanel.setBounds(0, 0, 1000, 30); //Posiziona upper panel
		getContentPane().setLayout(null); //Imposta layout pannello principale
		//Per poter trascinare la finestra
		upperPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { //Quando trascino
		        Point newPoint = e.getLocationOnScreen(); //Posizione MainFrame sullo schermo
		        newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); //Trasla la posizione del MainFrame in base alla posizione del mouse (mouseClickPoint)
		        setLocation(newPoint); //Imposta nuova posizione
		    }
		});
		upperPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) { //Quando premo il mouse
				mouseClickPoint = e.getPoint(); //Aggiorna posizione mouse
		    }
		});
		upperPanel.setBackground(controller.steel); //Imposta colore background upper panel
		getContentPane().add(upperPanel); //Aggiungi upper panel al mainFrame
		upperPanel.setLayout(null); //Imposta layout upper panel
		
		
		JButton btnClose = new JButton(); //Nuovo bottone close
		btnClose.setBounds(960, 0, 30, 30); //Posizione e dimensioni
		//Impostazioni bottone close
		btnClose.setContentAreaFilled(false); //Lascia trasparente il bottone (mostra solo icona di esso)
		btnClose.setBorder(null); //Nessun bordo per il bottone
		btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit.png"))); //Imposta icona a "Exit"
		btnClose.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				setVisible(false); //Rendi invisibile
				dispose(); //Elimina mainFrame (e chiudi applicazione)
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit highlighted.png"))); //Imposta icona a "Exit highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit.png"))); //Imposta icona a "Exit"
			}
		});
		upperPanel.add(btnClose); //Aggiungi bottone close all'upper panel
		
		
		JButton btnMinimize = new JButton(); //Nuovo bottone minimize
		//Impostazioni bottone minimize
		btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize.png"))); //Imposta icona a "Minimize"
		btnMinimize.setContentAreaFilled(false); //Lascia trasparente il bottone (mostra solo icona di esso)
		btnMinimize.setBorder(null); //Nessun bordo per il bottone
		btnMinimize.setBounds(930, 0, 30, 30); //Posiziona
		btnMinimize.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED); //Imposta stato mainFrame a ICONIFIED (minimizza)
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize highlighted.png"))); //Imposta icona a "Minimize highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize.png"))); //Imposta icona a "Minimize"
			}
		});
		upperPanel.add(btnMinimize); //Aggiungi bottone minimize all'upper panel
		
		
		JTextPane txtpnTripadvisor = new JTextPane(); //Nuovo pannello di testo ("Titolo finestra")
		//Impostazioni testo
		txtpnTripadvisor.setFont(new Font("Tahoma", Font.BOLD, 20)); //Font
		txtpnTripadvisor.setBounds(0, 0, 128, 30); //Posizione e dimensioni
		txtpnTripadvisor.setHighlighter(null); //Non evidenziabile
		txtpnTripadvisor.setForeground(controller.electric); //Colora
		txtpnTripadvisor.setEditable(false); //Non editabile
		txtpnTripadvisor.setBackground(upperPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(upperPanel))
		txtpnTripadvisor.setText("TripAdvisor"); //Imposta testo
		upperPanel.add(txtpnTripadvisor); //Aggiungi testo TripAdvisor all'upper panel
		
		
		JPanel mainPanel = new JPanel(); //Nuovo pannello (Pannello principale in cui vi sono workPanel e dashboardPanel)
		mainPanel.setBounds(10, 41, 980, 548); //Posizione e dimensioni
		mainPanel.setBackground(getContentPane().getBackground()); //Imposta colore mainPanel (Uguale a quello dello sfondo del mainFrame)
		getContentPane().add(mainPanel); //Aggiungi mainPanel al mainFrame
		
		
		JPanel dashBoardPanel = new JPanel(); //Nuovo pannello (pannello dashboard (Alla sinistra del mainFrame))
		dashBoardPanel.setBackground(controller.prussian); //Imposta colore dashBoardPanel
		
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel); //GroupLayout per mainPanel (e salvato in gl_mainPanel)
		
		
		SpringLayout sl_dashBoardPanel = new SpringLayout(); //SpringLayout per dashBoardPanel (e salvato in sl_dashBoardPanel)
		dashBoardPanel.setLayout(sl_dashBoardPanel); //Imposta lo SpringLayout sl_dashBoardPanel per il dashBoardPanel
		
		
		JLabel labelProfilePic = new JLabel(""); //Nuovo label per l'immagine dell'utente
		labelProfilePic.setHorizontalAlignment(SwingConstants.CENTER); //Allignment
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, labelProfilePic, 10, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, labelProfilePic, 0, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, labelProfilePic, 212, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, labelProfilePic, 202, SpringLayout.WEST, dashBoardPanel);
		labelProfilePic.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/ProfilePic.png"))); //Imposta immagine per il label (ProfilePic)
		dashBoardPanel.add(labelProfilePic); //Aggiungi labelProfilePic alla dashboard (dashBoardPanel)
		
		
		JPanel panelInfoUtente = new JPanel(); //Nuovo label per info utente (se ha fatto il login)
		panelInfoUtente.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelInfoUtente, 6, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelInfoUtente, 10, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelInfoUtente, 99, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelInfoUtente, 192, SpringLayout.WEST, labelProfilePic);
		dashBoardPanel.add(panelInfoUtente); //Aggiungi panelInfoUtente alla dashboard (dashBoardPanel)
		
		
		JPanel panelControl = new JPanel(); //Nuovo pannello (sulla dashboard, dove si trovano i bottoni HOME/LOGIN etc)
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelControl, 0, SpringLayout.WEST, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelControl, 0, SpringLayout.EAST, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelControl, 19, SpringLayout.SOUTH, panelInfoUtente);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelControl, 221, SpringLayout.SOUTH, panelInfoUtente);
		panelControl.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		dashBoardPanel.add(panelControl); //Aggiungi panelControl alla dashboard (dashBoardPanel)
		SpringLayout sl_panelControl = new SpringLayout(); //SpringLayout per panelControl (e salvato in sl_panelControl)
		panelControl.setLayout(sl_panelControl); //Imposta lo SpringLayout sl_panelControl per il panelControl
		
		
		workPanel = new JPanel(); //Crea pannello di lavoro (gi� dichiarato come variabile privata fuori)
		workPanel.setBackground(Color.LIGHT_GRAY); //Imposta colore
		//Allinea orizzontale
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addComponent(dashBoardPanel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(workPanel, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
		);
		//Allinea verticale
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dashBoardPanel, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
						.addComponent(workPanel, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		workPanel.setLayout(new CardLayout(0, 0)); //Imposta layout per workPanel
		
		
		//Imposta primo pannello di lavoro (workPanel) a HomePanel
		cambiaPannelloLavoroAHomePanel(workPanel);
		
		
		JButton buttonHome = new JButton(""); //Nuovo bottone Home
		buttonHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		buttonHome.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonHome.png"))); //Imposta icona bottone a "buttonHome"
		buttonHome.setBorder(null); //Nessun bordo
		//Costraints per limitarne la posizione in base al layout
		sl_panelControl.putConstraint(SpringLayout.NORTH, buttonHome, 10, SpringLayout.NORTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, buttonHome, 0, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, buttonHome, -162, SpringLayout.SOUTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, buttonHome, 0, SpringLayout.EAST, panelControl);
		buttonHome.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				cambiaPannelloLavoroAHomePanel(workPanel); //Imposta il pannello di lavoro (workPanel) a HomePanel
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				buttonHome.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonHome Highlighted.png"))); //Imposta icona a "buttonHome Highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				buttonHome.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonHome.png"))); //Imposta icona a "buttonHome"
			}
		});
		panelControl.add(buttonHome); //Aggiungi bottone Home al pannello di controllo
		
		
		JButton btnGestisciLocali = new JButton("Gestisci locali"); //Nuovo bottone Gestisci Locali
		btnGestisciLocali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		//Costraints per limitarne la posizione in base al layout
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnGestisciLocali, 46, SpringLayout.NORTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnGestisciLocali, 0, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnGestisciLocali, 0, SpringLayout.EAST, panelControl);
		btnGestisciLocali.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				cambiaPannelloLavoroALocaliPanel(workPanel); //Imposta il pannello di lavoro (workPanel) a LocaliPanel
			}
		});
		panelControl.add(btnGestisciLocali); //Aggiungi bottone Gestisci Locali al pannello di controllo
		
		
		JButton btnGestisciRecensioni = new JButton("Gestisci recensioni"); //Nuovo bottone Gestisci Recensioni
		btnGestisciRecensioni.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		//Costraints per limitarne la posizione in base al layout
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnGestisciRecensioni, 82, SpringLayout.NORTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnGestisciRecensioni, 0, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnGestisciRecensioni, 0, SpringLayout.EAST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, btnGestisciLocali, -6, SpringLayout.NORTH, btnGestisciRecensioni);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, btnGestisciRecensioni, 112, SpringLayout.NORTH, panelControl);
		btnGestisciRecensioni.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				cambiaPannelloLavoroARecensioniPanel(workPanel); //Imposta il pannello di lavoro (workPanel) a RecensioniPanel
			}
		});
		panelControl.add(btnGestisciRecensioni); //Aggiungi bottone Gestisci Recensioni al pannello di controllo
		
		
		btnRegistrati = new JButton("Registrati"); //Nuovo bottone Registrati
		btnRegistrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		btnRegistrati.setEnabled(!controller.isLoggedIn()); //Abilita solo se IsLoggedIn del controller = 0
		btnRegistrati.setVisible(btnRegistrati.isEnabled()); //Rendi visibile solo se � abilitato
		btnRegistrati.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				cambiaPannelloLavoroARegistrazionePanel(workPanel); //Imposta il pannello di lavoro (workPanel) a RegistrazionePanel
			}
		});
		panelControl.add(btnRegistrati); //Aggiungi bottone Registrati al pannello di controllo
		
		
		buttonLogin = new JButton(); //Nuovo bottone Login (o logout)
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin.png"))); //Per anteprima del bottone nella tab design (non necessario)
		buttonLogin.setBorder(null); //Nessun bordo
		//Gestisci icona bottone in base a controller.isLoggedIn() (In realt� ad inizio programma controller.isLoggedIn() � sempre falso quindi � ridondante questo controllo)
		if(controller.isLoggedIn()) { //Utente loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogout.png")));  //Imposta icona a "buttonLogout" inizialmente
		}else { //Utente non loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin.png"))); //Imposta icona a "buttonLogin" inizialmente
		}
		//Costraints per limitarne la posizione in base al layout
		sl_panelControl.putConstraint(SpringLayout.NORTH, buttonLogin, 6, SpringLayout.SOUTH, btnGestisciRecensioni);
		sl_panelControl.putConstraint(SpringLayout.WEST, buttonLogin, 0, SpringLayout.WEST, buttonHome);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, buttonLogin, -54, SpringLayout.SOUTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, buttonLogin, 0, SpringLayout.EAST, panelControl);
		buttonLogin.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				if(!controller.isLoggedIn()) { //Se non sono loggato (sto premendo su login)
					cambiaPannelloLavoroALoginPanel(workPanel); //Vai al pannello LoginPanel
				}else { //Se sono loggato (sto premendo su logout)
					controller.setLoggedIn(false); //Imposta loggedIn del controller a falso
					controller.setUtente(null); //Imposta utente del controller a null
					refreshaLoginButton(); //Refresha il bottone (per vedere quale icona deve mostrare)
					cambiaPannelloLavoroAHomePanel(workPanel); //Vai al pannello HomePanel
				}
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				if(controller.isLoggedIn()) { //Se sono loggato
					buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogout highlighted.png"))); //Imposta icona a "buttonLogout highlighted"
				}else { //Se non sono loggato
					buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin highlighted.png"))); //Imposta icona a "buttonLogin highlighted"
				}
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				if(controller.isLoggedIn()) { //Se sono loggato
					buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogout.png"))); //Imposta icona a "buttonLogout"
				}else { //Se non sono loggato
					buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin.png"))); //Imposta icona a "buttonLogin"
				}
			}
		});
		panelControl.add(buttonLogin); //Aggiungi bottone Login (o logout) al pannello di controllo
		
		
		//Costraints per limitarne la posizione in base al layout del bottone Registrati (Va pi� sotto perch� si basa su un altra componente dichiarata dopo (buttonHome))
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnRegistrati, 6, SpringLayout.SOUTH, buttonLogin);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnRegistrati, 0, SpringLayout.WEST, buttonHome);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, btnRegistrati, 36, SpringLayout.SOUTH, buttonLogin);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnRegistrati, 0, SpringLayout.EAST, buttonHome);
		
		
		mainPanel.setLayout(gl_mainPanel); //Imposta layout gl_mainPanel (Salvato prima, riga 150) in mainPanel
		
	}
	
	/**
	 * Fa mostrare il pannello Login nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroALoginPanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class LoginPanel")) { //Se non sono gi� sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo LoginPanel al workPanel
			LoginPanel panel = new LoginPanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Home nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroAHomePanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class HomePanel")) { //Se non sono gi� sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo HomePanel al workPanel
			HomePanel panel = new HomePanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Locali nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroALocaliPanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class LocalPanel")) { //Se non sono gi� sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo LocaliPanel al workPanel
			LocaliPanel panel = new LocaliPanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Recensioni nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroARecensioniPanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class RecensioniPanel")) { //Se non sono gi� sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo RecensioniPanel al workPanel
			RecensioniPanel panel = new RecensioniPanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Registrazioni nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroARegistrazionePanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class RegistrazionePanel")) { //Se non sono gi� sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo RegistrazionePanel al workPanel
			RegistrazionePanel panel = new RegistrazionePanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}

	/**
	 * Ricarica bottone login/logout per mostrare la giusta icona (Se di login o di logout)
	 */
	public void refreshaLoginButton() {
		
		btnRegistrati.setEnabled(!controller.isLoggedIn()); //Abilita il bottone registrati se non sono loggato e lo disattiva se sono loggato
		btnRegistrati.setVisible(btnRegistrati.isEnabled()); //Rende visibile il bottone registrati solo se � abilitato
		if(controller.isLoggedIn()) { //Se sono loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogout.png"))); //Imposta icona a "buttonLogout"
		}else { //Se non sono loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin Highlighted.png"))); //Imposta icona a "buttonLogin highlighted" (Highlighted perch� ho appena premuto sul bottone per sloggare)
		}
		
	}

	//Getter per variabile workPanel (P)
	public JPanel getWorkPanel() {
		
		return this.workPanel;
		
	}
	
}