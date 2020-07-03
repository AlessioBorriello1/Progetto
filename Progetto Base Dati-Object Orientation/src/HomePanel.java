import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;
	private List<Luogo> ricerca; //Array list che contiene la lista dei luoghi in base alla ricerca effettuata

	public HomePanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		//Pannello appena aperto, effettua la prima ricerca, senza filtri
		LuogoDAO dao = new LuogoDAO();
		
		setBackground(controller.skyWhiter);
		
		JPanel panelRicerca = new JPanel();
		panelRicerca.setBackground(controller.steel);
		
		JScrollPane panelMostraLuoghi = new JScrollPane();
		panelMostraLuoghi.setBorder(null);
		panelMostraLuoghi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelMostraLuoghi.getVerticalScrollBar().setUnitIncrement(16);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelRicerca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
							.addGap(0)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addComponent(panelRicerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panelMostraLuoghi, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextField textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JTextField textFieldProprietario = new JTextField();
		textFieldProprietario.setColumns(10);
		
		JCheckBox chckbxRistorante = new JCheckBox("Ristorante");
		chckbxRistorante.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxRistorante.setSelected(true);
		
		JCheckBox chckbxAlloggio = new JCheckBox("Alloggio");
		chckbxAlloggio.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAlloggio.setSelected(true);
		
		JCheckBox chckbxAttrazione = new JCheckBox("Attrazione");
		chckbxAttrazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAttrazione.setSelected(true);
		
		JSpinner spinnerVotoMinimo = new JSpinner();
		spinnerVotoMinimo.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		
		JLabel lblVotoMinimo = new JLabel("Voto minimo");
		lblVotoMinimo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblOrdinaPer = new JLabel("Ordina per:");
		lblOrdinaPer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdinaPer.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox<String> comboBoxOrdine = new JComboBox<String>();
		comboBoxOrdine.addItem("Ordine alfabetico");
		comboBoxOrdine.addItem("Valutazione");
		comboBoxOrdine.addItem("Ordine creazione");
		
		JButton btnCerca = new JButton("Cerca");
		
		JCheckBox chckbxInvertiRisultati = new JCheckBox("Inverti risultati");
		chckbxInvertiRisultati.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNomeLuogo = new JLabel("Nome luogo");
		lblNomeLuogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblProprietario = new JLabel("Proprietario");
		lblProprietario.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panelRicerca = new GroupLayout(panelRicerca);
		gl_panelRicerca.setHorizontalGroup(
			gl_panelRicerca.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRicerca.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldNome, Alignment.TRAILING)
						.addComponent(textFieldProprietario, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeLuogo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProprietario, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRicerca.createSequentialGroup()
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxAttrazione)
								.addGroup(gl_panelRicerca.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxAlloggio)))
							.addGap(65)
							.addComponent(spinnerVotoMinimo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxOrdine, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxInvertiRisultati, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
							.addComponent(btnCerca))
						.addGroup(gl_panelRicerca.createSequentialGroup()
							.addComponent(chckbxRistorante)
							.addGap(44)
							.addComponent(lblVotoMinimo)
							.addGap(59)
							.addComponent(lblOrdinaPer)))
					.addContainerGap())
		);
		gl_panelRicerca.setVerticalGroup(
			gl_panelRicerca.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRicerca.createSequentialGroup()
					.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRicerca.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxRistorante, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblVotoMinimo)
								.addComponent(lblOrdinaPer))
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRicerca.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
									.addComponent(btnCerca))
								.addGroup(gl_panelRicerca.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelRicerca.createParallelGroup(Alignment.BASELINE)
											.addComponent(comboBoxOrdine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(spinnerVotoMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(chckbxAlloggio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(1)
									.addGroup(gl_panelRicerca.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxAttrazione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(chckbxInvertiRisultati))
									.addGap(1))))
						.addGroup(gl_panelRicerca.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNomeLuogo))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelRicerca.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldProprietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProprietario))))
					.addContainerGap())
		);
		panelRicerca.setLayout(gl_panelRicerca);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(true);
		verticalBox.setBackground(controller.skyWhiter);
		panelMostraLuoghi.setViewportView(verticalBox);
		setLayout(groupLayout);
		
		ricerca = dao.getListaTuttiLuoghi();
		btnCerca.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				LuogoDAO dao = new LuogoDAO();
				ricerca = dao.faiRicerca(textFieldProprietario.getText(), textFieldNome.getText(),
						 (chckbxRistorante.isSelected())? "Ristorante":null, (chckbxAlloggio.isSelected())? "Alloggio":null, (chckbxAttrazione.isSelected())? "Attrazione":null,
						 Integer.parseInt(spinnerVotoMinimo.getValue().toString()), comboBoxOrdine.getSelectedItem().toString(), chckbxInvertiRisultati.isSelected());
				
				verticalBox.removeAll();
				verticalBox.repaint();
				verticalBox.revalidate();
				if(!ricerca.isEmpty()) {
					for(Luogo l : ricerca) {
						PanelInfoLuogoAnteprima panel = new PanelInfoLuogoAnteprima(controller, mainFrame, l, true, workPanel);
						verticalBox.add(panel);
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
		});
		
		if(!ricerca.isEmpty()) {
			for(Luogo l : ricerca) {
				PanelInfoLuogoAnteprima panel = new PanelInfoLuogoAnteprima(controller, mainFrame, l, true, workPanel);
				verticalBox.add(panel);
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
