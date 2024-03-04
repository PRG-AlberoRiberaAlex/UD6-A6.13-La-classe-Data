/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog.cipfpbatoi;

/**
 *
 * @author batoi
 */
public class TestData {

    public static void main(String[] args) {
        Data fecha1 = new Data(5, 1, 2012);
        Data fecha2 = new Data("23/3/2021");
        Data fecha3 = new Data();
        Data fecha4 = new Data(16, 1, 2021);
        Data d1 = fecha2.clone();
        System.out.println("=== INICIO- PRUEBA DE CONSTRUCTORES- INICIO ===");
        System.out.println("____________________________________________________________");
        System.out.println("Crea un nuevo objeto usando el contructor parametritizar int");
        Constructores(fecha1);
        System.out.println("____________________________________________________________");
        System.out.println("Crea un nuevo objeto usando el contructor String");
        Constructores(fecha2);
        System.out.print("Fecha clonada: ");
        d1.mostrarEnFormatES();
        System.out.println("La fecha con los contructores anteriores son iguales: " + d1.isIgual(fecha2));
        System.out.println("____________________________________________________________");
        System.out.println("Crea un nuevo objeto usando el contructor por defecto");
        Constructores(fecha3);
        System.out.println("____________________________________________________________");
        System.out.println("=== FIN- PRUEBA DE CONSTRUCTORES- FIN ===");
        System.out.println(" ");
        System.out.println("=== INICIO- PRUEBA DE METODOS AÑADIR/RESTAR DIAS- INICIO ===");
        System.out.print("Fecha inicial: ");
        fecha1.mostrarEnFormatES();
        AñadirRestar(fecha4);
        System.out.println("=== FIN- PRUEBA DE METODOS AÑADIR/RESTAR DIAS- FIN ===");
        System.out.println("=== INICIO- PRUEBA DEL METODO MODIFICADOR- INICIO ===");
        fecha4.set(22, 1, 2021);
        Constructores(fecha4);
    }

    public static void Constructores(Data fecha) {
        System.out.print("Fecha formato español: ");
        fecha.mostrarEnFormatES();
        System.out.print("Fecha formato ingles: ");
        fecha.mostrarEnFormatGB();
        System.out.print("Fecha en texto: ");
        fecha.mostrarEnFormatText();
        System.out.println("Es correcta: " + fecha.isCorrecta());
        System.out.println("Es festivo: " + fecha.isFestiu());
        System.out.println("El dia de la semana és: " + fecha.getDiaSetmana());
    }

    public static void AñadirRestar(Data fecha) {
        Data d1 = fecha.afegir(1);
        Data d2 = fecha.restar(1);
        Data d3 = fecha.restar(30);
        System.out.println("____________________________________________________________");
        System.out.println("El dia de mañana a la fecha base: ");
        Constructores(d1);
        System.out.println("____________________________________________________________");
        System.out.println("El dia anterior a la fecha base: ");
        Constructores(d2);
        System.out.println("____________________________________________________________");
        System.out.println("30 dias antes a la fecha base: ");
        Constructores(d3);
        System.out.println("____________________________________________________________");
    }
  
}
