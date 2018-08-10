package heap;


/**
 * 쳲�������
 * @author renzhijiang
 * ����: [a] ��һϵ�о�����С������и����ļ���;  ÿһ������������С�ѵ�����
 * [b] ÿһ���ڵ���  x.parentָ��(��ͬ), x.child, x.left, x.right  
 * �� x.degree(�ý��Ķ�) �� x.mark (���x �Դ���һ�γ�Ϊ��һ�����ĺ��Ӻ�,
 * ����Ϊtrue,����ʧȥ����ʱ���false)
 * [c] ÿһ����㶼��һ�����Ķ���D(n),  
 * ���������Ķȶ������������ͬ�� ��������������D(n)+1 ����.
 * 
 * 
 * T: 쳲������Ѵ�ŵ� ��������
 */
public class FibHeap<T extends Comparable<T>>{
	
	public static void main(String[] args) {
		FibHeap<Integer> fibheap = new FibHeap<Integer>();
		
		Item<Integer> a23 = new Item<Integer>(23);
		Item<Integer> a7 = new Item<Integer>(7);
		Item<Integer> a21 = new Item<Integer>(21);
		Item<Integer> a3 = new Item<Integer>(3);a3.degree = 3;
			Item<Integer> a18 = new Item<Integer>(18);a18.degree = 1;
				Item<Integer> a39 = new Item<Integer>(39);a39.parent = a18;a39.left = a39;a39.right = a39;
			Item<Integer> a52 = new Item<Integer>(52);
			Item<Integer> a38 = new Item<Integer>(38);a38.degree = 1;
				Item<Integer> a41 = new Item<Integer>(41);a41.parent = a38;a41.left = a41;a41.right = a41;
		Item<Integer> a17 = new Item<Integer>(17);a17.degree = 1;
			Item<Integer> a30 = new Item<Integer>(30);a30.left = a30;a30.right=a30;a30.parent = a17;a17.child = a30;
		Item<Integer> a24 = new Item<Integer>(24);a24.degree =  2;
			Item<Integer> a26 = new Item<Integer>(26);a26.degree = 1;
				Item<Integer> a35 = new Item<Integer>(35);a35.left = a35;a35.right = a35;a35.parent = a26;a26.child = a35;
			Item<Integer> a46 = new Item<Integer>(46);
		//rootList 
		a23.left = a24;a23.right = a7;
		a7.left = a23;a7.right = a21;
		a21.left = a7;a21.right = a3;
		a3.left = a21;a3.right = a17;
		a17.left = a3;a17.right = a24;
		a24.left = a17;a24.right = a23;
		
		//a3 childs
		a3.child = a18;
		a18.parent = a3;a52.parent = a3;a38.parent = a3;
		a18.left = a38;a18.right = a52;
		a52.left = a18;a52.right = a38;
		a38.left = a52;a38.right = a18;
		//a38 child
		a38.child = a41;
		//a18 child
		a18.child = a39;
		//a24 childs
		a24.child = a26;
		a26.parent = a24;
		a46.parent = a24;
		a26.left = a46;a26.right = a46;
		a46.left = a26;a46.right = a26;
		
		
		fibheap.rootList.size = 6;
		fibheap.n = 15;
		fibheap.min = a3;
		fibheap.printFibHeap();
		
		System.out.println(fibheap.extractMin());
		fibheap.printFibHeap();
		System.out.println(fibheap.extractMin());
		fibheap.printFibHeap();
		
	}
	
	private static final int Dn = 3;
	
	private int n;//���н������
	private Item<T> min;// 쳲������� �� ��С��� 
	private CycleLinkedList<T> rootList;
	public FibHeap() {
		rootList = new CycleLinkedList<>();
	}
	// ������ �Ĵ�С 
	public int rootSize() {return rootList.size;}
	//���н������
	public int getSize() {return n;}
	/**
	 * 
	 * ����һ����㵽����
	 * o(1) ��ʱ�临�Ӷ�
	 * @param data
	 */
	public void insert(T data) {
		
		Item<T> addItem = new Item<T>(data);
		rootList.insert(addItem);
		if(min.compareTo(addItem) > 0) {//min.key > addItem.key
			min = addItem;
		}
		n+=1;
	}
	/**
	 * ��ȡ��С���
	 * @return
	 */
	public T extractMin() {
		Item<T> minElement = this.min;
		// ����С�������к��Ӳ��뵽rootList��
		Item<T> child = null;
		if(minElement.child != null) {
			child = minElement.child;
			Item<T> over = child.left;
			boolean tag = true;
			while(tag) {
				Item<T> next = child.right;
				if(child == over)
					tag = false;
				rootList.insert(child);//��minElement ����߲��� child
				child = next;
			}
		}
		// ��rootlist���Ƴ���С���
		if(minElement.right != minElement)
			min = minElement.right;
		else
			min = null;//���Ѿ�����
		rootList.delete(minElement);
		
		
		
		n--;//���н����Ŀ��һ
		consolidate();//���϶�������
		return minElement.data;
	}
	
