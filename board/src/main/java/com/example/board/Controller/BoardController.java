package com.example.board.Controller;

import com.example.board.Entity.Board;
import com.example.board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {

        return "boardForm";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board) {
        boardService.write(board);

        return "";
    }
}
