package aed;

import java.util.Arrays;

public class InternetToolkit {
    public InternetToolkit() {
    }

    private void insertionSortAdaptativo(Fragment[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            Fragment key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        insertionSortAdaptativo(fragments);
        return fragments;
    }

    private void heapify(Router[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            Router temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    private void buildMaxHeap(Router[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        Router[] filteredRouters = Arrays.stream(routers)
                .filter(router -> router.getTrafico() > umbral)
                .toArray(Router[]::new);

        int n = filteredRouters.length;
        buildMaxHeap(filteredRouters);

        Router[] result = new Router[Math.min(k, n)];
        for (int i = 0; i < result.length; i++) {
            result[i] = filteredRouters[0];
            filteredRouters[0] = filteredRouters[n - 1];
            n--;
            heapify(filteredRouters, n, 0);
        }

        return result;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        IPv4Address[] ipv4Addresses = new IPv4Address[ipv4.length];
        
        for (int i = 0; i < ipv4.length; i++) {
            ipv4Addresses[i] = new IPv4Address(ipv4[i]);
        }
        
        Arrays.sort(ipv4Addresses, (ip1, ip2) -> {
            for (int i = 0; i < 4; i++) {
                if (ip1.getOctet(i) != ip2.getOctet(i)) {
                    return Integer.compare(ip1.getOctet(i), ip2.getOctet(i));
                }
            }
            return 0;
        });
        
        return ipv4Addresses;
    }

}
