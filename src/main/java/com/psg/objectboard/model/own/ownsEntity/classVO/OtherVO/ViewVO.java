package com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO;

public class ViewVO {
    private Integer format;
    private Integer ctaLinea;
    private Integer ctaPregunta;
    private Integer ctaImagen;

    public ViewVO(Integer format, Integer ctaLinea, Integer ctaPregunta, Integer ctaImagen) {
        this.format = format;
        this.ctaLinea = ctaLinea;
        this.ctaPregunta = ctaPregunta;
        this.ctaImagen = ctaImagen;
    }

    public Integer getCtaImagen() {
        return ctaImagen;
    }

    public void setCtaImagen(Integer ctaImagen) {
        this.ctaImagen = ctaImagen;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getCtaLinea() {
        return ctaLinea;
    }

    public void setCtaLinea(Integer ctaLinea) {
        this.ctaLinea = ctaLinea;
    }

    public Integer getCtaPregunta() {
        return ctaPregunta;
    }

    public void setCtaPregunta(Integer ctaPregunta) {
        this.ctaPregunta = ctaPregunta;
    }
}
