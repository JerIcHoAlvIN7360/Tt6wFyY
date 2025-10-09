// 代码生成时间: 2025-10-10 01:46:30
package com.example.crossborder;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// 跨境电商平台服务端点
@Path("/crossborder")
@RegisterForReflection
public class CrossBorderECommerceService {

    // 使用AtomicLong来模拟数据库中的ID自增
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    // 存储商品信息的列表
    private List<Product> products = new ArrayList<>();

    // 商品实体类
    public static class Product {
        private final long id;
        private String name;
        private double price;
        private String description;

        public Product(String name, double price, String description) {
            this.id = ID_GENERATOR.incrementAndGet();
            this.name = name;
            this.price = price;
            this.description = description;
        }

        // Getter和Setter方法
        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    // 获取所有商品信息
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return products;
    }

    // 添加新商品
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        products.add(product);
        return Response.ok(product).build();
    }

    // 根据ID获取商品信息
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        return Response.ok(product).build();
    }

    // 更新商品信息
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id\) long id, Product product) {
        Product existingProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        return Response.ok(existingProduct).build();
    }

    // 删除商品信息
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        Product productToRemove = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (productToRemove == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        products.remove(productToRemove);
        return Response.ok().build();
    }
}
