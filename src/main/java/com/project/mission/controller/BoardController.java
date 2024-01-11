package com.project.mission.controller;

import com.project.mission.dto.BoardWriteDTO;
import com.project.mission.entity.BoardWrite;
import com.project.mission.repository.BoardWriteRepository;
import com.project.mission.service.BoardWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
//@RequestMapping("boards")
public class BoardController {
    private final BoardWriteService boardWriteService;

    @GetMapping("boards/article")
    public String writeArticle() {

        return "board/boardWriteForm";
    }

    @PostMapping("boards/article")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password
    ) {
        boardWriteService.create(title, content, password);
        return "redirect:/boards";
    }

    @GetMapping("article/{num}")
    public String readOne(
        @PathVariable("num") Long num,
        Model model
    ) {
        model.addAttribute("board", boardWriteService.readBoard(num));
        return "board/boardRead";
    }

    @GetMapping("boards")
    public String readAll(Model model) {
        model.addAttribute("boards", boardWriteService.readBoardsAll());
        return "board/boardList";
    }


    @GetMapping("article/{num}/update")
    public String updateView(
            @PathVariable("num") Long num,
            Model model
    ) {
        model.addAttribute("board", boardWriteService.readBoard(num));
        return "board/boardUpdateForm";
    }

    @PostMapping("article/{num}/update")
    public String update(
            @PathVariable("num") Long num,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        boardWriteService.update(num, title, content);
        return "redirect:/boards";
//        return String.format("redirect:/article/%d", num);
    }

    @PostMapping("/article/{num}/delete")
    public String delete(
            @PathVariable("num") Long num
    ) {
        boardWriteService.delete(num);
        return "redirect:/boards";
    }



//    /*
//    게시글 작성하기 Form 페이지 + 게시글 수정 Form 페이지
//     */
//    @RequestMapping(value = "/boards/article", method = RequestMethod.GET)
//    public String boardWriteForm(
//            @RequestParam(value = "num", required = false) Integer num,
//            Model model
//    ) {
//        if (num != null) {
//            System.out.println(num);
//
//            // 기존 게시글 수정
//            BoardWrite boardWrite= boardWriteRepository.findById(num).orElse(null);
//            model.addAttribute("boardWriteDTO", boardWrite);
//
//        } else {
//            // 신규 게시글 작성
//            model.addAttribute("boardWriteDTO", new BoardWriteDTO());
//
//        }
//
//
//        return "board/boardWriteForm";
//    }

//    /*
//    게시글 작성 Ok
//     */
//    @RequestMapping(value = "/boards/article", method = RequestMethod.POST)
//    public String boardWriteOk(
//            BoardWriteDTO boardWriteDTO,
//            Model model
//    ) {
//        // 등록 처리
//        System.out.println(boardWriteDTO.toString());
//
//        // 1. DTO -> Entity
//        BoardWrite boardWrite = boardWriteDTO.toEntity();
//        System.out.println(boardWrite.toString());
//
//        // 2. Repository로 Entity를 DB에 저장
//        boardWriteRepository.save(boardWrite);
//
//        return "redirect:/";
//    }
//
//    /*
//    게시판 리스트
//     */
//    @RequestMapping(value = "/boards", method = RequestMethod.GET)
//    public String boardList(
//            Model model
//    ) {
//        List<BoardWrite> boards = boardWriteRepository.findAll();
//
//        // 객체 리스트 전달 - 모델에 담아서 리스트 뷰페이지로 전달
//        model.addAttribute("boards", boards);
//
//        return "board/boardList";
//    }
//
//    /*
//    게시글 조회
//     */
//    @RequestMapping(value = "/article/{num}", method = RequestMethod.GET)
//    public String articleRead(
//            @PathVariable("num")
//            String id,
////            @RequestParam(value = "num", required = false) Integer num,
//            Model model
//    ) {
////        BoardWrite boardWrite = boardWriteRepository.findById(id).orElse(null);
////        model.addAttribute("boardWrite", boardWrite);
//
//
//        return "board/bdd";
//    }



}
