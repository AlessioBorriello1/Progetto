import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Parco extends Attrazione{

	private boolean ingressoGratuito;

	//Getter e setter ingressoGratuito
	public boolean isIngressoGratuito() {
		return ingressoGratuito;
	}
	public void setIngressoGratuito(boolean ingressoGratuito) {
		this.ingressoGratuito = ingressoGratuito;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaParco.png")));
		
		boolean promozione = isPromozioneStudenti();
		boolean gratuito = isIngressoGratuito();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		if(gratuito) {
			JLabel lblGratuito = new JLabel("Ingresso gratuito");
			lblGratuito.setForeground(controller.independence);
			box.add(lblGratuito);
		}
		
		box.revalidate();
		box.repaint();
		
	}

}
