import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame {

	private Urna urna = new Urna();
	private JTextField numero;

	public Interface() {
		super("Urna Eletrônica Digital");
		urna.addCandidato();
		criaMenu();
		criaFormulario();
	}

	private void criaMenu() {

		JMenu menuCandidato = new JMenu("Candidato");

		VerCandidatos verCandidato = new VerCandidatos();
		sobreUrna sobreUrna = new sobreUrna();

		JMenuItem menuItemCandidatos = new JMenuItem("Ver Candidatos");
		menuItemCandidatos.addActionListener(verCandidato);
		menuCandidato.add(menuItemCandidatos);

		JMenu menuAjuda = new JMenu("Ajuda");

		JMenuItem menuItemAjuda = new JMenuItem("Sobre...");
		menuItemAjuda.addActionListener(sobreUrna);
		menuAjuda.add(menuItemAjuda);

		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(menuCandidato);
		barra.add(menuAjuda);
	}

	private void criaFormulario() {
		String quantiaVotos = JOptionPane.showInputDialog(null, "Digite a quntidade de Eleitores: ",
				"Quantidade de Eleitores", JOptionPane.PLAIN_MESSAGE);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		
		JLabel titulo = new JLabel("Digite o número do Candidato:");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 16));

		panelTitulo.add(titulo);
		
		JPanel panelVotar = new JPanel();
		panelVotar.setLayout(new FlowLayout());
		
		numero = new JTextField(40);
		
		panelVotar.add(numero);
		
		add(panelTitulo, BorderLayout.NORTH);
		add(panelVotar, BorderLayout.CENTER);
	}

	private class VerCandidatos implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, urna.verCandidato(), "Todos os Candidatos", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private class sobreUrna implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Programa de Urna Eletrônica Digital\n@author: Lucas Lima Vieira\n@version: 1.0",
					"Sobre o Programa", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
