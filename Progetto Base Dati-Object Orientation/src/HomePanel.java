import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HomePanel extends JPanel {

	MainController controller;

	public HomePanel(MainController controller) {
		
		this.controller = controller;
		
		setBackground(Color.GRAY);
		
		JTextPane txtpnTripadvisor = new JTextPane();
		txtpnTripadvisor.setText("Home page");
		add(txtpnTripadvisor);
		
	}

}
