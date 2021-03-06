public class Solution {
    public String removeKdigits(String num, int k) {
         if (num.length() <= k)
            return "0";
         StringBuilder result = new StringBuilder();
         Deque<Character> stack = new ArrayDeque<>();
         int i = 0;
         while (i < num.length()) {
             while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {//while not if
                stack.pop();
                k--;
             }
             stack.push(num.charAt(i++));
         }
         while (k > 0) {
             stack.pop();
             k--;
         }//to deal with the cases like "1111" or "1234"
         while (!stack.isEmpty())
            result.insert(0, stack.pop());
         while (result.length() > 0 && result.charAt(0) == '0')
            result.deleteCharAt(0);
         return result.length() == 0 ? "0" : result.toString();
    }
}//when num sorted like a increasing sequence it has the minium value because every digit with big value is at the least significant digit, so you should delete the digit which violates the increasing sequence
/*
public class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        //corner case
        if(k==len)        
            return "0";
            
        Stack<Character> stack = new Stack<>();
        int i =0;
        while(i<num.length()){
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        
        // corner case like "1111"
        while(k>0){
            stack.pop();
            k--;            
        }
        
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();
        
        //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(0)=='0')
            sb.deleteCharAt(0);
        return sb.toString();
*/
/*
The first algorithm is straight-forward. Let's think about the simplest case: how to remove 1 digit from the number so that the new number is the smallest possible？ Well, one can simply scan from left to right, and remove the first "peak" digit; the peak digit is larger than its right neighbor. One can repeat this procedure k times, and obtain the first algorithm:

string removeKdigits(string num, int k) {
        while (k > 0) {
            int n = num.size();
            int i = 0;
            while (i+1<n && num[i]<=num[i+1])  i++;
            num.erase(i, 1);
            k--;
        }
        // trim leading zeros
        int s = 0;
        while (s<(int)num.size()-1 && num[s]=='0')  s++;
        num.erase(0, s);
        
        return num=="" ? "0" : num;
    }
The above algorithm is a bit inefficient because it frequently remove a particular element from a string and has complexity O(k*n).

One can simulate the above procedure by using a stack, and obtain a O(n) algorithm. Note, when the result stack (i.e. res) pop a digit, it is equivalent as remove that "peak" digit.

    string removeKdigits(string num, int k) {
        string res;
        int keep = num.size() - k;
        for (int i=0; i<num.size(); i++) {
            while (res.size()>0 && res.back()>num[i] && k>0) {
                res.pop_back();
                k--;
            }
            res.push_back(num[i]);
        }
        res.erase(keep, string::npos);
        
        // trim leading zeros
        int s = 0;
        while (s<(int)res.size()-1 && res[s]=='0')  s++;
        res.erase(0, s);
        
        return res=="" ? "0" : res;
    }
*/
