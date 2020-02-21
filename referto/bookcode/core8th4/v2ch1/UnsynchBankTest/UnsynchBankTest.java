/**
 * @version 1.20 1999-04-26
 * @author Cay Horstmann
 */

public class UnsynchBankTest
{  public static void main(String[] args)
   {  Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
      int i;
      for (i = 0; i < NACCOUNTS; i++)
      {  TransferThread t = new TransferThread(b, i,
            INITIAL_BALANCE);
         t.setPriority(Thread.NORM_PRIORITY + i % 2);
         t.start();
      }
   }

   public static final int NACCOUNTS = 10;
   public static final int INITIAL_BALANCE = 10000;
}

class Bank
{  public Bank(int n, int initialBalance)
   {  accounts = new int[n];
      int i;
      for (i = 0; i < accounts.length; i++)
         accounts[i] = initialBalance;
      ntransacts = 0;
   }

   public void transfer(int from, int to, int amount)
   {  if (accounts[from] < amount) return;
      accounts[from] -= amount;
      accounts[to] += amount;
      ntransacts++;
      if (ntransacts % NTEST == 0) test();
   }

   public void test()
   {  int sum = 0;

      for (int i = 0; i < accounts.length; i++)
         sum += accounts[i];

      System.out.println("Transactions:" + ntransacts
         + " Sum: " + sum);
   }

   public int size()
   {  return accounts.length;
   }

   public static final int NTEST = 10000;
   private int[] accounts;
   private long ntransacts = 0;
}

class TransferThread extends Thread
{  public TransferThread(Bank b, int from, int max)
   {  bank = b;
      fromAccount = from;
      maxAmount = max;
   }

   public void run()
   {  try
      {  while (!interrupted())
         {  int toAccount = (int)(bank.size() * Math.random());
            int amount = (int)(maxAmount * Math.random());
            bank.transfer(fromAccount, toAccount, amount);
            sleep(1);
         }
      }
      catch(InterruptedException e) {}
   }

   private Bank bank;
   private int fromAccount;
   private int maxAmount;
}

