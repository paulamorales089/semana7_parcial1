package model;

public class GenesisParticulas {

    private String grupo;
    private String cantidad;

    private int posXE, posYE;

    private int r,g,b;

    private int movimiento;


    public GenesisParticulas (String grupo,String cantidad,  int posXE, int posYE, int r, int g, int b) {

        this.grupo = grupo;
        this.cantidad = cantidad;

        this.posXE = posXE;
        this.posYE = posYE;

        this.r = r;
        this.g = g;
        this.b = b;


        this.movimiento= movimiento;

    }


    public String getNombreGrupo() {
        return grupo;
    }


    public void setNombreGrupo(String nombreGrupo) {
        this.grupo = nombreGrupo;
    }


    public String getCantidadParticulas() {
        return cantidad;
    }


    public void setCantidadParticulas(String cantidadParticulas) {
        this.cantidad = cantidad;
    }


    public int getPosX() {
        return posXE;
    }


    public void setPosX(int posX) {
        this.posXE = posXE;
    }


    public int getPosY() {
        return posYE;
    }


    public void setPosY(int posY) {
        this.posYE = posYE;
    }


    public int getR() {
        return r;
    }


    public void setR(int r) {
        this.r = r;
    }


    public int getG() {
        return g;
    }


    public void setG(int g) {
        this.g = g;
    }


    public int getB() {
        return b;
    }


    public void setB(int b) {
        this.b = b;
    }


    public int getMovimiento() {
        return movimiento;
    }


    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }
}

