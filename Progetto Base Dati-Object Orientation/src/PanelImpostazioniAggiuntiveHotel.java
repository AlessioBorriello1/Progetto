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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PanelImpostazioniAggiuntiveHotel extends JPanel {
	
	public PanelImpostazioniAggiuntiveHotel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveHotel");
		
		JLabel lblImpostazioniaggiuntivehotel = new JLabel("Impostazioni aggiuntive hotel");
		lblImpostazioniaggiuntivehotel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		lblNumeroStanze.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSpinner spinnerNumeroStelle = new JSpinner();
		spinnerNumeroStelle.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerNumeroStelle.setName("spinnerNumeroStelle");
		
		JLabel lblStelle = new JLabel("Stelle");
		lblStelle.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxWifi)
						.addComponent(lblImpostazioniaggiuntivehotel)
						.addComponent(chckbxPiscina)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNumeroStanze))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinnerNumeroStelle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblStelle)))
					.addContainerGap(175, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniaggiuntivehotel)
					.addGap(18)
					.addComponent(chckbxPiscina)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxWifi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNumeroStanze, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroStanze))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerNumeroStelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStelle))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
