/*
Happy Number [MEDIUM]

Any number will be called a happy number if, after repeatedly replacing it with a number
equal to the sum of the square of all of its digits, leads us to number '1'. All other
(not-happy) numbers will never reach '1'. Instead, they will be stuck in a cycle of numbers
which does not include '1'.

--- EXAMPLES ---

- EX 1 -
Input:          23
Output:         true (23 is a happy number)
Explanations:   Here are the steps to find out that 23 is a happy number:

                1. 2^2 + 3^2 = 4 + 9 = 13
                2. 1^2 + 3^2 = 1 + 9 = 10
                3. 1^0 + 0^2 = 1 + 0 = 1

- EX 2 -
Input:          12
Output:         false (12 is not a happy number)
Explanations:   Here are the steps to find out that 12 is not a happy number:

                1. 1^2 + 2^2 = 1 + 4 = 5
                2. 5^2 = 25
                3. 2^2 + 5^2 = 4 + 25 = 29
                4. 2^2 + 9^2 = 4 + 81 = 85
                5. 8^2 + 1^2 = 64 + 25 = 89
                6. 8^2 + 9^2 = 64 + 81 = 145
                7. 1^2 + 4^2 + 5^2 = 1 + 16 + 25 = 42
                8. 4^2 + 2^2 = 16 + 4 = 20
                9. 2^2 + 0^2 = 4 + 0 = 4
                10. 4^2 = 16
                11. 1^2 + 6^2 = 1 + 36 = 37
                12. 3^2 + 7^2 = 9 + 49 = 58
                13. 5^2 + 8^2 = 25 + 64 = 89

                Step '13' leads us back to step '5' as the number becomes equal to
                '89', this means that we can never reach '1', therefore, '12' is
                not a happy number.

*/

public class HappyNumber {

    /*
     * The idea here is pretty simple. Although we are not using a Linked List, we
     * still use the two pointer ideology. The nodes in our Linked List in this case
     * is the number after every iteration of happy number calculation.
     * 
     * Our slow pointer will do one iteration. Our fast pointer will do two
     * iterations. The "null" in our Linked List is the '1' that our number turns to
     * in the end.
     * 
     * We keep looping our numbers in a cycle until our slow pointer equals our fast
     * pointer. That is, until the two numbers are equal. At this point, our
     * pointers will either point to '1' or a certain number other than '1'. If it
     * is not one, this indicates a cycle and we return false.
     * 
     * If it is a '1', we return true because there is no cycle and our number is a
     * happy number.
     * 
     * Time Complexity: O(log N). This is a bit complicated. All unhappy numbers get
     * stuck in a cycle. The sequence behavior tells us:
     * 
     * 1. If the number N is <= 1000, then we reach the cycle or '1' in at most 1001
     * steps.
     * 
     * 2. For N > 1000, suppose the number has 'M' digits and the next number is
     * 'N1'. From Wikipedia, we know that the sum of squares of digits of 'N' is at
     * most 9^2M or 81M (when all digits of 'N' are '9').
     * 
     * This means:
     * 
     * 1. N1 < 81M
     * 
     * 2. As we know M = log(N + 1)
     * 
     * 3. Therefore: N1 < 81 * log(N + 1) => N1 = O(log N).
     * 
     * Space Complexity: O(1).
     */

    public static boolean find(int num) {
        // We make two pointers.
        int fastNum = num;
        int slowNum = num;

        do {
            // Calculate the happy number iteration twice for fast.
            fastNum = returnHappy(returnHappy(fastNum));
            // Calculate the happy number iteration once for slow.
            slowNum = returnHappy(slowNum);
            // If the two are equal, break out of loop.
        } while (slowNum != fastNum);

        // Check to see if slow pointer is 1. If so, return true.
        // Can also check fast pointer.
        return slowNum == 1;
    }

    // This is the helper function that we use to calculate the next number.
    public static int returnHappy(int num) {
        // We need to find the sum of the squares of its digits.
        // Initialize sum to 0.
        int sum = 0;
        // We reduce our number down until it reaches 0.
        while (num > 0) {
            // Grab the last digit of our number.
            int digit = num % 10;
            // Square this digit and add to accumulating sum.
            sum += digit * digit;
            // Get rid of the last digit by integer division by 10.
            num /= 10;
        }
        // Return the new number after one happy number iteration.
        return sum;
    }

    public static void main(String[] args) {
        // Samples.
        int s1 = 23;
        int s2 = 12;

        // Results.
        boolean r1 = find(s1);
        boolean r2 = find(s2);

        // Print.
        System.out.println(s1 + " is a happy number: " + r1);
        System.out.println(s2 + " is a happy number: " + r2);
    }
}
