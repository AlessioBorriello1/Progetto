import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Hotel extends Alloggio{

	//Attributi unici hotel
	private int numeroStelle;

	//Getter e setter numero stelle
	public int getNumeroStelle() {
		return numeroStelle;
	}
	public void setNumeroStelle(int numeroStelle) {
		this.numeroStelle = numeroStelle;
	}
	
	/**
	 * Mostra le informazioni uniche di questo luogo nel pannello corretto
	 * @param controller MainController
	 * @param box Box a cui aggiungere le componenti per mostrare le informazioni
	 * @param immagine Immagine da modificare in base al tipo di luogo
	 */
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaHotel.png")));
		
		boolean piscina = isPiscina();
		boolean wifi = isWifi();
		int numeroStanze = getNumeroStanze();
		
		int numeroStelle = getNumeroStelle();
		
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
		
		JLabel lblNumeroStelle = new JLabel("Stelle: " + numeroStelle);
		lblNumeroStelle.setForeground(controller.independence);
		box.add(lblNumeroStelle);
		
		box.revalidate();
		box.repaint();
		
	}

}
