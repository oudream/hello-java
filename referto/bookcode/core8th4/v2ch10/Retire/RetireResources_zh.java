/**
 * @version 1.2 1999-10-13
 * @author Cay Horstmann
 */

import java.util.*;
import java.awt.*;

public class RetireResources_zh
   extends java.util.ListResourceBundle
{  public Object[][] getContents() { return contents; }
   static final Object[][] contents =
   {  // BEGIN LOCALIZE
      { "language", "\u8bed\u8a00" },
      { "computeButton", "\u8ba1\u7b97" },
      { "savings", "\u65e2\u5b58" },
      { "contrib", "\u6bcf\u5e74\u5b58\u91d1" },
      { "income", "\u9000\u4f11\u6536\u5165" },
      { "currentAge", "\u73b0\u5cad" },
      { "retireAge", "\u9000\u4f11\u5e74\u9f84" },
      { "deathAge", "\u9884\u671f\u5bff\u547d" },
      { "inflationPercent", "\u901a\u8d27\u81a8\u6da8" },
      { "investPercent", "\u6295\u8d44\u62a5\u916c" },
      { "retire",
         "\u5e74\u9f84: {0,number} \u603b\u7ed3: {1,number,currency}" },
      { "colorPre", Color.red },
      { "colorGain", Color.blue },
      { "colorLoss", Color.yellow }

      // END LOCALIZE
   };
}
