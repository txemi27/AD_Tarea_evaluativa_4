package entidad;

import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name="reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(name="fechaReserva")
	private Date fechaReserva;

	@Column(name="numeroPlazasReservadas")
	private int numeroPlazasReservadas;

	@ManyToOne
	@JoinColumn(name="pasajero_id")
	private Pasajero pasajero;

	@ManyToOne
	@JoinColumn(name="viaje_id")
	private Viaje viaje;
	
	

	public Reserva() {}

	public Reserva(Date fechaReserva, int numeroPlazasReservadas) {
		this.fechaReserva = fechaReserva;
		this.numeroPlazasReservadas = numeroPlazasReservadas;
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getNumeroPlazasReservadas() {
		return numeroPlazasReservadas;
	}

	public void setNumeroPlazasReservadas(int numeroPlazasReservadas) {
		this.numeroPlazasReservadas = numeroPlazasReservadas;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaReserva=" + fechaReserva + ", numeroPlazasReservadas=" + numeroPlazasReservadas + ", pasajero=" + pasajero + ", viaje=" + viaje + "]";
	}
	
	
}
