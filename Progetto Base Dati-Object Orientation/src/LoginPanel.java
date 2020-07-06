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
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel {
	
	MainController controller;
	MainFrame mainFrame;
	
	/**
	 * Pannello Login del programma, permette il login di un utente
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param workPanel JPanel dove mostrare il pannello
	 */
	public LoginPanel(MainController controller, MainFrame mainFrame, JPanel wP) {
		
		JPanel workPanel = wP;
		this.controller = controller;
		this.mainFrame = mainFrame;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new LineBorder(controller.independence, 2, true));
		panelLogin.setBackground(controller.sky);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(190)
					.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
					.addGap(190))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addGap(45))
		);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new MatteBorder(0, 0, 2, 0, controller.independence));
		upperPanel.setBackground(controller.steel);
		
		JTextField nomeUtenteField = new JTextField();
		nomeUtenteField.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 15));
		nomeUtenteField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) controller.pigeon));
		nomeUtenteField.setForeground(controller.independence);
		nomeUtenteField.setBackground(controller.sky);
		nomeUtenteField.setHorizontalAlignment(SwingConstants.CENTER);
		nomeUtenteField.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 15));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) controller.pigeon));
		passwordField.setForeground(controller.independence);
		passwordField.setBackground(controller.sky);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNomeUtente = new JLabel("Nome utente o Email");
		lblNomeUtente.setForeground(controller.egyptian);
		lblNomeUtente.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNomeUtente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Georgia", Font.BOLD, 18));
		lblPassword.setForeground(controller.egyptian);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(controller.steel);
		lowerPanel.setBorder(new MatteBorder(2, 0, 0, 0, controller.independence));
		
		JButton buttonCheckPasswordField = new JButton("");
		buttonCheckPasswordField.setBorder(null);
		buttonCheckPasswordField.setContentAreaFilled(false);
		buttonCheckPasswordField.setBackground(null);
		buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIconClosed.png")));
		buttonCheckPasswordField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCheckPasswordField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				passwordField.setEchoChar((char)0);
				buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIcon.png")));
			}
			public void mouseReleased(MouseEvent e) {
				passwordField.setEchoChar('*');
				buttonCheckPasswordField.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/EyeIconClosed.png")));
			}
		});
		
		JLabel lblRegistrati = new JLabel("Oppure registrati");
		lblRegistrati.setForeground(controller.independence);
		lblRegistrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrati.setFont(new Font("Georgia", Font.BOLD, 10));
		lblRegistrati.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainFrame.cambiaPannelloLavoroARegistrazionePanel(workPanel);
			}
		});
		
		JLabel lblUtenteIcon = new JLabel("");
		lblUtenteIcon.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/LoginIconSmall.png")));
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/PasswordIconSmall.png")));
		GroupLayout gl_panelLogin = new GroupLayout(panelLogin);
		gl_panelLogin.setHorizontalGroup(
			gl_panelLogin.createParallelGroup(Alignment.TRAILING)
				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblUtenteIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPasswordIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(lblNomeUtente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(nomeUtenteField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonCheckPasswordField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panelLogin.createSequentialGroup()
					.addComponent(lblRegistrati, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelLogin.setVerticalGroup(
			gl_panelLogin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelLogin.createSequentialGroup()
							.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelLogin.createSequentialGroup()
									.addGap(66)
									.addComponent(lblUtenteIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(54)
									.addComponent(lblPasswordIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelLogin.createSequentialGroup()
									.addGap(27)
									.addComponent(lblNomeUtente, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nomeUtenteField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(lblRegistrati, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonCheckPasswordField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(85)))
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(LoginPanel.class.getResource("/icons/MainLog.png")));
		GroupLayout gl_upperPanel = new GroupLayout(upperPanel);
		gl_upperPanel.setHorizontalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addGap(131)
					.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(131))
		);
		gl_upperPanel.setVerticalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addGap(12))
		);
		upperPanel.setLayout(gl_upperPanel);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.setFocusable(false);
		buttonLogin.setForeground(new Color(0, 17, 255));
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLogin.setContentAreaFilled(false);
		buttonLogin.setFont(new Font("Bauhaus 93", Font.BOLD, 48));
		buttonLogin.setBackground(controller.steel);
		buttonLogin.setBorder(null);
		buttonLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				String nomeUtente = nomeUtenteField.getText(); //Prendi nome utente dal rispettivo campo
				String password = String.valueOf(passwordField.getPassword()); //Prendi password dal rispettivo campo
				
				controller.login(nomeUtente, password, mainFrame); //Bottone login premuto
			}
		});
		GroupLayout gl_lowerPanel = new GroupLayout(lowerPanel);
		gl_lowerPanel.setHorizontalGroup(
			gl_lowerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(buttonLogin, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
		);
		gl_lowerPanel.setVerticalGroup(
			gl_lowerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(buttonLogin, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
		);
		lowerPanel.setLayout(gl_lowerPanel);
		panelLogin.setLayout(gl_panelLogin);
		setLayout(groupLayout);
		
	}

}