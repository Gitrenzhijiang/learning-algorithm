package find;

import util.Find;
import util.FindHelper;

public class BinarySearch implements Find {
	public static void main(String[] args) {
		System.out.println(FindHelper.testCanFind(new BinarySearch()));
		FindHelper.printfindAlgorithm(new BinarySearch(), new int[] {2,3,20,20,20,33,36,38,39}, 20);
	}
	
	public int find_simple(int[] arr, int n, int target) {
		int left = 0, right = n-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == target) {
				return mid;
			}else if(target < arr[mid]) {
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return -1;
	}
	public int floor(int[] arr, int n, int target) {
		int left = 0, right = n-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == target) {
				while(mid-1 >= left && arr[mid-1] == arr[mid]) {
					mid--;
				}
				return mid;
			}else if(target < arr[mid]) {
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return -1;
	}
	public int ceil(int[] arr, int n, int target) {
		int left = 0, right = n-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == target) {
				while(mid+1 <= right && arr[mid+1] == arr[mid]) {
					mid++;
				}
				return mid;
			}else if(target < arr[mid]) {
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return -1;
	}


	@Override
	public int find(int[] arr, int n, int target) {
		return ceil(arr, n, target);
	}
}
