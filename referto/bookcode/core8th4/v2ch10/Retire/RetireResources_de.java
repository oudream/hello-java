/**
 * @version 1.2 1999-10-13
 * @author Cay Horstmann
 */

import java.util.*;
import java.awt.*;

public class RetireResources_de
   extends java.util.ListResourceBundle
{  public Object[][] getContents() { return contents; }
   static final Object[][] contents =
   {  // BEGIN LOCALIZE
      { "language", "Sprache" },
      { "computeButton", "Rechnen" },
      { "savings", "Vorherige Ersparnisse" },
      { "contrib", "Jährliche Einzahlung" },
      { "income", "Einkommen nach Ruhestand" },
      { "currentAge", "Jetziges Alter" },
      { "retireAge", "Ruhestandsalter" },
      { "deathAge", "Lebenserwartung" },
      { "inflationPercent", "Inflation" },
      { "investPercent", "Investitionsgewinn" },
      { "retire", "Alter: {0,number} Guthaben: {1,number,currency}" },
      { "colorPre", Color.yellow },
      { "colorGain", Color.black },
      { "colorLoss", Color.red }

      // END LOCALIZE
   };
}
