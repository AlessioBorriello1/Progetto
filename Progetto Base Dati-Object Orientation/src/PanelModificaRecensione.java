import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class PanelModificaRecensione extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	
	/**
	 * Pannello modifica di una recensione
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param workPanel JPanel dove mostrare il pannello
	 * @param l Luogo che possiede la recensione da modificare
	 * @param r Recensione da modificare
	 */
	public PanelModificaRecensione(MainController controller, MainFrame mainFrame, JPanel workPanel, Luogo l, Recensione r) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(controller.skyWhiter);
		
		JPanel panelLuogo = new JPanel();

		PanelInfoLuogoAnteprima infoLuogo = new PanelInfoLuogoAnteprima(controller, mainFrame, l, false, workPanel);
		
		JPanel panelRecensione = new JPanel();
		panelRecensione.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelRecensione.setBackground(controller.sky);
		
		JLabel lblRecensione = new JLabel("La tua recensione");
		lblRecensione.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JLabel lblLuogo = new JLabel("Luogo");
		lblLuogo.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelRecensione, GroupLayout.PREFERRED_SIZE, 752, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(401, Short.MAX_VALUE)
					.addComponent(lblLuogo)
					.addGap(386))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(348, Short.MAX_VALUE)
					.addComponent(lblRecensione)
					.addGap(319))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(lblLuogo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelLuogo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblRecensione)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelRecensione, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblVoto = new JLabel("Voto:");
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSpinner spinnerVoto = new JSpinner();
		spinnerVoto.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerVoto.setValue(r.getVoto());
		((JSpinner.DefaultEditor) spinnerVoto.getEditor()).getTextField().setEditable(false);
		
		JEditorPane editorPaneScriviRecensione = new JEditorPane();
		editorPaneScriviRecensione.setText(r.getTesto());
		editorPaneScriviRecensione.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int lenght = editorPaneScriviRecensione.getText().length();
				//Limita il numero di caratteri nella recensione
				int maxLenght = 406;
				if(lenght > maxLenght) {
					if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE) {
						editorPaneScriviRecensione.setEditable(false);
					}else {
						editorPaneScriviRecensione.setEditable(true);
					}
				}
			}
		});
		editorPaneScriviRecensione.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		editorPaneScriviRecensione.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editorPaneScriviRecensione.setBackground(Color.WHITE);
		
		JLabel lblRecensioni = new JLabel("Scrivi la recensione:");
		lblRecensioni.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnModificaRecensione = new JButton("Modifica la recensione");
		btnModificaRecensione.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Recensione rNuova = new Recensione();
				//Prende informazioni della nuova recensione
				rNuova.setTesto(editorPaneScriviRecensione.getText());
				rNuova.setVoto(Integer.parseInt(spinnerVoto.getValue().toString()));
				//Modifica recensione
				if(controller.modificaRecensione(mainFrame, l, rNuova, r)) {
					//Se riuscito torna alla home
					mainFrame.cambiaPannelloLavoroAHomePanel(workPanel);
				}
			}
		});
		
		GroupLayout gl_panelRecensione = new GroupLayout(panelRecensione);
		gl_panelRecensione.setHorizontalGroup(
			gl_panelRecensione.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRecensione.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRecensione.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelRecensione.createSequentialGroup()
							.addComponent(lblVoto)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerVoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(662, Short.MAX_VALUE))
						.addGroup(gl_panelRecensione.createSequentialGroup()
							.addComponent(lblRecensioni)
							.addContainerGap(579, Short.MAX_VALUE))
						.addGroup(gl_panelRecensione.createSequentialGroup()
							.addComponent(editorPaneScriviRecensione, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_panelRecensione.createSequentialGroup()
					.addGap(285)
					.addComponent(btnModificaRecensione, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(287, Short.MAX_VALUE))
		);
		gl_panelRecensione.setVerticalGroup(
			gl_panelRecensione.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRecensione.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRecensione.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoto)
						.addComponent(spinnerVoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRecensioni)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorPaneScriviRecensione, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnModificaRecensione, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
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
