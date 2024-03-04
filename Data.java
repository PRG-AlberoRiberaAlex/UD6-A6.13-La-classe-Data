package prog.cipfpbatoi;

import java.util.StringTokenizer;

public class Data {

    private int dia;

    private int mes;

    private int any;

    private static final String[] DIES_TEXT = new String[] { "diumenge", "dilluns", "dimarts", "dimecres", "dijous",
            "divendres",
            "dissabte" };

    private static final String[] MESOS_TEXT = new String[] { "gener", "febrer", "març", "abril", "maig", "juny",
            "juliol", "agost", "setembre", "octubre", "novembre", "desembre" };

    public Data() {
        this.any = 1970;
        this.dia = 1;
        this.mes = 1;
    }

    public Data(int dia, int mes, int any) {
        this.any = any;
        this.mes = mes;
        this.dia = dia;
    }

  
    public Data(String data) {
        StringTokenizer st = new StringTokenizer(data, "/");
        if (st.countTokens() == 3) {
            String token = st.nextToken();
            if (esNumero(token)) {
                this.dia = Integer.parseInt(token);
            }
            token = st.nextToken();
            if (esNumero(token)) {
                this.mes = Integer.parseInt(token);
            }
            token = st.nextToken();
            if (esNumero(token)) {
                this.any = Integer.parseInt(token);
            }
        }
    }

    // Método para verificar si un String representa un número
    private boolean esNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public void set(int dia, int mes, int anyo) {
        this.any = any + anyo;
        this.dia = dia + dia;
        this.mes = mes + mes;
    }

