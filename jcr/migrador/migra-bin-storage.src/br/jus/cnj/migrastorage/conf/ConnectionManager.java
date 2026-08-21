/*     */ package br.jus.cnj.migrastorage.conf;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class ConnectionManager
/*     */ {
/*  13 */   private static Logger logger = Logger.getLogger(ConnectionManager.class);
/*     */   private static ConnectionManager connectionManager;
/*     */   private static Map<String, Connection> liveConnections;
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  20 */       Class.forName((String)Config.PJE_CONNECTION_DRIVER.getValue());
/*  21 */       Class.forName((String)Config.PJE_BIN_CONNECTION_DRIVER.getValue());
/*     */     } catch (ClassNotFoundException e) {
/*  23 */       throw new RuntimeException(e);
/*     */     }
/*     */ 
/*  26 */     Thread thread = new Thread() {
/*     */       public void run() {
/*     */         try {
/*  29 */           ConnectionManager.release();
/*     */         } catch (Throwable e) {
/*  31 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     };
/*  36 */     Runtime.getRuntime().addShutdownHook(thread);
/*     */ 
/*  49 */     liveConnections = new HashMap();
/*     */   }
/*     */ 
/*     */   public static ConnectionManager instance()
/*     */   {
/*  42 */     if (connectionManager == null) {
/*  43 */       connectionManager = new ConnectionManager();
/*     */     }
/*     */ 
/*  46 */     return connectionManager;
/*     */   }
/*     */ 
/*     */   public static synchronized Connection getConnection(DB db)
/*     */     throws SQLException
/*     */   {
/*  52 */     String key = db.name() + Thread.currentThread().getId();
/*  53 */     Connection connection = (Connection)liveConnections.get(key);
/*     */ 
/*  55 */     if (connection == null) {
/*  56 */       switch (db)
/*     */       {
/*     */       case DB_STORAGE:
/*  58 */         connection = DriverManager.getConnection(
/*  59 */           (String)Config.PJE_CONNECTION_URL.getValue(), 
/*  60 */           (String)Config.PJE_CONNECTION_USER.getValue(), 
/*  61 */           (String)Config.PJE_CONNECTION_PASSWORD.getValue());
/*  62 */         break;
/*     */       case PJE:
/*  64 */         connection = DriverManager.getConnection(
/*  65 */           (String)Config.PJE_BIN_CONNECTION_URL.getValue(), 
/*  66 */           (String)Config.PJE_BIN_CONNECTION_USER.getValue(), 
/*  67 */           (String)Config.PJE_BIN_CONNECTION_PASSWORD.getValue());
/*  68 */         break;
/*     */       case PJE_BIN:
/*  70 */         connection = DriverManager.getConnection(
/*  71 */           (String)Config.PJE_BIN_CONNECTION_URL.getValue(), 
/*  72 */           (String)Config.PJE_BIN_CONNECTION_USER.getValue(), 
/*  73 */           (String)Config.PJE_BIN_CONNECTION_PASSWORD.getValue());
/*     */       }
/*     */ 
/*  76 */       liveConnections.put(key, connection);
/*     */     }
/*  78 */     return connection;
/*     */   }
/*     */ 
/*     */   public static synchronized void release(DB db) throws Exception {
/*  82 */     String key = db.name() + Thread.currentThread().getId();
/*  83 */     Connection conn = (Connection)liveConnections.get(key);
/*  84 */     conn.close();
/*  85 */     liveConnections.remove(key);
/*     */   }
/*     */ 
/*     */   public static synchronized void release() throws Throwable {
/*  89 */     logger.info("Fechando Conexoes abertas...");
/*  90 */     int i = 0;
/*  91 */     for (String key : liveConnections.keySet()) {
/*     */       try {
/*  93 */         Connection connection = (Connection)liveConnections.get(key);
/*  94 */         if (!connection.isClosed()) {
/*  95 */           connection.close();
/*  96 */           ++i;
/*     */         }
/*     */       } catch (Exception e) {
/*  99 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 103 */     logger.info(i + " conexoes fechadas");
/*     */   }
/*     */ 
/*     */   public static enum DB
/*     */   {
/*  15 */     PJE, PJE_BIN, DB_STORAGE;
/*     */   }
/*     */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.conf.ConnectionManager
 * JD-Core Version:    0.5.4
 */