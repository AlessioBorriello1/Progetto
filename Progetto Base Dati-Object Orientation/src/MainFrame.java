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

public class MainFrame extends JFrame {
	
	private MainController controller; //Controller che chiama il JFrame
	private Point mouseClickPoint; //Posizione mouse
	private JFrame currentFrame; //Frame che sta mostrando nella parte destra del MainFrame
	
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
	
	public MainFrame(MainController controller) {
		
		setSize(new Dimension(1000, 600)); //Dimensioni MainFrame
		getContentPane().setBackground(white); //Colore background
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
	            newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); //Trasla la posizione del MainFrame in base alla posizione del mouse (mouseClickPoint)
	            setLocation(newPoint); //Imposta nuova posizione
	        }
		});
		upperPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) { //Quando premo il mouse
				mouseClickPoint = e.getPoint(); //Aggiorna posizione mouse
	        }
		});
		
		upperPanel.setBackground(steel); //Imposta colore background upper panel
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
		txtpnTripadvisor.setForeground(electric);
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
		dashBoardPanel.setBackground(prussian);
		
		JPanel workPanel = new JPanel();
		workPanel.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
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
}