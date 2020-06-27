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
import javax.swing.JTextArea;

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
		
		JLabel lblVoto = new JLabel("Voto: " + r.getVoto());
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblDate = new JLabel("Creata da: " + r.getNomeUtente() + " il " + r.getData().toString());
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setText(r.getTesto());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVoto)
							.addPreferredGap(ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
							.addComponent(lblDate)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoto)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
