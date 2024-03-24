package model;

import util.ValidadorCPF_CNPJ;

// Cliente.java
public class Cliente {
    private String cpf;
    // Outros atributos, se necessário

    public String getCpf() {
        return cpf;
    }

    // Métodos para realizar depósitos e saques
    public void realizarDeposito(Empresa empresa, double valor) {
        empresa.realizarDeposito(valor);
    }

    public void realizarSaque(Empresa empresa, double valor) {
        empresa.realizarSaque(valor);
    }

    public void setCpf(String cpf) {
        // Validar CPF
        if (ValidadorCPF_CNPJ.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
}