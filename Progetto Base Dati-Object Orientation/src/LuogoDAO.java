import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class LuogoDAO {
	
	public boolean creaLuogo(MainController c, MainFrame mf, String nome, String indirizzo, String telefono, String proprietario, String tipoAttivita, String specializzazione, JPanel pannelloImpostazioniAggiuntive, Utente u) {
		
		try {
			
			if(nome.contentEquals("") || indirizzo.contentEquals("") || proprietario.contentEquals("") || telefono.length() < 10) {
				System.out.println("Valori non inseriti");
				mf.createNotificationFrame("Completa tutti i campi!");
				return false; //Operazione fallita, valori non inseriti
			}
			
			if(tipoAttivita.contentEquals("Alloggio")) {
				if(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString().contentEquals("")) {
					mf.createNotificationFrame("Completa tutti i campi!");
					return false; //Operazione fallita, valori non inseriti
				}
			}
			
			if(luogoEsiste(nome, proprietario)){
				mf.createNotificationFrame("Un luogo con questo nome e proprietario esiste già!");
				return false; //Operazione fallita, valori non inseriti
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			String q1 = "INSERT INTO luogo(Nome, Indirizzo, Telefono, Proprietario, Idutente, tipoattivita, attributoattivita)\r\n" + 
					"VALUES ('"+ nome +"','"+ indirizzo +"','"+ telefono + "','" + proprietario + "','" + u.getNomeUtente() + "','" + tipoAttivita + "','" + specializzazione + "');"; //Inizializzo query 1
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			st.executeUpdate(q1); //Eseguo la query contenuta in stringa q1
			
			/*Luogo l = new Luogo();
			l.setID(getIDLuogoByNomeAndPossessore(nome, proprietario));
			l.setNome(nome);
			l.setIndirizzo(indirizzo);
			l.setTelefono(telefono);
			l.setProprietario(proprietario);
			l.setTipoAttivita(tipoAttivita);
			l.setAttributoAttivita(specializzazione);
			l.setMediaRecensioni(0);
			l.setNomeUtente(u.getNomeUtente());*/
			if(tipoAttivita.contentEquals("Ristorante")) {
				System.out.println("Inserimento ristorante");
				boolean vegano = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxCiboVegano")).isSelected();
				String nazionalita = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxNazionalitCibo")).getSelectedItem().toString();
				String q2 = "INSERT INTO ristorante(idluogo, vegano, tiporistorante)\r\n" + 
						"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) +"','"+ ((vegano) ? 1:0) + "','" + nazionalita + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				
				if(specializzazione.contentEquals("Pizzeria")) {
					System.out.println("Inserimento pizzeria");
					boolean asporto = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAsporto")).isSelected();
					
					String q3 = "INSERT INTO pizzeria(idristorante, asporto)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + ((asporto) ? 1:0) + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					
				}else if(specializzazione.contentEquals("Braceria")) {
					System.out.println("Inserimento braceria");
					String tipoCarne = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoCarne")).getSelectedItem().toString();
					
					String q3 = "INSERT INTO braceria(idristorante, tipocarne)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + tipoCarne + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}else {
					System.out.println("Inserimento pub");
					String tipoBirra = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoBirra")).getSelectedItem().toString();
					
					String q3 = "INSERT INTO braceria(idristorante, tipobirra)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + tipoBirra + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}
				
			}else if(tipoAttivita.contentEquals("Alloggio")) {
				System.out.println("Inserimento alloggio");
				boolean piscina = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPiscina")).isSelected();
				boolean wifi = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxWifi")).isSelected();
				int numeroStanze = Integer.parseInt(((JTextField)c.getComponentByName(pannelloImpostazioniAggiuntive, "textFieldNumeroStanze")).getText().toString());
				
				String q2 = "INSERT INTO alloggio(idluogo, piscina, NumeroStanze, WiFi)\r\n" + 
						"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) +"','"+ ((piscina) ? 1:0) + "','" + numeroStanze + "','" + ((wifi) ? 1:0) + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				
				if(specializzazione.contentEquals("Hotel")) {
					System.out.println("Inserimento hotel");
					int numeroStelle = Integer.parseInt(((JSpinner)c.getComponentByName(pannelloImpostazioniAggiuntive, "spinnerNumeroStelle")).getValue().toString());
					
					String q3 = "INSERT INTO hotel(idalloggio, numerostelle)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + numeroStelle + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					
				}else if(specializzazione.contentEquals("Motel")) {
					System.out.println("Inserimento motel");
					boolean assistenzaAutovetture = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxAssistenzaAutovetture")).isSelected();
					
					String q3 = "INSERT INTO motel(idalloggio, assistenzaautovetture)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + ((assistenzaAutovetture) ? 1:0) + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}else {
					System.out.println("Inserimento BB");
					boolean colazione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxColazione")).isSelected();
					
					String q3 = "INSERT INTO bed&breakfast(idalloggio, colazione)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + ((colazione) ? 1:0) + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}
				
			}else {
				System.out.println("Inserimento attrazione");
				boolean promozione = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxPromozioneStudenti")).isSelected();
				
				String q2 = "INSERT INTO attrazione(idluogo, promozionestudenti)\r\n" + 
						"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + ((promozione) ? 1:0) + "');"; //Inizializzo query 2
				
				st.executeUpdate(q2); //Eseguo la query contenuta in stringa q2
				
				if(specializzazione.contentEquals("Museo")) {
					System.out.println("Inserimento museo");
					String tipoMuseo = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxTipoMuseo")).getSelectedItem().toString();
					
					String q3 = "INSERT INTO museo(idattrazione, tipomuseo)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + tipoMuseo + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
					
				}else if(specializzazione.contentEquals("Zoo")) {
					System.out.println("Inserimento zoo");
					String tipoSpecie = ((JComboBox)c.getComponentByName(pannelloImpostazioniAggiuntive, "comboBoxSpecie")).getSelectedItem().toString();
					
					String q3 = "INSERT INTO zoo(idattrazione, specie)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + tipoSpecie + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}else {
					System.out.println("Inserimento Parco");
					boolean gratuito = ((JCheckBox)c.getComponentByName(pannelloImpostazioniAggiuntive,  "chckbxIngressoGratuito")).isSelected();
					
					String q3 = "INSERT INTO parco(idattrazione, ingressogratuito)\r\n" + 
							"VALUES ('"+ getIDLuogoByNomeAndPossessore(nome, proprietario) + "','" + ((gratuito) ? 1:0) + "');"; //Inizializzo query 3
					
					st.executeUpdate(q3); //Eseguo la query contenuta in stringa q3
					con.close(); //Chiudi connessione
					st.close(); //Chiudi statement
				}
			
			}
			
			return true; //Operazione inserimento riuscita, restituisce true
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false; //Operazione inserimento fallita, restituisce false
		}
	
	}
	
	public int getIDLuogoByNomeAndPossessore(String nome, String proprietario) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where Nome = '" + nome + "' AND Proprietario = '" + proprietario + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			System.out.println("Query eseguita");
			
			if(rs.next()) { //Se il luogo è stato trovato
				
				return (rs.getInt("Idluogo"));
				
			}else { //Se il luogo non è stato trovato (result set vuoto)
	
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				
				System.out.println("Resul set vuoto");
				return -1; //Restituisci null
				
			}
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return -1;
		}
		
	}
	
	public boolean luogoEsiste(String nome, String proprietario) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String q = "Select * from luogo where Nome = '" + nome + "' AND Proprietario = '" + proprietario + "'" ; //Inizializzo query
			
			String connectionURL = MainController.URL; //URL di connessione

	        Connection con = DriverManager.getConnection(connectionURL, "root", "password");  //Crea connessione
			Statement st = con.createStatement(); //Creo statement
			ResultSet rs = st.executeQuery(q); //Eseguo la query contenuta in stringa q
			
			if(rs.next()) { //Se il luogo è stato trovato
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return true;
				
			}else { //Se il luogo non è stato trovato (result set vuoto)
				
				con.close(); //Chiudi connessione
				st.close(); //Chiudi statement
				return false;
				
			}
			
		}catch(Exception e) { //Error catching
			System.out.println(e);
			return false;
		}
		
	}
	
}
