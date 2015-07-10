public class Peca {
	private String nome, simboloVisivel, simboloInvisivel;
	private boolean visibilidade;

	/* construtor pecas atingiveis */
	public Peca(String n, String sv, String si, boolean v) {
		this.nome = n;
		this.simboloVisivel = sv;
		this.simboloInvisivel = si;
		this.visibilidade = v;
	}

	/* gets */
	public String getNome() {
		return this.nome;
	}

	public boolean getVisibilidade() {
		return this.visibilidade;
	}

	public String getSimbolo() {
		// avalia a visibilidade
		if (this.getVisibilidade() == true) {
			return this.simboloVisivel;
		}
		return this.simboloInvisivel;
	}

	public String getSimboloVisivel() {
		return simboloVisivel;
	}

	public String getSimboloInvisivel() {
		return simboloInvisivel;
	}

	/* sets */
	public void setVisibilidade(boolean v) {
		this.visibilidade = v;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
