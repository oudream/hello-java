import java.util.*;
import java.awt.*;

public class RetireResources
   extends java.util.ListResourceBundle 
{  public Object[][] getContents() { return contents; }
   static final Object[][] contents = 
   {  // BEGIN LOCALIZE
      { "language", "Language" },
      { "computeButton", "Compute" },
      { "savings", "Prior Savings" },
      { "contrib", "Annual Contribution" },
      { "income", "Retirement Income" },
      { "currentAge", "Current Age" },
      { "retireAge", "Retirement Age" },
      { "deathAge", "Life Expectancy" },
      { "inflationPercent", "Inflation" },
      { "investPercent", "Investment Return" },
      { "retire", "Age: {0,number} Balance: {1,number,currency}" },
      { "colorPre", Color.blue },
      { "colorGain", Color.white },
      { "colorLoss", Color.red }
      // END LOCALIZE
   };
}

