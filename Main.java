import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        int valor;
        String monedaD = "";
        String monedaO = "";
        System.out.println("*********************************************************");
        do {
            System.out.println("""
                    Sea bienvenido/a al Conversor de Moneda
                                        
                    1)Dolar =>> Peso Argentino
                    2)Peso Argentino =>> Dolar
                    3)Dolar =>> Real Brasile;o
                    4)Real Brasile;o =>> Dolar
                    5)Dolar =>> Peso Colombiano
                    6)Peso Colombiano =>> Dolar
                    7) Salir
                    Eliga una opcion valida:
                    *********************************************************
                    """);
            opcion = entrada.nextInt();
            if (opcion != 7) {
                System.out.println("Ingrese el valor que deseas convertir:");
                valor = entrada.nextInt();
                switch (opcion) {

                    case 1:
                        monedaO = "USD";
                        monedaD = "ARS";
                        break;
                    case 2:
                        monedaD = "USD";
                        monedaO = "ARS";
                        break;
                    case 3:
                        monedaO = "USD";
                        monedaD = "BRL";
                        break;

                    case 4:
                        monedaD = "USD";
                        monedaO = "BRL";
                        break;
                    case 5:
                        monedaO = "USD";
                        monedaD = "COP";
                        break;
                    case 6:
                        monedaD = "USD";
                        monedaO = "COP";
                        break;
                }

                String direccion = "https://v6.exchangerate-api.com/v6/0820ea3acdaaa28448b732d6/pair/" + monedaO + "/" + monedaD;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                Gson gson = new Gson();
                Moneda_Destino miMonedaDestino = gson.fromJson(json, Moneda_Destino.class);
                Float moneda = Float.valueOf(miMonedaDestino.conversion_rate());
                System.out.println(moneda.getClass());
                float cambio = valor * moneda;
                System.out.println("El valor " + valor + " " + monedaO + " corresponde al valor final de " + cambio + " =>>>" + monedaD);
            }

        }while (opcion != 7) ;
    }
}