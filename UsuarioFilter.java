package kalangos.kpizza.dao;

import gminet.infra.dao.Filter;
import gminet.infra.toolkit.StringToolkit;

public class UsuarioFilter extends Filter {

    private static final long serialVersionUID = 1253389663522749305L;

    private String primeiroDigito;

    private int codigoSituacao;

    private int codigoPerfil;

    private String nome;

    private String email;

    public UsuarioFilter() {
        super();
    }

    public boolean hasPrimeiroDigito() {
        return StringToolkit.isFill(getPrimeiroDigito());
    }

    public boolean hasCodigoSituacao() {
        return getCodigoSituacao() > 0;
    }

    public boolean hasNome() {
        return StringToolkit.isFill(getNome());
    }

    public boolean hasCodigoPerfil() {
        return getCodigoPerfil() > 0;
    }

    public boolean hasEmail() {
        return StringToolkit.isFill(getEmail());
    }

    public int getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(int codigocodigoPerfil) {
        this.codigoPerfil = codigocodigoPerfil;
    }

    public String getPrimeiroDigito() {
        return primeiroDigito;
    }

    public void setPrimeiroDigito(String primeiroDigito) {
        this.primeiroDigito = primeiroDigito;
    }

    public int getCodigoSituacao() {
        return codigoSituacao;
    }

    public void setCodigoSituacao(int codigoSituacao) {
        this.codigoSituacao = codigoSituacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
