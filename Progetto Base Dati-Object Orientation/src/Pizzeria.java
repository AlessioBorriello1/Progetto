import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pizzeria extends Ristorante{

	private boolean asporto;

	//Getter e setter asporto
	public boolean isAsporto() {
		return asporto;
	}
	public void setAsporto(boolean asporto) {
		this.asporto = asporto;
	}

	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPizzeria.png")));
		
		boolean vegano = isVegano();
		String cibo = getNazionalitaCibo();
		boolean asporto = isAsporto();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}

		if(asporto) {
			JLabel lblAsporto = new JLabel("Asporto");
			lblAsporto.setForeground(controller.independence);
			box.add(lblAsporto);
		}
		
		box.revalidate();
		box.repaint();
		
	}

}
