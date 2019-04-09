package t1IA;

public class Maze {
	int[][] maze = { { 9, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
					 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					 { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, 
					 { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 }, 
					 { 1, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
					 { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, 
					 { 1, 0, 0, 0, 0, 1, 1, 0, 1, 8 }, 
					 { 1, 0, 1, 1, 1, 1, 1, 1, 0, 0 },
					 { 1, 0, 0, 0, 0, 0, 0, 1, 1, 0 }, 
					 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, };
	
	private int entrada[] = new int[2];
	private int saida[] = new int[2];
	public Maze() throws Exception {
		entrada [0]=999999999;
		for(int i = 0; i<maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if(maze[i][j]==9) {
					setEntrada(i,j);
					break;
				}
			}
		}
		
		if(entrada[0]==999999999) {
			System.out.println("entrada nao encontrada");
			throw new Exception();
		}else {
			System.out.println("entrada encontrada");
			for(int i = 0; i<maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					if(maze[i][j]==8) {
						setSaida(i,j);
						System.out.println("saida encontrada");
						break;
					}
				}
			}
		}
		
	}

	public void setMaze() {

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
	}

}
