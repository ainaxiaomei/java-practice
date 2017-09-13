package com.practice.customize;

public class Node<T> {

	private Node<T> next;

	private T value;

	Node(T value) {
		this.value = value;
		this.next = null;
	}

	public void add(T value) {
		if (this.next == null) {
			this.next = new Node(value);
			return;
		}

		Node n = next;
		while ((n.next) != null) {
			n = n.next;
		}

		n.next = new Node(value);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.value);
		Node n = this.next;
		while (n != null) {
			sb.append(" , ").append(n.value);
			n = n.next;
		}
		return "Node [ " + sb.toString() + " ]";
	}

	public static void main(String[] args) {
		Node<String> node = new Node<String>("a");
		node.add("b");
		node.add("c");
		node.add("d");
		node.add("e");
		System.out.println(node);
	}

}
