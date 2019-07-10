package list;

public class SinglyLinkList<E> {

    private Node<E> head;
	private int size;

		public void add(E e){
			addAtEnd(e);
		}
		
		public int size(){
			return size;
		}
		
		
		public void reverse(){
			if(head == null || head.next == null){
				return;
			}
			Node<E> p,q,r=null;
			p =q = head;
			
			while(p!=null){
				q=p;
				p=p.next;
				q.next=r;
				r = q;
			}
			head = q;
		}
		
		
		public void reversePairs(){
			if(head == null || head.next ==null){
				return;
			}
			
			Node<E> p,q,r;
			
			r=head;
			q=head = r.next;
			
			while(true){
				p=q.next;
				q.next=r;
				if(p==null || p.next == null){
					r.next = p;
					break;
				}
				
				r.next = p.next;
				r = p;
				q=r.next;
			
			}
			
		}
			
			
			
		
		public E findNodeFromEnd(int positionFromEnd){
			Node<E> t = head;
			
			if(t == null){
				throw new IllegalStateException("List is empty");
			}

			if( positionFromEnd < 0){
                throw new IllegalArgumentException("Invalid position : " + positionFromEnd);
            }
			
			while(t != null && t.next !=null && positionFromEnd > 1){
				--positionFromEnd;
				t = t.next;
			}
			
			if(positionFromEnd > 1 ){
				throw new IllegalArgumentException("List has fewer element then passed argument");
			}
			
			Node<E> p = head;
			
			while(t != null && t.next!=null){
				t = t.next;
				p = p.next;
			}
			
			return p.data;
		}
	
	
	private void addAtStart(E e){
		
		if(head == null) {
			head = new Node(e, null);
		} else {
			Node<E> t = head;
			head = new Node(e, t);
		}
		size++;
	}
	
	public void addAt(int index, E e){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index should be between 0 and " + (size-1));
		}

		Node<E> t = head;

        if(index ==0 ){
            head = new Node<>(e, t);
            return;
        }
		while(--index > 0 ) {
            t = t.next;
        }
		t.next = new Node(e, t.next);

        size++;
	}
	
	private void addAtEnd(E e){
		
		if(head == null) {
			head = new Node(e, null);
		} else {
			Node<E> t = head;
			while(t.next  != null){
			    t= t.next;
            }
			t.next = new Node(e, null);
		}
		size++;
	}
	


		
		
		@Override
		public String toString(){
			if(head == null){
				return "[]";
			}
			
			Node<E> t = head;
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			
			while(t.next != null){
				sb.append(t.data.toString());
				sb.append(", ");
				t = t.next;
			}
			sb.append(t.data);
			sb.append("]");
			return sb.toString();
		}
			
			
			
			
			
			
	private static class Node<E>{
        E data;
        Node<E> next;

        Node(E data, Node < E > next) {
        this.data = data;
        this.next = next;
    }

        @Override
        public String toString() {
            E e = next != null ? next.data : null;
            return "["+this.data + ", next->" + e + "]";
        }
    }
}
