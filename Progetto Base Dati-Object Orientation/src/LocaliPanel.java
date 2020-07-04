import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class LocaliPanel extends JPanel  {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;

	public LocaliPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel panelControl = new JPanel();
		panelControl.setBorder(new LineBorder(controller.independence, 2, true));
		panelControl.setBackground(controller.steel);
		
		JScrollPane panelLocali = new JScrollPane();
		panelLocali.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelLocali.setBorder(null);
		panelLocali.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelLocali.getVerticalScrollBar().setUnitIncrement(16);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLocali, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
						.addComponent(panelControl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelControl, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelLocali, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(true);
		verticalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.setBackground(controller.skyWhiter);
		panelLocali.setViewportView(verticalBox);
		
		JButton btnAggiungiLuogo = new JButton("Aggiungi luogo");
		btnAggiungiLuogo.setFont(new Font("Georgia", Font.BOLD, 18));
		btnAggiungiLuogo.setBorder(new MatteBorder(0, 2, 0, 0, controller.independence));
		btnAggiungiLuogo.setFocusable(false);
		btnAggiungiLuogo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainFrame.cambiaPannelloLavoroACreazioneLuogoPanel(workPanel);
			}
		});
		
		JLabel lblIMieiLuoghi = new JLabel("I miei luoghi:");
		lblIMieiLuoghi.setFont(new Font("Georgia", Font.BOLD, 20));
		lblIMieiLuoghi.setForeground(controller.electric);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIMieiLuoghi, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
					.addComponent(btnAggiungiLuogo, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addGroup(gl_panelControl.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAggiungiLuogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelControl.createSequentialGroup()
							.addGap(5)
							.addComponent(lblIMieiLuoghi)))
					.addGap(1))
		);
		panelControl.setLayout(gl_panelControl);
		setLayout(groupLayout);
		
		List<Luogo> luoghiUtente = controller.getUtente().getLuoghiUtente();
		
		if(!luoghiUtente.isEmpty()) {
			for(Luogo l : luoghiUtente) {
				PanelInfoLuogoAnteprima panel = new PanelInfoLuogoAnteprima(controller, mainFrame, l, true, workPanel);
				verticalBox.add(panel);
				JButton removeButton = new JButton("X");
				removeButton.setFocusable(false);
				verticalBox.add(removeButton);
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						boolean answer = mainFrame.createConfirmationFrame("Sei sicuro di voler cancellare il luogo " + l.getNome() + "?");
						if(answer) {
							controller.rimuoviLuogo(mainFrame, controller, workPanel, l);
						}
					}
				});
				verticalBox.add(Box.createRigidArea(new Dimension(0, 8)));
			}
		}else {
			
			JLabel lblNessunLuogo = new JLabel("  Nessun luogo");
			lblNessunLuogo.setFont(new Font("Georgia", Font.BOLD, 21));
			lblNessunLuogo.setForeground(controller.egyptian);
			lblNessunLuogo.setHorizontalTextPosition(SwingConstants.CENTER);
			verticalBox.add(Box.createRigidArea(new Dimension(0, 14)));
			verticalBox.add(lblNessunLuogo);
			
		}
		
	}

}