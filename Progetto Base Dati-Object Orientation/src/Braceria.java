import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Braceria extends Ristorante{

	//Attributi unici braceria
	private String tipoCarne;

	//Getter e setter tipo carne
	public String getTipoCarne() {
		return tipoCarne;
	}
	public void setTipoCarne(String tipoCarne) {
		this.tipoCarne = tipoCarne;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaBraceria.png")));
		
		boolean vegano = isVegano();
		String cibo = getNazionalitaCibo();
		String carne = getTipoCarne();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}
		
		JLabel lblCarne = new JLabel("Carne: " + carne);
		lblCarne.setForeground(controller.independence);
		box.add(lblCarne);
		
		
		box.revalidate();
		box.repaint();
		
	}
	
}
