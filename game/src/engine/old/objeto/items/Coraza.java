package engine.old.objeto.items;

public class Coraza {

	private int numDeposito = 0;
	private int numMaxDeposito = 5;

	public void addDeposito() {
		if (numDeposito < numMaxDeposito) {
			numDeposito++;
		}
	}
}
