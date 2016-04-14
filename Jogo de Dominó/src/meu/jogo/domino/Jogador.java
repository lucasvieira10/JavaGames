package meu.jogo.domino;

import java.util.ArrayList;
import java.util.Random;

public class Jogador {
	private ArrayList<Peca> pecasJogador = new ArrayList<>();
	
	public void pegaPeca(Peca peca) {
		pecasJogador.add(peca);
	}

	public boolean quantiaDePecas() {
		return pecasJogador.size() > 0 ? true : false;
	}
	
	public int confereTamanho() {
		return pecasJogador.size();
	}
	
	public int[] obterPecaIndice(int indice) {
		for (int i = 0; i < pecasJogador.size(); i++) {
			if (indice == i)
				return pecasJogador.get(i).getPeca();
		}
		return null;
	}
		
	public ArrayList<Peca> getPecasJogador() {
		return pecasJogador;
	}

	public Peca pecaDobradaMaisAlta() {
		int[] carrocao = { 0, 0 };
		Peca peca = null;
		for (int i = 0; i < pecasJogador.size(); i++) {
			for (int j = pecasJogador.size(); j >= 0; j--) {
				if (pecasJogador.get(i).getPeca()[0] == j && pecasJogador.get(i).getPeca()[1] == j) {
					if (pecasJogador.get(i).getPeca()[0] > carrocao[0]
							&& pecasJogador.get(i).getPeca()[1] > carrocao[1]) {
						carrocao = pecasJogador.get(i).getPeca();
						peca = pecasJogador.get(i);
					}
				}
			}
		}
		return peca;
	}

	public Peca pecaAleatoria() {
		return pecasJogador.get(new Random().nextInt(pecasJogador.size()));
	}

	public Peca jogarPeca(Peca peca, int permicao) {
		for (int i = 0; i < pecasJogador.size(); i++) {
			if (peca.getPeca()[0] == pecasJogador.get(i).getPeca()[0]
					&& peca.getPeca()[1] == pecasJogador.get(i).getPeca()[1]) {
				if (permicao == 1)
					return pecasJogador.remove(i);
				else
					return pecasJogador.get(i);
			}
		}
		return null;
	}
	
	public String mostrarJogoPc() {
		String meuJogo = "";
		for (int i = 0; i < pecasJogador.size(); i++) {
			meuJogo += "[" + "X|Y" + "] ";
		}
		return meuJogo;
	}

	public String mostrarJogoJogador() {
		String meuJogo = "";
		for (int i = 0; i < pecasJogador.size(); i++) {
			int[] peca = pecasJogador.get(i).getPeca();
			meuJogo += i + " =>[" + peca[0] + "|" + peca[1] + "] ";
		}
		return meuJogo;
	}

	public int contagemPontos() {
		int pontos = 0;
		for (int i = 0; i < pecasJogador.size(); i++) {
			pontos += pecasJogador.get(i).getPeca()[0] + pecasJogador.get(i).getPeca()[1];
		}
		return pontos;
	}
	
	public String vencedor(Jogador jogador, Jogador pc) {
		if (jogador.contagemPontos() < pc.contagemPontos())
			return "Você venceu! ;)";
		return "Você perdeu! :(";
	}
}
