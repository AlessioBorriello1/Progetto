import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PanelImpostazioniAggiuntiveHotel extends JPanel {
	
	/**
	 * Pannello contenente le impostazioni uniche per Alloggio e Hotel
	 * @param piscina Se il alloggio ha una piscina
	 * @param wifi Se il alloggio ha il wifi
	 * @param numeroStanze Numero stanze del alloggio
	 * @param numeroStelle Numero di stelle dell'hotel
	 */
	public PanelImpostazioniAggiuntiveHotel(boolean piscina, boolean wifi, String numeroStanze, int numeroStelle) {
		setBorder(null);
		setBackground(new Color(149, 200, 216));
		
		setName("panelImpostazioniAggiuntiveHotel");
		
		JLabel lblImpostazioniaggiuntivehotel = new JLabel("Impostazioni aggiuntive hotel");
		lblImpostazioniaggiuntivehotel.setFont(new Font("Georgia", Font.BOLD, 14));
		
		JCheckBox chckbxPiscina = new JCheckBox("Piscina");
		chckbxPiscina.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxPiscina.setName("chckbxPiscina");
		chckbxPiscina.setSelected(piscina);
		chckbxPiscina.setBackground(null);
		
		JCheckBox chckbxWifi = new JCheckBox("WiFi");
		chckbxWifi.setFont(new Font("Georgia", Font.PLAIN, 11));
		chckbxWifi.setName("chckbxWifi");
		chckbxWifi.setSelected(wifi);
		chckbxWifi.setBackground(null);
		
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
		lblNumeroStanze.setFont(new Font("Georgia", Font.PLAIN, 11));
		
		JSpinner spinnerNumeroStelle = new JSpinner();
		spinnerNumeroStelle.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerNumeroStelle.setName("spinnerNumeroStelle");
		spinnerNumeroStelle.setValue(numeroStelle);
		((JSpinner.DefaultEditor) spinnerNumeroStelle.getEditor()).getTextField().setEditable(false);
		
		JLabel lblStelle = new JLabel("Stelle");
		lblStelle.setFont(new Font("Georgia", Font.PLAIN, 11));
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
