import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class RegistrazionePanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;
	JTextField nomeUtenteField;
	JTextField emailField;
	JPasswordField passwordField;

	/**
	 * Pannello Registrazione permette la registrazione di un nuovo utente
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param wP JPanel dove mostrare il pannello
	 */
	public RegistrazionePanel(MainController controller, MainFrame mainFrame, JPanel wP) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		JPanel workPanel = wP;
		
		setBackground(controller.skyWhiter);
		
		JPanel registrazionePanel = new JPanel();
		registrazionePanel.setBackground(controller.sky);
		registrazionePanel.setBorder(new LineBorder(null, 2, true));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(190)
					.addComponent(registrazionePanel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addGap(166))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addComponent(registrazionePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(controller.independence);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBackground(controller.sky);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) controller.pigeon));
		
		JLabel lblPassword = new JLabel(MainController.PASSWORD);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Georgia", Font.BOLD, 18));
		
		emailField = new JTextField();
		emailField.setForeground(controller.independence);
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) controller.pigeon));
		emailField.setBackground(controller.sky);
		emailField.setColumns(10);
		
		JLabel lblNomeUtente = new JLabel("Nome utente");
		lblNomeUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUtente.setFont(new Font("Georgia", Font.BOLD, 18));
		
		nomeUtenteField = new JTextField();
		nomeUtenteField.setForeground(controller.independence);
		nomeUtenteField.setHorizontalAlignment(SwingConstants.CENTER);
		nomeUtenteField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) controller.pigeon));
		nomeUtenteField.setBackground(controller.sky);
		nomeUtenteField.setColumns(10);
		
		JButton buttonCheckPasswordField = new JButton("");
		buttonCheckPasswordField.setBorder(null);
		buttonCheckPasswordField.setContentAreaFilled(false);
		buttonCheckPasswordField.setBackground(null);
		buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIconClosed.png")));
		buttonCheckPasswordField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCheckPasswordField.addMouseListener(new MouseAdapter() {
			//Mostra il campo password se il bottone è premuto
			public void mousePressed(MouseEvent e) {
				passwordField.setEchoChar((char)0);
				buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIcon.png")));
			}
			//Nascondi il campo password se il bottone non è premuto
			public void mouseReleased(MouseEvent e) {
				passwordField.setEchoChar('*');
				buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIconClosed.png")));
			}
		});
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(0, 0, 2, 0, controller.independence));
		upperPanel.setBackground(controller.steel);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBorder(new MatteBorder(2, 0, 0, 0, controller.independence));
		lowerPanel.setBackground(controller.steel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Georgia", Font.BOLD, 18));
		
		JLabel lblUtenteIcon = new JLabel("");
		lblUtenteIcon.setIcon(new ImageIcon(RegistrazionePanel.class.getResource("/icons/LoginIconSmall.png")));
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setIcon(new ImageIcon(RegistrazionePanel.class.getResource("/icons/PasswordIconSmall.png")));
		
		JLabel lblEmailIcon = new JLabel("");
		lblEmailIcon.setIcon(new ImageIcon(RegistrazionePanel.class.getResource("/icons/EmailIconSmall.png")));
		GroupLayout gl_registrazionePanel = new GroupLayout(registrazionePanel);
		gl_registrazionePanel.setHorizontalGroup(
			gl_registrazionePanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_registrazionePanel.createSequentialGroup()
					.addGap(138)
					.addComponent(lblNomeUtente, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(138))
				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
				.addGroup(gl_registrazionePanel.createSequentialGroup()
					.addGap(137)
					.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(137))
				.addGroup(gl_registrazionePanel.createSequentialGroup()
					.addGap(137)
					.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(137))
				.addGroup(Alignment.LEADING, gl_registrazionePanel.createSequentialGroup()
					.addGroup(gl_registrazionePanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_registrazionePanel.createSequentialGroup()
							.addGap(50)
							.addComponent(lblUtenteIcon)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_registrazionePanel.createSequentialGroup()
							.addContainerGap(50, Short.MAX_VALUE)
							.addComponent(lblPasswordIcon)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_registrazionePanel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblEmailIcon)
							.addGap(10)))
					.addGroup(gl_registrazionePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(nomeUtenteField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addComponent(emailField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
					.addGap(9)
					.addComponent(buttonCheckPasswordField)
					.addGap(65))
		);
		gl_registrazionePanel.setVerticalGroup(
			gl_registrazionePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registrazionePanel.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_registrazionePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_registrazionePanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(lblNomeUtente, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nomeUtenteField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_registrazionePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_registrazionePanel.createSequentialGroup()
									.addComponent(buttonCheckPasswordField, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addGroup(Alignment.TRAILING, gl_registrazionePanel.createSequentialGroup()
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(11))))
						.addGroup(gl_registrazionePanel.createSequentialGroup()
							.addGap(57)
							.addComponent(lblUtenteIcon)
							.addGap(63)
							.addComponent(lblEmailIcon)
							.addGap(57)
							.addComponent(lblPasswordIcon)
							.addGap(14)))
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton buttonRegistrati = new JButton("Registrati");
		buttonRegistrati.setFont(new Font("Bauhaus 93", Font.BOLD, 48));
		buttonRegistrati.setForeground(new Color(0, 17, 255));
		buttonRegistrati.setBackground(controller.steel);
		buttonRegistrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonRegistrati.setContentAreaFilled(false);
		buttonRegistrati.setFocusable(false);
		buttonRegistrati.setBorder(null);
		buttonRegistrati.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//Registrati con le credenziali inserite
				if(mainFrame.createConfirmationFrame("Creare account con queste informazioni?")) {
					String nomeUtente = nomeUtenteField.getText(); //Prendi nome utente dal rispettivo campo
					String email = emailField.getText(); //Prendi email dal rispettivo campo
					String password = String.valueOf(passwordField.getPassword()); //Prendi password dal rispettivo campo
					
					if(controller.registrazione(nomeUtente, password, email, mainFrame)) {
						mainFrame.cambiaPannelloLavoroALoginPanel(workPanel);//Vai a pannello login
					}
				}
			}
		});
		GroupLayout gl_lowerPanel = new GroupLayout(lowerPanel);
		gl_lowerPanel.setHorizontalGroup(
			gl_lowerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(buttonRegistrati, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
		);
		gl_lowerPanel.setVerticalGroup(
			gl_lowerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_lowerPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(buttonRegistrati))
		);
		lowerPanel.setLayout(gl_lowerPanel);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(RegistrazionePanel.class.getResource("/icons/MainLog.png")));
		GroupLayout gl_upperPanel = new GroupLayout(upperPanel);
		gl_upperPanel.setHorizontalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_upperPanel.createSequentialGroup()
					.addGap(131)
					.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(131))
		);
		gl_upperPanel.setVerticalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_upperPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addGap(10))
		);
		upperPanel.setLayout(gl_upperPanel);
		registrazionePanel.setLayout(gl_registrazionePanel);
		setLayout(groupLayout);
		
	}
}