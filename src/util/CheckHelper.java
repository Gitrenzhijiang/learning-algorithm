package util;

import java.util.Arrays;


public class CheckHelper {
	
	//打印排序数组前后变化
	public static void testSortPrint(Sort sort, int test_len, boolean canSort) {
		int[]arr = SortHelper.simpleRandomArray(test_len, test_len);
		testSortPrint(sort, arr, canSort);
	}
	//打印排序数组前后变化
	public static void testSortPrint(Sort sort, int []arr, boolean canSort) {
		SortHelper.printArray(arr);
		sort.sort(arr);
		SortHelper.printArray(arr);
		if(canSort)
			System.out.println("canSort?"+CheckHelper.canSort(sort));
	}
	/**
	 * 测试该排序算法的正确性
	 * @param sort
	 * @return
	 */
	public static boolean canSort(Sort sort) {
		int n = 1000;//1000 长度的数组
		int num = 10;//验证次数
		final int TWO = 2;
		int[][][] arr = new int[num][TWO][];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = SortHelper.simpleRandomArrays(TWO, n, n-15);
			Arrays.sort(arr[i][0]);
			try {
				sort.sort(arr[i][1]);
			}catch(Exception e) {
				System.out.println("err in canSort():"+ e);
			}
			if(!isEquals(arr[i][0], arr[i][1])) {
				return false;
			}
		}
		return true;
		
	}
	//比较两个数组是否相等
	private static boolean isEquals(int[]arr1, int []arr2) {
		if(arr1.length != arr2.length)
			return false;
		for(int i = 0;i < arr1.length;i++) {
			if(arr1[i] != arr2[i])
				return false;
		}
		return true;
	}
}
