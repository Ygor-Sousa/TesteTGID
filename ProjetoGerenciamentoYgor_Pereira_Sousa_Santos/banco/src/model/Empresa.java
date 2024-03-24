package model;

import java.util.ArrayList;
import java.util.List;

import util.ValidadorCPF_CNPJ;

// Empresa.java
public class Empresa {
    private String cnpj;
    private double saldo;
    private List<Taxa> taxas;
    
     public Empresa() {
        // Inicialize a lista de taxas
        this.taxas = new ArrayList<>();
    }


        // Getters e setters
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        // Validar CNPJ
        if (ValidadorCPF_CNPJ.validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public List<Taxa> getTaxas() {
        return taxas;
    }
    public void setTaxas(List<Taxa> taxas) {
        this.taxas = taxas;
    }


    // Métodos para realizar depósitos e saques
    public void realizarDeposito(double valor) {
        double taxaSistema = calcularTaxaSistema(TipoTransacao.DEPOSITO);
        this.saldo += valor - taxaSistema;
    }

    public void realizarSaque(double valor) {
        double taxaSistema = calcularTaxaSistema(TipoTransacao.SAQUE);
        this.saldo -= valor + taxaSistema;
    }

    public double calcularTaxaSistema(TipoTransacao tipoTransacao) {
        double taxaSistema = 0.0;
        // Supondo que a empresa tenha apenas uma taxa de sistema
        if (taxas != null && !taxas.isEmpty()) {
            for (Taxa taxa : taxas) {
                if (taxa.getNome().equals("Taxa de Sistema")) {
                    taxaSistema = taxa.getValor();
                    break;
                }
            }
        }
        return taxaSistema;
    }





}