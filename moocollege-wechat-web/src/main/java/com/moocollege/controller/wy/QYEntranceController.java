package com.moocollege.controller.wy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.ascent.util.JaxbMapper;
import team.ascent.util.security.AesException;
import team.ascent.util.security.WXBizMsgCrypt;
import team.ascent.util.weixin.InstructionDTO;
import team.ascent.util.weixin.process.AutoExpandExcutor;
import team.ascent.util.weixin.process.IProcessor;
import team.ascent.util.weixin.process.InstructionMatcher;
import team.ascent.util.weixin.process.ResponseCallable;
import team.ascent.util.weixin.request.RequestDTO;
import team.ascent.util.weixin.response.ResponseDTO;

import com.moocollege.controller.base.BaseController;
import com.moocollege.dto.QyApp;
import com.moocollege.service.IQyAppService;

 
/**
 * 手动安装方式回调
 * 例:hujinhu.vicp.cc/qyEntrance/index?appId=1
 */
@Controller
@RequestMapping("/qyEntrance")
public class QYEntranceController extends BaseController {
	private static Log log = LogFactory.getLog(QYEntranceController.class);
	@Autowired
	private IQyAppService qyAppService;
	@RequestMapping(value = "/index")
	public void proceed(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "appId", required = true)  int appId) throws IOException, AesException {
		log.error("手动安装应用,回调信息 应用ID:"+appId);
		QyApp app = qyAppService.getByAppId(appId);
		if (app!=null) {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(app.getToken(),app.getAesKey(), this.getCorpId());
			if (request.getParameter("echostr") != null) {
				log.error("微信校验回调地址.");
				valid(request, response, wxcpt);
				log.error("校验结束");
				return ;
			}
			//处理回调
			InstructionDTO _io = process(request, wxcpt);
			if(_io==null){
				response(response, "");
				return ;
			}
			String sReqTimeStamp = request.getParameter("timestamp");
			String sReqNonce = request.getParameter("nonce");
			String sRespData= JaxbMapper.toXml(_io.getResponse());
			try {
				String sEncryptMsg = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);
				response(response, sEncryptMsg);
			} catch (Exception e) {
				log.error(e);
				response(response, "");
			}
		}
	}

	private InstructionDTO process(HttpServletRequest request, WXBizMsgCrypt wxcpt) throws IOException {
		String sReqMsgSig = request.getParameter("msg_signature");
		String sReqTimeStamp = request.getParameter("timestamp");
		String sReqNonce = request.getParameter("nonce");
		byte[] buffer = new byte[1024 * 1024];
		InputStream inStream=request.getInputStream();
		int length = inStream.read(buffer);
		if(length<0){
			inStream.close();
			return null;
		} 
			String requestXml = new String(buffer, 0, length, "UTF-8");
			inStream.close();
		try {
			String sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, requestXml);
//			log.error("回调信息："+sMsg);
			RequestDTO requestDTO = JaxbMapper.fromXml(sMsg, RequestDTO.class);
			ResponseDTO response = new ResponseDTO(requestDTO);
			InstructionDTO io = new InstructionDTO(requestDTO, response);
			IProcessor<InstructionDTO> processor = InstructionMatcher.match(io);
			FutureTask<InstructionDTO> ft = new FutureTask<InstructionDTO>(new ResponseCallable(io, processor));
			AutoExpandExcutor.execute(ft);
			io = ft.get(4800, TimeUnit.MILLISECONDS);
			return io;
		} catch (Exception e) {
			log.error(e);
			return null;
		}

	}

	public void valid(HttpServletRequest request, HttpServletResponse response, WXBizMsgCrypt wxcpt) {
		try {
			// 解析出url上的参数值如下：
			String sVerifyMsgSig = request.getParameter("msg_signature");
			String sVerifyTimeStamp = request.getParameter("timestamp");
			String sVerifyNonce = request.getParameter("nonce");
			String sVerifyEchoStr = request.getParameter("echostr");
			String sEchoStr; // 需要返回的明文
			
			
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
			response(response, sEchoStr);
		} catch (AesException e) {
			log.error(e);
			response(response, "");
		}
		
		
	 
		
		
		
		
	}

	public void response(HttpServletResponse response, String respText) {
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
			out.print(respText);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
