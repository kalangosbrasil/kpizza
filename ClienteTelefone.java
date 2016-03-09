package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

import java.util.Date;

public class ClienteTelefone extends TransferObject {

    private static final long serialVersionUID = 5038696537480734159L;

    private Cliente cliente;
    private Integer ddd;
    private Integer telefone;
    private Integer ramal;
    private Date dataCadastro;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getRamal() {
        return ramal;
    }

    public void setRamal(Integer ramal) {
        this.ramal = ramal;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
