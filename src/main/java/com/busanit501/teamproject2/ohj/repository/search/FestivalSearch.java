package com.busanit501.teamproject2.ohj.repository.search;

import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.dto.FestivalListAllDTO;
import com.busanit501.teamproject2.ohj.domain.Festival;
import com.busanit501.teamproject2.ohj.dto.FestivalListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FestivalSearch {
  Page<Festival> search(Pageable pageable);

  Page<Festival> searchAll(String[] types, String keyword ,Pageable pageable);



  Page<FestivalListAllDTO> searchWithAll(
          String[] types, String keyword ,Pageable pageable
  );

}













