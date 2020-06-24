import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class RistoranteDAO {
	
	public Pizzeria creaPizzeria(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Pizzeria p = new Pizzeria();
		
		boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVeganoPizzeria")).isSelected();
		String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCiboPizzeria")).getSelectedItem().toString();
		
		boolean asporto = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAsporto")).isSelected();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + nazionalita);
		if(vegano) {
			System.out.println("Vegano");
		}
		
		if(asporto) {
			System.out.println("Asporto");
		}
		
		return null;
		
	}
	
	public Braceria creaBraceria(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Braceria b = new Braceria();
		
		boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVeganoBraceria")).isSelected();
		String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCiboBraceria")).getSelectedItem().toString();
		
		String tipoCarne = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "comboBoxTipoCarne")).getSelectedItem().toString();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + nazionalita + " " + tipoCarne);
		if(vegano) {
			System.out.println("Vegano");
		}
		
		return null;
		
	}
	
	public Pub creaPub(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Pub b = new Pub();
		
		boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVeganoPub")).isSelected();
		String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCiboPub")).getSelectedItem().toString();
		
		String tipoBirra = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "comboBoxTipoBirra")).getSelectedItem().toString();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + nazionalita + " " + tipoBirra);
		if(vegano) {
			System.out.println("Vegano");
		}
		
		return null;
	
	}
	
}
