/*
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

 

Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.
Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".
 

Constraints:

1 <= low <= high <= 105
1 <= zero, one <= low
 */

public class CountWaysToString {
    public int countGoodStrings(int low, int high, int zero, int one) {
        String res = "";
        return buildStrings(low, high, zero, one, res);
    }

    public int buildStrings(int low, int high, int zero, int one, String sb){
        if(sb.length() > high) return 0;
        int count = 0;
        if(sb.length() >= low) count++;
        int c1 = buildStrings(low, high, zero, one, sb + "1".repeat(one));
        int c2 = buildStrings(low, high, zero, one, sb + "0".repeat(zero));
        return (c1+c2+count) % (int)(Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {
        CountWaysToString cws = new CountWaysToString();
        System.out.println(cws.countGoodStrings(3, 3, 1, 1));
    }
}
