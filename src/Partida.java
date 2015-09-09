import java.util.Scanner;
import java.util.InputMismatchException;

public class Partida {
	private Jogador jogadorPessoa, jogadorCPU;
	private Tabuleiro tabuleiroPessoa, tabuleiroCPU;
	private Scanner captura;

	public Partida(String nomePessoa, String nomeCPU) {
		// cria tabuleiros
		this.tabuleiroPessoa = new Tabuleiro(true);
		this.tabuleiroCPU = new Tabuleiro(false);

		// cria jogadores novos
                
		this.jogadorPessoa = new Jogador(nomePessoa, this.tabuleiroPessoa);
		this.jogadorCPU = new Jogador(nomeCPU, tabuleiroCPU);
		this.captura = new Scanner(System.in);

	}

	/* exibe o estado atual da partida */
	public void exibePartida() {
		this.jogadorPessoa.getTabuleiro().exibeTabuleiro();
		System.out.println("\n");
		this.jogadorCPU.getTabuleiro().exibeTabuleiro();
	}

	/* realiza a jogada para a pessoa */
	public void jogadaPessoa() {
		int linha = this.tabuleiroPessoa.getPecas().length;
		int coluna = 10;

		do {
			// tenta a coleta das coordenadas
			try {

				// coleta coordernadas para jogar;
				do {
					System.out
							.println("\nInforme a linha para efetuar a jogada:");
					linha = this.captura.nextInt();

					System.out
							.println("Informa a coluna para efetuar a jogada:");
					coluna = this.captura.nextInt();

				} while (this.jogadorPessoa.verificaLog(linha, coluna) == true);
			}
			// captura exception fora do formato e exibe na tela
			catch (InputMismatchException e) {
				System.out.println("ERRO: posicao deve ser um inteiro.");
				this.captura.nextLine();
			}
		}

		// avalia o intervalo de valores jogaveis
		while (linha > 9 || linha < 0 || coluna > 9 || coluna < 0);

		// faz a jogada
		if (jogadorCPU.getTabuleiro().getPeca(linha, coluna) instanceof PecaNeutra) {
			System.out.println("\n" + jogadorPessoa.getNome() + " jogou em: "
					+ linha + "x" + coluna + " - Tiro no mar...\n");
		} else {
			System.out.println("\n" + jogadorPessoa.getNome() + " jogou em: "
					+ linha + "x" + coluna + " - Acertou!\n");
			this.jogadorPessoa.setPontuacao();
		}
		jogadorCPU.getTabuleiro().getPeca(linha, coluna).setVisibilidade(true);
	}

	/* realiza jogadas aleatorias para a CPU */
	public void jogadaCPU() {
		// gera chute inicial
		int chutaLinha = this.geraNumero();
		int chutaColuna = this.geraNumero();

		// verifica se a CPU ja jogou na mesma posicao
		do {
			// gera novos chutes
			chutaLinha = this.geraNumero();
			chutaColuna = this.geraNumero();
		} while (this.jogadorCPU.verificaLog(chutaLinha, chutaColuna));

		// faz jogada
		if (jogadorPessoa.getTabuleiro().getPeca(chutaLinha, chutaColuna) instanceof PecaNeutra) {
			System.out.println(jogadorCPU.getNome() + " jogou em: "
					+ chutaLinha + "x" + chutaColuna + " - Tiro no mar...\n");
		} else {
			System.out.println(jogadorCPU.getNome() + " jogou em: "
					+ chutaLinha + "x" + chutaColuna + " - Acertou!\n");
			this.jogadorCPU.setPontuacao();
		}
		jogadorPessoa.getTabuleiro().getPeca(chutaLinha, chutaColuna)
				.setVisibilidade(true);
	}

	/* distribui a peca aleatoriamente no tabuleiro */
	public void sorteiaPecas(Tabuleiro tabuleiro, int tamanho, String nomePeca,
			String simboloVisivel, String simboloInvisivel, boolean visibilidade) {
		int linhaAleatoria = this.geraNumero();
		int colunaAleatoria = this.geraNumero();

		// fornece posicoes aleatoria ate encontrar um espaco livre
		while (!(tabuleiro.verificaEspaco(linhaAleatoria, colunaAleatoria,
				tamanho))) {
			linhaAleatoria = this.geraNumero();
			colunaAleatoria = this.geraNumero();
		}

		tabuleiro.posicionaPecas(linhaAleatoria, colunaAleatoria, tamanho,
				nomePeca, simboloVisivel, simboloInvisivel, visibilidade);
	}

	/* gera um numero aleatorio entre 0 e 9 */
	public int geraNumero() {
		return (int) (Math.random() * 9);
	}

	/* cria e distribui todas as pecas no tabuleiro */
	public void preparaTabuleiro() {
		//distribui pecas Rebocador
		for (int i = 0; i < 3; i++) {
			sorteiaPecas(this.tabuleiroPessoa, 1, "Rebocador", "X", "R", false);
			sorteiaPecas(this.tabuleiroCPU, 1, "Rebocador", "R", "#", false);
		}
		//distribui pecas Destroyer
		for (int i = 0; i < 2; i++) {
			sorteiaPecas(this.tabuleiroPessoa, 2, "Destroyer", "X", "D", false);
			sorteiaPecas(this.tabuleiroCPU, 2, "Destroyer", "D", "#", false);
		}
		//distribui pecas Submarino
		for (int i = 0; i < 2; i++) {
			sorteiaPecas(this.tabuleiroPessoa, 3, "Submarino", "X", "S", false);
			sorteiaPecas(this.tabuleiroCPU, 3, "Submarino", "S", "#", false);
		}
		//Distribui pecas Porta Avioes
		sorteiaPecas(this.tabuleiroPessoa, 4, "Porta Avioes", "X", "P", false);
		sorteiaPecas(this.tabuleiroCPU, 4, "Porta Avioes", "P", "#", false);
	}

	/* avalia a pontuacao dos dois jogadores (maximo 17 pontos) */
	public boolean avaliaPontuacao() {

		if (this.jogadorPessoa.getPontuacao() == 17) {
			System.out.println(this.jogadorPessoa.getNome() + " venceu!");
			return true;
		} else if (this.jogadorCPU.getPontuacao() == 17) {
			System.out.println(this.jogadorCPU.getNome() + " venceu!");
			return true;
		}
		return false;
	}

}
