var company = "Uber";

var items = [
    {
        "id": 10,
        "title": "Regular Expression Matching",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 76,
        "title": "Minimum Window Substring",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 291,
        "title": "Word Pattern II ",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 297,
        "title": "Serialize and Deserialize Binary Tree",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 146,
        "title": "LRU Cache",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 432,
        "title": "All O`one Data Structure",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 262,
        "title": "Trips and Users",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 699,
        "title": "Falling Squares",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 37,
        "title": "Sudoku Solver",
        "difficulty": "Hard",
        "done": 1,
        "again": 1,
        "description": "Backtracking. For each blank cell, try 1-9. 如果试的数字is valid, 那么继续solve, 不然试下一个数",
    },
    {
        "id": 140,
        "title": "Word Break II",
        "difficulty": "Hard",
        "done": 0,
        "description": "",
    },
    {
        "id": 23,
        "title": "Merge k Sorted Lists",
        "difficulty": "Hard",
        "done": 1,
        "description": "minHeap 存K个list head",
    },
    {
        "id": 39,
        "title": "Combination Sum",
        "difficulty": "Medium",
        "done": 1,
        "description": "BackTracking",
    },
    {
        "id": 17,
        "title": "Letter Combinations of a Phone Number",
        "difficulty": "Medium",
        "done": 1,
        "description": "两种方法，Iterative 和 DFS",
    },
    {
        "id": 49,
        "title": "Group Anagrams",
        "difficulty": "Medium",
        "done": 1,
        "description": "方法1, map key=sort string, 时间复杂度O(NKlogK), K是string平均长度\n"
                       + "方法2, count string里面char出现次数， count = int[26], map key=Arrays.toString(count), O(NK)",
    },
    {
        "id": 648,
        "title": "Replace Words",
        "difficulty": "Medium",
        "done": 1,
        "description": "Trie 存所有的root"
    },
    {
        "id": 249,
        "title": "Group Shifted Strings",
        "difficulty": "Medium",
        "done": 1,
        "description": "每个string shift to fisrt char is a, 作为key, 存入map",
    },
    {
        "id": 22,
        "title": "Generate Parentheses",
        "difficulty": "Medium",
        "done": 1,
        "description": "BackTracking, helper(String tmp, left, right, n, res)\n"
                        + "if (left < n) helper(tmp + (, left+1, right, n, res)\n"
                        + "if (left > right) helper(tmp + ), left, right+1, n, res)",
    },
    {
        "id": 54,
        "title": "Spiral Matrix",
        "difficulty": "Medium",
        "done": 1,
        "description": "4个变量，rowStart, rowEnd, colStart, colEnd",
    },
    {
        "id": 133,
        "title": "Clone Graph",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "Map, Queue. clone过的点都放map里, 如果遇到之前clone过的，不用再放queue里",
    },
    {
        "id": 534,
        "title": "Design TinyURL",
        "difficulty": "Medium",
        "done": 0,
        "description": "",
    },
    {
        "id": 8,
        "title": "String to Integer (atoi)",
        "difficulty": "Medium",
        "done": 0,
        "description": "",
    },
    {
        "id": 535,
        "title": "Encode and Decode TinyURL",
        "difficulty": "Medium",
        "done": 1,
        "description": "Map, key is tinyUrl, tinyUrl is generated randomly",
    },
    {
        "id": 636,
        "title": "Exclusive Time of Functions",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "stack 存之前剩下的fid, 记录上一个开始时间preime\n" +
                       "如果新遇到log是start, stack.peek的时间加上time - preTime, preTime = time\n" +
                       "如果新遇到log是end, stack.peek的时间加上time - preTime + 1, preTime = time + 1 (因为time是end time)",
    },
    {
        "id": 450,
        "title": "Delete Node in a BST",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "Once the node is found, have to handle the below 4 cases\n"
                       + "     * 1. node doesn’t have left or right - return null\n"
                       + "     * 2. node only has left subtree - return the left subtree\n"
                       + "     * 3. node only has right subtree - return the right subtree\n"
                       + "     * 4. node has both left and right - find the minimum value in the right subtree, set that value to the currently found node, then recursively delete the minimum value in the right subtree",
    },
    {
        "id": 208,
        "title": "Implement Trie (Prefix Tree)",
        "difficulty": "Medium",
        "done": 1,
        "description": "",
    },
    {
        "id": 186,
        "title": "Reverse Words in a String II ",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "1 reverse entire string. 2, reverse each word. 3 reverse last word",
    },
    {
        "id": 139,
        "title": "Word Break",
        "difficulty": "Medium",
        "done": 0,
        "description": "",
    },
    {
        "id": 254,
        "title": "Factor Combinations ",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "Backtracking方法, getFactors(n, start, tmp list, result list).\n"
                       + "i从start到n, if(n%i == 0), getFactors(n/i, start=i, tmp, result)\n"
                       + "初始start=2, 终止条件n=1. 注意只有tmp size > 1才放入result list, 以避免放入n本身",
    },
    {
        "id": 380,
        "title": "Insert Delete GetRandom O(1)",
        "difficulty": "Medium",
        "done": 1,
        "description": "ArrayList, and Map -(key is number, value is its index in array).\n"
                       + "When Insert, add to list end. When remove, move the number to list end, remove from end.",
    },
    {
        "id": 138,
        "title": "Copy List with Random Pointer",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "",
    },
    {
        "id": 36,
        "title": "Valid Sudoku",
        "difficulty": "Medium",
        "done": 1,
        "description": "boolean[][] rows = new boolean[9][9],\n" +
                        "boolean[][] cols, boolean[][] blocks\n" +
                        "blockIndex = i/3 * 3 + j/3",
    },
    {
        "id": 207,
        "title": "Course Schedule",
        "difficulty": "Medium",
        "done": 1,
        "description": "1, create count[] array and unlockMap\n"
                       + "2, count[i] == 0, queue.offer(i)\n"
                       + "3. while queue not empty, queue poll, unlockMap.get, count[j]--, count[j] == 0, queue.offer(j)",
    },
    {
        "id": 230,
        "title": "Kth Smallest Element in a BST",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "In order travser the tree",
    },
    {
        "id": 33,
        "title": "Search in Rotated Sorted Array",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "二分find smallest index in array, 就是数组rotate了多少. \n"
                       + "realMid = (mid + rotate) % size, 根据nums[realMid]移动 e = mid - 1/s = mid + 1",
    },
    {
        "id": 516,
        "title": "Longest Palindromic Subsequence",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "dp[i][j]: the longest palindromic subsequence's length of substring(i, j)\n"
                       + "dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)\n"
                       + "otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])",
    },
    {
        "id": 91,
        "title": "Decode Ways",
        "difficulty": "Medium",
        "done": 1,
        "description": "dp, 1. s.charAt(i) == 0, dp[i] = 0\n"
                       + "2. s.substring(i, i+2) <= 26, dp[i] = dp[i+1] + dp[i+2]\n"
                       + "3. dp[i] = dp[i+1]",
    },
    {
        "id": 78,
        "title": "Subsets",
        "difficulty": "Medium",
        "done": 1,
        "description": "BackTracking, helper(int[] nums, List<List<Integer>> res, List<Integer> tmp, int start)",
    },
    {
        "id": 337,
        "title": "House Robber III",
        "difficulty": "Medium",
        "done": 1,
        "description": "从上往下递归，每个点算偷这个点的钱 include，和不偷这个点的钱 exclude\n"
                       + "不改变TreeNode结构的话，就返回int[2], res[0] = include, res[1]=exlude \n"
                       + "每个点 include = left exclude + right exclude + this.val \n"
                       + "exclude = max(left include, left exclude) + max(right include, right exclude)",
    },
    {
        "id": 161,
        "title": "One Edit Distance ",
        "difficulty": "Medium",
        "done": 1,
        "description": "找到第一个不同的char，比较两个string的长度，有三种可能，1 replace, 2 delete char from s, 3 detele char from t ",
    },
    {
        "id": 373,
        "title": "Find K Pairs with Smallest Sums",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "minHeap 放Pair(i:nums1 index, j:nums2 index, sum), nums1[0] 跟nums2各个组成的Pair 放进minHeap\n"
                       + "每拿出一个min pair, 把nums1[i+1], nums2[j]组成的Pair 放入minHeap",
    },
    {
        "id": 692,
        "title": "Top K Frequent Words",
        "difficulty": "Medium",
        "done": 1,
        "description": "map存word count，minHeap存前k个most frequency word"
    },
    {
        "id": 24,
        "title": "Swap Nodes in Pairs",
        "difficulty": "Medium",
        "done": 1,
        "description": "dummyHead, prepre, pre, cur",
    },
    {
        "id": 735,
        "title": "Asteroid Collision",
        "difficulty": "Medium",
        "done": 1,
        "description": "从左往右扫，左边扫完地放stack里，右边新元素跟stack.peek() 比较。\n"
                       + "只有stack.peek>0 (向右移) && 新元素<0（向左移）才有可能碰撞",
    },
    {
        "id": 202,
        "title": "Happy Number",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 155,
        "title": "Min Stack",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 121,
        "title": "Best Time to Buy and Sell Stock",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 125,
        "title": "Valid Palindrome",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 242,
        "title": "Valid Anagram",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 266,
        "title": "Palindrome Permutation ",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 206,
        "title": "Reverse Linked List",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 104,
        "title": "Maximum Depth of Binary Tree",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 171,
        "title": "Excel Sheet Column Number",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 1,
        "title": "Two Sum",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 690,
        "title": "Employee Importance",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 13,
        "title": "Roman to Integer",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 290,
        "title": "Word Pattern",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    },
    {
        "id": 733,
        "title": "Flood Fill",
        "difficulty": "Easy",
        "done": 0,
        "description": "",
    }
];