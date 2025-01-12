package entidad;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name="viaje")
public class Viaje {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="ciudadDestino")
	private String ciudadDestino;

	@Column(name="ciudadOrigen")
	private String ciudadOrigen;

	@Column(name="fechaHora")
	private Timestamp fechaHora;

	@Column(name="plazasDisponibles")
	private int plazasDisponibles;

	// Usamos el ManyToOne para la relacion.
	@ManyToOne
	@JoinColumn(name = "conductor_id")
	private Conductor conductor;
	
	// Usamos el OneToMany para la relacion 1n.
		@OneToMany (cascade=CascadeType.ALL)	
		@JoinColumn(name="viaje_id")
		List<Reserva> reservas = new ArrayList<>();

	public Viaje() {}

	public Viaje(String ciudadDestino, String ciudadOrigen, Timestamp fechaHora, int plazasDisponibles) {
		this.ciudadDestino = ciudadDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.fechaHora = fechaHora;
		this.plazasDisponibles = plazasDisponibles;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}


	@Override
	public String toString() {
		return "Viaje [id=" + id + ", ciudadDestino=" + ciudadDestino + ", ciudadOrigen=" + ciudadOrigen + ", fechaHora=" + fechaHora + ", plazasDisponibles=" + plazasDisponibles +  "]";
	}

	// Metodos para obtener y insertar un conductor
	public Conductor getConductor() {
	    return conductor;
	}

	public void setConductor(Conductor conductor) {
	    this.conductor = conductor;
	}

	public List<Reserva> getReserva() {
		return reservas;
	}
}

