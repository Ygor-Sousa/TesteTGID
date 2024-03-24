package service;

import model.Cliente;
import model.Empresa;
import model.TipoTransacao;

public class TransacaoService {
    // Método para realizar uma transação
    public void realizarTransacao(Empresa empresa, Cliente cliente, double valor, TipoTransacao tipoTransacao) {
        // Verificar se a empresa possui saldo suficiente para a transação
        if (verificarSaldoSuficiente(empresa, valor, tipoTransacao)) {
            // Atualizar saldo da empresa após a transação
            atualizarSaldoEmpresa(empresa, valor, tipoTransacao);

            // Simular envio de callback para empresa
           // enviarCallbackParaEmpresa(empresa, cliente, valor, tipoTransacao);

            // Simular envio de notificação para cliente
            //enviarNotificacaoParaCliente(cliente, valor, tipoTransacao);
        } else {
            System.out.println("Empresa não possui saldo suficiente para realizar a transação.");
        }
    }

    // Método para verificar se a empresa possui saldo suficiente para a transação
    private boolean verificarSaldoSuficiente(Empresa empresa, double valor, TipoTransacao tipoTransacao) {
        double saldoDisponivel = empresa.getSaldo();

        // Verificar se a transação é um saque e se a empresa possui saldo suficiente
        if (tipoTransacao == TipoTransacao.SAQUE && saldoDisponivel < valor) {
            return false;
        }

        return true;
    }

    // Método para atualizar o saldo da empresa após a transação
    private void atualizarSaldoEmpresa(Empresa empresa, double valor, TipoTransacao tipoTransacao) {
        // Deduzir o valor da transação do saldo da empresa
        double novoSaldo = empresa.getSaldo() - valor;

        // Se a transação for um depósito, adicionar o valor ao saldo da empresa
        if (tipoTransacao == TipoTransacao.DEPOSITO) {
            novoSaldo = empresa.getSaldo() + valor;
        }

        empresa.setSaldo(novoSaldo);
    }


}
