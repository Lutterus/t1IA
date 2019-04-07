
package t1IA;

public class Main {
	public static void main(String[] args) {
		// cria o labirinto
		Maze maze = new Maze();
		maze.setMaze();
		Heuristic heu = new Heuristic(maze.getMatriz());
		Tela tela = new Tela(heu);
		// DrawMaze dm = new DrawMaze();

	}

}
