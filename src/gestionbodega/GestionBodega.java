/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionbodega;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionBodega {
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        mostrarMenuPrincipal();
    }
    public static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 3);
    }

    public static void registrarUsuario() {
        System.out.print("Ingresa nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa contraseña: ");
        String contraseña = scanner.nextLine();

        listaUsuarios.add(new Usuario(nombre, contraseña));
        System.out.println("Usuario registrado correctamente.");
    }

    public static void iniciarSesion() {
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        boolean encontrado = false;

        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equals(nombre) && u.getContraseña().equals(contraseña)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + nombre + "!");
                encontrado = true;
                mostrarMenuBodega(); // Aquí iría tu menú de gestión
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    public static void mostrarMenuBodega() {
        System.out.println("\n*** MENÚ DE BODEGA ***");
        // Aquí ponemos las opciones: registrar producto, ver stock y algunas mas 
    }
    
}
