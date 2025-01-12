package tarea_evaluativa;

import java.util.*;
import servicio.*;

import java.io.*;

public class principal {
	

	public static void main(String[] args) {
		
		ConductorService newConductor = new ConductorService(); 
		ReservaService newReserva = new ReservaService();
		ViajeService newViaje = new ViajeService();
		PasajeroService newPasajero = new PasajeroService();
		

		Scanner teclado = new Scanner(System.in);
		int numMenu;
		
		do {
		menu();
		numMenu = teclado.nextInt();
		teclado.nextLine();

		switch (numMenu) {
		case 1: {
			System.out.println("Usted eligió la opcion Crear Conductor.");
			newConductor.crearConductor(teclado);	
            break;

		}
		case 2: {
			System.out.println("Usted eligió la opcion Crear Viaje.");
			newViaje.crearViaje(teclado);
			break;
		}
		case 3: {
			System.out.println("Usted eligió la opcion Buscar viajes disponible.");
			newViaje.buscarViajeDisponibles();
			break;
		}
		case 4: {
			System.out.println("Usted eligió la opcion Crear pasajero.");
			newPasajero.crearPasajero();
			break;
		}
		case 5: {
			System.out.println("Usted eligió la opcion Crear Reserva.");
			newReserva.crearReserva();
			break;

		}
		case 6: {
			System.out.println("Usted eligió la opcion Cancelar Reserva.");
			newReserva.cancelarReserva(teclado);
			break;
		}
		case 7: {
			System.out.println("Usted eligió la opcion Listar todos los viajes.");
			newViaje.listarViaje();
			break;
		}
		case 8: {
			System.out.println("Adios.");
			break;
		}
		default: {

			System.out.println("Opcion incorrecta");
			break;
		}

		}	
		}
		while(numMenu > 0 && numMenu <= 7);

		teclado.close();
	}

	public static void menu() {

		System.out.println("== Menú de gestión de viajes compartidos ==\n" 
		+ "1. Crear conductor\n"
		+ "2. Crear viajes\n" 
		+ "3. Buscar viajes disponibles\n" 
		+ "4. Crear pasajero\n" 
		+ "5. Crear reserva\n"
		+ "6. Cancelar reserva\n" 
		+ "7. Listar viajes\n" 
		+ "8. Salir");

	}
	

}
