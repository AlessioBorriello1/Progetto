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

public class MainFrame extends JFrame {

	private MainController controller; //Controller che chiama il JFrame
	
	public MainFrame(MainController controller) {
		
		setSize(new Dimension(1000, 600)); //Dimensioni MainFrame
		getContentPane().setBackground(controller.white); //Colore background
		setUndecorated(true); //Undecorated
		setResizable(false); //Non ridimensionabile
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando si chiude il MainFrame chiudi applicazione
		
		JPanel upperPanel = new JPanel(); //Nuovo pannello (Upper panel)
		upperPanel.setBounds(0, 0, 1000, 30); //Posiziona upper panel
		getContentPane().setLayout(null); //Imposta layout pannello principale
		//Per poter trascinare la finestra
		upperPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { //Quando trascino
		        Point newPoint = e.getLocationOnScreen(); //Posizione MainFrame sullo schermo
		        newPoint.translate(-controller.mouseClickPoint.x, -controller.mouseClickPoint.y); //Trasla la posizione del MainFrame in base alla posizione del mouse (mouseClickPoint)
		        setLocation(newPoint); //Imposta nuova posizione
		    }
		});
		upperPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) { //Quando premo il mouse
				controller.mouseClickPoint = e.getPoint(); //Aggiorna posizione mouse
		    }
		});
		
		upperPanel.setBackground(controller.steel); //Imposta colore background upper panel
		getContentPane().add(upperPanel); //Aggiungi upper panel al pannello principale
		upperPanel.setLayout(null); //Imposta layout upper panel
		
		JButton btnClose = new JButton(); //Nuovo bottone close
		btnClose.setBounds(960, 0, 30, 30); //Posiziona
		//Impostazioni bottone close
		btnClose.setContentAreaFilled(false);
		btnClose.setSelectedIcon(null);
		btnClose.setBorderPainted(false);
		btnClose.setBorder(null);
		btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit.png")));
		
		//Eventi bottone close
		btnClose.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse chiudi il MainFrame
			public void mouseClicked(MouseEvent e) {
			
				setVisible(false);
				dispose();
				
			}
			//Quando il mouse passa sull'icona cambia icona
			public void mouseEntered(MouseEvent e) {
			
				btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit highlighted.png")));
				
			}
			//Quando il mouse esce dall'icona cambia icona
			public void mouseExited(MouseEvent e) {
			
				btnClose.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Exit.png")));
				
			}
		});
		
		
		JTextPane txtpnTripadvisor = new JTextPane();
		//Impostazioni testo
		txtpnTripadvisor.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnTripadvisor.setBounds(0, 0, 116, 30);
		txtpnTripadvisor.setHighlighter(null);
		txtpnTripadvisor.setForeground(controller.electric);
		txtpnTripadvisor.setEditable(false);
		txtpnTripadvisor.setBackground(upperPanel.getBackground());
		txtpnTripadvisor.setText("TripAdvisor");
		
		upperPanel.add(txtpnTripadvisor); //Aggiungi testo TripAdvisor all'upper panel
		upperPanel.add(btnClose); //Aggiungi bottone close all'upper panel
		
		JButton btnMinimize = new JButton(); //Nuovo bottone minimize
		//Impostazioni bottone minimize
		btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize.png")));
		btnMinimize.setContentAreaFilled(false);
		btnMinimize.setBorderPainted(false);
		btnMinimize.setBorder(null);
		btnMinimize.setBounds(930, 0, 30, 30);
		
		upperPanel.add(btnMinimize); //Aggiungi bottone minimize all'upper panel
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 41, 980, 548);
		mainPanel.setBackground(getContentPane().getBackground());
		getContentPane().add(mainPanel);
		
		JPanel dashBoardPanel = new JPanel();
		dashBoardPanel.setBackground(controller.prussian);
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		
		SpringLayout sl_dashBoardPanel = new SpringLayout();
		dashBoardPanel.setLayout(sl_dashBoardPanel);
		
		JLabel labelProfilePic = new JLabel("");
		labelProfilePic.setHorizontalAlignment(SwingConstants.CENTER);
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, labelProfilePic, 10, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, labelProfilePic, 0, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, labelProfilePic, 212, SpringLayout.NORTH, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, labelProfilePic, 202, SpringLayout.WEST, dashBoardPanel);
		labelProfilePic.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/ProfilePic.png")));
		dashBoardPanel.add(labelProfilePic);
		
		JPanel panelInfoUtente = new JPanel();
		panelInfoUtente.setBackground(dashBoardPanel.getBackground());
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelInfoUtente, 6, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelInfoUtente, 10, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelInfoUtente, 99, SpringLayout.SOUTH, labelProfilePic);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelInfoUtente, 192, SpringLayout.WEST, labelProfilePic);
		dashBoardPanel.add(panelInfoUtente);
		
		JPanel panelControl = new JPanel();
		panelControl.setBackground(dashBoardPanel.getBackground());
		sl_dashBoardPanel.putConstraint(SpringLayout.NORTH, panelControl, 19, SpringLayout.SOUTH, panelInfoUtente);
		sl_dashBoardPanel.putConstraint(SpringLayout.WEST, panelControl, 10, SpringLayout.WEST, dashBoardPanel);
		sl_dashBoardPanel.putConstraint(SpringLayout.SOUTH, panelControl, 221, SpringLayout.SOUTH, panelInfoUtente);
		sl_dashBoardPanel.putConstraint(SpringLayout.EAST, panelControl, 0, SpringLayout.EAST, panelInfoUtente);
		dashBoardPanel.add(panelControl);
		SpringLayout sl_panelControl = new SpringLayout();
		panelControl.setLayout(sl_panelControl);
		
		//Crea pannello di lavoro
		JPanel workPanel = new JPanel();
		workPanel.setBackground(Color.LIGHT_GRAY);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addComponent(dashBoardPanel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(workPanel, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dashBoardPanel, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
						.addComponent(workPanel, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		workPanel.setLayout(new CardLayout(0, 0));
		
		JButton buttonHome = new JButton("Home");
		buttonHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				cambiaPannelloLavoroAHomePanel(workPanel);
				
			}
		});
		
		sl_panelControl.putConstraint(SpringLayout.EAST, buttonHome, -10, SpringLayout.EAST, panelControl);
		panelControl.add(buttonHome);
		
		JButton btnGestisciLocali = new JButton("Gestisci locali");
		sl_panelControl.putConstraint(SpringLayout.WEST, btnGestisciLocali, 10, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnGestisciLocali, -10, SpringLayout.EAST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, buttonHome, 0, SpringLayout.WEST, btnGestisciLocali);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, buttonHome, -6, SpringLayout.NORTH, btnGestisciLocali);
		panelControl.add(btnGestisciLocali);
		
		JButton btnGestisciRecensioni = new JButton("Gestisci recensioni");
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnGestisciRecensioni, 104, SpringLayout.NORTH, panelControl);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnGestisciRecensioni, 10, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnGestisciRecensioni, -10, SpringLayout.EAST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.SOUTH, btnGestisciLocali, -6, SpringLayout.NORTH, btnGestisciRecensioni);
		panelControl.add(btnGestisciRecensioni);
		
		JButton btnLogout = new JButton("Logout");
		sl_panelControl.putConstraint(SpringLayout.NORTH, btnLogout, 6, SpringLayout.SOUTH, btnGestisciRecensioni);
		sl_panelControl.putConstraint(SpringLayout.WEST, btnLogout, 10, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, panelControl);
		panelControl.add(btnLogout);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				cambiaPannelloLavoroALoginPanel(workPanel);
				
			}
			
		});
		sl_panelControl.putConstraint(SpringLayout.NORTH, buttonLogin, 6, SpringLayout.SOUTH, btnLogout);
		sl_panelControl.putConstraint(SpringLayout.WEST, buttonLogin, 10, SpringLayout.WEST, panelControl);
		sl_panelControl.putConstraint(SpringLayout.EAST, buttonLogin, 172, SpringLayout.WEST, panelControl);
		panelControl.add(buttonLogin);
		
		mainPanel.setLayout(gl_mainPanel);
		//Eventi bottone minimize
		btnMinimize.addMouseListener(new MouseAdapter() {
			//Quando premo il mouse minimizza il MainFrame
			public void mouseClicked(MouseEvent e) {
			
				setState(ICONIFIED);
				
			}
			//Quando il mouse passa sull'icona cambia icona
			public void mouseEntered(MouseEvent e) {
			
				btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize highlighted.png")));
				
			}
			//Quando il mouse esce dall'icona cambia icona
			public void mouseExited(MouseEvent e) {
			
				btnMinimize.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/Minimize.png")));
				
			}
		});
		
	}
	
	public void cambiaPannelloLavoroALoginPanel(JPanel workPanel) {
		
		//Rimuovi vecchio panel
		workPanel.removeAll();
		workPanel.repaint();
		workPanel.revalidate();
		
		//Aggiungi nuovo panel
		workPanel.add(new LoginPanel(controller));
		workPanel.repaint();
		workPanel.revalidate();
		
	}
	
	public void cambiaPannelloLavoroAHomePanel(JPanel workPanel) {
		
		//Rimuovi vecchio panel
		workPanel.removeAll();
		workPanel.repaint();
		workPanel.revalidate();
		
		//Aggiungi nuovo panel
		workPanel.add(new HomePanel(controller));
		workPanel.repaint();
		workPanel.revalidate();
		
	}
	
}