
package t1IA;

public class Main {
	public static void main(String[] args) {
		String caminho = "C:\\Users\\luttb\\Desktop\\lab\\labirinto3_20.txt";
		
		
		// cria o labirinto
		Maze maze;
		try {
			maze = new Maze();
			maze.setMaze(caminho);
			Heuristic heu = new Heuristic(maze);
			Tela tela = new Tela(heu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
