package com.busanit501.teamproject2.hjt.repository.search;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.dto.BoardListAllDTO;
import com.busanit501.teamproject2.hjt.dto.HjtBoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HjtBoardSearch {
    Page<HjtTripBoard> search(Pageable pageable);
    Page<HjtTripBoard> searchAll(String[] types, String keyword ,Pageable pageable);
    Page<HjtBoardListReplyCountDTO> searchWithReplyCount(
            String[] types, String keyword ,Pageable pageable
    );

    Page<BoardListAllDTO> searchWithAll(
            String[] types, String keyword ,Pageable pageable
    );

}
