import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Motel extends Alloggio{

	private boolean assistenzaAuto;

	//Getter e setter assistenzaAuto
	public boolean isAssistenzaAuto() {
		return assistenzaAuto;
	}
	public void setAssistenzaAuto(boolean assistenzaAuto) {
		this.assistenzaAuto = assistenzaAuto;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaMotel.png")));
		
		boolean piscina = isPiscina();
		boolean wifi = isWifi();
		int numeroStanze = getNumeroStanze();
		
		boolean assistenza = isAssistenzaAuto();
		
		JLabel lblNumeroStanze = new JLabel("Numero stanze: " + numeroStanze);
		lblNumeroStanze.setForeground(controller.independence);
		box.add(lblNumeroStanze);
		
		if(piscina) {
			JLabel lblPiscina = new JLabel("Piscina");
			lblPiscina.setForeground(controller.independence);
			box.add(lblPiscina);
		}
		
		if(wifi) {
			JLabel lblWifi = new JLabel("Wifi");
			lblWifi.setForeground(controller.independence);
			box.add(lblWifi);
		}
		
		if(assistenza) {
			JLabel lblAssistenza = new JLabel("Assistenza vetture");
			lblAssistenza.setForeground(controller.independence);
			box.add(lblAssistenza);
		}
		
		box.revalidate();
		box.repaint();
		
	}
	
}
