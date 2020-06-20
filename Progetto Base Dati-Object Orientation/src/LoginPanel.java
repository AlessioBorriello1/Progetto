import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

public class LoginPanel extends JPanel {
	
	MainController controller;
	MainFrame mainFrame;

	public LoginPanel(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		setBackground(Color.GRAY);
		
		JTextPane txtpnTripadvisor = new JTextPane();
		txtpnTripadvisor.setText("Login page");
		add(txtpnTripadvisor);
		
	}

}