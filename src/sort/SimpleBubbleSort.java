package sort;

import util.Sort;
import util.SortHelper;

public class SimpleBubbleSort implements Sort {
/**
 * �򵥵�ð������
 * ͨ���𲽱Ƚϲ����������Ƚϳ���
 */
	@Override
	public void sort(int[] arr) {
		for(int i = 0;i < arr.length - 1;i++) {
			for(int j = 0;j < arr.length - 1 - i;j++) {
				if(arr[j] > arr[j+1]) {
					SortHelper.swap(arr, j, j+1);
				}
			}
		}
	}

}
