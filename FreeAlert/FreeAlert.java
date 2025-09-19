package PruebasTest.practicar.PruebasConAPI.FreeAlert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class FreeAlert {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String urlTodo = "https://www.gamerpower.com/api/giveaways";

        JSONArray jsonTodos = requestHTTP(urlTodo);
        int cantidadResultados = jsonTodos.length();
        while (true) {
            int cont = 1;
            menuApp();
            int opcM = sc.nextInt();
            if(opcM == 0){
                System.out.println("Finalizar");
                break;
            }
            if(opcM == 1){
                for (int i = 0; i < cantidadResultados; i++) {
                    JSONObject juego = jsonTodos.getJSONObject(i);
                    System.out.println((cont) + ". " + juego.getString("title"));
                    cont++;
                }
            }
            if (opcM == 2){
                menuPlat();
                String plat;
                int opcPlat = sc.nextInt();
                switch (opcPlat){
                    case 1 -> {
                        plat = "PC";
                    }
                    case 2 -> {
                        plat = "Playstation";
                    }case 3 -> {
                        plat = "Xbox";
                    }case 4 -> {
                        plat = "Android";
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + opcPlat);
                }
                ArrayList<String> juegos = new ArrayList<>();

                for (int i = 0; i < cantidadResultados; i++) {
                    JSONObject juego = jsonTodos.getJSONObject(i);
                    String plataformas = juego.getString("platforms").toLowerCase();
                    if (plataformas.contains(plat.toLowerCase())) {
                        System.out.println((cont) + ". " + juego.getString("title"));
                        cont++;
                        juegos.add(String.valueOf(juego));
                    }
                }

            }
            if(opcM == 3){
                System.out.println("");
            }
            if(opcM == 4){
                System.out.println("");

            }
        }


    }
    public static JSONArray requestHTTP(String url) throws IOException, InterruptedException {
        HttpClient httpR = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = httpR.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }
    public static void menuApp() {
        System.out.println("══════════════════════════════════════════════");
        System.out.println("           💲🎮   FreeGames App   🎮💲");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("📋 Menú de opciones:");
        System.out.println("1.  Ver todos los juegos gratuitos disponibles");
        System.out.println("2.  Filtrar juegos por plataforma ");
        System.out.println("0.  Salir");
        System.out.print("👉 Escribe el número de tu elección: ");
    }

    public static void menuPlat() {
        System.out.println("📋 Menú de opciones:");
        System.out.println("1.  PC");
        System.out.println("2.  Playstation");
        System.out.println("3.  Xbox");
        System.out.println("4.  Android");
        System.out.print("👉 Escribe el número de tu elección: ");
    }


}


