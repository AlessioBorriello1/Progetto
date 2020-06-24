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
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

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
		textFieldTelefono.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String s = textFieldTelefono.getText();
				int l = s.length();
				char c = e.getKeyChar();
				
				if(c >= '0' && c <= '9') {
					if(l < 10) {
						textFieldTelefono.setEditable(true);
					}else {
						textFieldTelefono.setEditable(false);
					}
				}else {
					if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
						textFieldTelefono.setEditable(true);
					}else {
						textFieldTelefono.setEditable(false);
					}
				}
			}
		});
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
		panelAttributiAggiuntivi.setBackground(Color.LIGHT_GRAY);
		panelAttributiAggiuntivi.setBorder(null);
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		
		JButton btnCreaLuogo = new JButton("Crea luogo");
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
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
								.addComponent(comboBoxSpecializzazione, 0, 207, Short.MAX_VALUE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCreaLuogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelAttributiAggiuntivi, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 338, Short.MAX_VALUE))
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
					.addGap(44)
					.addComponent(btnCreaLuogo)
					.addGap(153))
		);
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0));
		setLayout(groupLayout);
	
		btnCreaLuogo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.creaLuogo(mainFrame, textFieldNome.getText(), textFieldIndirizzo.getText(), textFieldTelefono.getText(), textFieldNomePropretario.getText(), comboBoxTipoAttivita.getSelectedItem().toString(), comboBoxSpecializzazione.getSelectedItem().toString(), currentImpostazioniAggiuntivePanel);
			}
		});
		
		currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
		
		
	}
	
	public void refreshaComboBoxSpecializzazione(JComboBox box, int index) {
		
		box.removeAllItems();
		
		switch(index) {
		case 0:{
			box.addItem("Pizzeria");
			box.addItem("Braceria");
			box.addItem("Pub");
		}break;
		case 1:{
			box.addItem("Hotel");
			box.addItem("Motel");
			box.addItem("B&B");
		}break;
		case 2:{
			box.addItem("Museo");
			box.addItem("Zoo");
			box.addItem("Parco");
		}break;
		default:{
			box.addItem("Pizzeria");
			box.addItem("Braceria");
			box.addItem("Pub");
		}break;
		}
		
	}

	/**
	 * Ricarica il pannello delle impostazioni aggiuntive per mostrare le corrette opzioni
	 * @param box comboBox delle specializzazioni
	 * @param index index della specializzazione scelta
	 * @param currentPanel pannello che si sta mostrando attualmente come pannello delle impostazioni aggiuntive
	 * @return nuovo pannello impostazioni aggiuntive appena settato
	 */
	public JPanel refreshaPannelloImpostazioniAggiuntive(JComboBox box, int index, JPanel currentPanel) {
		
		String item = box.getItemAt(index).toString(); //Crea stringa con la selezione della combobox della specializzazione
		
		if(currentPanel == null) {
			PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria();
			JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
			basePanel.removeAll();
			basePanel.repaint();
			basePanel.revalidate();
			
			basePanel.add(panel);
			basePanel.repaint();
			basePanel.revalidate();
			
			currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			return currentPanel;
		}
		
		switch(item) { //In base a cosa è selezionato
		case "Pizzeria":{ //Se pizzeria è selezionato
			PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Braceria":{ //Se braceria è selezionato
			PanelImpostazioniAggiuntiveBracieria panel = new PanelImpostazioniAggiuntiveBracieria();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Pub":{ //Se ristorante è selezionato
			PanelImpostazioniAggiuntivePub panel = new PanelImpostazioniAggiuntivePub();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Hotel":{ //Se hotel è selezionato
			PanelImpostazioniAggiuntiveHotel panel = new PanelImpostazioniAggiuntiveHotel();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "B&B":{ //Se B&B è selezionato
			PanelImpostazioniAggiuntiveBB panel = new PanelImpostazioniAggiuntiveBB();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Motel":{ //Se motel è selezionato
			PanelImpostazioniAggiuntiveMotel panel = new PanelImpostazioniAggiuntiveMotel();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Museo":{ //Se intrattenimento è selezionato
			PanelImpostazioniAggiuntiveMuseo panel = new PanelImpostazioniAggiuntiveMuseo();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Zoo":{ //Se culturale è selezionato
			PanelImpostazioniAggiuntiveZoo panel = new PanelImpostazioniAggiuntiveZoo();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		case "Parco":{ //Se culturale è selezionato
			PanelImpostazioniAggiuntiveParco panel = new PanelImpostazioniAggiuntiveParco();
			if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
				JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
				
				basePanel.removeAll();
				basePanel.repaint();
				
				basePanel.add(panel);
				basePanel.repaint();
				basePanel.revalidate();
				
				currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
			}
		}break;
		}
		
		return currentPanel; //Aggiorna il pannello attuale
		
	}
}
