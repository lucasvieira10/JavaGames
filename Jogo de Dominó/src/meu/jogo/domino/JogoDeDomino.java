/*
 * @author: Lucas Lima Vieira
 * @version: 1.0
 */

package meu.jogo.domino;

import java.util.ArrayList;
import java.util.Scanner;

public class JogoDeDomino {

	public static final int PECAS_A_DISTRIBUIR = 7;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		MesaDeJogo jogo = new MesaDeJogo();
		Domino domino = new Domino();
		domino.pecasDomino();
		domino.embaralharDomino();

		Jogador jogador = new Jogador();
		distribuiPecas(domino, jogador);
		Jogador pc = new Jogador();
		distribuiPecas(domino, pc);
		iniciaPartida(jogador, pc, domino, pc.getPecasJogador(), jogo.getJogo());
		System.out.println(detalhesJogo(domino, jogo, jogador, pc));

		while (!pararJogo(domino, jogador, pc, jogo.getJogo())) {
			// player:
			while (domino.quantiaDePecas() && !pecasRestantes(jogador, jogo.getJogo())) {
				System.out.print("+ Precisa-se de Peça, digite (1): ");
				String pegarPeca = in.next();
				if (pegarPeca.equals("1")) {
					jogador.pegaPeca(domino.fornecePeca());
					if (!domino.quantiaDePecas() && pecasRestantes(jogador, jogo.getJogo())) {
						System.out.print("\n" + detalhesJogo(domino, jogo, jogador, pc) + "\n");
						break;
					}
					System.out.print("\n" + detalhesJogo(domino, jogo, jogador, pc) + "\n");
				}
			}
			//if (pararJogo(domino, jogador, pc, jogo.getJogo())) break;
			if (jogador.quantiaDePecas() && pecasRestantes(jogador, jogo.getJogo())) {
				while (true) {
					System.out.print("- Digite o indíce da Peça: ");
					int indice = in.nextInt(), ladoEsquerdo = 0, ladoDireito = 0;
					if (indice >= 0 && indice < jogador.confereTamanho()) {
						ladoEsquerdo = jogador.obterPecaIndice(indice)[0];
						ladoDireito = jogador.obterPecaIndice(indice)[1];
					} else {
						System.out.println("\n- Peça inválida!\n");
						continue;
					}
					Peca peca = new Peca(ladoEsquerdo, ladoDireito);
					if (jogador.jogarPeca(peca, 0) != null) {
						int permicao = podeJogar(new Peca(ladoEsquerdo, ladoDireito), jogo.getJogo());
						if (permicao != 0) {
							jogador.jogarPeca(peca, 1);
							addPeca(peca, podeJogar(peca, jogo.getJogo()), jogo.getJogo());
							break;
						} else {
							System.out.println("\n- Peça inválida!\n");
							continue;
						}
					} else {
						System.out.println("\n- Peça inválida!\n");
						continue;
					}
				}
				System.out.println("\n+ Você jogou!");
			}
			//if (pararJogo(domino, jogador, pc, jogo.getJogo())) break;
			// computer:
			System.out.println("+ O PC jogou!");
			while (pc.quantiaDePecas() || pecasRestantes(pc, jogo.getJogo())) {
				boolean jogou = false;
				for (int i = 0; i < pc.getPecasJogador().size(); i++) {
					Peca peca = pc.getPecasJogador().get(i);
					if (podeJogar(peca, jogo.getJogo()) != 0) {
						pc.jogarPeca(peca, 1);
						addPeca(peca, podeJogar(peca, jogo.getJogo()), jogo.getJogo());
						jogou = true;
						break;
					}
				}
				if (jogou)
					break;
				else {
					if (domino.quantiaDePecas())
						pc.pegaPeca(domino.fornecePeca());
					else
						break;
				}
			}
			//if (pararJogo(domino, jogador, pc, jogo.getJogo())) break;
			System.out.println("\n" + detalhesJogo(domino, jogo, jogador, pc));
		}
		System.out.println("\n+++++++++++++++++++++++ FIM DE JOGO +++++++++++++++++++++++\n\n" 
			+ detalhesJogo(domino, jogo, jogador, pc) + "\n- " + jogador.vencedor(jogador, pc));
		in.close();
	}

	private static String detalhesJogo(Domino domino, MesaDeJogo jogo, Jogador jogador, Jogador pc) {
		return "+ JOGO DE DOMINÓ => " + domino.pecasRestantes() + " PEÇAS RESTANTES NA MESA." + "\n\n" + jogo
				+ "\n\n========================= MEU JOGO =========================\n" + jogador.mostrarJogoJogador()
				+ "\n\n========================= PC JOGO ==========================\n" + pc.mostrarJogoPc() + "\n";
	}

	private static void distribuiPecas(Domino domino, Jogador jogador) {
		for (int i = 0; i < PECAS_A_DISTRIBUIR; i++) {
			jogador.pegaPeca(domino.fornecePeca());
		}
	}

	public static boolean pararJogo(Domino domino, Jogador jogador, Jogador pc, ArrayList<Peca> jogo) {
		return (!domino.quantiaDePecas() && !pecasRestantes(jogador, jogo) && !pecasRestantes(pc, jogo))
				|| (!jogador.quantiaDePecas() || !pc.quantiaDePecas());
	}

	public static boolean pecasRestantes(Jogador jogador, ArrayList<Peca> jogo) {
		// grants pieces of the players:
		boolean podeJogar = false;
		for (int i = 0; i < jogador.getPecasJogador().size(); i++) {
			if (podeJogar(jogador.getPecasJogador().get(i), jogo) != 0) {
				podeJogar = true;
				break;
			}
		}
		return podeJogar;
	}

	private static void iniciaPartida(Jogador jogador, Jogador pc, Domino domino, ArrayList<Peca> pecaJog,
			ArrayList<Peca> jogo) {
		Peca dobradaMaisAlta1 = jogador.pecaDobradaMaisAlta();
		Peca dobradaMaisAlta2 = pc.pecaDobradaMaisAlta();

		if (dobradaMaisAlta1 == null && dobradaMaisAlta2 == null) {
			jogo.add(pc.jogarPeca(pc.pecaAleatoria(), 1));
		} else if (dobradaMaisAlta1 == null) {
			jogo.add(pc.jogarPeca(dobradaMaisAlta2, 1));
		} else if (dobradaMaisAlta2 == null) {
			jogo.add(jogador.jogarPeca(dobradaMaisAlta1, 1));
		} else if (dobradaMaisAlta1.getPeca()[0] > dobradaMaisAlta2.getPeca()[0]) {
			jogo.add(jogador.jogarPeca(dobradaMaisAlta1, 1));
		} else {
			jogo.add(pc.jogarPeca(dobradaMaisAlta2, 1));
		}
		if (jogador.confereTamanho() < 7) {
			System.out.println("- Seu carroção iniciou a partida!\n");
			while (domino.quantiaDePecas()) {
				boolean jogou = false;
				for (int i = 0; i < pecaJog.size(); i++) {
					Peca peca = pecaJog.get(i);
					if (podeJogar(peca, jogo) != 0) {
						pc.jogarPeca(peca, 1);
						addPeca(peca, podeJogar(peca, jogo), jogo);
						jogou = true;
						break;
					}
				}
				if (jogou) {
					System.out.println("+ Você jogou!\n+ O PC jogou!\n");
					break;
				}
				pc.pegaPeca(domino.fornecePeca());
			}
		} else {
			System.out.println("- O carroção do PC iniciou a partida!\n");
		}
	}

	public static void addPeca(Peca peca, int extremo, ArrayList<Peca> jogo) {
		if (extremo == 1)
			jogo.add(0, peca);
		else if (extremo == 2) {
			jogo.add(peca);
		}
	}

	private static int podeJogar(Peca peca, ArrayList<Peca> jogo) {
		int ladoEsquerdo = jogo.get(0).getPeca()[0];
		int ladoDireito = jogo.get(jogo.size() - 1).getPeca()[1];
		if (peca.getPeca()[0] == ladoEsquerdo) {
			peca.virarLado();
			return 1;
		} else if (peca.getPeca()[1] == ladoDireito) {
			peca.virarLado();
			return 2;
		} else if (peca.getPeca()[0] == ladoDireito) {
			return 2;
		} else if (peca.getPeca()[1] == ladoEsquerdo) {
			return 1;
		} else {
			return 0;
		}
	}
}
