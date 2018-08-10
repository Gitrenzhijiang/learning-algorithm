package sort;

import java.util.Arrays;

import heap.IndexMinHeap;
import heap.MinHeap;
import util.CheckHelper;
import util.Sort;
import util.SortHelper;
/**
 * d  ·�鲢����
 * ˼����Խ��
 * ʵ�ַ�ʽǷ��
 * @author renzhijiang
 *
 */
public class DWaysMergeSort implements Sort {
	public static void main(String[] args) {
		int len = 100000;
		
		Sort []sorts = 
			{
					new MergeSort(),
					new DWaysMergeSort(5),
					new ThreeWaysQuickSort(), 
					new SimpleHeapSort()
			};
		SortHelper.testSortAlgorithm(sorts, len);
		
	}
	public DWaysMergeSort() {
	}
	public DWaysMergeSort(int d) {
		if(d < 2)
			throw new IllegalArgumentException(""+d);
		this.d = d;
	}
	@Override
	public void sort(int[] arr) {
		if(arr.length > 1 && arr.length < d) {
			throw new RuntimeException("data error");
		}
		//��ʼ����С�ѵ�����
		minheap = new IndexMinHeap(arr.length);
		dwaysMergeSort0(arr, 0, arr.length-1);
	}
	// [left, right] �ֳ� d ���� , ÿ���ηֱ���й鲢����֮��,�ٺϲ�d����
	private void dwaysMergeSort0(int[]arr, int left, int right) {
		if(left >= right)
			return;
//		if(right - left < d - 1){
//			insertSort.sort(arr, left, right);
//			return;
//		}
		if(right - left < Math.max(d - 1, 15)){ // ���������Ż�
			insertSort.sort(arr, left, right);
			return;
		}
		int div = d;
		//ÿһ�ζ����� �鲢����
		int a = left;//  [a, b]ΪÿһС��
		int[] aux = new int[d];// ���ÿһ�ε�һ��Ԫ������
		for(int i = 0;i < d;i++) {
			aux[i] = a;//Ϊ�˺��淽��
			int b = (right- a + 1)/(div-i);
			b = (right- a + 1)%(div-i) == 0? b+a-1 : b+a;
			dwaysMergeSort0(arr, a, b);
			a = b+1;
		}
		//[left, right] �ϲ�
		//��������εĵ�һ��Ԫ��
		int copy[] = copyArr(arr, left, right);
		
		for(int i = 0;i < d;i++) {
			minheap.insert(aux[i], copy[aux[i]-left]);//copy��һ��left��ƫ��
		}
		int index = left;
		while(!minheap.isEmpty()) {
			int e_index = minheap.extractionIndex();
			arr[index++] = copy[e_index-left];
			//�ж�e_index �����ڵķֶ�,������÷ֶ�(�������)����һ��Ԫ��
			int nextIndex = getNextIndex(aux, right, e_index);
			if(nextIndex != -1) {
				minheap.insert(nextIndex, copy[nextIndex-left]);
			}
		}
	}
	//���� arr[left,right] --> copy[0, right-left]
	private int[] copyArr(int[]arr, int left, int right) {
		int[] copy = new int [right-left+1];
		for(int i = left;i <= right;i++) {
			copy[i-left] = arr[i];
		}
		return copy;
	}
	//���ص�ǰ�ֶε���һ��Ԫ�ص�����,���û����,����-1
	private int getNextIndex(int[] aux, int right, int curIndex) {
		for(int i = 0;i < aux.length;i++) {
			if(curIndex >= aux[i]) {
				int b = i < aux.length-1?aux[i+1]-1:right;
				if(curIndex <= b && curIndex+1 <= b) {
					return curIndex+1;
				}
			}
		}
		return -1;
	}
	private int d = 2;//Ĭ��d = 2;����ͨ�Ĺ鲢����
	
	private IndexMinHeap minheap;
	private InsertSort insertSort = new InsertSort();
}
