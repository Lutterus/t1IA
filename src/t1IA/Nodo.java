package t1IA;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

	private int pesoF;
	private int pesoG;
	private int pesoH;
	private int idNo;
	private Nodo ant;
	private int eixoX;
	private int eixoY;
	private List<Nodo> proximo;
	private boolean entrada;

	public Nodo(int id, int eixoX, int eixoY) {
		this.idNo = id;
		pesoF = 0;
		pesoG = 0;
		pesoH = 0;
		ant = null;
		this.eixoX = eixoX;
		this.eixoY = eixoY;
		this.proximo = null;
		proximo = new ArrayList<Nodo>();
		this.entrada = false;
	}

	public boolean getEntrada() {
		return entrada;
	}

	public void setEntrada() {
		this.entrada = true;
	}

	public void removeProx(int pos) {
		proximo.remove(pos);
	}

	public List<Nodo> getProx() {
		return proximo;
	}

	public void setProx(Nodo n) {
		proximo.add(n);
	}

	public int getId() {
		return idNo;
	}

	public void setAnt(Nodo n) {
		ant = n;
	}

	public void setPesoF() {
		this.pesoF = pesoG + pesoH;
	}

	public void setPedoG(int pesoG) {
		this.pesoG = pesoG;
	}

	public void setPesoH(int x, int y, int[] saida) {
		this.pesoH = calculaPesoH(x, y, saida);
	}

	private int calculaPesoH(int x, int y, int[] saida) {
		int valorX = saida[0] - x;
		if (valorX < 0) {
			valorX = valorX * (-1);
		}

		int valorY = saida[1] - y;
		if (valorY < 0) {
			valorY = valorY * (-1);
		}

		return (valorX + valorY);
	}

	public Nodo getAnt() {
		return ant;
	}

	public int getPesoF() {
		return pesoF;
	}

	public int getPesoG() {
		return pesoG;
	}

	public int getPesoH() {
		return pesoH;
	}

	public int getEixoX() {
		return eixoX;
	}

	public int getEixoY() {
		return eixoY;
	}

	public void fakeSetPesoF(int i) {
		this.pesoF = i;

	}
}
