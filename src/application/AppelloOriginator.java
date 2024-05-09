package application;

import java.util.List;
import java.util.ArrayList;

public class AppelloOriginator {
	List<AbstractAppello> appelloIniziale = new ArrayList<>();

	public void setAppello(List<AbstractAppello> appello) {
		appelloIniziale = appello;
	}

	public List<AbstractAppello> getAppello() {
		return appelloIniziale;
	}

	public AppelloMemento salvaAppello() {
		return new ConcreteAppelloMemento();
	}

	// classe innestata
	public class ConcreteAppelloMemento implements AppelloMemento {
		protected List<AbstractAppello> appelloCorrente;

		public ConcreteAppelloMemento() {
			appelloCorrente = appelloIniziale;
		}

		public void annullaAppello() {
			appelloCorrente = new ArrayList<>(appelloIniziale);
		}
	}
}
