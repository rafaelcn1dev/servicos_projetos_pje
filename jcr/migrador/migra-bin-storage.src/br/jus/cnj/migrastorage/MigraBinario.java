/*    */ package br.jus.cnj.migrastorage;
/*    */ 
/*    */ import br.jus.cnj.migrastorage.conf.Config;
/*    */ import br.jus.cnj.migrastorage.conf.ConnectionManager;
/*    */ import br.jus.cnj.migrastorage.conf.ConnectionManager.DB;
/*    */ import br.jus.cnj.migrastorage.manager.DocumentoManager;
/*    */ import br.jus.cnj.pje.storage.jcr.client.Configuration;
/*    */ import br.jus.cnj.pje.storage.jcr.client.JCRStorageService;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.postgresql.PGConnection;
/*    */ import org.postgresql.largeobject.LargeObject;
/*    */ import org.postgresql.largeobject.LargeObjectManager;
/*    */ 
/*    */ public class MigraBinario
/*    */ {
/* 24 */   private static Logger logger = Logger.getLogger(MigraBinario.class);
/*    */   public long offset;
/*    */   public long rowCount;
/*    */   public int id;
/*    */   public long total;
/* 29 */   private static int passo = 0;
/* 30 */   public static int qtd = 1;
/*    */ 
/* 32 */   private DocumentoManager manager = new DocumentoManager();
/*    */ 
/*    */   public void migra() throws Exception
/*    */   {
/* 36 */     qtd = 1;
/*    */ 
/* 38 */     ResultSet docRS = this.manager.recuperarDocumento(this.offset, this.rowCount);
/* 39 */     JCRStorageService storageService = new JCRStorageService();
/* 40 */     storageService.setConfiguration(new Configuration());
/*    */     try
/*    */     {
/* 43 */       this.manager.preparaAtualizacao();
/* 44 */       while (docRS.next()) {
/* 45 */         Object idDocumento = null;
/* 46 */         if (Config.TIPO_MIGRACAO.getValue().equals("BIN_JCR"))
/* 47 */           idDocumento = Long.valueOf(docRS.getLong("id_processo_documento_bin"));
/* 48 */         else if (Config.TIPO_MIGRACAO.getValue().equals("DB_JCR"))
/* 49 */           idDocumento = docRS.getString("nr_documento_storage");
/*    */         try
/*    */         {
/* 52 */           ResultSet rsBin = this.manager.recuperarBinario(idDocumento);
/* 53 */           LargeObject obj = null;
/* 54 */           if ((rsBin != null) && (rsBin.next())) {
/* 55 */             Connection conn = ConnectionManager.getConnection(ConnectionManager.DB.PJE_BIN);
/* 56 */             conn.setAutoCommit(false);
/* 57 */             byte[] data = null;
/* 58 */             InputStream dados = null;
/* 59 */             if (Config.TIPO_MIGRACAO.getValue().equals("DB_JCR")) {
/* 60 */               LargeObjectManager lobj = ((PGConnection)conn).getLargeObjectAPI();
/*    */ 
/* 62 */               long oid = rsBin.getLong("ob_processo_documento");
/* 63 */               obj = lobj.open(oid, 262144);
/* 64 */               dados = obj.getInputStream();
/*    */             }
/*    */             else {
/* 67 */               data = rsBin.getBytes("ob_processo_documento");
/*    */             }
/*    */ 
/* 70 */             if ((data != null) && (data.length > 0)) {
/* 71 */               String numeroDocumento = storageService.persist(new ByteArrayInputStream(data));
/* 72 */               this.manager.atualizar(numeroDocumento, Long.valueOf(docRS.getLong("id_processo_documento_bin")));
/*    */             }
/* 74 */             if (dados != null) {
/* 75 */               String numeroDocumento = storageService.persist(dados);
/* 76 */               this.manager.atualizar(numeroDocumento, Long.valueOf(docRS.getLong("id_processo_documento_bin")));
/*    */             }
/* 78 */             data = null;
/* 79 */             dados = null;
/* 80 */             obj.close();
/*    */           }
/*    */         } catch (Exception e) {
/* 83 */           e.printStackTrace();
/*    */         }
/* 85 */         logger.info(qtd + " de " + this.total + " documentos " + qtd++ * 100 / this.total + " % concluido");
/* 86 */         Atualizador.migrado = qtd;
/* 87 */         passo += 1;
/*    */ 
/* 89 */         if (passo % ((Long)Config.BUFFER.getValue()).longValue() == 0L) {
/* 90 */           this.manager.flush();
/*    */         }
/*    */       }
/* 93 */       this.manager.flush();
/*    */     }
/*    */     finally {
/* 96 */       if (docRS != null) {
/* 97 */         docRS.close();
/*    */       }
/* 99 */       this.manager.finalizar();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.MigraBinario
 * JD-Core Version:    0.5.4
 */