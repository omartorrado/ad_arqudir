/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arqudir;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author otorradomiguez
 */
public class Arqudir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1,2- Crea el direcrtorio y el archivo, tambien compueba si ya han sido creados
        crearDirectorio("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir");
        crearArchivo("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        //3- Vuelvo a lanzar los mismos metodos para comprobar la creacion
        checkExists("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir");
        checkExists("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        //4- creamos subdir y Products2.txt y comprobamos que existen
        crearDirectorio("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/subdir");
        crearArchivo("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/subdir/Products2.txt");
        checkExists("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/subdir");
        checkExists("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/subdir/Products2.txt");
        //5- Listar los contenidos del directorio arquivosdir
        verDirectorio("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir");
        //6- Comprobar la ruta del primer directorio que creamos
        verRuta("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir");
        //7- Mostrar informacion del archivo Products1.txt
        verInfoArchivo("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        //8- Cambiar los permisos de lectura/escritura de un archivo
        hacerSoloLectura("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        hacerWritable("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        //9- Borrar el archivo Products1.txt
        borrar("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir/Products1.txt");
        //10- Borrar todo
        borrarRecursivo("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/arqudir/arquivosdir");
    }

    //Los metodos creados a continuacion, tambien podrian recibir directamente un archivo en lugar de la ruta
    public static boolean crearDirectorio(String ruta) {
        File directorio = new File(ruta);
        //Comprobar que la ruta existe
        //System.out.println(directorio.exists());
        if (directorio.exists() == false) {
            return directorio.mkdir();
        } else {
            System.out.println("El directorio ya existe");
            return false;
        }
    }

    public static boolean crearArchivo(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists() == false) {
            try {
                archivo.createNewFile();
                return true;
            } catch (IOException ex) {
                System.out.println("Error al crear el archivo");
                return false;
            }
        } else {
            System.out.println("El archivo ya existe");
            return false;
        }
    }

    public static void checkExists(String ruta) {
        File path = new File(ruta);
        if (path.exists()) {
            System.out.println("El directorio o archivo existe");
        } else {
            System.out.println("No se encuentra el directorio o archivo");
        }
    }

    public static void verDirectorio(String ruta) {
        File path = new File(ruta);
        if (path.isDirectory()) {
            String[] listado = path.list();
            for (String x : listado) {
                System.out.println(x);
            }
        } else {
            System.out.println("No se encuentra el directorio");
        }
    }

    public static void verRuta(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            System.out.println(archivo.getAbsolutePath());
            /*
        //Segunda forma de obtener la ruta
        try {
            System.out.println(archivo.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(Arqudir.class.getName()).log(Level.SEVERE, null, ex);
        }
             */
 /*
        //Tercer forma de obtener la ruta
        System.out.println(archivo.getPath());
             */
        }
    }

    public static void verInfoArchivo(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists() && archivo.isFile()) {
            System.out.println("Nombre: " + archivo.getName());
            System.out.println("Ruta: " + archivo.getPath());
            System.out.println("Writable: " + archivo.canWrite());
            System.out.println("Readable: " + archivo.canRead());
            System.out.println("Longitud en bytes: " + archivo.length());
        }
    }

    public static void hacerSoloLectura(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            archivo.setWritable(false);
            System.out.println("No Writable: " + !archivo.canWrite());
        }
    }

    public static void hacerWritable(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            archivo.setWritable(true);
            System.out.println("Writable: " + archivo.canWrite());
        }
    }

    public static void borrar(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            if (archivo.isFile()) {
                System.out.println("Se va a borrar el archivo " + archivo.getName());
                archivo.delete();
            } else if (archivo.isDirectory()) {
                System.out.println("Se va a borrar el directorio " + archivo.getName());
                boolean check = archivo.delete();
                if (check == false) {
                    System.out.println("El directorio " + archivo.getName() + " no esta vacio, borrando contenido...");
                }
            }
        }
    }

    public static void borrarRecursivo(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            if (archivo.isFile()) {
                System.out.println("Se va a borrar el archivo " + archivo.getName());
                archivo.delete();
            } else if (archivo.isDirectory()) {
                File[] listaArchivos = archivo.listFiles();
                for (File x : listaArchivos) {
                    if (x.isFile()) {
                        System.out.println("Se va a borrar el archivo " + x.getName());
                        x.delete();
                    } else {
                        System.out.println("Se va a borrar el directorio " + x.getName());
                        boolean check = x.delete();
                        if (check == false) {
                            System.out.println("El directorio " + x.getName() + " no esta vacio, borrando contenido...");
                            borrarRecursivo(x.getPath());
                            System.out.println("Se han borrado los contenidos del directorio " + x.getName());
                        }
                    }
                }
                System.out.println("Se va a borrar el directorio " + archivo.getName());
                archivo.delete();
            }
        }
    }
}
