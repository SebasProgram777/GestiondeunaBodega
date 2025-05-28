/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionbodega;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionBodega {
    private static ArrayList<Producto> inventario = new ArrayList<>();
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
            System.out.println("3. Gestión de usuarios");
            System.out.println("4. Salir");
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
                    menuGestionUsuarios();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 4);
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
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE BODEGAS ---");
            System.out.println("1. Ingresar nuevo producto");
            System.out.println("2. Ver inventario actual");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Consultar producto");
            System.out.println("6. Ver estadísticas del inventario");
            System.out.println("7. Aumentar stock de un producto");
            System.out.println("8. Reducir stock de un producto");
            System.out.println("0. Cerrar sesión y volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    ingresarProducto();
                    break;
                case 2:
                    verInventario();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    consultarProducto();
                    break;
                case 6:
                    mostrarEstadisticasInventario();
                    break;
                case 7:
                    aumentarStockProducto();
                    break;
                case 8:
                    reducirStockProducto();
                case 0:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void ingresarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad (ej: 1, 2.5, 2k, 1.5k): ");
        String entrada = scanner.nextLine().toLowerCase().replace(",", ".");
        
        System.out.print("Precio por unidad en S/.: ");
        String costo = scanner.nextLine().toLowerCase().replace(",", ".");

        double cantidad;
        double precio;

        try {
            if (entrada.endsWith("k") && costo.endsWith("k")) {
                String numStr = entrada.replace("k", "");
                cantidad = Double.parseDouble(numStr) * 1000;
                String numCos = costo.replace("k", "");
                precio = Double.parseDouble(numCos) * 1000;
                
            } else {
                cantidad = Double.parseDouble(entrada);
                precio = Double.parseDouble(costo);
            }

            inventario.add(new Producto(nombre, cantidad, precio));
            System.out.println("Producto agregado correctamente al inventario.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa una cantidad válida como 1, 2.5 o 2k.");
        }
    }

    public static void verInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("\n--- INVENTARIO ACTUAL ---");
            for (Producto p : inventario) {
                System.out.println(p);
            }
        }
    }

    public static void actualizarProducto() {
        System.out.print("Nombre del producto a actualizar: ");
        String nombre = scanner.nextLine();

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Nueva cantidad (ej: 1, 2.5, 2k): ");
                String entrada = scanner.nextLine().toLowerCase().replace(",", ".");
                System.out.print("Nuevo precio por unidad (en S/.): ");
                String costo = scanner.nextLine().toLowerCase().replace(",", ".");
                double cantidad;
                double precio;
                try {
                    if (entrada.endsWith("k")&& costo.endsWith("k")) {
                        cantidad = Double.parseDouble(entrada.replace("k", "")) * 1000;
                        precio = Double.parseDouble(costo.replace("k", "")) * 1000;
                    } else {
                        precio = Double.parseDouble(costo);
                        cantidad = Double.parseDouble(entrada);
                    }
                    p.setPrecio(precio);
                    p.setCantidad(cantidad);
                    System.out.println("Producto actualizado correctamente.");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida.");
                    return;
                }
            }
        }

        System.out.println("Producto no encontrado.");
    }

    public static void eliminarProducto() {
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();

        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getNombre().equalsIgnoreCase(nombre)) {
                inventario.remove(i);
                System.out.println("Producto eliminado correctamente.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    public static void consultarProducto() {
        System.out.print("Nombre del producto a consultar: ");
        String nombre = scanner.nextLine();

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Producto encontrado: " + p);
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }
    public static void menuGestionUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Ver usuarios registrados");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Cambiar contraseña de usuario");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    eliminarUsuario();
                    break;
                case 3:
                    cambiarContraseñaUsuario();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    //Metodo para cambiar la contraseña de un usuario
    //Se solicita el ingreso del nombre de usuario y la contraseña actual para su modificación
    public static void cambiarContraseñaUsuario() {
    System.out.print("Nombre de usuario: ");
    String nombre = scanner.nextLine();
    System.out.print("Contraseña actual: ");
    String contraseñaActual = scanner.nextLine();

    boolean encontrado = false;
    for (Usuario u : listaUsuarios) {
        if (u.getNombreUsuario().equalsIgnoreCase(nombre) && u.getContraseña().equals(contraseñaActual)) {
            System.out.print("Nueva contraseña: ");
            String nuevaContraseña = scanner.nextLine();
            u.setContraseña(nuevaContraseña);
            System.out.println("Contraseña actualizada correctamente.");
            encontrado = true;
            break;
        }
    }
    if (!encontrado) {
        System.out.println("Nombre de usuario o contraseña incorrectos. No se puede cambiar la contraseña.");
    }
}

    public static void verUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("\nUsuarios registrados:");
            for (Usuario u : listaUsuarios) {
                System.out.println("- " + u.getNombreUsuario());
            }
        }
    }

    public static void eliminarUsuario() {
        System.out.print("Nombre del usuario a eliminar: ");
        String nombre = scanner.nextLine();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getNombreUsuario().equalsIgnoreCase(nombre)) {
                listaUsuarios.remove(i);
                System.out.println("Usuario eliminado correctamente.");
                return;
            }
        }

        System.out.println("Usuario no encontrado.");
    }

    public static void mostrarEstadisticasInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        Producto mayor = inventario.get(0);
        Producto menor = inventario.get(0);
        double suma = 0;

        for (Producto p : inventario) {
            if (p.getCantidad() > mayor.getCantidad()) mayor = p;
            if (p.getCantidad() < menor.getCantidad()) menor = p;
            suma += p.getCantidad();
        }

        double promedio = suma / inventario.size();

        System.out.println("\n--- ESTADÍSTICAS DEL INVENTARIO ---");
        System.out.println("Producto con mayor cantidad: " + mayor.getNombre() + " (" + mayor.getCantidad() + ")");
        System.out.println("Producto con menor cantidad: " + menor.getNombre() + " (" + menor.getCantidad() + ")");
        System.out.printf("Promedio de cantidades: %.2f\n", promedio);
    }
    
    public static void aumentarStockProducto() {
        System.out.print("Nombre del producto a aumentar stock: ");
        String nombre = scanner.nextLine();

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Cantidad a aumentar (ej: 1, 2.5, 2k): ");
                String entrada = scanner.nextLine().toLowerCase().replace(",", ".");
                double cantidad;
                try {
                    if (entrada.endsWith("k")) {
                        cantidad = Double.parseDouble(entrada.replace("k", "")) * 1000;
                    } else {
                        cantidad = Double.parseDouble(entrada);
                    }
                    p.setCantidad(p.getCantidad() + cantidad);
                    System.out.println("Stock aumentado correctamente.");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida.");
                    return;
                }
            }
        }

        System.out.println("Producto no encontrado.");
    }
    
    public static void reducirStockProducto() {
        System.out.print("Nombre del producto a reducir stock: ");
        String nombre = scanner.nextLine();

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Cantidad a reducir (ej: 1, 2.5, 2k): ");
                String entrada = scanner.nextLine().toLowerCase().replace(",", ".");
                double cantidad;
                try {
                    if (entrada.endsWith("k")) {
                        cantidad = Double.parseDouble(entrada.replace("k", "")) * 1000;
                    } else {
                        cantidad = Double.parseDouble(entrada);
                    }
                    p.setCantidad(p.getCantidad() - cantidad);
                    System.out.println("Stock reducido correctamente.");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida.");
                    return;
                }
            }
        }

        System.out.println("Producto no encontrado.");
    }
}
