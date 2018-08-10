package util;

import java.util.Random;

public class SortHelper extends PrintHelper {
	/**
	 * ����һ���򵥵�����
	 * @param length ���鳤��
	 * @return
	 */
	public static int[] simpleArray(int length) {
		return new int[length];
	}
	/**
	 * ��һ������������������ֵ
	 * [left, right)
	 * @param arr
	 */
	public static void randomArray(int[] arr, int bound) {
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(bound);
		}
	}
	/**
	 * ����һ��ָ���������������ʼ������������
	 * @param length
	 * @return
	 */
	public static int[] simpleRandomArray(int length, int bound) {
		int[] arr = simpleArray(length);
		randomArray(arr, bound);
		return arr;
	}
	/**
	 * һ���������������,ֻ�и�����Ԫ���������
	 * @param n
	 * @param swapTimes
	 * @return
	 */
	public static int[] generateNearlyOrderedArray(int n, int swapTimes) {
		int[]arr = simpleArray(n);
		for(int i = 0;i < n;i++) {
			arr[i] = i;
		}
		Random random = new Random();
		for(int i = 0;i < swapTimes;i++) {
			swap(arr, random.nextInt(n), random.nextInt(n));
		}
		return arr;
	}
	/**
	 * �������ά����,ÿһ��һά���鶼����ͬ��
	 * @param arr_len ��ά���鳤��
	 * @param length  �г���
	 * @param bound �������[0-bound)֮��
	 * @return
	 */
	public static int[][] simpleRandomArrays(int arr_len, int length, int bound){
		Random random = new Random();
		int[][] arr = new int[arr_len][length];
		for(int i = 0;i < length;i++) {//��
			int temp = random.nextInt(bound);
			for(int j = 0;j < arr_len;j++) {
				arr[j][i] = temp;
			}
		}
		return arr;
	}
	/**
	 * ����һ����������Ķ�ά����
	 * @param arr_len ��ά���������
	 * @param n ����
	 * @param swapTimes һ�����ж��ٵĽ�����
	 * @return
	 */
	public static int[][] generateNearlyOrderedArrays(int arr_len, int n, int swapTimes){
		int[][] arr = new int[arr_len][n];
		for(int i = 0;i < arr_len;i++) {
			for(int j = 0;j < n;j++) {
				arr[i][j] = j;
			}
		}
		Random random = new Random();
		for(int i = 0;i < swapTimes;i++) {
			int r_x = random.nextInt(n);
			int r_y = random.nextInt(n);
			for(int j = 0;j < arr_len;j++)
				swap(arr[j], r_x, r_y);
		}
		return arr;
	}
	
	/**
	 * ����sort�����㷨�Ƿ���Ч
	 * @param sortName �����㷨����
	 * @param sort �����㷨
	 * @param arr ��������
	 */
	public static void testSortAlgorithm(String sortName, Sort sort, int[]arr) {
		if(!CheckHelper.canSort(sort))
		{
			System.out.println(sortName + " ʧ��!");
			return;
		}
		long startTime = System.nanoTime();
		sort.sort(arr);
		long time = (System.nanoTime() - startTime)/1000000;
		if(!isSorted(arr)) {
			System.out.println(sortName + " ʧ��!");
			return;
		}
		System.out.println(sortName + ": " + time + " ��s");
	}
	//����sort֮�� �ڸ������� �ĶԱ�,��ӡ���
	public static void testSortAlgorithm(Sort[] sorts, int n) {
		
		String [] strs = {"һ���������", "������������", "�����ظ�����"};
		int[][][] Arrays = new int[strs.length][sorts.length][];
		Arrays[0] = simpleRandomArrays(sorts.length, n, n-(n/1000));//һ���������
		Arrays[1] = generateNearlyOrderedArrays(sorts.length, n, (n/100)+5);//������������
		Arrays[2] = simpleRandomArrays(sorts.length, n, n/100+5);//�����ظ�����	
				
		for(int j = 0;j < Arrays.length;j++) {
			System.out.println(strs[j] + "(" + n + "):");
			for(int i = 0;i < sorts.length;i++) {
				Sort sort = sorts[i];
				testSortAlgorithm(sort.getClass().getSimpleName(), sort, Arrays[j][i]);
			}
		}
	}
	/**
	 * �����С���������򷵻�true,���򷵻�false
	 * @param arr
	 * @return
	 */
	public static boolean isSorted(int[]arr) {
		if(arr == null)return false;
		if(arr.length == 1)return true;
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i+1])
				return false;
		}
		return true;
	}
	/**
	 * ��������Ԫ��
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
}
