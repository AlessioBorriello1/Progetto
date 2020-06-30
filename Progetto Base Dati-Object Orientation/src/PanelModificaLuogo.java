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

public class PanelModificaLuogo extends JPanel {

	private MainController controller; //Controller principale
	private MainFrame mainFrame; //Frame che ha creato il pannello
	private JPanel workPanel; //Pannello con informazioni sul luogo da creare (fisso)
	private JPanel currentImpostazioniAggiuntivePanel = null; //Pannello con informazioni aggiuntive sul luogo da creare (in base al tipo)
	
	public PanelModificaLuogo(MainController controller, MainFrame mainFrame, JPanel workPanel, Luogo l) {
		
		this.controller = controller; //Collega controller
		this.mainFrame = mainFrame; //Collega mainFrame
		this.workPanel = workPanel; //Collega workPanel
		
		setBackground(controller.skyWhiter); //Imposta colore
		
		JTextField textFieldNome = new JTextField(l.getNome()); //Nuovo textField Nome
		textFieldNome.setColumns(10);
		
		JTextField textFieldIndirizzo = new JTextField(l.getIndirizzo()); //Nuovo textField Indirizzo
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldTelefono = new JTextField(l.getTelefono()); //Nuovo textField Telefono
		textFieldTelefono.addKeyListener(new KeyAdapter() {
			//Tasto premuto
			public void keyPressed(KeyEvent e) {
				String s = textFieldTelefono.getText(); //Ottieni stringa
				int l = s.length(); //Ottieni lunghezza string
				char c = e.getKeyChar(); //Ottieni il tasto premuto
				
				if(c >= '0' && c <= '9') { //Se il tasto premuto � un numero (tra 0 e 9)
					if(l < 10) { //Se la stringa � pi� corta di 10 caratteri (numeri)
						textFieldTelefono.setEditable(true); //Imposta campo come editabile
					}else { //Se la stringa � pi� lunga di 10 caratteri (numeri)
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
		
		JTextField textFieldNomePropretario = new JTextField(l.getProprietario()); //Nuovo textField Nome proprietario
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
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel, l);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		
		JComboBox comboBoxTipoAttivita = new JComboBox(); //ComboBox tipo attivit� (ristorante, alloggio, attrazione)
		comboBoxTipoAttivita.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { //Quando il popup menu della combobox scompare
				//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attivit�
				refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
				//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox
				currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel, l);
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
		});
		comboBoxTipoAttivita.addItem("Ristorante"); //Aggiungo Ristorante alla combo box
		comboBoxTipoAttivita.addItem("Alloggio"); //Aggiungo Alloggio alla combo box
		comboBoxTipoAttivita.addItem("Attrazione"); //Aggiungo Attrazione alla combo box
		comboBoxTipoAttivita.setSelectedItem(l.getTipoAttivita());
		//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attivit�
		refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
		comboBoxSpecializzazione.setSelectedItem(l.getAttributoAttivita());
		
		JLabel labelTipoAttivita = new JLabel("Tipo attivit\u00E0:"); //Label tipo attivit�
		labelTipoAttivita.setHorizontalAlignment(SwingConstants.TRAILING);
		labelTipoAttivita.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel labelSpecializzazione = new JLabel("Specializzazione:"); //Label specializzazione
		labelSpecializzazione.setHorizontalAlignment(SwingConstants.TRAILING);
		labelSpecializzazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panelAttributiAggiuntivi = new JPanel(); //Pannello attributi aggiuntivi (quello dinamico)
		panelAttributiAggiuntivi.setBackground(controller.sky);
		panelAttributiAggiuntivi.setBorder(null);
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		
		JButton btnModificaLuogo = new JButton("Modifica luogo"); //Crea bottone
		
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
						.addComponent(btnModificaLuogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
					.addComponent(btnModificaLuogo)
					.addGap(153))
		);
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0)); //Imposta layout pannello attributi aggiuntivi a cardLayout
		setLayout(groupLayout);
	
