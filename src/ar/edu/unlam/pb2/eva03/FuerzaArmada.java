package ar.edu.unlam.pb2.eva03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unlam.pb2.eva03.enumeradores.TipoDeBatalla;

public class FuerzaArmada {

	private List<Vehiculo> convoy;
	private List<Batalla> batallas;
	
	public FuerzaArmada() {
		convoy = new ArrayList<Vehiculo>();
		batallas = new ArrayList<Batalla>();
	}
	
	public void agregarVehiculo(Vehiculo unVehiculo) {
		Boolean puedoAgregar = true;
		for (int i = 0; i < convoy.size(); i++) {
			if(convoy.get(i).getId() == unVehiculo.getId()) {
				puedoAgregar = false;
			}
		}
		if(puedoAgregar == true) {
		convoy.add(unVehiculo);
		}
	}

	public Integer getCapacidadDeDefensa() {
		return convoy.size();
	}

	public void crearBatalla(String nombre, TipoDeBatalla tipo, double latitud, double longitud) {
		Batalla nuevaBatalla = new Batalla(nombre, tipo, latitud, longitud);
		Boolean puedoAgregar = true;
		for (int i = 0; i < batallas.size(); i++) {
			if(batallas.get(i).getNombre().equals(nuevaBatalla.getNombre())) {
				puedoAgregar = false;
			}
		}
		if(puedoAgregar == true) {
			batallas.add(nuevaBatalla);
			}
	}

	public Batalla getBatalla(String nombre) {
		for (int i = 0; i < batallas.size(); i++) {
			if(batallas.get(i).getNombre().equals(nombre)) {
				return batallas.get(i);
			}
		}
		return null;
	}

	public boolean enviarALaBatalla(String nombreDeLaBatalla, int idVehiculo) {
		Boolean laEnvie = false;
		Batalla unaBatalla = null;
		Vehiculo unVehiculo = null;
		for (int i = 0; i < batallas.size(); i++) {
			if(batallas.get(i).getNombre().equals(nombreDeLaBatalla)) {
				unaBatalla = batallas.get(i);
			}
		}
		for (int i = 0; i < convoy.size(); i++) {
			if(convoy.get(i).getId() == idVehiculo) {
				unVehiculo = convoy.get(i);
			}
		}
		if(unaBatalla != null && unVehiculo != null) {
			if(unaBatalla.getTipo() == TipoDeBatalla.AEREA) {
				if(Volador.class.isAssignableFrom(unVehiculo)) {
					unaBatalla.agregarVehiculoALaBatalla(unVehiculo);
				}
			}
			if(unaBatalla.getTipo() == TipoDeBatalla.NAVAL) {
				if(Acuatico.class.isAssignableFrom(unVehiculo)) {
					unaBatalla.agregarVehiculoALaBatalla(unVehiculo);
				}
			}
			if(unaBatalla.getTipo() == TipoDeBatalla.TERRESTRE) {
				if(Terrestre.class.isAssignableFrom(unVehiculo)) {
					unaBatalla.agregarVehiculoALaBatalla(unVehiculo);
				}
			}
		}
		return laEnvie = true;
	} 

}
