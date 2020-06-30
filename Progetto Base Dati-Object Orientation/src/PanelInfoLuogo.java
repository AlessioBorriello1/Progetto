import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class PanelInfoLuogo extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	private List<Recensione> recensioniLuogo;
	
	public PanelInfoLuogo(MainController controller, MainFrame mainFrame, JPanel workPanel, Luogo l) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelLuogo = new JPanel();

		PanelInfoLuogoAnteprima infoLuogo = new PanelInfoLuogoAnteprima(controller, mainFrame, l, false, workPanel);
		
		JLabel lblRecensioni = new JLabel("Recensioni:");
		lblRecensioni.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLasciaRecensione = new JButton("Lascia recensione");
		btnLasciaRecensione.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				if(controller.getUtente()!= null) {
					if(!l.getNomeUtente().contentEquals(controller.getUtente().getNomeUtente())) {
						mainFrame.cambiaPannelloLavoroAPanelScriviRecensione(workPanel, l);
					}else {
						mainFrame.createNotificationFrame("Non puoi lasciare una recensione al tuo stesso luogo!");
					}
				}else {
					mainFrame.createNotificationFrame("Devi prima eseguire il Login!");
					mainFrame.cambiaPannelloLavoroALoginPanel(workPanel);
				}
			
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setBackground(controller.skyWhiter);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
						.addComponent(panelLuogo, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRecensioni)
							.addPreferredGap(ComponentPlacement.RELATED, 499, Short.MAX_VALUE)
							.addComponent(btnLasciaRecensione)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLasciaRecensione)
						.addComponent(lblRecensioni))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		Box verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);
		verticalBox.setBackground(controller.skyWhiter);
		verticalBox.setOpaque(true);
		GroupLayout gl_panelLuogo = new GroupLayout(panelLuogo);
		gl_panelLuogo.setHorizontalGroup(
			gl_panelLuogo.createParallelGroup(Alignment.LEADING)
				.addComponent(infoLuogo, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
		);
		gl_panelLuogo.setVerticalGroup(
			gl_panelLuogo.createParallelGroup(Alignment.LEADING)
				.addComponent(infoLuogo, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
		);
		panelLuogo.setLayout(gl_panelLuogo);
		setLayout(groupLayout);
		
		RecensioneDAO dao = new RecensioneDAO();
		recensioniLuogo = dao.getListaRecensioniLuogo(l);
		
		
		if(!recensioniLuogo.isEmpty()) {
			for(Recensione r : recensioniLuogo) {
				
				PanelRecensione panel = new PanelRecensione(controller, mainFrame, r, workPanel);
				verticalBox.add(panel);
				
				verticalBox.add(Box.createRigidArea(new Dimension(0, 8)));
				
			}
		}else {
			JLabel lblNessunaRecensione = new JLabel("  Nessuna recensione");
			lblNessunaRecensione.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblNessunaRecensione.setHorizontalTextPosition(SwingConstants.CENTER);
			verticalBox.add(Box.createRigidArea(new Dimension(0, 14)));
			verticalBox.add(lblNessunaRecensione);
			
		}
		
		
	}

}
