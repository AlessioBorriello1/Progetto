import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PubDAO {

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
