package a01_arrays;

public class Leetcode_stock
{
    public int maxProfit(int[] prices)
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

    public int maxProfit2(int[] prices)
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

    public static void main(String[] args)
    {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        Leetcode_stock sol = new Leetcode_stock();
        System.out.println(sol.maxProfit(test));
        System.out.println(sol.maxProfit2(test));
    }
}
