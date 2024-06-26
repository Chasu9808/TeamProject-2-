package com.busanit501.teamproject2.hjt.service;

import com.busanit501.teamproject2.hjt.domain.HjtTripBoard;
import com.busanit501.teamproject2.hjt.domain.HjtTripReply;
import com.busanit501.teamproject2.hjt.dto.HjtPageRequestDTO;
import com.busanit501.teamproject2.hjt.dto.HjtPageResponseDTO;
import com.busanit501.teamproject2.hjt.dto.HjtReplyDTO;
import com.busanit501.teamproject2.hjt.repository.HjtBoardRepository;
import com.busanit501.teamproject2.hjt.repository.HjtReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class HjtReplyServiceImpl implements HjtReplyService {
    private final HjtReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final HjtBoardRepository hjtBoardRepository;
    private final HjtReplyRepository hjtReplyRepository;

    @Override
    public Long register(HjtReplyDTO hjtReplyDTO) {
        HjtTripReply hjtTripReply = modelMapper.map(hjtReplyDTO, HjtTripReply.class);
        Optional<HjtTripBoard> result = hjtBoardRepository.findById(hjtReplyDTO.getTripBno());
        HjtTripBoard hjtTripBoard = result.orElseThrow();
        hjtTripReply.setHjtTripBoard(hjtTripBoard);
        Long tripRno = replyRepository.save(hjtTripReply).getTripRno();
        return tripRno;
    }

    @Override
    public HjtReplyDTO read(Long tripRno) {
        Optional<HjtTripReply> replyOptional = hjtReplyRepository.findById(tripRno);
        HjtTripReply hjtTripReply = replyOptional.orElseThrow();
        HjtReplyDTO hjtReplyDTO = modelMapper.map(hjtTripReply, HjtReplyDTO.class);
        return hjtReplyDTO;
    }

    @Override
    public void update(HjtReplyDTO hjtReplyDTO) {
        Optional<HjtTripReply> replyOptional = hjtReplyRepository.findById(hjtReplyDTO.getTripRno());
        HjtTripReply hjtTripReply = replyOptional.orElseThrow();
        hjtTripReply.changText(hjtReplyDTO.getReplyText());
        hjtReplyRepository.save(hjtTripReply);
    }

    @Override
    public void delete(Long tripRno) {
        Optional<HjtTripReply> replyOptional = replyRepository.findById(tripRno);
        HjtTripReply reply = replyOptional.orElseThrow();
        hjtReplyRepository.deleteById(tripRno);
    }

    @Override
    public HjtPageResponseDTO<HjtReplyDTO> getListOfHjtTripBoard(Long tripBno, HjtPageRequestDTO hjtPageRequestDTO) {
        Pageable pageable = PageRequest.of(hjtPageRequestDTO.getPage()-1 <=0 ? 0 :hjtPageRequestDTO.getPage()-1,
                hjtPageRequestDTO.getSize(),Sort.by("tripRno").ascending());
        Page<HjtTripReply> result = replyRepository.listOfBoard(tripBno,pageable);

        List<HjtReplyDTO> dtoList = result.getContent().stream()
                .map(reply ->{
                    HjtReplyDTO replyDTO = modelMapper.map(reply,HjtReplyDTO.class);
                    replyDTO.setTripBno(reply.getHjtTripBoard().getTripBno());
                    return replyDTO;
                })
                .collect(Collectors.toList());

        HjtPageResponseDTO<HjtReplyDTO> pageResponseDTO = HjtPageResponseDTO.<HjtReplyDTO>withAll()
                .hjtPageRequestDTO(hjtPageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

        return pageResponseDTO;
    }
}
