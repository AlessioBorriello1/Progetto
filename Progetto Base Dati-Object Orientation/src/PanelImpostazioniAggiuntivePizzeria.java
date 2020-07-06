import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class PanelImpostazioniAggiuntivePizzeria extends JPanel {
	
	/**
	 * Pannello contenente le impostazioni uniche per Ristorante e Pizzeria
	 * @param vegano Se il ristorante fa cibo vegano
	 * @param nazionalita Nazione cibo del ristorante
	 * @param asporto Se la pizzeria fa asporto
	 */
	public PanelImpostazioniAggiuntivePizzeria(boolean vegano, String nazionalita, boolean asporto) {
		
		setBorder(null);
		setBackground(new Color(149, 200, 216));
		
		setName("panelImpostazioniAggiuntivePizzeria");
		
		JLabel lblImpostazioniAggiuntivePizzeria = new JLabel("Impostazioni aggiuntive pizzeria");
		lblImpostazioniAggiuntivePizzeria.setFont(new Font("Georgia", Font.BOLD, 14));
		
		JCheckBox chckbxCiboVegano = new JCheckBox("Cibo vegano");
		chckbxCiboVegano.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxCiboVegano.setName("chckbxCiboVegano");
		chckbxCiboVegano.setSelected(vegano);
		chckbxCiboVegano.setBackground(null);
		
		JComboBox comboBoxNazionalitCibo = new JComboBox();
		comboBoxNazionalitCibo.setFont(new Font("Georgia", Font.PLAIN, 11));
		comboBoxNazionalitCibo.setName("comboBoxNazionalitCibo");
		comboBoxNazionalitCibo.addItem("Italiano");
		comboBoxNazionalitCibo.addItem("Messicano");
		comboBoxNazionalitCibo.addItem("Indiano");
		comboBoxNazionalitCibo.setSelectedItem(nazionalita);
		
		JLabel lblNazionalitaCibo = new JLabel("Nazionalit\u00E0 cibo");
		lblNazionalitaCibo.setFont(new Font("Georgia", Font.PLAIN, 11));
		
		JCheckBox chckbxAsporto = new JCheckBox("Asporto");
		chckbxAsporto.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxAsporto.setName("chckbxAsporto");
		chckbxAsporto.setSelected(asporto);
		chckbxAsporto.setBackground(null);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblImpostazioniAggiuntivePizzeria, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(chckbxAsporto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxNazionalitCibo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxCiboVegano, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNazionalitaCibo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(163, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntivePizzeria)
					.addGap(18)
					.addComponent(chckbxCiboVegano)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxNazionalitCibo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNazionalitaCibo))
					.addGap(28)
					.addComponent(chckbxAsporto)
					.addContainerGap(138, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
