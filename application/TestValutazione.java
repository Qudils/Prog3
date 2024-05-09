package application;

public class TestValutazione {
    private String materia;
    private final String matricola;
    private String valutazione;

    public TestValutazione(String matricola, String materia, String valutazione) {
        this.matricola = matricola;
        this.materia = materia;
        this.valutazione = valutazione;

    }


    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getMatricola() {
        return matricola;
    }
     public void setValutazione(String valutazione) {
            this.valutazione = valutazione;
        }
    public String getValutazione() {
        return valutazione;
    }
}