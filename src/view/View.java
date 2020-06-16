/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author aagpazos
 */
public class View {

    Scanner sc = new Scanner(System.in);
    Controller c = new Controller();

    public void runMenu(String menu) {
        boolean salir = false;

        c.arranque();

        do {
            System.out.println("");
            System.out.println("+----------------+\n"
                    + "|   FILMOTECA    |\n"
                    + "+----------------+");
            System.out.printf("%s: ", menu);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    this.archivos();
                    break;
                case "2":
                    this.peliculas();
                    break;
                case "3":
                    this.directores();
                    break;
                case "4":
                    this.actores();
                    break;
                case "5":
                    this.imprimirListados();
                    break;
                case "q":
                    salir = preguntarSiSalimos();
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!salir);
    }

    private void archivos() {
        String menuListado = "ARCHIVOS\n"
                + "1. Exportar Directores en formato col\n"
                + "2. Exportar Peliculas en formato html\n"
                + "q. Volver Menu Principal";
        boolean volver = false;
        do {
            System.out.println("");
            System.out.printf("%s: ", menuListado);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    c.directoresCol();
                    break;
                case "2":
                    c.peliculasHTML();
                    break;
                case "q":
                    volver = true;
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!volver);
    }

    private void peliculas() {
        String menuPeliculas = "PELICULAS\n"
                + "1. Altas\n"
                + "2. Bajas\n"
                + "3. Modificaciones\n"
                + "4. Consulta\n"
                + "q. Volver: ";
        boolean volver = false;

        do {
            System.out.printf("\n%s: ", menuPeliculas);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("\nALTAS");
                    boolean volver1 = false;
                    do {
                        System.out.printf("\n1. Añadir pelicula a la filmoteca\n"
                                + "q. Volver a Peliculas: ");
                        String opcion1 = sc.nextLine();
                        switch (opcion1) {
                            case "1":
                                String[] atributos = new String[10];
                                int[] añoDuracion = new int[2];
                                String tmp;
                                boolean atributoCorrecto = false;

                                do {
                                    System.out.printf("\nTitulo: ");
                                    atributos[0] = sc.nextLine();
                                    if (atributos[0].isEmpty()) {
                                        System.out.println("El campo titulo no puede quedar vacio");
                                    }
                                } while (atributos[0].isEmpty());
                                do {
                                    System.out.printf("\nAño: ");
                                    tmp = sc.nextLine();
                                    if (tmp.isEmpty()) {
                                        atributoCorrecto = true;
                                    } else {
                                        try {
                                            añoDuracion[0] = Integer.parseInt(tmp);
                                            atributoCorrecto = true;
                                        } catch (NumberFormatException ex) {
                                            System.out.println("\nEl año debe ser un numero valido");
                                        }
                                    }
                                } while (!atributoCorrecto);

                                atributoCorrecto = false;
                                do {
                                    System.out.printf("\nDuracion(en minutos): ");
                                    tmp = sc.nextLine();
                                    if (tmp.isEmpty()) {
                                        atributoCorrecto = true;
                                    } else {
                                        try {
                                            añoDuracion[1] = Integer.parseInt(tmp);
                                            atributoCorrecto = true;
                                        } catch (NumberFormatException ex) {
                                            System.out.println("\nLa duracion debe ser un numero valido");
                                        }
                                    }
                                } while (!atributoCorrecto);

                                System.out.printf("\nPais: ");
                                atributos[1] = sc.nextLine();
                                do {
                                    System.out.printf("\nDirector/es (Separados por comas si hay mas de uno): ");
                                    atributos[2] = sc.nextLine();
                                    if (atributos[2].isEmpty()) {
                                        System.out.println("El director no puede quedar vacio");
                                    }
                                } while (atributos[2].isEmpty());
                                System.out.printf("\nGuion: ");
                                atributos[3] = sc.nextLine();
                                System.out.printf("\nMusica: ");
                                atributos[4] = sc.nextLine();
                                System.out.printf("\nFotografia: ");
                                atributos[5] = sc.nextLine();
                                do {
                                    System.out.printf("\nReparto(separado por comas): ");
                                    atributos[6] = sc.nextLine();
                                    if (atributos[6].isEmpty()) {
                                        System.out.println("El reparto no puede quedar vacio");
                                    }
                                } while (atributos[6].isEmpty());
                                System.out.printf("\nProductora: ");
                                atributos[7] = sc.nextLine();
                                System.out.printf("\nGenero: ");
                                atributos[8] = sc.nextLine();
                                System.out.printf("\nSinopsis: ");
                                atributos[9] = sc.nextLine();

                                c.añadirPelicula(atributos, añoDuracion);
                                System.out.println("Se ha añadido correctamente la pelicula");
                                break;
                            case "q":
                                volver1 = true;
                                break;
                        }

                    } while (!volver1);
                    break;
                case "2":
                    System.out.println("\nBAJAS");
                    boolean volver2 = false;
                    do {
                        System.out.printf("\n1. Buscar pelicula a eliminar\n"
                                + "q. Volver a Peliculas: ");
                        String opcion2 = sc.nextLine();
                        switch (opcion2) {
                            case "1":
                                System.out.printf("\nIntroduce la pelicula para eliminar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaPelicula(p);
                                if (i == -1) {
                                    System.out.println("La pelicula no se ha encontrado\n");
                                } else {
                                    c.borrarPelicula(i);
                                    System.out.println("La pelicula se ha eliminado correctamente\n");
                                }
                                break;
                            case "q":
                                volver2 = true;
                                break;
                        }
                    } while (!volver2);

                    break;
                case "3":
                    System.out.println("\nMODIFICACIONES");
                    boolean volver3 = false;
                    do {
                        System.out.printf("\n1. Modificar pelicula\n"
                                + "q. Volver a Peliculas: ");
                        String opcion3 = sc.nextLine();
                        switch (opcion3) {
                            case "1":
                                boolean v3 = false;
                                System.out.printf("\nIntroduce una pelicula a modificar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaPelicula(p);
                                if (i == -1) {
                                    System.out.println("La pelicula no se ha encontrado");
                                } else {
                                    do {

                                        System.out.printf("Elige un atributo para modificar " + p + "\n"
                                                + "1. Año\n"
                                                + "2. Duracion\n"
                                                + "3. Pais\n"
                                                + "4. Guion\n"
                                                + "5. Musica\n"
                                                + "6. Fotografia\n"
                                                + "7. Productora\n"
                                                + "8. Genero\n"
                                                + "9. Sinopsis\n"
                                                + "q. volver: ");
                                        String op3 = sc.nextLine();
                                        switch (op3) {
                                            case "1":
                                            case "2":
                                            case "3":
                                            case "4":
                                            case "5":
                                            case "6":
                                            case "7":
                                            case "8":
                                            case "9":
                                                int o3 = Integer.parseInt(op3);
                                                String[] atributo = {"Año", "Duracion", 
                                                    "Pais", "Guion","Musica", 
                                                    "Fotografia", "Productora", 
                                                    "Genero","Sinopsis"};
                                                boolean modificado;
                                                do {
                                                    System.out.printf("Introduce el nuevo valor para "
                                                            + atributo[o3-1] + ": ");
                                                    String modificacion = sc.nextLine();
                                                    modificado = c.modificarPelicula(o3, i, modificacion);
                                                    if (true == modificado) {
                                                        System.out.println("Se ha modificado correctamente\n");
                                                    } else {
                                                        System.out.println("No se ha podido modificar\n");
                                                    }
                                                } while (!modificado);
                                                break;
                                            case "q":
                                                v3 = true;
                                                break;
                                            default:
                                                System.out.println("Opcion incorrecta");
                                        }
                                    } while (!v3);
                                }
                                break;
                            case "q":
                                volver3 = true;
                                break;
                        }
                    } while (!volver3);

                    break;
                case "4":
                    System.out.println("\nCONSULTA");
                    boolean volver4 = false;
                    do {
                        System.out.printf("\n1. Buscar pelicula\n"
                                + "q. Volver a Peliculas: ");
                        String opcion4 = sc.nextLine();
                        switch (opcion4) {
                            case "1":
                                System.out.printf("\nIntroduce una pelicula a consultar: ");
                                String p = sc.nextLine();
                                String consulta = c.consultaPeliculas(p);
                                if (consulta == null) {
                                    System.out.println("La pelicula no se ha encontrado");
                                } else {
                                    System.out.println(consulta);
                                }
                                break;
                            case "q":
                                volver4 = true;
                                break;
                        }
                    } while (!volver4);

                    break;
                case "q":
                    volver = true;
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }

        } while (!volver);
    }

    private void directores() {
        String menuDirectores = "DIRECTORES\n"
                + "1. Altas\n"
                + "2. Bajas\n"
                + "3. Modificaciones\n"
                + "q. Volver Menu Principal";
        boolean volver = false;
        do {
            System.out.printf("\n%s: ", menuDirectores);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("\nALTAS");
                    boolean volver1 = false;
                    do {
                        System.out.printf("\n1. Añadir Director a la filmoteca\n"
                                + "q. Volver a Directores: ");
                        String opcion1 = sc.nextLine();
                        switch (opcion1) {
                            case "1":
                                String[] atributos = new String[4];
                                LocalDate nacimiento = LocalDate.of(9999, 9, 9);
                                String tmp;
                                boolean atributoCorrecto = false;

                                do {
                                    System.out.printf("\nNombre: ");
                                    atributos[0] = sc.nextLine();
                                    if (atributos[0].isEmpty()) {
                                        System.out.println("El campo nombre no puede quedar vacio");
                                    }
                                } while (atributos[0].isEmpty());
                                do {
                                    System.out.printf("\nFecha Nacimiento(AAAA-MM-DD): ");
                                    tmp = sc.nextLine();
                                    if (tmp.isEmpty()) {
                                        atributoCorrecto = true;
                                    } else {
                                        try {
                                            nacimiento = LocalDate.parse(tmp);
                                            atributoCorrecto = true;
                                        } catch (DateTimeParseException ex) {
                                            System.out.println("\nLa fecha de nacimiento debe ser valida: AÑO-MES-DIA");
                                        }
                                    }
                                } while (!atributoCorrecto);

                                System.out.printf("\nNacionalidad: ");
                                atributos[1] = sc.nextLine();
                                System.out.printf("\nOcupacion: ");
                                atributos[2] = sc.nextLine();
                                do {
                                    System.out.printf("\nPeliculas (Separadas por comas): ");
                                    atributos[3] = sc.nextLine();
                                    if (atributos[3].isEmpty()) {
                                        System.out.println("Las peliculas no pueden quedar vacias");
                                    }
                                } while (atributos[3].isEmpty());
                                c.añadirDirector(atributos, nacimiento);
                                System.out.println("Se ha añadido correctamente el director");
                                break;
                            case "q":
                                volver1 = true;
                                break;
                        }
                    } while (!volver1);
                    break;
                case "2":
                    System.out.println("\nBAJAS");
                    boolean volver2 = false;
                    do {
                        System.out.printf("\n1. Buscar director a eliminar\n"
                                + "q. Volver a Directores: ");
                        String opcion2 = sc.nextLine();
                        switch (opcion2) {
                            case "1":
                                System.out.printf("\nIntroduce el director para eliminar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaDirector(p);
                                if (i == -1) {
                                    System.out.println("El director no se ha encontrado\n");
                                } else {
                                    c.borrarDirector(i);
                                }
                                break;
                            case "q":
                                volver2 = true;
                                break;
                        }
                    } while (!volver2);
                    break;
                case "3":
                    System.out.println("\nMODIFICACIONES");
                    boolean volver3 = false;
                    do {
                        System.out.printf("1. Modificar Director\n"
                                + "q. Volver a Peliculas: ");
                        String opcion3 = sc.nextLine();
                        switch (opcion3) {
                            case "1":
                                boolean v3 = false;
                                System.out.printf("\nIntroduce un director modificar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaDirector(p);
                                if (i == -1) {
                                    System.out.println("El director no se ha encontrado");
                                } else {
                                    do {
                                        System.out.printf("Elige un atributo para modificar " + p + "\n"
                                                + "1. Fecha de nacimiento\n"
                                                + "2. Nacionalidad\n"
                                                + "3. Ocupacion\n"
                                                + "q. volver: ");
                                        String op3 = sc.nextLine();
                                        switch (op3) {
                                            case "1":
                                            case "2":
                                            case "3":
                                                int o3 = Integer.parseInt(op3);
                                                String[] atributo = {"Fecha Nacimiento(AAAA-MM-DD)",
                                                    "Nacionalidad", "Ocupacion"};
                                                boolean modificado;
                                                do {
                                                    System.out.printf("Introduce el nuevo valor para "
                                                            + atributo[o3 - 1] + ": ");
                                                    String modificacion = sc.nextLine();
                                                    modificado = c.modificarDirector(o3, i, modificacion);
                                                    if (true == modificado) {
                                                        System.out.println("Se ha modificado correctamente\n");
                                                    } else {
                                                        System.out.println("No se ha podido modificar\n");
                                                    }
                                                } while (!modificado);
                                                break;
                                            case "q":
                                                v3 = true;
                                                break;
                                            default:
                                                System.out.println("Opcion incorrecta");
                                        }
                                    } while (!v3);
                                }
                                break;
                            case "q":
                                volver3 = true;
                                break;
                        }
                    } while (!volver3);
                    break;
                case "q":
                    volver = true;
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!volver);
    }

    private void actores() {
        String menuActores = "ACTORES\n"
                + "1. Altas\n"
                + "2. Bajas\n"
                + "3. Modificaciones\n"
                + "4. Peliculas de un actor\n"
                + "q. Volver Menu Principal";
        boolean volver = false;
        do {
            System.out.printf("\n%s: ", menuActores);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("\nALTAS");
                    boolean volver1 = false;
                    do {
                        System.out.printf("\n1. Añadir Actor a la filmoteca\n"
                                + "q. Volver a Actores: ");
                        String opcion1 = sc.nextLine();
                        switch (opcion1) {
                            case "1":
                                String[] atributos = new String[3];
                                LocalDate nacimiento = LocalDate.of(9999, 9, 9);
                                int añoDebut = 9999;
                                String tmp;
                                boolean atributoCorrecto = false;
                                do {
                                    System.out.printf("\nNombre: ");
                                    atributos[0] = sc.nextLine();
                                    if (atributos[0].isEmpty()) {
                                        System.out.println("El campo nombre no puede quedar vacio");
                                    }
                                } while (atributos[0].isEmpty());
                                do {
                                    System.out.printf("\nFecha Nacimiento(AAAA-MM-DD): ");
                                    tmp = sc.nextLine();
                                    if (tmp.isEmpty()) {
                                        atributoCorrecto = true;
                                    } else {
                                        try {
                                            nacimiento = LocalDate.parse(tmp);
                                            atributoCorrecto = true;
                                        } catch (DateTimeParseException ex) {
                                            System.out.println("\nLa fecha de nacimiento debe ser valida: AÑO-MES-DIA");
                                        }
                                    }
                                } while (!atributoCorrecto);

                                System.out.printf("\nNacionalidad: ");
                                atributos[1] = sc.nextLine();
                                atributoCorrecto = false;
                                do {
                                    System.out.printf("\nAño Debut: ");
                                    tmp = sc.nextLine();
                                    if (tmp.isEmpty()) {
                                        atributoCorrecto = true;
                                    } else {
                                        try {
                                            añoDebut = Integer.parseInt(tmp);
                                            atributoCorrecto = true;
                                        } catch (NumberFormatException ex) {
                                            System.out.println("\nEl año debut debe ser un año valido");
                                        }
                                    }
                                } while (!atributoCorrecto);
                                do {
                                    System.out.printf("\nPeliculas (Separadas por comas): ");
                                    atributos[2] = sc.nextLine();
                                    if (atributos[2].isEmpty()) {
                                        System.out.println("Las peliculas no pueden quedar vacias");
                                    }
                                } while (atributos[2].isEmpty());
                                c.añadirActor(atributos, nacimiento, añoDebut);
                                System.out.println("Se ha añadido correctamente el actor");
                                break;
                            case "q":
                                volver1 = true;
                                break;
                        }
                    } while (!volver1);

                    break;
                case "2":
                    System.out.println("\nBAJAS");
                    boolean volver2 = false;
                    do {
                        System.out.printf("\n1. Buscar actor a eliminar\n"
                                + "q. Volver a Actores: ");
                        String opcion2 = sc.nextLine();
                        switch (opcion2) {
                            case "1":
                                System.out.printf("\nIntroduce el actor para eliminar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaActor(p);
                                if (i == -1) {
                                    System.out.println("El actor no se ha encontrado\n");
                                } else {
                                    c.borrarActor(i);
                                }
                                break;
                            case "q":
                                volver2 = true;
                                break;
                        }
                    } while (!volver2);
                    break;
                case "3":
                    System.out.println("\nMODIFICACIONES");
                    boolean volver3 = false;
                    do {
                        System.out.printf("\n1. Modificar Actor\n"
                                + "q. Volver a Peliculas: ");
                        String opcion3 = sc.nextLine();
                        switch (opcion3) {
                            case "1":
                                boolean v3 = false;
                                System.out.printf("\nIntroduce un actor modificar: ");
                                String p = sc.nextLine();
                                int i = c.busquedaActor(p);
                                if (i == -1) {
                                    System.out.println("El actor no se ha encontrado");
                                } else {
                                    do {
                                        System.out.printf("Elige un atributo para modificar " + p + "\n"
                                                + "1. Fecha de nacimiento\n"
                                                + "2. Nacionalidad\n"
                                                + "3. Año Debut\n"
                                                + "q. volver: ");
                                        String op3 = sc.nextLine();
                                        switch (op3) {
                                            case "1":
                                            case "2":
                                            case "3":
                                                int o3 = Integer.parseInt(op3);
                                                String[] atributo = {"Fecha Nacimiento(AAAA-MM-DD)",
                                                    "Nacionalidad", "Año Debut"};
                                                boolean modificado;
                                                do {
                                                    System.out.printf("Introduce el nuevo valor para "
                                                            + atributo[o3 - 1] + " : ");
                                                    String modificacion = sc.nextLine();
                                                    modificado = c.modificarActor(o3, i, modificacion);
                                                    if (true == modificado) {
                                                        System.out.println("Se ha modificado correctamente\n");
                                                    } else {
                                                        System.out.println("No se ha podido modificar\n");
                                                    }
                                                } while (!modificado);
                                                break;
                                            case "q":
                                                v3 = true;
                                                break;
                                            default:
                                                System.out.println("Opcion incorrecta");
                                        }
                                    } while (!v3);
                                }
                                break;
                            case "q":
                                volver3 = true;
                                break;
                        }
                    } while (!volver3);
                    break;
                case "4":
                    System.out.println("\nPELICULAS DE UN ACTOR");
                    boolean volver4 = false;
                    do {
                        System.out.printf("\n1. Buscar actor para ver peliculas\n"
                                + "q. Volver a Actores: ");
                        String opcion2 = sc.nextLine();
                        switch (opcion2) {
                            case "1":
                                System.out.printf("\nIntroduce el actor: ");
                                String p = sc.nextLine();
                                int i = c.busquedaActor(p);
                                if (i == -1) {
                                    System.out.println("El actor no se ha encontrado\n");
                                } else {
                                    String[] peliculas = c.peliculasActor(i);
                                    if (null == peliculas) {
                                        System.err.println("Error: No ha sido posible obtener el listado");
                                        return;
                                    }
                                    String linea = "+------------------------------------------"
                                            + "--------+------+----------+--------------------------"
                                            + "------+-----------------+";
                                    System.out.println(linea);
                                    System.out.printf("| %-117s |\n", "LISTADO DE PELICULAS DEL ACTOR " + p);
                                    System.out.println(linea);
                                    System.out.printf("| %-48s | %4s | %8s | %-30s | %15s |\n",
                                            "TITULO", "AÑO", "DURACION", "PAIS", "GENERO");
                                    for (String s : peliculas) {
                                        System.out.println(linea);
                                        System.out.printf("%s%n", s);
                                    }
                                    System.out.println(linea);
                                }
                                break;
                            case "q":
                                volver4 = true;
                                break;
                        }
                    } while (!volver4);
                    break;
                case "q":
                    volver = true;
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!volver);
    }

    private void imprimirListados() {
        String menuListado = "ARCHIVOS\n"
                + "1. Listado de Peliculas\n"
                + "2. Listado de Directores\n"
                + "3. Listado de Actores\n"
                + "q. Volver Menu Principal";
        boolean volver = false;
        do {
            System.out.println("");
            System.out.printf("%s: ", menuListado);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    String[] peliculas = c.peliculasColumns();
                    if (null == peliculas) {
                        System.err.println("Error: No ha sido posible obtener el listado");
                        return;
                    }
                    String linea = "+------------------------------------------"
                            + "--------+------+----------+--------------------------"
                            + "------+-----------------+";
                    System.out.println(linea);
                    System.out.printf("| %-117s |\n", "LISTADO DE PELICULAS");
                    System.out.println(linea);
                    System.out.printf("| %-48s | %4s | %8s | %-30s | %15s |\n",
                            "TITULO", "AÑO", "DURACION", "PAIS", "GENERO");
                    for (String s : peliculas) {
                        System.out.println(linea);
                        System.out.printf("%s%n", s);
                    }
                    System.out.println(linea);

                    break;
                case "2":
                    String[] directores = c.directoresColumns();
                    if (null == directores) {
                        System.err.println("Error: No se ha podido obtener el listado");
                        return;
                    }
                    String line = "+----------------------+------------+-----"
                            + "------------+---------------------------------"
                            + "---------------------------------+------------"
                            + "----------------------------------------------"
                            + "---------+";
                    System.out.println(line);
                    System.out.printf("| %-186s |\n", "LISTADO DE DIRECTORES");
                    System.out.println(line);
                    System.out.printf("| %-20s | %10s | %15s | %-64s | %-65s |\n",
                            "NOMBRE", "NACIMIENTO", "NACIONALIDAD",
                            "OCUPACION", "PELICULAS");
                    for (String s : directores) {
                        System.out.println(line);
                        System.out.printf("%s%n", s);
                    }
                    System.out.println(line);
                    break;
                case "3":
                    String[] actores = c.actoresColumns();
                    if (null == actores) {
                        System.err.println("Error: No se ha podido obtener el listado");
                        return;
                    }
                    String ln = "+----------------------+------------+-----"
                            + "------------+-----------+-------------------"
                            + "---------------------------------------------"
                            + "---------------------------------------------"
                            + "---------------------------------+";
                    System.out.println(ln);
                    System.out.printf("| %-206s |\n", "LISTADO DE ACTORES");
                    System.out.println(ln);
                    System.out.printf("| %-20s | %10s | %-15s | %-9s | %-140s |\n",
                            "NOMBRE", "NACIMIENTO", "NACIONALIDAD",
                            "AÑO DEBUT", "PELICULAS");
                    for (String s : actores) {
                        System.out.println(ln);
                        System.out.printf("%s%n", s);
                    }
                    System.out.println(ln);
                    break;
                case "q":
                    volver = true;
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!volver);
    }

    private boolean preguntarSiSalimos() {
        String opcion;
        do {
            System.out.printf("Deseo realmente salir de la aplicacion:  ");
            opcion = sc.nextLine();
        } while (!"sSnN".contains(opcion));
        System.out.println();
        if ("sS".contains(opcion)) {
            c.salida();
        }
        return (!opcion.isEmpty() && "sS".contains(opcion));

    }

}
