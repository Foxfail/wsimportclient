package localhost._8888;

import com.google.gson.Gson;

import java.util.*;

public class Main {

    @SuppressWarnings("FieldCanBeLocal")
    private static int number_of_requests = 100;

    public static void main(String[] args) throws InterruptedException {
        //"C:\Program Files\Java\jdk1.8.0_201\bin\wsimport" -keep http://localhost:8888/?wsdl
        System.out.println("start");
        AsyncImplService asyncImplService = new AsyncImplService();
        System.out.println("connecting...");
        AsyncImpl asyncImpl = asyncImplService.getAsyncImplPort();
        System.out.println("sending 100 requests");
        ArrayList<Integer> ids = new ArrayList<>();

        // ЗАПРАШИВАЕМ ИНФУ
        for (int i = 0; i < number_of_requests; i++) {

            // Формирую рандомные запросы из сервиса (пока что поддерживается два INN и RND)
            // В первом случае возвращается 770088994455
            // Во втором возвращается рандомное число
            Random random = new Random();
            String request = "";
            int rndInt = random.nextInt(2);
            switch (rndInt) {
                case 0:
                    request = "INN";
                    break;
                case 1:
                    request = "RND";
                    break;
            }


            // СОБСТВЕННО ЗАПРОС
            Integer id = asyncImpl.addDataRequest(request);
            ids.add(id);

//            Thread.sleep(10); // иногда сервис не успивает принять все запросы

            // вывожу красиво на экран
            if (i % 25 == 0) {
                System.out.println();
            }
            System.out.print("\t" + id + " ");
        }
        System.out.println();


        Thread.sleep(1000);

        // ЗАПРАШИВАЕМ РЕЗУЛЬТАТЫ
        System.out.println();
        System.out.println("Получаем результаты");
        //noinspection MismatchedQueryAndUpdateOfCollection
        TreeMap<Integer, String> resultsTreeMap = new TreeMap<>();

        int printedResults = 0; // для красивого вывода результатов

//        Iterator<Integer> iterator = ids.iterator();
//        while (iterator.hasNext()) {
//            // чтобы не заспамить сервис постоянными запросами ожидаем 1 сек
//            Thread.sleep(1000);
//            Integer id = iterator.next();
//            String response = asyncImpl.pollForResult(id);
//
//            if (response != null) { //если в ответе пришел результат по запросу
//                iterator.remove();
//                resultsTreeMap.put(id, response);
//
//                // блок красивого вывода
//                System.out.print(id + "=" + response + ",\t   ");
//                printedResults++;
//                if (printedResults % 10 == 0) {
//                    System.out.println();
//                }
//
//            }
//
//            if (!iterator.hasNext()) {
//                // перезапускаем цикл если уже прошли весь массив, но не все результаты получены
////                System.out.println("Проверяю не осталось ли результатов");
//                iterator = ids.iterator();
//            }
//        }


        //новые результаты
        while (ids.size() > 0) {
            HashMap<String, String> readyHashMap = new HashMap<>();
            String jsonHashMap = asyncImpl.pollForResults(ids);
            Gson gson = new Gson();
            readyHashMap = gson.fromJson(jsonHashMap, readyHashMap.getClass());

            Iterator<String> iterator = readyHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                String id = iterator.next();
                String data = readyHashMap.get(id);
                ids.remove(id);
                resultsTreeMap.put(Integer.valueOf(id), data);

                // блок красивого вывода
                System.out.print(id + "=" + data + ",\t   ");
                printedResults++;
                if (printedResults % 10 == 0) {
                    System.out.println();
                }
            }


        }


// некрасиво выводит
//        System.out.println();
//        System.out.println("РЕЗУЛЬТАТЫ:");
//        System.out.println(resultsTreeMap.toString());
    }


}
