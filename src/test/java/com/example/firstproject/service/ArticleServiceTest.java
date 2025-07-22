package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다
class ArticleServiceTest {
    @Autowired ArticleService articleService;
    @Test
    void index() {
        // 예상 시나리오
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List <Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제 결과
        List<Article> articles = articleService.index();
        // 예상과 실제를 비교하여 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공__존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패__존재하지않는__아이디__입력() {
        Long id = -1L;

        // ArticleService.show(id)를 실행했을 때 IllegalArgumentException이 발생할 것임
        assertThrows(IllegalArgumentException.class, () -> articleService.show(id));
    }

    @Transactional // index()의 테스트를 해결하기 위해 성공 후 roll back 시켜줌
    // 테스트를 독립된 시공간(격리된 환경)에서 실행시키는 것 과 같아지기 때문에 index() 메서드에 영향을 주지 않게됨
    @Test
    void create__성공__title__content만__있는__dto입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
        // 그런데 위의 create__성공이 성공하면서 전체를 돌렸을 때, index()는 무결성이 깨짐
    }

    @Test
    @Transactional
    void create__실패__id가__포함된__dto__입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article); // null은 toString() 안 먹음
    }
}