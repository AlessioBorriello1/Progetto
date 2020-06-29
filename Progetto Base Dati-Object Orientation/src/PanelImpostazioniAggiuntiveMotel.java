import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelImpostazioniAggiuntiveMotel extends JPanel {

	public PanelImpostazioniAggiuntiveMotel(boolean piscina, boolean wifi, String numeroStanze, boolean assistenza) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveMotel");
		
		JLabel lblImpostazioniAggiuntiveMotel = new JLabel("Impostazioni aggiuntive motel");
		lblImpostazioniAggiuntiveMotel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxPiscina = new JCheckBox("Piscina");
		chckbxPiscina.setName("chckbxPiscina");
		chckbxPiscina.setSelected(piscina);
		
		JCheckBox chckbxWifi = new JCheckBox("WiFi");
		chckbxWifi.setName("chckbxWifi");
		chckbxWifi.setSelected(wifi);
		
		JTextField textFieldNumeroStanze = new JTextField();
		textFieldNumeroStanze.setColumns(10);
		textFieldNumeroStanze.setName("textFieldNumeroStanze");
		textFieldNumeroStanze.setText(numeroStanze);
		textFieldNumeroStanze.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String s = textFieldNumeroStanze.getText();
				int l = s.length();
				char c = e.getKeyChar();
				
				if(c >= '0' && c <= '9') {
					if(l < 4) {
						textFieldNumeroStanze.setEditable(true);
					}else {
						textFieldNumeroStanze.setEditable(false);
					}
				}else {
					if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
						textFieldNumeroStanze.setEditable(true);
					}else {
						textFieldNumeroStanze.setEditable(false);
					}
				}
			}
		});
		
		JLabel lblNumeroStanze = new JLabel("Numero stanze");
		lblNumeroStanze.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JCheckBox chckbxAssistenzaAutovetture = new JCheckBox("Assistenza autovetture");
		chckbxAssistenzaAutovetture.setName("chckbxAssistenzaAutovetture");
		chckbxAssistenzaAutovetture.setSelected(assistenza);
		
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
