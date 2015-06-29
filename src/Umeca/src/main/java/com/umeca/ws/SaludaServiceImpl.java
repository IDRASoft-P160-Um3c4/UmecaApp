package com.umeca.ws;

public class SaludaServiceImpl implements SaludaService {

    private String prefijoSaludo;
    private String sufijoSaludo;

    public SaludaServiceImpl() {

    }

    public SaludaServiceImpl(String prefijoSaludo, String sufijoSaludo) {
        super();
        this.prefijoSaludo = prefijoSaludo;
        this.sufijoSaludo = sufijoSaludo;
    }

    @Override
    public String saluda(String nombre) {
        return prefijoSaludo + nombre + sufijoSaludo;
    }



}