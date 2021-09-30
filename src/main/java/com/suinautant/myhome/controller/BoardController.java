package com.suinautant.myhome.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suinautant.myhome.model.Board;
import com.suinautant.myhome.repository.BoardRepository;
import com.suinautant.myhome.validator.BoardValidator;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardValidator boardValidator;

	// Pageable 설정시 req 매개변수 page와 size 사용 가능
	@GetMapping("/list")
	public String list(Model model, @PageableDefault(size = 2) Pageable pageable, 
			@RequestParam(required = false, defaultValue = "") String searchText) {
//		Page<Board> boards = boardRepository.findAll(pageable);
		Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
		// boards.getPageable().getPageNumber() : 현재 페이지 번호
		// boards.getTotalPages()  : 전체 페이지 수
		// ?? 4를 빼고 더하는 과정이 필요한가 ??
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);
		return "board/list";
	}

	@GetMapping("/form")
	public String form(Model model, @RequestParam(required = false) Long id) {
		if (id == null) {
			model.addAttribute("board", new Board());
		} else {
			Board board = boardRepository.findById(id).orElse(null);
			model.addAttribute("board", board);
		}
		return "board/form";
	}

	@PostMapping("/form")
	public String formSubmit(@Valid Board board, BindingResult bindingResult) {
		boardValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			return "board/form";
		}
		boardRepository.save(board);
		return "redirect:/board/list";
	}
}
