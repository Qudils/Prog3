package application;

public interface AbstractStudente {
	
	//Metodi set e get delle variabili di Studente
	public String getNome();
	public void setNome(String nome);
	public String getCognome();
	public void setCognome(String cognome);
    public String getMatricola();
    public void setMatricola(String matricola);
    public String getDataDiNascita();
    public void setDataDiNascita(String dataDiNascita);
    public String getResidenza();
    public void setResidenza(String residenza);
    public String getPianoDiStudi();
    public void setPianoDiStudi(String pianoDiStudi);
    public boolean isTassePagate();
    public void setTassePagate(boolean tassePagate);
}
