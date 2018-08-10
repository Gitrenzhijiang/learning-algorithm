package sort;

import util.Sort;

public class MergeSort implements Sort {
/**
	�鲢����:
	���÷���˼��,�𼶵ݼ�        o(  Nlog(n) )
	
	�Ѷ���Ҫ���ںϲ��㷨��ʵ��
	�ٶȱȽϿ�,�����ں���������
 *
 */
	protected InsertSort insertSort = new InsertSort();
	private void merge(int[]arr, int left, int right) {
		
//		if(left >= right)return;  //��right-left С��һ����ֵʱѡ�ò�������(����������������е������ж���Ч)
		if(right - left <= 15) {
			insertSort.sort(arr, left, right);
			return;
		}
		
		int mid = (left+right)/2;//�����п��ܻ����������
		//���߷ֱ��õݹ�鲢����
		merge(arr, left, mid);
		merge(arr, mid+1, right);
		if(arr[mid] > arr[mid+1])
			merge0(arr, left, mid, right);
	}
	//��arr[left...mid]   arr[mid+1...right]�鲢
	protected void merge0(int []arr, int left, int mid, int right) {
		if(left >= right)
			return;
		int[] iHelps = new int[right-left+1];//��������
		for (int i = 0; i < iHelps.length; i++) {
			iHelps[i] = arr[left+i];
		}
		
		int aIndex = left, bIndex = mid + 1;
		for (int index = left; index <= right; index++) {
			if(aIndex > mid) {
				arr[index] = iHelps[bIndex-left];
				bIndex++;
			}else if(bIndex > right) {
				arr[index] = iHelps[aIndex-left];
				aIndex++;
			}else if(iHelps[aIndex-left] > iHelps[bIndex-left]) {
				arr[index] = iHelps[bIndex-left];
				bIndex++;
			}else {
				arr[index] = iHelps[aIndex-left];
				aIndex++;
			}
		}
	}
	@Override
	public void sort(int[] arr) {
		merge(arr,0, arr.length-1);
	}
}
