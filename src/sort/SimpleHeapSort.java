package sort;

import util.Sort;
import util.SortHelper;

public class SimpleHeapSort implements Sort {
	public static void main(String[] args) {
		int len = 63000;
		
		Sort []sorts = 
			{		
					new MergeSort(),  
					new ThreeWaysQuickSort(), 
					new SimpleHeapSort(),
					new DWaysHeapSort(3)
					};
		
		SortHelper.testSortAlgorithm(sorts, len);
//		int [] arr = SortHelper.simpleRandomArray(10, 100);
		
//		new SimpleHeapSort().sort(arr);
		
	}
	@Override
	public void sort(int[] arr) {
		createMaxHeap(arr, arr.length);//��������һ���� o(n)
		for(int i = arr.length;i > 1;i--) {
			SortHelper.swap(arr, 0, i-1);//�����е��������ĩβ����,
			shiftDown(arr, 0, i-1);
		}
	}
	//����k Ԫ�� shiftDown    [0, len)
	private void shiftDown(int arr[], int k, int len) {
		int e = arr[k];
		while(2*k+1 < len) { //k ������
			int j = 2*k+1;//k Ӧ�ú�j ����λ��
			if(j+1 < len && arr[j+1] > arr[j]) {
				j += 1;
			}
			if(e < arr[j])
			{
				arr[k] = arr[j];
				k = j;
			}else {
				break;
			}
		}
		arr[k] = e;
	}
	
	
	//��  [0, len) �ڵ�Ԫ���������ѵĶ���
	// [0 , 10)
	//ʱ�临�Ӷ�Ϊ o(n) 
	private void createMaxHeap(int[]arr, int len) {
		/**
		 * ˼·:�����һ����Ҷ�ӽڵ㿪ʼ,ʹ���������Ѷ���
		 */
		for(int k = len/2-1;k >= 0;k--) {
			shiftDown(arr, k, len);
		}
	}
}
