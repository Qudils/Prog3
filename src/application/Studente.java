package application;



public class Studente implements AbstractStudente{ 

	private String nome;
	private String cognome;
    private String matricola;
    private String dataDiNascita;
    private String residenza;
    private String pianoDiStudi;
    private boolean tassePagate;
//    private List<Observer> osservatori = new ArrayList<>();
//    private StatoEsame statoEsame;

    // Costruttore vuoto
    public Studente() {
    	this.nome = "";
    	this.cognome = "";
        this.matricola = "";
        this.dataDiNascita = "";
        this.residenza = "";
        this.pianoDiStudi = "";
        this.tassePagate = false;
//        this.statoEsame = new Prenotato();
    }

    public Studente(String nome, String cognome, String matricola, String dataDiNascita, String residenza, String pianoDiStudi) {
        this.nome = nome; // Chiamiamo il costruttore parametrizzato della superclasse Persona
        this.cognome = cognome;
        this.matricola = matricola;
        this.dataDiNascita = dataDiNascita;
        this.residenza = residenza;
        this.pianoDiStudi = pianoDiStudi;    
    }
    
 
    // Override dei metodi setNome e setCognome della superclasse Persona
    
    public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    //Metodi set e get delle variabili di Studente
    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getPianoDiStudi() {
        return pianoDiStudi;
    }

    public void setPianoDiStudi(String pianoDiStudi) {
        this.pianoDiStudi = pianoDiStudi;
    }

    public boolean isTassePagate() {
        return tassePagate;
    }

    public void setTassePagate(boolean tassePagate) {
        this.tassePagate = tassePagate;
    }
}
