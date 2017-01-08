package br.com.tatica.entities;

// definição de um nó, com um nome e um id para ser encontrado no grafo
public class Node {

	private Integer id;
	private String name;

	public Node(String name, Integer id) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Node " + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
}
