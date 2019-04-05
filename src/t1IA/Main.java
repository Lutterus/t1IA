
package t1IA;

public class Main {
	public static void main(String[] args) {
		// cria o labirinto
		Maze maze = new Maze();
		maze.setMaze();
		
		DrawMaze dm = new DrawMaze(maze.getMatriz());
		
		Heuristic heu = new Heuristic();

	}

}
