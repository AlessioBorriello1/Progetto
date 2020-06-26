import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
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
import javax.swing.Box;

public class HomePanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;
	private List<Luogo> ricerca; //Array list che contiene la lista dei luoghi in base alla ricerca effettuata

	public HomePanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		//Pannello appena aperto, effettua la prima ricerca, senza filtri
		LuogoDAO dao = new LuogoDAO();
		ricerca = dao.getListaTuttiLuoghi();
		
		setBackground(controller.skyWhiter);
		
		JPanel panelRicerca = new JPanel();
		panelRicerca.setBackground(controller.sky);
		
		JScrollPane panelMostraLuoghi = new JScrollPane();
		panelMostraLuoghi.setBorder(null);
		panelMostraLuoghi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelMostraLuoghi.getVerticalScrollBar().setUnitIncrement(16);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelRicerca, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelRicerca, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(true);
		verticalBox.setBackground(controller.skyWhiter);
		panelMostraLuoghi.setViewportView(verticalBox);
		setLayout(groupLayout);
		
		for(Luogo l : ricerca) {
			
			PanelInfoLuogoAnteprima panel = new PanelInfoLuogoAnteprima(controller, mainFrame, l);
			verticalBox.add(panel);
			
			verticalBox.add(Box.createRigidArea(new Dimension(0, 8)));
			
		}
		
	}
}
