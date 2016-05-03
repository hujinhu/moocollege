package team.ascent.util.weixin.process;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.ascent.util.weixin.InstructionDTO;

@Service
@Transactional
public class UnknowRequestTypeProcessor implements IProcessor<InstructionDTO>{
	public void actualProcess(InstructionDTO io) {
		System.out.println("未知消息类型,不处理");
	}
}
