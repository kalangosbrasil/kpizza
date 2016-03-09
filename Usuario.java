package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;
import gminet.infra.toolkit.DateToolkit;

import java.util.Date;

public class Usuario extends TransferObject {

    private static final long serialVersionUID = -6274042672245532050L;

    private String login;
    private String nome;
    private String senhaAcesso;
    private Date dataCadastro;

    public Usuario() {
        super();
    }

    public Usuario(int codigo) {
        this();
        setCodigo(codigo);
    }

    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String password) {
        this.senhaAcesso = password;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date creationDate) {
        this.dataCadastro = creationDate;
    }

    public String getDataCadastroFormatado() {
        return DateToolkit.getStringFromDateTime(getDataCadastro());
    }
}
