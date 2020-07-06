import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntiveZoo extends JPanel {

	/**
	 * Pannello contenente le impostazioni uniche per Attrazione e Zoo
	 * @param promozione Se l'attrazione ha la promozione studenti
	 * @param specie Tipo della specia dello zoo
	 */
	public PanelImpostazioniAggiuntiveZoo(boolean promozione, String specie) {
		setBorder(null);
		
		setName("panelImpostazioniAggiuntiveZoo");
		setBackground(new Color(149, 200, 216));
		
		JLabel lblImpostazioniAggiuntiveZoo = new JLabel("Impostazioni aggiuntive Zoo");
		lblImpostazioniAggiuntiveZoo.setFont(new Font("Georgia", Font.BOLD, 14));
		
		JCheckBox chckbxPromozioneStudenti = new JCheckBox("Promozione studenti");
		chckbxPromozioneStudenti.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxPromozioneStudenti.setName("chckbxPromozioneStudenti");
		chckbxPromozioneStudenti.setSelected(promozione);
		chckbxPromozioneStudenti.setBackground(null);
		
		JComboBox comboBoxSpecie = new JComboBox();
		comboBoxSpecie.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxSpecie.addItem("Primati");
		comboBoxSpecie.addItem("Felini");
		comboBoxSpecie.addItem("Rettili");
		comboBoxSpecie.setName("comboBoxSpecie");
		comboBoxSpecie.setSelectedItem(specie);
		
		
		JLabel lblSpecie = new JLabel("Specie");
		lblSpecie.setFont(new Font("Georgia", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImpostazioniAggiuntiveZoo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxSpecie, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxPromozioneStudenti, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSpecie)))
					.addContainerGap(183, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveZoo)
					.addGap(18)
					.addComponent(chckbxPromozioneStudenti)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxSpecie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSpecie))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
