package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import org.hibernate.stat.Statistics;

import ar.edu.unq.tpi.persit.ball.persistencia.PersistenceManager;

public class Reporter {
	private static Statistics statistics;
	private static long time;
	static{
		statistics = PersistenceManager.getInstance().getDefaultSessionFactory().getStatistics();		
	}
	
	public static void startTime(){
		time = System.currentTimeMillis();
		Logger.log("startTime 0");
	}
	
	
	public static void finishTime(){
		Logger.log("finishTime "+ (System.currentTimeMillis()-time));
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
	




}
