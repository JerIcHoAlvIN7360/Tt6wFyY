// 代码生成时间: 2025-10-09 01:49:27
package com.example.treestructure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TreeComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // The name of the component

    @OneToMany(mappedBy = "parent")
    private Set<TreeComponent> children = new HashSet<>();

    // The parent of this component
    private TreeComponent parent;

    // Default constructor
    public TreeComponent() {
    }

    // Constructor with name
    public TreeComponent(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TreeComponent> getChildren() {
        return children;
    }

    public void setChildren(Set<TreeComponent> children) {
        this.children = children;
    }

    public TreeComponent getParent() {
        return parent;
    }

    public void setParent(TreeComponent parent) {
        this.parent = parent;
    }

    // Add a child component to this node
    public void addChild(TreeComponent child) {
        if (child != null) {
            child.setParent(this);
            this.children.add(child);
        } else {
            throw new IllegalArgumentException("Child component cannot be null");
        }
    }

    // Remove a child component from this node
    public boolean removeChild(TreeComponent child) {
        return this.children.remove(child);
    }

    // Helper method to recursively print the tree structure
    public void printTree(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent + this.name);
        for (TreeComponent child : this.children) {
            child.printTree(level + 1);
        }
    }
}
