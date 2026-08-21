/*    */ package br.jus.cnj.migrastorage;
/*    */ 
/*    */ import br.jus.cnj.migrastorage.conf.Config;
/*    */ import br.jus.cnj.migrastorage.conf.StorageConfiguration;
/*    */ import br.jus.cnj.migrastorage.manager.DocumentoManager;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class Atualizador
/*    */ {
/* 11 */   private static Logger logger = Logger.getLogger(MigraBinario.class);
/* 12 */   private static long count = 0L;
/* 13 */   public static int migrado = 0;
/*    */ 
/* 15 */   int threads = ((Integer)Config.THREADS.getValue()).intValue();
/*    */ 
/*    */   public static void main(String[] args) throws Throwable {
/* 18 */     StorageConfiguration.criarArquivoConfiguracao();
/* 19 */     producao();
/*    */   }
/*    */ 
/*    */   private static void test()
/*    */     throws Exception
/*    */   {
/* 25 */     count = 1000L;
/*    */ 
/* 27 */     MigraBinario migra = new MigraBinario();
/* 28 */     migra.id = 0;
/* 29 */     migra.total = count;
/* 30 */     migra.rowCount = count;
/* 31 */     migra.offset = 0L;
/* 32 */     migra.migra();
/*    */   }
/*    */ 
/*    */   private static void producao() throws Exception {
/* 36 */     logger.info("Iniciando Migração de documentos");
/* 37 */     int threads = ((Integer)Config.THREADS.getValue()).intValue();
/*    */ 
/* 39 */     count = new DocumentoManager().countDocumento();
/*    */ 
/* 42 */     for (int i = 0; i < threads; ++i) {
/* 43 */       MigraBinario migra = new MigraBinario();
/* 44 */       migra.id = i;
/* 45 */       migra.total = count;
/*    */ 
/* 48 */       migra.rowCount = (count / threads);
/* 49 */       migra.offset = (i * migra.rowCount);
/*    */ 
/* 53 */       if (i == threads - 1) {
/* 54 */         long resto = count - migra.rowCount * threads;
/* 55 */         migra.rowCount += resto;
/*    */       }
/*    */ 
/* 58 */       Thread t = new Thread(migra) {
/*    */         public void run() {
/*    */           try {
/* 61 */             Atualizador.this.migra();
/*    */           } catch (Exception e) {
/* 63 */             throw new RuntimeException(e);
/*    */           }
/*    */         }
/*    */       };
/* 67 */       t.start();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.Atualizador
 * JD-Core Version:    0.5.4
 */