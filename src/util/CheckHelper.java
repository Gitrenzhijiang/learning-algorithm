package util;

import java.util.Arrays;


public class CheckHelper {
	
	//��ӡ��������ǰ��仯
	public static void testSortPrint(Sort sort, int test_len, boolean canSort) {
		int[]arr = SortHelper.simpleRandomArray(test_len, test_len);
		testSortPrint(sort, arr, canSort);
	}
	//��ӡ��������ǰ��仯
	public static void testSortPrint(Sort sort, int []arr, boolean canSort) {
		SortHelper.printArray(arr);
		sort.sort(arr);
		SortHelper.printArray(arr);
		if(canSort)
			System.out.println("canSort?"+CheckHelper.canSort(sort));
	}
	/**
	 * ���Ը������㷨����ȷ��
	 * @param sort
	 * @return
	 */
	public static boolean canSort(Sort sort) {
		int n = 1000;//1000 ���ȵ�����
		int num = 10;//��֤����
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
	//�Ƚ����������Ƿ����
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
