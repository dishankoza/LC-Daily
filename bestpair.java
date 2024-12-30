class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int max = Integer.MIN_VALUE + 1;
        int score = Integer.MIN_VALUE;
        for(int r = 0; r < values.length; r++){
            int value = values[r];
            score = Math.max(score, value + max);
            max = Math.max(max - 1, value - 1 );
        }

        return score;
    }
}

/*

8,1,5,2,6

8,4,5,2,6

*/