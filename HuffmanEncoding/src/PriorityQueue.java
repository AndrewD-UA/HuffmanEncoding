//PriorityQueue.java

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

	private void resize() {
		if (size == capacity) {
			HuffmanNode[] copy = new HuffmanNode[capacity * 2];
			for (int i = 0; i < minHeap.length; i++) {
				copy[i] = minHeap[i];
			}
			minHeap = copy;
			capacity *= 2;
		}
	}


	public void insert() {
		// TODO
	}


	private HuffmanNode remove() {
		return null;
		// TODO}
	}

	private void swap() {
		// TODO}
	}

	private void heapify() {
		//TODO}
	}
	
}
