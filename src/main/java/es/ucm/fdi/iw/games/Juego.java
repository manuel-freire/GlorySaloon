package es.ucm.fdi.iw.games;

import java.util.ArrayList;
import es.ucm.fdi.iw.common.enums.Barajas;
import es.ucm.fdi.iw.common.enums.Juegos;
import es.ucm.fdi.iw.common.enums.Respuesta;
import es.ucm.fdi.iw.games.factorias.FactoriaBaraja;
import es.ucm.fdi.iw.games.factorias.FactoriaRegla;

public class Juego{
	
	private Baraja baraja;
	private Reglas reglas;
	private ArrayList<Jugador> jugadores;
	private double totalApostado;
	private int turno;
	private int maxJugadores;

	public Juego(Juegos juego, ArrayList<Jugador> jugadores,Barajas baraja,int maxJugadores) {
		
		this.jugadores = jugadores;
		this.reglas = FactoriaRegla.reglas(juego);
		this.baraja = FactoriaBaraja.baraja(baraja);
		this.totalApostado = 0;
		this.maxJugadores = maxJugadores;
		this.turno = 0;
	}
	
	public int getTurno(){
		return this.turno;
	}
	
	public void setTurno(int turno){
		this.turno = turno;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
		
	public int getJugador(String nombre) {
		
		int i = 0;	
		while(i < jugadores.size() && !jugadores.get(i).getNombre().equals(nombre)) {
			i++;
		}
		
		if(i!=jugadores.size())
			return i;
		
		return -1;
	}
	
	public Baraja getBaraja() {
		return this.baraja;
	}

	public void setBaraja(Baraja baraja) {
		this.baraja = baraja;
	}
	
	public Reglas getReglas() {
		return this.reglas;
	}
	
	public double getTotalApostado() {
		return this.totalApostado;
	}
	
	public void setTotalApostado(double totalApostado) {
		this.totalApostado = totalApostado;
	}
	
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public int getMaxJugadores() {
		return maxJugadores;
	}

	public void setMaxJugadores(int maxJugadores) {
		this.maxJugadores = maxJugadores;
	}
	
	boolean partidaAcabada() {
		return jugadores.size() == turno+1;
	}
	
	void avanzaTurno() {
		turno++;
	}
	
	void repartirBotin(ArrayList<String> ganadores){
			
		double porcentaje;
		double ganado;
		
		if(Integer.parseInt(ganadores.get(ganadores.size()-1)) > 1) {
			int j = 1;
			for(int indiceJugador = 0; indiceJugador < jugadores.size();indiceJugador++) {
				
				if(ganadores.contains(jugadores.get(indiceJugador).getNombre())) {
					
					porcentaje = (jugadores.get(indiceJugador).getApostado()*100)/totalApostado;
					ganado = (totalApostado*porcentaje)/100;
					
					jugadores.get(indiceJugador).setDinero(jugadores.get(indiceJugador).getDinero() + ganado);
					ganadores.add(indiceJugador+2*j, String.valueOf(ganado));
					
				}
				j++;
			}
		}else {
			
			int indiceJugador = getJugador(ganadores.get(0));
			jugadores.get(indiceJugador).setDinero(jugadores.get(indiceJugador).getDinero() + totalApostado);
			ganadores.add(2, String.valueOf(totalApostado));
		}
	}
	
	Respuesta reparteCartasATodos(int numDeCartasARepartir) {
		
		for(int indiceJugador = 0; indiceJugador < jugadores.size();indiceJugador++) {
			if(reparteCartasAUno(numDeCartasARepartir,indiceJugador) == Respuesta.NoQuedanCartas) {
				return Respuesta.NoQuedanCartas;
			}
		}
		return Respuesta.TodoCorrecto;
	}
	
	Respuesta reparteCartasAUno(int numDeCartasARepartir,int indiceJugador) {
		for(int j = 0; j < numDeCartasARepartir;j++) {
			Carta c = baraja.daUnaCarta();
			if(c != null) {
				jugadores.get(indiceJugador).getMano().add(c);
			}else {
				return Respuesta.NoQuedanCartas;
			}
		}
		
		return (reglas.ccpp(jugadores.get(indiceJugador).getMano()));
	}
	
	void reiniciaJuego() {
		baraja.reiniciaBaraja();
		totalApostado = 0;
		turno = 0;
		for(int i = 0; i < jugadores.size();i++) {
			jugadores.get(i).setMano(new ArrayList<Carta>());
			jugadores.get(i).setApostado(0.0);
		}
	}

}
