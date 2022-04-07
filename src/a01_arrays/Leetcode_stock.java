package a01_arrays;

public class Leetcode_stock
{
    public int maxProfit123a(int[] prices)
    {
        if (prices == null || prices.length < 2) return 0;
        int len = prices.length;
        int[] p1 = new int[len];
        int[] p2 = new int[len];

        int lowest = prices[0];
        int maxProfit1 = 0;
        int maxProfit2 = 0;
        int highest = prices[len - 1];

        for (int i = 1; i < len; i++)
        {
            if (prices[i] > lowest)
            {
                maxProfit1 = Math.max(maxProfit1, prices[i] - lowest);
                p1[i] = Math.max(maxProfit1, p1[i]);
            } else
            {
                lowest = prices[i];
                p1[i] = Math.max(maxProfit1, p1[i]);
            }
            //System.out.print(p1[i]);
        }

        for (int j = len - 2; j >= 0; j--)
        {
            if (prices[j] < highest)
            {
                maxProfit2 = Math.max(maxProfit2, highest - prices[j]);
                p2[j] = Math.max(maxProfit2, p2[j + 1]);
            } else
            {
                highest = prices[j];
                p2[j] = Math.max(maxProfit2, p2[j + 1]);
            }
            //System.out.print(p2[j]);
        }
        int res = 0;
        for (int i = 0; i < len; i++)
        {
            int total = p1[i] + p2[i];
            res = Math.max(res, total);
        }

        return res;
    }

    public int maxProfit123b(int[] prices)
    {
        // We need to prepare two arrays
        // First array starts from begin and record the max profit up to current index. It goes forward.
        // Second array starts from the end and record the max profit up to current index. It goes backward.
        // Sum up two arrays and get the max
        if (prices == null || prices.length < 2) return 0;
        int[] p1 = new int[prices.length];
        int[] p2 = new int[prices.length];

        int lowest = prices[0];
        for (int i = 1; i < prices.length; i++)
        {
            p1[i] = Math.max(p1[i], prices[i] - lowest);
            lowest = Math.min(prices[i], lowest);
        }
        int highest = prices[prices.length - 1];
        for (int j = prices.length - 2; j >= 0; j--)
        {
            p2[j] = Math.max(p2[j + 1], highest - prices[j]);
            highest = Math.max(prices[j], highest);
        }

        int max = 0;
        for (int idx = 0; idx < prices.length; idx++)
        {
            max = Math.max(max, p1[idx] + p2[idx]);
        }

        return max;
    }

    public int maxProfit188a(int k, int[] prices)
    {
        if (k == 0 || prices.length < 2) return 0;
        int[][] max = new int[k + 1][prices.length];
        for (int trans = 1; trans <= k; trans++)
        {
            for (int day = 1; day < prices.length; day++)
            {
                int curMax = 0;
                for (int lastDay = day - 1; lastDay >= 0; lastDay--)
                {
                    curMax = Math.max(curMax, getMaxProfitInOneTransaction(lastDay, day, prices) + max[trans - 1][lastDay]);
                }
                max[trans][day] = curMax;
            }
        }
        return max[k][prices.length - 1];
    }

    public int maxProfit188b(int k, int[] prices)
    {
        // Value function
        // no transaction: max[2][4]
        if (k == 0 || prices == null || prices.length < 2) return 0;
        int[][] max = new int[k + 1][prices.length];
        for (int trans = 1; trans <= k; trans++)
        {
            int maxRemaining = -prices[0];
            for (int day = 1; day < prices.length; day++)
            {   // find max of (have transaction, not have transaction) at day
                max[trans][day] = Math.max(maxRemaining + prices[day], max[trans][day - 1]);
                // find the max of remaining which is how much after buying a stock
                maxRemaining = Math.max(maxRemaining, max[trans - 1][day] - prices[day]);
            }
        }
        return max[k][prices.length - 1];
    }

    public int maxProfit188c(int k, int[] prices)
    {
        // Value function
        // no transaction: max[2][4]
        if (k == 0 || prices == null || prices.length < 2) return 0;
        int[] lastTransMax = new int[prices.length];
        int[] currentTransMax = new int[prices.length];

        for (int trans = 1; trans <= k; trans++)
        {
            int maxRemaining = -prices[0];
            for (int day = 1; day < prices.length; day++)
            {   // find max of (have transaction, not have transaction) at day
                currentTransMax[day] = Math.max(maxRemaining + prices[day], currentTransMax[day - 1]);
                // find the max of remaining which is how much after buying a stock
                maxRemaining = Math.max(maxRemaining, lastTransMax[day] - prices[day]);
            }
            lastTransMax = currentTransMax;
            currentTransMax = new int[prices.length];
        }
        return lastTransMax[prices.length - 1];
    }


    private int getMaxProfitInOneTransaction(int start, int end, int[] prices)
    {
        int max = 0;
        int low = prices[start];
        for (int i = start; i <= end; i++)
        {
            max = Math.max(max, prices[i] - low);
            low = Math.min(low, prices[i]);
        }
        return max;
    }

    public static void main(String[] args)
    {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        Leetcode_stock sol = new Leetcode_stock();
        System.out.println(sol.maxProfit123a(test));
        System.out.println(sol.maxProfit123b(test));
        System.out.println(sol.maxProfit188a(3, test));
        System.out.println(sol.maxProfit188b(3, test));
        System.out.println(sol.maxProfit188c(3, test));
    }
}
