package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class Filial extends TransferObject {

    private static final long serialVersionUID = 1697960432806757141L;

    private String nome;
    private LojaVirtual lojaVirtual;
    private LojaFisica lojaFisica;

    public Filial() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LojaVirtual getLojaVirtual() {
        return lojaVirtual;
    }

    public void setLojaVirtual(LojaVirtual lojaVirtual) {
        this.lojaVirtual = lojaVirtual;
    }

    public LojaFisica getLojaFisica() {
        return lojaFisica;
    }

    public void setLojaFisica(LojaFisica lojaFisica) {
        this.lojaFisica = lojaFisica;
    }

}
