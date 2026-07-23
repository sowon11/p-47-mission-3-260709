package com.ll.wiseSaying;

import java.util.List;

public class WiseSayingService {

    WiseSayingRepository wsRepository = AppContext.wsRepository;

    // 명언 등록
    public WiseSaying regWiseSaying(String content, String author){
        WiseSaying ws = new WiseSaying(content, author);
        return wsRepository.save(ws);
    }

    // 명언 삭제
    public void delWiseSaying(int id){
        wsRepository.delete(id);
    }

    // 명언 수정
    public void modWiseSaying(WiseSaying ws, String newContent, String newAuthor){
        ws.setContent(newContent);
        ws.setAuthor(newAuthor);

        wsRepository.save(ws);
    }

    // 명언 리스트에 해당 명언 있는지 확인
    public WiseSaying findWiseSaying(int id){
        return wsRepository.findWiseSaying(id);
    }

    // 명언 리스트 비어 있는지 확인
    public boolean isWsListEmpty(){
        return wsRepository.isWsListEmpty();
    }

    public List<WiseSaying> findAllIdDesc(){
        return wsRepository.findAllIdDesc();
    }
}
