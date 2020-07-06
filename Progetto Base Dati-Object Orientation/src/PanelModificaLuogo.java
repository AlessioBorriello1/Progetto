import java.awt.Color;
import java.awt.Component;

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

import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;

public class PanelModificaLuogo extends JPanel {

	private MainController controller; //Controller principale
	private MainFrame mainFrame; //Frame che ha creato il pannello
	private JPanel workPanel; //Pannello con informazioni sul luogo da creare (fisso)
	private JPanel currentImpostazioniAggiuntivePanel = null; //Pannello con informazioni aggiuntive sul luogo da creare (in base al tipo)
	
	/**
	 * Pannello modifica luogo, permette di modificare gli attributi di un certo luogo
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param workPanel JPanel dove mostrare il pannello
	 * @param l Luogo da modificare
	 */
	public PanelModificaLuogo(MainController controller, MainFrame mainFrame, JPanel workPanel, Luogo l) {
		
		this.controller = controller; //Collega controller
		this.mainFrame = mainFrame; //Collega mainFrame
		this.workPanel = workPanel; //Collega workPanel
		
		setBackground(controller.skyWhiter); //Imposta colore
		
		JPanel panelAttributiAggiuntivi = new JPanel(); //Pannello attributi aggiuntivi (quello dinamico)
		panelAttributiAggiuntivi.setBackground(controller.sky);
		panelAttributiAggiuntivi.setBorder(new MatteBorder(2, 2, 2, 2, controller.independence));
		panelAttributiAggiuntivi.setName("panelAttributiAggiuntivi");
		panelAttributiAggiuntivi.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel panelDati = new JPanel();
		panelDati.setBorder(new MatteBorder(2, 2, 2, 2, controller.independence));
		panelDati.setBackground(controller.sky);
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new LineBorder(controller.independence, 2));
		upperPanel.setBackground(controller.steel);
		
		JLabel lblModificaLuogo = new JLabel("Modifica luogo");
		lblModificaLuogo.setForeground((Color) null);
		lblModificaLuogo.setFont(new Font("Georgia", Font.BOLD, 30));
		GroupLayout gl_upperPanel = new GroupLayout(upperPanel);
		gl_upperPanel.setHorizontalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addComponent(lblModificaLuogo, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(464, Short.MAX_VALUE))
		);
		gl_upperPanel.setVerticalGroup(
			gl_upperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperPanel.createSequentialGroup()
					.addComponent(lblModificaLuogo)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		upperPanel.setLayout(gl_upperPanel);
		
		//Allineamento orizzontale
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelDati, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(0, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(106)
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelDati, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
							.addGap(149))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelAttributiAggiuntivi, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
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
		
		JTextField textFieldNome = new JTextField(l.getNome()); //Nuovo textField Nome
		textFieldNome.setFont(new Font("Georgia", Font.PLAIN, 11));
		textFieldNome.setColumns(10);
		
		JTextField textFieldIndirizzo = new JTextField(l.getIndirizzo()); //Nuovo textField Indirizzo
		textFieldIndirizzo.setFont(new Font("Georgia", Font.PLAIN, 11));
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldTelefono = new JTextField(l.getTelefono()); //Nuovo textField Telefono
		textFieldTelefono.setFont(new Font("Georgia", Font.PLAIN, 11));
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
		
		JTextField textFieldNomePropretario = new JTextField(l.getProprietario()); //Nuovo textField Nome proprietario
		textFieldNomePropretario.setFont(new Font("Georgia", Font.PLAIN, 11));
		textFieldNomePropretario.setColumns(10);
		
		
		JComboBox comboBoxSpecializzazione = new JComboBox(); //ComboBox specializzazione (pizzeria, braceria, pub, hotel, motel....)
		comboBoxSpecializzazione.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxSpecializzazione.setEnabled(false);
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
		comboBoxSpecializzazione.setSelectedItem(l.getAttributoAttivita());
		
		JComboBox<String> comboBoxTipoAttivita = new JComboBox(); //ComboBox tipo attività (ristorante, alloggio, attrazione)
		comboBoxTipoAttivita.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxTipoAttivita.setEnabled(false);
		comboBoxTipoAttivita.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				//Questo metodo deve esistere per forza
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { //Quando il popup menu della combobox scompare
				//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attività
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
		
		//Fai mostrare alla combobox specializzazione le corrette specializzazioni in base al tipo di attività
		refreshaComboBoxSpecializzazione(comboBoxSpecializzazione, comboBoxTipoAttivita.getSelectedIndex());
		
		//Imposta il pannello impostazioni aggiuntive in base all'elemento selezionato della combobox alla creazione del pannello
		currentImpostazioniAggiuntivePanel = refreshaPannelloImpostazioniAggiuntive(comboBoxSpecializzazione, comboBoxSpecializzazione.getSelectedIndex(), currentImpostazioniAggiuntivePanel, l);
		
		JButton btnModificaLuogo = new JButton("Modifica luogo");
		btnModificaLuogo.setFont(new Font("Georgia", Font.PLAIN, 13));
		
		btnModificaLuogo.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) { //Mouse cliccato
					//Funzione modifica luogo del controller, passa le informazioni scelte dall'utente nei vari pannelli
					if(mainFrame.createConfirmationFrame("Modificare luogo con queste nuove informazioni?")) {
						Luogo nuovoLuogo = new Luogo();
						nuovoLuogo.setNome(textFieldNome.getText());
						nuovoLuogo.setIndirizzo(textFieldIndirizzo.getText());
						nuovoLuogo.setProprietario(textFieldNomePropretario.getText());
						nuovoLuogo.setTelefono(textFieldTelefono.getText());
						
						controller.modificaLuogo(mainFrame, nuovoLuogo, l, panelAttributiAggiuntivi);
					}
				}
			});
		GroupLayout gl_panelDati = new GroupLayout(panelDati);
		gl_panelDati.setHorizontalGroup(
			gl_panelDati.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDati.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDati.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnModificaLuogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblNomeLuogo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldNome))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(lblNomeProprietario, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldNomePropretario, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(labelTipoAttivita, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxTipoAttivita, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelDati.createSequentialGroup()
							.addComponent(labelSpecializzazione, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxSpecializzazione, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(0, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(btnModificaLuogo)
					.addContainerGap())
		);
		panelDati.setLayout(gl_panelDati);
		panelAttributiAggiuntivi.setLayout(new CardLayout(0, 0)); //Imposta layout pannello attributi aggiuntivi a cardLayout
		setLayout(groupLayout);
		
		//Inizializza currentImpostazioniAggiuntivePanel a PanelImpostazioniAggiuntivePizzeria (di base, poi controlla effettivamente la selezione)
		JPanel panel = new JPanel();
		currentImpostazioniAggiuntivePanel = panel;
		
		
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
		
		switch(item) { //In base a cosa è selezionato
		case "Pizzeria":{ //Se pizzeria è selezionato
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
		}break;
		case "Braceria":{ //Se braceria è selezionato
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
		}break;
		case "Pub":{ //Se ristorante è selezionato
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
		}break;
		case "Hotel":{ //Se hotel è selezionato
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
		}break;
		case "B&B":{ //Se B&B è selezionato
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
		}break;
		case "Motel":{ //Se motel è selezionato
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
		}break;
		case "Museo":{ //Se intrattenimento è selezionato
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
		}break;
		case "Zoo":{ //Se culturale è selezionato
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
		}break;
		case "Parco":{ //Se culturale è selezionato
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
		}break;
		}
		
		return currentPanel; //Aggiorna il pannello attuale
		
	}
}
