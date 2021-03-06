/*
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int k = 0;
        int n = sentence.length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;) {
                System.out.println(k);
                j += sentence[k % n].length() + (j > 0 ? 1 : 0);
                if (j <= cols) k++;
            }
        }
        return k/n;
    }
}// time limit exceeded, when column is very big and only have a few words with short length
*/

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, length = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % length) == ' ') start++;
            else {
                while (start > 0 && s.charAt((start - 1) % length) != ' ') start--;
            }
        }
        return start / length;
    }
}

/*
Update: See @iambright's post below for optimized code.
Update: If you want to shorten the code by getting rid of either the while loop or the if-else check, see update below.

public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }
}
Update (4/7/2017): There is a way to get rid of the if-else check (see discussion below). If you would like to shorten the code, see the shorter code below first.

    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int[] offset = new int[s.length()];
        IntStream.range(1, s.length()).forEach(i -> offset[i] = s.charAt(i) == ' ' ? 1 : offset[i-1]-1);
        return IntStream.range(0, rows).reduce(0, (a, b) -> a + cols + offset[(a+cols) % s.length()]) / s.length();
    }
Explanation:

Say sentence=["abc", "de", "f], rows=4, and cols=6.
The screen should look like

"abc de"
"f abc "
"de f  "
"abc de"
Consider the following repeating sentence string, with positions of the start character of each row on the screen.

"abc de f abc de f abc de f ..."
 ^      ^     ^    ^      ^
 0      7     13   18     25
Our goal is to find the start position of the row next to the last row on the screen, which is 25 here. Since actually it's the length of everything earlier, we can get the answer by dividing this number by the length of (non-repeated) sentence string. Note that the non-repeated sentence string has a space at the end; it is "abc de f " in this example.

Here is how we find that position. In each iteration, we need to adjust start based on spaces either added or removed.

"abc de f abc de f abc de f ..." // start=0
 012345                          // start=start+cols+adjustment=0+6+1=7 (1 space removed in screen string)
        012345                   // start=7+6+0=13
              012345             // start=13+6-1=18 (1 space added)
                   012345        // start=18+6+1=25 (1 space added)
                          012345
Hope this helps.
*/

