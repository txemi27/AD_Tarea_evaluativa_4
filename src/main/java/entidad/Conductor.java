package entidad;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name="conductor")

public class Conductor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Valor autogenerado por mYSQL
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	
	@Column(name="vehiculo")
	private String vehiculo;
	
	
	// Usamos el OneToMany para la relacion 1n.
	@OneToMany (cascade=CascadeType.ALL)	
	@JoinColumn(name="conductor_id")
	List<Viaje> viajes = new ArrayList<>();
	
	public Conductor() {
		
	}

	public Conductor(int id) {
		super();
		this.id = id;
	}
	
	public Conductor(String nombre, String vehiculo) {
		super();
		this.nombre = nombre;
		this.vehiculo = vehiculo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	// agregar sus getters y setters
	public List<Viaje> getViajes() {
	    return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
	    this.viajes = viajes;
	}

	@Override
	public String toString() {
		return "Conductor [id=" + id + ", nombre=" + nombre + ", vehiculo=" + vehiculo + "]";
	}
	
	
	
	
	

}
