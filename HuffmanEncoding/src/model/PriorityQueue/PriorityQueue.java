package model.PriorityQueue;

/**
 * A priority queue implementation for HuffmanNode objects based on a min-heap.
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
			System.arraycopy(minHeap, 0, copy, 0, size);
			minHeap = copy;
			capacity *= 2;
		}
	}

	/**
	 * Inserts a new node into the priority queue.
	 * 
	 * @param newNode The node to insert
	 */
	public void insert(HuffmanNode newNode) {
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
	 * Removes and returns the minimum element (root) from the priority queue.
	 * 
	 * @return The minimum element in the priority queue
	 */
	public HuffmanNode popMin() {
		if (size == 0) {
			return null;
		}

		HuffmanNode removed = minHeap[0];
		minHeap[0] = minHeap[size - 1];
		size--;
		sinkDown();
		return removed;
	}

	/**
	 * Sink a node from its current position to the position where heap order is
	 * maintained.
	 */
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

	/**
	 * Swap two huffman nodes in an array
	 * 
	 * @param index1 The index of the first node to swap
	 * @param index2 The index of the second node to swap
	 */
	private void swap(int index1, int index2) {
		HuffmanNode temp = minHeap[index1];
		minHeap[index1] = minHeap[index2];
		minHeap[index2] = temp;
	}

	/**
	 * The size of the queue.
	 * 
	 * @return The logical size of the queue, e.g. the number of valid nodes in the
	 *         heap.
	 */
	public int size() {
		return size;
	}
}