    public Data clone() {
        Data fecha = new Data(this.dia, this.mes, this.any);
        return fecha;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAny() {
        return any;
    }

    public void mostrarEnFormatES() {
        System.out.println(this.dia + "-" + this.mes + "-" + this.any);
    }

    public void mostrarEnFormatGB() {
        System.out.println(this.any + "-" + this.mes + "-" + this.dia);
    }

    public void mostrarEnFormatText() {
        if (this.dia >= 1 && this.dia <= 31 && this.mes >= 1 && this.mes <= 12) {
            String nombreMes = MESOS_TEXT[this.mes - 1];
            System.out.println(this.dia + " " + nombreMes + " de " + this.any);
        } else {
            System.out.println("Fecha inválida");
        }
    }

    /**
     * Retorna un booleano indicando si la fecha del objeto es igual a la fecha
     * pasada como argumento
     *
     * @return boolean
     */
    public boolean isIgual(Data otraFecha) {
        boolean iguales = this.getDia() == otraFecha.getDia()
                && this.getMes() == otraFecha.getMes()
                && this.getAny() == otraFecha.getAny();

        if (iguales) {
            System.out.println("Las fechas son la misma");
        } else {
            System.out.println("Las fechas son diferentes.");
        }

        return iguales;
    }

    /**
     * Retorna un String que representa el dia de la setmana en format text
     * (dilluns, dimarts, dimecres, dijous, divendres, dissabte, diumenge).
     * L'algorisme de resolució d'aquest mètode es troba al enunciat.
     *
     * @return String
     */
    public String getDiaSetmana() {
        int diasPasados = getDiesTranscorregutsOrigen();

        int diaSemana = diasPasados % 7;
        if (diaSemana > DIES_TEXT.length) {
            System.out.println("Dia invalido");
        }
        return DIES_TEXT[diaSemana];
    }

    /**
     * Retorna un booleà indicant si la data representada per l'objecte actual
     * és festiva. Es considerarà festiu si el dia de la setmana és dissabte o
     * diumenge
     *
     * @return boolean
     */
    public boolean isFestiu() {
        boolean festivo;
        String diaSetmana = getDiaSetmana();
        if (diaSetmana.equals("dissabte") || diaSetmana.equals("diumenge")) {
            festivo = true;
            return festivo;
        } else {
            festivo = false;
            return festivo;
        }
    }

    /**
     * Retorna el número de la setmana dins de l'any actual. Es considera una
     * setmana l'interval de dates entre una data que siga dilluns i la següent
     * data en ordre cronològic que siga diumenge. També es comptabilitza com a
     * setmana tant la primera setmana de l'any com l'última (inclusivament en
     * aquells anys en què la primera i/o última setmana no conté set dies en
     * total).
     *
     * @return int dia semana
     */
    public int getNumeroSetmana() {
        int diasaño = getDiesAny(this.any);
        int semanas = diasaño / 7;
        int diasTranscurridosEsteAño = getDiesTranscorregutsEnAny();
        if (getDiaSetmana().equals("dilluns") && diasTranscurridosEsteAño % 7 != 0) {
            semanas++;
        }
        System.out.println(semanas + "Semanas");
        return semanas;
    }

    /**
     * Retorna un nou objecte de tipus data que representa la data resultant
     * d'afegir el nombre de dies passats com a argument a la data que
     * representa l'objecte actual. Haurem de tindre en compte els dies que té
     * el mes actual i si l'any és de traspàs (bisiesto) amb la finalitat de
     * construir el nou objecte amb la data correcta. El màxim nombre de dies
     * que podrem afegir serà 30 i no podrem afegir un nombre negatiu de dies.
     *
     * @return boolean
     */
    public Data afegir(int numDias) {
        int Mesactual = getDiesMes(this.mes, this.any);
        if (numDias > 30 || numDias < 0) {
            System.out.println("Dias incorrectos");
            return null;
        }
        int diaResult = this.dia + numDias;
        int mesResult = this.mes;
        int añoResult = this.any;

        if (diaResult > Mesactual) {
            diaResult -= Mesactual;
            mesResult++;
            if (mesResult > 12) {
                mesResult = 1;
                añoResult++;
            }
        }

        return new Data(diaResult, mesResult, añoResult);
    }

    /**
     * Retorna un nou objecte de tipus data que representa la data resultant de
     * restar el nombre de dies passats com a argument a la data que representa
     * l'objecte actual. Haurem de tindre en compte els dies que té el mes
     * actual i si l'any és de traspàs (bisiesto) amb la finalitat de construir
     * el nou objecte amb la data correcta. El màxim nombre de dies que podrem
     * restar serà 30 i no podrem restar un nombre negatiu de dies.
     *
     * @return boolean
     */
    public Data restar(int numDias) {
        if (numDias > 30 || numDias < 0) {
            System.out.println("Dias incorrectos");
            return null;
        }
        int diaResult = this.dia - numDias;
        int mesResult = this.mes;
        int añoResult = this.any;

        while (diaResult <= 0) {
            mesResult--;
            if (mesResult <= 0) {
                mesResult = 12;
                añoResult--;
            }
            int diesMesAnterior = getDiesMes(mesResult, añoResult);
            diaResult += diesMesAnterior;
        }
        return new Data(diaResult, mesResult, añoResult);
    }

    /**
     * Retorna un booleà indicant si la data representada per l'objecte actual
     * és correcta. No oblides comprovar que el dia es trobe dins del rang dels
     * dies que té el mes tenint en compte si l'any és de traspàs(bisiesto) o
     * no.
     *
     * @return
     */
    public boolean isCorrecta() {
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Comprova si és un any de traspàs
        boolean anyTraspas = isBisiesto(any);
        int diesMes = getDiesMes(mes, anyTraspas ? 366 : 365);

        // Verifica que el dia estigui dins del rang del mes
        return dia >= 1 && dia <= diesMes;
    }

    /**
     * Retorna el mes del año en formato text (enero, febrero, marzo,...)
     *
     * @return char
     */
    private String getMesEnFormatText() {
        if (this.mes >= 1 && this.mes <= MESOS_TEXT.length) {
            String Mes = DIES_TEXT[this.mes - 1];
            return Mes;
        } else {
            return "Mes inválido";
        }
    }

    /**
     * Devuelve el número de dias transcurridos desde el 1-1-1
     *
     * @return int
     */
    private int getDiesTranscorregutsOrigen() {
        int totalDies = 0;
        int anyInicial = 1970;
        int anyActual = this.any;
    
        // Calcular los días transcurridos desde 1970 hasta el año actual
        for (int i = anyInicial; i < anyActual; i++) {
            totalDies += isBisiesto(i) ? 366 : 365;
        }
    
        totalDies += getDiesMes(this.mes, this.any) + this.dia;
    
        return totalDies;
    }

    private int getDiesTranscorregutsEnAny() {
        int totalDias = 0;
        for (int i = 1; i < this.mes; i++) {
            totalDias += getDiesMes(i, this.any);
        }
        totalDias += this.dia;
        return totalDias;
    }

    /**
     * Indica si el año pasado como argumento es bisiesto. Un año es bisiesto si
     * es divisible por 4 pero no es divisible entre 100 o es divisible entre 4
     * entre 100 y entre 400
     *
     * @return boolean
     */
    public static boolean isBisiesto(int anyo) {
        return (anyo % 4 == 0 && anyo % 100 != 0) || (anyo % 400 == 0);
    }

    /**
     * Calcula el número de días que tiene el @mes en el @año pasado como
     * argumento Deberás hacer uso del métodos isBisiesto
     *
     * @return int total dias mes en curso
     */
    public static int getDiesMes(int mes, int anyo) {
        int[] diasMes = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (mes == 2 && isBisiesto(anyo)) {
            return 29;
        }
        return diasMes[mes - 1];
    }

    /**
     * Calcula el número total de dias que tiene el año pasado como argumento
     *
     * @return int total dias anyo en curso
     */
    public static int getDiesAny(int anyo) {
        return isBisiesto(anyo) ? 366 : 365;
    }
}
