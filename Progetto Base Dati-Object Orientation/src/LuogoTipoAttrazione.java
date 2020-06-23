import javax.swing.JPanel;

public class LuogoTipoAttrazione extends Luogo{
	
	private int ID;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public LuogoTipoAttrazione creaLuogoTipoAttrazione(String nome, String indirizzo, int telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive) {
		
		String s = pannelloImpostazioniAggiuntive.getName();
		
		switch(s) {
		case "panelImpostazioniAggiuntiveMuseo":{
			System.out.println("Museo");
		}break;
		case "panelImpostazioniAggiuntiveZoo":{
			System.out.println("Zoo");
		}break;
		case "panelImpostazioniAggiuntiveParco":{
			System.out.println("Parco");
		}break;
		}
		
		return null;
	}

	
}
