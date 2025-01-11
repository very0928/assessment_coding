package coding_problems_set.ds_algorithm.sorting_algorithms;

/**
 * In 3 Way QuickSort, an array arr[l..r] is divided in 3 parts:
 * a) arr[l..i] elements less than pivot.
 * b) arr[i+1..j-1] elements equal to pivot.
 * c) arr[j..r] elements greater than pivot.
 */
public class Jan_06_2025_2_quick_sort_with_Dutch_Flag_Partitioning {
    public static void main(String[] args) {

    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pivots = partition(arr, low, high);
            quickSort(arr, low, pivots[0] - 1);
            quickSort(arr, pivots[1] + 1, high);
        }
    }

    /**
     * 三路划分的方法 ➡️ 用于处理数组中包含多个相同元素的情况
     * 1. arr[i] < pivot ➡️ 与lt位置的元素交换，并将lt和i向前移动
     * 2. arr[i] > pivot ➡️ 与gt位置的元素交换，仅仅将gt向后移
     * 3. 当前元素等于基准值，继续检查下一个元素
     * @param arr 选择第一个元素作为基准值pivot
     * @param low - lt - 小于基准值的区域的尾部
     * @param high - gt - 大于基准值的区域的头部
     * @return [lt, gt] - 返回划分以后的边界索引
     */
    private int[] partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int lt = low;
        int gt = high;
        int i = low + 1;

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        return new int[] {lt, gt};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
