import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Zoo extends Attrazione{

	private String specie;

	//Getter e setter specie
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaZoo.png")));
		
		boolean promozione = isPromozioneStudenti();
		
		String specie = getSpecie();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		JLabel lblTipoSpecie = new JLabel("Specie: " + specie);
		lblTipoSpecie.setForeground(controller.independence);
		box.add(lblTipoSpecie);
		
		box.revalidate();
		box.repaint();
		
	}
	
}
