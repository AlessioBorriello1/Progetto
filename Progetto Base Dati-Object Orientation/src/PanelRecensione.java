import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class PanelRecensione extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	
	public PanelRecensione(MainController controller, MainFrame mainFrame, Recensione r, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setMaximumSize(new Dimension(748, 100));
		
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.lightGrey);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(714, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(714, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}

}
