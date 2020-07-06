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
import javax.swing.JDialog;

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
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame {

	private MainController controller; //Controller che chiama il mainFrame
	private Point mouseClickPoint; //Posizione mouse (per trascinare finestra)
	
	/**
	 * Frame principale in cui mostrare tutti i vari pannelli
	 * @param controller MainController da legare al frame
	 */
	public MainFrame(MainController controller) {
		
		this.controller = controller; //Collega variabile controller al controller che ha creato il mainFrame
		
		setSize(new Dimension(1000, 600)); //Dimensioni MainFrame
		getContentPane().setBackground(controller.white); //Colore background
		setUndecorated(true); //Undecorated (Senza pulsanti chiusura/minimizzazione etc)
		setResizable(false); //Non ridimensionabile
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando si chiude il MainFrame chiudi applicazione
		setName("mainFrame");
		
		
		JPanel upperPanel = new JPanel(); //Nuovo pannello (Upper panel)
		upperPanel.setName("upperPanel"); //Imposta nome
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
		getContentPane().add(upperPanel);
		
		
		JButton btnClose = new JButton(); //Nuovo bottone close
		btnClose.setName("btnClose");
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
		
		
		JButton btnMinimize = new JButton(); //Nuovo bottone minimize
		btnMinimize.setName("btnMinimize"); //Imposta nome
		//Impostazioni bottone minimize
		btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize.png"))); //Imposta icona a "Minimize"
		btnMinimize.setContentAreaFilled(false); //Lascia trasparente il bottone (mostra solo icona di esso)
		btnMinimize.setBorder(null);
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
		
		JLabel lblBlueGuide = new JLabel("Blue Guide");
		lblBlueGuide.setIcon(null);
		lblBlueGuide.setForeground(controller.electric);
		lblBlueGuide.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
		GroupLayout gl_upperPanel = new GroupLayout(upperPanel);
		gl_upperPanel.setHorizontalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBlueGuide, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(745)
					.addComponent(btnMinimize, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_upperPanel.setVerticalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addGroup(gl_upperPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMinimize, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBlueGuide))
					.addContainerGap())
		);
		upperPanel.setLayout(gl_upperPanel);
		
		
		JPanel mainPanel = new JPanel(); //Nuovo pannello (Pannello principale in cui vi sono workPanel e dashboardPanel)
		mainPanel.setName("mainPanel"); //Imposta nome
		mainPanel.setBounds(10, 41, 980, 548); //Posizione e dimensioni
		mainPanel.setBackground(getContentPane().getBackground()); //Imposta colore mainPanel (Uguale a quello dello sfondo del mainFrame)
		getContentPane().add(mainPanel); //Aggiungi mainPanel al mainFrame
		
		
		JPanel dashBoardPanel = new JPanel(); //Nuovo pannello (pannello dashboard (Alla sinistra del mainFrame))
		dashBoardPanel.setName("dashBoardPanel"); //Imposta nome
		dashBoardPanel.setBackground(controller.prussian); //Imposta colore dashBoardPanel
		
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel); //GroupLayout per mainPanel (e salvato in gl_mainPanel)
		
		
		SpringLayout sl_dashBoardPanel = new SpringLayout(); //SpringLayout per dashBoardPanel (e salvato in sl_dashBoardPanel)
		dashBoardPanel.setLayout(sl_dashBoardPanel); //Imposta lo SpringLayout sl_dashBoardPanel per il dashBoardPanel
		
		
		JLabel labelProfilePic = new JLabel(""); //Nuovo label per l'immagine dell'utente
		labelProfilePic.setName("labelProfilePic"); //Imposta nome
		labelProfilePic.setHorizontalAlignment(SwingConstants.CENTER); //Allignment
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, labelProfilePic, 10, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, labelProfilePic, 0, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, labelProfilePic, 212, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, labelProfilePic, 202, SpringLayout.WEST, dashBoardPanel);
		labelProfilePic.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/ProfilePic.png"))); //Imposta immagine per il label (ProfilePic)
		dashBoardPanel.add(labelProfilePic); //Aggiungi labelProfilePic alla dashboard (dashBoardPanel)
		
		
		JPanel panelInfoUtente = new JPanel(); //Nuovo label per info utente (se ha fatto il login)
		panelInfoUtente.setName("panelInfoUtente"); //Imposta nome
		panelInfoUtente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelInfoUtente.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelInfoUtente, 6, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelInfoUtente, 10, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelInfoUtente, 99, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelInfoUtente, 192, SpringLayout.WEST, labelProfilePic);
		panelInfoUtente.setLayout(new CardLayout(0, 0)); //Imposta layout
		dashBoardPanel.add(panelInfoUtente); //Aggiungi panelInfoUtente alla dashboard (dashBoardPanel)
		
		JPanel panelNotLogged = new JPanel(); //Nuovo pannello notLogged
		panelNotLogged.setName("panelNotLogged"); //Imposta nome
		panelNotLogged.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		panelInfoUtente.add(panelNotLogged, "name_254773764256500"); //Aggiungi pannello al pannello panelInfoUtente
		
		JLabel lblNotLogged = new JLabel("Effettua il login"); //Nuovo label notLogged
		lblNotLogged.setName("lblNotLogged"); //Imposta nome
		lblNotLogged.setFont(new Font("Georgia", Font.BOLD, 18)); //Imposta font
		lblNotLogged.setBackground(panelInfoUtente.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(panelInfoUtente))
		lblNotLogged.setForeground(controller.steel); //Colore font
		lblNotLogged.setHorizontalAlignment(SwingConstants.CENTER); //Allinea il testo al centro
		GroupLayout gl_panelNotLogged = new GroupLayout(panelNotLogged); //GroupLayout per panelNotLogged (e salvato in gl_panelNotLogged)
		//Allinea orizzontale
		gl_panelNotLogged.setHorizontalGroup(
			gl_panelNotLogged.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotLogged.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNotLogged, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addContainerGap())
		);
		//Allinea verticale
		gl_panelNotLogged.setVerticalGroup(
			gl_panelNotLogged.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotLogged.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNotLogged, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelNotLogged.setLayout(gl_panelNotLogged); //Imposta layout gl_panelNotLogged in panelNotLogged
		
		JPanel panelLogged = new JPanel(); //Nuovo pannello logged
		panelLogged.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelLogged.setName("panelLogged"); //Imposta nome
		panelInfoUtente.add(panelLogged, "name_254788425247100"); //Aggiungi pannello al pannello panelInfoUtente
		panelLogged.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		
		JLabel lblNomeUtente = new JLabel("Nome:"); //Nuovo label Nome utente
		lblNomeUtente.setName("lblNomeUtente"); //Imposta nome
		lblNomeUtente.setHorizontalAlignment(SwingConstants.LEADING);
		lblNomeUtente.setFont(new Font("Tahoma", Font.BOLD, 11)); //Imposta font
		lblNomeUtente.setForeground(controller.steel); //Imposta colore font
		
		JLabel lblEmail = new JLabel("Email:"); //Nuovo label Email
		lblEmail.setName("lblEmail"); //Imposta nome
		lblEmail.setHorizontalAlignment(SwingConstants.LEADING);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11)); //Imposta font
		lblEmail.setForeground(controller.steel); //Imposta colore font
		
		JLabel lblNumeroLuoghi = new JLabel("Numero luoghi:"); //Nuovo label Numero luoghi
		lblNumeroLuoghi.setName("lblNumeroLuoghi"); //Imposta nome
		lblNumeroLuoghi.setHorizontalAlignment(SwingConstants.LEADING);
		lblNumeroLuoghi.setFont(new Font("Tahoma", Font.BOLD, 11)); //Imposta font
		lblNumeroLuoghi.setForeground(controller.steel);
		
		JLabel lblNumeroRecensioni = new JLabel("Numero recensioni:");
		lblNumeroRecensioni.setName("lblNumeroRecensioni");
		lblNumeroRecensioni.setHorizontalAlignment(SwingConstants.LEADING);
		lblNumeroRecensioni.setForeground(controller.steel);
		lblNumeroRecensioni.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panelLogged = new GroupLayout(panelLogged);
		gl_panelLogged.setHorizontalGroup(
			gl_panelLogged.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLogged.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelLogged.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNomeUtente, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroLuoghi, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroRecensioni, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelLogged.setVerticalGroup(
			gl_panelLogged.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLogged.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNomeUtente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNumeroLuoghi)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNumeroRecensioni)
					.addContainerGap())
		);
		panelLogged.setLayout(gl_panelLogged); //Imposta layout gl_panelLogged in panelLogged
		
		
		JPanel panelControl = new JPanel(); //Nuovo pannello (sulla dashboard, dove si trovano i bottoni HOME/LOGIN etc)
		panelControl.setName("panelControl"); //Imposta nome
		//Costraints per limitarne la posizione in base al layout
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelControl, 0, SpringLayout.WEST, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelControl, 0, SpringLayout.EAST, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelControl, 19, SpringLayout.SOUTH, panelInfoUtente);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelControl, 221, SpringLayout.SOUTH, panelInfoUtente);
		
		panelControl.setBackground(dashBoardPanel.getBackground()); //Colore background (Uguale a quello del pannello dove si trova(dashBoardPanel))
		dashBoardPanel.add(panelControl); //Aggiungi panelControl alla dashboard (dashBoardPanel)
		SpringLayout sl_panelControl = new SpringLayout(); //SpringLayout per panelControl (e salvato in sl_panelControl)
		panelControl.setLayout(sl_panelControl); //Imposta lo SpringLayout sl_panelControl per il panelControl
		
		
		JPanel workPanel = new JPanel(); //Crea pannello di lavoro (già dichiarato come variabile privata fuori)
		workPanel.setName("workPanel"); //Imposta nome
		workPanel.setBackground(controller.pigeon); //Imposta colore
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
		buttonHome.setName("buttonHome"); //Imposta nome
		buttonHome.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonHome.png")));
		buttonHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
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
		
		
		JButton btnGestisciLocali = new JButton(""); //Nuovo bottone Gestisci Locali
		btnGestisciLocali.setBorder(null);
		btnGestisciLocali.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLocali.png")));
		btnGestisciLocali.setName("btnGestisciLocali"); //Imposta nome
		btnGestisciLocali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		//Costraints per limitarne la posizione in base al layout
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnGestisciLocali, 46, SpringLayout.NORTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnGestisciLocali, 0, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnGestisciLocali, 0, SpringLayout.EAST, panelControl);
		btnGestisciLocali.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				if(controller.isLoggedIn()) { //Se sono loggato
					cambiaPannelloLavoroALocaliPanel(workPanel); //Imposta il pannello di lavoro (workPanel) a LocaliPanel
				}else { //Se non sono loggato
					createNotificationFrame("Devi prima eseguire il Login!");
					cambiaPannelloLavoroALoginPanel(workPanel);
				}
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				btnGestisciLocali.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLocali Highlighted.png"))); //Imposta icona a "buttonHome Highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				btnGestisciLocali.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLocali.png"))); //Imposta icona a "buttonHome"
			}
		});
		panelControl.add(btnGestisciLocali); //Aggiungi bottone Gestisci Locali al pannello di controllo
		
		
		JButton btnGestisciRecensioni = new JButton(""); //Nuovo bottone Gestisci Recensioni
		btnGestisciRecensioni.setBorder(null);
		btnGestisciRecensioni.setName("btnGestisciRecensioni"); //Imposta nome
		btnGestisciRecensioni.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRecensioni.png"))); 
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
				if(controller.isLoggedIn()) { //Se sono loggato
					cambiaPannelloLavoroARecensioniPanel(workPanel); //Imposta il pannello di lavoro (workPanel) a RecensioniPanel
				}else { //Se non sono loggato
					createNotificationFrame("Devi prima eseguire il Login!");
					cambiaPannelloLavoroALoginPanel(workPanel);
				}
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				btnGestisciRecensioni.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRecensioni Highlighted.png"))); //Imposta icona a "buttonHome Highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				btnGestisciRecensioni.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRecensioni.png"))); //Imposta icona a "buttonHome"
			}
		});
		panelControl.add(btnGestisciRecensioni); //Aggiungi bottone Gestisci Recensioni al pannello di controllo
		
		
		JButton btnRegistrati = new JButton(""); //Nuovo bottone Registrati
		btnRegistrati.setBorder(null);
		btnRegistrati.setName("btnRegistrati"); //Imposta nome
		btnRegistrati.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRegistrazione.png")));
		btnRegistrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		btnRegistrati.setEnabled(!controller.isLoggedIn()); //Abilita solo se IsLoggedIn del controller = 0
		btnRegistrati.setVisible(btnRegistrati.isEnabled()); //Rendi visibile solo se è abilitato
		btnRegistrati.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse
			public void mouseClicked(MouseEvent e) {
				cambiaPannelloLavoroARegistrazionePanel(workPanel); //Imposta il pannello di lavoro (workPanel) a RegistrazionePanel
			}
			//Quando il mouse passa sul bottone
			public void mouseEntered(MouseEvent e) {
				btnRegistrati.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRegistrazione Highlighted.png"))); //Imposta icona a "buttonHome Highlighted"
			}
			//Quando il mouse esce dal bottone
			public void mouseExited(MouseEvent e) {
				btnRegistrati.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonRegistrazione.png"))); //Imposta icona a "buttonHome"
			}
		});
		panelControl.add(btnRegistrati); //Aggiungi bottone Registrati al pannello di controllo
		
		
		JButton buttonLogin = new JButton(); //Nuovo bottone Login (o logout)
		sl_panelControl.putConstraint(SpringLayout.SOUTH, btnRegistrati, 36, SpringLayout.SOUTH, buttonLogin);
		buttonLogin.setName("buttonLogin"); //Imposta nome
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Quando il mouse passa sopra rendi l'icona del mouse quella della mano
		buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin.png"))); //Per anteprima del bottone nella tab design (non necessario)
		buttonLogin.setBorder(null); //Nessun bordo
		//Gestisci icona bottone in base a controller.isLoggedIn() (In realtà ad inizio programma controller.isLoggedIn() è sempre falso quindi è ridondante questo controllo)
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
					if(createConfirmationFrame("Sei sicuro di voler uscire?")) {
						controller.setLoggedIn(false); //Imposta loggedIn del controller a falso
						controller.setUtente(null); //Imposta utente del controller a null
						refreshaLoginButton(); //Refresha il bottone (per vedere quale icona deve mostrare)
						refreshaPannelloInfo(); //Cambia pannello info utente se sono loggato o meno
						createNotificationFrame("Non sei più collegato al tuo account"); //Notifica che l'utente ha sloggato
						cambiaPannelloLavoroAHomePanel(workPanel); //Vai al pannello HomePanel
					}
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
		
		
		//Costraints per limitarne la posizione in base al layout del bottone Registrati (Va più sotto perchè si basa su un altra componente dichiarata dopo (buttonHome))
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnRegistrati, 6, SpringLayout.SOUTH, buttonLogin);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnRegistrati, 0, SpringLayout.WEST, buttonHome);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnRegistrati, 0, SpringLayout.EAST, buttonHome);
			
		mainPanel.setLayout(gl_mainPanel); //Imposta layout gl_mainPanel (Salvato prima, riga 150) in mainPanel
	}
	
	/**
	 * Fa mostrare il pannello Login nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroALoginPanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class LoginPanel")) { //Se non sono già sullo stesso panel
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
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class HomePanel")) { //Se non sono già sullo stesso panel
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
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class LocalPanel")) { //Se non sono già sullo stesso panel
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
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class RecensioniPanel")) { //Se non sono già sullo stesso panel
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
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class RegistrazionePanel")) { //Se non sono già sullo stesso panel
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
	 * Fa mostrare il pannello Creazione Luogo nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 */
	public void cambiaPannelloLavoroACreazioneLuogoPanel(JPanel workPanel) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class CreazioneLuogoPanel")) { //Se non sono già sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo CreazioneLuogoPanel al workPanel
			CreazioneLuogoPanel panel = new CreazioneLuogoPanel(controller, this, workPanel);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Modifica Luogo nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 * @param l Luogo da modificare per riempire i campi con i suoi attribuiti attuali
	 */
	public void cambiaPannelloLavoroAPanelModificaLuogo(JPanel workPanel, Luogo l) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class CreazioneLuogoPanel")) { //Se non sono già sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo CreazioneLuogoPanel al workPanel
			PanelModificaLuogo panel = new PanelModificaLuogo(controller, this, workPanel, l);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Info Luogo nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 * @param l Luogo di cui mostrare gli attributi
	 */
	public void cambiaPannelloLavoroAPanelInfoLuogo(JPanel workPanel, Luogo l) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class PanelInfoLuogo")) { //Se non sono già sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo PanelInfoLuogo al workPanel
			PanelInfoLuogo panel = new PanelInfoLuogo(controller, this, workPanel, l);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}

	/**
	 * Fa mostrare il pannello Scrivi Recensione nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 * @param l Luogo a cui aggiungere la recensione
	 */
	public void cambiaPannelloLavoroAPanelScriviRecensione(JPanel workPanel, Luogo l) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class PanelScriviRecensione")) { //Se non sono già sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo PanelInfoLuogo al workPanel
			PanelScriviRecensione panel = new PanelScriviRecensione(controller, this, workPanel, l);
			controller.setCurrentPanel(panel); //Imposta il pannello appena creato nella variabile currentPanel del controller
			workPanel.add(panel);
			workPanel.repaint();
			workPanel.revalidate();
		}
		
	}
	
	/**
	 * Fa mostrare il pannello Modifica Recensione nel workPanel
	 * @param workPanel pannello dove mostrare il pannello
	 * @param l Luogo che possiede la recensione da modificare (Per aggiornarne la media se si modifica la recensione)
	 * @param r Recensione da modificare
	 */
	public void cambiaPannelloLavoroAModificaRecensionePanel(JPanel workPanel, Luogo l, Recensione r) {
		
		if(controller.getCurrentPanel() == null || !controller.getCurrentPanel().getClass().toString().contentEquals("class PanelModificaRecensione")) { //Se non sono già sullo stesso panel
			//Rimuovi vecchio panel dal workPanel
			workPanel.removeAll();
			workPanel.repaint();
			workPanel.revalidate();
			
			//Aggiungi nuovo PanelInfoLuogo al workPanel
			PanelModificaRecensione panel = new PanelModificaRecensione(controller, this, workPanel, l, r);
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

		JButton btnRegistrati = (JButton)controller.getComponentByName(this, "btnRegistrati"); //Trova le components
		JButton buttonLogin = (JButton)controller.getComponentByName(this, "buttonLogin");
		
		btnRegistrati.setEnabled(!controller.isLoggedIn()); //Abilita il bottone registrati se non sono loggato e lo disattiva se sono loggato
		btnRegistrati.setVisible(btnRegistrati.isEnabled()); //Rende visibile il bottone registrati solo se è abilitato
		if(controller.isLoggedIn()) { //Se sono loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogout.png"))); //Imposta icona a "buttonLogout"
		}else { //Se non sono loggato
			buttonLogin.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/buttonLogin Highlighted.png"))); //Imposta icona a "buttonLogin highlighted" (Highlighted perchè ho appena premuto sul bottone per sloggare)
		}
		
	}

	/**
	 * Ricarica il pannello info utente se sono loggato o meno
	 * Se sono loggato mostra info sull'utente
	 * Se non sono loggato mostra la scritta "Effettua il login"
	 */
	public void refreshaPannelloInfo() {
		
		if(controller.isLoggedIn()) { //Se sono loggato
			
			JPanel panelNotLogged = (JPanel)controller.getComponentByName(this,  "panelNotLogged"); //Trova le components
			JPanel panelLogged = (JPanel)controller.getComponentByName(this,  "panelLogged");
			
			panelNotLogged.setVisible(false); //Rendi il pannello notLogged invisibile
			panelNotLogged.setEnabled(false); //Rendi il pannello notLogged inattivo
			
			panelLogged.setVisible(true); //Rendi il pannello logged visibile
			panelLogged.setEnabled(true); //Rendi il pannello logged attivo
			panelLogged.repaint(); //Repainta
			
			for(Component lbl: panelLogged.getComponents()) { //Per tutte le componenti del pannello logged
				
				if(lbl.getClass().getName().toString().contentEquals("javax.swing.JLabel")) { //Se la classe della componente è JLabel
					JLabel label = (JLabel) lbl; //Converti a classe Jlabel
					switch(label.getName()) { //Switch per il nome del label
					case "lblNomeUtente": label.setText("Nome: " + controller.getUtente().getNomeUtente()); break; //Se il nome è lblNomeUtente
					case "lblEmail": label.setText("Email: " + controller.getUtente().getEmail()); break; //Se il nome è lblEmail
					case "lblNumeroLuoghi": label.setText("Numero luoghi: " + controller.getUtente().getNumeroLuoghi()); break; //Se il nome è lblNumeroLuoghi
					case "lblNumeroRecensioni": label.setText("Numero recensioni: " + controller.getUtente().getNumeroRecensioni()); break; //Se il nome è lblNumeroRecensioni
					default: break;
					}
				}
			}
			
		}else { //Se non sono loggato
			
			JPanel panelNotLogged = (JPanel)controller.getComponentByName(this,  "panelNotLogged"); //Trova le components
			JPanel panelLogged = (JPanel)controller.getComponentByName(this,  "panelLogged");
			
			panelLogged.setVisible(false); //Rendi il pannello logged invisibile
			panelLogged.setEnabled(false);  //Rendi il pannello logged inattivo
			
			panelNotLogged.setVisible(true); //Rendi il pannello notLogged visibile
			panelNotLogged.setEnabled(true); //Rendi il pannello notLogged attivo
			panelNotLogged.repaint(); //Repainta pannello
		}
		
	}
	
	/**
	 * Crea una finestra di notifica che va chiusa prima di poter tornare al mainFrame
	 * @param notification Stringa da mostrare nella notifica
	 */
	public void createNotificationFrame(String notification) {
		
		notificationFrame frame = new notificationFrame(notification, this);
		frame.setVisible(true);
		
	}
	
	/**
	 * Cre una finestra di conferma che chiede all'utente di scegliere se proseguire con l'azione o annullarla
	 * @param notification Stringa da mostrare all'utente
	 * @return Boolean della risposta data dall'utente: 0 = annulla, 1 = ok;
	 */
	public boolean createConfirmationFrame(String notification) {
		
		confirmationFrame frame = new confirmationFrame(notification, this);
		frame.setVisible(true);
		boolean answer = frame.getAnswer();
		return answer;
		
	}

}