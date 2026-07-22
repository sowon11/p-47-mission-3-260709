import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    int lastId = 0;
    private final ArrayList<WiseSaying> wsList = new ArrayList<>();

    // 명언 리스트 비어 있는지 확인
    public boolean isWsListEmpty(){
        return wsList.isEmpty();
    }

    // 명언 리스트에 해당 명언 있는지 확인
    public WiseSaying findWiseSaying(int id){
        return wsList.stream()
                .filter((ws) -> ws.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 명언 저장
    public WiseSaying save(WiseSaying ws){
        if(ws.isNew()){ // 명언 번호가 있는지 검사(이미 있는 명언인지)
            lastId++;
            ws.setId(lastId); // 새로 생성되는 명언에 다음 번호 설정
            ws.setCreateDate(LocalDateTime.now()); // 명언 생성일
            wsList.add(ws); // 리스트에 명언 추가
        }
        ws.setModifyDate(LocalDateTime.now()); // 수정일 갱신
        return ws;
    }

    // 명언 삭제
    public void delete(int id){
        wsList.removeIf(ws -> ws.getId() == id); // id를 가진 명언 있으면 삭제
    }

    public List<WiseSaying> findAllIdDesc(){
        return wsList.reversed();
    }
}
