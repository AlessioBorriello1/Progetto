import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BB extends Alloggio{

	//Attributi unici B&B
	private boolean colazione;
	
	//Getter e setter colazione
	public boolean isColazione() {
		return colazione;
	}
	public void setColazione(boolean colazione) {
		this.colazione = colazione;
	}
	
	public void drawInformazioniUniche(MainController controller, Box box, JLabel immagine) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaBB.png")));
		
		boolean piscina = isPiscina();
		boolean wifi = isWifi();
		int numeroStanze = getNumeroStanze();
		
		boolean colazione = isColazione();
		
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
		
		if(colazione) {
			JLabel lblColazione = new JLabel("Colazione");
			lblColazione.setForeground(controller.independence);
			box.add(lblColazione);
		}
		
		box.revalidate();
		box.repaint();
		
	}

}
