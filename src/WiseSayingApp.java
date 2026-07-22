import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

// 명언 클래스
class WiseSaying{
    private final int id;
    private String content;
    private String author;

    // 생성자
    public WiseSaying(int id, String content, String author){
        this.id = id;
        this.content = content;
        this.author = author;
    }
    // id 가져오기
    public int getId(){ return this.id; }
    // 명언 가져오기
    public String getContent(){ return this.content; }
    // 작가 가져오기
    public String getAuthor(){ return this.author; }
    // 명언 수정
    public void setContent(String content){ this.content = content; }
    // 작가 수정
    public void setAuthor(String author){ this.author = author; }
}

// 명언 게시판 클래스
public class WiseSayingBoard {
    private final ArrayList<WiseSaying> wsList = new ArrayList<>();   // 명언 객체 리스트
    private final Scanner scanner = new Scanner(System.in);
    private int lastId = 0;

    static void main(){
        WiseSayingBoard wsBoard = new WiseSayingBoard();
        wsBoard.run();
    }

    public void run(){
        System.out.println("== 명언 앱 ==");

        while(true){
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            // cmd 파싱
            Rq rq = new Rq(cmd);
            String actionName = rq.getActionName();

            switch(actionName){
                case "등록":
                    regAction();
                    break;
                case "삭제":
                    delAction(rq);
                    break;
                case "수정":
                    modAction(rq);
                    break;
                case "목록":
                    showWiseSayingList();
                    break;
                case "종료":
                    return;
                default:
                    System.out.println("명령어를 다시 입력해주세요.");
                    break;
            }
            System.out.println("------------------------------");
        }
    }

    // 명언 등록 과정
    void regAction(){
        // 마지막 명언 번호 증가
        lastId++;
        // 명언 입력
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        // 작가 입력
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        // 명언 객체 생성
        WiseSaying ws = regWiseSaying(lastId, content, author);
        // 명언 파일 저장
        WiseSayingFileManager.saveWiseSayingFile(ws);
        // 마지막 명언 번호 파일 저장
        WiseSayingFileManager.saveLastIdFile(lastId);
        // 메시지 출력
        System.out.println(ws.getId() + "번 명언이 등록되었습니다.");
    }
    // 명언 등록
    WiseSaying regWiseSaying(int lastId, String content, String author){
        WiseSaying ws = new WiseSaying(lastId, content, author);
        wsList.add(ws);

        return ws;
    }

    // 명언 리스트에 해당 명언 있는지 확인
    WiseSaying findWiseSaying(int id){
        return wsList.stream()
                .filter((ws) -> ws.getId() == id)
                .findFirst()
                .orElse(null);
        /*
        for(WiseSaying ws : wsList){
            if(ws.getId() == modId){
                return ws;
            }
        }
        return null;
         */
    }

    // 명언 삭제 과정
    void delAction(Rq rq){
        if(wsList.isEmpty()){
            System.out.println("명언 목록이 없습니다.");
            return;
        }

        // 삭제?id=1 에서 id 추출
        int delId = rq.getParamAsInt("id", 0);

        if(delId == 0){
            System.out.println("잘못된 입력입니다.");
            return;
        }

        WiseSaying ws = findWiseSaying(delId);
        if(ws != null){
            delWiseSaying(ws);
            System.out.println(delId + "번 명언이 삭제되었습니다.");
        } else{
            System.out.println(delId + "번 명언은 존재하지 않습니다.");
        }
    }
    // 명언 삭제
    void delWiseSaying(WiseSaying ws){
        wsList.remove(ws);
    }

    // 명언 수정 과정
    void modAction(Rq rq){
        int modId = rq.getParamAsInt("id", 0);

        if(modId == 0){
            System.out.println("잘못된 입력입니다.");
            return;
        }

        WiseSaying ws = findWiseSaying(modId);
        if(ws != null){ // 명언 리스트에 명언 있는 경우
            System.out.println("명언(기존) : " + ws.getContent());
            System.out.print("명언(수정) : ");
            String newContent = scanner.nextLine();

            System.out.println("작가(기존) : " + ws.getAuthor());
            System.out.print("작가(수정) : ");
            String newAuthor = scanner.nextLine();

            modWiseSaying(ws, newContent, newAuthor); // 명언 갱신
            WiseSayingFileManager.saveWiseSayingFile(ws); // 파일 갱신

            System.out.println(modId + "번 명언이 수정되었습니다.");
        } else{
            System.out.println(modId + "번 명언은 존재하지 않습니다.");
        }
    }
    // 명언 수정
    void modWiseSaying(WiseSaying ws, String newContent, String newAuthor){
        ws.setContent(newContent);
        ws.setAuthor(newAuthor);
    }

    // 명언 목록 출력
    void showWiseSayingList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------------");

        if(wsList.isEmpty()){
            System.out.println("명언 목록이 없습니다.");
            return;
        }

        for(int i= wsList.size()-1;i>=0;i--){
            WiseSaying ws = wsList.get(i);
            String listPrint = "%d / %s / %s".formatted(ws.getId(), ws.getAuthor(), ws.getContent());
            System.out.println(listPrint);
        }
    }
}
