package t1IA;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

public class Heuristic {
	private int maze[][];
	private Maze matriz;
	private int localX;
	private int localY;

	public Heuristic(Maze maze) {
		this.matriz = maze;
		this.maze = matriz.getMatriz();
	}

	public void getEstrela(Graphics g, JPanel panel) {
		if (matriz.jaEncontrouSaida() == false) {
			System.out.println("saida nao encontrada, impossivel continuar");
		} else {
			// primeiro: implementar heuristica e corrigir a matriz
			// nomenclatura:
			// 0: parede
			// 1: caminho
			// 2: por onde o agente passou
			// 9: entrada
			// 8: saida

			List<Nodo> listaAberta = new ArrayList<Nodo>();
			List<Nodo> listaFechada = new ArrayList<Nodo>();
			int[][] mazeCopy = maze.clone();

			int primeiroNodo[] = matriz.getEntrada();
			Nodo pai = new Nodo(0, primeiroNodo[0], primeiroNodo[1]);
			listaAberta.add(pai);
			while ((pai.getEixoX() != matriz.getSaida()[0]) || (pai.getEixoY() != matriz.getSaida()[1])) {
				pai = listaAberta.get(0);
				if (listaFechada.size() == 0 || !listaFechada.contains(pai)) {
					if (pai.getEixoX() > 0) {
						Nodo nodo = new Nodo(pai.getId() + 1, pai.getEixoX() - 1, pai.getEixoY());
						nodo.setAnt(pai);
						nodo.setPesoH(nodo.getEixoX(), nodo.getEixoY(), matriz.getSaida());
						nodo.setPedoG(pai.getPesoG() + 1);
						nodo.setPesoF();
						if (mazeCopy[nodo.getEixoX()][nodo.getEixoY()] != 0 && !contem(listaFechada, nodo)) {
							listaAberta.add(nodo);
						}
					}

					if (pai.getEixoX() < (mazeCopy[0].length - 1)) {
						Nodo nodo = new Nodo(pai.getId() + 1, pai.getEixoX() + 1, pai.getEixoY());
						nodo.setAnt(pai);
						nodo.setPesoH(nodo.getEixoX(), nodo.getEixoY(), matriz.getSaida());
						nodo.setPedoG(pai.getPesoG() + 1);
						nodo.setPesoF();
						if (mazeCopy[nodo.getEixoX()][nodo.getEixoY()] != 0 && !contem(listaFechada, nodo)) {
							listaAberta.add(nodo);
						}
					}

					if (pai.getEixoY() > 0) {
						Nodo nodo = new Nodo(pai.getId() + 1, pai.getEixoX(), pai.getEixoY() - 1);
						nodo.setAnt(pai);
						nodo.setPesoH(nodo.getEixoX(), nodo.getEixoY(), matriz.getSaida());
						nodo.setPedoG(pai.getPesoG() + 1);
						nodo.setPesoF();
						if (mazeCopy[nodo.getEixoX()][nodo.getEixoY()] != 0 && !contem(listaFechada, nodo)) {
							listaAberta.add(nodo);
						}
					}

					if (pai.getEixoY() < (mazeCopy.length - 1)) {
						Nodo nodo = new Nodo(pai.getId() + 1, pai.getEixoX(), pai.getEixoY() + 1);
						nodo.setAnt(pai);
						nodo.setPesoH(nodo.getEixoX(), nodo.getEixoY(), matriz.getSaida());
						nodo.setPedoG(pai.getPesoG() + 1);
						nodo.setPesoF();
						if (mazeCopy[nodo.getEixoX()][nodo.getEixoY()] != 0 && !contem(listaFechada, nodo)) {
							listaAberta.add(nodo);
						}
					}
				}

				listaAberta.remove(pai);
				listaFechada.add(pai);
				listaAberta.sort(Comparator.comparing(Nodo::getPesoF));
			}

			/// corecao da matriz
			while (pai.getEixoX() != matriz.getEntrada()[0] || pai.getEixoY() != matriz.getEntrada()[1]) {
				if (mazeCopy[pai.getEixoX()][pai.getEixoY()] == 1) {
					mazeCopy[pai.getEixoX()][pai.getEixoY()]++;
					pai = pai.getAnt();
				} else {
					pai = pai.getAnt();
				}

			}

			// segundo: deixar o algoritmo abaixo desenhar

			int x = panel.getWidth() / 2;
			int y = mazeCopy.length / 2;
			int z = x - (27 * y);
			localX = z;
			localY = 0;
			g.setColor(Color.BLACK);
			for (int i = 0; i < mazeCopy.length; i++) {
				for (int j = 0; j < mazeCopy.length; j++) {
					if (mazeCopy[i][j] == 0) {
						g.fill3DRect(localX, localY, 25, 25, false);
						localX = localX + 27;
					} else if (mazeCopy[i][j] == 2) {
						g.fillOval(localX + 7, localY + 7, 10, 10);
						g.draw3DRect(localX, localY, 25, 25, false);
						localX = localX + 27;
					} else if (mazeCopy[i][j] == 1) {
						g.draw3DRect(localX, localY, 25, 25, false);
						localX = localX + 27;
					} else if (mazeCopy[i][j] == 9) {
						g.setColor(Color.RED);
						g.fill3DRect(localX, localY, 25, 25, false);
						localX = localX + 27;
						g.setColor(Color.BLACK);
					} else {
						g.setColor(Color.BLUE);
						g.fill3DRect(localX, localY, 25, 25, false);
						localX = localX + 27;
						g.setColor(Color.BLACK);
					}

				}
				localX = z;
				localY = localY + 27;
			}

		}

	}

	private boolean contem(List<Nodo> listaFechada, Nodo nodo) {
		if (listaFechada.size() == 0) {
			return false;
		}
		for (Nodo node : listaFechada) {
			if (node.getEixoX() == nodo.getEixoX() && node.getEixoY() == nodo.getEixoY()) {
				return true;
			}
		}
		return false;
	}

	public void getHeuristica(Graphics g, JPanel panel_1) {
		// primeiro: implementar heuristica e corrigir a matriz
		// nomenclatura:
		// 0: parede
		// 1: caminho
		// 2: por onde o agente passou
		// 9: entrada
		// 8: saida
		// segundo: deixar o algoritmo abaixo desenhar

		// centralizando a matriz no eixo X
		int x = panel_1.getWidth() / 2;
		int y = maze.length / 2;
		int z = x - (27 * y);

		localX = z;
		localY = 0;
		g.setColor(Color.BLACK);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] == 0) {
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX + 27;
				} else if (maze[i][j] == 2) {
					g.fillOval(localX + 7, localY + 7, 10, 10);
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX + 27;
				} else if (maze[i][j] == 1) {
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX + 27;
				} else if (maze[i][j] == 9) {
					g.setColor(Color.RED);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX + 27;
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.BLUE);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX + 27;
					g.setColor(Color.BLACK);
				}

			}
			localX = z;
			localY = localY + 27;
		}
	}

}
