/*    */ package br.jus.cnj.migrastorage.conf;
/*    */ 
/*    */ import java.io.FileWriter;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class LogAtualizacao
/*    */ {
/*    */   private static FileWriter arq;
/*    */   private static PrintWriter writer;
/*    */ 
/*    */   public static void append(String valor)
/*    */     throws Exception
/*    */   {
/* 10 */     if (writer == null) {
/* 11 */       arq = new FileWriter(System.getProperty("user.dir") + "/atualizacao.log");
/* 12 */       writer = new PrintWriter(arq);
/*    */     }
/* 14 */     writer.printf(valor + "\n", new Object[0]);
/*    */   }
/*    */ 
/*    */   public static void close() throws Exception {
/* 18 */     if (arq != null)
/* 19 */       arq.close();
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.conf.LogAtualizacao
 * JD-Core Version:    0.5.4
 */