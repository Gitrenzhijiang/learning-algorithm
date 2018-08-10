package sort;

import util.Sort;
import util.SortHelper;

public class JWGBubbleSort implements Sort {
/**
 * ��β������(ð������Ľ�):
 * ��ͨð������һ��ð�ݵĹ���ֻѡ��һ������ֵ,�������ݴ������ʱ����ȷ��β���������
 * ����ȷ��һ����Χ�������,ת����֮,����ȷ����������ķ�Χ,�����Χ����ÿһ������ð�ݻ��߷���ð�ݼ���
 */
	@Override
	public void sort(int[] arr) {
		int left = 0;
		int right = arr.length-1;
		boolean tag = true;//ð��˳����
		int index = 0;
		for(;left < right;) {
			if(tag) {//����ð��
				index = left;
				for(int i = left;i < right;i++) {
					if(arr[i] > arr[i+1]) {
						SortHelper.swap(arr, i, i+1);
						index = i+1;
					}
				}
				right = index;
				tag = false;//�л�
			}else {
				index = right;
				for(int i = right;i > left;i--) {
					if(arr[i] < arr[i-1]) {
						SortHelper.swap(arr, i, i-1);
						index = i-1;
					}
				}
				left = index;
				tag = true;//�л�
			}
		}
	}

}
