import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.SpringLayout;

public class PanelInfoLuogoAnteprima extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	boolean cliccabile;
	
	/**
	 * Mostra un pannello di anteprima con le info del luogo
	 * @param controller MainController
	 * @param mainFrame MainFrame in cui mostrare il pannello
	 * @param l Luogo di cui mostrare le info
	 * @param cliccabile Se il pannello è cliccabile o meno per rimandare al pannello di info luogo vero e proprio o al pannello di modifica
	 * @param workPanel JPanel dove mostrare il pannello
	 */
	public PanelInfoLuogoAnteprima(MainController controller, MainFrame mainFrame, Luogo l, boolean cliccabile, JPanel workPanel) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		this.cliccabile = cliccabile;
		
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(752, 134));
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(cliccabile) { //Se è cliccabile
					if(controller.getUtente() != null) { //Se sono loggato
						if(l.getNomeUtente().contentEquals(controller.getUtente().getNomeUtente())) { //Se l'utente è il creatore del luogo
							if(!controller.getCurrentPanel().getClass().toString().contentEquals("class HomePanel")) { //Se non sono nell'home panel
								mainFrame.cambiaPannelloLavoroAPanelModificaLuogo(workPanel, l); //Modifica luogo
							}else {
								mainFrame.cambiaPannelloLavoroAPanelInfoLuogo(workPanel, l);
							}
						}else {
							mainFrame.cambiaPannelloLavoroAPanelInfoLuogo(workPanel, l);
						}
					}else {
						mainFrame.cambiaPannelloLavoroAPanelInfoLuogo(workPanel, l);
					}
				}
			}
			
		});
		
		if(cliccabile) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.sky);
		
		JLabel labelImmagine = new JLabel("");
		labelImmagine.setBorder(null);
		labelImmagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPizzeria.png")));
		labelImmagine.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblId = new JLabel("ID: " + l.getID());
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblId.setForeground(controller.egyptian);
		
		JLabel lblCreatoDa = new JLabel("Creato da: " + l.getNomeUtente());
		lblCreatoDa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCreatoDa.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCreatoDa.setForeground(controller.egyptian);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setForeground(controller.egyptian);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo: ");
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIndirizzo.setForeground(controller.egyptian);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setForeground(controller.egyptian);
		
		JLabel lblProprietario = new JLabel("Proprietario: ");
		lblProprietario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProprietario.setForeground(controller.egyptian);
		
		JLabel lblValutazione = new JLabel("Valutazione: ");
		lblValutazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValutazione.setForeground(controller.egyptian);
		
		String specializzazione = l.getAttributoAttivita();
		JLabel lblSpecializzazione = new JLabel("Tipo:");
		lblSpecializzazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecializzazione.setForeground(controller.egyptian);
		
		JPanel panelInformazioniUniche = new JPanel();
		panelInformazioniUniche.setBackground(getBackground());
		
		JLabel lblNomeVariabile = new JLabel(l.getNome());
		lblNomeVariabile.setForeground(controller.independence);
		
		JLabel lblIndirizzoVariabile = new JLabel(l.getIndirizzo());
		lblIndirizzoVariabile.setForeground(controller.independence);
		
		JLabel lblTelefonoVariabile = new JLabel(l.getTelefono());
		lblTelefonoVariabile.setForeground(controller.independence);
		
		JLabel lblProprietarioVariabile = new JLabel(l.getProprietario());
		lblProprietarioVariabile.setForeground(controller.independence);
		
		JLabel lblValutazioneVariabile = new JLabel(String.format("%.2f", l.getMediaRecensioni()));
		lblValutazioneVariabile.setForeground(controller.independence);
		
		JLabel lblSpecializzazioneVariabile = new JLabel(specializzazione);
		lblSpecializzazioneVariabile.setForeground(controller.independence);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(labelImmagine, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNomeVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIndirizzo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIndirizzoVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTelefonoVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProprietario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProprietarioVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValutazione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValutazioneVariabile)))
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSpecializzazione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSpecializzazioneVariabile)
							.addPreferredGap(ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCreatoDa)
									.addGap(19))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId)
									.addGap(27))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelInformazioniUniche, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(lblNomeVariabile)
								.addComponent(lblCreatoDa)
								.addComponent(lblSpecializzazione)
								.addComponent(lblSpecializzazioneVariabile))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblIndirizzo)
										.addComponent(lblIndirizzoVariabile))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTelefono)
										.addComponent(lblTelefonoVariabile))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblProprietario)
										.addComponent(lblProprietarioVariabile))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblValutazione)
										.addComponent(lblValutazioneVariabile)))
								.addComponent(lblId)
								.addComponent(panelInformazioniUniche, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
						.addComponent(labelImmagine, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		SpringLayout sl_panelInformazioniUniche = new SpringLayout();
		panelInformazioniUniche.setLayout(sl_panelInformazioniUniche);
		
		Box verticalBox = Box.createVerticalBox();
		sl_panelInformazioniUniche.putConstraint(SpringLayout.NORTH, verticalBox, 0, SpringLayout.NORTH, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.WEST, verticalBox, 0, SpringLayout.WEST, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.SOUTH, verticalBox, 91, SpringLayout.NORTH, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.EAST, verticalBox, 135, SpringLayout.WEST, panelInformazioniUniche);
		panelInformazioniUniche.add(verticalBox);
		setLayout(groupLayout);
		
		//Casta il tipo corretto in base alla specializzazione e aggiungi il corretto pannello al pannello informazioni uniche
		switch(specializzazione) {
		case "Pizzeria": Pizzeria pizzeria = (Pizzeria)l; pizzeria.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Braceria": Braceria braceria = (Braceria)l; braceria.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Pub": Pub pub = (Pub)l; pub.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Hotel": Hotel hotel = (Hotel)l; hotel.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Motel": Motel motel = (Motel)l; motel.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "B&B": BB bb = (BB)l; bb.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Museo": Museo museo = (Museo)l; museo.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Zoo": Zoo zoo = (Zoo)l; zoo.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		case "Parco": Parco parco = (Parco)l; parco.drawInformazioniUniche(controller, verticalBox, labelImmagine); break;
		}
		
	}

}
