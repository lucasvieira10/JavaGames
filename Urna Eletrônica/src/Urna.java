import java.util.ArrayList;

public class Urna {
	private int branco, nulo;
	private ArrayList<Candidato> candidato = new ArrayList<>();

	public Candidato pesquisaCandidato(String numero) {
		for (int i = 0; i < candidato.size(); i++) {
			if (candidato.get(i).getNumeroCandidato().equals(numero)) {
				return candidato.get(i);
			}
		}
		return null;
	}

	public void votar(String numero) {
		Candidato candidato = pesquisaCandidato(numero);
		if (candidato != null) {
			candidato.incrementaVotos();
		} else if (numero.equals("00")) {
			branco++;
		} else {
			nulo++;
		}
	}

	public String verCandidato() {
		return "CANDIDATOS:\n#13 - Dilma\n#45 - Aécio\n#11 - Bolsonaro\n#00 - Branco";
	}
	
	
	public void addCandidato() {
		this.candidato.add(new Candidato("Dilma", "13"));
		this.candidato.add(new Candidato("Aécio", "45"));
		this.candidato.add(new Candidato("Bolsonaro", "11"));
	}

	public double totalDeVotos() {
		int total = 0;
		for (int i = 0; i < candidato.size(); i++) {
			total += candidato.get(i).getNumeroDeVotos();
		}
		total += branco + nulo;
		return total;
	}

	public String percentagem(int valor) {
		return String.format("%.2f", valor / totalDeVotos() * 100);
	}

	public String vencedor() {
		double maiorVoto = 0;
		String ganhou = "";
		for (int i = 0; i < candidato.size(); i++) {
			if (candidato.get(i).getNumeroDeVotos() > maiorVoto) {
				maiorVoto = candidato.get(i).getNumeroDeVotos();
				ganhou = candidato.get(i).getNomeCandidato() + "-" + candidato.get(i).getNumeroCandidato();
			}
		}
		int contadorIguais = 0;
		for (int i = 0; i < candidato.size(); i++) {
			if (candidato.get(i).getNumeroDeVotos() == maiorVoto) {
				contadorIguais++;
				if (contadorIguais > 1)
					return "**********EMPATE ENTRE OS CANDIDATOS!**********";
			}
		}
		return "************O VENCEDOR FOI: " + ganhou.toUpperCase() + "***********";
	}

	public String toString() {
		String resultado = "\n=====================VOTOS=====================\n";
		for (int i = 0; i < candidato.size(); i++) {
			resultado += "\n- Candidato(a) " + candidato.get(i).getNomeCandidato() + ": "
					+ candidato.get(i).getNumeroDeVotos() + " voto(s), "
					+ percentagem(candidato.get(i).getNumeroDeVotos()) + "%.";
		}

		resultado += "\n- Brancos: " + branco + " voto(s), " + percentagem(branco) + "%." + "\n- Nulos: " + nulo
				+ " voto(s), " + percentagem(nulo) + "%." + "\n\n" + vencedor();
		return resultado;
	}
}
