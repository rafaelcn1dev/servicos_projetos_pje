/*    */ package br.jus.cnj.migrastorage.conf;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class StorageConfiguration
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 13 */     criarArquivoConfiguracao();
/*    */   }
/*    */ 
/*    */   public static void criarArquivoConfiguracao() {
/*    */     try {
/* 18 */       String caminho = System.getProperty("user.dir") + "/conf/jcr-storage.properties";
/* 19 */       new FileWriter(caminho).close();
/* 20 */       File file = new File(caminho);
/* 21 */       Properties properties = new Properties();
/*    */ 
/* 24 */       FileInputStream fis = new FileInputStream(file);
/* 25 */       properties.load(fis);
/*    */ 
/* 27 */       properties.setProperty("jcr.url", (String)Config.JCR_STORAGE_URL.getValue());
/* 28 */       properties.setProperty("jcr.username", (String)Config.JCR_STORAGE_USERNAME.getValue());
/* 29 */       properties.setProperty("jcr.password", (String)Config.JCR_STORAGE_PASSOWRD.getValue());
/* 30 */       properties.setProperty("jcr.chunkSize", "1048576");
/* 31 */       properties.setProperty("jcr.hostMaxConn", "100");
/*    */ 
/* 34 */       FileOutputStream fos = new FileOutputStream(file);
/* 35 */       properties.store(fos, "");
/* 36 */       fos.close();
/* 37 */       System.setProperty("br.jus.cnj.pje.jcr-storage.configuration", caminho);
/*    */     } catch (IOException ex) {
/* 39 */       System.out.println(ex.getMessage());
/* 40 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.conf.StorageConfiguration
 * JD-Core Version:    0.5.4
 */