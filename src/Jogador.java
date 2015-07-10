
public class Jogador {

    private String nome;
    private int pontuacao;
    private Tabuleiro tabuleiroJogador;
    private boolean[][] logJogadas;

    /* construtor */
    public Jogador(String n, Tabuleiro t) {
        this.nome = n;
        this.tabuleiroJogador = t;

        // cria a matriz de jogadas
        this.logJogadas = new boolean[10][10];

        // inicializa a matriz de jogadas
        for (int i = 0; i < this.logJogadas.length; i++) {
            for (int j = 0; j < this.logJogadas[i].length; j++) {
                this.logJogadas[i][j] = false;
            }
        }

    }

    /* sets */
    public void setPontuacao() {
        pontuacao++;
    }

    /* gets */
    public int getPontuacao() {
        return this.pontuacao;
    }

    public String getNome() {
        return this.nome;
    }

    public Tabuleiro getTabuleiro() {
        return this.tabuleiroJogador;
    }

    /* verifica a jogada no log */
    public boolean verificaLog(int linha, int coluna)
    {

        // avalia os limites da matriz e o historico de jogadas
        if (linha < this.logJogadas.length && linha >= 0
                && coluna < this.logJogadas[linha].length && coluna >= 0) {
            if (logJogadas[linha][coluna] == false) {
                logJogadas[linha][coluna] = true;
                return false;
            } else {
                System.out.println("\nERRO: jogada repetida. Tente novamente.");
                return true;
            }
        } else 
        {
            System.out
                    .println("\nERRO: posicao deve ser menor que 10 e maior ou igual a 0.");
            return false;
        }
    }

}
