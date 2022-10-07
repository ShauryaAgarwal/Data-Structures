public class TreeSet<E extends Comparable<E>>
{
	private TreeNode<E> root;
	private int size;
	private String st = "";

	public TreeSet()
	{
		root = null;
		size = 0;
	}

	public void add(E value)
	{
		if(root == null)
		{
		 	root = new TreeNode<>(value);
		   	size = 1;
		}
		else
		{
		 	add(root, value);
        }
	}

	public void add(TreeNode<E> root, E value)
	{
		if(root == null)
		{
			root.setValue(value);
		}
		else if(value.compareTo(root.getValue()) < 0)
		{
			if(root.getLeft() == null)
			{
				root.setLeft(new TreeNode<E> (value));
				size++;
			}
			else
			{
				add(root.getLeft(), value);
			}
		}
		else if(value.compareTo(root.getValue()) > 0)
		{
			if(root.getRight() == null)
			{
				root.setRight(new TreeNode<E> (value));
				size++;
			}
			else
			{
				add(root.getRight(), value);
			}
		}
	}

	public boolean contains(TreeNode<E> root, E value)
	{
		if(root == null)
		{
			return false;
		}
		if(root.getValue().equals(value))
		{
			return true;
		}
		if(value.compareTo(root.getValue()) < 0)
		{
			return contains(root.getLeft(), value);
		}
		return contains(root.getRight(), value);
	}

	public E minValue(TreeNode<E> root)
	{
		if(root.getLeft() == null)
		{
			return root.getValue();
		}
		return minValue(root.getLeft());
	}

	public void remove(E value)
	{
		if(root == null)
		{
		 	return;
		}
		else if(contains(root, value))
		{
		   	if(root.getLeft() == null && root.getRight() == null)
		   	{
		    	root = null;
		       	size = 0;
		   	}
		   	else
		   	{
		     	size--;
		      	root = remove(root, value);
		  	}
        }
	}

	public TreeNode<E> remove(TreeNode<E> root, E value)
	{
		if (root == null)
		{
			return null;
		}
		if(value.compareTo(root.getValue()) < 0)
		{
			root.setLeft(remove(root.getLeft(), value));
		}
		else if(value.compareTo(root.getValue()) > 0)
		{
			root.setRight(remove(root.getRight(), value));
		}
		else
		{
			if(root.getLeft() == null && root.getRight() == null)
			{
				root = null;
			}
			else if(root.getLeft() == null)
			{
				return root.getRight();
			}
			else if(root.getRight() == null)
			{
				return root.getLeft();
			}
			else
			{
				E minValueTemp = minValue(root.getRight());
				root.setValue(minValueTemp);
				root.setRight(remove(root.getRight(), minValueTemp));
			}
		}
        return root;
	}

	public int size()
	{
		return size;
	}

	public String preOrder()
	{
		st = "";
		if (size == 0)
		{
		  	return "[]";
		}
		else
		{
		  	st += "[";
		   	preOrder(root);
		}
		st = st.substring(0, st.length() - 2);
		st += "]";
        return st;
	}

	public void preOrder(TreeNode<E> root)
	{
		if (root != null)
		{
			st += root.getValue() + ", ";
			preOrder(root.getLeft());
			preOrder(root.getRight());
        }
	}

	public String inOrder()
	{
		st = "";
		if(size == 0)
		{
			return "[]";
		}
		else
		{
			st += "[";
			inOrder(root);
		}
		st = st.substring(0, st.length() - 2);
		st += "]";
        return st;
	}

	public void inOrder(TreeNode<E> root)
	{
		if(root != null)
		{
			inOrder(root.getLeft());
			st += root.getValue() + ", ";
			inOrder(root.getRight());
        }
	}

	public String postOrder()
	{
		st = "";
		if(size == 0)
		{
			return "[]";
		}
		else
		{
			st += "[";
			postOrder(root);
		}
		st = st.substring(0, st.length() - 2);
		st += "]";
        return st;
	}

	public void postOrder(TreeNode<E> root)
	{
		if(root != null)
		{
			postOrder(root.getLeft());
			postOrder(root.getRight());
			st += root.getValue() + ", ";
        }
	}

	public class TreeNode<E extends Comparable<E>>
	{
		private E value;
		private TreeNode<E> right;
		private TreeNode<E> left;

		public TreeNode(E value)
		{
			this.value = value;
			right = null;
			left = null;
		}

		public TreeNode<E> getRight()
		{
			return right;
		}

		public TreeNode<E> getLeft()
		{
			return left;
		}

		public void setRight(TreeNode<E> node)
		{
			right = node;
		}

		public void setLeft(TreeNode<E> node)
		{
			left = node;
		}

		public E getValue()
		{
			return value;
		}

		public void setValue(E val)
		{
			value = val;
		}

		public String toString()
		{
			return "" + value;
		}
	}
}