import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Museo extends Attrazione{

	private String tipoMuseo;

	//Getter e setter tipoMuseo
	public String getTipoMuseo() {
		return tipoMuseo;
	}
	public void setTipoMuseo(String tipoMuseo) {
		this.tipoMuseo = tipoMuseo;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaMuseo.png")));
		
		boolean promozione = isPromozioneStudenti();
		
		String tipoMuseo = getTipoMuseo();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		JLabel lblTipoMuseo = new JLabel("Museo: " + tipoMuseo);
		lblTipoMuseo.setForeground(controller.independence);
		box.add(lblTipoMuseo);
		
		box.revalidate();
		box.repaint();
		
	}

}
