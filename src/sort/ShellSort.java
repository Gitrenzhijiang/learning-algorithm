package sort;

import util.Sort;

public class ShellSort implements Sort {
/**
 	ϣ������:
 	1, 4, 13, 40, 121, 364, 1093...
 	
 	�����������Ըߵ����黹�������Ե͵����鶼�в��Ƶ�Ч��
 */
	@Override
	public void sort(int[] arr) {
		int shell = 1;
		while(shell < arr.length/3) {
			shell = 3*shell + 1;
		}
		for(;shell >= 1;) {
			for(int i = shell;i < arr.length;i+=shell) {
				int temp = arr[i];
				int j = i;
				for(;j-shell >= 0 && arr[j-shell] > temp;j-=shell) {
					arr[j] = arr[j-shell];
				}
				arr[j] = temp;
			}
			shell /= 3;
		}
	}

}
