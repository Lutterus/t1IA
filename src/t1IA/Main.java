
package t1IA;

public class Main {
	public static void main(String[] args) {
		// cria o labirinto
		Maze maze;
		try {
			maze = new Maze();
			maze.setMaze();
			Heuristic heu = new Heuristic(maze);
			Tela tela = new Tela(heu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
