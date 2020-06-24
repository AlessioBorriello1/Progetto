import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class BraceriaDAO {
	
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
	
}
