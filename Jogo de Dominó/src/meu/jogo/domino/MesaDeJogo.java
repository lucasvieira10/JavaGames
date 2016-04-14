package meu.jogo.domino;

import java.util.ArrayList;

public class MesaDeJogo {
	private static ArrayList<Peca> jogo = new ArrayList<>();
	
	public ArrayList<Peca> getJogo() {
		return jogo;
	}
	
	public String toString() {
		String mostrarJogo = "";
		for (int i = 0; i < jogo.size(); i++) {
			int[] peca = jogo.get(i).getPeca();
			mostrarJogo += "[" + peca[0] + "|" + peca[1] + "]";
		}
		return mostrarJogo;
	}
}
