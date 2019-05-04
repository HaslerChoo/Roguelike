package launcher;

public class Elemento {
	private int x;
	private int y;
	private int velocidade;
	private int comprimento;
	private int largura;

	public Elemento(int x, int y, int comprimento, int largura) {
		super();
		this.x = x;
		this.y = y;
		this.comprimento = comprimento;
		this.largura = largura;
	}

	public Elemento(int x, int y, int velocidade, int comprimento, int largura) {
		super();
		this.x = x;
		this.y = y;
		this.velocidade = velocidade;
		this.comprimento = comprimento;
		this.largura = largura;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

}
