public class Tabuleiro {
	private Peca[][] pecas;

	/* construtor */
	public Tabuleiro(boolean visibilidade) {
		pecas = new Peca[10][10];
		for (int i = 0; i < pecas.length; i++) {
			for (int j = 0; j < pecas[i].length; j++) {
				pecas[i][j] = new PecaNeutra(visibilidade);
			}
		}
	}

	/* gets */
	public Peca getPeca(int linha, int coluna) {
		return pecas[linha][coluna];
	}

	public Peca[][] getPecas() {
		return this.pecas;
	}

	/* varre o array exibindo na tela */
	public void exibeTabuleiro() {
		int contaLinha = 0;
		System.out.print("  0 1 2 3 4 5 6 7 8 9 ");
		for (int i = 0; i < pecas.length; i++) {
			System.out.print("\n" + contaLinha++ + " ");
			for (int j = 0; j < pecas[i].length; j++) {
				System.out.print(pecas[i][j].getSimbolo() + " ");
			}
		}
		System.out.print("\n");
	}

	/* verifica se a posicao fornecida nao esta ocupada */
	public boolean verificaEspaco(int linha, int coluna, int tamanho) {

		// varre da posicao informada ate o fim do array
		for (; coluna < pecas[linha].length; coluna++, tamanho--) {

			// verifica se e possivel inserir peca
			if (!(pecas[linha][coluna] instanceof PecaNeutra)) {
				return false;
			}

			if (coluna == pecas[linha].length - 1 && tamanho > 0) {
				coluna = 0;
				tamanho--;

				// verifica se e possivel inserir peca
				if (!(pecas[linha][coluna] instanceof PecaNeutra)) {
					return false;
				}
			}
		}
		return true;
	}

	/* instancia uma nova peça em determinada posicao */
	public void posicionaPecas(int linha, int coluna, int tam, String n,
			String sv, String si, boolean v) {

		// varre a linha ate a posicao desejada
		for (; coluna < pecas[linha].length && tam > 0; coluna++) {
			pecas[linha][coluna] = new Peca(n, sv, si, v);
			tam--;

			// retorna ao inicio do array
			if (coluna == pecas[linha].length - 1 && tam > 0) {
				coluna = 0;
				tam--;
				pecas[linha][coluna] = new Peca(n, sv, si, v);
			}
		}
	}
}
