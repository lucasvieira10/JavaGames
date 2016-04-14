import javax.swing.JFrame;

public class Votacao {
	public static void main(String[] args) {
		Interface urna = new Interface();
		urna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		urna.setSize(600, 400);
		urna.setVisible(true);
		urna.setResizable(false);
		
		
		
		/*
		Scanner in = new Scanner(System.in);
		Urna urna = new Urna();
		urna.addCandidato(new Candidato("Dilma", "13")); 
		urna.addCandidato(new Candidato("Aécio", "45")); 
		urna.addCandidato(new Candidato("Bolsonaro", "11"));
		
		System.out.print("============URNA ELETRÔNICA DIGITAL============\n\n"
				+ "- Digite a quantidade de eleitores: ");
		int quantia = in.nextInt();
		System.out.println("\n********OPÇÕES DE NÚMEROS DE CANDIDATOS********"
				+ "\n(13-Dilma, 45-Aécio, 11-Bolsonaro ou 00-Branco)\n");
		
		for (int i = 0; i < quantia; i++) {
			System.out.print("- Número do Candidato: ");
			String numero = in.next();
			urna.votar(numero);
		}
		System.out.println(urna);
		in.close();
		*/
	}
}
