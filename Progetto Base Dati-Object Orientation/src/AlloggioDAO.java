import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AlloggioDAO {
	
	public Hotel creaHotel(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeHotel")).getText().contentEquals("")) {
			
			MainFrame m = (MainFrame)SwingUtilities.getWindowAncestor(pannelloImpostazioniAggiuntive); //Ottieni mainFrame
			m.createNotificationFrame("Inserisci numero stanze!");
			
			return null;
		}
		
		boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscinaHotel")).isSelected();
		boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifiHotel")).isSelected();
		int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeHotel")).getText());
		
		
		int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
		
		Hotel h = new Hotel();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + numeroStanze + " " + numeroStelle);
		if(piscina) {
			System.out.println("Piscina");
		}
		
		if(wifi) {
			System.out.println("Wifi");
		}
		
		return null;
		
	}
	
	public Motel creaMotel(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeMotel")).getText().contentEquals("")) {
			
			MainFrame m = (MainFrame)SwingUtilities.getWindowAncestor(pannelloImpostazioniAggiuntive); //Ottieni mainFrame
			m.createNotificationFrame("Inserisci numero stanze!");
			
			return null;
		}
		
		boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscinaMotel")).isSelected();
		boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifiMotel")).isSelected();
		int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeMotel")).getText());
		
		
		boolean assistenza = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
		
		Motel m = new Motel();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + numeroStanze);
		if(piscina) {
			System.out.println("Piscina");
		}
		
		if(wifi) {
			System.out.println("Wifi");
		}
		
		if(assistenza) {
			System.out.println("Assistenza auto");
		}
		
		return null;
		
	}
	
	public BB creaBB(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		
		if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeBB")).getText().contentEquals("")) {
			
			MainFrame m = (MainFrame)SwingUtilities.getWindowAncestor(pannelloImpostazioniAggiuntive); //Ottieni mainFrame
			m.createNotificationFrame("Inserisci numero stanze!");
			
			return null;
		}
		
		boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscinaBB")).isSelected();
		boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifiBB")).isSelected();
		int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanzeBB")).getText());
		
		
		boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
		
		BB b = new BB();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + numeroStanze);
		if(piscina) {
			System.out.println("Piscina");
		}
		
		if(wifi) {
			System.out.println("Wifi");
		}
		
		if(colazione) {
			System.out.println("Colazione");
		}
		
		return null;
	
	}
	
}
