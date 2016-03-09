package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class ClienteAcesso extends TransferObject {

    private static final long serialVersionUID = -6027388987020563570L;

    private Cliente cliente;
    private ClienteAcessoTipo tipo;
    private String senha;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteAcessoTipo getTipo() {
        return tipo;
    }

    public void setTipo(ClienteAcessoTipo tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