	//����rootList �ĺ���,��֤rootList ��ÿһ�� ���Ķȸ�����ͬ
	private void consolidate() {
		
		if(min == null || rootList.size == 1)// �ն� ����ֻ��һ�����
			return;
			
		Item<T>[] aux = new Item[Dn+1];// 0  1  2  3 aux[i] = xxx  ��ʾxxx�Ķ�Ϊi
		int minIndex = -1; // aux[]����С�������
		//����rootList��ÿһ�����
		Item<T> item = min;//item ��ǰ�����Ľ��
		Item<T> over = item.left;//������ֹ���
		
		boolean isover = false;
		while(!isover) {
			Item<T> x = item;
			item = item.right;//��һ����Ҫ������item
			
			//ѭ���ڱ�����over�����
			if(x == over)
				isover = true;
			
			
			int degree = x.degree;//�������Ķ�
			while(aux[degree] != null) {
				//������һ�����ĶȺ�x��ͬ,ȡ��������y
				Item<T> y = aux[degree];
				//��֤x.key <= y.key
				if(x.compareTo(y) > 0) {
					Item<T> _forChange = x;
					x = y;
					y = _forChange;
				}
				//�Ӹ�����ɾ��y, ��y �嵽 x ���ӽ����
				heapLink(x, y);
				//����֮��û�н��Ķ�Ϊdegree
				aux[degree] = null;
				if(degree == minIndex)// �Ż�
					minIndex = -1;
				//��ʱx �Ķ�Ϊ degree+1
				degree++;
			}
			x.degree = degree;
			aux[degree] = x;
			//just for find min
			if(minIndex == -1 || aux[minIndex].compareTo(aux[degree]) > 0) {
				minIndex = degree;
			}
		}
		// Ѱ����С
		min = aux[minIndex];
	}
	////�Ӹ�����ɾ��y,  ��y ���뵽 x ���ӽ����
	//x.child �������һ�������x�� ���� ��������������к��ӵ������
	private void heapLink(Item<T> x, Item<T> y) {
		
		rootList.delete(y);
		if(x.child == null) {
			x.child = y;
			y.parent = x;
			y.left = y;
			y.right = y;
		}else {
			Item<T> xChild1 = x.child;
			Item<T> xChild2 = xChild1.left;
			xChild1.left = y; 
			y.right = xChild1;
			
			y.left = xChild2;
			xChild2.right = y;
			
			y.parent = x;
			x.child = y;//���һ������ĺ���Ϊx.child
		}
	}
	//��ӡ쳲�������
	public void printFibHeap() {
		if(rootSize() == 0) {
			System.out.println("this is a null heap");
		}else {
			Item<T> item = min;
			do {
				//��ӡitem
				System.out.print("["+ item.data + ":");
				if(item.child != null) {
					Item temp = item.child;
					while(true) {
						System.out.print(temp.data + " ");
						temp = temp.right;
						if(temp == item.child)
							break;
					}
				}
				System.out.print("] ");
				
				item = item.right;
			}while(item != min);
			System.out.println();
		}
	}
	
	//���
 	static class Item <T> implements Comparable<Item<T>>{
		//һ������Ϊt, ���ҽ��ָ���Լ��Ľ��
		public Item(T t) {
			this.data = t;
			left = this;
			right = this;
		}
		Item<T> parent;
		Item<T> child;
		Item<T> left;//�����������
		Item<T> right;//�ҽ��
		int degree;// ���Ķ�
		boolean mark = false;//Ĭ��δ�����
		T data;//��ŵ�����
		
		//�ȽϷ���
		@Override
		public int compareTo(Item<T> o) {
			Comparable<T> com_this = (Comparable<T>)data;
			return com_this.compareTo(o.data);
		}
		
	}
	
	//ѭ��˫������ rootList
	class CycleLinkedList<T>{
		int size;
		
		//ɾ�����
		public void delete(Item deleteItem) {
			Item left = deleteItem.left;
			Item right = deleteItem.right;
			left.right = right;
			right.left = left;
			deleteItem.left = null;
			deleteItem.right = null;
			size--;
		}
		//����Ԫ����˫��ѭ������
		public void insert(Item addItem) {
			if(min == null) {
				min = addItem;
				size++;
			}else {
				insert0(addItem, min);
			}
		}
		// ��start������߲���һ��Ԫ��
		private void insert0(Item addItem, Item start) {
			Item temp = start.left;
			temp.right = addItem;
			addItem.left = temp;
			
			addItem.right = start;
			start.left = addItem;
			size++;
		}
	}
}