		btnModificaLuogo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { //Mouse cliccato
				//Funzione crea luogo del controller, passa le informazioni scelte dall'utente nei vari pannelli
				if(mainFrame.createConfirmationFrame("Modificare luogo con queste nuove informazioni?")) {
					System.out.println("Modifica");
				}
			}
		});
		
		//Inizializza currentImpostazioniAggiuntivePanel a PanelImpostazioniAggiuntivePizzeria (di base, poi controlla effettivamente la selezione)
		JPanel panel = new JPanel();
		currentImpostazioniAggiuntivePanel = panel;
		
		//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox alla creazione del pannello
		currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel, l);
		
		
	}
	/**
	 * Ricarica combo box che mostra le specializzazioni in base al tipo di attivit� selezionata
	 * @param box La comboBox da ricaricare
	 * @param index Indice dell'elemento selezionato nella combo box tipo attivit�
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
	public JPanel refreshaPannelloImpostazioniAggiuntive(JComboBox box, int index, JPanel currentPanel, Luogo l) {
		
		String item = box.getItemAt(index).toString(); //Crea stringa con la selezione della combobox della specializzazione
		
		if(currentPanel == null) {
			Pizzeria p = (Pizzeria)l;
			PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria(p.isVegano(), p.getNazionalitaCibo(), p.isAsporto());
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
		
		switch(item) { //In base a cosa � selezionato
		case "Pizzeria":{ //Se pizzeria � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Pizzeria p = (Pizzeria)l;
				PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria(p.isVegano(), p.getNazionalitaCibo(), p.isAsporto());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria(false, "Italiano", false);
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Braceria":{ //Se braceria � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Braceria b = (Braceria)l;
				PanelImpostazioniAggiuntiveBracieria panel = new PanelImpostazioniAggiuntiveBracieria(b.isVegano(), b.getNazionalitaCibo(), b.getTipoCarne());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveBracieria panel = new PanelImpostazioniAggiuntiveBracieria(false, "Italiano", "Pollo");
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Pub":{ //Se ristorante � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Pub p = (Pub)l;
				PanelImpostazioniAggiuntivePub panel = new PanelImpostazioniAggiuntivePub(p.isVegano(), p.getNazionalitaCibo(), p.getTipoBirra());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntivePub panel = new PanelImpostazioniAggiuntivePub(false, "Italiano", "Spina");
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Hotel":{ //Se hotel � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Hotel h = (Hotel)l;
				PanelImpostazioniAggiuntiveHotel panel = new PanelImpostazioniAggiuntiveHotel(h.isPiscina(), h.isWifi(), String.valueOf(h.getNumeroStanze()), h.getNumeroStelle());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveHotel panel = new PanelImpostazioniAggiuntiveHotel(false, false, "", 1);
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "B&B":{ //Se B&B � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				BB b = (BB)l;
				PanelImpostazioniAggiuntiveBB panel = new PanelImpostazioniAggiuntiveBB(b.isPiscina(), b.isWifi(), String.valueOf(b.getNumeroStanze()), b.isColazione());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveBB panel = new PanelImpostazioniAggiuntiveBB(false, false, "", false);
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Motel":{ //Se motel � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Motel m = (Motel)l;
				PanelImpostazioniAggiuntiveMotel panel = new PanelImpostazioniAggiuntiveMotel(m.isPiscina(), m.isWifi(), String.valueOf(m.getNumeroStanze()), m.isAssistenzaAuto());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveMotel panel = new PanelImpostazioniAggiuntiveMotel(false, false, "", false);
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Museo":{ //Se intrattenimento � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Museo m = (Museo)l;
				PanelImpostazioniAggiuntiveMuseo panel = new PanelImpostazioniAggiuntiveMuseo(m.isPromozioneStudenti(), m.getTipoMuseo());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveMuseo panel = new PanelImpostazioniAggiuntiveMuseo(false, "Artistico");
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Zoo":{ //Se culturale � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Zoo z = (Zoo)l;
				PanelImpostazioniAggiuntiveZoo panel = new PanelImpostazioniAggiuntiveZoo(z.isPromozioneStudenti(), z.getSpecie());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveZoo panel = new PanelImpostazioniAggiuntiveZoo(false, "Primati");
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		case "Parco":{ //Se culturale � selezionato
			if(currentPanel.getClass().toString().contentEquals("class javax.swing.JPanel")) {
				Parco p = (Parco)l;
				PanelImpostazioniAggiuntiveParco panel = new PanelImpostazioniAggiuntiveParco(p.isPromozioneStudenti(), p.isIngressoGratuito());
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}else {
				PanelImpostazioniAggiuntiveParco panel = new PanelImpostazioniAggiuntiveParco(false, false);
				if(currentPanel != null && !currentPanel.getClass().equals(panel.getClass())) {
					JPanel basePanel = (JPanel)controller.getComponentByName(this, "panelAttributiAggiuntivi");
					
					basePanel.removeAll();
					basePanel.repaint();
					
					basePanel.add(panel);
					basePanel.repaint();
					basePanel.revalidate();
					
					currentPanel = panel; //Imposta il pannello attuale al nuovo pannello
				}
			}
		}break;
		}
		
		return currentPanel; //Aggiorna il pannello attuale
		
	}
}