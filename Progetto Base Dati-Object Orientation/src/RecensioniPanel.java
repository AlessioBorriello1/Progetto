import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RecensioniPanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;

	public RecensioniPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
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
		
		JLabel lblLeMieRecensioni = new JLabel("Le mie recensioni:");
		lblLeMieRecensioni.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLeMieRecensioni.setForeground(controller.egyptian);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLeMieRecensioni, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addGap(5)
					.addComponent(lblLeMieRecensioni)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelControl.setLayout(gl_panelControl);
		setLayout(groupLayout);
		
		RecensioneDAO dao = new RecensioneDAO();
		
		List<Recensione> recensioniUtente = dao.getListaRecensioniByNomeUtente(controller.getUtente().getNomeUtente());
		
		if(!recensioniUtente.isEmpty()) {
			for(Recensione r : recensioniUtente) {
				
				PanelRecensione panel = new PanelRecensione(controller, mainFrame, r, workPanel);
				verticalBox.add(panel);
				JButton removeButton = new JButton("X");
				verticalBox.add(removeButton);
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						boolean answer = mainFrame.createConfirmationFrame("Sei sicuro di voler cancellare questa recensione?");
						if(answer) {
							System.out.println("Rimuovi recensione");
						}
					}
				});
				
				verticalBox.add(Box.createRigidArea(new Dimension(0, 8)));
				
			}
		}else {
			
			JLabel lblNessunLuogo = new JLabel("  Nessuna recensione");
			lblNessunLuogo.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblNessunLuogo.setHorizontalTextPosition(SwingConstants.CENTER);
			verticalBox.add(Box.createRigidArea(new Dimension(0, 14)));
			verticalBox.add(lblNessunLuogo);
			
		}
		
	}

}