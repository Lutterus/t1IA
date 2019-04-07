package t1IA;

public class Maze {
	int[][] maze = { { 9, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
					 { 2, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					 { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, 
					 { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 }, 
					 { 1, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
					 { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, 
					 { 1, 0, 0, 0, 0, 1, 1, 0, 1, 8 }, 
					 { 1, 0, 1, 1, 1, 1, 1, 1, 0, 0 },
					 { 1, 0, 0, 0, 0, 0, 0, 1, 1, 0 }, 
					 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, };
	
	private int entrada[][];
	private int saida[][];
	public Maze() {

	}

	public void setMaze() {

	}

	public int[][] getMatriz() {
		return maze;

	}

	public int[][] getEntrada() {
		return entrada;
	}

	public void setEntrada(int entrada[][]) {
		this.entrada = entrada;
	}

	public int[][] getSaida() {
		return saida;
	}

	public void setSaida(int saida[][]) {
		this.saida = saida;
	}

}
