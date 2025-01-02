/*You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days. 

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.

Approach:



*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MinTicketCost {
    public int mincostTickets(int[] days, int[] costs) {
        HashSet<Integer> daySet = Arrays.stream(days).boxed().collect(Collectors.toCollection(HashSet::new));
        return getMinCost(days[0], costs, daySet, days[days.length - 1]);
    }

    public int getMinCost(int currentDay, int[] costs, HashSet days, int max){
        if(currentDay > max) return 0;
        while(!days.contains(currentDay)){
            currentDay++;
        }

        int day1C =  costs[0] + getMinCost(currentDay + 1, costs, days, max);
        int day7C =  costs[1] + getMinCost(currentDay + 7, costs, days, max);
        int day30C = costs[2] + getMinCost(currentDay + 30, costs, days, max);

        return (int)Math.min(day1C, Math.min(day7C, day30C));
    }

    public static void main(String[] args) {
        MinTicketCost mt = new MinTicketCost();
        System.out.println(mt.mincostTickets(new int[]{1,4,6,7,8,20}, new int[] {2, 7, 15}));
    }
}
