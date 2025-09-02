package Modelo;

import java.util.List;

public class Mascota {
	
	private int IDMascota;
	private String nombre;
	private String especie;
	private String género;
	private String raza;
	private int idcedula;
	
	
	
	public Mascota() {}

	
	public Mascota(String nombre, String especie, String género, String raza, int idcedula ) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.género = género;
		this.raza = raza;
		this.idcedula = idcedula;
	}

	public Mascota(String nombre2, String especie2, String género2, String raza2, String iDCedula2) {
		// TODO Auto-generated constructor stub
	}


	public int getIDMascota() {
		return IDMascota;
	}


	public void setIDMascota(int iDMascota) {
		IDMascota = iDMascota;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEspecie() {
		return especie;
	}


	public void setEspecie(String especie) {
		this.especie = especie;
	}


	public String getGénero() {
		return género;
	}


	public void setGénero(String género) {
		this.género = género;
	}


	public String getRaza() {
		return raza;
	}


	public void setRaza(String raza) {
		this.raza = raza;
	}


	public int getIdcedula() {
		return idcedula;
	}


	public void setIdcedula(int idcedula) {
		this.idcedula = idcedula;
	}

	

	

}
