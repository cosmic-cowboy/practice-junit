package com.slgerkamp.junit.chapter12;

import java.sql.Connection;
import java.util.Properties;

import org.h2.Driver;
import org.h2.tools.Server;
import org.h2.util.JdbcUtils;
import org.junit.rules.ExternalResource;

public class H2DatabaseServer extends ExternalResource{

	private final String baseDir;
	private final String dbName;
	private final String schemaName;
	private Server server = null;
	
	public H2DatabaseServer(String baseDir, String dbName, String schemaName) {
		this.baseDir = baseDir;
		this.dbName = dbName;
		this.schemaName = schemaName;
	}
	

	@Override
	protected void before() throws Throwable {
		// DBサーバ起動
		server = Server.createTcpServer("-baseDir", baseDir);
		server.start();
		
		// スキーマ設計
		Properties props = new Properties();
		props.setProperty("user", "sa");
		props.setProperty("password", "");
		String url = "jdbc:h2:" + server.getURL() + "/" + dbName;
		Connection conn = Driver.load().connect(url, props);
		
		try {
			conn.createStatement()
			.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);			
		} finally {
			JdbcUtils.closeSilently(conn);
		}
	}
	
	@Override
	protected void after() {
		// DBサーバ停止
		server.shutdown();
	}
}
