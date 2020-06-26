import java.awt.Font;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JEditorPane;
import java.awt.Color;

public class PanelScriviRecensione extends JPanel {
	
	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;

	public PanelScriviRecensione(MainController controller, MainFrame mainFrame, JPanel workPanel, Luogo l) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelLuogo = new JPanel();

		PanelInfoLuogoAnteprima infoLuogo = new PanelInfoLuogoAnteprima(controller, mainFrame, l, false, workPanel);
		
		JPanel panelRecensione = new JPanel();
		
		JLabel lblRecensione = new JLabel("Recensione");
		lblRecensione.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, 430, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelRecensione, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(344, Short.MAX_VALUE)
					.addComponent(lblRecensione)
					.addGap(330))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRecensione)
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(panelRecensione, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblVoto = new JLabel("Voto:");
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSpinner spinnerVoto = new JSpinner();
		spinnerVoto.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
		JEditorPane editorPaneScriviRecensione = new JEditorPane();
		editorPaneScriviRecensione.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblRecensioni = new JLabel("Scrivi la recensione:");
		lblRecensioni.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLasciaRecensione = new JButton("Lascia recensione");
		GroupLayout gl_panelRecensione = new GroupLayout(panelRecensione);
		gl_panelRecensione.setHorizontalGroup(
			gl_panelRecensione.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRecensione.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRecensione.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRecensione.createSequentialGroup()
							.addGroup(gl_panelRecensione.createParallelGroup(Alignment.LEADING)
								.addComponent(editorPaneScriviRecensione, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
								.addGroup(gl_panelRecensione.createSequentialGroup()
									.addComponent(lblVoto)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(spinnerVoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRecensioni))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelRecensione.createSequentialGroup()
							.addComponent(btnLasciaRecensione, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addGap(271))))
		);
		gl_panelRecensione.setVerticalGroup(
			gl_panelRecensione.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRecensione.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRecensione.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoto)
						.addComponent(spinnerVoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(lblRecensioni)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editorPaneScriviRecensione, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLasciaRecensione, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panelRecensione.setLayout(gl_panelRecensione);
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
		
	}
}
