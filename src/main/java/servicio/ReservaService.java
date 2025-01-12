package servicio;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Reserva;
import entidad.Pasajero;
import entidad.Viaje;
import entidad.Conductor;

import java.util.*;

public class ReservaService {
	

	
	public static void crearReserva() {
		
		Session session = sesion();

		try {
			
			
			// Comienza la transaccion
			session.beginTransaction();
			
			//crea una reserva
			System.out.println("Creando una nueva Reserva...");
			Reserva tempReserva = new Reserva(new Timestamp(System.currentTimeMillis()), 3);
			
			 // Recuperamos el conductor
            System.out.println("Asignando el pasajero...");
            Pasajero pasajeroId = session.get(Pasajero.class, 1); // Busca el pasajero con id 1

            if (pasajeroId == null) {
                System.out.println("No se encontro un pasajero con ID 1.");
                return; // Sale del método si no se encuentra el conductor
            }
            
            System.out.println("Pasajero asignado: " + pasajeroId);
            
            pasajeroId.getReserva().add(tempReserva); //Añade el conductor que hemos definido anteriormente
            
            // Recuperamos el viaje
            System.out.println("Asignando el viaje...");
            Viaje viajeId = session.get(Viaje.class, 1); // Busca el conductor con id 1

            if (viajeId == null) {
                System.out.println("No se encontro un viaje con ID 1.");
                return; // Sale del método si no se encuentra el conductor
            }
            
            System.out.println("Viaje asignado: " + viajeId);
            
            viajeId.getReserva().add(tempReserva); //Añade el conductor que hemos definido anteriormente
            
            // Guarda la reserva
            System.out.println("Guardando la reserva...");
            session.persist(tempReserva); 
            
            session.getTransaction().commit(); // Realiza el commit
            
            System.out.println("Reserva guardada con el ID " + tempReserva.getId());
            
            System.out.println("¡Hecho!");
			
		}
		catch (Exception e) {
		System.out.println("Realizando rollback");
		session.getTransaction().rollback();
		e.printStackTrace();
		
		}
		finally {
			session.close();
			
		}

	}
	
	public static void cancelarReserva(Scanner teclado) {
		
		//crea sesionFactory y sesion
		
		Session session = sesion();

				try {
					int reservaId = 1;
					
					// Comienza la transaccion
					session.beginTransaction();
					
					System.out.println("Cancelando reserva con ID 1...");
					
					Reserva reserva = session.get(Reserva.class, reservaId);
					
					if (reserva == null) {
		                System.out.println("No se encontro una reserva con ID 1. Introduce otro ID de reserva");
		                reservaId = teclado.nextInt();
		                reserva = session.get(Reserva.class, reservaId);
		                
		            }
					
					session.remove(reserva);
					
		            session.getTransaction().commit(); // Realiza el commit
		            
		            System.out.println("¡Hecho!");
					
				}
				catch (Exception e) {
				System.out.println("Realizando rollback");
				session.getTransaction().rollback();
				e.printStackTrace();
				
				}
				finally {
					session.close();
					
				}
		
	}
	
	public static Session sesion() {
		//crea sesionFactory y sesion
		
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				        .configure("hibernate.cfg.xml")
				        .build();

				Metadata metadata = new MetadataSources(standardRegistry)
						.addAnnotatedClass(Reserva.class)
						.addAnnotatedClass(Pasajero.class)
						.addAnnotatedClass(Viaje.class)
						.addAnnotatedClass(Conductor.class)
						.getMetadataBuilder()
						.build();

				SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
						.build();

				Session session = sessionFactory.openSession();
				
				return session;
	}

	
}
