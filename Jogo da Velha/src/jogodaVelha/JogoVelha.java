package jogodaVelha;

/*
 * My first game: naughts and crosses!
 * @author Lucas Lima Vieira
 * @version 1.0
 */

public class JogoVelha {

	private String matriz[][] = { { "00", "01", "02" }, { "10", "11", "12" }, { "20", "21", "22" } };

	public boolean valido(int linha, int coluna) {
		return (matriz[linha][coluna].equals(" X") || matriz[linha][coluna].equals(" O"));
	}

	public void jogada(int linha, int coluna, String jog) {
		matriz[linha][coluna] = " " + jog;
	}

	public String ganhou(int jogadas, String jog1, String jog2) {
		String t[] = new String[8];
		String vencedor = "null";
		if (jogadas == 9)
			vencedor = "empate";

		t[0] = matriz[0][0] + matriz[0][1] + matriz[0][2];
		t[1] = matriz[1][0] + matriz[1][1] + matriz[1][2];
		t[2] = matriz[2][0] + matriz[2][1] + matriz[2][2];

		t[3] = matriz[0][0] + matriz[1][0] + matriz[2][0];
		t[4] = matriz[0][1] + matriz[1][1] + matriz[2][1];
		t[5] = matriz[0][2] + matriz[1][2] + matriz[2][2];

		t[6] = matriz[0][0] + matriz[1][1] + matriz[2][2];
		t[7] = matriz[0][2] + matriz[1][1] + matriz[2][0];

		for (int i = 0; i < t.length; i++) {
			if (t[i].equals(" X X X"))
				vencedor = jog1;
			else if (t[i].equals(" O O O"))
				vencedor = jog2;
		}
		return vencedor;
	}

	public String toString() {
		String m = "\n";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m += " " + matriz[i][j];
				if (j < 2)
					m += "  | ";
				if (j == 2)
					m += "\n";
				if (j == 2 && i < 2)
					m += "------------------\n";
			}
		}
		return m;
	}
}
