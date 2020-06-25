import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelInfoLuogoAnteprima extends JPanel {

	public PanelInfoLuogoAnteprima() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblPannello = new JLabel("Pannello 2");
		
		JLabel lblPannello_1 = new JLabel("Pannello 1");
		
		JLabel lblPannello_2 = new JLabel("Pannello 3");
		
		JLabel lblPannello_3 = new JLabel("Pannello 4");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPannello_1)
							.addPreferredGap(ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
							.addComponent(lblPannello_2))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPannello)
							.addPreferredGap(ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
							.addComponent(lblPannello_3)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPannello_1)
						.addComponent(lblPannello_2))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPannello)
						.addComponent(lblPannello_3))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}

}
