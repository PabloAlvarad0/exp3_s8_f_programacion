package exp3_s08_pabloalvarado_005a;

/**Experiencia de Aprendizaje 03, Semana 08
 * Fundamentos de Programacion - 005A
 * @author Pablo Alvarado 
 */

import java.util.*;

public class EXP3_S08_PabloAlvarado_005A {
    
    // 0 - DEFINICION DE VARIABLES Y VALORES
    
    // 0.1 Definicion de Variables Globales
    
    static final int TOTAL_ASIENTOS = 10;
    static boolean[] asientos = new boolean[TOTAL_ASIENTOS];

   
    // 0.1.2 Definicion de Valores para las Variables
    
    static int[] idVentas = new int[100];
    static String[] ubicaciones = new String[100];
    static String[] nombresClientes = new String[100];
    static int[] edadesClientes = new int[100];
    static int contadorVentas = 0;

    
    // 0.1.3 Condiciones para Promociones - Reservas
    
    static List<String> promociones = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static int contadorReservas = 0;

    static Scanner sc = new Scanner(System.in);

    
    // 0.1.4 Funcion para el Menu principal
    
    public static void main(String[] args) {
        promociones.add("Estudiante - 10%");
        promociones.add("Tercera Edad - 15%");

        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    venderEntrada();
                    break;
                case 2:
                    mostrarPromociones();
                    break;
                case 3:
                    mostrarReservas();
                    break;
                case 4:
                    mostrarEstadoAsientos();
                    break;
                case 5:
                    modificarVenta();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema del Teatro Moro.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    // 1 - INICIO DEL PROGRAMA
    
    // 1.1 - Inicio del Programa - Menu principal
    
    static void mostrarMenu() {
        System.out.println("\n=== Bienvenidos al TEATRO MORO ===");
        System.out.println("1. Venta de entrada");
        System.out.println("2. Promociones");
        System.out.println("3. Reservas");
        System.out.println("4. Asientos disponibles");
        System.out.println("5. Modificar Reservas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    
    // 1.1.2 - Interaccion para vender entradas
    
    static void venderEntrada() {
        boolean continuar;
        do {5
                5
            System.out.println("\n=== Venta de Entrada ===");
            System.out.print("Ingrese su nombre: ");
            String nombre = sc.nextLine();

            int edad;
            do {
                System.out.print("Ingrese su edad: ");
                edad = sc.nextInt();
                sc.nextLine();
            } while (edad <= 0);

            mostrarEstadoAsientos();

            int asiento;
            do {
                System.out.print("Ingrese número de asiento (0 a " + (TOTAL_ASIENTOS - 1) + "): ");
                asiento = sc.nextInt();
                sc.nextLine();
            } while (asiento < 0 || asiento >= TOTAL_ASIENTOS || asientos[asiento]);

            double precioBase = 10000;
            double descuento = 0;
            String tipoDescuento = "Ninguno";

            if (edad <= 25) {
                descuento = 0.10;
                tipoDescuento = "Estudiante";
            } else if (edad >= 60) {
                descuento = 0.15;
                tipoDescuento = "Tercera Edad";
            }

            double precioFinal = precioBase * (1 - descuento);

            // Guardar venta
            idVentas[contadorVentas] = contadorVentas + 1;
            ubicaciones[contadorVentas] = "Asiento " + asiento;
            nombresClientes[contadorVentas] = nombre;
            edadesClientes[contadorVentas] = edad;
            asientos[asiento] = true;

            // Guardar reserva
            reservas.add(new Reserva(contadorReservas + 1, nombre, asiento));
            contadorReservas++;

            System.out.println("\n=== Resumen de Venta ===");
            System.out.println("Cliente: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Asiento: " + asiento);
            System.out.println("Descuento aplicado: " + tipoDescuento);
            System.out.println("Total a pagar: $" + precioFinal);

            contadorVentas++;

            System.out.print("¿Desea comprar otra entrada? (s/n): ");
            continuar = sc.nextLine().equalsIgnoreCase("s");
        } while (continuar);
    }

    // 1.1.5 - Función para modificar una venta
    
    static void modificarVenta() {
        System.out.println("\n=== Modificar Venta ===");
        System.out.print("Ingrese el ID de la venta a modificar: ");
        int idVentaModificar = sc.nextInt();
        sc.nextLine();

        // Validar que la venta exista
        boolean ventaEncontrada = false;
        for (int i = 0; i < contadorVentas; i++) {
            if (idVentas[i] == idVentaModificar) {
                ventaEncontrada = true;

                // Mostrar detalles actuales de la venta
                System.out.println("Venta encontrada: ");
                System.out.println("ID: " + idVentas[i]);
                System.out.println("Cliente: " + nombresClientes[i]);
                System.out.println("Edad: " + edadesClientes[i]);
                System.out.println("Asiento: " + ubicaciones[i]);

                // Permitir modificar el nombre del cliente
                System.out.print("Nuevo nombre del cliente (dejar vacío para no modificar): ");
                String nuevoNombre = sc.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    nombresClientes[i] = nuevoNombre;
                }

                // Permitir modificar la edad del cliente
                System.out.print("Nueva edad del cliente (dejar vacío para no modificar): ");
                String nuevaEdadStr = sc.nextLine();
                if (!nuevaEdadStr.isEmpty()) {
                    int nuevaEdad = Integer.parseInt(nuevaEdadStr);
                    edadesClientes[i] = nuevaEdad;
                }

                // Permitir modificar el asiento
                System.out.print("Nuevo número de asiento (dejar vacío para no modificar): ");
                String nuevoAsientoStr = sc.nextLine();
                if (!nuevoAsientoStr.isEmpty()) {
                    int nuevoAsiento = Integer.parseInt(nuevoAsientoStr);

                    // Verificar que el asiento esté disponible
                    if (!asientos[nuevoAsiento]) {
                        // Liberar el asiento anterior
                        int asientoAnterior = Integer.parseInt(ubicaciones[i].split(" ")[1]);
                        asientos[asientoAnterior] = false;

                        // Asignar el nuevo asiento
                        ubicaciones[i] = "Asiento " + nuevoAsiento;
                        asientos[nuevoAsiento] = true;
                    } else {
                        System.out.println("El asiento seleccionado no está disponible.");
                    }
                }

                System.out.println("Venta modificada exitosamente.");
                break;
            }
        }

        if (!ventaEncontrada) {
            System.out.println("Venta no encontrada.");
        }
    }

    // 2 - Interacción para mostrar promociones
    
    // 2.1 - Funcion para mostrar promociones disponibles
    
    static void mostrarPromociones() {
        System.out.println("\n=== Promociones disponibles ===");
        for (String promo : promociones) {
            System.out.println("- " + promo);
        }
    }

    // 3 - Interacción para mostrar reservas
    
    //3.1 - Funcion para mostrar reservas
    
    static void mostrarReservas() {
        System.out.println("\n=== Reservas realizadas ===");
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    // 4 - Para Mostrar el estado de los asientos
    
    static void mostrarEstadoAsientos() {
        System.out.println("\nAsientos disponibles:");
        boolean hayDisponibles = false;

        for (int i = 0; i < asientos.length; i++) {
            if (!asientos[i]) {
                System.out.print(i + " ");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay asientos disponibles.");
        } else {
            System.out.println();
        }
    }

    //4.1 - Condición para las reservas
    
    static class Reserva {
        int idReserva;
        String nombreCliente;
        int numeroAsiento;

        public Reserva(int id, String nombre, int asiento) {
            this.idReserva = id;
            this.nombreCliente = nombre;
            this.numeroAsiento = asiento;
        }

        public String toString() {
            return "ID Reserva: " + idReserva + ", Cliente: " + nombreCliente + ", Asiento: " + numeroAsiento;
        }
    }
}

// FIN DEL PROGRAMA