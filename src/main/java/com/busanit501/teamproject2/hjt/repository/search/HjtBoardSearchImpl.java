package com.busanit501.teamproject2.hjt.repository.search;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.domain.QHjtTripBoard;
import com.busanit501.teamproject2.hjt.domain.QHjtTripReply;
import com.busanit501.teamproject2.hjt.dto.BoardImageDTO;
import com.busanit501.teamproject2.hjt.dto.BoardListAllDTO;
import com.busanit501.teamproject2.hjt.dto.HjtBoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class HjtBoardSearchImpl extends QuerydslRepositorySupport implements HjtBoardSearch {

    public HjtBoardSearchImpl() {
        super(HjtTripBoard.class);
    }

    @Override
    public Page<HjtTripBoard> search(Pageable pageable) {
        QHjtTripBoard hjtTripBoard = QHjtTripBoard.hjtTripBoard;
        JPQLQuery<HjtTripBoard> query = from(hjtTripBoard);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(hjtTripBoard.tripTitle.contains("1"));
        booleanBuilder.or(hjtTripBoard.tripContent.contains("1"));
        query.where(booleanBuilder);
        query.where(hjtTripBoard.tripBno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<HjtTripBoard> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<HjtTripBoard> searchAll(String[] types, String keyword, Pageable pageable) {
        QHjtTripBoard hjtTripBoard = QHjtTripBoard.hjtTripBoard;
        JPQLQuery<HjtTripBoard> query = from(hjtTripBoard);
        if ((types != null && types.length > 0) && keyword != null) {
            log.info("조건절 실행여부 확인 1 ");
            BooleanBuilder   booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        log.info("조건절 실행여부 확인 2 :  title");
                        booleanBuilder.or(hjtTripBoard.tripTitle.contains(keyword));
                    case "w":
                        log.info("조건절 실행여부 확인 2 :  writer");
                        booleanBuilder.or(hjtTripBoard.tripWriter.contains(keyword));
                    case "c":
                        log.info("조건절 실행여부 확인 2 :  content");
                        booleanBuilder.or(hjtTripBoard.tripContent.contains(keyword));
                } //switch
            } // end for
            query.where(booleanBuilder);
        } // end if


        query.where(hjtTripBoard.tripBno.gt(0L));


        this.getQuerydsl().applyPagination(pageable, query);


        List<HjtTripBoard> list = query.fetch();

        long count = query.fetchCount();

        Page<HjtTripBoard> result = new PageImpl<>(list, pageable,count);

        return result;
    }

    @Override
    public Page<HjtBoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QHjtTripBoard hjtTripBoard = QHjtTripBoard.hjtTripBoard;
        QHjtTripReply hjtTripReply = QHjtTripReply.hjtTripReply;
        JPQLQuery<HjtTripBoard> query = from(hjtTripBoard);
        query.leftJoin(hjtTripReply).on(hjtTripReply.hjtTripBoard.eq(hjtTripBoard));
        query.groupBy(hjtTripBoard);
        if ((types != null && types.length > 0) && keyword != null) {
            log.info("조건절 실행여부 확인 1 ");
            BooleanBuilder   booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        log.info("조건절 실행여부 확인 2 :  title");
                        booleanBuilder.or(hjtTripBoard.tripTitle.contains(keyword));
                        break;
                    case "w":
                        log.info("조건절 실행여부 확인 2 :  writer");
                        booleanBuilder.or(hjtTripBoard.tripWriter.contains(keyword));
                        break;
                    case "c":
                        log.info("조건절 실행여부 확인 2 :  content");
                        booleanBuilder.or(hjtTripBoard.tripContent.contains(keyword));
                        break;
                } //switch
            } // end for
            query.where(booleanBuilder);
        } // end if

        query.where(hjtTripBoard.tripBno.gt(0L));

        JPQLQuery<HjtBoardListReplyCountDTO> dtoQuery = query.select(
                Projections.bean(HjtBoardListReplyCountDTO.class,
                        hjtTripBoard.tripBno,
                        hjtTripBoard.tripTitle,
                        hjtTripBoard.tripContent,
                        hjtTripBoard.regDate,
                        hjtTripReply.count().as("hjtReplyCount"))
        );
        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        // 위의 조건으로 디비에서 정보 가져오기.
        List<HjtBoardListReplyCountDTO> dtoList = dtoQuery.fetch();
        // 조회된 데이터의 갯수
        long count = dtoQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
        QHjtTripBoard hjtTripBoard = QHjtTripBoard.hjtTripBoard;
        QHjtTripReply hjtTripReply = QHjtTripReply.hjtTripReply;
        JPQLQuery<HjtTripBoard> query = from(hjtTripBoard);
        query.leftJoin(hjtTripReply).on(hjtTripReply.hjtTripBoard.eq(hjtTripBoard));
        if ((types != null && types.length > 0) && keyword != null) {
            log.info("조건절 실행여부 확인 1 ");
            BooleanBuilder   booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        log.info("조건절 실행여부 확인 2 :  title");
                        booleanBuilder.or(hjtTripBoard.tripTitle.contains(keyword));
                        break;
                    case "w":
                        log.info("조건절 실행여부 확인 2 :  writer");
                        booleanBuilder.or(hjtTripBoard.tripWriter.contains(keyword));
                        break;
                    case "c":
                        log.info("조건절 실행여부 확인 2 :  content");
                        booleanBuilder.or(hjtTripBoard.tripContent.contains(keyword));
                        break;
                } //switch
            } // end for
            // BooleanBuilder를 적용하기.
            query.where(booleanBuilder);
        } // end if

        query.where(hjtTripBoard.tripBno.gt(0L));
        query.groupBy(hjtTripBoard);
        getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Tuple> tupleListQuery = query.select(hjtTripBoard,hjtTripReply.countDistinct());
        List<Tuple> tupleList = tupleListQuery.fetch();
        List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple -> {
            HjtTripBoard hjtTripBoard1 = tuple.get(hjtTripBoard);
            long replyCount = tuple.get(1,Long.class);

            BoardListAllDTO boardListAllDTO = BoardListAllDTO.builder()
                    .tripBno(hjtTripBoard1.getTripBno())
                    .tripTitle(hjtTripBoard1.getTripTitle())
                    .tripWriter(hjtTripBoard1.getTripWriter())
                    .regDate(hjtTripBoard1.getRegDate())
                    .tripReplyCount(replyCount)
                    .build();

            List<BoardImageDTO> imageDTOS = hjtTripBoard1.getImageSet().stream().sorted().map(
                    boardImage -> BoardImageDTO.builder()
                            .uuid(boardImage.getUuid())
                            .fileName(boardImage.getFileName())
                            .ord(boardImage.getOrd())
                            .build()
            ).collect(Collectors.toList());
            boardListAllDTO.setBoardImages(imageDTOS);


            return boardListAllDTO;

        }).collect(Collectors.toList());


        // entity -> dto 변환 했고,
        long totalCount = query.fetchCount();


        return new PageImpl<>(dtoList,pageable,totalCount);
    }

}
