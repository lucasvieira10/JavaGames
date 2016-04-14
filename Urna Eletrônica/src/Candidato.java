
public class Candidato {
	private String nomeCandidato;
	private String numeroCandidato;
	private int numeroDeVotos;

	public Candidato(String nomeCandidato, String numeroCandidato) {
		this.nomeCandidato = nomeCandidato;
		this.numeroCandidato = numeroCandidato;
	}
	
	public void incrementaVotos() {
		this.numeroDeVotos += 1;
	}

	public String getNumeroCandidato() {
		return this.numeroCandidato;
	}
	
	public String getNomeCandidato() {
		return this.nomeCandidato;
	}
	
	public int getNumeroDeVotos() {
		return this.numeroDeVotos;
	}
}
