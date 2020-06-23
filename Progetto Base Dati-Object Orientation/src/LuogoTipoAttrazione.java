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
		case "panelImpostazioniAggiuntiveIntrattenimento":{
			System.out.println("Intrattenimento");
		}break;
		case "panelImpostazioniAggiuntiveCulturale":{
			System.out.println("Culturale");
		}break;
		}
		
		return null;
	}

	
}
