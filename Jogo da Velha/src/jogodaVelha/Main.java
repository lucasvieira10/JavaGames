package jogodaVelha;
import java.util.Scanner;

/*
 * My first game: naughts and crosses!
 * @author Lucas Lima Vieira
 * @version 1.0
 */

public class Main {
	public static void main(String[] args) {
		int novamente = 1;
		
		while (novamente == 1) {
			Scanner in = new Scanner(System.in);
			JogoVelha jogo = new JogoVelha();
			int linha, coluna;
			String jog1, jog2;
			int valida = 0, jogadas = 0;
			
			System.out.println("\n@author: Lucas Lima Vieira\n---Jogo da Velha---");
			System.out.println(jogo.toString());
			System.out.print("Nome do jogador 1 (X): ");
			jog1 = in.nextLine();
			System.out.print("Nome do jogador 2 (O): ");
			jog2 = in.nextLine();
			
			while (true) {			
				do { //starts player 1
					System.out.print(jog1+", informe posição (Ex: 0 2): ");
					linha = in.nextInt();
					coluna = in.nextInt();
					while (jogo.valido(linha, coluna) || (linha < 0 && linha > 2) || (coluna < 0 && coluna > 2)) {
						System.out.println("Jogada Inválida, tente novamente!");
						System.out.print(jog1+", informe posição (Ex: 0 2): ");
						linha = in.nextInt();
						coluna = in.nextInt();
						valida = 0;
					}
					jogo.jogada(linha, coluna, "X");
					valida = 1;
				} while (valida == 0); //ends player 1
				
				jogadas++;
				valida = 0;
				System.out.println(jogo.toString());
				if (!jogo.ganhou(jogadas, jog1, jog2).equals("null"))
					break;
				
				do { //starts player 2
					System.out.print(jog2+", informe posição (Ex: 0 2): ");
					linha = in.nextInt();
					coluna = in.nextInt();
					while (jogo.valido(linha, coluna) || (linha < 0 && linha > 2) || (coluna < 0 && coluna > 2)) {
						System.out.println("Jogada Inválida, tente novamente!");
						System.out.print(jog2+", informe posição (Ex: 0 2): ");
						linha = in.nextInt();
						coluna = in.nextInt();
						valida = 0;
					}
					jogo.jogada(linha, coluna, "O");
					valida = 1;
				} while (valida == 0); //ends player 2
				
				jogadas++;
				valida = 0;
				System.out.println(jogo.toString());
				if (!jogo.ganhou(jogadas, jog1, jog2).equals("null"))
					break;
				
			}
		System.out.println("\n---Jogo da Velha---");
		if (jogo.ganhou(jogadas, jog1, jog2).equals("empate"))
			System.out.println("Empate entre os jogadores!");
		else
			System.out.println("Parabéns, "+jogo.ganhou(jogadas, jog1, jog2)+" venceu!");
			System.out.print("Deseja jogar novamente (1 - sim, 0 - não): ");
			novamente = in.nextInt();
		}
	}
}
