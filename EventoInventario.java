package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

import java.util.Date;

public class EventoInventario extends TransferObject {

    private static final long serialVersionUID = 9062313907492996015L;

    private Inventario inventario;
    private Integer quantidade;
    private Double valor;
    private Date dataevento;
    private TipoEventoInventario tipo;

    public EventoInventario() {
    }

    public Inventario getInventario() {
        return inventario;
    }
    

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

   
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataEvento() {
        return dataevento;
    }

    public void setDataEvento(Date dataevento) {
        this.dataevento = dataevento;
    }

    public TipoEventoInventario getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventoInventario tipo) {
        this.tipo = tipo;
    }

}
