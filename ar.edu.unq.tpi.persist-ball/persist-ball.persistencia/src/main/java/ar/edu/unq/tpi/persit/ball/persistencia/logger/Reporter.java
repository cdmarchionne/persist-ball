package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.stat.Statistics;

import ar.edu.unq.tpi.persist.ball.domain.utils.ListUtils;
import ar.edu.unq.tpi.persit.ball.persistencia.PersistenceManager;

public class Reporter {
	private static Statistics statistics;
	private static List<Long> queryDelays = new ArrayList<Long>();
	private static final String FILE_NAME = "src/main/resources/reporter.txt";
	private static final Log LOG = LogFactory.getLog(Reporter.class);
	private static long time;
	static{
		statistics = PersistenceManager.getInstance().getDefaultSessionFactory().getStatistics();
	}
	
	public static void startTime(){
		time = System.currentTimeMillis();
		Logger.log("startTime 0");
	}
	
	
	public static void finishTime(String cunsulta){
		long currentQueryDelay = System.currentTimeMillis()-time;
		queryDelays.add(currentQueryDelay);
		Logger.log("finishTime "+ currentQueryDelay);
//		LOG.debug(cunsulta+"\t"+currentQueryDelay +"\tms");
	}
	
	
	public static void show(){
		Logger.log("transactions: " + statistics.getTransactionCount());
		Logger.log("connections obtained: " + statistics.getConnectCount());
		Logger.log("second level cache puts: " + statistics.getSecondLevelCachePutCount());
		Logger.log("second level cache hits: " + statistics.getSecondLevelCacheHitCount());
		Logger.log("second level cache misses: " + statistics.getSecondLevelCacheMissCount());
		Logger.log("entities loaded: " + statistics.getEntityLoadCount());
		Logger.log("entities updated: " + statistics.getEntityUpdateCount());
		Logger.log("entities inserted: " + statistics.getEntityInsertCount());
		Logger.log("entities deleted: " + statistics.getEntityDeleteCount());
		Logger.log("queries executed to database: " + statistics.getQueryExecutionCount());
		Logger.log("query cache puts: " + statistics.getQueryCachePutCount());
		Logger.log("query cache hits: " + statistics.getQueryCacheHitCount());
		Logger.log("query cache misses: " + statistics.getQueryCacheMissCount());
		Logger.log("max query time: " + statistics.getQueryExecutionMaxTime() + "ms");
		statistics.clear();
	}
	
	public static void logAverageQueryDelay(){
		Long totalTime = 0L;
		for (Long time : queryDelays) {
			totalTime += time;
		}
		DecimalFormat format = new DecimalFormat("#.##");
		String message = "Promedio de tiempo de las consultas "+format.format((float)totalTime/queryDelays.size()) +"\tms";
		if(PersistenceManager.getInstance().isLogFile()){
			LOG.debug(message);
		}
		queryDelays.removeAll(queryDelays);
	}
	
}
