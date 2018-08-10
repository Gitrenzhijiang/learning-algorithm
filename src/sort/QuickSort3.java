package sort;

import java.util.Random;

import util.Sort;
import util.SortHelper;

public class QuickSort3 implements Sort {

	@Override
	public void sort(int[] arr) {
		sort0(arr, 0, arr.length-1);
	}
	protected InsertSort insertSort = new InsertSort();
	protected Random random = new Random();
	private void sort0(int[] arr, int left, int right) {
		//���������Ż�
		if(right - left < 16) {
			insertSort.sort(arr, left, right);
			return;
		}
//		if(left >= right)return;
		int p = partition(arr, left, right);
		sort0(arr, left, p-1);
		sort0(arr, p+1, right);
	}
	//�����ܵ���p����left �� right���м�λ��
	private int partition(int[]arr, int left, int right) {
		//�Ż�1:���������Ը�ʱ,����ֽ�㲻ƽ��
		SortHelper.swap(arr, left, random.nextInt(right-left+1) + left);
		//�Ż�2: arr[left+1, i) <= e; arr(j, right] >= e  �����ִ����ظ�ʱ,�ܾ���ʹ�ֽ��ƽ��
		int i = left+1, j = right;
		int e = arr[left];
		while(true) {
			while(i <= right && arr[i] < e)
				i++;
			while(j >= left + 1 && arr[j] > e)
				j--;
			
			if(i > j)break;
			SortHelper.swap(arr, i, j);
			i++;
			j--;
		}
		SortHelper.swap(arr, left, j);
		return j;
	}
}
