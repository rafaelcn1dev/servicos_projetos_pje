/*    */ package br.jus.cnj.migrastorage.conf;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.text.Normalizer;
/*    */ import java.text.Normalizer.Form;
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   public static boolean comparaStringSemAcento(String str1, String str2)
/*    */   {
/* 11 */     return (str1 != null) && (str1.trim().length() > 0) && (str2 != null) && (str2.trim().length() > 0) && 
/* 12 */       (removeAcentos(str1).equalsIgnoreCase(removeAcentos(str2)));
/*    */   }
/*    */ 
/*    */   private static String removeAcentos(String str) {
/* 16 */     str = Normalizer.normalize(str, Normalizer.Form.NFD);
/* 17 */     str = str.replaceAll("[^\\p{ASCII}]", "");
/* 18 */     return str.trim();
/*    */   }
/*    */ 
/*    */   public static String sqlIN(Object[] objs)
/*    */   {
/* 24 */     if ((objs != null) && (objs.length > 0)) {
/* 25 */       String in = "in(";
/* 26 */       int i = 1;
/* 27 */       Object[] arrayOfObject = objs; int j = objs.length; for (int i = 0; i < j; ++i) { Object obj = arrayOfObject[i];
/* 28 */         in = in + ((i == objs.length) ? "?" : "?,");
/* 29 */         ++i; }
/*    */ 
/* 31 */       in = in + ")";
/* 32 */       return in;
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   public static void sqlInPstmt(PreparedStatement pstmt, Object[] objs) throws SQLException {
/* 38 */     int i = 1;
/* 39 */     for (Object obj : objs) {
/* 40 */       if (obj instanceof Integer)
/* 41 */         pstmt.setInt(i, ((Integer)obj).intValue());
/*    */       else {
/* 43 */         pstmt.setString(i, obj.toString());
/*    */       }
/* 45 */       ++i;
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\pje\filesystem\migra-binario-storage\migra-bin-storage\
 * Qualified Name:     br.jus.cnj.migrastorage.conf.Util
 * JD-Core Version:    0.5.4
 */