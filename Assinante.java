import java.util.ArrayList;

public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    private int numChamadas;
    private ArrayList<Chamada> chamadas;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.numChamadas = 0;
        this.chamadas = new ArrayList<>();
    }

    public long getCpf() {
        return cpf;
    }

    public void adicionarChamada(Chamada chamada) {
        chamadas.add(chamada);
        numChamadas++;
    }

    public String toString() {
        return "Assinante{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", numero=" + numero +
                ", numChamadas=" + numChamadas +
                '}';
    }

    // Getter para chamadas
    public ArrayList<Chamada> getChamadas() {
        return chamadas;
    }
}
