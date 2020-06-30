import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LocaliPanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;

	public LocaliPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelControl = new JPanel();
		panelControl.setBackground(controller.sky);
		
		JScrollPane panelLocali = new JScrollPane();
		panelLocali.setBorder(null);
		panelLocali.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelLocali.getVerticalScrollBar().setUnitIncrement(16);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelLocali, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
						.addComponent(panelControl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelControl, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelLocali, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(true);
		verticalBox.setBackground(controller.skyWhiter);
		panelLocali.setViewportView(verticalBox);
		
		JButton btnAggiungiLuogo = new JButton("Aggiungi luogo");
		btnAggiungiLuogo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainFrame.cambiaPannelloLavoroACreazioneLuogoPanel(workPanel);
			}
		});
		
		JLabel lblIMieiLuoghi = new JLabel("I miei luoghi:");
		lblIMieiLuoghi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIMieiLuoghi.setForeground(controller.egyptian);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIMieiLuoghi, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 468, Short.MAX_VALUE)
					.addComponent(btnAggiungiLuogo)
					.addGap(12))
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelControl.createSequentialGroup()
							.addGap(5)
							.addComponent(lblIMieiLuoghi))
						.addGroup(gl_panelControl.createSequentialGroup()
							.addGap(4)
							.addComponent(btnAggiungiLuogo)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelControl.setLayout(gl_panelControl);
		setLayout(groupLayout);
		
		List<Luogo> luoghiUtente = controller.getUtente().getLuoghiUtente();
		
		if(!luoghiUtente.isEmpty()) {
			for(Luogo l : luoghiUtente) {
				
				PanelInfoLuogoAnteprima panel = new PanelInfoLuogoAnteprima(controller, mainFrame, l, true, workPanel);
				verticalBox.add(panel);
				JButton removeButton = new JButton("X");
				verticalBox.add(removeButton);
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						boolean answer = mainFrame.createConfirmationFrame("Sei sicuro di voler cancellare questo luogo?");
						if(answer) {
							controller.rimuoviLuogo(mainFrame, controller, workPanel, l);
						}
					}
				});
				
				verticalBox.add(Box.createRigidArea(new Dimension(0, 8)));
				
			}
		}else {
			
			JLabel lblNessunLuogo = new JLabel("  Nessun luogo");
			lblNessunLuogo.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblNessunLuogo.setHorizontalTextPosition(SwingConstants.CENTER);
			verticalBox.add(Box.createRigidArea(new Dimension(0, 14)));
			verticalBox.add(lblNessunLuogo);
			
		}
		
	}

}