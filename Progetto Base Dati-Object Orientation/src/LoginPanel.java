import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

public class LoginPanel extends JPanel {
	
	MainController controller;

	public LoginPanel(MainController controller) {
		
		this.controller = controller;
		
		setBackground(Color.GRAY);
		
		JTextPane txtpnTripadvisor = new JTextPane();
		txtpnTripadvisor.setText("Login page");
		add(txtpnTripadvisor);
		
	}
	
}