package com.busanit501.teamproject2.service;


import com.busanit501.teamproject2.hjt.dto.*;
import com.busanit501.teamproject2.hjt.repository.HjtBoardRepository;
import com.busanit501.teamproject2.hjt.service.HjtBoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
  @Autowired
  HjtBoardService hjtBoardService;
  @Autowired
  HjtBoardRepository hjtBoardRepository;

  @Test
  @Transactional
  public void testInsert() {
    // 화면에서 넘어온 더미 데이터 만들기. DTO 타입.
    HjtBoardDTO hjtBoardDTO = HjtBoardDTO.builder()
        .tripTitle("내일 모하니?")
        .tripContent("부모님 인사하기")
        .tripWriter("이상용")
        .build();

    Long tripBno = hjtBoardService.register(hjtBoardDTO);
    log.info("추가 후에 반환 게시글 번호 bno : " + tripBno);

  }

  @Test
  @Transactional
  public void testRead() {

    HjtBoardDTO hjtBoardDTO = hjtBoardService.read(1L);
    log.info("하나 조회 boardDTO : " + hjtBoardDTO);

  }

  @Test
  @Commit
  @Transactional
  public void testUpdate() {
    // 변경시, 변경할 더미 데이터, 임시, 601L
// 화면에서 넘어온 더미 데이터 만들기. DTO 타입.
    HjtBoardDTO hjtBoardDTO = HjtBoardDTO.builder()
        .tripBno(2L)
        .tripTitle("내일 모하니?수정버전")
        .tripContent("부모님 인사하기 : 수정버전")
        .tripWriter("이상용 : 수정버전")
        .build();

    //디비에서 조회하기.
    hjtBoardService.update(hjtBoardDTO);
    log.info("hjtBoardDTO 값을 확인해요~!" + hjtBoardDTO);

  }


  @Test
  @Commit
  public void testDelete() {
        //디비에서 조회하기.
    // 좋은 질문,
    // 서버에서 화면, 데이터 같이 처리, Controller,
    // 질문, 게시글 삭제 할려면, 화면에 보일까요? 안보일까요?

    // RestController, 데이터만 처리.
    // 화면이 없어서, method delete 형식으로 명령이 오면,
    hjtBoardService.delete(2L);


  }

  @Test
  public void testList() {
    // 화면에서 전달할 내용을 담은 PageRequestDTO 더미가 필요.
    HjtPageRequestDTO hjtPageRequestDTO = HjtPageRequestDTO.builder()
            .type("tcw")
            .keyword("오늘")
            .page(1)
            .size(10)
            .build();

  HjtPageResponseDTO<HjtBoardDTO> responseDTO = hjtBoardService.list(hjtPageRequestDTO);
    log.info("list 테스트 responseDTO : " + responseDTO);

  }
//
//
  @Test
  public void testListWithCount() {
    // 화면에서 전달할 내용을 담은 PageRequestDTO 더미가 필요.
    HjtPageRequestDTO hjtPageRequestDTO = HjtPageRequestDTO.builder()
            .type("tcw")
            .keyword("오늘")
            .page(1)
            .size(10)
            .build();

    HjtPageResponseDTO<HjtBoardListReplyCountDTO> hjtResponseDTO = hjtBoardService.listWithReplyCount(hjtPageRequestDTO);
    log.info("list 테스트 responseDTO : " + hjtResponseDTO);

  }

  @Test
  public void testRegisterWithImages() {
    HjtBoardDTO hjtBoardDTO = HjtBoardDTO.builder()
            .tripTitle("샘플 제목입니다.")
            .tripContent("샘플 내용입니다.")
            .tripWriter("이상용")
            .build();

    // 더미 이미지들
    hjtBoardDTO.setFileNames(
            Arrays.asList(
                  //파일명,
                    UUID.randomUUID()+"_testImage.png",
                    UUID.randomUUID()+"_testImage2.png",
                    UUID.randomUUID()+"_testImage3.png"
            )
    ); // 더미 이미지 파일명 추가

    Long tripBno = hjtBoardService.register(hjtBoardDTO);
    log.info("boardService, register 확인 tripBno : " + tripBno);
  }

  @Test
  public void testReadWithImage() {

    HjtBoardDTO hjtBoardDTO = hjtBoardService.read(104L);
    log.info("testReadWithImage, 하나 조회 boardDTO : " + hjtBoardDTO);
    for(String fileImage : hjtBoardDTO.getFileNames()){
      log.info("각 이미지 파일명만 조회 : " + fileImage);
    }

  }

  @Test
  public void testUpdateWithImages() {
    // 변경시, 변경할 더미 데이터, 임시, 601L
// 화면에서 넘어온 더미 데이터 만들기. DTO 타입.
    HjtBoardDTO hjtBoardDTO = HjtBoardDTO.builder()
            .tripBno(104L)
            .tripTitle("내일 모하니?수정버전")
            .tripContent("부모님 인사하기 : 수정버전")
            .build();

    // 더미 데이터에 첨부 이미지 파일 추가.
    hjtBoardDTO.setFileNames(
            Arrays.asList(
                    UUID.randomUUID()+"_sampleImage.png",
                    UUID.randomUUID()+"_sampleImage2.png"
            )
    );

    //디비에서 조회하기.
    hjtBoardService.update(hjtBoardDTO);


  }
//
//  @Test
//  public void deleteAll() {
//    boardService.deleteAll(103L);
//
//  }

  @Test
  @Transactional
  public void testListAll() {
    // 화면에서 전달할 내용을 담은 PageRequestDTO 더미가 필요.
    HjtPageRequestDTO hjtPageRequestDTO = HjtPageRequestDTO.builder()
            .type("tcw")
            .keyword("1")
            .page(1)
            .size(10)
            .build();

    HjtPageResponseDTO<BoardListAllDTO> responseDTO =
            hjtBoardService.listWithAll(hjtPageRequestDTO);

    // 이미지들 만 뽑아 보기..
    List<BoardListAllDTO> dtoList = responseDTO.getDtoList();
    dtoList.forEach(boardListAllDTO -> {
      log.info("제목 : " + boardListAllDTO.getTripTitle());
      if(boardListAllDTO.getBoardImages() !=null ) {
        for (BoardImageDTO boardImageDTO : boardListAllDTO.getBoardImages()) {
          log.info("이미지들 목록중에서 하나씩 꺼내서 파일명 조회 : " + boardImageDTO);
        }
      }
    });

    log.info("testListAll 테스트 responseDTO : " + responseDTO);

  }

}







