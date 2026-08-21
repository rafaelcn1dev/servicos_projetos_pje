/*    */ package br.jus.cnj.migrastorage.conf;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public enum Config
/*    */ {
/*  9 */   PJE_CONNECTION_DRIVER("pje.connection.driver"), 
/* 10 */   PJE_CONNECTION_URL("pje.connection.url"), 
/* 11 */   PJE_CONNECTION_USER("pje.connection.user"), 
/* 12 */   PJE_CONNECTION_PASSWORD("pje.connection.password"), 
/*    */ 
/* 14 */   PJE_BIN_CONNECTION_DRIVER("pje.bin.connection.driver"), 
/* 15 */   PJE_BIN_CONNECTION_URL("pje.bin.connection.url"), 
/* 16 */   PJE_BIN_CONNECTION_USER("pje.bin.connection.user"), 
/* 17 */   PJE_BIN_CONNECTION_PASSWORD("pje.bin.connection.password"), 
/*    */ 
/* 19 */   JCR_STORAGE_URL("jcr-storage.url"), 
/* 20 */   JCR_STORAGE_USERNAME("jcr-storage.username"), 
/* 21 */   JCR_STORAGE_PASSOWRD("jcr-storage.password"), 
/*    */ 
/* 23 */   THREADS("threads"), 
/* 24 */   BUFFER("buffer"), 
/* 25 */   COMMIT("commit"), 
/* 26 */   TIPO_MIGRACAO("tipoMigracao");
/*    */ 
/*    */   private static Properties properties;
/*    */   private String propiedade;
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 56 */       File configFile = new File(System.getProperty("user.dir") + "/conf/properties.xml");
/* 57 */       properties = new Properties();
/* 58 */       FileInputStream fis = new FileInputStream(configFile);
/* 59 */       properties.loadFromXML(fis);
/* 60 */       fis.close();
/*    */     }
/*    */     catch (Exception e) {
/* 63 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   private Config(String propiedade)
/*    */   {
/* 32 */     this.propiedade = propiedade;
/*    */   }
/*    */ 
/*    */   public Object getValue() {
/* 36 */     if (equals(THREADS))
/* 37 */       return Integer.valueOf(Integer.parseInt(properties.getProperty(this.propiedade)));
/* 38 */     if (equals(BUFFER))
/* 39 */       return Long.valueOf(Long.parseLong(properties.getProperty(this.propiedade)));
/* 40 */     if (equals(TIPO_MIGRACAO)) {
/* 41 */       String valor = properties.getProperty(this.propiedade);
/* 42 */       if ((valor != null) && (valor.trim().length() > 0) && (((valor.equals("BIN_JCR")) || (valor.equals("DB_JCR"))))) {
/* 43 */         return valor;
/*    */       }
/* 45 */       throw new IllegalArgumentException(String.format("O Valor informado para o tipoMigracao foi %s valor esperado %s ou %s", new Object[] { 
/* 46 */         valor, "BIN_JCR", "DB_JCR" }));
/*    */     }
/*    */ 
/* 50 */     return properties.getProperty(this.propiedade);
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.conf.Config
 * JD-Core Version:    0.5.4
 */