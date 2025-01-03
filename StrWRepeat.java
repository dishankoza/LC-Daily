// import java.util.Collections;
// import java.util.HashMap;
// import java.util.PriorityQueue;

// class Solution {
//     public String repeatLimitedString(String s, int repeatLimit) {
//         PriorityQueue<Character> pq = new PriorityQueue<>(Collections.reverseOrder());
//         HashMap<Character, Integer> chCount = new HashMap<>();

//         for(char ch: s.toCharArray()){
//             chCount.put(ch, chCount.getOrDefault(ch, 0) + 1);
//         }

//         for(Character ch: chCount.keySet()){
//             pq.add(ch);
//         }

//         StringBuilder res = new StringBuilder();

//         while(!pq.isEmpty()){
//             Character ch = pq.poll();
//             int chLen = chCount.get(ch);
//             int toAdd = Math.min(chLen, repeatLimit);
//             chLen -=  toAdd;
//             res.append(String.valueOf(ch).repeat(toAdd));
//             if(chLen > 0){
//                 if(pq.size() == 0)
//                     break;
                
//                 Character nxt_ch = pq.poll();
//                 int len = chCount.get(nxt_ch);
//                 res.append(nxt_ch);
//                 if(len - 1 > 0){
//                     chCount.put(nxt_ch, len - 1);
//                     pq.add(nxt_ch);
//                 }else  chCount.remove(nxt_ch);
//             chCount.put(ch, chLen);
//             pq.add(ch);
//             }else{
//                 chCount.remove(ch);
//             }
//         }

//         return res.toString();
//     }
// }

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {


    public static void main(String[] args) {
        System.out.println(repeatLimitedString("zzzaaccccaaa", 2));
    }
    
    public static String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder res = new StringBuilder();
        HashMap<Character, Integer> counter = new HashMap<>();
        Character last = null;
        int count = 0;

        for(char ch: s.toCharArray()){
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);
        }
        boolean done = false;
        while(!done){
            done = true;
            for(Character ch = 'z'; ch >= 'a'; ch-- ){
                if(counter.get(ch) != null && counter.get(ch) > 0){
                    if(last != ch || (count + 1) <= repeatLimit){
                        res.append(ch);
                        if(last == ch) count++;
                        else
                            count = 1;
                        last = ch;
                        counter.put(ch, counter.get(ch) - 1);
                        done = false;
                        break;
                    }

                }
            }
        }
        return res.toString();
    }
}