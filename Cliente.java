package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

import java.util.Date;

public class Cliente extends TransferObject {

    private static final long serialVersionUID = 6718369614473300846L;

    private String nome;
    private String cpf;
    private String email;
    private String comentario;
    private ClienteSituacao situacao;
    private LojaVirtual lojaVirtual;
    private Date dataCadastro;

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ClienteSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(ClienteSituacao situacao) {
        this.situacao = situacao;
    }

    public LojaVirtual getLojaVirtual() {
        return lojaVirtual;
    }

    public void setLojaVirtual(LojaVirtual lojaVirtual) {
        this.lojaVirtual = lojaVirtual;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
