import com.ll.wiseSaying.AppContext;
import com.ll.wiseSaying.WiseSayingController;
import com.ll.wiseSaying.WiseSayingRepository;
import com.ll.wiseSaying.WiseSayingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @BeforeEach
    void setUp(){ // 테스트마다 객체 초기화
        AppContext.wsRepository = new WiseSayingRepository();
        AppContext.wsService = new WiseSayingService();
        AppContext.wsController = new WiseSayingController();
    }

    @Test
    @DisplayName("등록")
    void t1(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 : ")
                .contains("작가 : ");
    }

    @Test
    @DisplayName("등록 후 명언 번호 출력")
    void t2(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);
        assertThat(out).contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록할 때마다 명언 번호 증가")
    void t3(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                종료
                """);
        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t4(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                종료
                """);
        assertThat(out)
                .contains("번호 / 작가 / 명언 / 작성일 / 마지막 수정일")
                .contains("----------------------------------------------------------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("명언 삭제")
    void t5(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                종료
                """);
        assertThat(out).contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 명언 삭제 예외 처리")
    void t6(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                삭제?id=1
                종료
                """);
        assertThat(out)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("없는 명언 수정 예외 처리")
    void t7(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                삭제?id=1
                수정?id=3
                종료
                """);
        assertThat(out)
                .contains("1번 명언은 존재하지 않습니다.")
                .contains("3번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("명언 수정")
    void t8(){
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                삭제?id=1
                수정?id=3
                수정?id=2
                현재와 자신을 사랑하라.
                홍길동
                목록
                종료
                """);
        assertThat(out)
                .contains("1번 명언은 존재하지 않습니다.")
                .contains("3번 명언은 존재하지 않습니다.")
                .contains("명언(기존) : 과거에 집착하지 마라.")
                .contains("작가(기존) : 작자미상")
                .contains("번호 / 작가 / 명언 / 작성일 / 마지막 수정일")
                .contains("2 / 홍길동 / 현재와 자신을 사랑하라.");
    }
}