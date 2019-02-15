package localhost._8888;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AsyncImplService asyncImplService = new AsyncImplService();
        AsyncImpl asyncImpl = asyncImplService.getAsyncImplPort();
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Integer id = asyncImpl.addDataRequest("INN");
            ids.add(id);
            System.out.println(id);
        }



    }
}
