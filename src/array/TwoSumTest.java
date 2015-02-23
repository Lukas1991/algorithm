package array;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TwoSumTest {

	private void eval(int[] data, int target, int index1, int index2) {
		//ArrayList<ArrayList<Integer>> result = new TwoSum().twoSum(data);

		//int[] actual = new TwoSum().twoSum(data);
		//assertEquals(index1, actual[0]);
		//assertEquals(index2, actual[1]);
	}

	@Test
	public void testNull() {
		eval(null, 100, -1, -1);
	}

	@Test
	public void testEmpty() {
		eval(new int[] {}, 100, -1, -1);
	}

	public void myfunc(ArrayList<ArrayList<Integer>> actual,ArrayList<ArrayList<Integer>> expected) {
		
		assertEquals(expected.size(), actual.size());
		
		for(int i=0;i<actual.size();i++){
			
			assertEquals(actual.get(i).get(0),expected.get(i).get(0));
			assertEquals(actual.get(i).get(1),expected.get(i).get(1));
		}
		

	}

	@Test
	public void test1() {
		ArrayList<ArrayList<Integer>> actual = new ThreeSum().twoSum(new int[] {
				0,0},0);

		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		expected.add(new ArrayList<Integer>(Arrays.asList(0,0)));

		myfunc(actual, expected);
	}

	@Test
	public void test2() {
		
		ArrayList<ArrayList<Integer>> actual = new ThreeSum().twoSum(new int[] {
				-1, 0, 79, 50, 88, 345, 3 , -79,-50,-88,-345,79,-79},0);
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
				
		expected.add(new ArrayList<Integer>(Arrays.asList(-345, 345)));
		expected.add(new ArrayList<Integer>(Arrays.asList(-88, 88)));
		expected.add(new ArrayList<Integer>(Arrays.asList(-79, 79)));
		//expected.add(new ArrayList<Integer>(Arrays.asList(-79, 79)));
		expected.add(new ArrayList<Integer>(Arrays.asList(-50, 50)));
		myfunc(actual, expected);
		
	}

	@Test
	public void test3() {
		ArrayList<ArrayList<Integer>> actual = new ThreeSum().twoSum(new int[] {
				0,0,0,0,0,0,0},0);
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		expected.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
		
		
		myfunc(actual, expected);
		
		
		
		
	}

	@Test
	public void test4() {
		eval(new int[] { 230, 863, 916, 585, 981, 404, 316, 785, 88, 12, 70,
				435, 384, 778, 887, 755, 740, 337, 86, 92, 325, 422, 815, 650,
				920, 125, 277, 336, 221, 847, 168, 23, 677, 61, 400, 136, 874,
				363, 394, 199, 863, 997, 794, 587, 124, 321, 212, 957, 764,
				173, 314, 422, 927, 783, 930, 282, 306, 506, 44, 926, 691, 568,
				68, 730, 933, 737, 531, 180, 414, 751, 28, 546, 60, 371, 493,
				370, 527, 387, 43, 541, 13, 457, 328, 227, 652, 365, 430, 803,
				59, 858, 538, 427, 583, 368, 375, 173, 809, 896, 370, 789 },
				542, 29, 46);
	}

	@Test
	public void test5() {
		eval(new int[] { 591, 955, 829, 805, 312, 83, 764, 841, 12, 744, 104,
				773, 627, 306, 731, 539, 349, 811, 662, 341, 465, 300, 491,
				423, 569, 405, 508, 802, 500, 747, 689, 506, 129, 325, 918,
				606, 918, 370, 623, 905, 321, 670, 879, 607, 140, 543, 997,
				530, 356, 446, 444, 184, 787, 199, 614, 685, 778, 929, 819,
				612, 737, 344, 471, 645, 726 }, 789, 11, 56);
	}

	@Test
	public void test6() {
		eval(new int[] { 572, 815, 387, 418, 434, 530, 376, 190, 196, 74, 830,
				561, 973, 771, 640, 37, 539, 369, 327, 51, 623, 575, 988, 44,
				659, 48, 22, 776, 487, 873, 486, 169, 499, 82, 128, 31, 386,
				691, 553, 848, 968, 874, 692, 404, 463, 285, 745, 631, 304,
				271, 40, 921, 733, 56, 883, 517, 99, 580, 55, 81, 232, 971,
				561, 683, 806, 994, 823, 219, 315, 564, 997, 976, 158, 208,
				851, 206, 101, 989, 542, 985, 940, 116, 153, 47, 806, 944, 337,
				903, 712, 138, 236, 777, 630, 912, 22, 140, 525, 270, 997, 763,
				812, 597, 806, 423, 869, 926, 344, 494, 858, 519, 389, 627,
				517, 964, 74, 432, 730, 843, 673, 985, 819, 397, 607, 34, 948,
				648, 43, 212, 950, 235, 995, 76, 439, 614, 203, 313, 180, 760,
				210, 813, 920, 229, 615, 730, 359, 863, 678, 43, 293, 978, 305,
				106, 797, 769, 3, 700, 945, 135, 430, 965, 762, 479, 152, 121,
				935, 809, 101, 271, 428, 608, 8, 983, 758, 662, 755, 190, 632,
				792, 789, 174, 869, 622, 885, 626, 310, 128, 233, 82, 223, 339,
				771, 741, 227, 131, 85, 51, 361, 343, 641, 568, 922, 145, 256,
				177, 329, 959, 991, 293, 850, 858, 76, 291, 134, 254, 956, 971,
				718, 391, 336, 899, 206, 642, 254, 851, 274, 239, 538, 418, 21,
				232, 706, 275, 615, 568, 714, 234, 567, 994, 368, 54, 744, 498,
				380, 594, 415, 286, 260, 582, 522, 795, 261, 437, 292, 887,
				405, 293, 946, 678, 686, 682, 501, 238, 245, 380, 218, 591,
				722, 519, 770, 359, 340, 215, 151, 368, 356, 795, 91, 250, 413,
				970, 37, 941, 356, 648, 594, 513, 484, 364, 484, 909, 292, 501,
				59, 982, 686, 827, 461, 60, 557, 178, 952, 218, 634, 785, 251,
				290, 156, 300, 711, 322, 570, 820, 191, 755, 429, 950, 18, 917,
				905, 905, 126, 790, 638, 94, 857, 235, 889, 611, 605, 203, 859,
				749, 874, 530, 727, 764, 197, 537, 951, 919, 24, 341, 334, 505,
				796, 619, 492, 295, 380, 128, 533, 600, 160, 51, 249, 5, 837,
				905, 747, 505, 82, 158, 687, 507, 339, 575, 206, 28, 29, 91,
				459, 118, 284, 995, 544, 3, 154, 89, 840, 364, 682, 700, 143,
				173, 216, 290, 733, 525, 399, 574, 693, 500, 189, 590, 529,
				972, 378, 299, 461, 866, 326, 43, 711, 460, 426, 947, 391, 536,
				26, 579, 304, 852, 158, 621, 683, 901, 237, 22, 225, 59, 52,
				798, 262, 754, 649, 504, 861, 472, 480, 570, 347, 891, 956,
				347, 31, 784, 581, 668, 127, 628, 962, 698, 191, 313, 714, 893 },
				101, 84, 240);
	}

	@Test
	public void test7() {
		eval(new int[] { 438, 507, 629, 255, 813, 423, 536, 428, 340, 767, 208,
				808, 882, 142, 835, 423, 331, 545, 627, 750, 397, 675, 662, 92,
				465, 627, 15, 522, 395, 727, 561, 73, 570, 606, 826, 651, 743,
				214, 881, 685, 820, 326, 653, 334, 698, 604, 938, 260, 51, 597,
				291, 855, 427, 117, 943, 142, 166, 439, 833, 4, 180, 10, 531,
				350, 785, 989, 607, 303, 554, 764, 769, 451, 654, 752, 15, 90,
				505, 159, 1, 516, 801, 938, 442, 543, 761, 548, 523, 766, 445,
				696, 217, 352, 333, 383, 868, 764, 556, 943, 280, 140, 627,
				870, 635, 753, 743, 978, 611, 326, 405, 143, 564, 256, 304,
				913, 570, 331, 340, 222, 952, 959, 535, 113, 148, 125, 874,
				354, 984, 753, 423, 448, 235, 621, 796, 355, 64, 682, 326, 500,
				609, 293, 566, 974, 808, 568, 729, 173, 735, 764, 987, 588,
				227, 961, 621, 340, 245, 570, 640, 814, 635, 482, 520, 563,
				695, 399, 95, 92, 813, 135, 342, 513, 410, 943, 64, 458, 801,
				835, 977, 932, 838, 604, 500, 266, 395, 108, 788, 161, 769,
				662, 697, 167, 143, 383, 880, 19, 758, 552, 396, 226, 548, 560,
				916, 766, 568, 192, 100, 734, 639, 288, 187, 465, 345, 535,
				293, 130, 488, 172, 108, 313, 800, 662, 644, 758, 843, 953, 73,
				543, 630, 37, 711, 372, 372, 410, 60, 973, 750, 833, 827, 572,
				31, 157, 473, 529, 410, 650, 930, 635, 253, 203, 159, 431, 848,
				969, 982, 229, 504, 617, 273, 723, 854, 572, 879, 382, 18, 418,
				990, 321, 871, 544, 662, 900, 340, 358, 788, 998, 376, 87, 520,
				543, 717, 67, 713, 419, 108, 796, 143, 342, 896, 526, 926, 362,
				23, 63, 426, 514, 526, 499, 857, 87, 499, 635, 496, 69, 558,
				635, 1, 635, 125, 611, 459, 11, 14, 305, 969, 225, 677, 354,
				90, 509, 317, 895, 617, 798, 210, 830, 63, 488, 139, 500, 744,
				564, 295, 782, 214, 569, 863, 835, 14, 70, 687, 321, 130, 933,
				822, 761, 985, 637, 995, 604, 932, 54, 51, 170, 951, 774, 117,
				117, 863, 940, 599, 820, 473, 350, 572, 776, 198, 958, 1, 281,
				539, 874, 38, 205, 591, 901, 588, 743, 926, 270, 675, 135, 208,
				588, 438, 444, 752, 172, 709, 191, 199, 494, 743, 854, 362,
				340, 170, 909, 434, 258, 621, 983, 119, 37, 281, 987, 53, 486,
				709, 824, 817, 590, 632, 798, 207, 691, 592, 358, 92, 316, 864,
				345, 488, 408, 172, 392, 402, 188, 880, 336, 350, 739, 301,
				953, 314, 414, 261, 701, 431, 984, 350, 800, 476, 370, 952,
				580, 585, 335, 14, 486, 448, 710, 637, 134, 665, 166, 631, 526,
				877, 940, 492, 855, 563, 417, 517, 543, 496, 979, 802, 100,
				100, 854, 706, 350, 796, 140, 46, 434, 811, 543, 743, 614, 838,
				803, 583, 280, 748, 124, 570, 134, 18, 282, 879, 324, 142, 904,
				861, 884, 974, 630, 319, 340, 130, 954, 130, 199, 681, 465,
				295, 597, 492, 893, 552, 920, 370, 221, 293, 26, 632, 695, 967,
				372, 321, 723, 907, 701, 733, 520, 482, 927, 11, 606, 466, 404,
				529, 402, 166, 646, 60, 802, 101, 822, 93, 12, 336, 136, 650,
				539, 640, 791, 108, 997, 268, 833, 562, 132, 423, 879, 884,
				362, 374, 522, 188, 496, 472, 531, 434, 942, 46, 528, 423, 504,
				664, 867, 467, 685, 899, 884, 746, 548, 937, 296, 436, 295,
				486, 722, 288, 465, 89, 736, 900, 174, 497, 37, 84, 817, 411,
				486, 387, 949, 893, 187, 776, 771, 617, 175, 516, 705, 296,
				452, 268, 6, 922, 402, 991, 723, 609, 98, 347, 588, 554, 80,
				169, 197, 931, 19, 597, 888, 164, 347, 27, 916, 378, 630, 321,
				644, 156, 362, 4, 705, 164, 465, 543, 806, 282, 827, 448, 670,
				950, 458, 388, 239, 744, 507, 516, 414, 459, 934, 130, 924,
				440, 166, 788, 343, 379, 884, 758, 808, 912, 187, 706, 419, 88,
				355, 996, 331, 777, 500, 863, 796, 627, 124, 535, 561, 466,
				370, 101, 383, 813, 44 }, 929, 413, 584);
	}

	@Test
	public void test8() {
		eval(new int[] { 217, 231, 523, 52, 547, 243, 648, 509, 415, 149, 689,
				710, 265, 187, 370, 56, 977, 182, 400, 329, 471, 805, 955, 989,
				255, 766, 38, 566, 79, 843, 295, 229, 988, 108, 781, 619, 704,
				542, 335, 307, 359, 907, 727, 959, 161, 699, 123, 650, 147,
				459, 657, 188, 304, 268, 405, 685, 620, 721, 351, 570, 899, 60,
				388, 771, 24, 659, 425, 440, 508, 373, 32, 645, 409, 272, 356,
				175, 533, 740, 370, 152, 34, 510, 745, 251, 227, 494, 258, 527,
				817, 773, 178, 194, 860, 387, 627, 851, 449, 736, 15, 212, 529,
				950, 316, 28, 65, 484, 968, 63, 4, 643, 795, 669, 203, 677,
				139, 636, 289, 555, 430, 849, 150, 493, 301, 377, 240, 873,
				965, 441, 230, 349, 447, 470 }, 718, 28, 80);
	}

	@Test
	public void test9() {
		eval(new int[] { 876, 879, 155, 291, 431, 296, 592, 965, 502, 173, 869,
				504, 258, 342, 192, 478, 270, 341, 811, 794, 472, 625, 229,
				829, 122, 858, 738, 481, 102, 946, 305, 399, 216, 752, 413,
				352, 271, 193, 534, 508, 152, 989, 154, 456, 168, 510, 391, 28,
				317, 409, 609, 532, 784, 160, 696, 105, 245, 231, 20, 17, 81,
				781, 79, 816, 918, 838, 123, 602, 338, 997, 192, 947, 388, 515,
				510, 441, 175, 539, 708, 980, 207, 336, 524, 610, 3, 427, 282,
				84, 953, 855, 117, 737, 288, 371, 623, 484, 738, 874, 426, 202,
				481, 132, 499, 500, 89, 786, 276, 221, 857, 398, 242, 639, 771,
				149, 758, 775, 39, 836, 70, 903, 193, 959, 169, 851, 798, 815,
				755, 498, 308, 70, 217, 765, 504, 498, 56, 547, 578, 977, 882,
				909, 9, 874, 223, 39, 429, 982, 129, 712, 77, 996, 43, 613,
				800, 810, 73, 993, 763, 978, 912, 255, 468, 937, 987, 701, 155,
				347, 980, 147, 698, 41, 353, 178, 396, 241, 71, 482, 40, 593,
				993, 959, 193, 544, 376, 752, 804, 194, 800, 837, 673, 261,
				348, 963, 918, 217, 945, 271, 493, 538, 203, 54, 850, 753, 954,
				312, 584, 399, 504, 62, 124, 790, 542, 239, 662, 410, 12, 362,
				798, 726, 798, 780, 785, 737, 280, 931, 452, 643, 362, 190,
				975, 520, 219, 330, 290, 451, 22, 756, 837, 787, 758, 661, 75,
				697, 419, 485, 290, 84, 401, 447, 400, 311, 121, 216, 574, 724,
				733, 496, 680, 831, 736, 43, 578, 201, 109, 197, 125, 66, 739,
				339, 925, 148, 381, 513, 152, 305, 603, 516, 979, 133, 993,
				430, 167, 826, 526, 290, 562, 559, 947, 448, 903, 289, 259,
				221, 915, 71, 879, 639, 390, 588, 496, 430, 778, 722, 421, 821,
				436, 621, 959, 728, 81, 117, 202, 17, 408, 829, 438, 970, 93,
				738, 838, 902, 248, 128, 903, 800, 567, 829, 3, 407, 306, 773,
				71, 323, 492, 305, 301, 28, 220, 455, 320, 478, 873, 483, 521,
				260, 460, 342, 846, 577, 874, 530, 588, 965, 985, 606, 410,
				443, 662, 81, 667, 27, 912, 602, 957, 822, 164, 489, 942, 414,
				549, 991, 747, 680, 498, 831, 805, 89, 846, 467, 909, 7, 651,
				250, 534, 984, 587, 348, 150, 329, 194, 20, 519, 250, 232, 224,
				378, 539, 83, 177, 872, 130, 419, 387, 654, 917, 259, 447, 979,
				184, 965, 51, 349, 422, 983, 682, 172, 177, 177, 484, 652, 930,
				495, 65, 511, 318, 621, 297, 803, 476, 370, 826, 328, 150, 354,
				393, 900, 340, 73, 781, 70, 260, 293, 862, 335, 395, 51, 326,
				363, 78, 968, 446, 565, 683, 654, 767, 719, 324, 2, 617, 451,
				56, 789, 464, 119, 53, 269, 369, 137, 612, 54, 217, 719, 823,
				601, 663, 310, 594, 301, 636, 22, 333, 351, 126, 810, 812, 827,
				634, 441, 534, 434, 967, 637, 795, 335, 965, 876, 778, 987,
				217, 451, 264, 341, 566, 656, 612, 413, 682, 429, 161, 801,
				167, 309, 846, 754, 541, 9, 711, 707, 848, 989, 580, 20, 431,
				163, 252, 200, 54, 56, 666, 425, 592, 513, 230, 894, 20, 260,
				282, 297, 129, 414, 326, 577, 184, 698, 620, 138, 131, 236,
				848, 995, 879, 354, 107, 67, 92, 260, 531, 757, 640, 305, 848,
				959, 416, 109, 513, 769, 131, 501, 197, 225, 358, 67, 663, 761,
				742, 83, 648, 230, 59, 873, 231, 228, 470, 503, 615, 245, 258,
				84, 832, 132, 156, 324, 27, 583, 766, 676, 130, 978, 306, 387,
				733, 592, 763, 592, 487, 504, 493, 139, 897, 290, 432, 976,
				946, 24, 586, 104, 648, 333, 2, 359, 166, 968, 990, 39, 353,
				376, 839, 9, 75, 874, 203, 762, 489, 21, 14, 888, 570, 449,
				539, 772, 919, 697, 883, 278, 18, 151, 113, 148, 330, 158, 772,
				852, 93, 288, 213, 299, 338, 297, 862, 371, 708, 815, 108, 326,
				115, 923, 541, 144, 521, 441, 99, 773, 950, 519, 948, 258, 328,
				624, 936, 681, 935, 328, 70, 826, 110, 153, 236, 191, 222, 340,
				653, 918, 976, 857, 184, 193, 397, 39, 190, 147, 763, 760, 95,
				917, 559, 529, 680, 376, 389, 215, 705, 586, 205, 653, 324,
				960, 33, 404, 888, 680, 95, 263, 860, 150, 683, 930, 588, 9,
				690, 919, 745, 815, 331, 425, 879, 648, 398, 2, 997, 865, 429,
				399, 264, 704, 699, 333, 126, 753, 565, 529, 35, 520, 94, 401,
				552, 592, 543, 864, 23, 764, 763, 51, 631, 348, 198, 255, 73,
				281, 996, 371, 23, 581, 84, 367, 469, 604, 716, 393, 942, 764,
				239, 502, 501, 973, 438, 760, 398, 158, 853, 178, 348, 659,
				1000, 739, 296, 444, 587, 528, 355, 867, 615, 847, 885, 160,
				357, 618, 959, 330, 82, 182, 59, 224, 355, 250, 270, 447, 534,
				97, 590, 284, 909, 406, 954, 419, 909, 158, 626, 818, 350, 994,
				609, 540, 957, 152, 827, 830, 386, 380, 318, 580, 853, 440,
				789, 432, 710, 955, 381, 241, 930, 880, 632, 750, 876, 189,
				662, 127, 434, 38, 144, 20, 424, 27, 466, 538, 158, 416, 508,
				990, 650, 698, 990, 970, 663, 121, 9, 713, 489, 977, 530, 694,
				141, 930, 169, 695, 305, 567, 368, 777, 442, 668, 746, 618, 86,
				592, 185, 328, 772, 213, 644, 440, 178, 243, 774, 467, 991,
				455, 404, 919, 197, 830, 568, 661, 826, 841, 695, 52, 982, 515,
				47, 47, 198, 9, 272, 425, 975, 472, 9, 302, 338, 470, 542, 247,
				492, 367, 180, 708, 521, 592, 58, 572, 887, 670, 314, 191, 280,
				256, 845, 971, 157, 725, 862, 452, 76, 200, 538, 44, 324, 992,
				459, 196, 18, 64, 147, 423, 187, 191, 246, 305, 973, 802, 832,
				436, 444, 242, 979, 351, 733, 459, 825, 833, 691, 372, 861,
				617, 618, 190, 57, 848, 527, 56, 378, 533, 308, 430, 473, 701,
				401, 871, 790, 459, 216, 983, 305, 61, 391, 251, 447, 661, 951,
				150, 28, 572, 206, 299, 477, 703, 301, 227, 960, 866, 450, 335,
				337, 852, 906, 956, 873, 893, 867, 196, 131, 456, 608, 688,
				840, 569, 91, 922, 606, 961, 906, 836, 168, 838, 91, 607, 186,
				754, 708, 477, 248, 138, 211, 458, 17, 509, 645, 629, 816, 47,
				185, 661, 856, 508, 984, 320, 763, 297, 9, 446, 970, 472, 12,
				386, 476, 686, 940, 387, 721, 546, 206, 110, 349, 88, 781, 150,
				308, 136, 809, 670, 291, 767, 889, 926, 999, 832, 462, 706, 13,
				9, 753, 458, 309, 984, 404, 801, 366, 56, 611, 38, 691, 174,
				670, 306, 229, 12, 151, 697, 415, 180, 655, 418, 975, 781, 40,
				448, 625, 775, 722, 350, 163, 397, 634, 102, 961, 322, 354,
				836, 652, 877, 997, 397, 957, 640, 70, 467, 976, 901, 792, 173,
				869, 248, 829, 919, 89, 324, 9, 639, 560, 744, 890, 846, 452,
				197, 558, 756, 988, 771, 573, 494, 64, 423, 348, 296, 587, 327,
				909, 371, 24, 369, 174, 132, 197, 412, 142, 257, 790, 770, 171,
				875, 724, 608, 329, 256, 626, 868, 22, 311, 499, 933, 173, 78,
				631, 931, 191, 132, 970, 194, 778, 33, 832, 75, 76, 63, 271,
				905, 164, 970, 716, 216, 828, 56, 131, 898, 565, 791, 47, 634,
				205, 118, 280, 605, 896, 433, 38, 39, 303, 242, 746, 673, 541,
				759, 588, 990, 586, 244, 152, 586, 371, 666, 361, 691, 815,
				658, 537, 371, 482, 656, 117, 316, 327, 368, 657, 848, 557,
				761, 221, 147, 673, 945, 914, 976, 579, 804, 405, 182, 89, 429,
				133, 485, 939, 586, 452, 20, 292, 108, 747, 188, 899, 293, 125,
				976, 573, 162, 592, 880, 241, 685, 191, 539, 361, 430, 84, 791,
				903, 475, 96, 388, 485, 416, 583, 944, 939, 987, 939, 545, 474,
				272, 494, 664, 543, 480, 812, 212, 400, 728, 28, 379, 410, 127,
				607, 59, 614, 883, 509, 695, 765, 533, 665, 754, 848, 268, 159,
				678, 807, 325, 125, 92, 208, 216, 337, 697, 778, 466, 861, 22,
				950, 74, 804, 925, 617, 159, 73, 676, 712, 558, 487, 711, 774,
				383, 817, 737, 555, 811, 304, 743, 27, 67, 535, 426, 766, 615,
				102, 437, 765, 291, 718, 641, 951, 255, 375, 442, 204, 108,
				455, 592, 364, 457, 758, 486, 593, 780, 277, 789, 323, 404,
				473, 258, 953, 318, 898, 555, 390, 727, 510, 783, 427, 806, 92,
				33, 474, 858, 851, 783, 12, 752, 356, 942, 307, 235, 397, 915,
				502, 939 }, 28, 380, 633);
	}

	@Test
	public void test10() {
		eval(new int[] { 448, 74, 41, 680, 719, 173, 774, 492, 636, 199, 362,
				792, 74, 647, 587, 678, 261, 874, 467, 462, 735, 582, 999, 479,
				34, 294, 702, 834, 239, 853, 349, 142, 690, 81, 324, 188, 813,
				931, 502, 707, 967, 895, 445, 878, 426, 430, 93, 255, 304, 928,
				960, 192, 452, 211, 495, 787, 328, 568, 313, 194, 608, 990,
				944, 256, 204, 616, 44, 991, 461, 59, 854, 348, 611, 535, 459,
				724, 213, 683, 777, 885, 460, 751, 450, 918, 806, 395, 454,
				603, 57, 655, 110, 542, 24, 82, 777, 395, 877, 229, 550, 685,
				142, 845, 139, 804, 353, 111, 599, 114, 679, 728, 82, 137, 274,
				490, 340, 244, 880, 982, 281, 852, 568, 428, 474, 348, 487,
				432, 749, 586, 983, 402, 386, 210, 300, 864, 29, 980, 715, 911,
				738, 375, 688, 515, 374, 945, 513, 392, 999, 214, 658, 920,
				695, 391, 880, 600, 916, 235, 384, 763, 943, 399, 958, 826,
				663, 844, 733, 461, 226, 616, 536, 246, 155, 83, 732, 940, 392,
				176, 806, 925, 377, 824, 465, 175, 342, 291, 113, 186, 410,
				490, 170, 32, 701, 162, 471, 742, 297, 791, 541, 243, 885, 603,
				292, 933, 948, 326, 894, 686, 316, 341, 119, 610, 276, 463,
				883, 849, 854, 682, 304, 737, 99, 760, 411, 426, 445, 682, 794,
				541, 147, 520, 576, 644, 727, 499, 369, 222, 226, 836, 354, 53,
				996, 868, 37, 42, 293, 271, 452, 181, 219, 125, 943, 149, 591,
				599, 972, 961, 351, 545, 928, 922, 376, 917, 621, 777, 844,
				655, 151, 881, 125, 877, 258, 291, 566, 76, 58, 18, 692, 815,
				448, 224, 286, 191, 110, 655 }, 74, 190, 242);
	}

}
