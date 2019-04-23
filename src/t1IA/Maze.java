package t1IA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.security.util.Length;

public class Maze {
	private int[][] maze;
	private int confirmaSaida = 0;
	private int entrada[] = new int[2];
	private int saida[] = new int[2];

	public Maze() throws Exception {

	}

	public void setMaze(String caminho) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "UTF8"));

			String line;
			line = br.readLine();
			maze = new int[Integer.parseInt(line)][Integer.parseInt(line)];
			int j = 0;
			int k = 0;
			while ((line = br.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					if (i % 2 == 0) {
						if (line.charAt(i) == '0') {
							maze[j][k] = 1;
							k++;
						} else if (line.charAt(i) == '1') {
							maze[j][k] = 0;
							k++;
						} else if (line.charAt(i) == 'E') {
							maze[j][k] = 9;
							setEntrada(j, k);
							k++;
						} else if (line.charAt(i) == 'S') {
							maze[j][k] = 8;
							setSaida(j, k);
							k++;
						} else {
							System.out.println("char nao reconhecido");
							System.out.println(line.charAt(i));
							System.err.println("aaaaaa");
						}

					}
				}
				j++;
				k = 0;
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[][] getMatriz() {
		return maze;

	}

	public int[] getEntrada() {
		return entrada;
	}

	public void setEntrada(int x, int y) {
		this.entrada[0] = x;
		this.entrada[1] = y;
	}

	public int[] getSaida() {
		return saida;
	}

	public void setSaida(int x, int y) {
		this.saida[0] = x;
		this.saida[1] = y;
		confirmaSaida = 0;
	}

	public boolean jaEncontrouSaida() {
		if (confirmaSaida == 1) {
			return true;
		}
		return false;
	}

	public void encontrouSaida() {
		confirmaSaida = 1;
	}

}
