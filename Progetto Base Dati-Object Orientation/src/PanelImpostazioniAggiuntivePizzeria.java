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

	public PanelImpostazioniAggiuntivePizzeria() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(Color.WHITE);
		
		setName("panelImpostazioniAggiuntivePizzeria");
		
		JLabel lblImpostazioniAggiuntivePizzeria = new JLabel("Impostazioni aggiuntive pizzeria");
		lblImpostazioniAggiuntivePizzeria.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxCiboVegano = new JCheckBox("Cibo vegano");
		chckbxCiboVegano.setName("chckbxCiboVegano");
		
		JComboBox comboBoxNazionalitCibo = new JComboBox();
		comboBoxNazionalitCibo.setName("comboBoxNazionalitCibo");
		comboBoxNazionalitCibo.addItem("Italiano");
		comboBoxNazionalitCibo.addItem("Messicano");
		comboBoxNazionalitCibo.addItem("Indiano");
		
		JLabel lblNazionalitaCibo = new JLabel("Nazionalit\u00E0 cibo");
		lblNazionalitaCibo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JCheckBox chckbxAsporto = new JCheckBox("Asporto");
		chckbxAsporto.setName("chckbxAsporto");
		
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
