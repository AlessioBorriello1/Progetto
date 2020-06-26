import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelInfoLuogo extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	
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
					mainFrame.cambiaPannelloLavoroAPanelScriviRecensione(workPanel, l);
				}else {
					mainFrame.createNotificationFrame("Devi prima eseguire il Login!");
					mainFrame.cambiaPannelloLavoroALoginPanel(workPanel);
				}
			
			}
		});
		
		JScrollPane panelRecensioni = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(panelRecensioni, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelLuogo, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRecensioni)
							.addPreferredGap(ComponentPlacement.RELATED, 547, Short.MAX_VALUE)
							.addComponent(btnLasciaRecensione)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLasciaRecensione)
						.addComponent(lblRecensioni))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelRecensioni, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
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
		
		Box verticalBox = Box.createVerticalBox();
		panelRecensioni.setViewportView(verticalBox);
		setLayout(groupLayout);
		
	}

}
