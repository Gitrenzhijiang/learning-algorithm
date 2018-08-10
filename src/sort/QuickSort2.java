package sort;

import java.util.Random;

import util.Sort;
import util.SortHelper;

public class QuickSort2 implements Sort {
//�����������ʵ��2
	@Override
	public void sort(int[] arr) {
		sort0(arr, 0, arr.length-1);
	}
	private Random random = new Random();
	private void sort0(int[] arr, int left, int right) {
		
		//�Ż�: ���ѡ��,�����˿��������˻��� o(n2)�������㷨
		//�Խ����������������ʱ,Ч�ʴ������
		SortHelper.swap(arr, left, left + random.nextInt(right-left+1));
		//˼·:�ڿ�����
		int i = left, j = right;
		int e = arr[left];//e
		while(i < j) {
			//�Ӻ����ҵ�һ��<e����
			while(i<j && arr[j] >= e)
				j--;
			if(i < j) {
				arr[i++] = arr[j];
			}
			while(i<j && arr[i] <= e)
				i++;
			if(i<j) {
				arr[j--] = arr[i];
			}
		}
		arr[i] = e;
		if(left < i-1)
			sort0(arr, left, i-1);
		if(i+1 < right)
			sort0(arr, i+1, right);
	}
	public static void main(String[] args) {
		//��ʹ�����Aʱ����(java.lang.StackOverflowError),ʹ�����Bʱ������
		//��ֵ��ǵ�n���ٵ�һ������,���A�����B��������
		//ԭ�����ڵ���������ʱ,�����Ĵ�������n2
		int n = 15000;
		int[]arr = new int[n];
		for(int i = 0;i < arr.length;i++) {
			//arr[i] = i+1;   //���A
			arr[i] = new Random().nextInt();//���B
		}
		//��������
		new QuickSort().sort(arr);
		//SortHelper.printArray(arr);
		//System.out.println(SortHelper.isSorted(arr));
	}
}
