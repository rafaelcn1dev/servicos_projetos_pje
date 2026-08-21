/*     */ package br.jus.cnj.migrastorage.manager;
/*     */ 
/*     */ import br.jus.cnj.migrastorage.conf.Config;
/*     */ import br.jus.cnj.migrastorage.conf.ConnectionManager;
/*     */ import br.jus.cnj.migrastorage.conf.ConnectionManager.DB;
/*     */ import java.sql.BatchUpdateException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class DocumentoManager
/*     */ {
/*  17 */   private static Logger logger = Logger.getLogger(DocumentoManager.class);
/*  18 */   Connection conn = null;
/*  19 */   PreparedStatement update = null;
/*     */ 
/*     */   public long countDocumento() throws Exception {
/*  22 */     Connection conn = ConnectionManager.getConnection(ConnectionManager.DB.PJE);
/*     */ 
/*  24 */     PreparedStatement pstmt = null;
/*  25 */     ResultSet rs = null;
/*     */     try
/*     */     {
/*  28 */       String sql = "select count(*) from core.tb_processo_documento_bin ";
/*  29 */       if (Config.TIPO_MIGRACAO.getValue().equals("BIN_JCR"))
/*  30 */         sql = sql + "where nr_documento_storage is null";
/*     */       else {
/*  32 */         sql = sql + "where nr_documento_storage is not null";
/*     */       }
/*     */ 
/*  35 */       sql = sql + " and id_sessao_pg is null";
/*     */ 
/*  37 */       pstmt = conn.prepareStatement(sql);
/*  38 */       rs = pstmt.executeQuery();
/*     */ 
/*  40 */       long resultCount = 0L;
/*     */ 
/*  42 */       if (rs.next()) {
/*  43 */         resultCount = rs.getLong(1);
/*     */       }
/*     */ 
/*  46 */       return resultCount;
/*     */     }
/*     */     finally
/*     */     {
/*  50 */       if (rs != null)
/*  51 */         rs.close();
/*  52 */       if (pstmt != null)
/*  53 */         pstmt.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public ResultSet recuperarDocumento(long offset, long rowCount) throws Exception
/*     */   {
/*  59 */     Connection conn = ConnectionManager.getConnection(ConnectionManager.DB.PJE);
/*     */ 
/*  61 */     PreparedStatement pstmt = null;
/*  62 */     ResultSet rs = null;
/*  63 */     String sql = "select id_processo_documento_bin, nr_documento_storage from core.tb_processo_documento_bin ";
/*  64 */     if (Config.TIPO_MIGRACAO.getValue().equals("BIN_JCR"))
/*  65 */       sql = sql + "where nr_documento_storage is null";
/*     */     else {
/*  67 */       sql = sql + "where nr_documento_storage is not null";
/*     */     }
/*  69 */     sql = sql + " and id_sessao_pg is null";
/*  70 */     sql = sql + " order by 1 LIMIT ? OFFSET ?";
/*  71 */     pstmt = conn.prepareStatement(sql);
/*  72 */     pstmt.setLong(1, rowCount);
/*  73 */     pstmt.setLong(2, offset);
/*  74 */     rs = pstmt.executeQuery();
/*     */ 
/*  76 */     return rs;
/*     */   }
/*     */ 
/*     */   public ResultSet recuperarBinario(Object idDocumento) throws Exception
/*     */   {
/*  81 */     Connection conn = ConnectionManager.getConnection(ConnectionManager.DB.PJE_BIN);
/*  82 */     PreparedStatement pstmt = null;
/*  83 */     ResultSet rs = null;
/*  84 */     String sql = "select * from core.tb_processo_documento_bin ";
/*  85 */     if (Config.TIPO_MIGRACAO.getValue().equals("BIN_JCR"))
/*  86 */       sql = sql + "where id_processo_documento_bin = ?";
/*     */     else {
/*  88 */       sql = sql + "where hash_documento = ?";
/*     */     }
/*  90 */     pstmt = conn.prepareStatement(sql);
/*  91 */     if (Config.TIPO_MIGRACAO.getValue().equals("BIN_JCR"))
/*  92 */       pstmt.setLong(1, ((Long)idDocumento).longValue());
/*     */     else {
/*  94 */       pstmt.setString(1, (String)idDocumento);
/*     */     }
/*  96 */     rs = pstmt.executeQuery();
/*  97 */     return rs;
/*     */   }
/*     */ 
/*     */   public void preparaAtualizacao()
/*     */     throws Exception
/*     */   {
/* 103 */     this.conn = ConnectionManager.getConnection(ConnectionManager.DB.PJE);
/* 104 */     if (this.conn.getAutoCommit()) {
/* 105 */       this.conn.setAutoCommit(false);
/*     */     }
/* 107 */     String sqlUpdatePessoa = "update core.tb_processo_documento_bin  set nr_documento_storage = ? , id_sessao_pg = 1 where id_processo_documento_bin = ?";
/*     */ 
/* 109 */     this.update = this.conn.prepareStatement(sqlUpdatePessoa);
/*     */   }
/*     */ 
/*     */   public void atualizar(String numeroDocumento, Long id) throws Exception
/*     */   {
/* 114 */     this.update.setString(1, numeroDocumento);
/* 115 */     this.update.setLong(2, id.longValue());
/* 116 */     this.update.addBatch();
/*     */   }
/*     */ 
/*     */   public void flush() throws Exception {
/*     */     try {
/* 121 */       this.update.executeBatch();
/* 122 */       this.conn.commit();
/*     */     }
/*     */     catch (Exception e) {
/* 125 */       String mensagem = "Os " + 
/* 126 */         Config.BUFFER.getValue() + 
/* 127 */         " Não foram atualizados: ";
/* 128 */       mensagem = mensagem + " Exc: " + e.getMessage();
/* 129 */       if (e instanceof BatchUpdateException) {
/* 130 */         logger.error("NextException:", 
/* 131 */           ((BatchUpdateException)e).getNextException());
/* 132 */         mensagem = mensagem + "NextException:" + 
/* 133 */           ((BatchUpdateException)e).getNextException()
/* 134 */           .getMessage();
/*     */       }
/* 136 */       logger.error(mensagem, e);
/* 137 */       if (this.conn != null)
/* 138 */         ConnectionManager.release(ConnectionManager.DB.PJE);
/*     */     } finally {
/* 140 */       finalizar();
/* 141 */       preparaAtualizacao();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void finalizar() throws Exception
/*     */   {
/* 147 */     if (this.update != null) {
/* 148 */       this.update.clearBatch();
/* 149 */       this.update.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.manager.DocumentoManager
 * JD-Core Version:    0.5.4
 */