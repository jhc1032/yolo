package com.yolo.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yolo.model.biz.HomeworkBoardService;
import com.yolo.model.biz.NoticeBoardService;
import com.yolo.model.domain.HomeworkBoard;
import com.yolo.model.domain.HomeworkBoardReply;
import com.yolo.model.domain.NoticeBoard;
import com.yolo.model.domain.PageBean;
import com.yolo.model.domain.UpdateException;
import com.yolo.util.LoginCheck;

@Controller
public class HomeworkBoardController {

	/**
	 * error 처리
	 * 
	 * @ExceptionHandler Controller에서 오류가 발생하면 처리하는 기능
	 */
	@ExceptionHandler
	public ModelAndView handler(Exception e) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("msg", e.getMessage());
		model.addObject("content", "ErrorHandler.jsp");
		return model;
	}

	@Autowired
	private HomeworkBoardService boardService;

	@RequestMapping(value = "listHomeworkBoard.do", method = RequestMethod.GET)
	public String listBoard(PageBean bean, Model model, HttpSession session) {
		
		if (LoginCheck.check(model, session, "insertBoardForm.do")) {
			List<HomeworkBoard> list = boardService.searchAll(bean);
			for(HomeworkBoard board : list) {
				board.setReplyCnt(boardService.getReplyCount(board.getNo()));
			}
			model.addAttribute("list", list);
			model.addAttribute("content", "homework/listBoard.jsp");
		} else {
			model.addAttribute("content", "member/login.jsp");
		}
		
		return "index";
	}
	
	@RequestMapping(value="homeworkAuth.do", method=RequestMethod.GET)
	public String authForm(Model model, int no, HttpSession session) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>homeworkAuth.do");
		if(session.getAttribute("role") != null && 
				!session.getAttribute("role").equals("인턴")) {
			return "redirect: searchHomeworkBoard.do?no="+no;
		}
		
		model.addAttribute("no", no);
		model.addAttribute("content", "homework/homeworkAuth.jsp");
		return "index";
	}
	
	@RequestMapping(value="auth.do", method=RequestMethod.POST)
	public String auth(int no, String id, String password, Model model) {
		
		try{
			HomeworkBoard board = boardService.search(no);
			String authId = board.getId();
			String authPw = board.getPw();
			
			if(id != null && authId != null && password != null && authPw != null) {
				if(id.equals(authId) && password.equals(authPw)) {
//					model.addAttribute("board", board);
//					model.addAttribute("content", "homework/searchBoard.jsp");
					return "redirect: searchHomeworkBoard.do?no="+no;
				}
				else {
					System.out.println("게시판 로그인 정보 오류");
					throw new Exception("게시판 로그인 정보 오류");
				}
			} else {
				throw new Exception("잘못된 데이터입니다.");
			}
			
		} catch(Exception e) {
			throw new UpdateException(e.getMessage());
		}
	}

	@RequestMapping(value = "searchHomeworkBoard.do", method = RequestMethod.GET)
	public String searchBoard(int no, Model model) {
		
		HomeworkBoard board = boardService.search(no);
		
		String regDate = board.getRegdate();
		String[] date = regDate.split("-");
		String day = date[2];
		Integer dueDay = Integer.parseInt(day) + 7;
		date[2] = dueDay.toString(); 
		
		
		model.addAttribute("board", board);
		model.addAttribute("content", "homework/searchBoard.jsp");
		return "index";
	}

	@RequestMapping(value = "insertHomeworkBoard.do", method = RequestMethod.POST)
	public String insertBoard(HomeworkBoard board, HttpServletRequest request) {
		
		System.out.println(board);
		
		String dir = request.getRealPath("upload/");
		boardService.add(board, dir);
		return "redirect:listHomeworkBoard.do";
	}

	@RequestMapping(value = "insertHomeworkBoardReply.do", method = RequestMethod.POST)
	public String insertReply(HomeworkBoardReply reply, int no, String id,
			HttpServletRequest request, String returnurl) {
		String dir = request.getRealPath("upload/");
		reply.setNo(no);
		reply.setWriter(id);
		boardService.addReply(reply, dir);
		return "redirect:searchHomeworkBoard.do?" + returnurl;
	}

	@RequestMapping(value = "insertHomeworkBoardForm.do", method = RequestMethod.GET)
	public String insertBoardForm(Model model, HttpSession session) {
		if (LoginCheck.check(model, session, "insertHomeworkBoardForm.do")) {
			model.addAttribute("content", "homework/insertBoard.jsp");
		} else {
			model.addAttribute("content", "member/login.jsp");
		}
		return "index";
	}

	@RequestMapping(value = "updateHomeworkBoard.do", method = RequestMethod.GET)
	public String updateNoticeBoard(int no, String title, String contents, String returnurl,
			Model model) {
		System.out.println(no);
		System.out.println(contents);
		System.out.println(returnurl);
		HomeworkBoard board = boardService.search(no);
		board.setContents(contents);
		board.setTitle(title);
		boardService.update(board);

		return "redirect:searchHomeworkBoard.do?" + returnurl;
	}

	@RequestMapping(value = "deleteHomeworkBoard.do", method = RequestMethod.GET)
	public String deleteNoticeBoard(int no) {
		System.out.println(no + "======================");
		boardService.remove(no);
		return "redirect: listHomeworkBoard.do";
	}


	@RequestMapping(value = "filedown.do")
	public void fileDown(HttpServletRequest req, HttpServletResponse res,
			@RequestParam String rfilename, @RequestParam String sfilename) {
		res.reset();
		int len = 0;
		byte[] buf = new byte[1024];
		BufferedOutputStream out = null;
		FileInputStream fis = null;
		StringBuffer sb = new StringBuffer();
		try {
			System.out.println("=================================");
			System.out.println("realFilename:" + sfilename);
			System.out.println("File Name = " + rfilename);
			System.out.println("=================================");

			String dir = req.getRealPath("/upload/");
			System.out.println("dir :" + dir);
			// 다운 시켜줄 파일에 대한 경로를 지정한 파일 읽어줄 객체를 생성
			fis = new FileInputStream(dir + "/" + sfilename);
			String agent = req.getHeader("User-Agent");
			if (agent != null && agent.indexOf("MSIE 5.5") != -1) {
				res.setContentType("doesn/matter");
				res.setHeader("Content-Disposition", "filename=" + rfilename
						+ ";");
			} else {
				res.setContentType("application/octet-stream");
				res.setHeader("Content-Disposition", "attachment;filename="
						+ rfilename + ";");
			}
			;
			res.setHeader("Content-Transfer-Encoding", "binary;");
			res.setHeader("Content-Length", "" + fis.available());
			res.setHeader("Pragma", "no-cache;");
			res.setHeader("Expires", "-1;");

			out = new BufferedOutputStream(res.getOutputStream());

			System.out.println("OutputStream 생성");
			// 파일의 끝이 아닐때까지 파일 정보를 읽는다.
			while ((len = fis.read(buf)) > 0) {
				out.write(buf, 0, len); // 읽은 데이타를 출력
			}
			System.out.println("OutputStream 완료");
			out.flush(); // 버퍼에 출력된 데이타를 강제로 출력
			System.out.println("OutputStream flush()");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (Exception e) {
				}
		}

	}

}
