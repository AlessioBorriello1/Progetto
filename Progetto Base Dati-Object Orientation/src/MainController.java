import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainController {
	
	public Point mouseClickPoint; //Posizione mouse
	public JPanel currentPanel;
	
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

	public static void main(String[] args) {
		
		//Crea Main frame
		MainController controller = new MainController();
		MainFrame mainFrame = new MainFrame(controller);
		mainFrame.setVisible(true);
	
	}
	
	public JPanel createLoginPanel(MainFrame mainFrame){
		
		LoginPanel loginPanel = new LoginPanel(mainFrame);
		currentPanel = loginPanel;
		return loginPanel;
		
	}
	
	public JPanel createHomePanel(MainFrame mainFrame){
		
		HomePanel homePanel = new HomePanel(mainFrame);
		currentPanel = homePanel;
		return homePanel;
		
	}

}