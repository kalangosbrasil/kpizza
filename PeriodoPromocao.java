package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

import java.util.Date;

public class PeriodoPromocao extends TransferObject {

    private static final long serialVersionUID = -8631866326826418566L;

    private PromocaoVigente promocao;
    private Item item;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private DiaSemana diaSemana;
    private Integer canal;

    public PromocaoVigente getPromocao() {
        return promocao;
    }

    public void setPromocao(PromocaoVigente promocao) {
        this.promocao = promocao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Integer getCanal() {
        return canal;
    }

    public void setCanal(Integer canal) {
        this.canal = canal;
    }

}