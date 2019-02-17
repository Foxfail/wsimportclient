package localhost._8888;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    private static int number_of_requests = 100;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        AsyncImplService asyncImplService = new AsyncImplService();
        System.out.println("connecting...");
        AsyncImpl asyncImpl = asyncImplService.getAsyncImplPort();
        System.out.println("sending 100 requests");
        ArrayList<Integer> ids = new ArrayList<>();

        // ЗАПРАШИВАЕМ ИНФУ
        for (int i = 0; i < number_of_requests; i++) {
            Integer id = asyncImpl.addDataRequest("INN");
            ids.add(id);

            // вывожу красиво на экран
            if (i % 20 == 0) {
                System.out.println();
            }
            System.out.print(" " + id + " ");
        }
        System.out.println();


        Thread.sleep(1000);

        // ЗАПРАШИВАЕМ РЕЗУЛЬТАТЫ
        ArrayList<String> resultsArrayList = new ArrayList<>();

        Iterator<Integer> iterator = ids.iterator();
        while (iterator.hasNext()) {

            String response = asyncImpl.pollForResult(iterator.next());
            if (response != null) {
                iterator.remove();
                resultsArrayList.add(response);

            }
            resultsArrayList.trimToSize();
            if (!iterator.hasNext()){
                iterator = ids.iterator();
            }
        }


        System.out.println(resultsArrayList.toString());
    }
}
