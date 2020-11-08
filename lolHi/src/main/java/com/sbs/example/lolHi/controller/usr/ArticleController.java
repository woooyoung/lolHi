package com.sbs.example.lolHi.controller.usr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.ReplyService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, @RequestParam Map<String, Object> param) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		int totalCount = articleService.getTotalCount(param);
		int itemsCountInAPage = 10;
		int totalPage = (int) Math.ceil(totalCount / (double) itemsCountInAPage);
		int pageMenuArmSize = 10;
		int page = Util.getAsInt(param.get("page"), 1);
		int pageMenuStart = page - pageMenuArmSize;
		if (pageMenuStart < 1) {
			pageMenuStart = 1;
		}
		int pageMenuEnd = page + pageMenuArmSize;
		if (pageMenuEnd > totalPage) {
			pageMenuEnd = totalPage;
		}

		param.put("itemsCountInAPage", itemsCountInAPage);
		List<Article> articles = articleService.getForPrintArticles(loginedMember, param);

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageMenuArmSize", pageMenuArmSize);
		model.addAttribute("pageMenuStart", pageMenuStart);
		model.addAttribute("pageMenuEnd", pageMenuEnd);
		model.addAttribute("page", page);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id, String listUrl) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Article article = articleService.getForPrintArticleById(loginedMember, id);
		List<Reply> replies = replyService.getForPrintReplies("article", id);

		if (listUrl == null) {
			listUrl = "/usr/article/list";
		}

		model.addAttribute("article", article);
		model.addAttribute("replies", replies);
		model.addAttribute("listUrl", listUrl);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpServletRequest req, int id, Model model) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);

		if ((boolean) article.getExtra().get("actorCanDelete") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.deleteArticleById(id);

		model.addAttribute("msg", String.format("%d번 글을 삭제하였습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article/list"));
		return "common/redirect";
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);

		if ((boolean) article.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	public String doModify(HttpServletRequest req, int id, String title, String body, Model model) {

		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);

		if ((boolean) article.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.modifyArticle(id, title, body);

		model.addAttribute("msg", String.format("%d번 글을 수정하였습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", id));
		return "common/redirect";
	}

	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, Model model) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	public String doWrite(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		param.put("memberId", loginedMemberId);
		int id = articleService.writeArticle(param);

		model.addAttribute("msg", String.format("%d번 글이 생성되었습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", id));
		return "common/redirect";
	}
}
