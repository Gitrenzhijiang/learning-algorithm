package algorithm1;

import java.util.Random;

import sort.ThreeWaysQuickSort;
import util.SortHelper;
import util.TimeUtils;

//��һ�������N�����
/**
 * һ�㷽��:o(nlog(n)) ֱ��������� 
 * ���ÿ��������˼��,���ǿ�����o(n)���
 */
public class NumberN {

	public static void main(String[] args) {
		int[]arr = SortHelper.generateNearlyOrderedArray(100000, 10000);
		int res = 0;
		int n = 5600;
		
		TimeUtils.start();
		res = getNumberN(arr, n, 0, arr.length-1);
		TimeUtils.over(true);
		
		TimeUtils.start();
		res = getNumberN_2(arr, n);
		TimeUtils.over(true);
		
		//��Ȼ�ǿ�������,���ǻ��� nlogn �����ʱ�临�Ӷ�
		ThreeWaysQuickSort sort = new ThreeWaysQuickSort();
		TimeUtils.start();
		sort.sort(arr);
		TimeUtils.over(true);
	}
	public static int getNumberN_2(int[]arr, int n) {
		
		int left = 0, right = arr.length-1;
		while(true) {
			//�Ż�1:���ڸ߶���������
			SortHelper.swap(arr, left, left + new Random().nextInt(right-left+1));
			int e = arr[left];
			//partition �Ĺ��� ���һ���м���   [left+1, lt] < e  [gt,right] > e
			//    [lt+1, i) = e
			int lt = left, gt = right+1, i = left+1;
			while(i < gt) {
				if(arr[i] < e) {
					SortHelper.swap(arr, ++lt, i);
					i++;
				}else if(arr[i] > e) {
					SortHelper.swap(arr, --gt, i);
				}else {
					i++;
				}
			}
			SortHelper.swap(arr, left, lt);// [left,lt) < e; [lt, i)=e; [gt,right]>e
			//����lt, gt �ж��Ƿ�ó���
			if(n-1 < lt) {
				right = lt-1;
			}else if(n-1 >= gt) {
				left = gt;
			}else {//�ó���
				return arr[lt];
			}
		}
	}
	
	
	
	//�õ�һ�������n�����,n{1,2,3,4...arr.length}
	public static int getNumberN(int []arr, int n, int left, int right) {
		
		//�Ż�1:���ڸ߶���������
		SortHelper.swap(arr, left, left + new Random().nextInt(right-left+1));
		int e = arr[left];
		//partition �Ĺ��� ���һ���м���   [left+1, lt] < e  [gt,right] > e
		//    [lt+1, i) = e
		int lt = left, gt = right+1, i = left+1;
		while(i < gt) {
			if(arr[i] < e) {
				SortHelper.swap(arr, ++lt, i);
				i++;
			}else if(arr[i] > e) {
				SortHelper.swap(arr, --gt, i);
			}else {
				i++;
			}
		}
		SortHelper.swap(arr, left, lt);// [left,lt) < e; [lt, i)=e; [gt,right]>e
		//����lt, gt �ж��Ƿ�ó���
		if(n-1 < lt) {
			return getNumberN(arr, n, left, lt-1);
		}else if(n-1 >= gt) {
			return getNumberN(arr, n, gt, right);
		}else {//�ó���
			return arr[lt];
		}
		
	}
	
}
