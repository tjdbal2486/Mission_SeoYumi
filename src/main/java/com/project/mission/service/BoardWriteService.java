package com.project.mission.service;

import com.project.mission.entity.BoardWrite;
import com.project.mission.repository.BoardWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardWriteService {
    private final BoardWriteRepository boardWriteRepository;

    public void create(
        String title,
        String content,
        String password
    ) {
        // 주어진 정보로 새로운 Student 객체를 만든다.
        BoardWrite boardWrite = new BoardWrite();
        boardWrite.setTitle(title);
        boardWrite.setContent(content);
        boardWrite.setPassword(password);
        // repository의 save 메서드를 호출
        boardWriteRepository.save(boardWrite);
    }

    public BoardWrite readBoard(Long num) {
        Optional<BoardWrite> optionalBoard = boardWriteRepository.findById(num);
        //실제 데이터가 있으면 해당 데이터를, 없으면 null을 반환.
        return optionalBoard.orElse(null);
    }

    public List<BoardWrite> readBoardsAll() {
        List<BoardWrite> boards = boardWriteRepository.findAll();
        return boards;
    }

    public void update(
            //수정할 데이터의 pk가 무엇인지
            Long num,
            //수정할 데이터
            String title,
            String content
    ) {
        // 1. 업데이트 할 대상 데이터를 찾고,
        BoardWrite target = readBoard(num);
        // 2. 데이터의 내용을 전달받은 내용으로 갱신하고,
        target.setTitle(title);
        target.setContent(content);
        // 3. repository를 이용해 저장한다.
        boardWriteRepository.save(target);
    }

    public void delete(Long num) {
        boardWriteRepository.deleteById(num);
    }
}
