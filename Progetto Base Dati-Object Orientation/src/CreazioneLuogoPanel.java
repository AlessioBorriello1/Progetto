import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.border.LineBorder;

public class CreazioneLuogoPanel extends JPanel {

	MainController controller;
	MainFrame mainFrame;
	JPanel workPanel;
	JPanel currentImpostazioniAggiuntivePanel = null;
	
	public CreazioneLuogoPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setBackground(Color.LIGHT_GRAY);
		
		JTextField textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JTextField textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		
		JTextField textFieldNomePropretario = new JTextField();
		textFieldNomePropretario.setColumns(10);
		
		JLabel lblNomeLuogo = new JLabel("Nome luogo:");
		lblNomeLuogo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeLuogo.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNomeProprietario = new JLabel("Nome proprietario:");
		lblNomeProprietario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeProprietario.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox comboBoxSpecializzazione = new JComboBox();
		comboBoxSpecializzazione.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		
		JComboBox comboBoxTipoAttivita = new JComboBox();
		comboBoxTipoAttivita.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		comboBoxTipoAttivita.addItem("Ristorante");
		comboBoxTipoAttivita.addItem("Alloggio");
		comboBoxTipoAttivita.addItem("Attrazione");
		refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
		
		JLabel labelTipoAttivita = new JLabel("Tipo attivit\u00E0:");
		labelTipoAttivita.setHorizontalAlignment(SwingConstants.TRAILING);
		labelTipoAttivita.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel labelSpecializzazione = new JLabel("Specializzazione:");
		labelSpecializzazione.setHorizontalAlignment(SwingConstants.TRAILING);
		labelSpecializzazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panelAttributiAggiuntivi = new JPanel();
		panelAttributiAggiuntivi.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefono, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(lblIndirizzo, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(lblNomeLuogo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeProprietario, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTipoAttivita, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSpecializzazione, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxTipoAttivita, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldNomePropretario, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(comboBoxSpecializzazione, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomeLuogo)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomeProprietario, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNomePropretario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxTipoAttivita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTipoAttivita, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxSpecializzazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSpecializzazione, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addGap(223))
		);
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0));
		
		JPanel panelImpostazioniAggiuntivePizzeria = new JPanel();
		currentImpostazioniAggiuntivePanel = panelImpostazioniAggiuntivePizzeria;
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntivePizzeria, "panelImpostazioniAggiuntivePizzeria");
		panelImpostazioniAggiuntivePizzeria.setName("panelImpostazioniAggiuntivePizzeria");
		
		JLabel lblImpostazioniAggiuntivePizzeria = new JLabel("Impostazioni aggiuntive pizzeria:");
		lblImpostazioniAggiuntivePizzeria.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntivePizzeria = new GroupLayout(panelImpostazioniAggiuntivePizzeria);
		gl_panelImpostazioniAggiuntivePizzeria.setHorizontalGroup(
			gl_panelImpostazioniAggiuntivePizzeria.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntivePizzeria.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntivePizzeria, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelImpostazioniAggiuntivePizzeria.setVerticalGroup(
			gl_panelImpostazioniAggiuntivePizzeria.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntivePizzeria.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntivePizzeria)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntivePizzeria.setLayout(gl_panelImpostazioniAggiuntivePizzeria);
		
		JPanel panelImpostazioniAggiuntiveBraceria = new JPanel();
		panelImpostazioniAggiuntiveBraceria.setEnabled(false);
		panelImpostazioniAggiuntiveBraceria.setVisible(false);
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveBraceria, "panelImpostazioniAggiuntiveBraceria");
		panelImpostazioniAggiuntiveBraceria.setName("panelImpostazioniAggiuntiveBraceria");
		
		JLabel lblImpostazioniAggiuntiveBraceria = new JLabel("Impostazioni aggiuntive braceria:");
		lblImpostazioniAggiuntiveBraceria.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveBraceria = new GroupLayout(panelImpostazioniAggiuntiveBraceria);
		gl_panelImpostazioniAggiuntiveBraceria.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveBraceria.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveBraceria.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBraceria, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelImpostazioniAggiuntiveBraceria.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveBraceria.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveBraceria.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBraceria, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveBraceria.setLayout(gl_panelImpostazioniAggiuntiveBraceria);
		
		JPanel panelImpostazioniAggiuntiveRistorante = new JPanel();
		panelImpostazioniAggiuntiveRistorante.setEnabled(false);
		panelImpostazioniAggiuntiveRistorante.setVisible(false);
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveRistorante, "panelImpostazioniAggiuntiveRistorante");
		panelImpostazioniAggiuntiveRistorante.setName("panelImpostazioniAggiuntiveRistorante");
		
		JLabel lblImpostazioniAggiuntiveRistorante = new JLabel("Impostazioni aggiuntive ristorante:");
		lblImpostazioniAggiuntiveRistorante.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveRistorante = new GroupLayout(panelImpostazioniAggiuntiveRistorante);
		gl_panelImpostazioniAggiuntiveRistorante.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveRistorante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveRistorante.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveRistorante, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelImpostazioniAggiuntiveRistorante.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveRistorante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveRistorante.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveRistorante, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveRistorante.setLayout(gl_panelImpostazioniAggiuntiveRistorante);
		
		JPanel panelNessunaImpostazioneAggiuntiva = new JPanel();
		panelNessunaImpostazioneAggiuntiva.setEnabled(false);
		panelNessunaImpostazioneAggiuntiva.setVisible(false);
		panelNessunaImpostazioneAggiuntiva.setName("panelNessunaImpostazioneAggiuntiva");
		
		JPanel panelImpostazioniAggiuntiveHotel = new JPanel();
		panelImpostazioniAggiuntiveHotel.setEnabled(false);
		panelImpostazioniAggiuntiveHotel.setVisible(false);
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveHotel, "panelImpostazioniAggiuntiveHotel");
		panelImpostazioniAggiuntiveHotel.setName("panelImpostazioniAggiuntiveHotel");
		
		JLabel lblImpostazioniAggiuntiveHotel = new JLabel("Impostazioni aggiuntive hotel:");
		lblImpostazioniAggiuntiveHotel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveHotel = new GroupLayout(panelImpostazioniAggiuntiveHotel);
		gl_panelImpostazioniAggiuntiveHotel.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveHotel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveHotel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveHotel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelImpostazioniAggiuntiveHotel.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveHotel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveHotel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveHotel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveHotel.setLayout(gl_panelImpostazioniAggiuntiveHotel);
		
		JPanel panelImpostazioniAggiuntiveBB = new JPanel();
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveBB, "panelImpostazioniAggiuntiveBB");
		panelImpostazioniAggiuntiveBB.setName("panelImpostazioniAggiuntiveBB");
		
		JLabel lblImpostazioniAggiuntiveBB = new JLabel("Impostazioni aggiuntive B&B:");
		lblImpostazioniAggiuntiveBB.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveBB = new GroupLayout(panelImpostazioniAggiuntiveBB);
		gl_panelImpostazioniAggiuntiveBB.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveBB.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveBB.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBB, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_panelImpostazioniAggiuntiveBB.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveBB.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveBB.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBB, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveBB.setLayout(gl_panelImpostazioniAggiuntiveBB);
		
		JPanel panelImpostazioniAggiuntiveMotel = new JPanel();
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveMotel, "panelImpostazioniAggiuntiveMotel");
		panelImpostazioniAggiuntiveMotel.setName("panelImpostazioniAggiuntiveMotel");
		
		JLabel lblImpostazioniAggiuntiveMotel = new JLabel("Impostazioni aggiuntive motel:");
		lblImpostazioniAggiuntiveMotel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveMotel = new GroupLayout(panelImpostazioniAggiuntiveMotel);
		gl_panelImpostazioniAggiuntiveMotel.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveMotel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveMotel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveMotel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_panelImpostazioniAggiuntiveMotel.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveMotel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveMotel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveMotel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveMotel.setLayout(gl_panelImpostazioniAggiuntiveMotel);
		
		JPanel panelImpostazioniAggiuntiveIntrattenimento = new JPanel();
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveIntrattenimento, "panelImpostazioniAggiuntiveIntrattenimento");
		panelImpostazioniAggiuntiveIntrattenimento.setName("panelImpostazioniAggiuntiveIntrattenimento");
		
		JLabel lblImpostazioniAggiuntiveIntrattenimento = new JLabel("Impostazioni aggiuntive intrattenimento:");
		lblImpostazioniAggiuntiveIntrattenimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveIntrattenimento = new GroupLayout(panelImpostazioniAggiuntiveIntrattenimento);
		gl_panelImpostazioniAggiuntiveIntrattenimento.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveIntrattenimento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveIntrattenimento.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveIntrattenimento, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelImpostazioniAggiuntiveIntrattenimento.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveIntrattenimento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveIntrattenimento.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveIntrattenimento, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveIntrattenimento.setLayout(gl_panelImpostazioniAggiuntiveIntrattenimento);
		
		JPanel panelImpostazioniAggiuntiveCulturale = new JPanel();
		panelAttributiAggiuntivi.add(panelImpostazioniAggiuntiveCulturale, "panelImpostazioniAggiuntiveCulturale");
		panelAttributiAggiuntivi.setName("panelImpostazioniAggiuntiveCulturale");
		
		JLabel lblImpostazioniAggiuntiveCulturale = new JLabel("Impostazioni aggiuntive culturale:");
		lblImpostazioniAggiuntiveCulturale.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelImpostazioniAggiuntiveCulturale = new GroupLayout(panelImpostazioniAggiuntiveCulturale);
		gl_panelImpostazioniAggiuntiveCulturale.setHorizontalGroup(
			gl_panelImpostazioniAggiuntiveCulturale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveCulturale.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveCulturale, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelImpostazioniAggiuntiveCulturale.setVerticalGroup(
			gl_panelImpostazioniAggiuntiveCulturale.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpostazioniAggiuntiveCulturale.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveCulturale, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		panelImpostazioniAggiuntiveCulturale.setLayout(gl_panelImpostazioniAggiuntiveCulturale);
		panelAttributiAggiuntivi.add(panelNessunaImpostazioneAggiuntiva, "name_304077949539800");
		
		JLabel lblNessunaImpostazioneAggiuntiva = new JLabel("Nessuna impostazione aggiuntiva");
		lblNessunaImpostazioneAggiuntiva.setHorizontalAlignment(SwingConstants.CENTER);
		lblNessunaImpostazioneAggiuntiva.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelNessunaImpostazioneAggiuntiva = new GroupLayout(panelNessunaImpostazioneAggiuntiva);
		gl_panelNessunaImpostazioneAggiuntiva.setHorizontalGroup(
			gl_panelNessunaImpostazioneAggiuntiva.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNessunaImpostazioneAggiuntiva.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNessunaImpostazioneAggiuntiva, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelNessunaImpostazioneAggiuntiva.setVerticalGroup(
			gl_panelNessunaImpostazioneAggiuntiva.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNessunaImpostazioneAggiuntiva.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNessunaImpostazioneAggiuntiva, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelNessunaImpostazioneAggiuntiva.setLayout(gl_panelNessunaImpostazioneAggiuntiva);
		setLayout(groupLayout);
	
		controller.getAllComponents(this);
	}
	
	public void refreshaComboBoxSpecializzazione(JComboBox box, int index) {
		
		box.removeAllItems();
		
		switch(index) {
		case 0:{
			box.addItem("Pizzeria");
			box.addItem("Braceria");
			box.addItem("Ristorante");
		}break;
		case 1:{
			box.addItem("Hotel");
			box.addItem("Motel");
			box.addItem("B&B");
		}break;
		case 2:{
			box.addItem("Intrattenimento");
			box.addItem("Culturale");
		}break;
		default:{
			box.addItem("Pizzeria");
			box.addItem("Braceria");
			box.addItem("Ristorante");
		}break;
		}
		
	}

	public JPanel refreshaPannelloImpostazioniAggiuntive(JComboBox box, int index, JPanel currentPanel) {
		
		String item = box.getItemAt(index).toString(); //Crea stringa con la selezione della combobox della specializzazione
		
		switch(item) { //In base a cosa è selezionato
		case "Pizzeria":{ //Se pizzeria è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntivePizzeria");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
			
		}break;
		case "Braceria":{ //Se braceria è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveBraceria");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Ristorante":{ //Se ristorante è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveRistorante");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Hotel":{ //Se hotel è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveHotel");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "B&B":{ //Se B&B è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveBB");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Motel":{ //Se motel è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveMotel");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Intrattenimento":{ //Se intrattenimento è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveIntrattenimento");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Culturale":{ //Se culturale è selezionato
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntiveCulturale");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		default:{ //Altrimenti
			JPanel panel = (JPanel)controller.getComponentByName(this, "panelImpostazioniAggiuntivePizzeria");
			if(currentPanel != null && currentPanel != panel) {
				currentPanel.setVisible(false); //Rendi il pannello attuale invisibile
				currentPanel.setEnabled(false); //Rendi il pannello attuale inattivo
				
				panel.setVisible(true); //Rendi il nuovo pannello visibile
				panel.setEnabled(true); //Rendi il nuovo pannello attivo
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		}
		
		return currentPanel; //Aggiorna il pannello attuale
		
	}

}
