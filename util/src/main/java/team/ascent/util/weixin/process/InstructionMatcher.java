package team.ascent.util.weixin.process;

import team.ascent.util.SpringContextHolder;
import team.ascent.util.weixin.InstructionDTO;
import team.ascent.util.weixin.enumeration.RequestType;
import team.ascent.util.weixin.request.RequestDTO;

/**
 * 指令匹配器
 *
 */
public class InstructionMatcher {
	
	
	public static IProcessor<InstructionDTO> match(InstructionDTO io) {
		RequestDTO request = io.getRequest();

		switch (RequestType.getByValue(request.getMsgType())) {
		case EVENT://只处理接收事件
			return SpringContextHolder.getBean(EventProcessor.class);
		default:
			return SpringContextHolder.getBean(UnknowRequestTypeProcessor.class);
		}
	}
	
}
