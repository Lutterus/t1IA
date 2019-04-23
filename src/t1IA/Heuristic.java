package t1IA;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Heuristic {
	private int maze[][];
	private Maze matriz;
	private int localX;
	private int localY;
	private int tamanhoDaPopulacao = 8;
	private int tentativas = 20;

	public Heuristic(Maze maze) {
		this.matriz = maze;
		this.maze = matriz.getMatriz();
	}

	public void getEstrela(Graphics g, JPanel panel) {
		if (matriz.jaEncontrouSaida() == false) {
			System.err.println("saida nao encontrada, impossivel continuar");
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
			int[][] mazeCopy = new int[maze.length][maze.length];

			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze.length; j++) {
					mazeCopy[i][j] = maze[i][j];
				}
			}

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
			draw(g, panel, mazeCopy);
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

		int[][] mazeCopy = new int[maze.length][maze.length];

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				mazeCopy[i][j] = maze[i][j];
			}
		}

		// preparação para inicialização, destaque de todos as posições do labirinto que
		// nao são paredes
		List<Nodo> posicoeslivres = new ArrayList<Nodo>();
		preparaList(mazeCopy, posicoeslivres);

		List<List<Nodo>> populacao = new ArrayList<List<Nodo>>();
		populacao.addAll(geraPopulacaoInicial(posicoeslivres));

		List<Nodo> caminhoEncontrado = null;
		List<Nodo> melhorCaminhoAtual = null;
		caminhoEncontrado = saidaEncontrada(populacao, mazeCopy);
		if (caminhoEncontrado == null) {
			int i = 0;
			while (caminhoEncontrado == null && i < tentativas) {
				List<List<Nodo>> novaPopulacao = new ArrayList<List<Nodo>>();
				List<List<Nodo>> populacaoTemporaria = new ArrayList<List<Nodo>>();

				for (int j = 0; j < tamanhoDaPopulacao / 2; j++) {
					melhorCaminhoAtual = selecaoPorTorneio(populacao, mazeCopy);
					populacaoTemporaria.add(melhorCaminhoAtual);
					populacao.remove(melhorCaminhoAtual);

					melhorCaminhoAtual = selecaoPorTorneio(populacao, mazeCopy);
					populacaoTemporaria.add(melhorCaminhoAtual);
					populacao.remove(melhorCaminhoAtual);
					novaPopulacao.addAll(reproducao(populacaoTemporaria, posicoeslivres));
					populacaoTemporaria.clear();
				}
				populacao.clear();
				populacao.addAll(novaPopulacao);
				caminhoEncontrado = saidaEncontrada(populacao, mazeCopy);
				i++;
			}
			System.out.println("tentativa: " + i);
			draw(g, panel_1, mazeCopy, caminhoEncontrado);
		} else {
			System.out.println("tentativa: 1");
			draw(g, panel_1, mazeCopy, caminhoEncontrado);
		}

	}

	private List<List<Nodo>> reproducao(List<List<Nodo>> Populacao, List<Nodo> posicoeslivres) {
		List<String> instrucoesPai = new ArrayList<String>();
		List<String> instrucoesMae = new ArrayList<String>();
		List<List<Nodo>> novaPopulacao = new ArrayList<List<Nodo>>();

		Random r = new Random();
		int count = 0, countMutacao = 0;
		int random = r.nextInt(Populacao.get(0).size());
		int mutacaoQual = r.nextInt(1);
		for (List<Nodo> list : Populacao) {
			Nodo nodo = Populacao.get(0).get(0);
			instrucoesPai.add("ENTRADA");
			instrucoesMae.add("ENTRADA");
			if (count == 0) {
				for (Nodo n : list) {
					if (n != nodo) {
						if (n.getEixoX() == nodo.getEixoX()) {
							if (countMutacao == random && mutacaoQual == 0) {
								instrucoesPai.add("X");
								nodo = n;
							} else {
								instrucoesPai.add("Y");
								nodo = n;
							}

						} else {
							if (count == random) {
								instrucoesPai.add("Y");
								nodo = n;
							} else {
								instrucoesPai.add("X");
								nodo = n;
							}

						}
						countMutacao++;
					}

				}
				count = 1;
			} else if (count == 1) {
				for (Nodo n : list) {
					if (n != nodo) {
						if (n.getEixoX() == nodo.getEixoX()) {
							if (countMutacao == random && mutacaoQual == 1) {
								instrucoesMae.add("X");
								nodo = n;
							} else {
								instrucoesMae.add("Y");
								nodo = n;
							}

						} else {
							if (countMutacao == random) {
								instrucoesMae.add("Y");
								nodo = n;
							} else {
								instrucoesMae.add("X");
								nodo = n;
							}

						}
					}
					countMutacao++;
				}
				count = 0;
				novaPopulacao.add(geraFilhos(instrucoesPai, instrucoesMae, posicoeslivres));
				novaPopulacao.add(geraFilhos(instrucoesMae, instrucoesPai, posicoeslivres));
				instrucoesMae.clear();
				instrucoesPai.clear();
			} else {
				System.err.println("erro");
			}

		}

		return novaPopulacao;
	}

	private List<Nodo> geraFilhos(List<String> instrucoesPai, List<String> instrucoesMae, List<Nodo> posicoeslivres) {
		Collections.shuffle(posicoeslivres);
		List<String> filho = new ArrayList<String>();
		List<Nodo> filhoNodo = new ArrayList<Nodo>();
		for (int i = 0; i < (instrucoesPai.size() / 2); i++) {
			filho.add(instrucoesPai.get(i));
		}
		for (int i = (instrucoesMae.size() / 2); i < instrucoesMae.size(); i++) {
			filho.add(instrucoesMae.get(i));
		}

		int achou = 0;
		Nodo atual = null;

		for (String string : filho) {
			if (string.contentEquals("ENTRADA")) {
				for (Nodo n : posicoeslivres) {
					if (n.getEntrada() == true) {
						filhoNodo.add(n);
						atual = n;
						break;
					}
				}
			} else {
				if (string.contentEquals("Y")) {
					for (Nodo n : posicoeslivres) {
						if (n.getEixoX() == atual.getEixoX() && n.getEixoY() == atual.getEixoY() + 1
								|| n.getEixoX() == atual.getEixoX() && n.getEixoY() == atual.getEixoY() - 1) {
							filhoNodo.add(n);
							atual = n;
							achou = 1;
							break;
						}
					}
					if (achou == 0) {
						for (Nodo n : posicoeslivres) {
							if (n.getEixoY() == atual.getEixoY() && n.getEixoX() == atual.getEixoX() + 1
									|| n.getEixoY() == atual.getEixoY() && n.getEixoX() == atual.getEixoX() - 1) {
								filhoNodo.add(n);
								atual = n;
								achou = 1;
								break;
							}
						}
					}
				} else {
					for (Nodo n : posicoeslivres) {
						if (n.getEixoY() == atual.getEixoY() && n.getEixoX() == atual.getEixoX() + 1
								|| n.getEixoY() == atual.getEixoY() && n.getEixoX() == atual.getEixoX() - 1) {
							filhoNodo.add(n);
							atual = n;
							achou = 1;
							break;
						}
					}
				}
				if (achou == 0) {
					for (Nodo n : posicoeslivres) {
						if (n.getEixoX() == atual.getEixoX() && n.getEixoY() == atual.getEixoY() + 1
								|| n.getEixoX() == atual.getEixoX() && n.getEixoY() == atual.getEixoY() - 1) {
							filhoNodo.add(n);
							atual = n;
							achou = 1;
							break;
						}
					}
				}

			}
			achou = 0;
		}

		filhoNodo = preencheFilho(filhoNodo);
		return filhoNodo;
	}

	private List<Nodo> preencheFilho(List<Nodo> filhoNodo) {
		Nodo ultimovisitado = filhoNodo.get(filhoNodo.size() - 1);
		while (ultimovisitado.getProx().size() > 0) {
			Random r = new Random();
			int random = r.nextInt(ultimovisitado.getProx().size());
			ultimovisitado = ultimovisitado.getProx().get(random);
			filhoNodo.add(ultimovisitado);
		}

		return filhoNodo;
	}

	private List<Nodo> selecaoPorTorneio(List<List<Nodo>> populacao, int[][] mazeCopy) {
		Collections.shuffle(populacao);
		List<Nodo> individuo1 = null;
		List<Nodo> individuo2 = null;
		Random r = new Random();
		int posX = matriz.getSaida()[0];
		int posY = matriz.getSaida()[1];

		int random = r.nextInt(populacao.size());
		individuo1 = populacao.get(random);

		if (populacao.size() == 1) {
			return individuo1;
		} else {
			int random2 = r.nextInt(populacao.size());
			if (random == random2) {
				if (random2 > 0) {
					random2 = random2 - 1;
					individuo2 = populacao.get(random2);
				} else {
					random2 = random2 + 1;
					individuo2 = populacao.get(random2);
				}
			} else {
				individuo2 = populacao.get(random2);
			}

			int distancia1 = testeDistancia(individuo1, posX, posY);
			int distancia2 = testeDistancia(individuo2, posX, posY);

			if (distancia1 < distancia2) {
				return individuo1;
			} else {
				return individuo2;
			}
		}

	}

	private int testeDistancia(List<Nodo> individuo1, int posX, int posY) {
		int distancia = 999999999;
		for (Nodo n : individuo1) {
			int x = n.getEixoX() - posX;
			int y = n.getEixoY() - posY;
			if (x < 0) {
				x = x * (-1);
			}
			if (y < 0) {
				y = y * (-1);
			}

			if (distancia > (x + y)) {
				distancia = (x + y);
			}
		}

		return distancia;

	}

	private List<Nodo> saidaEncontrada(List<List<Nodo>> populacao, int[][] mazeCopy) {
		for (List<Nodo> list : populacao) {
			for (Nodo n : list) {
				if (mazeCopy[n.getEixoX()][n.getEixoY()] == 8) {
					matriz.setSaida(n.getEixoX(), n.getEixoY());
					return list;
				}
			}
		}
		return null;
	}

	private List<List<Nodo>> geraPopulacaoInicial(List<Nodo> posicoeslivres) {
		List<Nodo> populacao = new ArrayList<Nodo>();
		List<List<Nodo>> sequencia = new ArrayList<List<Nodo>>();
		Nodo entrada = null;
		for (Nodo nodos : posicoeslivres) {
			if (nodos.getEntrada() == true) {
				entrada = nodos;
			}
		}
		Nodo nodo = entrada;
		Random r = new Random();
		for (int i = 0; i < tamanhoDaPopulacao; i++) {
			Collections.shuffle(posicoeslivres);
			nodo = entrada;
			populacao.add(nodo);
			while (nodo.getProx().size() > 0) {
				int random = r.nextInt(nodo.getProx().size());
				if (populacao.contains(nodo.getProx().get(random)) == false) {
					nodo = nodo.getProx().get(random);
					populacao.add(nodo);
				} else {
					nodo.removeProx(random);
				}
			}
			if (populacao.size() > 0) {
				List<Nodo> temp = new ArrayList<Nodo>(populacao);
				sequencia.add(temp);
				populacao.clear();
			}
		}

		return sequencia;
	}

	private void preparaList(int[][] mazeCopy, List<Nodo> posicoeslivres) {
		int auxId = 0;
		for (int i = 0; i < mazeCopy.length; i++) {
			for (int j = 0; j < mazeCopy.length; j++) {
				if (mazeCopy[i][j] != 0) {

					if (matriz.getEntrada()[0] == i && matriz.getEntrada()[1] == j) {
						Nodo n = new Nodo(auxId, i, j);
						n.setEntrada();
						posicoeslivres.add(n);
					} else {
						posicoeslivres.add(new Nodo(auxId, i, j));
					}
					auxId++;
				}
			}
		}

		List<Nodo> COPYposicoeslivres = new ArrayList<Nodo>(posicoeslivres);
		while (COPYposicoeslivres.size() > 0) {
			Nodo n = COPYposicoeslivres.get(0);
			COPYposicoeslivres.remove(0);
			for (Nodo nodo : posicoeslivres) {
				if (valid(nodo, n) == true) {
					for (Nodo nodo2 : posicoeslivres) {
						if (nodo2 == n) {
							nodo2.setProx(nodo);
							break;
						}
					}
				}
			}

		}

	}

	private boolean valid(Nodo nodo, Nodo n) {
		if (nodo.getEixoX() == n.getEixoX() + 1 && nodo.getEixoY() == n.getEixoY()) {
			return true;
		} else if (nodo.getEixoX() == n.getEixoX() - 1 && nodo.getEixoY() == n.getEixoY()) {
			return true;
		} else if (nodo.getEixoY() == n.getEixoY() + 1 && nodo.getEixoX() == n.getEixoX()) {
			return true;
		} else if (nodo.getEixoY() == n.getEixoY() - 1 && nodo.getEixoX() == n.getEixoX()) {
			return true;
		}
		return false;
	}

	private void draw(Graphics g, JPanel panel_1, int[][] mazeCopy, List<Nodo> caminho) {
		if (caminho != null) {
			matriz.encontrouSaida();
			for (Nodo nodo : caminho) {
				if (mazeCopy[nodo.getEixoX()][nodo.getEixoY()] == 1) {
					mazeCopy[nodo.getEixoX()][nodo.getEixoY()]++;
				}

			}
		}

		int x = panel_1.getWidth() / 2;
		int y = maze.length / 2;
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

	private void draw(Graphics g, JPanel panel_1, int[][] mazeCopy) {
		int x = panel_1.getWidth() / 2;
		int y = maze.length / 2;
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
