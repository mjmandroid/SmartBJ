

package com.itheiam62;

/**
 * @author Administrator
 * @创建时间 2015-7-8 下午3:32:45
 * @描述 TODO
 * 
 *     @ svn提交者：$Author$ @ 提交时间: $Date$ @ 当前版本: $Rev$
 */
public class AlogrithmApp
{

	/**
	 * @param number
	 * @return 1 到nubmer的和
	 */
	public static int sum(int number) {
		if (number < 1) {
			throw new RuntimeException("number must > 0");
		}

		if (number == 1) {
			return 1;
		} else {
			return number + sum(number - 1);
		}

	}

	private static int	counts	= 0;

	public static void moveNumber(int panNum, char a, char b, char c) {
		counts++;
		if (panNum != 1) {
			moveNumber(panNum - 1, a, c, b);
			moveNumber(panNum - 1, b, a, c);
		}
	}

	public static void move(int panNum, char a, char b, char c) {
		if (panNum == 1) {
			System.out.println("盘：" + panNum + " 从 " + a + " 柱移动 " + c + " 柱");
		} else {
			move(panNum - 1, a, c, b);
			System.out.println("盘：" + panNum + " 从 " + a + " 柱移动 " + c + " 柱");
			move(panNum - 1, b, a, c);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*moveNumber(30, 'a', 'b', 'c');

		System.out.println(counts);*/
		
		  int datas[] = new int[100000]; //初始化数据 
		  initData(datas); //打印数据
		  
		  //printData(datas); //插入排序 
		  quickSort(datas, 0, datas.length-1);
		  
		  System.out.println(System.currentTimeMillis());
		  insertSort(datas); //排序后打印 
		  //printData(datas);
		  System.out.println(System.currentTimeMillis());
		 
	}

	public static void selectSort(int[] datas) {
		for (int i = 0; i < datas.length - 1; i++) {
			int minIndex = i;// 最小数据的下标
			for (int j = i + 1; j < datas.length; j++) {
				if (datas[minIndex] > datas[j]) {
					minIndex = j;
				}
			}
			int temp = datas[i];
			datas[i] = datas[minIndex];
			datas[minIndex] = temp;
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @param datas
	 */
	public static void initData(int datas[]) {
		for (int i = 0; i < datas.length; i++) {
			// 随机1到100的值
			datas[i] = (int) (Math.random() * 100) + 1;
		}
	}

	/**
	 * 打印数据
	 * 
	 * @param datas
	 */
	public static void printData(int datas[]) {
		for (int i = 0; i < datas.length; i++) {
			System.out.print(datas[i] + ",");
		}
		System.out.println();
	}

	public static void bubbleSort(int datas[]) {
		int j = 0; // 冒泡的次数
		int i = 0; // 每次冒泡的比较
		for (j = datas.length - 1; j > 0; j--) {
			for (i = 0; i < j; i++) {
				if (datas[i] > datas[i + 1]) {
					int temp = datas[i];
					datas[i] = datas[i + 1];
					datas[i + 1] = temp;
				}
			}
		}
	}

	public static void insertSort(int datas[]) {
		int j = 0; // 第二个数据开始插入的下标
		int i = 0;// 插入是次数
		for (i = 1; i < datas.length; i++) {
			int temp = datas[i];
			for (j = i - 1; j >= 0; j--) {
				if (datas[j] > temp) {
					datas[j + 1] = datas[j];
				} else {
					break;
				}
			}
			// 判断 j == -1 或者 就是第一个小于等于temp数据的位置
			datas[j + 1] = temp;
		}
	}

	/**
	 * 快速排序
	 * 
	 * @param datas
	 */
	public static void quickSort(int datas[], int start, int end) {
		if (start >= end) {
			return;
		} else {
			int middle = findMiddle(datas, start, end);
			quickSort(datas, start, middle - 1);
			quickSort(datas, middle + 1, end);
		}
	}

	private static int findMiddle(int datas[], int start, int end) {
		int temp = datas[end];// 参照物

		int left = start - 1;
		int right = end;

		while (true) {
			// 1.从左边依次找数据，找到第一个比参照大的数据
			while (++left < end && datas[left] <= temp);
				
			
			if (left == end) {
				//参照物是最大值
				break;
			}
			// 2.从右边依次找数据，找到第一个比参数小的数据
			while (--right >= start && datas[right] >= temp);
				
			

			// 3,比较是否出现交叉（left 和 right）
			if (left < right) {
				// 4,如果没有交叉，交换左右位置的数据
				int d = datas[left];
				datas[left] = datas[right];
				datas[right] = d;
			} else {
				// 5,如果出现交叉，交换左指针和参照物的值，结束
				int d = datas[left];
				datas[left] = datas[end];
				datas[end] = d;
				break;
			}
		}
		return left;
	}

}
