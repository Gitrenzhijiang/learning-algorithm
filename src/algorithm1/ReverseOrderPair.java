package algorithm1;

import util.SortHelper;
import util.TimeUtils;

//��һ�������������
/**
 * [a,b][c,d]�ϲ����̿��Կ���  [a,b][c,d]����ϲ���������   �ٷֱ����[a,b],[c,d]  =res
 * 
 */
public class ReverseOrderPair {
	public static void main(String[] args) {
		int[] arr = SortHelper.simpleRandomArray(10000, 10000);
		TimeUtils.start();
		funNotGood(arr);
		TimeUtils.over(true);
		//
		TimeUtils.start();
		fun_merge(arr);
		TimeUtils.over(true);
	}
	//�������
	public static void funNotGood(int[]arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[i] > arr[j])
					count++;
			}
		}
		System.out.println("count:" + count);
	}
	//ʹ��merge �����㷨���
	public static void fun_merge(int[]arr) {
		System.out.println("res:" + fun_merge0(arr, 0, arr.length-1));
	}
	private static int fun_merge0(int[]arr, int left, int right) {
		if(left >= right)
			return 0;
		int mid = (left + right)/2;
		int part1 = fun_merge0(arr, left, mid);
		int part2 = fun_merge0(arr, mid+1, right);
		int count = 0;
		//�鲢����
		int[] iHelps = new int[right-left+1];
		for(int i = 0;i < iHelps.length;i++)
			iHelps[i] = arr[i+left];
		int aIndex = left, bIndex = mid+1;//������������
		
		for (int index = left; index <= right; index++) {
			if(aIndex > mid) {
				arr[index] = iHelps[bIndex-left];
				bIndex++;
			}else if(bIndex > right) {
				arr[index] = iHelps[aIndex-left];
				aIndex++;
			}else if(iHelps[bIndex-left] >= iHelps[aIndex-left]) {
				arr[index] = iHelps[aIndex-left];
				aIndex++;
			}else {//
				arr[index] = iHelps[bIndex-left];
				count += (mid-aIndex+1);
				bIndex++;
			}
		}
		return count+part1+part2;
	}
}
