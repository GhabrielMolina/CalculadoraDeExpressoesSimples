public class Pilha {
  private class Node {
    double element;
    Node next;

    public Node(double element) {
      this.element = element;
    }
  }

  private Node top;
  private int size;

  public Pilha() {
    top = null;
    size = 0;
  }

  public void push(double e) {
    Node newNode = new Node(e);
    if(size != 0)
      newNode.next = top;
    top = newNode;
    size++;
  }

  public double pop() {
    if (isEmpty()) {
      throw new RuntimeException("Pilha vazia");
    }
    double element = top.element;
    top = top.next;
    size--;
    return element;
  }

  public double top() {
    if (isEmpty()) {
      throw new RuntimeException("Pilha vazia");
    }
    return top.element;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public void clear() {
    top = null;
    size = 0;
  }
}
