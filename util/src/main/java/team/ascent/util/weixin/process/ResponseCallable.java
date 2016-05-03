package team.ascent.util.weixin.process;

import java.util.concurrent.Callable;

import team.ascent.util.weixin.InstructionDTO;

public class ResponseCallable implements Callable<InstructionDTO> {

	private InstructionDTO io;
	private IProcessor<InstructionDTO> process;

	public ResponseCallable(InstructionDTO io, IProcessor<InstructionDTO> process) {
		super();
		this.io = io;
		this.process = process;
	}

	public InstructionDTO call() throws Exception {
		process.actualProcess(io);
		return io;
	}

}
