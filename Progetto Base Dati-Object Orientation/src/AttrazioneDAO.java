import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AttrazioneDAO {

	public Museo creaMuseo(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Museo m = new Museo();
		
		boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudentiMuseo")).isSelected();
		
		String tipoMuseo = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoMuseo")).getSelectedItem().toString();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " " + tipoMuseo);
		if(promozione) {
			System.out.println("Promozione");
		}
		
		return null;
		
	}
	
	public Zoo creaZoo(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Zoo z = new Zoo();
		
		boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudentiZoo")).isSelected();
		
		String tipoSpecie = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "comboBoxSpecie")).getSelectedItem().toString();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione + " "  + tipoSpecie);
		if(promozione) {
			System.out.println("Promozione");
		}
		
		return null;
		
	}
	
	public Parco creaParco(String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, MainController c){
		
		Parco p = new Parco();
		
		boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudentiParco")).isSelected();
		
		boolean ingressoGratuito = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxIngressoGratuito")).isSelected();
		
		System.out.println(nome + " " + indirizzo + " " + telefono + " " + proprietario + " " + tipoAttivita + " " + specializzazione);
		if(promozione) {
			System.out.println("Promozione");
		}
		if(ingressoGratuito) {
			System.out.println("Ingresso gratuito");
		}
		
		return null;
	
	}
	
}
