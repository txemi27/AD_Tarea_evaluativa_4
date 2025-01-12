package servicio;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Viaje;
import entidad.Conductor;
import entidad.Reserva;

import java.util.*;


import java.sql.*;

public class ViajeService {
    
 
    
    public static void crearViaje(Scanner teclado) {
        
        // Crea StandardServiceRegistry y SessionFactory
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Viaje.class)
                .addAnnotatedClass(Conductor.class)
                .addAnnotatedClass(Reserva.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        try {
        	
        	int conductorId = 3;
        	
            // Comienza la transacción
            session.beginTransaction();
            
           
        	
            System.out.println("Introduce las plazas disponibles:");
            int numPlazas = teclado.nextInt();
            teclado.nextLine(); 
            
            System.out.println("Introduce las Ciudad Destino:");
            String ciudadDestino = teclado.nextLine();
            
            System.out.println("Introduce las Ciudad Origen:");
            String ciudadOrigen = teclado.nextLine();
            
            // Creamos un viaje
            System.out.println("Creando un nuevo Viaje...");
            Viaje nuevoViaje = new Viaje(ciudadDestino, ciudadOrigen, new Timestamp(System.currentTimeMillis()), numPlazas);
            
        
       	 // Recuperamos el conductor
            System.out.println("Introduce el ID conductor...");
            conductorId = teclado.nextInt();
            System.out.println("Asignando el conductor...");
            Conductor conductor = session.get(Conductor.class, conductorId); // Busca el conductor con id 1

            if (conductor == null) {
                System.out.println("No se encontró un conductor con ID 1.");
                return; // Sale del método si no se encuentra el conductor
            }
            
            System.out.println("Conductor asignado: " + conductor);
            
            conductor.getViajes().add(nuevoViaje); //Añade el conductor que hemos definido anteriormente
            
            // Guarda el viaje con el conductor asinado
            System.out.println("Guardando el viaje...");
            session.persist(nuevoViaje); 
            
            session.getTransaction().commit(); // Realiza el commit
            
            System.out.println("Viaje guardado con el id " + nuevoViaje.getId());
            
            System.out.println("¡Hecho!");
            
        } catch (Exception e) {
            System.out.println("Realizando rollback...");
            session.getTransaction().rollback();
            e.printStackTrace();
            
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    
    public static void listarViaje() {
    	
    	 // Crea StandardServiceRegistry y SessionFactory
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Viaje.class)
                .addAnnotatedClass(Conductor.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        try {
        	
            // Comienza la transacción
            session.beginTransaction();
        	
            // Obtener todos los viajes con createQuery
            List<Viaje> listaViajes = session.createQuery("FROM Viaje", Viaje.class).getResultList();
            
            // Mostrarmos los viajes
            System.out.println("\n=== Lista de Viajes ===");
            if(listaViajes.isEmpty()) {
                System.out.println("No hay viajes registrados");
            } else {
                for(Viaje viaje : listaViajes) {
                    System.out.println(viaje.toString());
                }
            }
            
            session.getTransaction().commit();
        	
            
        } catch (Exception e) {
            System.out.println("Realizando rollback...");
            session.getTransaction().rollback();
            e.printStackTrace();
            
        } finally {
            session.close();
            sessionFactory.close();
        }
    	
    }
    
    public static void buscarViajeDisponibles() {
    	
    	 // Crea StandardServiceRegistry y SessionFactory
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Viaje.class)
                .addAnnotatedClass(Conductor.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        try {
        	
            // Comienza la transacción
            session.beginTransaction();
        	
            // Obtener todos los viajes con createQuery
            List<Viaje> listaViajes = session.createQuery("FROM Viaje v WHERE v.plazasDisponibles > 0", Viaje.class).getResultList();
         
            
            // Mostrarmos los viajes
            System.out.println("\n=== Lista de viajes con plazas disponibles ===");
            if(listaViajes.isEmpty()) {
                System.out.println("No hay viajes registrados");
            } else {
                for(Viaje viaje : listaViajes) {
                    System.out.println(viaje.toString());
                }
            }
            
            session.getTransaction().commit();
        	
            
        } catch (Exception e) {
            System.out.println("Realizando rollback...");
            session.getTransaction().rollback();
            e.printStackTrace();
            
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
