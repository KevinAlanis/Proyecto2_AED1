package ServerCliente;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class mainServer {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            public void run() {
                Servidor s = new Servidor();
                try {
                    s.inicio();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        };
        Thread t2=new Thread(){
            public void run() {
                ServerCliente.Cliente cc = new Cliente();
                try {
                    cc.mainClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
    }
}
