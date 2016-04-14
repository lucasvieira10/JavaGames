package meu.jogo.domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Domino {
	private ArrayList<Peca> domino = new ArrayList<>();

	public void pecasDomino() {
		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				domino.add(new Peca(i, j));
			}
		}
	}
	
	public int pecasRestantes() {
		return domino.size();
	}
	
	public boolean quantiaDePecas() {
		return domino.size() > 0 ? true : false;
	}
	
	public Peca fornecePeca() {
		return domino.remove(new Random().nextInt(domino.size()));
	}

	public void embaralharDomino() {
		Collections.shuffle(domino);
	}
}
