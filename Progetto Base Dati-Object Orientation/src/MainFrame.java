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

public class MainFrame extends JFrame {
	
	private MainController controller; //Controller che chiama il JFrame
	private Point mouseClickPoint; //Posizione mouse
	
	public MainFrame(MainController controller) {
		
		setSize(new Dimension(1000, 600)); //Dimensioni MainFrame
		getContentPane().setBackground(new Color(240, 240, 240)); //Colore background
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
		
		upperPanel.setBackground(SystemColor.inactiveCaption); //Imposta colore background upper panel
		getContentPane().add(upperPanel); //Aggiungi upper panel al pannello principale
		upperPanel.setLayout(null); //Imposta layout upper panel
		
		JButton btnClose = new JButton(); //Nuovo bottone close
		btnClose.setBounds(970, 0, 30, 30); //Posiziona
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
		txtpnTripadvisor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnTripadvisor.setBounds(0, 0, 96, 30);
		txtpnTripadvisor.setHighlighter(null);
		txtpnTripadvisor.setForeground(SystemColor.textHighlight);
		txtpnTripadvisor.setEditable(false);
		txtpnTripadvisor.setCaretColor(SystemColor.inactiveCaptionText);
		txtpnTripadvisor.setBackground(SystemColor.inactiveCaption);
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
