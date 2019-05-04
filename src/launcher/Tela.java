package launcher;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tela extends JPanel {

	private Elemento tiro;
	private Elemento jogador;
	private Elemento blocos[];
	private int linhaLimite;
	private int pontos;

	public Tela(Elemento tiro, Elemento jogador, Elemento[] blocos, int linhaLimite, int pontos) {
		super();
		this.tiro = tiro;
		this.jogador = jogador;
		this.blocos = blocos;
		this.linhaLimite = linhaLimite;
		this.pontos = pontos;
	}

	public Elemento getTiro() {
		return tiro;
	}

	public void setTiro(Elemento tiro) {
		this.tiro = tiro;
	}

	public Elemento getJogador() {
		return jogador;
	}

	public void setJogador(Elemento jogador) {
		this.jogador = jogador;
	}

	public Elemento[] getBlocos() {
		return blocos;
	}

	public void setBlocos(Elemento[] blocos) {
		this.blocos = blocos;
	}

	public int getLinhaLimite() {
		return linhaLimite;
	}

	public void setLinhaLimite(int linhaLimite) {
		this.linhaLimite = linhaLimite;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);
		g.fillRect(tiro.getX(), tiro.getY(), tiro.getComprimento(), tiro.getLargura());

		g.setColor(Color.GREEN);
		g.fillRect(jogador.getX(), jogador.getY(), jogador.getComprimento(), jogador.getLargura());

		g.setColor(Color.BLUE);
		for (int i = 0; i < blocos.length; i++) {
			g.fillRect(blocos[i].getX(), blocos[i].getY(), blocos[i].getComprimento(), blocos[i].getLargura());
		}

		g.setColor(Color.GRAY);
		g.drawLine(0, linhaLimite, getWidth(), linhaLimite);

		g.drawString("Pontos" + pontos, 0, 10);
	}

}
