package heap;

import util.SortHelper;

public class MinHeap {
	public static void main(String[] args) {
		MinHeap minheap = new MinHeap(30);
		minheap.insert(30);minheap.insert(2);minheap.insert(12);
		minheap.insert(15);minheap.insert(1);minheap.insert(33);
		minheap.extraction();
		minheap.extraction();
		minheap.insert(18);minheap.insert(3);minheap.insert(88);
		while(!minheap.isEmpty()) {
			System.out.print(minheap.extraction() + " ");
		}
	}
	public MinHeap( int capacity) {
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
	//β������һ����ʱ,ʹ�����������С��
	private void shiftUp(int k) {
		int p = 0, e = arr[k];
		while( (p = (k%2==0?k/2-1:k/2)) >= 0 && arr[p] > e) {
			arr[k] = arr[p];
			k = p;
		}
		arr[k] = e;
	}
	private void shiftDown(int k) {
		int e = arr[k], j = 2*k+1;
		while(j < count) {
			if(j + 1 < count && arr[j+1] < arr[j])
				j+=1;
			if(e > arr[j]) {
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
	
	protected int []arr;//�洢���ݵ�����
	protected int count;//��ǰ�ѵĴ�С
	protected int capacity;//�ѵ�����
}
