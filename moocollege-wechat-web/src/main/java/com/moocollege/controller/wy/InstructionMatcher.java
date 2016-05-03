package com.moocollege.controller.wy;
//package com.box.tei.controller.wy;
//
//import com.box.tei.dto.InstructionObject;
//import com.box.tei.dto.RequestDTO;
//import com.box.tei.enumeration.RequestType;
//import com.box.tei.service.process.Processor;
//import com.box.tei.service.process.impl.EventProcessor;
//import com.box.tei.service.process.impl.LocationProcessor;
//import com.box.tei.service.process.impl.PictureProcessor;
//import com.box.tei.service.process.impl.TextProcessor;
//import com.box.tei.service.process.impl.UnknowRequestTypeProcessor;
//import com.box.tei.service.process.impl.VoiceProcessor;
//import com.box.tei.util.SpringContextHolder;
//
///**
// * 指令匹配器
// * @author "iacdp"
// *
// */
//public class InstructionMatcher {
//	
//	
//	public static Processor<InstructionObject> match(InstructionObject io) {
//		RequestDTO request = io.getRequest();
//
//		switch (RequestType.getByValue(request.getMsgType())) {
//		case EVENT:
//			return SpringContextHolder.getBean(EventProcessor.class);
//		case TEXT:
//			return SpringContextHolder.getBean(TextProcessor.class);
//		case VOICE:
//			return SpringContextHolder.getBean(VoiceProcessor.class);
//		case IMAGE:
//			return SpringContextHolder.getBean(PictureProcessor.class);
//		case LOCATION:
//			return SpringContextHolder.getBean(LocationProcessor.class);
//		default:
//			return SpringContextHolder.getBean(UnknowRequestTypeProcessor.class);
//		}
//	}
//	
//}
