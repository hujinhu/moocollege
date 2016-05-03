package team.ascent.util.weixin.process;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自动扩展线程池执行器
 *
 */
public class AutoExpandExcutor {

	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	public static void execute(Runnable task) {
		executor.execute(task);
	}
}
