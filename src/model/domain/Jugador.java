package src.model.domain;

public class Jugador extends Persona {
    private int numeroCamiseta;
    private String posicion;
    private int golesTotales;
    private Equipo equipo;

    public Jugador(String identificacion, String nombre, int numeroCamiseta, String posicion, int golesTotales, Equipo equipo) {
        super(identificacion, nombre);
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.golesTotales = golesTotales;
        this.equipo = equipo;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getGolesTotales() {
        return golesTotales;
    }

    public void setGolesTotales(int golesTotales) {
        this.golesTotales = golesTotales;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String rolEnPartido() {
        return "Jugador [Camiseta: " + numeroCamiseta + ", Posición: " + posicion + ", Goles Totales: " + golesTotales + ", " + datosResumen() + "]";
    }    

    @Override
    public String datosResumen() {
        return "Jugador: " + getNombre() + " - Posición: " + posicion;
    }
}