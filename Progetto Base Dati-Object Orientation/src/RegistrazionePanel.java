import java.awt.Color;
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

public class RegistrazionePanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;

	public RegistrazionePanel(MainController controller, MainFrame mainFrame, JPanel wP) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		JPanel workPanel = wP;
		
		setBackground(controller.skyWhiter);
		setLayout(null);
		
		JTextField nomeUtenteField = new JTextField();
		nomeUtenteField.setBounds(486, 110, 220, 30);
		add(nomeUtenteField);
		nomeUtenteField.setColumns(10);
		
		JTextField emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(486, 167, 220, 30);
		add(emailField);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(486, 225, 220, 30);
		add(passwordField);
		
		JLabel lblNomeUtente = new JLabel("Nome utente:");
		lblNomeUtente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeUtente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeUtente.setBounds(256, 109, 220, 31);
		add(lblNomeUtente);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(256, 167, 220, 31);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(256, 224, 220, 31);
		add(lblPassword);
		
		JButton buttonRegistrati = new JButton("Submit");
		buttonRegistrati.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
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
		buttonRegistrati.setBounds(486, 308, 220, 30);
		add(buttonRegistrati);
		
		JButton buttonCheckPasswordField = new JButton("");
		buttonCheckPasswordField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				passwordField.setEchoChar((char)0);
			}
			public void mouseReleased(MouseEvent e) {
				passwordField.setEchoChar('*');
			}
		});
		buttonCheckPasswordField.setBounds(716, 225, 17, 17);
		add(buttonCheckPasswordField);
		
	}

}