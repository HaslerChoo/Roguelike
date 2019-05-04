package launcher;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Roguelike extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int FPS = 50;
	private final int jogadorVelocidade = 5;

	private int pontos;
	private int tamanhoJogador;

	private Random random;

	private Tela tela;

	private boolean controleTecla[] = new boolean[4];

	private boolean game = true;
	private boolean fim;

	public Roguelike() {
		this.addKeyListener(this);
		random = new Random();
		controleTecla = new boolean[4];
		tamanhoJogador = 50;
		Elemento tiro = new Elemento(0, 0, 1, 0);
		Elemento jogador = new Elemento(0, 0, jogadorVelocidade, tamanhoJogador, tamanhoJogador);
		Elemento[] blocos = new Elemento[10];
		for (int i = 0; i < blocos.length; i++) {
			int espaco = i * tamanhoJogador + 10 * (i + 1);
			blocos[i] = new Elemento(espaco, 0, 1, tamanhoJogador, tamanhoJogador);

		}

		tela = new Tela(tiro, jogador, blocos, 0, 0);
		tela.setLinhaLimite(350);
		getContentPane().add(tela);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);

		tela.getJogador().setX(tela.getWidth() / 2 - tela.getJogador().getX() / 2);
		tela.getJogador().setY(tela.getHeight() - tela.getJogador().getLargura());
		tela.getTiro().setLargura(tela.getHeight() - tela.getJogador().getLargura());

	}

	@Override
	public void keyPressed(KeyEvent e) {
		setTecla(e.getKeyCode(), true);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		setTecla(e.getKeyCode(), false);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void inicio() {
		long prxAtualizado = 0;

		while (game) {
			if (System.currentTimeMillis() >= prxAtualizado) {
				actualizarJogo();
				tela.repaint();
				prxAtualizado = System.currentTimeMillis() + FPS;
			}

		}
	}

	private void actualizarJogo() {
		Elemento jogador = tela.getJogador();
		Elemento tiro = tela.getTiro();
		Elemento blocos[] = tela.getBlocos();
		if (fim) {
			return;
		}
		if (controleTecla[2]) {
			jogador.setX(jogador.getX() + jogador.getVelocidade());
		}
		if (controleTecla[3]) {
			jogador.setX(jogador.getX() - jogador.getVelocidade());
		}

		if (jogador.getX() < 0) {
			jogador.setX(tela.getWidth() - jogador.getX());
		}

		if (jogador.getX() - jogador.getLargura() > tela.getWidth()) {
			jogador.setX(0);
		}

		tiro.setY(0);
		tiro.setX(jogador.getX() + jogador.getLargura() / 2);

		for (Elemento bloco : blocos) {
			if (bloco.getY() > tela.getLinhaLimite()) {
				fim = true;
				break;
			}

			if (bloco.getY() < 0) {
				bloco.setY(0);
			}

			if (shotHit(bloco, tiro)) {
				bloco.setY(bloco.getY() - bloco.getVelocidade() * 2);
				tiro.setY(bloco.getY());
			} else {
				int sorte = random.nextInt(10);
				if (sorte == 0) {
					bloco.setY(bloco.getY() + bloco.getVelocidade() + 1);
				} else if (sorte == 5) {
					bloco.setY(bloco.getY() - bloco.getVelocidade());
				} else {
					bloco.setY(bloco.getY() + bloco.getVelocidade());
				}
			}
		}
		pontos = pontos + blocos.length;
		tela.setPontos(pontos);
	}

	public void setTecla(int keyCode, boolean active) {

		switch (keyCode) {
		case KeyEvent.VK_ESCAPE:
			this.game = false;
			dispose();
			break;
		case KeyEvent.VK_UP:
			controleTecla[0] = active;
			break;
		case KeyEvent.VK_DOWN:
			controleTecla[1] = active;
			break;
		case KeyEvent.VK_RIGHT:
			controleTecla[2] = active;
			break;
		case KeyEvent.VK_LEFT:
			controleTecla[3] = active;
		default:
			break;
		}
	}

	private boolean shotHit(Elemento bloco, Elemento tiro) {

		if (bloco.getX() <= tiro.getX() && (bloco.getX() + bloco.getLargura()) >= tiro.getX()) {
			return true;
		}
		return false;
	}

}
