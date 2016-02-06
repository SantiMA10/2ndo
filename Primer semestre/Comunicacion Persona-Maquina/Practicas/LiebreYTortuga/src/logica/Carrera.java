package logica;


public class Carrera {

	private Corredor liebre;
	private Corredor tortuga;
	private Corredor corredorActivo;
	private int numeroDado;
	private boolean bonus = false;

	public Carrera(int numArboles) {
		inicializarJuego(numArboles);
	}

	public void inicializarJuego(int numArboles) {
		int num = (int) (Math.random()*2);
		if(num == 1){
			liebre = new Liebre( new Calle(true, numArboles,true, false),"liebre.jpg");
			tortuga = new Tortuga( new Calle(false, numArboles,false, bonus),"tortuga.jpg");
		}
		else{
			liebre = new Liebre( new Calle(true, numArboles,false, false),"liebre.jpg");
			tortuga = new Tortuga( new Calle(false, numArboles,true, bonus),"tortuga.jpg");
		}
		numeroDado = 0;
		// Establecemos el corredor activo apuntando a la tortuga ya que sale la primera
		this.setCorredorActivo(tortuga);
	}
	
	public void cambiarBonus(){
		bonus = !bonus;
	}
	public boolean getBonus(){
		return bonus;
	}
	public void lanzarDado() {
		numeroDado = Dado.lanzar(getCorredorActivo());
	}

	public boolean isJugadaPosible() {
		boolean isPosible = false;
		if (corredorActivo.getPosicion() + numeroDado < Calle.DIM)
			isPosible = true;
		else {
			numeroDado = 0;
			cambiarTurnos();
		}
		return isPosible;
	}

	public boolean isJugadaCorrecta(int i) {
		return ((corredorActivo.getPosicion() + numeroDado == i) && (numeroDado != 0));
	}

	public void resolverJugada() {
		corredorActivo.setPosicion(corredorActivo.getPosicion() + numeroDado);
		// Si tenemos un arbol en la casilla, ponemos a dormir al corredor
		if (corredorActivo.getCalleAsignada().getCasilla(corredorActivo.getPosicion()).tieneArbol())
			corredorActivo.dormir();
		// Incrementamos la puntuci�n del corredor
		corredorActivo.incrementaPuntuacion(corredorActivo.getCalleAsignada().puntosCasilla(corredorActivo.getPosicion()));
		if(corredorActivo.getCalleAsignada().getCasilla(corredorActivo.getPosicion()).tieneAgujero()){
			corredorActivo.caer();
		}
		// Intercambiamos los turnos
		cambiarTurnos();
	}

	public Corredor getCorredorActivo() {
		return corredorActivo;
	}

	public Corredor getCorredorNoActivo() {
		if (corredorActivo == liebre) {
			return tortuga;
		}
		return liebre;
	}

	public void cambiarTurnos() {
		if (getCorredorNoActivo().isDurmiendo()) {
			// Si el corredor no activo est� durmiendo, lo despertamos para el siguiente turno
			// pero NO CAMBIAMOS EL TURNO
			getCorredorNoActivo().despertar();
		} else {
			// Cambiamos el turno
			corredorActivo = getCorredorNoActivo();
		}
	}

	public boolean isPartidaFinalizada() {
		return (liebre.getPosicion() == Calle.POSICION_META || tortuga.getPosicion() == Calle.POSICION_META || tortuga.haCaido() || liebre.haCaido());
	}

	public int getNumeroDado() {
		return numeroDado;
	}

	public Corredor getLiebre() {
		return liebre;
	}

	public Corredor getTortuga() {
		return tortuga;
	}

	public void setCorredorActivo(Corredor corredorActivo) {
		this.corredorActivo = corredorActivo;
	}
}