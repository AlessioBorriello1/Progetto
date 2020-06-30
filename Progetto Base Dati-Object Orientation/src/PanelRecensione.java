import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

public class PanelRecensione extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	private JPanel workPanel;
	private boolean cliccabile;
	
	public PanelRecensione(MainController controller, MainFrame mainFrame, Recensione r, JPanel workPanel) {
		
		setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		this.workPanel = workPanel;
		
		setMaximumSize(new Dimension(786, 100));
		
		if(controller.getUtente() != null) {
			cliccabile = (controller.getUtente().getNomeUtente().contentEquals(r.getNomeUtente()));
		}else {
			cliccabile = false;
		}
		
		if(cliccabile) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(cliccabile) {
					int ID = r.getIDLuogo();
					LuogoDAO dao = new LuogoDAO();
					Luogo l = dao.getLuogoByID(ID);
					mainFrame.cambiaPannelloLavoroAModificaRecensionePanel(workPanel, l, r);
				}
			}
		});
		
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.white);
		
		JLabel lblVoto = new JLabel("Voto: " + r.getVoto());
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		LuogoDAO dao = new LuogoDAO();
		JLabel lblDate = new JLabel("Creata da: " + r.getNomeUtente() + " il " + r.getData().toString() + " per il luogo " + dao.getLuogoByID(r.getIDLuogo()).getNome().toString() + " ID(" + r.getIDLuogo() + ")");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(cliccabile) {
					int ID = r.getIDLuogo();
					LuogoDAO dao = new LuogoDAO();
					Luogo l = dao.getLuogoByID(ID);
					mainFrame.cambiaPannelloLavoroAModificaRecensionePanel(workPanel, l, r);
				}
			}
		});
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setText(r.getTesto());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(textArea)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVoto)
							.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
							.addComponent(lblDate)
							.addGap(25))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoto)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

	public void setCliccabile(boolean cliccabile) {
		this.cliccabile = cliccabile;
	}
	
}
