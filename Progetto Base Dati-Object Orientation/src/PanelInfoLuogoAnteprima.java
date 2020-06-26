import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ModuleLayer.Controller;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.SpringLayout;

public class PanelInfoLuogoAnteprima extends JPanel {

	private MainController controller;
	private MainFrame mainFrame;
	
	public PanelInfoLuogoAnteprima(MainController controller, MainFrame mainFrame, Luogo l) {
		
		this.controller = controller;
		this.mainFrame = mainFrame;
		
		setMaximumSize(new Dimension(786, 130));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if(controller.getUtente() != null) {
					if(l.getNomeUtente().contentEquals(controller.getUtente().getNomeUtente())) {
						System.out.println("Modifica luogo");
					}else {
						System.out.println("Visita luogo");
					}
				}else {
					mainFrame.createNotificationFrame("Devi prima eseguire il Login!");
					mainFrame.cambiaPannelloLavoroALoginPanel((JPanel)controller.getComponentByName(mainFrame, "workPanel"));
				}
			
			}
			
		});
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(controller.sky);
		
		JLabel labelImmagine = new JLabel("");
		labelImmagine.setBorder(null);
		labelImmagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPizzeria.png")));
		labelImmagine.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblId = new JLabel("ID: " + l.getID());
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblId.setForeground(controller.egyptian);
		
		JLabel lblCreatoDa = new JLabel("Creato da: " + l.getNomeUtente());
		lblCreatoDa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCreatoDa.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCreatoDa.setForeground(controller.egyptian);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setForeground(controller.egyptian);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo: ");
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIndirizzo.setForeground(controller.egyptian);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setForeground(controller.egyptian);
		
		JLabel lblProprietario = new JLabel("Proprietario: ");
		lblProprietario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProprietario.setForeground(controller.egyptian);
		
		JLabel lblValutazione = new JLabel("Valutazione: ");
		lblValutazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValutazione.setForeground(controller.egyptian);
		
		String specializzazione = l.getAttributoAttivita();
		JLabel lblSpecializzazione = new JLabel("Tipo:");
		lblSpecializzazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecializzazione.setForeground(controller.egyptian);
		
		JPanel panelInformazioniUniche = new JPanel();
		panelInformazioniUniche.setBackground(getBackground());
		
		JLabel lblNomeVariabile = new JLabel(l.getNome());
		lblNomeVariabile.setForeground(controller.independence);
		
		JLabel lblIndirizzoVariabile = new JLabel(l.getIndirizzo());
		lblIndirizzoVariabile.setForeground(controller.independence);
		
		JLabel lblTelefonoVariabile = new JLabel(l.getTelefono());
		lblTelefonoVariabile.setForeground(controller.independence);
		
		JLabel lblProprietarioVariabile = new JLabel(l.getProprietario());
		lblProprietarioVariabile.setForeground(controller.independence);
		
		JLabel lblValutazioneVariabile = new JLabel(String.valueOf(l.getMediaRecensioni()));
		lblValutazioneVariabile.setForeground(controller.independence);
		
		JLabel lblSpecializzazioneVariabile = new JLabel(specializzazione);
		lblSpecializzazioneVariabile.setForeground(controller.independence);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(labelImmagine, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNomeVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIndirizzo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIndirizzoVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTelefonoVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProprietario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProprietarioVariabile))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValutazione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValutazioneVariabile)))
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSpecializzazione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSpecializzazioneVariabile))
						.addComponent(panelInformazioniUniche, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCreatoDa)
							.addGap(19))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblId)
							.addGap(27))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblNomeVariabile)
						.addComponent(lblCreatoDa)
						.addComponent(lblSpecializzazione)
						.addComponent(lblSpecializzazioneVariabile))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo)
								.addComponent(lblIndirizzoVariabile))
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono)
								.addComponent(lblTelefonoVariabile))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProprietario)
								.addComponent(lblProprietarioVariabile))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblValutazione)
								.addComponent(lblValutazioneVariabile)))
						.addComponent(lblId)
						.addComponent(panelInformazioniUniche, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(labelImmagine, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
		);
		SpringLayout sl_panelInformazioniUniche = new SpringLayout();
		panelInformazioniUniche.setLayout(sl_panelInformazioniUniche);
		
		Box verticalBox = Box.createVerticalBox();
		sl_panelInformazioniUniche.putConstraint(SpringLayout.NORTH, verticalBox, 0, SpringLayout.NORTH, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.WEST, verticalBox, 0, SpringLayout.WEST, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.SOUTH, verticalBox, 81, SpringLayout.NORTH, panelInformazioniUniche);
		sl_panelInformazioniUniche.putConstraint(SpringLayout.EAST, verticalBox, 135, SpringLayout.WEST, panelInformazioniUniche);
		panelInformazioniUniche.add(verticalBox);
		setLayout(groupLayout);
		
		switch(specializzazione) {
		case "Pizzeria": drawInformazioniUnichePizzeria(verticalBox, labelImmagine, l); break;
		case "Braceria": drawInformazioniUnicheBraceria(verticalBox, labelImmagine, l); break;
		case "Pub": drawInformazioniUnichePub(verticalBox, labelImmagine, l); break;
		case "Hotel": drawInformazioniUnicheHotel(verticalBox, labelImmagine, l); break;
		case "Motel": drawInformazioniUnicheMotel(verticalBox, labelImmagine, l); break;
		case "B&B": drawInformazioniUnicheBB(verticalBox, labelImmagine, l); break;
		case "Museo": drawInformazioniUnicheMuseo(verticalBox, labelImmagine, l); break;
		case "Zoo": drawInformazioniUnicheZoo(verticalBox, labelImmagine, l); break;
		case "Parco": drawInformazioniUnicheParco(verticalBox, labelImmagine, l); break;
		}
		
	}
	
	public void drawInformazioniUnichePizzeria(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPizzeria.png")));
		Pizzeria l = (Pizzeria)luogo;
		
		boolean vegano = l.isVegano();
		String cibo = l.getNazionalitaCibo();
		boolean asporto = l.isAsporto();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}

		if(asporto) {
			JLabel lblAsporto = new JLabel("Asporto");
			lblAsporto.setForeground(controller.independence);
			box.add(lblAsporto);
		}
		
		box.revalidate();
		box.repaint();
		
	}
	
	public void drawInformazioniUnicheBraceria(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaBraceria.png")));
		Braceria l = (Braceria)luogo;
		
		boolean vegano = l.isVegano();
		String cibo = l.getNazionalitaCibo();
		String carne = l.getTipoCarne();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}
		
		JLabel lblCarne = new JLabel("Carne: " + carne);
		lblCarne.setForeground(controller.independence);
		box.add(lblCarne);
		
		
		box.revalidate();
		box.repaint();
		
	}
	
	public void drawInformazioniUnichePub(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaPub.png")));
		Pub l = (Pub)luogo;
		
		boolean vegano = l.isVegano();
		String cibo = l.getNazionalitaCibo();
		String birra = l.getTipoBirra();
		
		JLabel lblCibo = new JLabel("Cibo: " + cibo);
		lblCibo.setForeground(controller.independence);
		box.add(lblCibo);
		
		if(vegano) {
			JLabel lblVegano = new JLabel("Vegano");
			lblVegano.setForeground(controller.independence);
			box.add(lblVegano);
		}
		
		JLabel lblBirra = new JLabel("Birra: " + birra);
		lblBirra.setForeground(controller.independence);
		box.add(lblBirra);
		
		
		box.revalidate();
		box.repaint();
		
	}
	
	public void drawInformazioniUnicheHotel(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaHotel.png")));
		Hotel l = (Hotel)luogo;
		
		boolean piscina = l.isPiscina();
		boolean wifi = l.isWifi();
		int numeroStanze = l.getNumeroStanze();
		
		int numeroStelle = l.getNumeroStelle();
		
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
		
		JLabel lblNumeroStelle = new JLabel("Stelle: " + numeroStelle);
		lblNumeroStelle.setForeground(controller.independence);
		box.add(lblNumeroStelle);
		
		box.revalidate();
		box.repaint();
		
	}
	
	public void drawInformazioniUnicheMotel(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaMotel.png")));
		Motel l = (Motel)luogo;
		
		boolean piscina = l.isPiscina();
		boolean wifi = l.isWifi();
		int numeroStanze = l.getNumeroStanze();
		
		boolean assistenza = l.isAssistenzaAuto();
		
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
		
		if(assistenza) {
			JLabel lblAssistenza = new JLabel("Assistenza vetture");
			lblAssistenza.setForeground(controller.independence);
			box.add(lblAssistenza);
		}
		
		box.revalidate();
		box.repaint();
		
	}

	public void drawInformazioniUnicheBB(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaBB.png")));
		BB l = (BB)luogo;
		
		boolean piscina = l.isPiscina();
		boolean wifi = l.isWifi();
		int numeroStanze = l.getNumeroStanze();
		
		boolean colazione = l.isColazione();
		
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

	public void drawInformazioniUnicheMuseo(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaMuseo.png")));
		Museo l = (Museo)luogo;
		
		boolean promozione = l.isPromozioneStudenti();
		
		String tipoMuseo = l.getTipoMuseo();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		JLabel lblTipoMuseo = new JLabel("Museo: " + tipoMuseo);
		lblTipoMuseo.setForeground(controller.independence);
		box.add(lblTipoMuseo);
		
		box.revalidate();
		box.repaint();
		
	}

	public void drawInformazioniUnicheZoo(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaZoo.png")));
		Zoo l = (Zoo)luogo;
		
		boolean promozione = l.isPromozioneStudenti();
		
		String specie = l.getSpecie();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		JLabel lblTipoSpecie = new JLabel("Specie: " + specie);
		lblTipoSpecie.setForeground(controller.independence);
		box.add(lblTipoSpecie);
		
		box.revalidate();
		box.repaint();
		
	}

	public void drawInformazioniUnicheParco(Box box, JLabel immagine, Luogo luogo) {
		
		immagine.setIcon(new ImageIcon(PanelInfoLuogoAnteprima.class.getResource("/icons/AnteprimaParco.png")));
		Parco l = (Parco)luogo;
		
		boolean promozione = l.isPromozioneStudenti();
		boolean gratuito = l.isIngressoGratuito();
		
		if(promozione) {
			JLabel lblPromozione = new JLabel("Promozione studenti");
			lblPromozione.setForeground(controller.independence);
			box.add(lblPromozione);
		}
		
		if(gratuito) {
			JLabel lblGratuito = new JLabel("Ingresso gratuito");
			lblGratuito.setForeground(controller.independence);
			box.add(lblGratuito);
		}
		
		box.revalidate();
		box.repaint();
		
	}

}
