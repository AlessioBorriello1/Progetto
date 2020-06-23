import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntiveMotel extends JPanel {
	private JTextField textField;
	private JTextField textFieldNumeroStanze;

	public PanelImpostazioniAggiuntiveMotel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveMotel");
		
		JLabel lblImpostazioniAggiuntiveMotel = new JLabel("Impostazioni aggiuntive motel");
		lblImpostazioniAggiuntiveMotel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxPiscina = new JCheckBox("Piscina");
		
		JCheckBox chckbxWifi = new JCheckBox("WiFi");
		
		textFieldNumeroStanze = new JTextField();
		textFieldNumeroStanze.setColumns(10);
		
		JLabel lblNumeroStanze = new JLabel("Numero stanze");
		lblNumeroStanze.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JCheckBox chckbxAssistenzaAutovetture = new JCheckBox("Assistenza autovetture");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxAssistenzaAutovetture)
						.addComponent(chckbxPiscina)
						.addComponent(lblImpostazioniAggiuntiveMotel)
						.addComponent(chckbxWifi)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNumeroStanze)))
					.addContainerGap(171, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveMotel)
					.addGap(18)
					.addComponent(chckbxPiscina)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxWifi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroStanze))
					.addGap(18)
					.addComponent(chckbxAssistenzaAutovetture)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		

	}
}
