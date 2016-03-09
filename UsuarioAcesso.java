package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;
import gminet.infra.toolkit.DateToolkit;

import java.util.Date;

public class UsuarioAcesso extends TransferObject {

    private static final long serialVersionUID = -6136725066536817088L;

    private Usuario usuario;
    private LojaVirtual lojaVirtual;
    private Filial filial;
    private UsuarioSituacao situacao;
    private UsuarioTipo tipo;
    private Date dataCadastro;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LojaVirtual getLojaVirtual() {
        return lojaVirtual;
    }

    public void setLojaVirtual(LojaVirtual lojaVirtual) {
        this.lojaVirtual = lojaVirtual;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public UsuarioSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(UsuarioSituacao situacao) {
        this.situacao = situacao;
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
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
