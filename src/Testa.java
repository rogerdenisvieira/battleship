public class Testa {
	public static void main(String[] args) {

		Partida p1 = new Partida("MANOLO", "J.A.R.V.I.S.");

		p1.preparaTabuleiro();

		while (!p1.avaliaPontuacao()) {
			p1.exibePartida();
			p1.jogadaPessoa();
			p1.jogadaCPU();
		}
		System.out.println("GAME OVER");
	}
}
