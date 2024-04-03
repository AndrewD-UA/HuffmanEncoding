//PriorityQueue.java
package model.PriorityQueue;

/**
 * A priority queue implementation
 * 
 * @author Chris Reid
 */
public class PriorityQueue {
	private HuffmanNode[] minHeap;
	private int size;
	private int capacity;

	public PriorityQueue(int capacity) {
		this.capacity = capacity;
		this.minHeap = new HuffmanNode[capacity];
		this.size = 0;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private HuffmanNode leftChild(int index) {
		return minHeap[getLeftChildIndex(index)];
	}

	private HuffmanNode rightChild(int index) {
		return minHeap[getRightChildIndex(index)];
	}

	private HuffmanNode parent(int index) {
		return minHeap[getParentIndex(index)];
	}

	private void resizeCheck() {
		if (size == capacity) {
			HuffmanNode[] copy = new HuffmanNode[capacity * 2];
			for (int i = 0; i < minHeap.length; i++) {
				copy[i] = minHeap[i];
			}
			minHeap = copy;
			capacity *= 2;
		}
	}
	
	/**
	 * Inserts new node at tail end of array
	 */
	private void insert(HuffmanNode newNode) {
		resizeCheck();
		minHeap[size] = newNode;
		size++;
		swimUp();
	}
	
	private void swimUp() {
        int index = size - 1;
        while (index > 0 && parent(index).compareTo(minHeap[index]) > 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }
	
	/**
	 * Removes the minimum element from the min heap. Replaces with last entry
	 */
	private HuffmanNode popMin() {
		HuffmanNode removed = minHeap[0];
		minHeap[0] = minHeap[size - 1];
		size--;
		sinkDown();
		return removed;
	}

	private void sinkDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (minHeap[index].compareTo(minHeap[smallerChildIndex]) <= 0) {
                break; // Already in heap sorted order
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }
	
	private void swap(int index1, int index2) {
		HuffmanNode temp = minHeap[index1];
        minHeap[index1] = minHeap[index2];
        minHeap[index2] = temp;
	}

}
