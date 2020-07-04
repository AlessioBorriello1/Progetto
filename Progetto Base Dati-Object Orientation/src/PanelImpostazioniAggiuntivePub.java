import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntivePub extends JPanel {
	
	public PanelImpostazioniAggiuntivePub(boolean vegano, String nazione, String tipoBirra) {
		setBorder(null);
		setBackground(new Color(149, 200, 216));
		
		setName("panelImpostazioniAggiuntivePub");
		
		JLabel lblImpostazioniAggiuntivePub = new JLabel("Impostazioni aggiuntive pub");
		lblImpostazioniAggiuntivePub.setFont(new Font("Georgia", Font.BOLD, 14));
		
		JCheckBox chckbxCiboVegano = new JCheckBox("Cibo vegano");
		chckbxCiboVegano.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxCiboVegano.setName("chckbxCiboVegano");
		chckbxCiboVegano.setSelected(vegano);
		chckbxCiboVegano.setBackground(null);
		
		JComboBox comboBoxNazionalitaCibo = new JComboBox();
		comboBoxNazionalitaCibo.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxNazionalitaCibo.setName("comboBoxNazionalitCibo");
		comboBoxNazionalitaCibo.addItem("Italiano");
		comboBoxNazionalitaCibo.addItem("Messicano");
		comboBoxNazionalitaCibo.addItem("Indiano");
		comboBoxNazionalitaCibo.setSelectedItem(nazione);
		
		JLabel lblNazionalitaCibo = new JLabel("Nazionalit\u00E0 cibo");
		lblNazionalitaCibo.setFont(new Font("Georgia", Font.PLAIN, 11));
		
		JComboBox comboBoxTipoBirra = new JComboBox();
		comboBoxTipoBirra.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxTipoBirra.setName("comboBoxTipoBirra");
		comboBoxTipoBirra.addItem("Spina");
		comboBoxTipoBirra.addItem("Malto");
		comboBoxTipoBirra.setSelectedItem(tipoBirra);
		
		JLabel lblTipoBirra = new JLabel("Tipo birra");
		lblTipoBirra.setFont(new Font("Georgia", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImpostazioniAggiuntivePub, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxTipoBirra, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxNazionalitaCibo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxCiboVegano, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoBirra)
								.addComponent(lblNazionalitaCibo))))
					.addGap(159))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntivePub, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chckbxCiboVegano)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxNazionalitaCibo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNazionalitaCibo))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipoBirra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoBirra))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

		
	}

}
