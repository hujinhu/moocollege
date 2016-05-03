package team.ascent.util.weixin;

import team.ascent.util.weixin.request.RequestDTO;
import team.ascent.util.weixin.response.ResponseDTO;


/**
 * 用来指定微信命令泛型
 *
 */
public class InstructionDTO {
	private RequestDTO request;
	private ResponseDTO response;
	
	
	
	public InstructionDTO(RequestDTO request, ResponseDTO response) {
		this.request = request;
		this.response = response;
	}
	
	public InstructionDTO(RequestDTO request) {
		this(request, new ResponseDTO(request));
	}

	public RequestDTO getRequest() {
		return request;
	}
	public void setRequest(RequestDTO request) {
		this.request = request;
	}
	public ResponseDTO getResponse() {
		return response;
	}
	public void setResponse(ResponseDTO response) {
		this.response = response;
	}
	
}
