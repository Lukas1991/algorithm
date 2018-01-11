var company = "Amazon";

var items = [
    {
        "id": 42,
        "title": "Trapping Rain Water",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 675,
        "title": "Cut Off Trees for Golf Event",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 711,
        "title": "Number of Distinct Islands II",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 239,
        "title": "Sliding Window Maximum",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 460,
        "title": "LFU Cache",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 297,
        "title": "Serialize and Deserialize Binary Tree",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 23,
        "title": "Merge k Sorted Lists",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 126,
        "title": "Word Ladder II",
        "difficulty": "Hard",
        "done": 1,
        "again": 1,
        "description": "双向BFS，startSet, endSet\n" +
                    "\t * build map, 如果是从start to end 方向，key is word in startSet, value is transformed word\n" +
                    "\t * 如果是从end to start方向，key is tranformed word\n" +
                    "\t * 从map 再generate result list, DFS."
    },
    {
        "id": 127,
        "title": "Word Ladder",
        "difficulty": "Medium",
        "done": 1,
        "again": 1,
        "description": "双向BFS，start queue, end queue， visited\n" +
                        "* 先BFS start queue, if end queue contains the transformed word, then that's the answer.\n" +
                        "* 不存在的话，放到next queue里面，visited.add\n" +
                        "* 比较next queue和end queue，小的作为下一次的start queue, 另一个是end queue."
    },
    {
        "id": 517,
        "title": "Super Washing Machines",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 146,
        "title": "LRU Cache",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 579,
        "title": "Find Cumulative Salary of an Employee",
        "difficulty": "Hard",
        "done": 0,
        "description": ""
    },
    {
        "id": 17,
        "title": "Letter Combinations of a Phone Number",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 138,
        "title": "Copy List with Random Pointer",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 238,
        "title": "Product of Array Except Self",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 200,
        "title": "Number of Islands",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 2,
        "title": "Add Two Numbers",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 380,
        "title": "Insert Delete GetRandom O(1)",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 449,
        "title": "Serialize and Deserialize BST",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 240,
        "title": "Search a 2D Matrix II",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 98,
        "title": "Validate Binary Search Tree",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 49,
        "title": "Group Anagrams",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 139,
        "title": "Word Break",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 215,
        "title": "Kth Largest Element in an Array",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 236,
        "title": "Lowest Common Ancestor of a Binary Tree",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 3,
        "title": "Longest Substring Without Repeating Characters",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 5,
        "title": "Longest Palindromic Substring",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 725,
        "title": "Split Linked List in Parts",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 48,
        "title": "Rotate Image",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 692,
        "title": "Top K Frequent Words",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 186,
        "title": "Reverse Words in a String II",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 529,
        "title": "Minesweeper",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 102,
        "title": "Binary Tree Level Order Traversal",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 15,
        "title": "3Sum",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 78,
        "title": "Subsets",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 535,
        "title": "Encode and Decode TinyURL",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 545,
        "title": "Boundary of Binary Tree",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 73,
        "title": "Set Matrix Zeroes",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 516,
        "title": "Longest Palindromic Subsequence",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 199,
        "title": "Binary Tree Right Side View",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 8,
        "title": "String to Integer (atoi)",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 451,
        "title": "Sort Characters By Frequency",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 508,
        "title": "Most Frequent Subtree Sum",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 89,
        "title": "Gray Code",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 355,
        "title": "Design Twitter",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 553,
        "title": "Optimal Division",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 536,
        "title": "Construct Binary Tree from String",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 534,
        "title": "Design TinyURL",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 537,
        "title": "Complex Number Multiplication",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 662,
        "title": "Maximum Width of Binary Tree",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 663,
        "title": "Equal Tree Partition",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 640,
        "title": "Solve the Equation",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 694,
        "title": "Number of Distinct Islands",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 646,
        "title": "Maximum Length of Pair Chain",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 742,
        "title": "Closest Leaf in a Binary Tree",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 396,
        "title": "Rotate Function",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 738,
        "title": "Monotone Increasing Digits",
        "difficulty": "Medium",
        "done": 0,
        "description": ""
    },
    {
        "id": 121,
        "title": "Best Time to Buy and Sell Stock",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 204,
        "title": "Count Primes",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 155,
        "title": "Min Stack",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 538,
        "title": "Convert BST to Greater Tree",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 414,
        "title": "Third Maximum Number",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 189,
        "title": "Rotate Array",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 167,
        "title": "Two Sum II - Input array is sorted",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 532,
        "title": "K-diff Pairs in an Array",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 119,
        "title": "Pascal's Triangle II",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 606,
        "title": "Construct String from Binary Tree",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 387,
        "title": "First Unique Character in a String",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 1,
        "title": "Two Sum",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 617,
        "title": "Merge Two Binary Trees",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 21,
        "title": "Merge Two Sorted Lists",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 682,
        "title": "Baseball Game",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 661,
        "title": "Image Smoother",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 20,
        "title": "Valid Parentheses",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 206,
        "title": "Reverse Linked List",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 160,
        "title": "Intersection of Two Linked Lists",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 645,
        "title": "Set Mismatch",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 438,
        "title": "Find All Anagrams in a String",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 459,
        "title": "Repeated Substring Pattern",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 235,
        "title": "Lowest Common Ancestor of a Binary Search Tree",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 234,
        "title": "Palindrome Linked List",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 242,
        "title": "Valid Anagram",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 746,
        "title": "Min Cost Climbing Stairs",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
    {
        "id": 141,
        "title": "Linked List Cycle",
        "difficulty": "Easy",
        "done": 0,
        "description": ""
    },
];