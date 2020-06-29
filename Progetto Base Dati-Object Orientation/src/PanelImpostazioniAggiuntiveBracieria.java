import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntiveBracieria extends JPanel {

	public PanelImpostazioniAggiuntiveBracieria(boolean vegano, String nazionalita, String tipoCarne) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveBracieria");
		
		JLabel lblImpostazioniAggiuntiveBracieria = new JLabel("Impostazioni aggiuntive bracieria");
		lblImpostazioniAggiuntiveBracieria.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxCiboVegano = new JCheckBox("Cibo vegano");
		chckbxCiboVegano.setName("chckbxCiboVegano");
		chckbxCiboVegano.setSelected(vegano);
		
		JComboBox comboBoxNazionalitaCibo = new JComboBox();
		comboBoxNazionalitaCibo.setName("comboBoxNazionalitCibo");
		comboBoxNazionalitaCibo.addItem("Italiano");
		comboBoxNazionalitaCibo.addItem("Messicano");
		comboBoxNazionalitaCibo.addItem("Indiano");
		comboBoxNazionalitaCibo.setSelectedItem(nazionalita);
		
		JLabel lblNazionalitCibo = new JLabel("Nazionalit\u00E0 cibo");
		lblNazionalitCibo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JComboBox comboBoxTipoCarne = new JComboBox();
		comboBoxTipoCarne.setName("comboBoxTipoCarne");
		comboBoxTipoCarne.addItem("Pollo");
		comboBoxTipoCarne.addItem("Manzo");
		comboBoxTipoCarne.addItem("Tacchino");
		comboBoxTipoCarne.setSelectedItem(tipoCarne);
		
		JLabel lblTipoCarne = new JLabel("Tipo carne");
		lblTipoCarne.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImpostazioniAggiuntiveBracieria)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxTipoCarne, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxNazionalitaCibo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxCiboVegano, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoCarne)
								.addComponent(lblNazionalitCibo))))
					.addContainerGap(152, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBracieria)
					.addGap(18)
					.addComponent(chckbxCiboVegano)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxNazionalitaCibo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNazionalitCibo))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipoCarne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoCarne))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
