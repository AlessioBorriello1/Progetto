import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelInfoLuogoAnteprima extends JPanel {

	MainController controller;
	
	public PanelInfoLuogoAnteprima(MainController controller, Luogo l) {
		
		setMaximumSize(new Dimension(786, 130));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				if(l.getNomeUtente().contentEquals(controller.getUtente().getNomeUtente())) {
					System.out.println("Modifica luogo");
				}else {
					System.out.println("Visita luogo");
				}
			
			}
		});
		
		this.controller = controller;
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.sky);
		
		JLabel labelImmagine = new JLabel("");
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
		
		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setForeground(controller.egyptian);
		
		String specializzazione = l.getAttributoAttivita();
		JLabel lblSpecializzazione = new JLabel("Specializzazione: ");
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
		
		JLabel lblValutazioneVariabile = new JLabel(String.valueOf(l.getMediaRecensioni()));
		lblValutazioneVariabile.setForeground(controller.independence);
		
		JLabel lblTipoVariabile = new JLabel(l.getTipoAttivita());
		lblTipoVariabile.setForeground(controller.independence);
		
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
							.addComponent(lblSpecializzazioneVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTipoVariabile))
						.addComponent(panelInformazioniUniche, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblCreatoDa)
							.addGap(19))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblId)
							.addGap(27))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblNomeVariabile)
						.addComponent(lblTipo)
						.addComponent(lblTipoVariabile)
						.addComponent(lblCreatoDa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo)
								.addComponent(lblIndirizzoVariabile)
								.addComponent(lblSpecializzazione)
								.addComponent(lblSpecializzazioneVariabile))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
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
								.addComponent(panelInformazioniUniche, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
						.addComponent(lblId))
					.addContainerGap())
				.addComponent(labelImmagine, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		
	}

}
