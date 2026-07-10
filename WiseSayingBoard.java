import java.util.Scanner;
import java.util.ArrayList;

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
    int lastId = 0;

    static void main(){
        WiseSayingBoard wiseSayingBoard = new WiseSayingBoard();
        wiseSayingBoard.run();
    }

    public void run(){
        System.out.println("== 명언 앱 ==");

        while(true){
            System.out.print("명령) ");
            String order = scanner.nextLine();
            switch(order){
                case "등록":
                    regWiseSaying();
                    break;
                case "삭제":
                    delWiseSaying();
                    break;
                case "수정":
                    modWiseSaying();
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

    // 명언 등록
    void regWiseSaying(){
        lastId++;

        System.out.print("명언 : ");
        String content = scanner.nextLine();    // 명언 입력

        System.out.print("작가 : ");
        String author = scanner.nextLine();     // 작가 입력

        WiseSaying ws = new WiseSaying(lastId, content, author);    // 명언 객체 생성
        wsList.add(ws);     // 명언 객체 리스트에 추가

        System.out.println(ws.getId() + "번 명언이 등록되었습니다.");
    }

    // 명언 삭제
    void delWiseSaying(){
        System.out.print("삭제할 명언 ID : ");
        int delId = scanner.nextInt();  // 삭제할 명언 번호 입력
        scanner.nextLine();

        for(int i=0;i<wsList.size();i++){
            if(wsList.get(i).getId() == delId){
                wsList.remove(i);   // 해당 인덱스의 명언 삭제
                System.out.println(delId + "번 명언이 삭제되었습니다.");
                return;
            }
        }
        System.out.println(delId + "번 명언은 존재하지 않습니다.");
    }

    // 명언 수정
    void modWiseSaying(){
        System.out.print("수정할 명언 ID : ");
        int modId = scanner.nextInt();
        scanner.nextLine();

        for (WiseSaying wiseSaying : wsList) {
            if (wiseSaying.getId() == modId) {
                System.out.println("명언(기존) : " + wiseSaying.getContent());
                System.out.print("명언(수정) : ");
                String newContent = scanner.nextLine();
                wiseSaying.setContent(newContent);

                System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                System.out.print("작가(수정) : ");
                String newAuthor = scanner.nextLine();
                wiseSaying.setAuthor((newAuthor));

                System.out.println(modId + "번 명언이 수정되었습니다.");
                return;
            }
        }
        System.out.println(modId + "번 명언은 존재하지 않습니다.");
    }

    // 명언 목록 출력
    void showWiseSayingList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------------");
        boolean exist = false;
        for (WiseSaying wiseSaying : wsList){
            exist = true;
            System.out.print(wiseSaying.getId() + " / ");
            System.out.print(wiseSaying.getAuthor() + " / ");
            System.out.println(wiseSaying.getContent());
        }
        if(!exist) System.out.println("명언 목록이 없습니다.");
    }
}
