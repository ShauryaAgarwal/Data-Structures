public class SuperList<E>
{
	private SuperNode<E> root;
	private SuperNode<E> end;
	private int size;

	public SuperList()
	{
		root = null;
		end = null;
		size = 0;
	}

	public SuperList(E r)
	{
		root = new SuperNode<E>(r);
		end = root;
		size = 1;
	}

	public void add(E val)
	{
		SuperNode<E> node = new SuperNode<E>(val);
		if(root == null)
		{
			root = node;
			end = node;
		}
		else if(val == null)
		{
			size--;
		}
		else
		{
			end.setNext(node);
			node.setPrevious(end);
			end = node;
		}
		size++;
	}

	public void add(int index, E val)
	{
		SuperNode<E> newNode = new SuperNode<E>(val);
		if(index == 0 && root == null)
		{
			root = newNode;
			end = newNode;
			size++;
		}
		else if(val == null)
		{
			//
		}
		else if(index < 0 || index > size)
		{
			System.out.println("Array Index Out of Bounds Exception for Index " + index);
		}
		else if(index == 0)
		{
			SuperNode<E> tempNode = root;
			newNode.setNext(tempNode);
			tempNode.setPrevious(newNode);
			root = newNode;
			size++;
		}
		else if(index == size)
		{
			SuperNode<E> tempNode = root;
			end.setNext(newNode);
			newNode.setPrevious(end);
			end = newNode;
			size++;
		}
		else
		{
			SuperNode<E> tempNode = root;
			for(int i = 0; i < index; i++)
			{
				if(i != 0)
				{
					tempNode = tempNode.getNext();
				}
				if(i == index - 1)
				{
					newNode.setPrevious(tempNode);
					newNode.setNext(tempNode.getNext());
					tempNode.getNext().setPrevious(newNode);
					tempNode.setNext(newNode);
					size++;
				}
			}
		}
	}

	public void push(E val)
	{
		add(val);
	}

	public E pop()
	{
		if(size == 0)
		{
			return null;
		}
		else
		{
			SuperNode<E> tempEnd = end;
			SuperNode<E> temp = end.getPrevious();
			end = null;
			end = temp;
			size--;
			return tempEnd.getValue();
		}
	}

	public E poll()
	{
		if(size == 0)
		{
			return null;
		}
		else
		{
			SuperNode<E> tempRoot = root;
			SuperNode<E> temp = root.getNext();
			root = null;
			root = temp;
			size--;
			return tempRoot.getValue();
		}
	}

	public E stackPeek()
	{
		if(size == 0)
			return null;
		return end.getValue();
	}

	public E queuePeek()
	{
		if(size == 0)
			return null;
		return root.getValue();
	}

	public E get(int index)
	{
		SuperNode<E> tempNode = null;
		if(index < 0 || index > size - 1)
		{
			System.out.println("Array Index Out of Bounds Exception for Index " + index);
		}
		else
		{
			tempNode = root;
			for(int i = 0; i < index; i++)
			{
				tempNode = tempNode.getNext();
			}
		}
		return tempNode.getValue();
	}

	public E set(int index, E val)
	{
		E tempStore = null;
		if(index < 0 || index > size - 1)
		{
			System.out.println("Array Index Out of Bounds Exception for Index " + index);
			return null;
		}
		else
		{
			SuperNode<E> tempNode = root;
			for(int i = 0; i < index; i++)
			{
				tempNode = tempNode.getNext();
			}
			tempStore = tempNode.getValue();
			tempNode.value = val;
			return tempStore;
		}
	}

	public int size()
	{
		return size;
	}

	public E remove(int index)
	{
		E tempStore = root.getValue();
		if(index < 0 || index > size)
		{
			System.out.println("Array Index Out of Bounds Exception for Index " + index);
			return null;
		}
		else if(index == 0)
		{
			tempStore = root.getValue();
			root.getNext().setPrevious(null);
			root = root.getNext();
			size--;
		}
		else if(index == size - 1)
		{
			tempStore = end.getValue();
			end.getPrevious().setNext(null);
			end = end.getPrevious();
			size--;
		}
		else
		{
			SuperNode<E> tempNode = root;
			for(int i = 0; i < index; i++)
			{
				tempNode = tempNode.getNext();
			}
			tempStore = tempNode.getValue();
			tempNode.getNext().setPrevious(tempNode.getPrevious());
			tempNode.getPrevious().setNext(tempNode.getNext());
			size--;
		}
		return tempStore;
	}

	public boolean isEmpty()
	{
		return (size == 0);
	}

	public void clear()
	{
		root = null;
		end = null;
		size = 0;
	}

	public boolean contains(E val)
	{
		SuperNode<E> tempNode = root;
		for(int i = 0; i < size; i++)
		{
			tempNode = tempNode.getNext();
			if(tempNode.value == val)
			{
				return true;
			}
		}
		return false;
	}

	public String toString()
	{
		if(size == 0)
		{
			return "[]";
		}
		else if(size == 1)
		{
			return "[" + String.valueOf(root.getValue()) + "]";
		}
		else
		{
			String temp = "[";
			SuperNode<E> currentNode = root;
			while(currentNode != end)
			{
				temp += String.valueOf(currentNode.getValue()) + ", ";
				currentNode = currentNode.getNext();
			}
			temp += String.valueOf(end.getValue()) + "]";
			return temp;
		}
	}

	public class SuperNode<E>
	{
		private E value;
		private SuperNode<E> next;
		private SuperNode<E> previous;

		public SuperNode(E val)
		{
			value = val;
			next = null;
			previous = null;
		}

		public void setNext(SuperNode<E> next)
		{
			this.next = next;
		}

		public void setPrevious(SuperNode<E> previous)
		{
			this.previous = previous;
		}

		public SuperNode<E> getNext()
		{
			return next;
		}

		public SuperNode<E> getPrevious()
		{
			return previous;
		}

		public E getValue()
		{
			return value;
		}

		public boolean hasNext()
		{
			return (next != null);
		}

		public boolean hasPrevious()
		{
			return (previous != null);
		}
	}
}

/*
public static void main(String[]args)
{
	SuperList<E> list = new SuperList<E>();

	list.add(7);
	list.add(0,7);
	list.push(2);
	System.out.println(list.size());
	System.out.println(list);

	list.add(list.peekStack());
	list.push(list.peekQueue());
	list.add(list.pop());

	System.out.println(list.pop());
}
*/