package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션

public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결, 새로운 객체 안 만들어줘 됨
    // Autowired가 없다면 new articleRepository 라는 객체를 만들어줘야함
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString()); // logging

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString()); // logging

        // 2. 리포지토리에게 엔티티를 데이터 베이스 안에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); // logging

        return "";
    }

    // view 화면에 DB의 데이터를 가져오기 위한 메서드
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info(id.toString());

        // 1. repository에서 id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록해야함
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }
}
