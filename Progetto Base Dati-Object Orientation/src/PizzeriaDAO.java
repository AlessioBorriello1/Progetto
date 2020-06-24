import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class PizzeriaDAO {

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
	
}
