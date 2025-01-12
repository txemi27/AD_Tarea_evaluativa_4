package servicio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Pasajero;

public class PasajeroService {
	
	public static void crearPasajero() {
		
		//crea sesionFactory y sesion
		
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				        .configure("hibernate.cfg.xml")
				        .build();

				Metadata metadata = new MetadataSources(standardRegistry)
						.addAnnotatedClass(Pasajero.class)
						.getMetadataBuilder()
						.build();

				SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
						.build();

				Session session = sessionFactory.openSession();

				try {
					//crea un Pasajero
					System.out.println("Creando un nuevo Pasajero...");
					Pasajero tempPasajero = new Pasajero("Juan", "jlopez@birt.eus");
					
					// Comienza la transaccion
					session.beginTransaction();
					
					// guarda el pasajero
					System.out.println("Guardando el pasajero...");
					session.persist(tempPasajero); // metodo save deprecated
					
					session.getTransaction().commit();
					
					System.out.println("Hecho!");
					
				}
				catch (Exception e) {
				System.out.println("Realizando rollback");
				session.getTransaction().rollback();
				e.printStackTrace();
				
				}
				finally {
					session.close();
					sessionFactory.close();
				}
		
	}

}
