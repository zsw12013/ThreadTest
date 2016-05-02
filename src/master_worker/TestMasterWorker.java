package master_worker;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
/*
 * Master-Worker模式是常用的并行模式。它的核心思想是，系统由两类进程协作工作：
 * Master进程，Worker进程。Master进程负责接收和分配任务，worker进程负责处理子任务。
 * 当各个Worker进程将子任务处理完成后，将结果返回给Master进程，由Master进程做归纳和汇总，从而得到系统的最终结果。
 * 
 * 计算任务被分解为100个任务，每个任务仅用于计算单独的立方和。
 * Master产生固定个数的worker来处理所有这些子任务。
 * Worker不断地从任务集体集合中取得这些计算立方和的子任务，
 * 并将计算结果返回给Master.Master负责将所有worker累加，从而产生最终的结果。
 * 在计算过程中，Master和Worker的运行也是完全异步的,Master不必等所有的Worker都执行完成后，
 * 就可以进行求和操作。Master在获得部分子任务结果集时，就可以开始对最终结果进行计算，
 * 从而进一步提高系统的并行度和吞吐量。
 */


public class TestMasterWorker {
	public class PlusWorker extends Worker {
		public Object handle(Object input) {
			Integer i = (Integer) input;
			return i * i * i;
		}
	}
	// Master m = new Master(new PlusWorker(), 5);

	@Test
	public void testMasterWorker() {
		Master m = new Master(new PlusWorker(), 5);
		for (int i = 0; i < 100; i++) {
			m.submit(i);
		}
		m.execute();

		int re = 0;
		Map<String, Object> resultMap = m.getResultMap();
		while (resultMap.size() > 0 || !m.isComplete()) {
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if (key != null) {
				i = (Integer) resultMap.get(key);
			}
			if (i != null) {
				re += i;
			}
			if (key != null) {
				resultMap.remove(key);
			}
		}
        System.out.println("testMasterWorker:" + re);
	}
	  @Test
	    public void testPlus() {
	        int re = 0;
	        for (int i = 0; i < 100; i++) {
	            re += i * i * i;
	        }
	        System.out.println("testPlus:" + re);
	    }
}