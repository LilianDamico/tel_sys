import java.time.LocalDate;
import java.util.Date;

public class PrePago extends Assinante {
    private float creditos;
    private int numRecargas;

    public PrePago(long cpf, String nome, int numero) {
        super(cpf, nome, numero);
        this.creditos = 0;
        this.numRecargas = 0;
    }

    public void recarregar(Date data, float valor) {
        creditos += valor;
        numRecargas++;
    }

    public float fazerChamada(Date data, int duracao) {
        float custo = duracao * 1.45f;
        if (creditos >= custo) {
            creditos -= custo;
            Chamada chamada = new Chamada(data, duracao);
            adicionarChamada(chamada);
            return custo;
        } else {
            System.out.println("Créditos insuficientes!");
            return 0;
        }
    }

    public void imprimirFatura(int mes) {
        float total = 0;
        System.out.println("Fatura do mês: " + mes);
        for (Chamada chamada : getChamadas()) {
            LocalDate data = chamada.getData().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if (data.getMonthValue() == mes) {
                System.out.println(chamada);
                total += chamada.getDuracao() * 1.45f;
            }
        }
        System.out.println("Total a pagar: " + total);
    }

    public String toString() {
        return super.toString() + ", PrePago{" +
                "creditos=" + creditos +
                ", numRecargas=" + numRecargas +
                '}';
    }
}
