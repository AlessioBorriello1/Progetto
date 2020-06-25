import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JCheckBox;

public class PanelImpostazioniAggiuntiveParco extends JPanel {

	public PanelImpostazioniAggiuntiveParco() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setName("panelImpostazioniAggiuntiveParco");
		
		JLabel lblImpostazioniAggiuntiveParco = new JLabel("Impostazioni aggiuntive parco");
		lblImpostazioniAggiuntiveParco.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JCheckBox chckbxPromozioneStudenti = new JCheckBox("Promozione studenti");
		chckbxPromozioneStudenti.setName("chckbxPromozioneStudenti");
		
		JCheckBox chckbxIngressoGratuito = new JCheckBox("Ingresso gratuito");
		chckbxIngressoGratuito.setName("chckbxIngressoGratuito");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxIngressoGratuito)
						.addComponent(lblImpostazioniAggiuntiveParco)
						.addComponent(chckbxPromozioneStudenti))
					.addContainerGap(171, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImpostazioniAggiuntiveParco)
					.addGap(18)
					.addComponent(chckbxPromozioneStudenti)
					.addGap(18)
					.addComponent(chckbxIngressoGratuito)
					.addContainerGap(172, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
