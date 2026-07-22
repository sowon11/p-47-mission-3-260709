import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WiseSayingFileManager {

    /*
    public static void saveWiseSayingFile(WiseSaying ws) {
        // 폴더가 없으면 생성
        File directory = new File("db/wiseSaying");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 이름 지정
        String fileName = "db/wiswSaying/%d.json".formatted(ws.getId());
        Path path = Paths.get(fileName);

        // 저장할 형태 지정
        String jsonContent = """
        {
            "id": %d,
            "content": "%s",
            "author": "%s"
        }
        """.formatted(ws.getId(), ws.getContent(), ws.getAuthor());

        // 파일에 쓰기
        try{
            Files.writeString(path, jsonContent);
        }catch(IOException e){
            System.out.println(ws.getId() + "번 명언 파일 저장 중 오류 발생 : " + e.getMessage());
        }
    }

    public static void saveLastIdFile(int lastId){
        // 폴더가 없으면 생성
        File directory = new File("db/wiseSaying");
        if(!directory.exists()){
            directory.mkdirs();
        }

        // 저장할 파일 경로 지정 (db/wiseSaying/lastId.txt)
        Path path = Paths.get("db/wiseSaying/lastId.txt");

        // 파일에 내용 작성
        try{
            Files.writeString(path, String.valueOf(lastId));
        }catch(IOException e){
            System.out.println("마지막 ID 파일 저장 중 오류 발생 : " + e.getMessage());
        }
    }
     */
}
