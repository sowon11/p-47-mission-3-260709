import java.util.Scanner;

public class AppContext {
    public static Scanner scanner = new Scanner(System.in);
    public static WiseSayingRepository wsRepository = new WiseSayingRepository();
    public static WiseSayingService wsService = new WiseSayingService();
    public static WiseSayingController wsController = new WiseSayingController();
    public static SystemController systemController = new SystemController();
}
