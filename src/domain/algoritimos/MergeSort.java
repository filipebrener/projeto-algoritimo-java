package domain.algoritimos;

public class MergeSort extends Algoritmo{
    public MergeSort() {
        super("MergeSort");
    }

    @Override
    protected int[] ordenar(int[] vetor) {
        return mergeSort(vetor, vetor.length);
    }

    private static int[] mergeSort(int[] vetor, int n){
        if (n < 2) {
            return vetor;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = vetor[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = vetor[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        return merge(vetor, l, r, mid, n - mid);
    }

    public static int[] merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
        return a;
    }

}
