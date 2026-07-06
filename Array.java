//package array;

public class Array {
  private int size = 0;
  private int[] array;
  private int initialCapacity = 0;
  


  public Array(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("InitialCapacity must be greater than zero.");    
    }
    this.initialCapacity = initialCapacity;
    array = new int[initialCapacity];
  }



  private void checkIfArrayIsFull() {
    if (size == initialCapacity) {
      throw new IllegalStateException("Array is full. No more elements can be added.");
    }
  }



  private void checkIfArrayIsEmpty() {
    if (size == 0) {
      throw new IllegalStateException("Array is empty. Please add elements to the array.");
    }
  }


  private void validateIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("Invalid index. Index must be between 0 and " + (size - 1) + ".");
    }  
  }


  public void insertFirst(int element) {
    checkIfArrayIsFull();
    if (size == 0) {
      array[0] = element;
      size++;
    } else {
      for (int i = initialCapacity - 1; i > 0; i--) {
        array[i] = array[i - 1];
      }
      array[0] = element;
      size++;
    }
  }



  public void insertLast(int element) {
    checkIfArrayIsFull();
    array[size] = element;
    size++;
  }



  public void insertAtIndex(int index, int element) {
    checkIfArrayIsEmpty();
    checkIfArrayIsFull();
    validateIndex(index);
    
    if (index == 0) {
      insertFirst(element);
      return;
    }

    for (int i = initialCapacity - 1; i > index; i--) {
      array[i] = array[i - 1];
    }
    array[index] = element;
    size++;
  }



  public int deleteFirst() {
    checkIfArrayIsEmpty();
    int element = array[0];
    for (int i = 0; i < size - 1; i++) {
      array[i] = array[i + 1];
    }
    size --;
    return element;
  }


  
  public int deleteLast() {
    checkIfArrayIsEmpty();
    int element = array[size - 1];
    size--;
    return element;
  }



  public int deleteAtIndex(int index) {
    checkIfArrayIsEmpty();
    validateIndex(index);

    if (index == 0) {
      return deleteFirst();
    }

    if (index == size - 1) {
      return deleteLast();
    } 

    int element = array[index];
    size--;
    for (int i = index; i < initialCapacity - 1; i++) {
      array[i] = array[i + 1];
    }

    return element;
  }


  public int indexOf(int element) {
    checkIfArrayIsEmpty();
    for (int i = 0; i < size; i++) {
      if (array[i] == element) {
        return i;
      }
    }
    return -1;
  }


  public int removeElement(int element) {
    checkIfArrayIsEmpty();
    int index = indexOf(element);
    if (index == -1) return -1;
    return deleteAtIndex(index);
  }


  
  public void replace(int target, int newElement) {
    checkIfArrayIsEmpty();
    int index = indexOf(target);
    if (index == -1) return;
    array[index] = newElement;
  }



  public int size() {
    return this.size;
  }


  
  public void print() {
    System.out.print("\nArray Elements: [ ");
    for (int i = 0; i < size; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.print("]\n");
  }



  public void indexView() {
    if (size > 0) {
      System.out.println();
      for (int i = 0; i < size; i++) {
        System.out.println("Index: " + i + " Element: " + array[i]);
      }
    }
  }
}