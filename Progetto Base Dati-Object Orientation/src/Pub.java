import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pub extends Ristorante{

	private String tipoBirra;

	//Getter e setter tipoBirra
	public String getTipoBirra() {
		return tipoBirra;
	}
	public void setTipoBirra(String tipoBirra) {
		this.tipoBirra = tipoBirra;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPub.png")));
		
		boolean vegano = isVegano();
		String cibo = getNazionalitaCibo();
		String birra = getTipoBirra();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}
		
		JLabel lblBirra = new JLabel("Birra: " + birra);
		lblBirra.setForeground(controller.independence);
		box.add(lblBirra);
		
		
		box.revalidate();
		box.repaint();
		
	}
	
}
