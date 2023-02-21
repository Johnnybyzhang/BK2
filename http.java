import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class http {
     private void start(backend bk) throws IOException, InterruptedException{
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new MyHandler(bk));
        server.setExecutor(threadPoolExecutor); 
        server.start();
     }
     
    public static void main(String[] args) {
        http server = new http();
        backend bk = new backend();
        try{
            bk.start();
            server.start(bk);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // private static void debug(backend bk) {
    //     boolean changed = true;
    //     boolean previousState = true;
    //     while(true){
    //         if(changed){
    //             System.out.println(bk.getState());
    //             changed = false;
    //         }
    //         boolean currentState=bk.getState();
    //         if(previousState==!currentState){
    //             changed = true;
    //             previousState=currentState;
    //         }
    //     }
    // }
}
