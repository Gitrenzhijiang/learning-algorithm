package heap;

import java.util.Random;

import util.SortHelper;

//����
/**
 * ��������һ����ȫ������ 
 * ����ĳһ���ڵ� : ���ӽڵ����ǲ����ڸ��ڵ��ֵ
 */
public class MaxHeap {
	public static void main(String[] args) {
		MaxHeap maxHeap = new IndexMaxHeap(30);
		for(int i = 0;i < 19;i++) {
			maxHeap.insert(new Random().nextInt(130));
		}
		System.out.println(maxHeap.extraction());
		System.out.println(maxHeap.extraction());
		SortHelper.printArray(maxHeap.arr);
		System.out.println("maxHeap.size() "+ maxHeap.size());
		for(;!maxHeap.isEmpty();) {
			System.out.print(maxHeap.extraction() +" ");
		}
	}
	//shift Up
	//shift Down
	//print Heap
	//Insert
	//extraction
	//size
	//isEmpty
	protected int []arr;
	protected int count;//��ǰ�ѵĴ�С
	protected int capacity;
	public MaxHeap( int capacity) {
		this.capacity = capacity;
		arr = new int[capacity];
		count = 0;
	}
	/**
	 * ����һ����
	 * @param e
	 */
	public void insert(int e) {
		arr[count] = e;
		shiftUp(count);
		count++;
	}
	public int extraction() {
		int ret = arr[0];
		SortHelper.swap(arr, 0, count-1);
		count--;
		shiftDown(0);
		return ret;
	}
	//β������һ����ʱ,ʹ�������������
	private void shiftUp(int k) {
		int p = 0, e = arr[k];
		while( (p = (k%2==0?k/2-1:k/2)) >= 0 && arr[p] < e) {
			arr[k] = arr[p];
			k = p;
		}
		arr[k] = e;
	}
	private void shiftDown(int k) {
		int e = arr[k], j = 2*k+1;
		while(j < count) {
			if(j + 1 < count && arr[j+1] > arr[j])
				j+=1;
			if(e < arr[j]) {
				arr[k] = arr[j];
				k = j;
				j = 2*k+1;
			}else {
				break;
			}
		}
		arr[k] = e;
	}
	
	public int size() {
		return count;
	}
	public boolean isEmpty() {
		return count==0;
	}
	//ȡ���Ѷ�Ԫ��
	public int getTop() {
		if(isEmpty())
			throw new RuntimeException("�ն�");
		return arr[0];
	}
}
