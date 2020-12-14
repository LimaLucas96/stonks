package br.com.stonks.stonks.models;

import java.util.Date;

public class DadosFundamentalista {

    private Ativo ativo;

    private Double pL;

    private Double pVp;

    private Double psr;

    private Double pAtivo;

    private Double pCapGiro;

    private Double dy;

    private Double pEbit;

    private Double pAcl;

    private Double evEbit;

    private Double evEbitda;

    private Double margeEbit;

    private Double margeLiq;

    private Double liquidezCorrente;

    private Double roic;

    private Double roe;

    private Boolean status;

    private Date dataAtualizacao;

    public DadosFundamentalista() {
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Double getpL() {
        return pL;
    }

    public void setpL(Double pL) {
        this.pL = pL;
    }

    public Double getpVp() {
        return pVp;
    }

    public void setpVp(Double pVp) {
        this.pVp = pVp;
    }

    public Double getPsr() {
        return psr;
    }

    public void setPsr(Double psr) {
        this.psr = psr;
    }

    public Double getpAtivo() {
        return pAtivo;
    }

    public void setpAtivo(Double pAtivo) {
        this.pAtivo = pAtivo;
    }

    public Double getpCapGiro() {
        return pCapGiro;
    }

    public void setpCapGiro(Double pCapGiro) {
        this.pCapGiro = pCapGiro;
    }

    public Double getDy() {
        return dy;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Double getpEbit() {
        return pEbit;
    }

    public void setpEbit(Double pEbit) {
        this.pEbit = pEbit;
    }

    public Double getpAcl() {
        return pAcl;
    }

    public void setpAcl(Double pAcl) {
        this.pAcl = pAcl;
    }

    public Double getEvEbit() {
        return evEbit;
    }

    public void setEvEbit(Double evEbit) {
        this.evEbit = evEbit;
    }

    public Double getEvEbitda() {
        return evEbitda;
    }

    public void setEvEbitda(Double evEbitda) {
        this.evEbitda = evEbitda;
    }

    public Double getMargeEbit() {
        return margeEbit;
    }

    public void setMargeEbit(Double margeEbit) {
        this.margeEbit = margeEbit;
    }

    public Double getMargeLiq() {
        return margeLiq;
    }

    public void setMargeLiq(Double margeLiq) {
        this.margeLiq = margeLiq;
    }

    public Double getLiquidezCorrente() {
        return liquidezCorrente;
    }

    public void setLiquidezCorrente(Double liquidezCorrente) {
        this.liquidezCorrente = liquidezCorrente;
    }

    public Double getRoic() {
        return roic;
    }

    public void setRoic(Double roic) {
        this.roic = roic;
    }

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
