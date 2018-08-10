package sort;

import util.Sort;

public class InsertSort implements Sort {
/**
 * ��������
 * ��ʼ״̬Ϊ��һ��Ԫ������
 * �������Ԫ�ز��뵽����������
 * 
 * �Ż�1:�����swap�滻�ɸ�ֵ
 * 
 */
	/*
	@Override
	public void sort(int[] arr) {
		for(int i = 1;i < arr.length;i++) {
			int temp = arr[i];
			boolean isPut = false;
			for(int j = i;j > 0;j--) {
				if(temp < arr[j-1]) {
					arr[j] = arr[j-1];
				}else {
					arr[j] = temp;
					isPut = true;
				}
			}
			if(!isPut) {
				arr[0] = temp;
			}
		}
	}
	*/
	@Override
	public void sort(int[] arr) {
		for(int i = 1;i < arr.length;i++) {
			int temp = arr[i];
			int j = i;
			for(;j > 0 && arr[j-1] > temp;j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}
	
	public void sort(int[] arr, int left, int right) {
		for(int i = left+1;i <= right;i++) {
			int temp = arr[i];
			int j = i;
			for(;j > left && arr[j-1] > temp;j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}
}
