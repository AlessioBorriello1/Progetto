import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginPanel extends JPanel {
	
	MainController controller;
	MainFrame mainFrame;

	public LoginPanel(MainController controller, MainFrame mainFrame, JPanel wP) {
		
		JPanel workPanel = wP;
		this.controller = controller;
		this.mainFrame = mainFrame;
		
		setBackground(controller.skyWhiter);
		setLayout(null);
		
		JTextField nomeUtenteField = new JTextField();
		nomeUtenteField.setBounds(402, 140, 220, 30);
		add(nomeUtenteField);
		nomeUtenteField.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(402, 197, 220, 30);
		add(passwordField);
		
		JLabel lblNomeUtente = new JLabel("Nome utente o Email:");
		lblNomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUtente.setBounds(172, 137, 220, 31);
		add(lblNomeUtente);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(160, 194, 220, 31);
		add(lblPassword);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				String nomeUtente = nomeUtenteField.getText(); //Prendi nome utente dal rispettivo campo
				String password = String.valueOf(passwordField.getPassword()); //Prendi password dal rispettivo campo
				
				controller.login(nomeUtente, password, mainFrame); //Bottone login premuto
			
			}
		});
		buttonLogin.setBounds(402, 267, 220, 30);
		add(buttonLogin);
		
		JLabel lblRegistrati = new JLabel("Oppure registrati");
		lblRegistrati.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRegistrati.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistrati.setBounds(160, 371, 220, 31);
		add(lblRegistrati);
		
		JButton buttonRegistrati = new JButton("Registrati");
		buttonRegistrati.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainFrame.cambiaPannelloLavoroARegistrazionePanel(workPanel);
			}
		});
		buttonRegistrati.setBounds(402, 374, 220, 30);
		add(buttonRegistrati);
		
	}
}