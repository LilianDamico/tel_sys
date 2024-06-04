import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Telefonia {
    private ArrayList<PrePago> prePagos;
    private ArrayList<PosPago> posPagos;

    public Telefonia() {
        this.prePagos = new ArrayList<>();
        this.posPagos = new ArrayList<>();
    }

    public void cadastrarAssinante() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Cadastrar Assinante:");
            System.out.print("CPF: ");
            long cpf = scanner.nextLong();
            System.out.print("Nome: ");
            String nome = scanner.next();
            System.out.print("Numero: ");
            int numero = scanner.nextInt();
            System.out.print("Tipo (1-PrePago, 2-PosPago): ");
            int tipo = scanner.nextInt();

            if (tipo == 1) {
                prePagos.add(new PrePago(cpf, nome, numero));
            } else if (tipo == 2) {
                System.out.print("Valor da assinatura: ");
                float assinatura = scanner.nextFloat();
                posPagos.add(new PosPago(cpf, nome, numero, assinatura));
            } else {
                System.out.println("Tipo inválido!");
            }
        }
    }

    public void listarAssinantes() {
        System.out.println("Listar Assinantes:");
        System.out.println("PrePagos:");
        for (PrePago prePago : prePagos) {
            System.out.println(prePago);
        }
        System.out.println("PosPagos:");
        for (PosPago posPago : posPagos) {
            System.out.println(posPago);
        }
    }

    public void fazerChamada() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("CPF do Assinante: ");
            long cpf = scanner.nextLong();
            System.out.print("Data (yyyy-MM-dd): ");
            String dataStr = scanner.next();
            Date data = java.sql.Date.valueOf(dataStr);
            System.out.print("Duração (minutos): ");
            int duracao = scanner.nextInt();

            PrePago prePago = localizarPrePago(cpf);
            if (prePago != null) {
                prePago.fazerChamada(data, duracao);
            } else {
                PosPago posPago = localizarPosPago(cpf);
                if (posPago != null) {
                    posPago.fazerChamada(data, duracao);
                } else {
                    System.out.println("Assinante não encontrado!");
                }
            }
        }
    }

    public void fazerRecarga() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("CPF do Assinante PrePago: ");
            long cpf = scanner.nextLong();
            System.out.print("Data (yyyy-MM-dd): ");
            String dataStr = scanner.next();
            Date data = java.sql.Date.valueOf(dataStr);
            System.out.print("Valor: ");
            float valor = scanner.nextFloat();

            PrePago prePago = localizarPrePago(cpf);
            if (prePago != null) {
                prePago.recarregar(data, valor);
            } else {
                System.out.println("Assinante PrePago não encontrado!");
            }
        }
    }

    public PrePago localizarPrePago(long cpf) {
        for (PrePago prePago : prePagos) {
            if (prePago.getCpf() == cpf) {
                return prePago;
            }
        }
        return null;
    }

    public PosPago localizarPosPago(long cpf) {
        for (PosPago posPago : posPagos) {
            if (posPago.getCpf() == cpf) {
                return posPago;
            }
        }
        return null;
    }

    public void imprimirFaturas() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Mês da fatura: ");
            int mes = scanner.nextInt();

            System.out.println("Faturas PrePago:");
            for (PrePago prePago : prePagos) {
                prePago.imprimirFatura(mes);
            }

            System.out.println("Faturas PosPago:");
            for (PosPago posPago : posPagos) {
                posPago.imprimirFatura(mes);
            }
        }
    }

    public static void main(String[] args) {
        Telefonia telefonia = new Telefonia();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Cadastrar Assinante");
            System.out.println("2. Listar Assinantes");
            System.out.println("3. Fazer Chamada");
            System.out.println("4. Fazer Recarga");
            System.out.println("5. Imprimir Faturas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    telefonia.cadastrarAssinante();
                    break;
                case 2:
                    telefonia.listarAssinantes();
                    break;
                case 3:
                    telefonia.fazerChamada();
                    break;
                case 4:
                    telefonia.fazerRecarga();
                    break;
                case 5:
                    telefonia.imprimirFaturas();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
