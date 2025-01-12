package servicio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Conductor;

import java.util.*;

public class ConductorService {

	public static void main(String[] args) {
		
		
	
	}
	public static void crearConductor(Scanner teclado) {
		
		//crea sesionFactory y sesion
		
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
		        .configure("hibernate.cfg.xml")
		        .build();

		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(Conductor.class)
				.getMetadataBuilder()
				.build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();

		Session session = sessionFactory.openSession();

		try {
			
			
			
			//crea un objeto student
			System.out.println("Creando un nuevo Conductor...");
			System.out.println("Introduce nombre Conductor...");
			String nombre = teclado.next();
			System.out.println("Introduce Vehiculo...");
			String coche = teclado.next();
			Conductor tempConductor = new Conductor(nombre, coche);
			
			// Comienza la transacci�n
			session.beginTransaction();
			
			// guarda el objeto student
			System.out.println("Guardando el conductor...");
			session.persist(tempConductor); // metodo save deprecated
			
			session.getTransaction().commit();
			
			System.out.println("Conductor guardado con el id " + tempConductor.getId());
			
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
