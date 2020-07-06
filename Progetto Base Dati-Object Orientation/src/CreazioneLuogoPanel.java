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
import javax.swing.border.MatteBorder;

public class CreazioneLuogoPanel extends JPanel {

	private MainController controller; //Controller principale
	private MainFrame mainFrame; //Frame che ha creato il pannello
	private JPanel workPanel; //Pannello con informazioni sul luogo da creare (fisso)
	private JPanel currentImpostazioniAggiuntivePanel = null; //Pannello con informazioni aggiuntive sul luogo da creare (in base al tipo)
	JComboBox comboBoxSpecializzazione;
	
	/**
	 * Pannello per la creazione di un nuovo luogo
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param workPanel JPanel dove mostrare il pannello
	 */
	public CreazioneLuogoPanel(MainController controller, MainFrame mainFrame, JPanel workPanel) {
		
		this.controller = controller; //Collega controller
		this.mainFrame = mainFrame; //Collega mainFrame
		this.workPanel = workPanel; //Collega workPanel
		
		setBackground(controller.skyWhiter); //Imposta colore
		
		JPanel panelAttributiAggiuntivi = new JPanel(); //Pannello attributi aggiuntivi (quello dinamico)
		panelAttributiAggiuntivi.setBackground(controller.sky);
		panelAttributiAggiuntivi.setBorder(new MatteBorder(2, 0, 2, 2, controller.independence));
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		
		JPanel panelDati = new JPanel();
		panelDati.setBorder(new MatteBorder(2, 2, 2, 0, controller.independence));
		panelDati.setBackground(controller.sky);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new LineBorder(controller.independence, 2));
		upperPanel.setBackground(controller.steel);
		
		//Allineamento orizzontale
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(upperPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panelDati, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(0)
							.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(101)
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDati, 0, 0, Short.MAX_VALUE)
						.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addGap(154))
		);
		
		JLabel lblCreaLuogo = new JLabel("Crea luogo");
		lblCreaLuogo.setFont(new Font("Georgia", Font.BOLD, 30));
		lblCreaLuogo.setForeground(controller.electric);
		GroupLayout gl_upperPanel = new GroupLayout(upperPanel);
		gl_upperPanel.setHorizontalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addComponent(lblCreaLuogo, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(516, Short.MAX_VALUE))
		);
		gl_upperPanel.setVerticalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addComponent(lblCreaLuogo)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		upperPanel.setLayout(gl_upperPanel);
		
		JLabel lblNomeLuogo = new JLabel("Nome luogo:"); //Label nome luogo
		lblNomeLuogo.setFont(new Font("Georgia", Font.BOLD, 16));
		lblNomeLuogo.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:"); //Label indirizzo
		lblIndirizzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIndirizzo.setFont(new Font("Georgia", Font.BOLD, 16));
		
		JLabel lblTelefono = new JLabel("Telefono (10 cifre):"); //Labl telefono
		lblTelefono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelefono.setFont(new Font("Georgia", Font.BOLD, 16));
		
		JLabel lblNomeProprietario = new JLabel("Nome proprietario:"); //Label nome propretario
		lblNomeProprietario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeProprietario.setFont(new Font("Georgia", Font.BOLD, 16));
		
		JLabel labelTipoAttivita = new JLabel("Tipo attivit\u00E0:"); //Label tipo attività
		labelTipoAttivita.setHorizontalAlignment(SwingConstants.TRAILING);
		labelTipoAttivita.setFont(new Font("Georgia", Font.BOLD, 16));
		
		JLabel labelSpecializzazione = new JLabel("Specializzazione:"); //Label specializzazione
		labelSpecializzazione.setHorizontalAlignment(SwingConstants.TRAILING);
		labelSpecializzazione.setFont(new Font("Georgia", Font.BOLD, 16));
		
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
		
		JComboBox comboBoxTipoAttivita = new JComboBox(); //ComboBox tipo attività (ristorante, alloggio, attrazione)
		comboBoxTipoAttivita.setFont(new Font("Georgia", Font.PLAIN, 11));
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
		
		comboBoxSpecializzazione = new JComboBox(); //ComboBox specializzazione (pizzeria, braceria, pub, hotel, motel....)
		comboBoxSpecializzazione.setFont(new Font("Georgia", Font.PLAIN, 11));
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
		//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attività
		refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
		
		//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox alla creazione del pannello
		currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel);
		
		JButton btnCreaLuogo = new JButton("Crea luogo");
		btnCreaLuogo.setFont(new Font("Georgia", Font.PLAIN, 13));
		
			btnCreaLuogo.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) { //Mouse cliccato
					//Funzione crea luogo del controller, passa le informazioni scelte dall'utente nei vari pannelli
					if(mainFrame.createConfirmationFrame("Creare luogo con queste informazioni?")) {
						Luogo l = new Luogo();
						l.setNome(textFieldNome.getText());
						l.setIndirizzo(textFieldIndirizzo.getText());
						l.setTelefono(textFieldTelefono.getText());
						l.setProprietario(textFieldNomePropretario.getText());
						l.setTipoAttivita(comboBoxTipoAttivita.getSelectedItem().toString());
						l.setAttributoAttivita(comboBoxSpecializzazione.getSelectedItem().toString());
						controller.creaLuogo(mainFrame, l, currentImpostazioniAggiuntivePanel);
					}
				}
			});
		GroupLayout gl_panelDati = new GroupLayout(panelDati);
		gl_panelDati.setHorizontalGroup(
			gl_panelDati.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDati.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDati.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCreaLuogo, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblNomeLuogo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblNomeProprietario, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldNomePropretario, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(labelTipoAttivita, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxTipoAttivita, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(labelSpecializzazione, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxSpecializzazione, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelDati.setVerticalGroup(
			gl_panelDati.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDati.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeLuogo)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIndirizzo)
						.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeProprietario)
						.addComponent(textFieldNomePropretario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelTipoAttivita)
						.addComponent(comboBoxTipoAttivita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDati.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelSpecializzazione)
						.addComponent(comboBoxSpecializzazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(btnCreaLuogo)
					.addContainerGap())
		);
		panelDati.setLayout(gl_panelDati);
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0)); //Imposta layout pannello attributi aggiuntivi a cardLayout
		setLayout(groupLayout);
		
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
			PanelImpostazioniAggiuntivePizzeria panel = new PanelImpostazioniAggiuntivePizzeria(false, "Italiano", false);
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
		}break;
		case "Braceria":{ //Se braceria è selezionato
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
		}break;
		case "Pub":{ //Se ristorante è selezionato
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
		}break;
		case "Hotel":{ //Se hotel è selezionato
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
		}break;
		case "B&B":{ //Se B&B è selezionato
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
		}break;
		case "Motel":{ //Se motel è selezionato
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
		}break;
		case "Museo":{ //Se intrattenimento è selezionato
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
		}break;
		case "Zoo":{ //Se culturale è selezionato
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
		}break;
		case "Parco":{ //Se culturale è selezionato
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
		}break;
		}
		
		return currentPanel; //Aggiorna il pannello attuale
		
	}
}
