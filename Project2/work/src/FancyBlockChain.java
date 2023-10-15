public class FancyBlockChain {
    public Block[] bchain;
    private int size;  
    public int maxTimestamp = 0;

    public FancyBlockChain(int capacity) {
        bchain = new Block[capacity];
        size = 0;  
    }

    public FancyBlockChain(Block[] initialBlocks) {// Initialize the blockchain with blocks in heap order

        int n = initialBlocks.length;
        bchain = new Block[n];
        size = n;  

        for (int i = 0; i < n; i++) {// Copy blocks and set their indices
            initialBlocks[i].index = i;
            bchain[i] = initialBlocks[i];
        }

        for (int i = n / 2 - 1; i >= 0; i--) {// Bottom-up heap construction
            heapify(i);
        }
        maxTimestamp = getMaxTimestamp();
    }

    public int length() {
        return size;
    }

    public boolean addBlock(Block newBlock) {// Check if the blockchain is full

        if (size == bchain.length) {// Check if the new block has a later creation timestamp than the earliest block
            if (newBlock.timestamp > getEarliestBlock().timestamp) {// Remove the earliest block
                removeEarliestBlock();
                
                
            } else {
                return false;
            }

        }
    
        newBlock.index = size;
        bchain[size] = newBlock;
        size++;
    
        heapifyUp(size - 1);// Heapify up the last added block
        if(newBlock.timestamp > maxTimestamp){
            maxTimestamp = newBlock.timestamp;
        }
        return true;
    }
    

    public Block getEarliestBlock() {// Return the block with the earliest creation timestamp
        if (size > 0) {
            return bchain[0];
        } else {
            return null;
        }
    }

    public Block getBlock(String data) {// Find a block in the blockchain with matching data field
        for (Block block : bchain) {
            if (block != null && data.equals(block.data)) {
                return block;
            }
        }
        return null;
    }

    public Block removeEarliestBlock() {// Remove the earliest block from the blockchain

        if (size == 0) {
            return null;
        }

        Block removedBlock = bchain[0];
        removedBlock.removed = true;

        bchain[0] = bchain[size - 1];
        bchain[0].index = 0;
        bchain[size - 1] = null;
        size--;

        heapify(0);
        
        return removedBlock;
    }

    public Block removeBlock(String data) {// Remove a block from the blockchain by data
        for (int i = 0; i < size; i++) {
            if (bchain[i] != null && data.equals(bchain[i].data)) {
                Block removedBlock = bchain[i];
                int lastIndex = size - 1;

                if (i < lastIndex) {//force bubble up
                        while (i > 0) {
                            int parent = (i - 1) / 2;
                            if (bchain[i] != null && bchain[parent] != null ) {
                                swap(i, parent);
                                i = parent;     
                            }
                        }
                        removeEarliestBlock();
                } else {
                    bchain[i] = null;
                    size--;
                }
                heapify(i); 
                return removedBlock;
            }
        }   
        return null;
    }

    public void updateEarliestBlock(double nonce) {// Update the earliest block with the given nonce value
        if (size > 0 && bchain[0] != null) {
            bchain[0].nonce = nonce;
            bchain[0].timestamp = (int) (1 + maxTimestamp);// Correct the timestamp calculation

            heapify(0);
            maxTimestamp = maxTimestamp + 1;
        }
    }

    public void updateBlock(String data, double nonce) {// Update a block by data with the given nonce value
        if (size > 0) {
           
            for (int i = 0; i < size; i++) {
                if (bchain[i] != null && data.equals(bchain[i].data)) {
                    bchain[i].nonce = nonce;
                    bchain[i].timestamp = (int) (1 + maxTimestamp);
                    heapify(i);
                    maxTimestamp = maxTimestamp + 1;
                    return;
                }
            }
        }
    }

private void heapify(int i) {//Bottom-up heap construction

    int leftChild = 2 * i + 1;
    int rightChild = 2 * i + 2;
    int smallest = i;

    if (leftChild < size && bchain[leftChild] != null && (bchain[smallest] == null || bchain[leftChild].timestamp < bchain[smallest].timestamp)) {
        smallest = leftChild;
    }

    if (rightChild < size && bchain[rightChild] != null && (bchain[smallest] == null || bchain[rightChild].timestamp < bchain[smallest].timestamp)) {
        smallest = rightChild;
    }

    if (smallest != i) {
        swap(i, smallest);
        heapify(smallest);
    }
}

private void heapifyUp(int i) {//Heapify up
    while (i > 0) {
        int parent = (i - 1) / 2;
        if (bchain[i] != null && bchain[parent] != null && bchain[i].timestamp < bchain[parent].timestamp) {
            swap(i, parent);
            i = parent;
        } else {
            break;
        }
    }
}

    private void swap(int i, int j) { //swap blocks and update indices
        Block temp = bchain[i];
        bchain[i] = bchain[j];
        bchain[j] = temp;

        if (bchain[i] != null) {
            bchain[i].index = i;
        }
        if (bchain[j] != null) {
            bchain[j].index = j;
        }
    }

    private int getMaxTimestamp() {// Helper method to find the maximum timestamp    
        maxTimestamp = 0;
        for (Block block : bchain) {
            if (block != null && block.timestamp > maxTimestamp) {
                maxTimestamp = block.timestamp;
            }
        }
        return maxTimestamp;
    }

    
}

