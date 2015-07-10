public class PecaNeutra extends Peca {

	/* construtor */
	public PecaNeutra(boolean visibilidade) {
		super("null", "~", "#", visibilidade);
	}

	/* setters */
	public String getNome() {
		// avalia a visivilidade
		if (this.getVisibilidade() == true) {
			return "Agua";
		}
		return "Nevoa";
	}
}
