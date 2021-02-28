package com.mygdx.game.bl.personajes.Iprototipo;

public abstract class AtaqueEspecialT {

    private String tipoAtaqueEspecial;

    public abstract AtaqueEspecialT clone();


    //-----getters and setters------
    public String getTipoAtaqueEspecial() {
        return tipoAtaqueEspecial;
    }

    public void setTipoAtaqueEspecial(String tipoAtaqueEspecial) {
        this.tipoAtaqueEspecial = tipoAtaqueEspecial;
    }

    public String obtenerInformacionAtaqueEspecial(){
        return tipoAtaqueEspecial;
    }
}
