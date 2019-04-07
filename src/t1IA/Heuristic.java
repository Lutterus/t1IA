package t1IA;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Heuristic {
	private int maze[][];
	private int localX;
	private int localY;

	public Heuristic(int[][] maze) {
		this.maze = maze;
	}

	public void getEstrela(Graphics g, JPanel panel) {
		//primeiro: implementar heuristica e corrigir a matriz
		//nomenclatura:
		//0: parede
		//1: caminho
		//2: por onde o agente passou
		//9: entrada
		//8: saida
		//segundo: deixar o algoritmo abaixo desenhar
				
		int x = panel.getWidth()/2;
		int y = maze.length/2;
		int z = x-(27*y);
		localX = z;
		localY = 0;
		g.setColor (Color.BLACK);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if(maze[i][j]==0) {
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;					
				}else if(maze[i][j]==2){	
					g.fillOval(localX+7, localY+7, 10, 10);
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
				}else if(maze[i][j]==1) {				
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
				}else if(maze[i][j]==9){
					g.setColor (Color.RED);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
					g.setColor (Color.BLACK);
				}else {
					g.setColor (Color.BLUE);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
					g.setColor (Color.BLACK);
				}
				
			}
			localX = z;
			localY = localY+27;
		}
		
	}
	
	public void getHeuristica(Graphics g, JPanel panel_1) {
		//primeiro: implementar heuristica e corrigir a matriz
		//nomenclatura:
		//0: parede
		//1: caminho
		//2: por onde o agente passou
		//9: entrada
		//8: saida
		//segundo: deixar o algoritmo abaixo desenhar
		
		//centralizando a matriz no eixo X
		int x = panel_1.getWidth()/2;
		int y = maze.length/2;
		int z = x-(27*y);
		
		localX = z;
		localY = 0;
		g.setColor (Color.BLACK);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if(maze[i][j]==0) {
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;					
				}else if(maze[i][j]==2){	
					g.fillOval(localX+7, localY+7, 10, 10);
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
				}else if(maze[i][j]==1) {				
					g.draw3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
				}else if(maze[i][j]==9){
					g.setColor (Color.RED);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
					g.setColor (Color.BLACK);
				}else {
					g.setColor (Color.BLUE);
					g.fill3DRect(localX, localY, 25, 25, false);
					localX = localX+27;
					g.setColor (Color.BLACK);
				}
				
			}
			localX = z;
			localY = localY+27;
		}
	}

}
