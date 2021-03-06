import java.math.BigInteger;

public class PerfectWorker extends Thread{

    private Buffer buffer;
    private BigInteger suma = BigInteger.ZERO;
    private BigInteger datoActual;
    private Barrier miPool;

    public PerfectWorker(Buffer buffer, Barrier miPool){
        this.buffer=buffer;
        this.miPool=miPool;
    }

    public void run(){

        while(true){

            this.datoActual = this.buffer.pop();
            suma = BigInteger.ZERO;

            if (this.datoActual.compareTo(BigInteger.ZERO) >= 0) {

                for (BigInteger i = BigInteger.ONE; i.compareTo(this.datoActual) < 0; i=i.add(BigInteger.ONE)) {

                        if (this.datoActual.mod(i).equals(BigInteger.ZERO)) {
                            suma = suma.add(i);
                        }
                }

                if (suma.equals(this.datoActual)) {
                    System.out.println(this.datoActual.toString() + " es número perfecto");
                }

            }else{
                break; // si el numero es negativo, termina el thread automaticamente
            }
        }
        System.out.println("Antes de terminar");
        this.miPool.esperar();
        System.out.println("termine!");
    }
}
