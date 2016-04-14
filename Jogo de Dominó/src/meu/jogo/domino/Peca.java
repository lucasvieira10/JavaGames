package meu.jogo.domino;

public class Peca {
	private int ladoEsquerdo;
	private int ladoDireito;

	public Peca(int ladoEsquerdo, int ladoDireito) {
		this.ladoEsquerdo = ladoEsquerdo;
		this.ladoDireito = ladoDireito;
	}
	
	public void virarLado() {
		int auxiliar = ladoEsquerdo;
		ladoEsquerdo = ladoDireito;
		ladoDireito = auxiliar;
	}
	
	public int[] getPeca() {
		int[] peca = { ladoEsquerdo, ladoDireito };
		return peca;
	}
}
