import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Cliente;
import model.Empresa;
import model.Taxa;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // Criando empresas
        Empresa empresa1 = new Empresa();
        empresa1.setCnpj("33.773.046/0001-00"); 
        empresa1.setSaldo(1000.0); // Definindo saldo inicial

        Empresa empresa2 = new Empresa();
        empresa2.setCnpj("30.104.574/0001-70"); 
        empresa2.setSaldo(1000.0); // Definindo saldo inicial
        Taxa taxaEmpresa2 = new Taxa();
        taxaEmpresa2.setNome("Taxa de Serviço");
        taxaEmpresa2.setValor(7.5);
        empresa2.getTaxas().add(taxaEmpresa2);    

        // Criando clientes e associando às empresas
        Map<Empresa, List<Cliente>> clientesPorEmpresa = new HashMap<>();
        
        List<Cliente> clientesEmpresa1 = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setCpf("267.483.550-87"); 
        clientesEmpresa1.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setCpf("439.572.340-34"); 
        clientesEmpresa1.add(cliente2);
        
        clientesPorEmpresa.put(empresa1, clientesEmpresa1);

        List<Cliente> clientesEmpresa2 = new ArrayList<>();
        Cliente cliente3 = new Cliente();
        cliente3.setCpf("400.180.810-29"); 
        clientesEmpresa2.add(cliente3);

        Cliente cliente4 = new Cliente();
        cliente4.setCpf("794.229.820-57"); 
        clientesEmpresa2.add(cliente4);

        clientesPorEmpresa.put(empresa2, clientesEmpresa2);

        Scanner scanner = new Scanner(System.in);

        // Menu de opções para o cliente
        while (true) {
            System.out.println("\nMenu Principal");
            System.out.println("1. Acessar como Empresa");
            System.out.println("2. Acessar como Cliente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Acessar como empresa
                    System.out.println("Digite o CNPJ da empresa:");
                    scanner.nextLine(); // Limpar o caractere de nova linha pendente
                    String cnpj = scanner.nextLine();
                    Empresa empresaSelecionada = null;
                    for (Empresa empresa : clientesPorEmpresa.keySet()) {
                        if (empresa.getCnpj().equals(cnpj)) {
                            empresaSelecionada = empresa;
                            break;
                        }
                    }
                    if (empresaSelecionada == null) {
                        System.out.println("Empresa não encontrada.");
                        break;
                    }
                    acessarComoEmpresa(empresaSelecionada, clientesPorEmpresa);
                    break;

                case 2:
                    // Acessar como cliente
                    acessarComoCliente(clientesPorEmpresa);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void acessarComoEmpresa(Empresa empresa, Map<Empresa, List<Cliente>> clientesPorEmpresa) {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("\nMenu da Empresa");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Ver Histórico de Transações");
            System.out.println("3. Cadastrar Novo Cliente");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual: " + empresa.getSaldo());
                    break;
                case 2:
                    // Implementar lógica para ver histórico de transações
                    break;
                case 3:
                    // Implementar lógica para cadastrar novo cliente
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    

    private static void acessarComoCliente(Map<Empresa, List<Cliente>> clientesPorEmpresa) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CNPJ da empresa:");
        String cnpj = scanner.nextLine();

        Empresa empresaSelecionada = null;
        for (Empresa empresa : clientesPorEmpresa.keySet()) {
            if (empresa.getCnpj().equals(cnpj)) {
                empresaSelecionada = empresa;
                break;
            }
        }

        if (empresaSelecionada == null) {
            System.out.println("Empresa não encontrada.");
            return;
        }

        List<Cliente> clientes = clientesPorEmpresa.get(empresaSelecionada);

        System.out.println("Clientes disponíveis para a empresa " + empresaSelecionada.getCnpj() + ":");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getCpf());
        }

        System.out.print("Escolha o número do cliente: ");
        int clienteIndex = scanner.nextInt();
        Cliente clienteSelecionado = clientes.get(clienteIndex - 1);

        // Menu de opções para o cliente
        while (true) {
            System.out.println("\nMenu do Cliente");
            System.out.println("1. Visualizar Saldo");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Saque");
            System.out.println("4. Histórico de Transações");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
        
            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual: " + empresaSelecionada.getSaldo()); // Exemplo de visualização de saldo
                    break;
                case 2:
                    System.out.print("Digite o valor a ser depositado: ");
                    double valorDeposito = scanner.nextDouble();
                    empresaSelecionada.realizarDeposito(valorDeposito); // Exemplo de depósito
                    System.out.println("Depósito realizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    empresaSelecionada.realizarSaque(valorSaque); // Exemplo de saque
                    System.out.println("Saque realizado com sucesso.");
                    break;
                case 4:
                    // Implemente o histórico de transações aqui
                    System.out.println("Histórico de Transações:");
                    // Exemplo de como exibir histórico de transações
                    // Você pode acessar o histórico de transações do cliente ou da empresa, dependendo da implementação
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
