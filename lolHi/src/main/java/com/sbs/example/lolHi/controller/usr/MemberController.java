package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.service.MemberService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/findLoginId")
	public String showfindLoginId() {
		return "usr/member/findLoginId";
	}

	@RequestMapping("/usr/member/doFindLoginId")
	public String doFindLoginId(Model model, String name, String email) {

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			model.addAttribute("msg", String.format("해당 회원은 존재하지 않습니다."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		model.addAttribute("msg", String.format("가입 날짜 : %s,로그인 아이디 : %s", member.getRegDate(), member.getLoginId()));
		model.addAttribute("historyBack", true);
		return "common/redirect";
	}

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	public String doLogin(String loginId, String loginPw, HttpSession session, Model model) {
		if (loginId.length() == 0) {
			model.addAttribute("msg", String.format("로그인 아이디를 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			model.addAttribute("msg", String.format("%s(은)는 존재하지 않는 로그인 아이디 입니다.", loginId));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("msg", String.format("비밀번호를 정확히 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		model.addAttribute("msg", String.format("%s님 환영합니다.", member.getName()));
		model.addAttribute("replaceUri", String.format("/usr/article-free/list"));
		return "common/redirect";
	}

	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");

		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}

	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		String loginId = Util.getAsStr(param.get("loginId"), "");
		String name = Util.getAsStr(param.get("name"), "");
		String email = Util.getAsStr(param.get("email"), "");

		if (loginId.length() == 0) {
			model.addAttribute("msg", String.format("로그인 아이디를 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		boolean isJoinAvailableLoginId = memberService.isJoinAvailableLoginId(loginId);

		if (isJoinAvailableLoginId == false) {
			model.addAttribute("msg", String.format("%s(은)는 이미 사용중인 아이디 입니다.", loginId));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		boolean isJoinAvailableNameAndEmail = memberService.isJoinAvailableNameAndEmail(name, email);

		if (isJoinAvailableNameAndEmail == false) {
			model.addAttribute("msg", String.format("이미 가입된 회원의 정보입니다."));
			model.addAttribute("replaceUri", "/usr/member/findLoginId");
			return "common/redirect";
		}

		int id = memberService.join(param);

		model.addAttribute("msg", String.format("가입되었습니다."));
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}

	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "usr/member/modify";
	}

	@RequestMapping("/usr/member/doModify")
	public String doModify(Model model, HttpServletRequest req, @RequestParam Map<String, Object> param) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);

		// 해킹방지
		param.remove("loginId");
		param.remove("loginPw");

		memberService.modify(param);

		model.addAttribute("msg", String.format("수정되었습니다."));
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}
}