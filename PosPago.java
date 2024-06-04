import java.time.LocalDate;
import java.util.Date;

public class PosPago extends Assinante {
    private float assinatura;

    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    public void fazerChamada(Date data, int duracao) {
        Chamada chamada = new Chamada(data, duracao);
        adicionarChamada(chamada);
    }

    public void imprimirFatura(int mes) {
        float total = assinatura;
        System.out.println("Fatura do mês: " + mes);
        for (Chamada chamada : getChamadas()) {
            LocalDate data = chamada.getData().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if (data.getMonthValue() == mes) {
                System.out.println(chamada);
                total += chamada.getDuracao() * 1.04f;
            }
        }
        System.out.println("Total a pagar: " + total);
    }

    public String toString() {
        return super.toString() + ", PosPago{" +
                "assinatura=" + assinatura +
                '}';
    }
}
