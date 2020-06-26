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

	private MainController controller; //Controller principale
	private MainFrame mainFrame; //Frame che ha creato il pannello
	private JPanel workPanel; //Pannello con informazioni sul luogo da creare (fisso)
	private JPanel currentImpostazioniAggiuntivePanel = null; //Pannello con informazioni aggiuntive sul luogo da creare (in base al tipo)
	
	public CreazioneLuogoPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller; //Collega controller
		this.mainFrame = mainFrame; //Collega mainFrame
		this.workPanel = workPanel; //Collega workPanel
		
		setBackground(controller.skyWhiter); //Imposta colore
		
		JTextField textFieldNome = new JTextField(); //Nuovo textField Nome
		textFieldNome.setColumns(10);
		
		JTextField textFieldIndirizzo = new JTextField(); //Nuovo textField Indirizzo
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldTelefono = new JTextField(); //Nuovo textField Telefono
		textFieldTelefono.addKeyListener(new KeyAdapter() {
			//Tasto premuto
			public void keyPressed(KeyEvent e) {
				String s = textFieldTelefono.getText(); //Ottieni stringa
				int l = s.length(); //Ottieni lunghezza string
				char c = e.getKeyChar(); //Ottieni il tasto premuto
				
				if(c >= '0' && c <= '9') { //Se il tasto premuto è un numero (tra 0 e 9)
					if(l < 10) { //Se la stringa è più corta di 10 caratteri (numeri)
						textFieldTelefono.setEditable(true); //Imposta campo come editabile
					}else { //Se la stringa è più lunga di 10 caratteri (numeri)
						textFieldTelefono.setEditable(false); ////Imposta campo come non editabile
					}
				}else { //Se non ho premuto un numero
					if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) { //Se ho premuto back space o delete
						textFieldTelefono.setEditable(true); //Imposta campo come editabile
					}else { //Se non ho premuto back space o delete
						textFieldTelefono.setEditable(false); //Imposta campo come non editabile
					}
				}
			}
		});
		textFieldTelefono.setColumns(10);
		
		JTextField textFieldNomePropretario = new JTextField(); //Nuovo textField Nome proprietario
		textFieldNomePropretario.setColumns(10);
		
		JLabel lblNomeLuogo = new JLabel("Nome luogo:"); //Label nome luogo
		lblNomeLuogo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeLuogo.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:"); //Label indirizzo
		lblIndirizzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblTelefono = new JLabel("Telefono (10 cifre):"); //Labl telefono
		lblTelefono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNomeProprietario = new JLabel("Nome proprietario:"); //Label nome propretario
		lblNomeProprietario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeProprietario.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox comboBoxSpecializzazione = new JComboBox(); //ComboBox specializzazione (pizzeria, braceria, pub, hotel, motel....)
		comboBoxSpecializzazione.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { //Quando il popup menu della combobox scompare
				//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		
		JComboBox comboBoxTipoAttivita = new JComboBox(); //ComboBox tipo attività (ristorante, alloggio, attrazione)
		comboBoxTipoAttivita.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { //Quando il popup menu della combobox scompare
				//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attività
				refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
				//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		comboBoxTipoAttivita.addItem("Ristorante"); //Aggiungo Ristorante alla combo box
		comboBoxTipoAttivita.addItem("Alloggio"); //Aggiungo Alloggio alla combo box
		comboBoxTipoAttivita.addItem("Attrazione"); //Aggiungo Attrazione alla combo box
		//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attività
		refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
		
		JLabel labelTipoAttivita = new JLabel("Tipo attivit\u00E0:"); //Label tipo attività
		labelTipoAttivita.setHorizontalAlignment(SwingConstants.TRAILING);
		labelTipoAttivita.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel labelSpecializzazione = new JLabel("Specializzazione:"); //Label specializzazione
		labelSpecializzazione.setHorizontalAlignment(SwingConstants.TRAILING);
		labelSpecializzazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panelAttributiAggiuntivi = new JPanel(); //Pannello attributi aggiuntivi (quello dinamico)
		panelAttributiAggiuntivi.setBackground(controller.sky);
		panelAttributiAggiuntivi.setBorder(null);
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		
		JButton btnCreaLuogo = new JButton("Crea luogo"); //Crea bottone
		
		//Allineamento orizzontale
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
		//Allineamento verticale
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
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0)); //Imposta layout pannello attributi aggiuntivi a cardLayout
		setLayout(groupLayout);
	
		btnCreaLuogo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { //Mouse cliccato
				//Funzione crea luogo del controller, passa le informazioni scelte dall'utente nei vari pannelli
				controller.creaLuogo(mainFrame, textFieldNome.getText(), textFieldIndirizzo.getText(), textFieldTelefono.getText(), textFieldNomePropretario.getText(), comboBoxTipoAttivita.getSelectedItem().toString(), comboBoxSpecializzazione.getSelectedItem().toString(), currentImpostazioniAggiuntivePanel);
			}
		});
		
		//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox alla creazione del pannello
		currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
		
		
	}
	/**
	 * Ricarica combo box che mostra le specializzazioni in base al tipo di attività selezionata
	 * @param box La comboBox da ricaricare
	 * @param index Indice dell'elemento selezionato nella combo box tipo attività
	 */
	public void refreshaComboBoxSpecializzazione(JComboBox box, int index) {
		
		box.removeAllItems(); //Rimuovi tutti gli attuali elementi dalla combo box
		
		switch(index) { //In base all'index
		case 0:{
			box.addItem("Pizzeria"); //Aggiungi specializzazioni ristorante
			box.addItem("Braceria");
			box.addItem("Pub");
		}break;
		case 1:{
			box.addItem("Hotel"); //Aggiungi specializzazioni alloggio
			box.addItem("Motel");
			box.addItem("B&B");
		}break;
		case 2:{
			box.addItem("Museo"); //Aggiungi specializzazioni attrazione
			box.addItem("Zoo");
			box.addItem("Parco");
		}break;
		default:{
			box.addItem("Pizzeria"); //Aggiungi specializzazioni ristorante
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
