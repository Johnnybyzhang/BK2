import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {

    backend bk = new backend();

    public MyHandler(backend bk) {
        this.bk=bk;
    }

    @Override

    public void handle(HttpExchange httpExchange) throws IOException {

        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {

            requestParamValue = handleGetRequest(httpExchange);

        } else if ("POST".equals(httpExchange)) {

            throw new IOException("POST method not implemented");
            // requestParamValue = handlePostRequest(httpExchange);

        }

        handleResponse(httpExchange, requestParamValue,bk);
        bk.postState(toBool(requestParamValue,bk));
    }

    public static boolean toBool(String requestParamValue, backend bk) {
        if(requestParamValue.equals("true"))
            return true;
        else if(requestParamValue.equals("false"))
            return false;
        else {
            return bk.getState();
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        try{
            return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
        }
        catch (Exception e){
            return "";
        }

    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue, backend bk) throws IOException {

        OutputStream outputStream = httpExchange.getResponseBody();

        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append(bk.getState());


        // encode HTML content

        String htmlResponse = htmlBuilder.toString();

        // this line is a must

        httpExchange.sendResponseHeaders(200, htmlResponse.length());

        outputStream.write(htmlResponse.getBytes());

        outputStream.flush();

        outputStream.close();

    }
}
