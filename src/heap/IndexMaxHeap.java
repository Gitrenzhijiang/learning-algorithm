package heap;

import java.util.Random;

import util.SortHelper;
import util.TimeUtils;

// ���� ����
public class IndexMaxHeap extends MaxHeap{
	public static void main(String[] args) {
		IndexMaxHeap maxHeap = new IndexMaxHeap(20);
//		Random random = new Random();
//		for(int i = 0;i < 20;i++) {
//			maxHeap.insert(random.nextInt(20));
//		}
//		//155000 �� ���²���ֻ��Ҫ ���� ��ʱ��
//		TimeUtils.start();
//		for(int i = 0;i < 155000;i++)
//		{
//			maxHeap.change(random.nextInt(10000), random.nextInt(1000));
//		}
//		TimeUtils.over(true);
		
		maxHeap.insert(10);maxHeap.insert(9);maxHeap.insert(20);
		maxHeap.insert(30);maxHeap.insert(10);maxHeap.insert(13);
		SortHelper.printArray(maxHeap.indexs);
		
		System.out.println(maxHeap.extraction());
		System.out.println(maxHeap.extraction());
		
		SortHelper.printArray(maxHeap.indexs);
		SortHelper.printArray(maxHeap.arr);
		
		maxHeap.insert(7);maxHeap.insert(9);maxHeap.insert(1);
		SortHelper.printArray(maxHeap.indexs);
		SortHelper.printArray(maxHeap.arr);
		while(!maxHeap.isEmpty()) {
			System.out.print("[");
			System.out.print(maxHeap.getTop() + "," + maxHeap.extraction());
			System.out.print("] ");
		}
		System.out.println();
	}
	public IndexMaxHeap(int capacity) {
		super(capacity);
		indexs = new int[capacity];
		reverse = new int[capacity];
	}
	@Override
	public void insert(int e) {
		arr[max] = e;
		indexs[count] = max;
		reverse[max] = count;
		shiftUp(count);
		count++;
		max++;//
	}
	private void shiftUp(int k) {
		
		int e_index = indexs[k];
		int j = 0;
		while( (j = (k%2==0?k/2-1:k/2)) >= 0 && arr[e_index] > arr[indexs[j]] ){
			indexs[k] = indexs[j];
			reverse[indexs[j]] = k;
			k = j;
		}
		indexs[k] = e_index;
		reverse[e_index] = k;
	}
	@Override
	public int extraction() {
		int ret = arr[indexs[0]];
		SortHelper.swap(indexs, 0, count-1);// indexs[0] = indexs[count-1]
		SortHelper.swap(reverse, indexs[0], indexs[count-1]);
		count--;
		shiftDown(0);
		return ret;
	}
	//ȡ���Ѷ�Ԫ��
	public int getTop() {
		if(isEmpty())
			throw new RuntimeException("�ն�");
		return arr[indexs[0]];
	}
	private void shiftDown(int k) {
		int e_index = indexs[k];
		while(2*k+1 < count) {
			int j = 2*k + 1;
			if(j+1 < count && arr[indexs[j+1]] > arr[indexs[j]]) {
				j+=1;
			}
			if(arr[indexs[j]] > arr[e_index]) {
				indexs[k] = indexs[j];
				reverse[indexs[j]] = k;//for reverse
				k = j;
			}else
				break;
		}
		indexs[k] = e_index;
		reverse[e_index] = k;
	}
	/**
	 * �� i [0, size) λ�õ����ݸ��³� e
	 * @param i
	 * @param e
	 */
	public void change(int i, int e) {
		arr[i] = e;
		// �ҵ����� �е�tλ�ô洢��i
//		for(int t = 0;t < count;t++) {
//			if(indexs[t] == i) {
//				shiftDown(t);
//				shiftUp(t);
//				break;
//			}
//		}
		//ʹ�� reverse ����  
		int j = reverse[i];
		shiftDown(j);
		shiftUp(j);
	}
	private int max = 0;
	private int[]indexs;
	/*
	 * indexs[i] = j  reverse[j] = i
	 * j ���� �ڶ��е�λ��
	 */
	private int[]reverse;
}
