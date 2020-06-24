import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class HomePanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;

	public HomePanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelRicerca = new JPanel();
		panelRicerca.setBackground(controller.sky);
		
		JPanel panelMostraLuoghi = new JPanel();
		panelMostraLuoghi.setBackground(getBackground());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelRicerca, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
				.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelRicerca, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollBar scrollBar = new JScrollBar();
		GroupLayout gl_panelMostraLuoghi = new GroupLayout(panelMostraLuoghi);
		gl_panelMostraLuoghi.setHorizontalGroup(
			gl_panelMostraLuoghi.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelMostraLuoghi.createSequentialGroup()
					.addContainerGap(745, Short.MAX_VALUE)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelMostraLuoghi.setVerticalGroup(
			gl_panelMostraLuoghi.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
		);
		panelMostraLuoghi.setLayout(gl_panelMostraLuoghi);
		setLayout(groupLayout);
		
		//JScrollPane scrollPane = new JScrollPane(panelMostraLuoghi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
}
