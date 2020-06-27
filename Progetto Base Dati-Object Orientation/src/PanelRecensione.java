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
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.border.EtchedBorder;

public class PanelRecensione extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	
	public PanelRecensione(MainController controller, MainFrame mainFrame, Recensione r, JPanel workPanel) {
		setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setMaximumSize(new Dimension(786, 100));
		
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.white);
		
		JLabel lblVoto = new JLabel("Voto:" + r.getVoto());
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JTextPane textPane = new JTextPane();
		textPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPane.setEditable(false);
		textPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPane.setText(r.getTesto());
		
		JLabel lblDate = new JLabel("Creata da: " + r.getNomeUtente() + " il " + r.getData().toString());
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblVoto)
							.addPreferredGap(ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
							.addComponent(lblDate)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoto)
						.addComponent(lblDate))
					.addGap(1)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addGap(6))
		);
		setLayout(groupLayout);
		
	}
}
