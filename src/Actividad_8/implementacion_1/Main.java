package Actividad_8.implementacion_1;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        long start = 0;
        long tiempo = 0;
        int cantidadPuntos = (int) Math.pow(2.0, 14.0);

        start = System.currentTimeMillis();
        List<Punto> puntos = generarPuntos(cantidadPuntos);
        tiempo = System.currentTimeMillis() - start;
        System.out.println("Se generaron (" + tiempo + " ms): " + cantidadPuntos + " randoms");


        List<Punto> puntosOrdenadosPorY = new ArrayList<Punto>(puntos);
        ordenarPorY(puntosOrdenadosPorY);
        List<Punto> puntosOrdenadosPorX = new ArrayList<Punto>(puntosOrdenadosPorY);
        ordenarPorX(puntosOrdenadosPorX);


        System.out.println("-------------------------------------------------------------------------------");
        start = System.currentTimeMillis();
        Par parAlgoritmoBasico = algoritmoBasico(puntosOrdenadosPorX);
        tiempo = System.currentTimeMillis() - start;
        System.out.println("Algoritmo basico (" + tiempo + " ms): " + parAlgoritmoBasico);


        System.out.println("-------------------------------------------------------------------------------");
        start = System.currentTimeMillis();
        Par parDYC1 = DyC1(puntosOrdenadosPorX);
        tiempo = System.currentTimeMillis() - start;
        System.out.println("Divide y conquistar 1 (" + tiempo + " ms): " + parDYC1);


        System.out.println("-------------------------------------------------------------------------------");
        start = System.currentTimeMillis();
        Par parDYC2 = DyC2(puntosOrdenadosPorX, puntosOrdenadosPorY);
        tiempo = System.currentTimeMillis() - start;
        System.out.println("Divide y conquistar 2 (" + tiempo + " ms): " + parDYC2);
    }

    public static List<Punto> generarPuntos(int cant){
        Random random = new Random();
        List<Punto> puntos = new ArrayList<Punto>();
        for(int i = 0; i < cant; i++){
            Punto p = new Punto(random.nextInt((int) Math.pow(10.0,9.0)), random.nextInt((int) Math.pow(10.0,9.0)));
            puntos.add(p);
        }
        return puntos;
    }

    public static class Punto {
        public final int x;
        public final int y;

        public Punto(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "(" + x + ", " + y + ")";
        }
    }

    public static class Par {
        public Punto punto1 = null;
        public Punto punto2 = null;
        public double distancia = 0.0;

        public Par(Punto punto1, Punto punto2) {
            this.punto1 = punto1;
            this.punto2 = punto2;
            this.distancia = distancia(punto1, punto2);
        }

        public void update(Punto punto1, Punto punto2, double distance) {
            this.punto1 = punto1;
            this.punto2 = punto2;
            this.distancia = distancia;
        }

        public String toString() {
            return punto1 + "-" + punto2 + " : " + distancia;
        }
    }

    public static double distancia(Punto p1, Punto p2) {
        return Math.hypot((p2.x - p1.x), (p2.y - p1.y));
    }

    public static Par min(Par d1, Par d2) {
      Par respuesta;

      if(d1.distancia < d2.distancia) {
          respuesta = d1;
      } else {
          respuesta = d2;
      }

      return respuesta;
    }

    public static Par algoritmoBasico(List<? extends Punto> puntos) {
        if (puntos.size() < 2)
            return null;
        Par par = new Par(puntos.get(0), puntos.get(1));
        if (puntos.size() > 2) {
            for (int i = 0; i < puntos.size() - 1; i++) {
                Punto punto1 = puntos.get(i);
                for (int j = i + 1; j < puntos.size(); j++) {
                    Punto punto2 = puntos.get(j);
                    double distancia = distancia(punto1, punto2);
                    if (distancia < par.distancia)
                        par.update(punto1, punto2, distancia);
                }
            }
        }
        return par;
    }

    public static void ordenarPorX(List<? extends Punto> points) {
        Collections.sort(points, new Comparator<Punto>() {
                public int compare(Punto punto1, Punto punto2)
                {
                    if (punto1.x < punto2.x)
                        return -1;
                    if (punto1.x > punto2.x)
                        return 1;
                    return 0;
                }
            }
        );
    }

    public static void ordenarPorY(List<? extends Punto> points) {
        Collections.sort(points, new Comparator<Punto>() {
                public int compare(Punto punto1, Punto punto2)
                {
                    if (punto1.y < punto2.y)
                        return -1;
                    if (punto1.y > punto2.y)
                        return 1;
                    return 0;
                }
            }
        );
    }

    public static Par DyC1(List<? extends Punto> puntosOrdenadosPorX) {
        int cantidadPuntos = puntosOrdenadosPorX.size();

        if(cantidadPuntos <= 3)
            return algoritmoBasico(puntosOrdenadosPorX);

        int mitadArreglo = cantidadPuntos >>> 1;
        List<? extends Punto> izquierdaX = puntosOrdenadosPorX.subList(0, mitadArreglo);
        List<? extends Punto> derechaX = puntosOrdenadosPorX.subList(mitadArreglo, cantidadPuntos);

        Punto puntoMedio = puntosOrdenadosPorX.get(mitadArreglo);

        Par d1 = DyC1(izquierdaX);
        Par d2 = DyC1(derechaX);

        Par d = min(d1, d2);

        List<Punto> franja = crearFranja(puntosOrdenadosPorX, puntoMedio, d.distancia);

        ordenarPorY(franja);

        return min(d, minimoEnFranja(franja, d));
    }



    public static Par DyC2(List<? extends Punto> puntosOrdenadosPorX, List<? extends Punto> puntosOrdenadosPorY) {

        int cantidadPuntos = puntosOrdenadosPorX.size();
        if (cantidadPuntos <= 3)
            return algoritmoBasico(puntosOrdenadosPorX);

        int mitadArreglo = cantidadPuntos >>> 1;
        List<? extends Punto> izquierdaX = puntosOrdenadosPorX.subList(0, mitadArreglo);
        List<? extends Punto> derechaX = puntosOrdenadosPorX.subList(mitadArreglo, cantidadPuntos);

        List<Punto> derechaY = new ArrayList<Punto>();
        List<Punto> izquierdaY = new ArrayList<Punto>();

        Punto puntoMedio = puntosOrdenadosPorX.get(mitadArreglo);
        for(int i = 0; i < puntosOrdenadosPorY.size(); i++){
            if(puntosOrdenadosPorY.get(i).x < puntoMedio.x){
                izquierdaY.add(puntosOrdenadosPorY.get(i));
            }
            else{
                derechaY.add(puntosOrdenadosPorY.get(i));
            }
        }

        Par d1 = DyC2(izquierdaX, izquierdaY);
        Par d2 = DyC2(derechaX, derechaY);

        Par d = min(d1, d2);

        List<Punto> franja = crearFranja(puntosOrdenadosPorY, puntoMedio, d.distancia);

        return min(d, minimoEnFranja(franja, d));
    }

    private static List<Punto> crearFranja(List<? extends Punto> puntos, Punto puntoMedio, Double distancia){

        List<Punto> franja = new ArrayList<Punto>();

        for(int i = 0; i < puntos.size(); i++){
            Punto p = puntos.get(i);
            if (Math.abs(puntoMedio.x - p.x) < distancia)
                franja.add(p);
        }

        return franja;
    }

    private static Par minimoEnFranja(List<? extends Punto> franja, Par parMinimo) {
        for (int i = 0; i < franja.size() - 1; i++) {
            for (int j = i + 1; j < franja.size() && ((franja.get(j).y - franja.get(i).y) < parMinimo.distancia); j++) {
                double distancia = distancia(franja.get(j), franja.get(i));
                if (distancia < parMinimo.distancia) {
                    parMinimo.update(franja.get(j), franja.get(i), distancia);
                }
            }
        }
        return parMinimo;
    }
}
