import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntiveMuseo extends JPanel {

	public PanelImpostazioniAggiuntiveMuseo() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveMuseo");
		
		JLabel lblImpostazioniAggiuntiveMuseo = new JLabel("Impostazioni aggiuntive museo");
		lblImpostazioniAggiuntiveMuseo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxPromozioneStudenti = new JCheckBox("Promozione studenti");
		chckbxPromozioneStudenti.setName("chckbxPromozioneStudenti");
		
		JComboBox comboBoxTipoMuseo = new JComboBox();
		comboBoxTipoMuseo.addItem("Artistico");
		comboBoxTipoMuseo.addItem("Storico");
		comboBoxTipoMuseo.addItem("archeologico");
		comboBoxTipoMuseo.setName("comboBoxTipoMuseo");
		
		JLabel lblTipoMuseo = new JLabel("Tipo museo");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImpostazioniAggiuntiveMuseo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxTipoMuseo, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chckbxPromozioneStudenti, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTipoMuseo)))
					.addContainerGap(164, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveMuseo)
					.addGap(18)
					.addComponent(chckbxPromozioneStudenti)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipoMuseo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoMuseo))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
