  public class BlockChainSecure {
    public FancyBlockChain fbc;
    public Block[] btable;
    int size = 0;
    final int MAX_NUM = (int) 1e9;

    public class quadHash {
      public int first;
      public int second;

      public quadHash(int first, int second) {
        this.first = first;
        this.second = second;
      }
    }

    public quadHash calculateIntPair(String data) {
      int int1 = Hasher.hash1(data, btable.length);
      int int2 = Hasher.hash2(data, btable.length);
      return new quadHash(int1, int2);
    }

    private boolean primeCheck(int prime) {
      if (prime <= 1) {
        return false;
      }
      if (prime <= 3) {
        return true;
      }

      if (prime % 2 == 0 || prime % 3 == 0) {
        return false;
      }

      for (int i = 5; i * i <= prime; i = i + 6) {
        if (prime % i == 0 || prime % (i + 2) == 0) {
          return false;
        }
      }

      return true;
    }

    private int nextPrimeNumber(int capacity) {
      if (capacity <= 1) {
        return 2;
      }

      int prime = capacity;
      boolean found = false;
      while (!found) {
        prime++;
        if (primeCheck(prime)) {
          found = true;
        }
      }
      return prime;
    }

    public BlockChainSecure(int capacity) {
      int primeSize = nextPrimeNumber(capacity);
      this.fbc = new FancyBlockChain(capacity);
      this.btable = new Block[primeSize];
    }

    public BlockChainSecure(Block[] initialBlocks) {
      int capacity = nextPrimeNumber(initialBlocks.length);
      this.btable = new Block[capacity];
      this.fbc = new FancyBlockChain(initialBlocks);
      this.size = initialBlocks.length;

      for (Block block: initialBlocks) {
        int hashIndex = calculateHashIndex(block.data);
        btable[hashIndex] = block;
      }
    }
    public int length() {
      return size;
    }

    public boolean addBlock(Block newBlock) {
      boolean added = fbc.addBlock(newBlock);
      if (added) {
        int hashIndex = calculateHashIndex(newBlock.data);
        if (btable[hashIndex] == null || btable[hashIndex].removed) {
          size++;
        }
        btable[hashIndex] = newBlock;
      }
      return added;
    }

    public Block getEarliestBlock() {
      return fbc.getEarliestBlock();
    }

    public Block getBlock(String data) {
      quadHash hash = calculateIntPair(data);

      if (btable[hash.first] != null && btable[hash.first].data.equals(data)) {
        return btable[hash.first];
      } else {
        int i = 1;
        while (i < btable.length) {
          int newIndex = (hash.first + i * hash.second) % btable.length;
          if (btable[newIndex] != null && btable[newIndex].data.equals(data)) {
            return btable[newIndex];
          }
          i++;
        }
      }
      return null;
    }

    public Block removeEarliestBlock() {
      Block removedBlock = fbc.removeEarliestBlock();

      if (removedBlock != null) {
        int hashIndex = calculateHashIndex(removedBlock.data);
        if (btable[hashIndex] != null && !btable[hashIndex].removed) {
          btable[hashIndex].removed = true;
          size--;
        }
      }

      return removedBlock;
    }

    public Block removeBlock(String data) {
      int index1 = Hasher.hash1(data, btable.length);
      int index2 = Hasher.hash2(data, btable.length);

      if (btable[index1] != null && btable[index1].data.equals(data) && !btable[index1].removed) {
        Block removedBlock = btable[index1];
        btable[index1].removed = true;
        fbc.removeBlock(data);
        size--;
        return removedBlock;
      } else {
        int i = 1;
        while (i < btable.length) {
          int newIndex = (index1 + i * index2) % btable.length;
          if (btable[newIndex] != null && btable[newIndex].data.equals(data)) {
            Block removedBlock = btable[newIndex];
            removedBlock.removed = true;
            fbc.removeBlock(data);
            size--;
            return removedBlock;
          }
          i++;
        }
      }
      return null;
    }

    public void updateEarliestBlock(double nonce) {
      fbc.updateEarliestBlock(nonce);
    }

    public void updateBlock(String data, double nonce) {
      quadHash hash = calculateIntPair(data);
      int primaryIndex = hash.first;
      if (btable[primaryIndex] != null && btable[primaryIndex].data.equals(data)) {
        Block targetBlock = btable[primaryIndex];
        targetBlock.nonce = nonce;
        targetBlock.timestamp = fbc.maxTimestamp + 1;
        fbc.updateBlock(data, nonce);
      } else {
        for (int offset = 1; offset < btable.length; offset++) {
          int adjustedIndex = (hash.first + offset * hash.second) % btable.length;
          Block potentialBlock = btable[adjustedIndex];

          if (potentialBlock != null && potentialBlock.data.equals(data)) {
            potentialBlock.nonce = nonce;
            potentialBlock.timestamp = fbc.maxTimestamp + 1;
            fbc.updateBlock(data, nonce);
            break;
          }
        }
      }

    }

    private int calculateHashIndex(String data) {
      quadHash hash = calculateIntPair(data);

      int k = 0;
      int originalHashIndex = (hash.first + k * hash.second) % btable.length;
      int hashIndex = originalHashIndex;

      while (btable[hashIndex] != null && !btable[hashIndex].removed) {
        k++;
        hashIndex = (hash.first + k * hash.second) % btable.length;

        if (hashIndex == originalHashIndex) {
          k = 0;
          while (true) {
            if (btable[hashIndex] == null || btable[hashIndex].removed) break;
            k++;
            hashIndex = (originalHashIndex + k) % btable.length;
            if (hashIndex == originalHashIndex) break;
          }
        }
      }
      return hashIndex;
    }

  }