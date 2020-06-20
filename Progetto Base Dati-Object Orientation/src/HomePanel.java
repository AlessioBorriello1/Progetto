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
	MainFrame mainFrame;

	public HomePanel(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		setBackground(Color.GRAY);
		
		JTextPane txtpnTripadvisor = new JTextPane();
		txtpnTripadvisor.setText("Home page");
		add(txtpnTripadvisor);
		
	}

}
