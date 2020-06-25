import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class PanelImpostazioniAggiuntiveBB extends JPanel {

	
	public PanelImpostazioniAggiuntiveBB() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveBB");
		
		JLabel lblImpostazioniAggiuntiveBB = new JLabel("Impostazioni aggiuntive B&B");
		lblImpostazioniAggiuntiveBB.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxPiscina = new JCheckBox("Piscina");
		chckbxPiscina.setName("chckbxPiscina");
		
		JCheckBox chckbxWifi = new JCheckBox("WiFi");
		chckbxWifi.setName("chckbxWifi");
		
		JTextField textFieldNumeroStanze = new JTextField();
		textFieldNumeroStanze.setColumns(10);
		textFieldNumeroStanze.setName("textFieldNumeroStanze");
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
		lblNumeroStanze.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JCheckBox chckbxColazione = new JCheckBox("Colazione");
		chckbxColazione.setName("chckbxColazione");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxWifi)
						.addComponent(lblImpostazioniAggiuntiveBB)
						.addComponent(chckbxPiscina)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNumeroStanze))
						.addComponent(chckbxColazione))
					.addContainerGap(179, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveBB)
					.addGap(18)
					.addComponent(chckbxPiscina)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxWifi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroStanze))
					.addGap(18)
					.addComponent(chckbxColazione)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
