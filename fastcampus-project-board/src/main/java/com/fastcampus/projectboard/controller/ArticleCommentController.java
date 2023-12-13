package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest){
        //TODO : 인증 정보를 넣어줘야 함.
        UserAccountDto userAccountDto = UserAccountDto.of("koy1208", "pw", "koy1208@gmail.com", null, null);

        articleCommentService.saveArticleComment(articleCommentRequest.toDto(userAccountDto));
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId){
        articleCommentService.deleteArticleComment(commentId);
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{commentId}/update")
    public String updateArticleComment(@PathVariable Long commentId, ArticleCommentRequest articleCommentRequest){
        articleCommentService.updateArticleComment(articleCommentRequest.toDto(
                UserAccountDto.of("koy1208", "pw", "koy1208@gmail.com","koy1208", "momo")
        ));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }
}
